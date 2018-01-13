package com.lan.common.util;

import com.lan.common.annotation.LoginUser;
import com.lan.common.annotation.Token;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * package com.lan.common.util
 * <p>
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@ConfigurationProperties(prefix = "lan.ichat")
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private String tokenName;
    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) || methodParameter.hasParameterAnnotation(Token.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        if (methodParameter.hasParameterAnnotation(LoginUser.class)) {
            // token in request
            Object obj = request.getAttribute(AuthenticationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
            if (obj == null) {
                return null;
            }
            return userService.getUserById((Long) obj);
        } else if (methodParameter.hasParameterAnnotation(Token.class)) {
            return request.getHeader(this.getTokenName());
        } else {
            return null;
        }
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
