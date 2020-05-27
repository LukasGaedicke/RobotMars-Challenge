package com.evoluum.robotchallenge;

import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_NORTH;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.INVALID_COMMAND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.evoluum.robotchallenge.resource.RobotController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RobotController.class)
public class RobotControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected MockMvc mockMvc;

	private final String URL = "/rest/mars/";

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void moveTwoStepsFromNorth() throws Exception {
		String command = "MM";
		this.mockMvc.perform(post(URL + command)).andExpect(status().isOk()).andExpect(jsonPath("$.x").value(0))
				.andExpect(jsonPath("$.y").value(2)).andExpect(jsonPath("$.direction").value("N"));

	}

	@Test
	public void invalidCommand() throws Exception {
		String command = "AA";
		this.mockMvc.perform(post(URL + command)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(INVALID_COMMAND));
	}

	@Test
	public void walkingEightStepsFromNorth() throws Exception {
		String command = "MMMMMMMM";
		this.mockMvc.perform(post(URL + command)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(BORDER_OUT_NORTH));
	}
}
