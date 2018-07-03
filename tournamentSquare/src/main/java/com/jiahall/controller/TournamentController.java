package com.jiahall.controller;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jiahall.jsonBuilder.JsonReply;
import com.jiahall.model.Tournament;
import com.jiahall.model.User;
import com.jiahall.service.TournamentService;

@RestController
public class TournamentController {

	@Autowired
	TournamentService tournamentService;

	// works fart
	@RequestMapping(value = "/getAllTournaments", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List getTournaments() {

		List listOfTournaments = tournamentService.getAllTournaments();
		return listOfTournaments;
	}

	//rename find
	@RequestMapping(value = "/getTournaments/{criteria}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List getTournaments(@PathVariable("criteria") String criteria) {

		List foundTournaments = tournamentService.getTournaments(criteria);
		return foundTournaments;
	}
		
		@RequestMapping(value = "/getHostedTournaments/{host}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
		public List getHostedTournaments(@PathVariable("host") String host) {

			List foundTournaments = tournamentService.getHostedTournaments(host);
			return foundTournaments;

	}
		
		@RequestMapping(value = "/addTournament", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
		public String addTournament(@RequestBody Tournament tournament) {
			String response = "";
			int resNum = 4;
			if (tournament.getName().length() >= 4 && tournament.getMaxEntrants() < 33
					&& tournament.getPostcode().length() >= 6 && tournament.getGame().length() >= 3
					&& tournament.getCurrEntrants() < tournament.getMaxEntrants()) {
				try {
				
					resNum = tournamentService.addTournament(tournament);

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
				response = JsonReply.reply("Create tournament", false, "That name is already taken");
				break;
			case 3:
				response = JsonReply.reply("register", false, "Please use 4 characters or more, 6 for postcode");
				break;
			case 4:
				response = JsonReply.reply("register", false, "error");
				break;
			}
			return response;
		}
		
			

	@RequestMapping(value = "/deleteTournament/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteTournament(@PathVariable("id") String id) {
		tournamentService.deleteTournament(id);
	}
	
	@RequestMapping(value = "/updateTournament", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateTournament(@RequestBody Tournament tournament) {
		String response = "";
		int resNum = 4;
		if (tournament.getName().length() >= 4 && tournament.getMaxEntrants() < 33 && tournament.getPostcode().length() >= 6 && tournament.getGame().length() >= 3) {
			try {
			
				resNum = tournamentService.updateTournament(tournament);

			} catch (StaleObjectStateException e) {

				resNum = 2;
			}

		} else {
			resNum = 3;
		}
		switch (resNum) {
		case 1:
			response = JsonReply.reply("tournamentupdate", true, "");
			break;
		case 2:
			response = JsonReply.reply("tournamentupdate", false, "It seems sombody joined while editing, please try again");
			break;
		case 3:
			response = JsonReply.reply("tournamentupdate", false, "either the name is too small or you have more people then the new max entrants");
			break;
		case 4:
			response = JsonReply.reply("register", false, "error");
			break;
		}
		return response;
	}
	
	
	@RequestMapping(value = "/jointournament/{username}/{tournament}/{screen}/{console}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String jointournament(@PathVariable("username") String username, @PathVariable("tournament") String tournament
			, @PathVariable("screen") int screen, @PathVariable("console") int console) {
		String response = "";
		int resNum = 4;
			try {
			
				resNum = tournamentService.jointournament(tournament);

			} catch (StaleObjectStateException e) {

				resNum = 2;
			}
			
			if(resNum==1) {
				
			}


		switch (resNum) {
		case 1:
			response = JsonReply.reply("tournamentupdate", true, "");
			break;
		case 2:
			response = JsonReply.reply("tournamentupdate", false, "Iy seems sombody joined while editing, please try again");
			break;
		case 3:
			response = JsonReply.reply("tournamentupdate", false, "oh it's full");
			break;
		case 4:
			response = JsonReply.reply("register", false, "error");
			break;
		}
		return response;
	}

	
		


	/*	
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

	@RequestMapping(value = "/registerUser/{email}/{userName}/{password}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String registerUser(@PathVariable("email") String email, @PathVariable("userName") String userName,
			@PathVariable("password") String password) {
		String response = "";
		int resNum = 4;
		if (email.length() >= 4 && userName.length() >= 4 && password.length() >= 4) {
			try {
				String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt()); 
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

	@RequestMapping(value = "/login/{email}/{password}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String login(@PathVariable("email") String email, @PathVariable("password") String password) {

		String response = "";
		int resNum = 4;
		if (email.length() >= 4 && password.length() >= 4) {
			
			try {
				resNum = userService.loginCheck(email, password);
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

	}*/
}

