package GameEngine;

import java.awt.Rectangle;

public class Physics {
	static final double TERMINAL_VELOCITY = 5;
	static final double GRAVITY = 0.015;
	//applies air resistance to the value sent in; the higher the force is the more resistance there will be, this can be used to
	//have different resistance on ground and in the air.
	public static double applyAirResistance(double xVelocity, int force) {
		if (xVelocity > 0.01)
			xVelocity = xVelocity * (0.999 - (((double) force) / 1000));
		else if (xVelocity < -0.01)
			xVelocity = xVelocity * (0.999 - (((double) force) / 1000));
		if (xVelocity < 0.01 && xVelocity > -0.01)
			xVelocity = 0;
		return xVelocity;
	}

	public static double applyAirResistance(double xVelocity) {
		return applyAirResistance(xVelocity, 1);
	}
	
	//applies gravity to the value sent to the method and makes sure that the velocity isn't higher than the terminal velocity.
	public static double applyGravity(double yVelocity) {
		yVelocity += GRAVITY;
		if (yVelocity > TERMINAL_VELOCITY)
			yVelocity = TERMINAL_VELOCITY;
		return yVelocity;
	}
	/* Collision detection that takes 2 dynamicsprites as parameters; first checks if the bounds of the spriteAreas collide and if they
	 * do the method checks if both, the first one or the second has a hitbox and if they do it compares the hitboxes and see if a collision
	 * has taken place or not.*/
	public static boolean checkCollision(DynamicSprite s1, DynamicSprite s2) {
		if (s1 == s2)
			return false;
		if (s1.getBounds().intersects(s2.getBounds())) {
			if (s1.hasHitbox() && s2.hasHitbox()) {
				for (Rectangle r1 : s1.getHitbox()) {
					for (Rectangle r2 : s2.getHitbox()) {
						return r1.intersects(r2);
							
					}
				}

			} else if (s1.hasHitbox()) {
				for (Rectangle r1 : s1.getHitbox()) {
					return r1.intersects(s2.getBounds());
				}

			} else if (s2.hasHitbox()) {
				for (Rectangle r1 : s2.getHitbox()) {
					return r1.intersects(s1.getBounds());
				}

			} else {
				return true;
			}

		}
		return false;
	}
}
