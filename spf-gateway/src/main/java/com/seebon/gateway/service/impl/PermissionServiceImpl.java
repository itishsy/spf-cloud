package com.seebon.gateway.service.impl;

import com.seebon.gateway.feign.UaaService;
import com.seebon.gateway.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private UaaService uaaService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String requestUrl = request.getRequestURI();
        log.info("requestUrl:{}", requestUrl);
        log.info("principal:{}", principal);

        List<SimpleGrantedAuthority> authorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        if (principal == null || CollectionUtils.isEmpty(authorityList)) {
            return false;
        }

        for (SimpleGrantedAuthority authority : authorityList) {
            List<String> list = uaaService.findResourceByRole(authority.getAuthority());
            for (String uriPattern : list) {
                if (antPathMatcher.match(uriPattern, requestUrl)) {
                    return true;
                }
            }
        }

        return false;
    }
}