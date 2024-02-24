//package com.core.toy3.src.member.filter;
//
//import com.core.toy3.src.member.model.request.MemberRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.io.IOException;
//
//// UsernamePasswordAuthenticationFilter 대체
//public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//  public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
//                                    AuthenticationSuccessHandler authenticationSuccessHandler,
//                                    AuthenticationFailureHandler authenticationFailureHandler){
//    super(new AntPathRequestMatcher(
//            "/api/auth/login"
//            , HttpMethod.POST.name()));
//    setAuthenticationManager(authenticationManager);
//    setAuthenticationSuccessHandler(authenticationSuccessHandler);
//    setAuthenticationFailureHandler(authenticationFailureHandler);
//  }
//
//  @Override
//  public Authentication attemptAuthentication(
//          HttpServletRequest request,
//          HttpServletResponse response)
//          throws AuthenticationException, IOException, ServletException {
//
//      MemberRequest memberRequest = new ObjectMapper()
//              .readValue(request.getInputStream(), MemberRequest.class);
//
//    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//            memberRequest.getUsername(),memberRequest.getPassword()
//    );
//
//    return this.getAuthenticationManager().authenticate(authToken);
//  }
//}
