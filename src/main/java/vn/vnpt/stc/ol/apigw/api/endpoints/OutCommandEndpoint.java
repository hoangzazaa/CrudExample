package vn.vnpt.stc.ol.apigw.api.endpoints;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vnpt.stc.ol.apigw.api.models.ApiResponse;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.api.models.OutCommand;
import vn.vnpt.stc.ol.apigw.api.services.OutCommandService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/out_commands")
@Slf4j
public class OutCommandEndpoint extends BaseAsyncEndpoint{
    @Autowired
    private OutCommandService outCommandService;
    public OutCommandEndpoint(@Value("${timeoutAsyncResponse}") int timeoutInSecond) {
        super(timeoutInSecond);
    }
    @GetMapping(path = "/{id}")
    public OneDeferredResult<ResponseEntity<ApiResponse<?>>> getById(@PathVariable(value = "id") Long id, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> outCommandService.getByid(id, oneDeferredResult));
        return oneDeferredResult;
    }
    @PostMapping(path = "")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> add(@RequestBody OutCommand data, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> outCommandService.create(data, oneDeferredResult));
        return oneDeferredResult;
    }
    @PutMapping(path = "")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> update(@RequestBody OutCommand data, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> outCommandService.update(data, oneDeferredResult));
        return oneDeferredResult;
    }
    @DeleteMapping(path = "/{id}")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> delete(@PathVariable  Long id, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> outCommandService.delete(id, oneDeferredResult));
        return oneDeferredResult;
    }
}
