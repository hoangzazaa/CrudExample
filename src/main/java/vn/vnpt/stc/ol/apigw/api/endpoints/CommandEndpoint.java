package vn.vnpt.stc.ol.apigw.api.endpoints;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vnpt.stc.ol.apigw.api.models.ApiResponse;
import vn.vnpt.stc.ol.apigw.api.models.Command;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.api.services.CommandService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commands")
@Api(description = "Command management", value = "Command management")
@Slf4j
public class CommandEndpoint extends BaseAsyncEndpoint {

    @Autowired
    private CommandService commandService;

    public CommandEndpoint(@Value("${timeoutAsyncResponse}") int timeoutInSecond) {
        super(timeoutInSecond);
    }

    @GetMapping(path = "/{id}")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> getById(@PathVariable(value = "id") Long id, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> commandService.getByid(id, oneDeferredResult));
        return oneDeferredResult;
    }
    @PostMapping(path = "")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> add(@RequestBody Command data, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> commandService.create(data, oneDeferredResult));
        return oneDeferredResult;
    }
    @PutMapping(path = "")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> update(@RequestBody Command data, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> commandService.update(data, oneDeferredResult));
        return oneDeferredResult;
    }
    @DeleteMapping(path = "/{id}")
    public OneDeferredResult <ResponseEntity<ApiResponse<?>>> delete(@PathVariable  Long id, HttpServletRequest request) throws Exception{
        OneDeferredResult oneDeferredResult = prepareDeferredResult(request);
        executor.execute(() -> commandService.delete(id, oneDeferredResult));
        return oneDeferredResult;
    }
}
