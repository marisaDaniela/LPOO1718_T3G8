package com.mygdx.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * Class for Mario's enemy turtle
 */
public class Turtle extends Enemy {
    public static final int KICK_LEFT = -2;
    public static final int KICK_RIGHT = 2;
    public State currState;
    public State prevState;

    private float stateTime;
    private Animation walk;
    private Array<TextureRegion> frames;

    private TextureRegion shell;

    public enum State {
        WALKING,
        MOV_SHELL,
        STAND_SHELL
    }

    /**
     * Turtle's constructor
     * @param screen - actual screen of the game
     * @param x - x position
     * @param y - y position
     */
    public Turtle(Play screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 0, 0, 16, 24));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 16, 0, 16, 24));
        shell = new TextureRegion(screen.getAtlas().findRegion("turtle"), 64, 0, 16, 24);
        walk = new Animation(0.2f, frames);
        currState = prevState = State.WALKING;

        setBounds(getX(), getY(), 16 / SuperMario.PPM, 24 / SuperMario.PPM);

    }

    @Override
    protected void createEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMario.PPM);
        fixtureDef.filter.categoryBits = SuperMario.ENEMY;
        fixtureDef.filter.maskBits = SuperMario.GROUND | SuperMario.COIN | SuperMario.BRICK | SuperMario.ENEMY | SuperMario.OBJECT | SuperMario.MARIO;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);

        PolygonShape head = new PolygonShape();

        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-5, 8).scl(1 / SuperMario.PPM);
        vertice[1] = new Vector2(5, 8).scl(1 / SuperMario.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / SuperMario.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / SuperMario.PPM);
        head.set(vertice);

        fixtureDef.shape = head;
        fixtureDef.restitution = 1.8f;
        fixtureDef.filter.categoryBits = SuperMario.ENEMY_HEAD;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    /**
     * Function to get the frame of the turtle in that time
     * @param deltaTime - period of the time
     * @return TextureRegion of the turtle
     */
    public TextureRegion getFrameTextureRegion(float deltaTime){
        TextureRegion region;

        switch (currState){
            case MOV_SHELL:
            case STAND_SHELL:
                region = shell;
                break;
            case WALKING:
            default:
                region = (TextureRegion) walk.getKeyFrame(stateTime, true);
                break;
        }

        if(velocity.x > 0 && region.isFlipX() == false){
            region.flip(true, false);
        }
        if(velocity.x < 0 && region.isFlipX() == true){
            region.flip(true, false);
        }

        if(currState == prevState)
            stateTime = stateTime + deltaTime;
        else
            stateTime = 0;
        prevState = currState;

        return region;
    }

    @Override
    public void update(float deltaTime) {
        setRegion(getFrameTextureRegion(deltaTime));
        if(currState == State.STAND_SHELL && stateTime > 5) {
            currState = State.WALKING;

            velocity.x = 1;
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - 8 / SuperMario.PPM);
        b2body.setLinearVelocity(velocity);
    }

    @Override
    public void killEnemy(Mario mario) {
        if(currState == State.STAND_SHELL) {
            if(mario.b2body.getPosition().x > b2body.getPosition().x)
                velocity.x = -2;
            else
                velocity.x = 2;
            currState = State.MOV_SHELL;
        }
        else {
            currState = State.STAND_SHELL;
            velocity.x = 0;
        }
    }

    @Override
    public void hitByEnemy(Enemy enemy) {
        reverseVelocity(true, false);
    }

    /**
     * Function that changes the state of the shell according to the kick
     * @param direction - direction where shell will move
     */
    public void kick(int direction){
        velocity.x = direction;
        currState = State.MOV_SHELL;
    }
}
