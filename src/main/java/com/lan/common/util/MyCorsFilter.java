package com.lan.common.util;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * package com.lan.common.util
 *
 * @author lanzongxiao
 * @date 2017/11/2
 */
public class MyCorsFilter extends CorsFilter {

    public MyCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // 为了方便使用postman调试，设置为*,部署时修改为后台的url
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/console/**", config); // 设置只有后台的api会进行CORS跨域，限制可以访问的origin
        return source;
    }
}
