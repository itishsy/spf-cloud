//package com.seebon.gateway.feign.fallback;
//
//import com.seebon.gateway.feign.UaaService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Slf4j
////@Service
//public class UaaServiceFallback implements UaaService {
//
//    @Override
//    public List<String> findResourceByUsername(String username) {
////        log.error("调用{}异常{}","findResourceByUsername",username);
//        return new ArrayList<>();
//    }
//
//    @Override
//    public List<String> findResourceByRole(String role) {
////        log.error("调用{}异常{}","findResourceByRole",role);
//        return new ArrayList<>();
//    }
//
//}
