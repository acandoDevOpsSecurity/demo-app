package de.secdevops;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import de.secdevops.user.AppUserPrincipal;
import de.secdevops.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DemoApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RemoteCodeExecutionAttackTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	MockMultipartFile maliciousFile = new MockMultipartFile("file", "myscript.txt", "text/plain", "attack code goes here....".getBytes());
	AppUserPrincipal userDetails;
	
	@Before
    public void setup() {
		userDetails = new AppUserPrincipal(userRepository.findByName("Michael"));
    }
	
	@Test
	public void testFileUpload() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(maliciousFile)
				.param("filename", "attack.bat")
				.with(user(userDetails)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testMaliciousFileUpload() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(maliciousFile)
				.param("filename", "../attack.bat")
				.with(user(userDetails)))
		.andExpect(status().is(403));
		
		//due to the event configuration, appsensor should have disabled the user
		MvcResult result = mockMvc.perform(post("/login").param("username", "Michael").param("password", "password")).andDo(print()).andReturn();
		String redirectUrl = result.getResponse().getRedirectedUrl();
		assertTrue("Appsensor should have disabled the user. The login attempt should not be successful", redirectUrl.contains("/login?error"));
	}
}
