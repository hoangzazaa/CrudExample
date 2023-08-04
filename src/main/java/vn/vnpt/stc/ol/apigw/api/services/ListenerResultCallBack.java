package vn.vnpt.stc.ol.apigw.api.services;

import vn.vnpt.stc.ol.base.event.Event;

public interface ListenerResultCallBack {
    void onSuccess(Event success);

    default void onError(Event request, Throwable throwable) {
        // to-do
    }
}
