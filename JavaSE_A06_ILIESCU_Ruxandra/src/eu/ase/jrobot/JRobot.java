package eu.ase.jrobot;

import robocode.*;
//import java.awt.Color;

public class JRobot extends Robot {

    // Number of squares traveled
    int squaresTraveled = 0;
    // Direction of movement
    int direction = 0;
    // Boolean variables
    boolean clockwise = false;
    boolean aggressive = true;
    boolean scanForRobots = true;

    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        // setColors(Color.red,Color.blue,Color.green); // body,gun,radar

        // Robot main loop
        while(true) {
            // Robot moves in square of side length 150
            moveInSquare(150);
            squaresTraveled++;
            // Print number of squares traveled
            System.out.println("Squares traveled: " + squaresTraveled);
            // Alternate movement direction
            direction++;
        }
    }

    /**
     * moveInSquare: Move the robot in a square
     */
    public void moveInSquare(int lengthOfSide) {
        for(int i = 0; i < 4; i++) {
            ahead(lengthOfSide);
            if(direction % 2 == 0) {
                // Turn right
                System.out.println("Moving in clockwise direction");
                clockwise = true;
                turnRight(90);
            } else {
                // Turn left
                System.out.println("Moving in anticlockwise direction");
                clockwise = false;
                turnLeft(90);
            }
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        if(scanForRobots) {
            System.out.println("Scanned robot: " + e.getName() + ", Energy: " + e.getEnergy()
                    + ", Distance: " + e.getDistance());
        }
       if(aggressive) {
           fire(1);
       }
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        back(10);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        back(20);
    }

    /**
     * onRoundEnded: What to do at the end of each round
     */
    public void onRoundEnded(RoundEndedEvent e) {
        // Print the number of squares traveled during the round
        System.out.println("Round finished! Total squares traveled: " + squaresTraveled);
    }
}
