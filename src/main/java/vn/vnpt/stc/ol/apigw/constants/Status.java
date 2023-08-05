package vn.vnpt.stc.ol.apigw.constants;

public enum Status {
    /**
     * 操作成功
     */
    OK(200, "Success"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "Internal server error");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
