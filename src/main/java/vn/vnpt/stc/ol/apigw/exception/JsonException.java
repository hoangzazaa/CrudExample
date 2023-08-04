package vn.vnpt.stc.ol.apigw.exception;

import vn.vnpt.stc.ol.apigw.constants.Status;

public class JsonException extends BaseException {
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
