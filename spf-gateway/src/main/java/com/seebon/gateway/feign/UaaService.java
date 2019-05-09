package com.seebon.gateway.feign;

import com.seebon.gateway.feign.fallback.UaaFallbackFactory;
//import com.seebon.gateway.feign.fallback.UaaServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( value = "spf-oauth", fallbackFactory = UaaFallbackFactory.class)
public interface UaaService {

    @GetMapping(value = "/resource/findByUser/{username}")
    List<String> findResourceByUsername(@PathVariable("username") String username);

    @GetMapping(value = "/resource/findByRole/{role}")
    List<String> findResourceByRole(@PathVariable("role") String role);

}
