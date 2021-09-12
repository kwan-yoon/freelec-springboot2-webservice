package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

/*
*   @EnableWebSecurity
*   Spring Security의 설정들을 활성화시켜줌
*
*   @csrf().diable().headers().frameOptions().disable()
*   h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
*
*   authorizeRequests()
*   URL 별 권한 관리를 설정하는 옵션의 시작점임
*   authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있음
*
*   antMatchers()
*   권한 관리 대상을 지정하는 옵션
*   URL, HTTP 메소드별로 관리가 가능함
*   "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 주었음
*   "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하게함
*
*   anyRequest()
*   설정된 값들 이외에 나머지 URL들을 나타냄
*   여기서는 authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 함
*   인증된 사용자 즉, 로그인한 사용자들을 이야기함
*
*   logout().logoutSuccessUrl("/)
*   로그아웃 기능에 대한 여러 설정의 진입점임
*   로그아웃 성공 시 / 주소로 이동함
*
*   oauth2Login()
*   OAuth2 로그인 기능에 대한 여러 설정의 진입점임
*
*   userService()
*   소설 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록함
*   리소스서버(즉, 소셜서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시할 수 있음
* */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**",
                                "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
