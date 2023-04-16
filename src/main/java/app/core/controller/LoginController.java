package app.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.exeptions.CouponSystemExceptions;
import app.core.security.LoginManager;
import app.core.security.LoginRequest;
import app.core.security.LoginResponse;

@RestController
@RequestMapping("api/login/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class LoginController {
	@Autowired
	private LoginManager loginManager;

	@PostMapping
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		try {
			return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(),
					loginRequest.getClientType());
		} catch (CouponSystemExceptions e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}
	}
	
}
