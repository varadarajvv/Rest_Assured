package com.VRJD.PetStore.Utilities;

import com.VRJD.PetStore.POJO.UserPayload;
import com.github.javafaker.Faker;

public class TestDataBuild {

	Faker faker = new Faker();

	public UserPayload createUserPayload(String name) {
		UserPayload userPayload = new UserPayload();
		userPayload.setId(faker.idNumber().hashCode());
//		userPayload.setUsername(faker.name().username());
		userPayload.setUsername(name);
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		return userPayload;
	}
	
	public UserPayload updateUserPayload(String name) {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername(name);
		return userPayload;
	}

}
