package com.evoluum.robotchallenge.domain;

public enum Direction {
	NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

	public char directionValue;

	Direction(char value) {
		this.directionValue = value;
	}

	public char getDirectionValue() {
		return directionValue;
	}

}
