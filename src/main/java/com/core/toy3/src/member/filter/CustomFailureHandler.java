//package com.core.toy3.src.member.filter;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomFailureHandler implements AuthenticationFailureHandler {
//
//  private final String redirectUrl = "/api/auth/error";
//  @Override
//  public void onAuthenticationFailure(HttpServletRequest request,
//                                      HttpServletResponse response,
//                                      AuthenticationException exception) throws IOException, ServletException {
//    System.out.println("login failure");
//
//    response.sendRedirect(redirectUrl);
//  }
//}
