package com.example.demo;

import com.example.demo.services.CalculatorServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostfixCalculatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CalculatorServices calculatorServices;

	@Test
	public void testValidExpressionController() throws Exception {
		// Mock the CalculatorServices result for a valid expression
		when(calculatorServices.getResult(new ArrayList<>())).thenReturn(10.0);

		// Create an example valid expression as JSON
		String requestBody = "[{\"value\": 2.0, \"operator\": false}, " +
				"{\"value\": 3.0, \"operator\": false}, " +
				"{\"symbol\": \"+\", \"operator\": true}]";

		ResultActions resultActions = mockMvc.perform(post("/calculator")
				.contentType("application/json")
				.content(requestBody));

		resultActions.andExpect(status().isOk())
				.andExpect(content().string("10.0"));
	}

	@Test
	public void testInvalidExpressionController() throws Exception {
		// Mock the CalculatorServices result for an invalid expression
		when(calculatorServices.getResult(new ArrayList<>()))
				.thenThrow(new ArithmeticException("Division by zero is not allowed"));

		// Create an example invalid expression as JSON
		String requestBody = "[{\"value\": 2.0, \"operator\": false}, " +
				"{\"value\": 0.0, \"operator\": false}, " +
				"{\"symbol\": \"/\", \"operator\": true}]";

		ResultActions resultActions = mockMvc.perform(post("/calculator")
				.contentType("application/json")
				.content(requestBody));

		resultActions.andExpect(status().isInternalServerError());
	}
}
