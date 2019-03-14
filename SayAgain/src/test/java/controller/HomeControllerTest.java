package controller;



import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import spittr.web.HomeController;

public class HomeControllerTest {

	@Test
	public void test() throws Exception {
		 
		HomeController homeController = new HomeController();
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
	}

}
