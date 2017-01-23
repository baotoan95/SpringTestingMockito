package com.btit95.sample.controllers;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
@WebAppConfiguration
@ContextConfiguration({"classpath:dispatcher-servlet.xml"})
public class ToDoControllerTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAddToDo() throws Exception {
		
		mockMvc.perform(get("/todo"))
		.andExpect(status().isOk())
		.andExpect(view().name("todo_list"))
		.andExpect(model().attribute("todoList", hasSize(5)));
		
		mockMvc.perform(get("/todo/add"))
		.andExpect(model().attributeExists("todo"))
		.andExpect(view().name("edit_todo"));
		
		mockMvc.perform(post("/todo/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "Bao Toan")
				.param("description", "abc")
				.param("version", "0"))
		.andExpect(status().isMovedTemporarily())
		.andExpect(redirectedUrl("/todo"));
		
	}
}
