package com.example.tasks.rest.converter;

import com.example.tasks.persistence.model.User;
import com.example.tasks.rest.model.UserResponseModel;

public class UserConverter {

	public static UserResponseModel convertToResponseModel(User user) {
		return new UserResponseModel(user.getId(), user.getName(), user.getSurname());
	}
}
