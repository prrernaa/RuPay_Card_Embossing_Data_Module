package com.card.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.card.entity.InactiveUser;
import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;
import com.card.exception.UsernameAlreadyExistsException;
import com.card.projection.SignupProjection;
import com.card.repo.InactiveUserRepository;
import com.card.repo.SignUpRepository;
import com.card.repo.UserInfoRepository;
import com.card.service.AuthService;
import com.card.service.SignupService;
import com.card.service.UserService;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private SignupService signupService;

	@Autowired
	private UserService userService;

	@Autowired
	private SignUpRepository signupRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private InactiveUserRepository inactiveUserRepository;

	@GetMapping("/operation1")
	public String operation1() {
		return "Operation1"; // Returns Operation1.html
	}

	@GetMapping("/operation2")
	public String operation2() {
		return "Operation2"; // Returns Operation2.html
	}

	@GetMapping("/operation2/form")
	public String operation2Form() {
		return "Operation2_form"; // Returns Operation2.html
	}

	@GetMapping("/operation3")
	public String operation3() {
		return "Operation3"; // Returns Operation3.html
	}

	@GetMapping("/operation3/upload")
	public String operation3Upload() {
		return "Operation3_upload"; // Returns Operation2.html
	}

	@GetMapping("/operation3/form")
	public String operation3Form() {
		return "Operation3_form"; // Returns Operation2.html
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "AdminPage"; // Returns AdminPage.html
	}

	@GetMapping("/admin/upload")
	public String adminUpload() {
		return "adminupload"; // Returns AdminPage.html
	}

	@GetMapping("/admin/form")
	public String adminForm() {
		return "adminform"; // Returns AdminPage.html
	}

	@GetMapping("/login")
	public String showCardForm(Model model) {
		return "Login";
	}

	@GetMapping("/Signup")
	public String Signup() {
		return "Signup";
	}

	@GetMapping("/usermanagement")
	public String usermanagement() {
		return "UserMgmt"; // Returns UserMgmt.html
	}

	@GetMapping(value = "/pendingSignups", produces = "application/json")
	public ResponseEntity<List<SignupProjection>> getPendingSignups() {
		List<SignupProjection> pendingSignups = signupService.getPendingSignups();
		return ResponseEntity.status(HttpStatus.OK).body(pendingSignups); // Return the name of your HTML page
	}

	@PostMapping("/Signup")
	public ResponseEntity<?> Signup(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("userRole") String userRole, @RequestParam("mobileNum") String mobileNum) {
		SignupInfo signupInfo = new SignupInfo();
		signupInfo.setUsername(username);
		signupInfo.setPassword(password);
		signupInfo.setEmail(email);
		signupInfo.setUserRole(userRole);
		signupInfo.setMobileNum(mobileNum);

		try {

			boolean usernameExistsInUserInfo = userService.isUsernameExistsInUserInfo(signupInfo.getUsername());
			boolean usernameExistsInSignupInfo = signupService.isUsernameExistsInSignupInfo(signupInfo.getUsername());

			if (usernameExistsInUserInfo || usernameExistsInSignupInfo) {
				if (usernameExistsInUserInfo) {
					throw new UsernameAlreadyExistsException("Username already exists");
				} else {
					throw new UsernameAlreadyExistsException("Username already requested Signup");
				}
			}

			SignupInfo newSignup = authService.registerUser(signupInfo);
			return ResponseEntity.status(HttpStatus.CREATED).body(newSignup);
		} catch (UsernameAlreadyExistsException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}

	@PostMapping("/approve")
	public ResponseEntity<String> approveSignup(@RequestParam String username, @RequestParam String remarks) {
		SignupInfo signup = signupRepository.findByUsername(username);
		if (signup != null) {
			signup.setApprovalStatus(1); // Assuming 1 represents approved status
			signupRepository.save(signup);
			String editorname = getCurrentUsername();
			UserInfo userInfo = new UserInfo();

			userInfo.setUsername(signup.getUsername()); // Assuming similar field names
			userInfo.setEmail(signup.getEmail());
			userInfo.setPassword(signup.getPassword());
			userInfo.setUserRole(signup.getUserRole());
			userInfo.setActive(signup.getApprovalStatus());
			userInfo.setMobileNum(signup.getMobileNum());
			userInfo.setRemark("Approved by " + editorname + ". " + remarks);
			userInfoRepository.save(userInfo);

			// Delete the content from SignupInfo table
			signupRepository.delete(signup);

			return ResponseEntity.ok("Signup approved successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/reject")
	public ResponseEntity<String> rejectSignup(@RequestParam String username, @RequestParam String remarks) {
		SignupInfo signup = signupRepository.findByUsername(username);
		if (signup != null) {
			// Delete the content from SignupInfo table
			String editorname = getCurrentUsername();
			InactiveUser inactiveUser = new InactiveUser();
			inactiveUser.setUsername(signup.getUsername()); // Assuming similar field names
			inactiveUser.setEmail(signup.getEmail());
			inactiveUser.setUserrole(signup.getUserRole());
			inactiveUser.setMobileNum(signup.getMobileNum());
			inactiveUser.setRemark("Signup  Rejected by " + editorname + ". " + remarks);
			inactiveUserRepository.save(inactiveUser);
			signupRepository.delete(signup);

			return ResponseEntity.ok("Signup rejected successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
