package com.expertsvision.erp.core.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.expertsvision.erp.core.user.dto.UsersDTO;
import com.expertsvision.erp.core.user.dto.UsersViewFilter;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.UsersService;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
		
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private Response response;
	
	@Autowired
	private UsersService usersViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getUsersViewSubordinateList() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UsersDTO> usersViewList = usersViewService.getUsersViewSubordinateList(loginUser);
		return response.response(usersViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUsersView(@PathVariable("userId") Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UsersDTO usersView = usersViewService.getUsersView(loginUser, userId);
		return response.response(usersView, HttpStatus.OK);
	}
	
	@GetMapping("pageNo/{userId}")
	public ResponseEntity<Object> getUsersViewSinglePageNo(@PathVariable("userId") Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = usersViewService.getUserViewSinglePageNo(loginUser, userId);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<UsersDTO> singlePage = usersViewService.getUsersViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getUsersViewLastPage() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<UsersDTO> singlePage = usersViewService.getUsersViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getUsersViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<UsersDTO> multiplePages = usersViewService.getUsersViewMultiplePages(loginUser, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getUsersViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody UsersViewFilter usersViewFilter) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<UsersDTO> multiplePages = usersViewService.getUsersViewFilteredMultiplePages(loginUser, pageNo, usersViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addUser(@RequestBody UsersView usersView) {
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
			usersViewService.addUser(loginUser, usersView, cfupID);
		}  else {
			usersViewService.addUser(loginUser, usersView, null);
		}
		return response.response("added", "user", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateUser(@RequestBody UsersView usersView) {
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
		return response.response("updated", "user", HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId")Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usersViewService.deleteUsersView(loginUser, userId);
		return response.response("deleted", "user", HttpStatus.OK);
	}

}
