package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

import spittr.pojo.Spitter;
import spittr.repository.SpitterRepository;
import spittr.web.SpitterController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
public class SpitterControllerTest {

	@Test
	public void test() throws Exception {
		
		
		SpitterController controller = new SpitterController();
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testProcessRegistration() throws Exception {
		
		/*
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		
		Spitter unsaved = new Spitter("root", "1234", "JB", "Jack", "123@qq.com");
		Spitter saved = new Spitter(110L, "root", "1234", "JB", "Jack", "123@qq.com");
		
		
		when(mockRepository.save(unsaved)).thenReturn(saved);
		
		SpitterController spitterController = new SpitterController(mockRepository);
		
		MockMvc build = MockMvcBuilders.standaloneSetup(spitterController).build();
		
		build.perform(post("/spitter/register")
				.param("username", "root")
				.param("password", "1234")
				.param("firstName", "JB")
				.param("lastName", "Jack")
				.param("email", "123@qq.com"))
		.andExpect(redirectedUrl("/spitter/root"));
		
		verify(mockRepository, atLeastOnce()).save(unsaved);*/
		
	}

}
