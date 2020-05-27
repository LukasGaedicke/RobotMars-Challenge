package com.evoluum.robotchallenge.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.evoluum.robotchallenge.domain.Position;
import com.evoluum.robotchallenge.domain.Robot;

@RestController
@RequestMapping("rest/mars")
public class RobotController {

	private Robot robot;

	@PostMapping("/{command}")
	public ResponseEntity<Position> controlRobot(@PathVariable String command) {
		robot = new Robot();
		return ResponseEntity.status(HttpStatus.OK).body(robot.command(command));
	}
}
