package co.horn

import akka.actor.ActorSystem
import akka.stream.{Attributes, Materializer}
import akka.stream.scaladsl.{FileIO, Framing}
import akka.util.ByteString
import za.co.monadic.scopus.opus.OpusDecoderFloat
import za.co.monadic.scopus.{ArrayConversion, Sf48000}

import java.nio.ByteOrder
import java.nio.file.Path

object ScopusTest extends App {

  implicit val system     = ActorSystem("stereo-opus")
  implicit val mat        = Materializer(system)
  private val byteOrder   = ByteOrder.BIG_ENDIAN
  private val lengthField = 4

  def _test(in: String, out: String, channels: Int) = {
    val dec = OpusDecoderFloat(Sf48000, channels)

    FileIO
      .fromPath(Path.of(in))
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
      .runWith(FileIO.toPath(Path.of(out)))
  }

  _test("stereo.rtp", "stereo_to_stereo.raw", 2)
  _test("stereo.rtp", "stereo_to_mono.raw", 1)
  _test("mono.rtp", "mono.raw", 1)
  _test("mono.rtp", "mono_to_stereo.raw", 2)

}
