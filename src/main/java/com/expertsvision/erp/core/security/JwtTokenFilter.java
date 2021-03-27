package com.expertsvision.erp.core.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;

import com.expertsvision.erp.core.exception.InactiveUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.label.service.InMemoryLabelsViewService;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.message.service.InMemoryMessagesViewService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersViewService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private InMemoryUsersViewService inMemoryUsersViewService;
	
	@Autowired
	private InMemoryLabelsViewService inMemoryLabelsViewService;
	
	@Autowired
	private InMemoryMessagesViewService inMemoryMessagesViewService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().equals("/public/login")) {
			filterChain.doFilter(request, response);
			return;
		} else if (request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().startsWith("/public/")) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			String AuthorizationHeader = request.getHeader("Authorization");
			
			if (Strings.isEmpty(AuthorizationHeader) || !AuthorizationHeader.startsWith("Bearer ")) {
				handleUnauthorized(response);
				return;
			}
			
			String token = AuthorizationHeader.split(" ")[1].strip();
			
			UsersView usersView = inMemoryUsersViewService.getUsersView(jwtTokenUtil.validate(token));
			
			if (usersView.getInactive()) {
				throw new InactiveUserException();
			}
			
			Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ExpertsVisionUser"));
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usersView, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}  catch (InactiveUserException e) {
			handleInactiveUser(response);
			return;
		} catch (Exception e) {
			handleUnauthorized(response);
			return;
		}
		filterChain.doFilter(request, response);
	}
	
	
	private void handleInactiveUser (HttpServletResponse response) throws IOException {
		String labelAr = inMemoryLabelsViewService.getLabelsView(new LabelsViewPK(1, "user")).getLabelDesc();
		String labelEn = inMemoryLabelsViewService.getLabelsView(new LabelsViewPK(2, "user")).getLabelDesc();
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, "is_inactive")).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, "is_inactive")).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr.replace("#1", labelAr));
		message.get("message").put("en", messageEn.replace("#1", labelEn));
		String json = new ObjectMapper().writeValueAsString(message);
		response.setStatus(451);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
	}
	
	private void handleUnauthorized (HttpServletResponse response) throws IOException {
		String labelAr = inMemoryLabelsViewService.getLabelsView(new LabelsViewPK(1, "resource")).getLabelDesc();
		String labelEn = inMemoryLabelsViewService.getLabelsView(new LabelsViewPK(2, "resource")).getLabelDesc();
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, "donot_have_privileges")).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, "donot_have_privileges")).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr.replace("#1", labelAr));
		message.get("message").put("en", messageEn.replace("#1", labelEn));
		String json = new ObjectMapper().writeValueAsString(message);
		response.setStatus(401);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
	}

}
