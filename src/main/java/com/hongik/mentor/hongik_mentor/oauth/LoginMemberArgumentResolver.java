package com.hongik.mentor.hongik_mentor.oauth;

import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
@RequiredArgsConstructor
@Component  //ArgumentResolver를 스프링 빈으로 등록
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    //해당 파라미터를 LoginMemberArgumentResolver가 처리 가능한 대상인지 판별
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //@LoginMember 어노테이션이 있음
        boolean isLoginMemberAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
        //그 어노테이션이 SessionMember에 붙어있음
        boolean isSessionMemberClass = SessionMember.class.equals(parameter.getParameterType());

        return isLoginMemberAnnotation && isSessionMemberClass;

    }

    //처리 대상이 맞으면 -> 파라미터에 의존성 주입
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("sessionMember");
    }
}
