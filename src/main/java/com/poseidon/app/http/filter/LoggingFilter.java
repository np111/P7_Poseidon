package com.poseidon.app.http.filter;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
@Scope("singleton")
@Order(SecurityProperties.DEFAULT_FILTER_ORDER - 1) // just before spring-security filters
public class LoggingFilter extends AbstractRequestLoggingFilter {
    public LoggingFilter() {
        setIncludeHeaders(false);
        setIncludeClientInfo(true);
        setIncludeQueryString(true);
        setIncludePayload(false);
        setMaxPayloadLength(10 * 1024);
        setBeforeMessagePrefix("HTTP: ");
        setBeforeMessageSuffix("");
    }

    protected boolean shouldLog(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return !uri.startsWith("/css/") && !uri.startsWith("/static/") && !uri.equals("/favicon.ico");
    }

    protected void beforeRequest(HttpServletRequest request, String message) {
        this.logger.info(message);
    }

    protected void afterRequest(HttpServletRequest request, String message) {
    }
}
