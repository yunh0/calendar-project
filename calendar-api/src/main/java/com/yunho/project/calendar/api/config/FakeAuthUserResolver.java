package com.yunho.project.calendar.api.config;

import com.yunho.project.calendar.api.dto.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FakeAuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final String userIdStr = webRequest.getParameter("userId");
        return userIdStr == null
                ? new AuthUserResolver().resolveArgument(parameter, mavContainer, webRequest, binderFactory)
                : AuthUser.of(Long.parseLong(userIdStr));
    }
}
