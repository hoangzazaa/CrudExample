package vn.vnpt.stc.ol.apigw.api.endpoints;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.vnpt.stc.ol.apigw.api.models.OneDeferredResult;
import vn.vnpt.stc.ol.apigw.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;

public abstract class BaseAsyncEndpoint {

    protected long timeoutInSecond;

    @Autowired
    @Qualifier("taskExecutor")
    protected Executor executor;

    public BaseAsyncEndpoint(@Value("${timeoutAsyncResponse}") long timeoutInSecond) {
        this.timeoutInSecond = timeoutInSecond;
    }

    protected OneDeferredResult prepareDeferredResult(HttpServletRequest request) {
        OneDeferredResult deferredResult = new OneDeferredResult(timeoutInSecond);
        deferredResult.onTimeout(() -> {
//                    log.info("timeout request");
                    deferredResult.setResult(
                            ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                                    .body("Request timeout!"));
                }

        );

        String authorization = request.getHeader(Constants.AUTH_HEADER_STRING);
        if (!Strings.isNullOrEmpty(authorization)) {
            deferredResult.setSessionId(request.getHeader(Constants.AUTH_HEADER_STRING).substring(Constants.AUTH_TOKEN_PREFIX.length()));
        }
        return deferredResult;
    }

}
