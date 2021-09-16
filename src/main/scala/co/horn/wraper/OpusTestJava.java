package co.horn.wraper;

import com.sun.jna.ptr.PointerByReference;
import tomp2p.opuswrapper.Opus;

public abstract class OpusTestJava implements Opus {
    public int opus_custom_decoder_ctl(PointerByReference st, int request, Object... varargs) {
        return 0;
    }

    public int opus_custom_encoder_ctl(PointerByReference st, int request, Object... varargs) {
        return 0;
    }

    public int opus_decoder_ctl(PointerByReference st, int request, Object... varargs) {
        return 0;
    }

    public int opus_encoder_ctl(PointerByReference st, int request, Object... varargs) {
        return 0;
    }

    public int opus_multistream_encoder_ctl(PointerByReference st, int request, Object... varargs) {
        return 0;
    }
}
