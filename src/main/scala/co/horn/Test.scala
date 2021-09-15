package co.horn

import akka.actor.ActorSystem
import akka.stream.{Attributes, Materializer}
import akka.stream.scaladsl.{FileIO, Framing}
import akka.util.ByteString
import za.co.monadic.scopus.opus.OpusDecoderFloat
import za.co.monadic.scopus.{ArrayConversion, Sf48000}

import java.nio.ByteOrder
import java.nio.file.Path

object Test extends App {

  implicit val system     = ActorSystem("stereo-opus")
  implicit val mat        = Materializer(system)
  private val byteOrder   = ByteOrder.BIG_ENDIAN
  private val lengthField = 4

  val input = Path.of("stereo.rtp")
//  val input    = Path.of("mono.rtp")
  val output   = Path.of("output.raw")
  val channels = 2

  val dec = OpusDecoderFloat(Sf48000, channels)

  FileIO
    .fromPath(input)
    .log("stream")
    .addAttributes(
      Attributes.createLogLevels(
        Attributes.logLevelInfo,
        Attributes.logLevelInfo,
        Attributes.logLevelInfo
      )
    )
    .via(Framing.lengthField(lengthField, 0, 0xffff, byteOrder, (_: Array[Byte], l: Int) => l + 4))
    .map(_.drop(lengthField))
    .map(a => dec(a.iterator.toArray).get)
    .map(ArrayConversion.floatArrayToByteArray)
    .map(ByteString(_))
    .runWith(FileIO.toPath(output))

}
