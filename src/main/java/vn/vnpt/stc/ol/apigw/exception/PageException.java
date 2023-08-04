package vn.vnpt.stc.ol.apigw.exception;

import vn.vnpt.stc.ol.apigw.constants.Status;

public class PageException extends BaseException {
    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}
