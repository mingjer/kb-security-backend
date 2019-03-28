package com.ppdai.qa.batme.server.filter;

import com.ppdai.qa.batme.server.security.XssParameterRequestWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class XssFilter extends OncePerRequestFilter {
    @Value("${security.xss.filter:#{false}}")
    private Boolean xssFilterOpen;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (xssFilterOpen) {
            XssParameterRequestWrapper xssParameterRequestWrapper = new XssParameterRequestWrapper(httpServletRequest);
            filterChain.doFilter(xssParameterRequestWrapper, httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
