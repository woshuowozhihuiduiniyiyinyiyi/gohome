package com.hj.tj.gohome.filter;

import com.hj.tj.gohome.utils.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域处理器
 */
@WebFilter(filterName = "corsFilter", urlPatterns = {"/gohome/*"})
@Order(100)
@Component
public class CorsFilter implements Filter {

    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "User-Agent,Origin,Cache-Control,Content-type,Date,Server," +
            "withCredentials,access_token,x_requested_with,x-requested-with,Content-Type,sysCode";

    private static final String ACCESS_CONTROL_ALLOW_METHODS = "OPTIONS,GET,POST,DELETE,PUT";

    private static final String DEFAULT_MAX_AGE = "72000";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String originHeader = request.getHeader(HttpHeaders.ORIGIN);
        if (StringUtil.isBlank(originHeader)) {
            originHeader = "*";
        }

        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, DEFAULT_MAX_AGE);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
