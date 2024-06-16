package com.VRJD.PetStore.Utilities;

// ENUM is Special Class in Java which has Collection of Constants or Methods
public enum APIResources {

	CreateUserAPI("/v2/user"), 
	GetUserAPI("/v2/user/{username}"),
	UpdateUserAPI("/v2/user/{username}"),
	DeleteUserAPI("/v2/user/{username}"),
	
	CreatePetAPI("/v2/pet"), 
	GetPetAPI("/v2/pet/{petId}"),
	UpdatePetAPI("/v2/pet/{petId}"),
	DeletePetAPI("/v2/pet/{petId}"),
	
	CreateStoreAPI("/v2/store/order"), 
	GetStoreAPI("/v2/store/{orderId}"),
	DeleteStoreAPI("/v2/store/{orderId}");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
