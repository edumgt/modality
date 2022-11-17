package one.modality.ecommerce.payment.direct.buscall.serial;

import dev.webfx.platform.json.JsonObject;
import dev.webfx.platform.json.WritableJsonObject;
import dev.webfx.stack.com.serial.spi.impl.SerialCodecBase;
import one.modality.ecommerce.payment.direct.MakeDirectPaymentResult;

/**************************************
 *           Serial Codec             *
 * ***********************************/

public final class MakeDirectPaymentResultSerialCodec extends SerialCodecBase<MakeDirectPaymentResult> {

    private static final String CODEC_ID = "DirectPaymentResult";
    private static final String SUCCESS_KEY = "success";

    public MakeDirectPaymentResultSerialCodec() {
        super(MakeDirectPaymentResult.class, CODEC_ID);
    }

    @Override
    public void encodeToJson(MakeDirectPaymentResult arg, WritableJsonObject json) {
        json.set(SUCCESS_KEY, arg.isSuccess());
    }

    @Override
    public MakeDirectPaymentResult decodeFromJson(JsonObject json) {
        return new MakeDirectPaymentResult(
                json.getBoolean(SUCCESS_KEY)
        );
    }
}