package se.olofkarlsson.java.platformer.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Unit extends Image {

    public Unit() {
    }

    public enum State {
        IDLE, WALKING, RUNNING, JUMPING, DYING
    }

    private static final float MAX_X_SPEED = 8f;

    private static final float JUMP_VELOCITY = 4f;

    private static final float MAX_Y_SPEED = 16f;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private boolean onGround;

    private Animation idleAnimation;
    private Texture idleSheet;
    private TextureRegion[] idleFrames;
    /**
    private Animation walkingAnimation;
    private Texture walkingSheet;
    private TextureRegion[] walkingFrames;
     **/
    private Animation runningLeftAnimation;
    private Texture runningLeftSheet;
    private TextureRegion[] runningLeftFrames;

    private Animation runningRightAnimation;
    private Texture runningRightSheet;
    private TextureRegion[] runningRightFrames;

    private Animation jumpingUpAnimation;
    private Texture jumpingUpSheet;
    private TextureRegion[] jumpingUpFrames;

    private Animation jumpingLeftAnimation;
    private Texture jumpingLeftSheet;
    private TextureRegion[] jumpingLeftFrames;

    private Animation jumpingRightAnimation;
    private Texture jumpingRightSheet;
    private TextureRegion[] jumpingRightFrames;

    private Animation fallingAnimation;
    private Texture fallingSheet;
    private TextureRegion[] fallingFrames;
    /**
    private Animation dyingAnimation;
    private Texture dyingSheet;
    private TextureRegion[] dyingFrames;
     **/
    private Animation currentAnimation;
    private SpriteBatch spriteBatch;
    private TextureRegion currentFrame;

    public Unit(String spriteSheet, float posX, float posY) {

    }

    /*

	public player(String sprite, float posX, float posY) throws SlickException {
		this.sprite = new Image(sprite);
		standingSS = new SpriteSheet("res/player/player-base-standing.png", 32,
				32);
		runningLeftSS = new SpriteSheet(
				"res/player/player-base-running-left.png", 32, 32);
		runningRightSS = new SpriteSheet(
				"res/player/player-base-running-right.png", 32, 32);
		fallingSS = new SpriteSheet("res/player/player-base-falling.png", 32,
				32);
		jumpingLeftSS = new SpriteSheet(
				"res/player/player-base-jumping-left.png", 32, 32);
		jumpingRightSS = new SpriteSheet(
				"res/player/player-base-jumping-right.png", 32, 32);
		jumpingUpSS = new SpriteSheet("res/player/player-base-jumping-up.png",
				32, 32);

		standing = new Animation(standingSS, 500);
		standing.setAutoUpdate(true);
		runningLeft = new Animation(runningLeftSS, 100);
		runningLeft.setAutoUpdate(true);
		runningRight = new Animation(runningRightSS, 100);
		runningRight.setAutoUpdate(true);
		falling = new Animation(fallingSS, 100);
		falling.setAutoUpdate(true);
		jumpingLeft = new Animation(jumpingLeftSS, 200);
		jumpingLeft.setAutoUpdate(true);
		jumpingRight = new Animation(jumpingRightSS, 200);
		jumpingRight.setAutoUpdate(true);
		jumpingUp = new Animation(jumpingUpSS, 200);
		jumpingUp.setAutoUpdate(true);

		currentAnimation = falling;

		debugGraphics = new Graphics();

		inAir = true;

		setX(posX);
		setY(posY);

		velocityX = 0f;
		velocityY = 0f;

		maxSpeedX = 4f;
		maxSpeedY = 12f;

		acceleration = 0.5f;
		deceleration = 2f;
		setupCollisionShape(getX(), getY(), 32, 32);
		GameworldEntities.entitiesMovable.add(this);
	}
	
	public void draw() {
		currentAnimation.draw(getX(), getY());
	}
	
	public void update(Input input, float gravity) {
		float newX, newY;

		currentAnimation = standing;

		if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft();
		} else if (velocityX < 0) {
			velocityX += deceleration;
			if (velocityX > 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight();
		} else if (velocityX > 0) {
			velocityX -= deceleration;
			if (velocityX < 0) {
				velocityX = 0;
			}
		}

		if (input.isKeyDown(Input.KEY_RALT)) {
			jump();
		}

		if (input.isKeyDown(Input.KEY_SPACE)) {
			shoot();
		}

		if (velocityY < maxSpeedY) {
			velocityY += gravity;
		} else {
			velocityY = maxSpeedY;
		}

		if (inAir) {
			if (velocityY > 0) { // falling down
				currentAnimation = falling;
			} else if (velocityY < 0) { // jumping up
				if (velocityX > 0) {
					currentAnimation = jumpingRight;
				} else if (velocityX < 0) {
					currentAnimation = jumpingLeft;
				} else {
					currentAnimation = jumpingUp;
				}
			}
		}

		newX = getX() + velocityX;
		newY = getY() + velocityY;

		inAir = true;
		checkCollision(newX, newY);
	}

	public void moveLeft() {
		currentAnimation = runningLeft;

		if (velocityX > -maxSpeedX) {
			velocityX -= acceleration;
		} else {
			velocityX = -maxSpeedX;
		}
	}

	public void moveRight() {
		currentAnimation = runningRight;

		if (velocityX < maxSpeedX) {
			velocityX += acceleration;
		} else {
			velocityX = maxSpeedX;
		}
	}

	public void jump() {
		if (!inAir) {
			velocityY -= 8f;
			inAir = true;
		}
	}

	public void shoot() {
		try {
			new Arrow(getX(), getY() + 16 * random.nextFloat(), 32f, 0f);
		} catch (SlickException e) {
			System.err.println("Failed to create projectile, exception: " + e);
		}
	}

	public void checkCollision(float newX, float newY) {
		CollisionEntity otherEntity = null;

		setCollisionShapeX(newX); // first check x-axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				newX = collidedOnXAxis(newX, otherEntity);
			}
		}

		setX(newX); // update x position now
		setCollisionShapeX(newX); // re-adjust collision box

		setCollisionShapeY(newY); // now check y axis

		for (int i = 0; i < GameworldEntities.geometryCollision.size(); i++) {
			otherEntity = GameworldEntities.geometryCollision.get(i);
			if (getCollisionShape().intersects(otherEntity.getCollisionShape())) {
				newY = collidedOnYAxis(newY, otherEntity);
			}
		}

		setY(newY); // update y position now
		setCollisionShapeY(newY); // re-adjust collision box again
	}

	public float collidedOnXAxis(float newX, CollisionEntity otherEntity) {
		float wy = (getCollisionShape().getWidth() + otherEntity
				.getCollisionShape().getWidth())
				* (getCollisionShape().getCenterY() - otherEntity
						.getCollisionShape().getCenterY());
		float hx = (getCollisionShape().getHeight() + otherEntity
				.getCollisionShape().getHeight())
				* (getCollisionShape().getCenterX() - otherEntity
						.getCollisionShape().getCenterX());

		if (hx > wy) {
			if (wy > -hx) {
				// right side of B hit
				System.out.println("right");
				newX = otherEntity.getCollisionShape().getMaxX();
				velocityX = 0f;
			}
		} else {
			if (!(wy > -hx)) {
				// left side of B hit
				System.out.println("left");
				newX = otherEntity.getCollisionShape().getMinX()
						- getCollisionShape().getWidth();
				velocityX = 0f;
			}
		}

		return newX;
	}

	public float collidedOnYAxis(float newY, CollisionEntity otherEntity) {
		float wy = (getCollisionShape().getWidth() + otherEntity
				.getCollisionShape().getWidth())
				* (getCollisionShape().getCenterY() - otherEntity
						.getCollisionShape().getCenterY());
		float hx = (getCollisionShape().getHeight() + otherEntity
				.getCollisionShape().getHeight())
				* (getCollisionShape().getCenterX() - otherEntity
						.getCollisionShape().getCenterX());

		if (hx > wy) {
			if (!(wy > -hx)) {
				// bottom side of B hit
				System.out.println("bottom");
				newY = otherEntity.getCollisionShape().getMinY()
						- getCollisionShape().getHeight();
				collidedWithGround();
				velocityY = 0f;
			}
		} else {
			if (wy > -hx) {
				// top side of B hit
				System.out.println("top");
				newY = otherEntity.getCollisionShape().getMaxY();
				if (velocityY < 0) {
					velocityY = 0f;
				}
			}
		}

		return newY;
	}

	public void collidedWithGround() {
		if (inAir) {
			inAir = false;
		}
	}
      */
}
