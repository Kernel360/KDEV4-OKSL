package org.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
//@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info(">>>>> 진입전");

        // 1. HTTP Stream을 읽어버렸기 때문에 뒷단에서 req를 읽을 수 없다.
//        HttpServletRequestWrapper req = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
//        HttpServletResponseWrapper res = new HttpServletResponseWrapper((HttpServletResponse) servletRequest);
//
//        BufferedReader reader = req.getReader();
//        List<String> list = reader.lines().collect(Collectors.toList());
//        list.forEach(it -> {
//            log.info("{}", it);
//        });

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, servletResponse);

        String reqJson = new String(contentCachingRequestWrapper.getContentAsByteArray());
        log.info("req : {}", reqJson);
        String resJson = new String(contentCachingResponseWrapper.getContentAsByteArray());
        log.info("resp : {}", resJson);
        log.info("<<<<< 진입후");

        // Caching된 response를 반환하도록 해야한다.
        contentCachingResponseWrapper.copyBodyToResponse();
    }
}
