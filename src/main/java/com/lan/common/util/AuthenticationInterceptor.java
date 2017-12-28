package com.lan.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lan.common.annotation.OpenApi;
import com.lan.ichat.model.AdminEntity;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    @Autowired
    private TokenService tokenService;
    private String tokenName;
    public static final String USER_KEY = "chatId";

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
        String token = request.getHeader(this.getTokenName());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(this.getTokenName());
        }
        if (StringUtils.isEmpty(token)) {
            responseReturn(IChatStatus.TOKEN_EMPTY, response);
            return false;
        }
        Object userObj = tokenService.get(token);
        if (userObj == null) {
            responseReturn(IChatStatus.TOKEN_INVALID, response);
            return false;
        }

        // 将登录用户的id存入request，当有@LoginUser注解时，通过id拉取用户信息
        if (userObj instanceof UserEntity) {
            request.setAttribute(USER_KEY, ((UserEntity) userObj).getChatId());
            // 禁止非管理员用户访问/console/**
            if (request.getRequestURI().startsWith("/api/console")) {
                responseReturn(IChatStatus.OVERSTEP_AUTHORITY, response);
                return false;
            }
            return true;
        }

        if (userObj instanceof AdminEntity) {
            return true;
        } else {
            responseReturn(IChatStatus.TOKEN_INVALID, response);
            return false;
        }
    }

    private void responseReturn(IChatStatus status, HttpServletResponse response) throws IOException {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        ObjectMapper om = new ObjectMapper();
        logger.error(status.toString());
        response.setContentType("application/json;charset=utf-8");
        om.writeValue(response.getWriter(), baseResult);
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
