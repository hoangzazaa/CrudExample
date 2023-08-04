package vn.vnpt.stc.ol.apigw.api.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vnpt.stc.ol.apigw.amqpDto.GetTokenDTO;
import vn.vnpt.stc.ol.apigw.amqpDto.RegisterInfoDTO;
import vn.vnpt.stc.ol.apigw.api.models.ApiResponse;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.api.services.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/auth")
@Slf4j
@Api(description = "AuthorizeEndpoint", value = "AuthorizeEndpoint")
public class AuthorizeEndpoint extends BaseAsyncEndpoint {

    @Autowired
    private AuthService authService;

    public AuthorizeEndpoint(@Value("${timeoutAsyncResponse}") long timeoutInSecond) {
        super(timeoutInSecond);
    }

    @PostMapping("/register")
    public OneDeferredResult<ResponseEntity<ApiResponse<?>>> register(@RequestBody RegisterInfoDTO registerInfoDTO, HttpServletRequest request) {
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> authService.register(oneDeferredResult, registerInfoDTO));
        return oneDeferredResult;
    }

    @PostMapping("/token")
    public OneDeferredResult<ResponseEntity<ApiResponse<?>>> getToken(@RequestBody GetTokenDTO getTokenDTO, HttpServletRequest request) {
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> authService.getToken(oneDeferredResult, getTokenDTO));
        return oneDeferredResult;
    }
}
