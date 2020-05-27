package com.evoluum.robotchallenge.domain;

import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_EAST;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_NORTH;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_SOUTH;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.BORDER_OUT_WEST;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.INVALID_COMMAND;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.MOVE_INVALID_COMMAND;
import static com.evoluum.robotchallenge.exceptions.MessageExceptionConstants.TURN_INVALID_COMMAND;
import com.evoluum.robotchallenge.exceptions.BorderOutException;
import com.evoluum.robotchallenge.exceptions.InvalidCommandException;

public class Robot {

	private final int MAX_WEST = 0;
	private final int MAX_EAST = 5;
	private final int MAX_NORTH = 5;
	private final int MAX_SOUTH = 0;
	private final int ONE_STEP = 1;
	
	private final char NORTH = 'N';
	private final char WEST = 'W';
	private final char EAST = 'E';
	private final char SOUTH = 'S';
	
	private final char RIGHT = 'R';
	private final char LEFT = 'L';
	private final char MOVE = 'M';
	
	private Position actualPosition;

	public Robot() {
		this.actualPosition = new Position(0, 0, Direction.NORTH);
	}

	public Position command(String command) {
		for (int i = 0; i < command.length(); i++) {
			switch (command.charAt(i)) {
			case LEFT:
				this.turnLeft(this.actualPosition.getDirection());
				break;
			case RIGHT:
				this.turnRight(this.actualPosition.getDirection());
				break;
			case MOVE:
				this.move(this.actualPosition.getDirection());
				break;
			default:
				throw new InvalidCommandException(INVALID_COMMAND);
			}
		}
		return actualPosition;
	}

	public void move(char direction) {
		switch (direction) {
		case NORTH:
			moveNorth();
			break;
		case SOUTH:
			moveSouth();
			break;
		case WEST:
			moveWest();
			break;
		case EAST:
			moveEast();
			break;
		default:
			throw new InvalidCommandException(MOVE_INVALID_COMMAND);
		}

	}

	private void moveNorth() {
		if (actualPosition.getY() + ONE_STEP > MAX_NORTH) {
			throw new BorderOutException(BORDER_OUT_NORTH);
		}

		actualPosition.setY(actualPosition.getY() + ONE_STEP);
	}

	private void moveSouth() {
		if (actualPosition.getY() - ONE_STEP < MAX_SOUTH) {
			throw new BorderOutException(BORDER_OUT_SOUTH);
		}

		actualPosition.setY(actualPosition.getY() - ONE_STEP);
	}

	private void moveWest() {
		if (actualPosition.getX() - ONE_STEP < MAX_WEST) {
			throw new BorderOutException(BORDER_OUT_WEST);
		}

		actualPosition.setX(actualPosition.getX() - ONE_STEP);
	}

	private void moveEast() {
		if (actualPosition.getX() + ONE_STEP > MAX_EAST) {
			throw new BorderOutException(BORDER_OUT_EAST);
		}

		actualPosition.setX(actualPosition.getX() + ONE_STEP);
	}

	public void turnRight(char direction) {
		switch (direction) {
		case NORTH:
			actualPosition.setDirection(Direction.EAST.getDirectionValue());
			break;
		case EAST:
			actualPosition.setDirection(Direction.SOUTH.getDirectionValue());
			break;
		case SOUTH:
			actualPosition.setDirection(Direction.WEST.getDirectionValue());
			break;
		case WEST:
			actualPosition.setDirection(Direction.NORTH.getDirectionValue());
			break;
		default:
			throw new InvalidCommandException(TURN_INVALID_COMMAND);
		}
	}

	public void turnLeft(char direction) {
		switch (direction) {
		case NORTH:
			actualPosition.setDirection(Direction.WEST.getDirectionValue());
			break;
		case WEST:
			actualPosition.setDirection(Direction.SOUTH.getDirectionValue());
			break;
		case SOUTH:
			actualPosition.setDirection(Direction.EAST.getDirectionValue());
			break;
		case EAST:
			actualPosition.setDirection(Direction.NORTH.getDirectionValue());
			break;
		default:
			throw new InvalidCommandException(TURN_INVALID_COMMAND);
		}
	}

	public Position getActualPosition() {
		return actualPosition;
	}

}
