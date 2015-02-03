package GameEngine;

import java.awt.Rectangle;

public class Physics {
	static final double TERMINAL_VELOCITY = 10;
	static final double GRAVITY = 0.01;

	public static double applyAirResistance(double xVelocity, int force) {
		if (xVelocity > 0.01)
			xVelocity = xVelocity * (0.999 - (((double) force) / 1000));
		else if (xVelocity < -0.01)
			xVelocity += xVelocity * (0.999 - (((double) force) / 1000));
		if (xVelocity < 0.1 && xVelocity > -0.1)
			xVelocity = 0;
		return xVelocity;
	}

	public static double applyAirResistance(double xVelocity) {
		return applyAirResistance(xVelocity, 1);
	}

	public static double applyGravity(double yVelocity) {
		yVelocity += GRAVITY;
		if (yVelocity > TERMINAL_VELOCITY)
			yVelocity = TERMINAL_VELOCITY;
		return yVelocity;
	}

	public static boolean checkCollision(Sprite s1, Sprite s2) {
		if (s1 == s2)
			return false;
		if (s1.getBounds().intersects(s2.getBounds())) {
			if (s1.hasHitbox() && s2.hasHitbox()) {
				for (Rectangle r1 : s1.getHitbox()) {
					for (Rectangle r2 : s2.getHitbox()) {
						if (r1.intersects(r2))
							return true;
					}
				}

			} else if (s1.hasHitbox()) {
				for (Rectangle r1 : s1.getHitbox()) {
					if (r1.intersects(s2.getBounds()))
						return true;
				}

			} else if (s2.hasHitbox()) {
				for (Rectangle r1 : s2.getHitbox()) {
					if (r1.intersects(s1.getBounds()))
						return true;
				}

			} else {
				return true;
			}

		}
		return false;
	}
}
