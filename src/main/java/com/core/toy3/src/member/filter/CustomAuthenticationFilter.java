package com.core.toy3.src.member.filter;

import com.core.toy3.src.member.model.request.MemberRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

// UsernamePasswordAuthenticationFilter 대체
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
    super(new AntPathRequestMatcher(
            "/api/auth/login"
            ,"POST"));
    setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(
          HttpServletRequest request,
          HttpServletResponse response)
          throws AuthenticationException, IOException, ServletException {

      MemberRequest memberRequest = new ObjectMapper()
              .readValue(request.getInputStream(), MemberRequest.class);

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            memberRequest.getUsername(),memberRequest.getPassword()
    );

    return this.getAuthenticationManager().authenticate(authToken);
  }
}
