package vn.vnpt.stc.ol.apigw.api.services;

public abstract class BaseService {
    protected String routingKey;
    protected String category;

    public BaseService(String routingKey, String category) {
        this.routingKey = routingKey;
        this.category = category;
    }
}
