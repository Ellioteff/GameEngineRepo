package GameEngine;

public class PlayerSprite extends DynamicSprite {

	private static final long serialVersionUID = 1L;
	boolean rightDown = false;
	boolean leftDown = false;
	

	public PlayerSprite(int width, int height, int x, int y, String s) {
		super(width, height, x, y, Loader.loadImage(s));

	}

	@Override
	//if the sprite that the playersprite collided with is solid it will step back from collision.
	public void actOnCollision(DynamicSprite s) {
		if (s.isSolid())
			stepBackFromCollision();
	}
	//simple jump function that changes the velocity of the player and sets onground to false so that gravity is applied to the player.
	public void jump() {
		if (onGround) {
			yVelocity = -2;
			onGround = false;
		}
	}
	//walkleft and walkright methods which simply increases/decreases the xVelocity depending on if you're in the air or not.
	private void walkRight( ) {
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
	//set methods which allows for smooth movement by using booleans for keyreleased and keypressed.
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
	//move method that applies air resistance and gravity on the player on top of moving him.
	@Override
	public void move() {
		if (leftDown)
			walkLeft();
		if (rightDown)
			walkRight();
		super.move();
		if (onGround)
			xVelocity = Physics.applyAirResistance(xVelocity, 20);
		else
			xVelocity = Physics.applyAirResistance(xVelocity, 2);

		if (!onGround)
			yVelocity = Physics.applyGravity(yVelocity);

	}

}
