package com.core.toy3.src.member.filter;

import com.core.toy3.common.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private ObjectMapper objectMapper;
  private String redirectUrl = "/api/auth/logged";

  @Override
  public void onAuthenticationSuccess(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication) throws IOException, ServletException {

    System.out.println("login success");

    response.sendRedirect(redirectUrl);

  }
}
