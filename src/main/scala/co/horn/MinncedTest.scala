package co.horn

import akka.actor.ActorSystem
import akka.stream.scaladsl.{FileIO, Framing}
import akka.stream.{Attributes, Materializer}
import akka.util.ByteString
import club.minnced.opus.util.OpusLibrary
import com.sun.jna.Native
import com.sun.jna.ptr.PointerByReference
import tomp2p.opuswrapper.Opus
import za.co.monadic.scopus.{ArrayConversion, Sf48000}

import java.nio.file.Path
import java.nio.{ByteOrder, FloatBuffer, IntBuffer}

object MinncedTest extends App {

  implicit val system     = ActorSystem("stereo-opus")
  implicit val mat        = Materializer(system)
  private val byteOrder   = ByteOrder.BIG_ENDIAN
  private val lengthField = 4

  val input = Path.of("stereo.rtp")
//  val input    = Path.of("mono.rtp")
  val output   = Path.of("output.raw")
  val channels = 2

  OpusLibrary.loadFromJar()
  val opus = Native.loadLibrary(System.getProperty("opus.lib"), classOf[Opus])

  lazy val fs                          = Sf48000
  val bufferLen: Int                   = 2880
  var fec                              = 0
  var error: IntBuffer                 = IntBuffer.allocate(100)
  lazy val decoder: PointerByReference = opus.opus_decoder_create(fs(), channels, error)

  val decodedBuf = new Array[Float](bufferLen)

  val buffer = FloatBuffer.allocate(bufferLen)

  lazy val dec: Array[Byte] => Array[Float] = in => {
    opus.opus_decode_float(decoder, in, in.length, buffer, 5760, 0)
    buffer.array()
  }

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
    .map(a => dec(a.iterator.toArray))
    .map(ArrayConversion.floatArrayToByteArray)
    .map(ByteString(_))
    .runWith(FileIO.toPath(output))

}
