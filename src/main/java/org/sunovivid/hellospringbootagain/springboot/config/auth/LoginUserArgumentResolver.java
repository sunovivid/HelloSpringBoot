package org.sunovivid.hellospringbootagain.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.sunovivid.hellospringbootagain.springboot.config.auth.dto.SessionUser;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    //메서드가 지원하는 파라미터인가?
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; //파라미터에 @LoginUser 어노테이션이 붙어있음
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); //파라미터 클래스 타입이 SessionUser.class임
        return isLoginUserAnnotation && isUserClass;
    }

    //파라미터에 전달할 객체 생성 (여기서는 세션에서 객체를 가져옴)
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavConatiner, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
