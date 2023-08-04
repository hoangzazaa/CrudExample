package vn.vnpt.stc.ol.apigw.api.endpoints;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import vn.vnpt.stc.ol.apigw.amqpDto.OneLinkReq;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.api.services.DeviceService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/deviceConfiguration")
@Api(description = "Device configuration", value = "Device configuration")
@Slf4j
public class DeviceEndpoint extends BaseAsyncEndpoint {

    @Autowired
    private DeviceService deviceService;

    public DeviceEndpoint(@Value("${timeoutAsyncResponse}") long timeoutInSecond) {
        super(timeoutInSecond);
    }

    @PostMapping
    public OneDeferredResult<?> execute(@RequestBody OneLinkReq<?> req, HttpServletRequest request) {
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        String method ="POST";
        executor.execute(() -> deviceService.sendPostCommand(oneDeferredResult, req));
        return oneDeferredResult;
    }

    @GetMapping
    public OneDeferredResult<?> get(@RequestBody OneLinkReq<?> req, HttpServletRequest request) {
        String command = "xxxxx";
        String method ="get";
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> deviceService.sendPostCommand(oneDeferredResult, req));
        return oneDeferredResult;
    }
}
