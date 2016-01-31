package cis365;
import robocode.*;
import java.awt.Color;


/**
 * AntiRamFire
 */
public class AntiRamFire extends Robot
{

	int turnDirection = 1;	

	public void run() {
		
		while(true) {
			turnRight(5 * turnDirection);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}

	public void onHitByBullet(HitByBulletEvent e) {

	}
	
	public void onHitWall(HitWallEvent e) {

	}	
}
