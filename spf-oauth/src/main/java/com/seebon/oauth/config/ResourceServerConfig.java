package com.seebon.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 获取Token用户信息，必须启用ResourceServer。
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private IgnorePropertiesConfig ignorePropertiesConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        for (String au : ignorePropertiesConfig.getUrls()) {
//            http.authorizeRequests().antMatchers(au).permitAll();
//        }
//
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/resource/**").permitAll();
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/resource/**").permitAll()
//                .and()
//                .httpBasic();
    }
}