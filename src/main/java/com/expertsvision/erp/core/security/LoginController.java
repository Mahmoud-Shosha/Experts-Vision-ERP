package com.expertsvision.erp.core.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.exception.InactiveUserException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.form.service.FormsService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersViewService;

@RestController
@RequestMapping(value = "/public/login")
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private InMemoryUsersViewService inMemoryUsersViewService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private FormsService formsViewService;

	
	@PostMapping("")
	public  ResponseEntity<Object>  login(@RequestBody LoginDTO loginDTO) {
		try {
			if (loginDTO.getUserId() == null || loginDTO.getPassword() == null) {
				throw new ValidationException("auth_failed");
			}
			
			UsersView usersView = inMemoryUsersViewService.getUsersView(loginDTO.getUserId());
			
			if (usersView.getInactive()) {
				throw new InactiveUserException();
			}
		
			if (!passwordEncoder.matches(loginDTO.getPassword(), usersView.getPassword())) {
				throw new ValidationException("auth_failed");
			}
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("user_id", usersView.getUserId());
			res.put("admin_user", usersView.getAdminUser());
			res.put("super_admin", usersView.getSuperAdmin());
			res.put("user_d_name", usersView.getUserDName());
			res.put("user_f_name", usersView.getUserFName());
			res.put("group_no", usersView.getGroupNo());
			res.put("group_no_d_name", usersView.getGroupNoDName());
			res.put("group_no_f_name", usersView.getGroupNoFName());
			res.put("token", jwtTokenUtil.generateAccessToken(usersView));
			res.put("main_tree", formsViewService.getFormsViewMainTree(usersView));
			return ResponseEntity.ok().body(res);
			
		} catch (InactiveUserException e) {
			throw new InactiveUserException();
		} catch (Exception e) {
			throw new ValidationException("auth_failed");
		}

	}

}
