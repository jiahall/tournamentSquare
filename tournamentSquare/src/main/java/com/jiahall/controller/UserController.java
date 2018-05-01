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
import com.jiahall.model.User;
import com.jiahall.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	// works fart
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List getUsers() {

		List listOfUsers = userService.getAllUsers();
		return listOfUsers;
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUserById(@PathVariable("id") String id) {
		return userService.getUser(id);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addUser(@RequestBody User user) {

		String response = "error";
		try {
			userService.addUser(user);

			response = JsonReply.reply("entered?");

		} catch (DataIntegrityViolationException e) {
			JsonReply obj = new JsonReply("entered?", false, "Already there");
			Gson gson = new Gson();
			String json = gson.toJson(obj);
			response = json;
		}

		return response;

	}

	// works
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}

	// works
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}

	@RequestMapping(value = "/registerUser/{email}/{userName}/{passWord}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public String registerUser(@PathVariable("email") String email, @PathVariable("userName") String userName,
			@PathVariable("passWord") String passWord) {
		String response = "";
		int resNum = 4;
		if (email.length() >= 4 && userName.length() >= 4 && passWord.length() >= 4) {
			try {
				String pw_hash = BCrypt.hashpw(passWord, BCrypt.gensalt()); 
				resNum = userService.registerCheck(email, userName, pw_hash);

			} catch (DataIntegrityViolationException e) {

				resNum = 2;
			}

		} else {
			resNum = 3;
		}
		switch (resNum) {
		case 1:
			response = JsonReply.reply("register", true, "");
			break;
		case 2:
			response = JsonReply.reply("register", false, "You have already registered");
			break;
		case 3:
			response = JsonReply.reply("register", false, "Please use 4 characters or more");
			break;
		case 4:
			response = JsonReply.reply("register", false, "error");
			break;
		}
		return response;
	}
	
	@RequestMapping(value = "/getusername/{email}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String getusername(@PathVariable("email") String email) {

		String response = JsonReply.reply(userService.getusername(email));
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "/login/{email}/{passWord}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String login(@PathVariable("email") String email, @PathVariable("passWord") String passWord) {

		String response = "";
		int resNum = 4;
		if (email.length() >= 4 && passWord.length() >= 4) {
			
			try {
				resNum = userService.loginCheck(email, passWord);
			} catch (IndexOutOfBoundsException e) {
				resNum = 2;
				System.out.println("booty cheeks");
			}

		} else {
			resNum = 3;
		}
		switch (resNum) {
		case 1:
			response = JsonReply.reply("login", true, "");
			break;
		case 2:
			response = JsonReply.reply("login", false, "incorrect details");
			break;
		case 3:
			response = JsonReply.reply("login", false, "Please use 4 characters or more");
			break;
		case 4:
			response = JsonReply.reply("login", false, "error");
			break;
		}
		return response;

	}

}
