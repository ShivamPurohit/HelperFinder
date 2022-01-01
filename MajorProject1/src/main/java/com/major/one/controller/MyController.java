package com.major.one.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.major.one.ProfileHelper.ProfileUploadHelper;

import com.major.one.Service.HelperService;
import com.major.one.Service.UserService;

//import com.major.one.controller.BcryptPasswordncoder.BCryptPasswordEncoder;
import com.major.one.model.User;

import io.swagger.annotations.ApiOperation;

import com.major.one.model.Helper;

@Component
@RestController
public class MyController {

	@Autowired
	UserService userservice;

	@Autowired
	HelperService helperservice;

	// private Model model;

	@Autowired
	private ProfileUploadHelper fileUploadHelper;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@GetMapping("/index")
//	public ModelAndView indexPage() {
////		return "index.html";
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("index.html");
//		return modelAndView;
//	}

	@GetMapping("/index")
	public ModelAndView indexPage(HttpServletRequest request,@CookieValue(name="userCookieInfo",defaultValue="") String userCookieInfo) {
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println("Inside index method");
		
		System.out.println(userCookieInfo);
		Gson gson = new Gson();
		User user = gson.fromJson(userCookieInfo, User.class);
		
		if(user==null) {
			return new ModelAndView("redirect:/login");
		}
		
		System.out.println("USER INFO FROM COOKIE");
		System.out.println(user.getEmail());
		System.out.println(user.getUsername());
		System.out.println(user.getPass());
		System.out.println(user.getUniqueNO());
		
		
		modelAndView.addObject("username",user.getUsername());
		modelAndView.setViewName("index.html");
		return modelAndView;
	}

	
	@GetMapping("/admin")
	public ModelAndView admin() {
//		return "admin.html";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin.html");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
//			return "login.html";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.html");
		return modelAndView;
	}

	@ApiOperation(value = "User Login ", tags = "Login/Register")
	@PostMapping("/login-process")
	public ModelAndView getVaildUser(HttpServletResponse response,@RequestParam String email, @RequestParam String pass) {

		ModelAndView modelAndView = new ModelAndView();
		String x = "Invalid Username or Password";
		if (email.equals("admin@gmail.com") && pass.equals("admin")) {
	
			modelAndView.setViewName("admin.html");
			return modelAndView;
		}

		User userObj = userservice.getValidUser(email, pass);
		
		if (userObj != null) {
		if (userObj.getEmail().equals(email)) {
			if (userObj.getPass().equals(pass))
			{
				
//				modelAndView.setViewName("index.html");
//				return modelAndView;
				
				try {
					Gson gson = new Gson();
					
					Cookie userCookieInfo = new Cookie("userCookieInfo",
						URLEncoder.encode(gson.toJson(userObj),"UTF-8"));	
					
					userCookieInfo.setMaxAge(200);
					
					response.addCookie(userCookieInfo);
					return new ModelAndView("redirect:/index");

				}
				catch(Exception e) {
					System.out.println(e.getMessage());
						}
				
				}
			
			}
		}
		modelAndView.addObject("check",x);
		modelAndView.setViewName("login.html");
		return modelAndView;

	}

//	@ApiOperation(value = "User SignUp ", tags = "Login/Register")
//	@PostMapping("/signup-process")
//	public String addUser(@RequestParam String username, @RequestParam String email, @RequestParam String pass) {
//
//		if (email != null && !"".equals(email)) {
//			User userObj = userservice.fetchByUserEmailId(email);
//
//			if (userObj != null) {
//				return "User with " + email + " is already exist";
//			} else if (userObj == null) {
//
//				User user = new User(username, email, pass);
//		
//				userservice.addUser(user);
//			}
//
//		}
//
//		return "Welcome User!!!";
//	}

//	@ApiOperation(value = "User SignUp ", tags = "Login/Register")
//	@PostMapping("/signup-process")
//	public ModelAndView addUser(@RequestParam String username, @RequestParam String email, @RequestParam String pass) throws UnsupportedEncodingException {
//		ModelAndView modelAndView = new ModelAndView();
//		
//		if (email != null && !"".equals(email)) {
//			User userObj = userservice.fetchByUserEmailId(email);
//
//			if (userObj != null) {
////				return "User with " + email + " is already exist";
//				modelAndView.setViewName("error.html");
//				return modelAndView;
//			} else if (userObj == null) {
//			
//				
//				User user = new User(username, email, pass);
//		
//			
//				
//				userservice.addUser(user);
//			}
//
//		}
//
//		modelAndView.setViewName("index.html");
//		return modelAndView;
//	}

	@ApiOperation(value = "User SignUp ", tags = "Login/Register")
	@PostMapping("/signup-process")
	public ModelAndView addUser(HttpServletResponse response,@RequestParam String username, @RequestParam String email, @RequestParam String pass) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		
		if (email != null && !"".equals(email)) {
			User userObj = userservice.fetchByUserEmailId(email);

			if (userObj != null) {
//				return "User with " + email + " is already exist";
				model.setViewName("error.html");
				return model;
			} else if (userObj == null) {
				
				try {
					Gson gson = new Gson();
					
					User user = new User(username, email, pass);
					userservice.addUser(user);
					Cookie userCookieInfo = new Cookie("userCookieInfo",
						URLEncoder.encode(gson.toJson(user),"UTF-8"));	
					
					userCookieInfo.setMaxAge(200);
					
					response.addCookie(userCookieInfo);
					System.out.println(userCookieInfo);

				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}
		
		return new ModelAndView("redirect:/index");

	

//		modelAndView.setViewName("index.html");
//		return modelAndView;
	}

	
	@ApiOperation(value = "Helper Registration ", tags = "Admin")
	@GetMapping("/admin-register-form")
	public ModelAndView adminRegister() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminHelperRegister.html");
		return modelAndView;

	}

	@ApiOperation(value = "Helper Registration Submission", tags = "Admin")
	@PostMapping("/admin-register-form-submission")
	public String addHelperAdmin(@RequestParam String name, @RequestParam String age, @RequestParam String gender,
			@RequestParam String contact, @RequestParam String profession, @RequestParam String address,
			@RequestParam String location, @RequestParam int rating) {

		Helper helper = new Helper(name, age, gender, contact, profession, address, location, rating);
		helperservice.addHelper(helper);
		return "Helper Registered Successfully!!";
	}

	@ApiOperation(value = "View All Helpers ", tags = "Admin")
	@GetMapping("/helpers")
	public ModelAndView getAllHelper() throws Exception {

		List<Helper> l = new ArrayList<Helper>();
		l = this.helperservice.getAllHelper2();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("helper", l);
		if (l.size() == 0) {
			modelAndView.setViewName("nodata.html");
		} else
			modelAndView.setViewName("viewHelper.html");

		return modelAndView;
	}

	@ApiOperation(value = "Delete Helper by uniqueNo", tags = "Admin")
	@PostMapping("/deletehelper")
	public String getAllHelper(@RequestParam int uniqueHID) {
		Helper h = helperservice.findByuniqueHID(uniqueHID);
		helperservice.deleteById(h);
		return "Deleted";
	}

	@ApiOperation(value = "View All Customers ", tags = "Admin")
	@GetMapping("/customers")
	public ModelAndView getAllCustomers() {

		List<User> l = new ArrayList<User>();
		l = this.userservice.getAllCustomer();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("helper", l);
		if (l.size() == 0) {
			modelAndView.setViewName("nodata.html");
		} else
			modelAndView.setViewName("viewCustomer.html");

		return modelAndView;
	}

	@ApiOperation(value = "Filter Search ", tags = "Filters")
	@GetMapping("/filter-search")
	public ModelAndView getHelperFilter(HttpServletRequest request,@CookieValue(name="userCookieInfo",defaultValue=" ") String userCookieInfo,@RequestParam String location, @RequestParam String profession)throws Exception {
			
		Gson gson = new Gson();
		User user = gson.fromJson(userCookieInfo, User.class);	
		
		List<Helper> l = new ArrayList<Helper>();
		l = this.helperservice.findByLocationProfession(location, profession);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("username",user.getUsername());
		modelAndView.addObject("helper", l);
		if (l.size() == 0) {
			modelAndView.setViewName("nodata.html");
		} else
			modelAndView.setViewName("result.html");

		return modelAndView;
		
//		return new ModelAndView("redirect:/result");
	}
	
	
	@ApiOperation(value = "Filter Search ", tags = "Filters")
	@GetMapping("/confirm-Booking")
	public ModelAndView confirmBooking(HttpServletRequest request,@CookieValue(name="userCookieInfo",defaultValue=" ") String userCookieInfo,@RequestParam String name, @RequestParam String age, @RequestParam String gender,
			@RequestParam String contact, @RequestParam String profession, @RequestParam String address,
			@RequestParam String location, @RequestParam int rating)throws Exception {
			
		Gson gson = new Gson();
		User user = gson.fromJson(userCookieInfo, User.class);	
		
		String currentUser = user.getUsername();
		String currentUserEmail = user.getEmail();
		String helperName = name;
		String helperContact = contact;
		String helperLocation = location;
		String helperProfession = profession;
		int helperUniqueId = this.helperservice.findByNameContactLocationProfession(name,contact,location,profession);
		
		System.out.println(currentUser + helperName + helperContact + helperLocation + helperProfession + helperUniqueId + " ");
		
		String res = "\nHelper Name : " + helperName + "\nHelper contact : " + helperContact + "\nHelper Location : " + helperLocation + "\nHelper Profession : " + helperProfession + "\nHelper Unique Id : " + helperUniqueId; 
		boolean check = userservice.sendEmail(res, currentUserEmail);
		if(!check) {
			System.out.println("Message was not sent");
		}
		return new ModelAndView("redirect:/filter-search?location="+location+"&profession="+profession);
	}


	@ApiOperation(value = "Update profile picture Of Existing User", tags = "Profile-Photo")
	@PostMapping("/upload-file/{uniqueHID}")
	public ResponseEntity<String> uploadFile(@RequestParam("img") MultipartFile file,
			@PathVariable("uniqueHID") int uniqueHID) {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());

		Helper helperFetch = null;
		helperFetch = helperservice.findByuniqueHID(uniqueHID);
		if (helperFetch == null) {
			throw new NoSuchElementException("null");
		}

		try {
			// validations
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty");
			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only JPEG is allowed");
			}
			// file upload code

			boolean profile1 = fileUploadHelper.uploadFile(file);

			if (profile1) {
				helperFetch.setProfile(file.getOriginalFilename());

				try {
					helperFetch = helperservice.registerHelper(helperFetch);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return ResponseEntity.ok("Profile Picture Uploaded Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try again");
	}

	@ApiOperation(value = "Get profile picture of existing user", tags = "Profile-Photo")
	@GetMapping("/profile/{uniqueHID}")
	public ResponseEntity<Object> getProfile(@PathVariable("uniqueHID") int uniqueHID) {
		Helper helperFetch = null;

		try {
			helperFetch = helperservice.findByuniqueHID(uniqueHID);
		} catch (Exception e) {
			throw new NoSuchElementException("User Not registered");
		}

		return ResponseEntity.ok(helperFetch.getProfile());
	}

}
