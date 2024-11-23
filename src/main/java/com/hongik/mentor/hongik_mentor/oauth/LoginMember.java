package com.hongik.mentor.hongik_mentor.oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //어노테이션 위치 지정: 파라미터에서 사용
@Retention(RetentionPolicy.RUNTIME) //Runtime까지 어노테이션 코드 유지 필요
public @interface LoginMember {
}
