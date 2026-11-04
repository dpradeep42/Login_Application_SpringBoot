package com.pradeep;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.pradeep.model.User;
import com.pradeep.service.UserService;
import com.pradeep.web.dto.UserRegistrationDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class LoginApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void loginPageIsServed() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("User Login Page")));

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:" + port + "/login")).GET()
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		assertThat(response.statusCode()).isEqualTo(200);
		assertThat(response.body()).contains("User Login Page");
	}

	@Test
	void registrationStoresBcryptHashAndLoginUsesIt() throws Exception {
		String rawPassword = "plain-secret";
		User saved = userService.save(new UserRegistrationDto("Ada", "Lovelace", "ada@example.com", rawPassword));

		assertThat(saved.getPassword()).isNotEqualTo(rawPassword);
		assertThat(saved.getPassword()).doesNotContain(rawPassword);
		assertThat(saved.getPassword()).startsWith("$2");
		assertThat(passwordEncoder.matches(rawPassword, saved.getPassword())).isTrue();

		mockMvc.perform(formLogin("/login").user("ada@example.com").password(rawPassword)).andExpect(status().isFound())
				.andExpect(redirectedUrl("/")).andExpect(authenticated().withUsername("ada@example.com"));

		mockMvc.perform(formLogin("/login").user("ada@example.com").password("wrong-secret"))
				.andExpect(status().isFound()).andExpect(unauthenticated());
	}

}
