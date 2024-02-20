package com.core.toy3.src.member.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("---- start");

    var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
    var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

    chain.doFilter(req, res);

    var reqJson = new String(req.getContentAsByteArray());
    log.info("req: {}", reqJson);
    var resJson = new String(res.getContentAsByteArray());
    log.info("res: {}", resJson);

    log.info("end ----");

    res.copyBodyToResponse();
  }
}