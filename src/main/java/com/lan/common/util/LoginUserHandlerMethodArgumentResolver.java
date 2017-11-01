package com.lan.common.util;

import com.lan.common.annotation.LoginUser;
import com.lan.common.annotation.Token;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) || methodParameter.hasParameterAnnotation(Token.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        // token in request
        Object obj = request.getAttribute(AuthenticationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (obj == null) {
            return null;
        }
        if (methodParameter.hasParameterAnnotation(LoginUser.class)) {
            UserEntity user = userService.getUserById((Long) obj);
            return user;
        } else if (methodParameter.hasParameterAnnotation(Token.class)) {
            return obj;
        } else {
            return null;
        }

    }
}
