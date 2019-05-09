package com.seebon.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * 自定义一个pre 类型的ZuulFilter
 */
@Component
public class PreRequestFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PreRequestFilter.class);


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;//int值来定义过滤器的执行顺序，数值越小优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LOG.info("receive a {} request from {} route to {} path: {}",
                request.getMethod(),
                request.getRequestURL().toString(),
                ctx.getOrDefault(SERVICE_ID_KEY,""),
                ctx.getOrDefault(REQUEST_URI_KEY,""));
        return null;
    }


}