package vn.vnpt.stc.ol.apigw.api.models;

import org.springframework.web.context.request.async.DeferredResult;

public class OneDeferredResult<T> extends DeferredResult<T> {
    private String sessionId;

    public OneDeferredResult(Long timeout) {
        super(timeout);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
