package com.learn.services;

import com.learn.models.User;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	List<User> list = new ArrayList<>();

	public UserService() {
		User u1 = new User("abc", "abc", "ABC@GMAIL.com");
		
		list.add(u1);
		list.add(new User("xyz","xyz","XYZ@GMAIL.com"));
		System.out.println("List in User Service" + this.list);
	}
		//get all users
		public List<User> getAllUsers(){
			System.out.println("LIST of all user"+ this.list);
			return this.list;
		}
			// get single user
			public User getUser(String username)
			{
				return this.list.stream().filter((user)->user.getUsername().equals(username)).findAny().orElse(null);
			}
			
			// add new user
			public User addUser(User user)
			{
				this.list.add(user);
				return user;
			}
		}



	
	

