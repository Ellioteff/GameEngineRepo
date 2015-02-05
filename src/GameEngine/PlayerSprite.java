package GameEngine;

public class PlayerSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	boolean rightDown = false;
	boolean leftDown = false;
	

	public PlayerSprite(int width, int height, int x, int y, String s) {
		super(width, height, x, y, Loader.loadImage(s));

	}

	@Override
	public void actOnCollision(DynamicSprite s) {
		if (s.isSolid())
			stepBackFromCollision();
	}

	public void jump() {
		if (onGround) {
			yVelocity = -4;
			onGround = false;
		}
	}

	private void walkRight(double delta) {
		if (onGround) {
			if (xVelocity < 2)
				xVelocity += 0.25*delta;
		} else {
			if (xVelocity < 2)
				xVelocity += 0.22*delta;
		}
	}

	private void walkLeft(double delta) {
		if (onGround) {
			if (xVelocity > -2)
				xVelocity -= 0.25*delta;
		} else {
			if (xVelocity > -2)
				xVelocity -= 0.22*delta;
		}
	}

	public void setRightDown() {
		rightDown = true;
	}

	public void setLeftDown() {
		leftDown = true;
	}

	public void setRightUp() {
		rightDown = false;
	}

	public void setLeftUp() {
		leftDown = false;
	}

	@Override
	public void move(double delta) {
		if (leftDown)
			walkLeft(delta);
		if (rightDown)
			walkRight(delta);
		super.move(delta);
		if (onGround)
			xVelocity = Physics.applyAirResistance(xVelocity, 20);
		else
			xVelocity = Physics.applyAirResistance(xVelocity, 2);

		if (!onGround)
			yVelocity = Physics.applyGravity(yVelocity);

	}

}
