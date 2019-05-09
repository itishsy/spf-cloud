package com.seebon.gateway.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2018/1/9
 */
@Data
@Configuration
//@RefreshScope
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class IgnorePropertiesConfig {
    private List<String> urls = new ArrayList<>();

    private List<String> clients = new ArrayList<>();
}