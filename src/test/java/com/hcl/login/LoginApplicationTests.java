package com.hcl.login;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

class LoginApplicationTests {

	private MockMvc mockmvc;
	@Autowired
	private WebApplicationContext context;
	ObjectMapper objectmapper = new ObjectMapper();

	@BeforeEach
	private void setUp() {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
		// System.out.println(mockmvc);
	}

	@Test
	void signUpTest() throws Exception {
		User user = new User();
		user.setDob("01/02/1990");
		user.setName("Ajeet yadav");
		user.setPassword("12345");
		user.setSex("M");
		user.setUserName("ajeetfdsfdsy@hcl.com");

		String jsonRequest = objectmapper.writeValueAsString(user);

		mockmvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.loginUrl").exists()).andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.loginUrl").value("http://shoppingcart.com/login")).andDo(print());

	}

	@Test
	void signUpWithMissingParameterTest() throws Exception {
		User user = new User();
		user.setDob("");
		user.setName("");
		user.setPassword("");
		user.setSex("");
		user.setUserName("");

		String jsonRequest = objectmapper.writeValueAsString(user);

		mockmvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.massage").exists())
				.andExpect(jsonPath("$.massage").value("Validation failed")).andDo(print());

	}

	@Test
	void existingUserSignUpTest() throws Exception {
		User user = new User();
		user.setDob("01/02/1990");
		user.setName("Ajeet yadav");
		user.setPassword("12345");
		user.setSex("M");
		user.setUserName("ajeety@hcl.com");

		String jsonRequest = objectmapper.writeValueAsString(user);

		mockmvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.status").value("failed")).andDo(print());

	}

	@Test
	void loginTest() throws Exception {
		mockmvc.perform(post("/login").param("username", "ajeety@hcl.com").param("password", "12345")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.isAuthenticated").exists())
				.andExpect(jsonPath("$.isAuthenticated").value("true")).andDo(print());
	}

	@Test
	void nonExistingEmailLoginTest() throws Exception {
		mockmvc.perform(post("/login").param("username", "ajeetyddfs@hcl.com").param("password", "12345")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.isAuthenticated").exists())
				.andExpect(jsonPath("$.isAuthenticated").value("false")).andDo(print());
	}

	@Test
	void nonExistingPasswordLoginTest() throws Exception {
		mockmvc.perform(post("/login").param("username", "ajeety@hcl.com").param("password", "123453432432432")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.isAuthenticated").exists())
				.andExpect(jsonPath("$.isAuthenticated").value("false")).andDo(print());
	}

	@Test
	void changePasswordTest() throws Exception {
		mockmvc.perform(post("/changePassword").param("oldPassword", "12345").param("newPassword", "67890")
				.param("userName", "ajeety@hcl.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isUpdated").exists()).andExpect(jsonPath("$.isUpdated").value("true"))
				.andDo(print());
	}

	@Test
	void changePasswordWithInvalidUserNameTest() throws Exception {
		mockmvc.perform(post("/changePassword").param("oldPassword", "12345").param("newPassword", "67890")
				.param("userName", "ajeehfjsdfhjsfj").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isUpdated").exists()).andExpect(jsonPath("$.isUpdated").value("false"))
				.andDo(print());
	}

	@Test
	void getAllUserTest() throws Exception {
		mockmvc.perform(get("/getAllUsers").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(5))).andDo(print());

	}

	@Test
	void getAllUserInvalidURLTest() throws Exception {
		mockmvc.perform(get("/getAllUsersdffsd").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andDo(print());

	}

}




//@Test
//void getAllUserTest() throws Exception {
//	mockmvc.perform(get("/getAllUsers").accept(MediaType.APPLICATION_JSON_VALUE))
//			.andExpect(jsonPath("$", hasSize(5))).andDo(print());
//
//}
//
