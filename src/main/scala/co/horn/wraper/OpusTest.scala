package co.horn.wraper

import com.sun.jna.Pointer
import com.sun.jna.ptr.{FloatByReference, IntByReference, PointerByReference, ShortByReference}

import java.nio.{ByteBuffer, FloatBuffer, IntBuffer, ShortBuffer}

object OpusTest extends OpusTestJava {
  @native
  override def opus_encoder_get_size(channels: Int): Int

  @native
  override def opus_encoder_create(Fs: Int, channels: Int, application: Int, error: IntBuffer): PointerByReference

  @native
  override def opus_encoder_init(st: PointerByReference, Fs: Int, channels: Int, application: Int): Int

  @native
  override def opus_encode(
      st: PointerByReference,
      pcm: ShortBuffer,
      frame_size: Int,
      data: ByteBuffer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_encode(
      st: PointerByReference,
      pcm: ShortByReference,
      frame_size: Int,
      data: Pointer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_encode_float(
      st: PointerByReference,
      pcm: Array[Float],
      frame_size: Int,
      data: ByteBuffer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_encode_float(
      st: PointerByReference,
      pcm: FloatByReference,
      frame_size: Int,
      data: Pointer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_encoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_decoder_get_size(channels: Int): Int

  @native
  override def opus_decoder_create(Fs: Int, channels: Int, error: IntBuffer): PointerByReference

  @native
  override def opus_decoder_init(st: PointerByReference, Fs: Int, channels: Int): Int

  @native
  override def opus_decode(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: ShortBuffer,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_decode(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: ShortByReference,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_decode_float(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: FloatBuffer,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_decode_float(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: FloatByReference,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_decoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_packet_parse(
      data: Array[Byte],
      len: Int,
      out_toc: ByteBuffer,
      frames: Array[Byte],
      size: ShortBuffer,
      payload_offset: IntBuffer
  ): Int

  @native
  override def opus_packet_get_bandwidth(data: Array[Byte]): Int

  @native
  override def opus_packet_get_samples_per_frame(data: Array[Byte], Fs: Int): Int

  @native
  override def opus_packet_get_nb_channels(data: Array[Byte]): Int

  @native
  override def opus_packet_get_nb_frames(packet: Array[Byte], len: Int): Int

  @native
  override def opus_packet_get_nb_samples(packet: Array[Byte], len: Int, Fs: Int): Int

  @native
  override def opus_decoder_get_nb_samples(dec: PointerByReference, packet: Array[Byte], len: Int): Int

  @native
  override def opus_decoder_get_nb_samples(dec: PointerByReference, packet: Pointer, len: Int): Int

  @native
  override def opus_pcm_soft_clip(pcm: FloatBuffer, frame_size: Int, channels: Int, softclip_mem: FloatBuffer): Unit

  @native
  override def opus_repacketizer_get_size(): Int

  @native
  override def opus_repacketizer_init(rp: PointerByReference): PointerByReference

  @native
  override def opus_repacketizer_create(): PointerByReference

  @native
  override def opus_repacketizer_destroy(rp: PointerByReference): Unit

  @native
  override def opus_repacketizer_cat(rp: PointerByReference, data: Array[Byte], len: Int): Int

  @native
  override def opus_repacketizer_cat(rp: PointerByReference, data: Pointer, len: Int): Int

  @native
  override def opus_repacketizer_out_range(
      rp: PointerByReference,
      begin: Int,
      end: Int,
      data: ByteBuffer,
      maxlen: Int
  ): Int

  @native
  override def opus_repacketizer_out_range(
      rp: PointerByReference,
      begin: Int,
      end: Int,
      data: Pointer,
      maxlen: Int
  ): Int

  @native
  override def opus_repacketizer_get_nb_frames(rp: PointerByReference): Int

  @native
  override def opus_repacketizer_out(rp: PointerByReference, data: ByteBuffer, maxlen: Int): Int

  @native
  override def opus_repacketizer_out(rp: PointerByReference, data: Pointer, maxlen: Int): Int

  @native
  override def opus_packet_pad(data: ByteBuffer, len: Int, new_len: Int): Int

  @native
  override def opus_packet_unpad(data: ByteBuffer, len: Int): Int

  @native
  override def opus_multistream_packet_pad(data: ByteBuffer, len: Int, new_len: Int, nb_streams: Int): Int

  @native
  override def opus_multistream_packet_unpad(data: ByteBuffer, len: Int, nb_streams: Int): Int

  @native
  override def opus_strerror(error: Int): String

  @native
  override def opus_get_version_string(): String

  @native
  override def opus_multistream_encoder_get_size(streams: Int, coupled_streams: Int): Int

  @native
  override def opus_multistream_surround_encoder_get_size(channels: Int, mapping_family: Int): Int

  @native
  override def opus_multistream_encoder_create(
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Array[Byte],
      application: Int,
      error: IntBuffer
  ): PointerByReference

  @native
  override def opus_multistream_surround_encoder_create(
      Fs: Int,
      channels: Int,
      mapping_family: Int,
      streams: IntBuffer,
      coupled_streams: IntBuffer,
      mapping: ByteBuffer,
      application: Int,
      error: IntBuffer
  ): PointerByReference

  @native
  override def opus_multistream_encoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Array[Byte],
      application: Int
  ): Int

  @native
  override def opus_multistream_encoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Pointer,
      application: Int
  ): Int

  @native
  override def opus_multistream_surround_encoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      mapping_family: Int,
      streams: IntBuffer,
      coupled_streams: IntBuffer,
      mapping: ByteBuffer,
      application: Int
  ): Int

  @native
  override def opus_multistream_surround_encoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      mapping_family: Int,
      streams: IntByReference,
      coupled_streams: IntByReference,
      mapping: Pointer,
      application: Int
  ): Int

  @native
  override def opus_multistream_encode(
      st: PointerByReference,
      pcm: ShortBuffer,
      frame_size: Int,
      data: ByteBuffer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_multistream_encode(
      st: PointerByReference,
      pcm: ShortByReference,
      frame_size: Int,
      data: Pointer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_multistream_encode_float(
      st: PointerByReference,
      pcm: Array[Float],
      frame_size: Int,
      data: ByteBuffer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_multistream_encode_float(
      st: PointerByReference,
      pcm: FloatByReference,
      frame_size: Int,
      data: Pointer,
      max_data_bytes: Int
  ): Int

  @native
  override def opus_multistream_encoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_multistream_decoder_get_size(streams: Int, coupled_streams: Int): Int

  @native
  override def opus_multistream_decoder_create(
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Array[Byte],
      error: IntBuffer
  ): PointerByReference

  @native
  override def opus_multistream_decoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Array[Byte]
  ): Int

  @native
  override def opus_multistream_decoder_init(
      st: PointerByReference,
      Fs: Int,
      channels: Int,
      streams: Int,
      coupled_streams: Int,
      mapping: Pointer
  ): Int

  @native
  override def opus_multistream_decode(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: ShortBuffer,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_multistream_decode(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: ShortByReference,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_multistream_decode_float(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: FloatBuffer,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_multistream_decode_float(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: FloatByReference,
      frame_size: Int,
      decode_fec: Int
  ): Int

  @native
  override def opus_multistream_decoder_ctl(st: PointerByReference, request: Int, varargs: Any*): Int

  @native
  override def opus_multistream_decoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_custom_mode_create(Fs: Int, frame_size: Int, error: IntBuffer): PointerByReference

  @native
  override def opus_custom_mode_destroy(mode: PointerByReference): Unit

  @native
  override def opus_custom_encoder_get_size(mode: PointerByReference, channels: Int): Int

  @native
  override def opus_custom_encoder_create(mode: PointerByReference, channels: Int, error: IntBuffer): PointerByReference

  @native
  override def opus_custom_encoder_create(
      mode: PointerByReference,
      channels: Int,
      error: IntByReference
  ): PointerByReference

  @native
  override def opus_custom_encoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_custom_encode_float(
      st: PointerByReference,
      pcm: Array[Float],
      frame_size: Int,
      compressed: ByteBuffer,
      maxCompressedBytes: Int
  ): Int

  @native
  override def opus_custom_encode_float(
      st: PointerByReference,
      pcm: FloatByReference,
      frame_size: Int,
      compressed: Pointer,
      maxCompressedBytes: Int
  ): Int

  @native
  override def opus_custom_encode(
      st: PointerByReference,
      pcm: ShortBuffer,
      frame_size: Int,
      compressed: ByteBuffer,
      maxCompressedBytes: Int
  ): Int

  @native
  override def opus_custom_encode(
      st: PointerByReference,
      pcm: ShortByReference,
      frame_size: Int,
      compressed: Pointer,
      maxCompressedBytes: Int
  ): Int

  @native
  override def opus_custom_decoder_get_size(mode: PointerByReference, channels: Int): Int

  @native
  override def opus_custom_decoder_init(st: PointerByReference, mode: PointerByReference, channels: Int): Int

  @native
  override def opus_custom_decoder_create(mode: PointerByReference, channels: Int, error: IntBuffer): PointerByReference

  @native
  override def opus_custom_decoder_create(
      mode: PointerByReference,
      channels: Int,
      error: IntByReference
  ): PointerByReference

  @native
  override def opus_custom_decoder_destroy(st: PointerByReference): Unit

  @native
  override def opus_custom_decode_float(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: FloatBuffer,
      frame_size: Int
  ): Int

  @native
  override def opus_custom_decode_float(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: FloatByReference,
      frame_size: Int
  ): Int

  @native
  override def opus_custom_decode(
      st: PointerByReference,
      data: Array[Byte],
      len: Int,
      pcm: ShortBuffer,
      frame_size: Int
  ): Int

  @native
  override def opus_custom_decode(
      st: PointerByReference,
      data: Pointer,
      len: Int,
      pcm: ShortByReference,
      frame_size: Int
  ): Int
}
