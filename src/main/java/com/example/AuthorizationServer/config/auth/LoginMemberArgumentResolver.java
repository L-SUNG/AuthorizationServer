package com.example.authorizationserver.config.auth;

import com.example.authorizationserver.config.auth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 LoginMember 어노테이션이 붙어있는것을 판정
        boolean isLoginMemberAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
        // 파라미터의 글래스타입이 SessionMember 인것을 판정
        boolean isMemberClass = SessionMember.class.equals(parameter.getParameterType());

        return isLoginMemberAnnotation && isMemberClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 세션에서 member를 취득
        return httpSession.getAttribute("member");
    }
}
