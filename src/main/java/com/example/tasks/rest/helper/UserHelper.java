package com.example.tasks.rest.helper;

import com.example.tasks.persistence.model.User;
import com.example.tasks.rest.model.UserRequestModel;

public class UserHelper {
	public static void updateUser(UserRequestModel updateUser, User user) {
		if (updateUser.getName() == null) {user.setName(user.getName());} else {user.setName(updateUser.getName());}
		if (updateUser.getSurname() == null) {user.setSurname(user.getSurname());} else {user.setSurname(updateUser.getSurname());}
	}
}