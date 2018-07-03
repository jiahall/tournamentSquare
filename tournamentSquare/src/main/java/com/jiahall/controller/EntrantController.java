package com.jiahall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jiahall.jsonBuilder.JsonReply;
import com.jiahall.model.Entrant;
import com.jiahall.model.User;
import com.jiahall.service.EntrantService;
import com.jiahall.service.UserService;

@RestController
public class EntrantController {

@Autowired
	EntrantService entrantService;

	// works fart
	@RequestMapping(value = "/getEntrants/{tournament}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List getEntrants(@PathVariable("tournament") String tournament) {

		List listOfEntrants = entrantService.getEntrants(tournament);
		return listOfEntrants;
	}
	
	
	@RequestMapping(value = "/addEntrant", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addEntrant(@RequestBody Entrant entrant) {

		String response = "";
		int resNum = 4;
			try {
				resNum = entrantService.registerEntrant(entrant);

			} catch (DataIntegrityViolationException e) {

				resNum = 2;
			}


		switch (resNum) {
		case 1:
			response = JsonReply.reply("register", true, "");
			break;
		case 2:
			response = JsonReply.reply("register", false, "You have already registered");
			break;

		case 4:
			response = JsonReply.reply("register", false, "error");
			break;
		}
		return response;

	}
	}
	
//	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
//	public User getUserById(@PathVariable("id") String id) {
//		return userService.getUser(id);
//	}
//

//
//	}
//
//	// works
//	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, headers = "Accept=application/json")
//	public void updateUser(@RequestBody User user) {
//		userService.updateUser(user);
//	}
//
//	// works
//	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
//	public void deleteUser(@PathVariable("id") String id) {
//		userService.deleteUser(id);
//	}
//
//	@RequestMapping(value = "/registerUser/{email}/{userName}/{password}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
//	public String registerUser(@PathVariable("email") String email, @PathVariable("userName") String userName,
//			@PathVariable("password") String password) {
//		String response = "";
//		int resNum = 4;
//		if (email.length() >= 4 && userName.length() >= 4 && password.length() >= 4) {
//			try {
//				String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt()); 
//				resNum = userService.registerCheck(email, userName, pw_hash);
//
//			} catch (DataIntegrityViolationException e) {
//
//				resNum = 2;
//			}
//
//		} else {
//			resNum = 3;
//		}
//		switch (resNum) {
//		case 1:
//			response = JsonReply.reply("register", true, "");
//			break;
//		case 2:
//			response = JsonReply.reply("register", false, "You have already registered");
//			break;
//		case 3:
//			response = JsonReply.reply("register", false, "Please use 4 characters or more");
//			break;
//		case 4:
//			response = JsonReply.reply("register", false, "error");
//			break;
//		}
//		return response;
//	}
//	
//	@RequestMapping(value = "/getusername/{email}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
//	public String getusername(@PathVariable("email") String email) {
//
//		String response = JsonReply.reply(userService.getusername(email));
//		System.out.println(response);
//		return response;
//
//	}
//
//	@RequestMapping(value = "/login/{email}/{password}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
//	public String login(@PathVariable("email") String email, @PathVariable("password") String password) {
//
//		String response = "";
//		int resNum = 4;
//		if (email.length() >= 4 && password.length() >= 4) {
//			
//			try {
//				resNum = userService.loginCheck(email, password);
//			} catch (IndexOutOfBoundsException e) {
//				resNum = 2;
//				System.out.println("booty cheeks");
//			}
//
//		} else {
//			resNum = 3;
//		}
//		switch (resNum) {
//		case 1:
//			response = JsonReply.reply("login", true, "");
//			break;
//		case 2:
//			response = JsonReply.reply("login", false, "incorrect details");
//			break;
//		case 3:
//			response = JsonReply.reply("login", false, "Please use 4 characters or more");
//			break;
//		case 4:
//			response = JsonReply.reply("login", false, "error");
//			break;
//		}
//		return response;
//
//	}

