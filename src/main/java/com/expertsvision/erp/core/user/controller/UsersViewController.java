package com.expertsvision.erp.core.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.UsersViewService;

@RestController
@RequestMapping(value = "/users")
public class UsersViewController {
		
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private Response response;
	
	@Autowired
	private UsersViewService usersViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getUsersViewSubordinateList() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UsersView> usersViewList = usersViewService.getUsersViewSubordinateList(loginUser);
		return response.response(usersViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUsersView(@PathVariable("userId") Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UsersView usersView = usersViewService.getUsersView(loginUser, userId);
		return response.response(usersView, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addUsersView(@RequestBody UsersView usersView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Get important request headers
		String cfup = request.getHeader("COPY-FROM-USER-PRIVILEGES");
		String cptg = request.getHeader("COPY-PRIVILEGES-TO-GROUP");
		// Place holders for request header cfup integer value
		Integer cfupID = null;
		// Prevent COPY-PRIVILEGES-TO-GROUP when adding a new user
		if (cptg != null) {
			throw new ValidationException("cannot_use_copy_privileges_to_group");
		} 
		// Parsing request header cfup value
		if (cfup != null) {
			try {
				cfupID = Integer.parseInt(cfup);
			} catch (Exception e) {
				throw new ValidationException("must_be_integer", "copy_privileges_from_user");
			}
			usersViewService.addUsersView(loginUser, usersView, cfupID);
		}  else {
			usersViewService.addUsersView(loginUser, usersView, null);
		}
		return response.response("added", "user", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateUsersView(@RequestBody UsersView usersView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Get important request headers
		String cfup = request.getHeader("COPY-FROM-USER-PRIVILEGES");
		String cptg = request.getHeader("COPY-PRIVILEGES-TO-GROUP");
		String confirm = request.getHeader("CONFIRM");
		// Place holders for request header cfup integer value
		Integer cfupID = null;
		Integer cptgID = null;
		// Prevent COPY-PRIVILEGES-TO-GROUP and COPY-FROM-USER-PRIVILEGES at the same time
		if (cfup != null && cptg != null) {
			throw new ValidationException("cannot_both_copy_privileges");
		} 
		// Parsing request header cfup value
		if (cfup != null) {
			try {
				cfupID = Integer.parseInt(cfup);
			} catch (Exception e) {
				throw new ValidationException("must_be_integer", "copy_privileges_from_user");
			}
			usersViewService.updateUsersView(loginUser, usersView, cfupID, null, null);
		} else if (cptg != null) {
			try {
				cptgID = Integer.parseInt(cptg);
			} catch (Exception e) {
				throw new ValidationException("must_be_integer", "copy_privileges_to_group");
			}
			usersViewService.updateUsersView(loginUser, usersView, null, cptgID, (confirm!=null) && (confirm.equals("TRUE")));
		} else {
			usersViewService.updateUsersView(loginUser, usersView, null, null, null);
		}
		return response.response("updated", "users", HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUsers(@PathVariable("userId")Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usersViewService.deleteUsersView(loginUser, userId);
		return response.response("deleted", "user", HttpStatus.OK);
	}

}
