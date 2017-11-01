package com.lan.common.util;

import com.lan.common.annotation.OpenApi;
import com.lan.common.exception.IChatException;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    private TokenService tokenService;
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
            throw new IChatException(IChatStatus.TOKEN_EMPTY);
        }
        UserEntity userEntity = tokenService.get(token);
        if (userEntity == null) {
            throw new IChatException(IChatStatus.TOKEN_INVALID);
        }
        // 1 为admin，禁止非管理员用户访问/console/**
        if (userEntity.getRoleId() != 1 && request.getRequestURI().startsWith("/console")) {
            throw new IChatException(IChatStatus.OVERSTEP_AUTHORITY);
        }
        // 将登录用户的id存入request，当有@LoginUser注解时，通过id拉取用户信息
        request.setAttribute(USER_KEY, userEntity.getId());
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
