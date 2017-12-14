package com.lan.common.config;

import com.lan.common.util.MyCorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * package com.lan.common.config
 *
 * @author lanzongxiao
 * @date 2017/11/2
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyCorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("myCorsFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
}
