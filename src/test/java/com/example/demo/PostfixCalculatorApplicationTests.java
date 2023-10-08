package com.example.demo;


import com.example.demo.model.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class YourControllerClassTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void evaluateExpression() throws Exception {
		List<TokenDTO> tokenList = new ArrayList<>();
		tokenList.add(new TokenDTO(1,' ', false));
		tokenList.add(new TokenDTO(1, ' ',false));
		tokenList.add(new TokenDTO(0,'+', true));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(tokenList)))
				.andExpect(status().isOk())
				.andReturn();
		double response = Double.parseDouble(result.getResponse().getContentAsString());
		assertEquals(2, response);
	}
	@Test
	void evaluateExpressionWithEmptyStack() throws Exception {
		List<TokenDTO> tokenList = new ArrayList<>();
		mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(tokenList)))
				.andExpect(result -> {
					Throwable error = result.getResolvedException();
					assertTrue(error instanceof RuntimeException);
					assertEquals("Stack is empty", error.getMessage());
				});
	}
}
