package com.ppdai.qa.batme.server.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangkun on 2018/6/14.
 */
public class UrlFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/api/")) {
            path = path.substring(4, path.length());
            httpRequest.getRequestDispatcher(path).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        return;
    }
}
