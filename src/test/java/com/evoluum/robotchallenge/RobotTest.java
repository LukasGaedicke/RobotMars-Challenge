package com.evoluum.robotchallenge;

import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_EAST;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_NORTH;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_SOUTH;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_WEST;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.INVALID_COMMAND;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.MOVE_INVALID_COMMAND;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.TURN_INVALID_COMMAND;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.evoluum.robotchallenge.domain.Direction;
import com.evoluum.robotchallenge.domain.Robot;
import com.evoluum.robotchallenge.exceptions.BorderOutException;
import com.evoluum.robotchallenge.exceptions.InvalidCommandException;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

	private Robot robot;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		robot = new Robot();
	}

	@Test
	public void moveOneStepFromNorth() {
		robot.command("M");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(1, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void moveFiveStepsFromNorth() {
		robot.command("MMMMM");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(5, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void moveToLimitNorthException() {
		exception.expectMessage(BORDER_OUT_NORTH);
		exception.expect(BorderOutException.class);

		robot.command("MMMMMM");
	}

	@Test
	public void moveToLimitSouthException() {
		exception.expectMessage(BORDER_OUT_SOUTH);
		exception.expect(BorderOutException.class);

		robot.command("RRM");

	}

	@Test
	public void moveToLimitWestException() {
		exception.expectMessage(BORDER_OUT_WEST);
		exception.expect(BorderOutException.class);

		robot.command("LM");

	}

	@Test
	public void moveToLimitEastException() {
		exception.expectMessage(BORDER_OUT_EAST);
		exception.expect(BorderOutException.class);

		robot.command("RMMMMMMM");

	}

	@Test
	public void rotageOneTimeToRight() {

		robot.command("R");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageTwoTimesToRight() {

		robot.command("RR");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.SOUTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageThreeTimesToRight() {

		robot.command("RRR");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.WEST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageFourTimesToRight() {

		robot.command("RRRR");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageNineTimesToRight() {

		robot.command("RRRRRRRRR");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageOneTimeToLeft() {

		robot.command("L");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.WEST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageTwoTimesToLeft() {

		robot.command("LL");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.SOUTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageThreeTimesToLeft() {

		robot.command("LLL");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void rotageFourTimesToLeft() {

		robot.command("LLLL");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreBorderAreaLeftMidlle() {

		robot.command("MMM");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(3, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreMaxNorth() {

		robot.command("MMMMM");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(5, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreBorderAreaRightMidlleBot() {

		robot.command("RMMM");

		assertEquals(3, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreMaxRightBot() {

		robot.command("RMMMMM");

		assertEquals(5, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreBorderAreaRightMidlleTop() {

		robot.command("RMMMMMLMMM");

		assertEquals(5, robot.getActualPosition().getX());
		assertEquals(3, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreBorderAreaRightMidlleTopAndMoveRight() {
		exception.expectMessage(BORDER_OUT_EAST);
		exception.expect(BorderOutException.class);

		robot.command("RMMMMMLMMMRM");

	}

	@Test
	public void exploreMaxRightTop() {

		robot.command("RMMMMMLMMMMM");

		assertEquals(5, robot.getActualPosition().getX());
		assertEquals(5, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreMiddleTop() {

		robot.command("MMMRMMMLMM");

		assertEquals(3, robot.getActualPosition().getX());
		assertEquals(5, robot.getActualPosition().getY());
		assertEquals(Direction.NORTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void exploreToCenter() {

		robot.command("MMMRMM");

		assertEquals(2, robot.getActualPosition().getX());
		assertEquals(3, robot.getActualPosition().getY());
		assertEquals(Direction.EAST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void invalidCommand_02() {
		exception.expectMessage(INVALID_COMMAND);
		exception.expect(InvalidCommandException.class);
		robot.command("@#DSA");

	}

	@Test
	public void cenario_01() {

		robot.command("MMRMMRMM");

		assertEquals(2, robot.getActualPosition().getX());
		assertEquals(0, robot.getActualPosition().getY());
		assertEquals(Direction.SOUTH.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void cenario_02() {
		robot.command("MML");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(2, robot.getActualPosition().getY());
		assertEquals(Direction.WEST.getDirectionValue(), robot.getActualPosition().getDirection());

	}

	@Test
	public void cenario_03() {
		exception.expectMessage(INVALID_COMMAND);
		exception.expect(InvalidCommandException.class);
		robot.command("AAA");

	}

	@Test
	public void cenario_04() {
		exception.expectMessage(BORDER_OUT_NORTH);
		exception.expect(BorderOutException.class);
		robot.command("MMMMMMMMMMMMMMMMMMMMMMMM");
	}

	@Test
	public void moveAnotherWayMiddleLeft() {
		robot.command("RMLMLM");

		assertEquals(0, robot.getActualPosition().getX());
		assertEquals(1, robot.getActualPosition().getY());
		assertEquals(Direction.WEST.getDirectionValue(), robot.getActualPosition().getDirection());
	}

	@Test
	public void invalidTurnLeftCommand() {
		exception.expectMessage(TURN_INVALID_COMMAND);
		exception.expect(InvalidCommandException.class);
		robot.turnLeft('X');
	}

	@Test
	public void invalidTurnRightCommand() {
		exception.expectMessage(TURN_INVALID_COMMAND);
		exception.expect(InvalidCommandException.class);
		robot.turnRight('X');
	}

	@Test
	public void invalidMoveCommand() {
		exception.expectMessage(MOVE_INVALID_COMMAND);
		exception.expect(InvalidCommandException.class);

		robot.move('X');
	}
}
