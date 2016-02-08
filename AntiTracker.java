package cis365;
import robocode.*;
import java.awt.Color;


/**
 * AntiTracker
 */
public class AntiTracker extends Robot
{

	int turnDirection = 1;	

	boolean fight;

	public void run() {
		
		while(true) {
			setBodyColor(Color.blue);				
			fight = false;
			turnRight(10 * turnDirection);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		turnDirection = e.getBearing() >= 0 ? 1:-1;		

		fire(3);

		if (e.getDistance() < 60) {
			fight = true;
			scan();
		} else if (e.getDistance() < 300){ 	
			fight = false;
			if (e.getVelocity() == 0 && e.getDistance() > 200) {
				fire(3);
				scan();
			}			
	
			runAway();
		} else {
			fight = false;
			scan();
		}
	}
	
	private void runAway() {
		setBodyColor(Color.pink);
		turnRight(70);
		ahead(600);
	}

	public void onHitByBullet(HitByBulletEvent e) {	
		//runAway();
	}
	
	public void onHitWall(HitWallEvent e) {	

		double heading = getHeading() % 90;
		
		if (heading == 0)
			heading = 90;		

		turnLeft(heading);
		ahead(150);
		turnLeft(90);
		ahead(380);
	}	
	
	public void onHitRobot(HitRobotEvent e) {
		//runAway();
		//turnDirection = e.getBearing() >= 0 ? 1:-1;
		
		//turnRight(e.getBearing());
		
		//fire(3);
	}
}
