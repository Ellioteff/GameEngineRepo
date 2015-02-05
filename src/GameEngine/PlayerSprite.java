package GameEngine;

public class PlayerSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	boolean rightDown = false;
	boolean leftDown = false;
	

	public PlayerSprite(int x, int y, String s) {
		super(50, 50, x, y, Loader.loadImage(s));

	}

	@Override
	public void actOnCollision(DynamicSprite s) {
		if (s.isSolid())
			stepBackFromCollision();
	}

	public void jump() {
		if (onGround) {
			yVelocity = -2;
			onGround = false;
		}
	}

	private void walkRight() {
		if (onGround) {
			if (xVelocity < 2)
				xVelocity += 0.05;
		} else {
			if (xVelocity < 2)
				xVelocity += 0.02;
		}
	}

	private void walkLeft() {
		if (onGround) {
			if (xVelocity > -2)
				xVelocity -= 0.05;
		} else {
			if (xVelocity > -2)
				xVelocity -= 0.02;
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
	public void move() {
		if (leftDown)
			walkLeft();
		if (rightDown)
			walkRight();
		this.setPrevious();
		xPos += xVelocity;
		yPos += yVelocity;
		if (onGround)
			xVelocity = Physics.applyAirResistance(xVelocity, 20);
		else
			xVelocity = Physics.applyAirResistance(xVelocity, 2);

		if (!onGround)
			yVelocity = Physics.applyGravity(yVelocity);

	}

}
