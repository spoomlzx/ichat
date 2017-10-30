package com.lan.common.util;

import com.lan.common.annotation.OpenApi;
import com.lan.common.exception.IChatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * package com.lan.common.util
 * 权限验证
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@ConfigurationProperties(prefix = "lan.ichat")
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private String token;
    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OpenApi annotation;
        // 如果有@OpenApi注解，则为公开api，跳过验证
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(OpenApi.class);
            if (annotation != null) {
                return true;
            }
        }
        String token = request.getHeader(this.getToken());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(this.getToken());
        }
        if (StringUtils.isEmpty(token)) {
            throw new IChatException(this.getToken() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        String str = redisTemplate.opsForValue().get(token);
        if (str == null) {
            throw new IChatException(this.getToken() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        // 将登录用户的id存入request，当有@LoginUser注解时，通过id拉取用户信息
        request.setAttribute(USER_KEY, Long.parseLong(str));
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
