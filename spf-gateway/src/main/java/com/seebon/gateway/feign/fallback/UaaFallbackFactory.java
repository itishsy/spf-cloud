package com.seebon.gateway.feign.fallback;

import com.seebon.gateway.feign.UaaService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UaaFallbackFactory implements FallbackFactory<UaaService> {

    @Override
    public UaaService create(Throwable throwable) {
        return new UaaService() {
            @Override
            public List<String> findResourceByUsername(String username) {
                log.error("调用{}异常{}","findResourceByUsername",username);
                throwable.printStackTrace();
                return new ArrayList<>();
            }

            @Override
            public List<String> findResourceByRole(String role) {
                log.error("调用{}异常{}","findResourceByRole",role);
                throwable.printStackTrace();
                return new ArrayList<>();
            }
        };
    }
}
