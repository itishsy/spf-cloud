package com.seebon.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;


    @Autowired
    private IgnorePropertiesConfig ignorePropertiesConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/uaa/**").permitAll()
//        .anyRequest().authenticated();

//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
        for (String au : ignorePropertiesConfig.getUrls()) {
            http.authorizeRequests().antMatchers(au).permitAll();
        }
        http.authorizeRequests().anyRequest().access("@permissionService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(expressionHandler);
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

}
