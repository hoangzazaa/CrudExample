package vn.vnpt.stc.ol.apigw.api.endpoints;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.vnpt.stc.ol.apigw.amqpDto.AppDTO;
import vn.vnpt.stc.ol.apigw.amqpDto.AppVerstionDTO;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.api.services.AppService;
import vn.vnpt.stc.ol.apigw.constants.Status;
import vn.vnpt.stc.ol.apigw.exception.JsonException;
import vn.vnpt.stc.ol.apigw.exception.PageException;
import vn.vnpt.stc.ol.apigw.api.models.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/apps")
@Slf4j
@Api(description = "AppEndpoint", value = "AppEndpoint")
public class AppEndpoint extends BaseAsyncEndpoint {

    @Autowired
    private AppService appService;

    public AppEndpoint(@Value("${timeoutAsyncResponse}") int timeoutInSecond) {
        super(timeoutInSecond);
    }

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }

    @PostMapping
    public OneDeferredResult<ResponseEntity<ApiResponse<?>>> create(@RequestBody AppDTO appDTO, HttpServletRequest request) {
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> appService.create(oneDeferredResult, appDTO));
        return oneDeferredResult;
    }

    @PostMapping("/add-version")
    public OneDeferredResult<ResponseEntity<ApiResponse<?>>> addAppVersion(@RequestBody AppVerstionDTO appVerstionDTO, HttpServletRequest request) {
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> appService.addAppVersion(oneDeferredResult, appVerstionDTO));
        return oneDeferredResult;
    }
}
