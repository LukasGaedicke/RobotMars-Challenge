# RobotMars-Challenge
Mars Robot Code Challenge. In this challange, we have an Rest API, developed with **Java 8 + Spring**, in which we have only one **POST** endpoint.

## Context 
A team of robots must be deployed by NASA to explore a terrain on Mars. This terrain, which is rectangular **(5x5)**, needs to be navigated by robots in such a way that their
coupled cameras can get a complete view of the region, sending these images back to Earth.

To control the robot it is necessary to send a simple string that can contain the letters `L`,
`R` and `M`. The letters `L` and `R` cause the robot to rotate on its own axis 90 degrees to the left or right, respectively, without moving from its current position. The letter 'M'
makes the robot move forward one position.

In addition, the possibility of the robot on the ground is defined by a combination of the Cartesian coordinates (x, y) and one of the four orientations: 
**NORTH, SOUTH, EAST and WEST.**

For example, a valid position for a robot would be (0, 0, N), which means that the robot is positioned on the lower left corner of the land, facing North.

## How to run the Spring Boot applications

Go to root project folder and run: 
```
mvn clean package
```

This will create a `jar` file inside the target folder and can be executed with:
```
java -jar robot-challenge-0.0.1-SNAPSHOT.jar
```

And for execute execute a resquest **POST** in this url: http://localhost:8080/rest/mars/`${COMMAND}`

Where `${COMMAND}` is a string containing the **movements commands** to our robot. Are valid commands:

- M (Moves robot foward);
- R (Turns robot right); 
- L (Turns robot left).

## Command Examples 
### Example 01 
```
- Input: http://localhost:8080/rest/mars/MMMMMRMMMMM
- Output: (5,5,E).

Simulation: 

 _ _ _ _ _ _
|X X X X X > |
|X           |
|X           |
|X           |
|X           |
 _ _ _ _ _ _
 ```

### Example 02 
 ```
Input: http://localhost:8080/rest/mars/RMMLMML
Output: (2,2,W).

Simulation: 
 _ _ _ _ _ _
|           |
|           |
|           |
|  <        |
|X X        |
 _ _ _ _ _ _
```
