package br.com.medical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.medical.exeption.AccessUnauthorizedException;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String serviceRequest = request.getServletPath();

		if (serviceRequest.equals("/users/signup") || serviceRequest.equals("/users/signin")) {
			return true;
		}

		if (session.getAttribute("user") != null) {
			return true;
		}

		throw new AccessUnauthorizedException("Acesso negado!");
	}

}
