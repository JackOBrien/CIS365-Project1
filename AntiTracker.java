package cis365;
import robocode.*;
import java.awt.Color;


/**
 * AntiTracker - Designed to battle sample.Tracker with
 * an optimized score and win rate.
 *
 * @author Jack O'Brien
 * @author Jake McKim
 */
public class AntiTracker extends Robot
{

	/** Modifier to decide the direction AntiTracker will
	 *  turn when searching for a target. */
	private int turnDirection = 1;	

	public void run() {
		setGunColor(Color.black);
		
		/* Main loop which will spin until interrupted. */
		while(true) {
			setBodyColor(Color.blue);	
			
			turnRight(10 * turnDirection);
		}
	}

	/** When a robot is scanned, we will fire at it, then
	 *  decide if we can fire again or if we need to run away. */
	public void onScannedRobot(ScannedRobotEvent e) {
		
		setBodyColor(Color.green);
		
		// Decides the direction that will be quickest to turn
		// in case the target is moving across our firld of vision.
		turnDirection = e.getBearing() >= 0 ? 1:-1;		

		// Fires with maximum power. Since Tracker drives towards us,
		// we are not likely to ever miss. 
		fire(3);

		/* If the target is too close to us, we will run away */
		if (e.getDistance() < 300){ 	
		
			/* If the target is close, but not too close, and
			 * sitting still, we will fire at them again! */
			if (e.getVelocity() == 0 && e.getDistance() > 200) {
				fire(3);
				scan();
			}			
	
			runAway();
		} 
		
		/* If the target is an acceptable distance away, we will
		 * scan again so that we continue to fire until the situation
		 * changes. */
		else {
			scan();
		}
	}
	
	/** Will turn and run a set distance. */
	private void runAway() {
		setBodyColor(Color.pink);
		turnRight(70);
		ahead(600);
	}
	
	/** If we hit a wall, we run along it for a bit and then 
	* turn perpendicular to the wall and move away a bit. */
	public void onHitWall(HitWallEvent e) {	
		setBodyColor(Color.gray);
	
		double heading = getHeading() % 90;
		
		if (heading == 0)
			heading = 90;		

		// Turns the shortest amount to be parallel to the wall.
		turnLeft(heading);
		
		// Moves along the wall a short distance
		ahead(150);
		
		// Turns to face away from the wall
		turnLeft(90);
		
		// Moves back into the map a ways
		ahead(300);
	}	
	
	
	// We ignore events where we get attacked and keep doing 
	// what we normally do!
	public void onHitRobot(HitRobotEvent e) {}
	public void onHitByBullet(HitByBulletEvent e) {}
}
