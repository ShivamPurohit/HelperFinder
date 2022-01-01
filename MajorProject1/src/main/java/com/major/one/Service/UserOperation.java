package com.major.one.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.major.one.Repository.UserRepository;
import com.major.one.model.User;



import org.springframework.stereotype.Service;

@Service
public class UserOperation implements UserService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public User getValidUser(String email, String pass) {
		 for(User user:userrepo.findAll())
	        {
	      	  if(user.getEmail().equals(email))
	    			{
	    				if(user.getPass().equals(pass))
	    				{
	    					return user;
	    			
	    				}
	    				else
	    				{
	    					return null;
	    					
	    				}
	    				
	    			}
	      	  
	        }
			
	        System.out.println("Invaild User");
			
			return null;
	}

	
	
	@Override
	public User addUser(User user) {
		
		userrepo.save(user);
	    return user;
	}

	@Override
	public User fetchByUserEmailId(String email) {
		return userrepo.findByEmail(email);
	}

	@Override
	public List<User> getAllCustomer() {
		return userrepo.findAll();
	}

	@Override
	public boolean sendEmail(String res,String email) {
	
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("HelperFinder: Helper Details");
        msg.setText("From HelperFinder => Helper details : " + res);
        
        javaMailSender.send(msg);

        
       return true;
    }

}
