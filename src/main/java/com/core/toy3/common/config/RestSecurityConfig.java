//package com.core.toy3.common.config;
//
//import com.core.toy3.src.member.filter.CustomAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class RestSecurityConfig {
//
//  private AuthenticationSuccessHandler authenticationSuccessHandler;
//  private AuthenticationFailureHandler authenticationFailureHandler;
//  @Autowired
//  private UserDetailsService authMemberService;
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder(){
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public CustomAuthenticationFilter customAuthenticationFilter(
//          final AuthenticationManager authenticationManager,
//          AuthenticationSuccessHandler authenticationSuccessHandler,
//          AuthenticationFailureHandler authenticationFailureHandler) {
//    return new CustomAuthenticationFilter(authenticationManager,
//            authenticationSuccessHandler,
//            authenticationFailureHandler);
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//    provider.setPasswordEncoder(passwordEncoder());
//    provider.setUserDetailsService(authMemberService);
//
//    return new ProviderManager(provider);
//  }
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//    AuthenticationManager authenticationManager = authenticationManager(http);
//
//    http
//            .csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(authz -> authz
//                    .requestMatchers("/api/auth/logout").authenticated() // 인증된 상태에서만 logout 접근 가능
//
//                    // ----(추후 url 수정 예정)----
//                    .requestMatchers("/api/likes/**").authenticated() // 좋아요 api
//                    .requestMatchers("/api/travel/**").authenticated() // 여행, 여정 api
//                    .requestMatchers("/api/comment/**").authenticated() // 댓글 api
//                    .requestMatchers(toH2Console()).permitAll() // h2 console 접근 가능
//                    .anyRequest().permitAll())
//
//            .formLogin(AbstractHttpConfigurer::disable)
//            .httpBasic(AbstractHttpConfigurer::disable)
//
//            // 필터 바꿔치기
//            .addFilterAt(this.customAuthenticationFilter(authenticationManager,
//                    authenticationSuccessHandler,
//                    authenticationFailureHandler),
//                    UsernamePasswordAuthenticationFilter.class)
//
//            .headers(headers -> headers
//                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//
//            .logout(logout -> logout
//                    .logoutSuccessUrl("/api/auth/") // 로그아웃시 보내질 페이지
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout"))
//                    .clearAuthentication(true)
//                    .deleteCookies("JSESSIONID")
//                    .invalidateHttpSession(true)
//            );
//    return (SecurityFilterChain) http.build();
//  }
//}
