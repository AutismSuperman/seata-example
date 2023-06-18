package com.example.config;

import com.example.interceptor.SeataRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Configuration
public class SeataRestTemplateAutoConfiguration {

    @Autowired(required = false)
    private Collection<RestTemplate> restTemplates;

    /**
     * 处理RestTemplate拦截器
     */
    @PostConstruct
    public void init() {
        if (this.restTemplates != null) {
            Iterator var1 = this.restTemplates.iterator();
            while (var1.hasNext()) {
                RestTemplate restTemplate = (RestTemplate) var1.next();
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList(restTemplate.getInterceptors());
                interceptors.add(new SeataRestTemplateInterceptor());
                restTemplate.setInterceptors(interceptors);
            }
        }

    }
}