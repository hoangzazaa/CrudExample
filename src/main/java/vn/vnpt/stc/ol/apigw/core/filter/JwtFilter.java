package vn.vnpt.stc.ol.apigw.core.filter;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import vn.vnpt.stc.ol.base.redis.apigw.LoginInfoCache;
import vn.vnpt.stc.ol.apigw.api.models.ApiResponse;
import vn.vnpt.stc.ol.apigw.constants.Constants;
import vn.vnpt.stc.ol.apigw.constants.Status;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setContentType("application/json");
        String authorization = httpServletRequest.getHeader(Constants.AUTH_HEADER_STRING);
        log.info("Authorization {}", authorization);
        if (authorization != null && authorization.startsWith(Constants.AUTH_TOKEN_PREFIX)) {
            String token = authorization.substring(Constants.AUTH_TOKEN_PREFIX.length());
            LoginInfoCache loginInfoCache = new LoginInfoCache();
            if (loginInfoCache == null) { // token đã hết hạn cache, cần đăng nhập lại
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(ApiResponse.ofStatus(Status.UNKNOWN_ERROR)));
                return;
            }
        } else {
            if (!httpServletRequest.getRequestURI().contains("/login") &&
                    !httpServletRequest.getRequestURI().contains("/api/auth")
                    && !httpServletRequest.getRequestURI().contains("reset-password")
                    && !httpServletRequest.getRequestURI().contains("/forgot-password")
                    && !httpServletRequest.getRequestURI().contains("/active-new-user")
                    && !httpServletRequest.getRequestURI().contains("/validate-token-mail")
                    && !httpServletRequest.getRequestURI().contains("swagger")
                    && !httpServletRequest.getRequestURI().contains("api-docs")
                    && !httpServletRequest.getRequestURI().contains("actuator")
                    && !httpServletRequest.getRequestURI().contains("email-exist")
                    && !httpServletRequest.getRequestURI().contains("/api/statistic")
            ) {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, new Gson().toJson(ApiResponse.ofStatus(Status.UNKNOWN_ERROR)));
                return;
            }
        }
        ContentCachingRequestWrapper servletWrappedRequest = new ContentCachingRequestWrapper(httpServletRequest);
        filterChain.doFilter(servletWrappedRequest, httpServletResponse);
    }
}
