package com.mygdx.Sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Enemies.Enemy;
import com.mygdx.Sprites.Enemies.Turtle;
import com.mygdx.Sprites.Entities.FireBall;
import com.mygdx.SuperMario;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Mario - class for Mario
 */
public class Mario extends Sprite {
    public enum State {
        JUMPING, RUNNING,FALLING,GROWING,STANDING,DEAD
    };
    public State currState;
    public State prevState;
    public World world;
    public Body b2body;
    private TextureRegion marioStand, marioJump, marioDead, bigMarioStand, bigMarioJump;
    private Animation bigMarioRun, growMario, marioRun;

    private float stateTimer;
    private boolean runningRight, isBigMario, runBigAnimation, defineBigMario, redefineMario, idMarioDead;
    private Play screen;

    private Array<FireBall> fireballs;

    public Mario(Play screen){
        this.screen = screen;
        this.world = screen.getWorld();
        currState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("little_mario"), i * 16, 0, 16, 16));
        marioRun = new Animation(0.1f, frames);

        frames.clear();

        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), i * 16, 0, 16, 32));
        bigMarioRun = new Animation(0.1f, frames);

        frames.clear();

        frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 240, 0, 16, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 0, 0, 16, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 240, 0, 16, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 0, 0, 16, 32));
        growMario = new Animation(0.2f, frames);

        marioJump = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 80, 0, 16, 16);
        bigMarioJump = new TextureRegion(screen.getAtlas().findRegion("big_mario"), 80, 0, 16, 32);

        marioStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
        bigMarioStand = new TextureRegion(screen.getAtlas().findRegion("big_mario"), 0, 0, 16, 32);

        marioDead = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 96, 0, 16, 16);

        defineMario();

        setBounds(0, 0, 16 / SuperMario.PPM, 16 / SuperMario.PPM);
        setRegion(marioStand);

        fireballs = new Array<FireBall>();

    }

    /**
     * Function to updates Mario according with delta time
     * @param deltaTime
     */
    public void update(float deltaTime){
        if (screen.getHud().isTimeUp() && !isDead()) {
            die();
        }
        if(isBigMario)
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 - 6 / SuperMario.PPM);
        else
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(deltaTime));
        if(defineBigMario)
            defineBigMario();
        if(redefineMario)
            redefineMario();

        for(FireBall  ball : fireballs) {
            ball.update(deltaTime);
            if(ball.isDestroyed())
                fireballs.removeValue(ball, true);
        }
    }

    /**
     * Function to get the frames according with delta time
     * @param deltaTime
     * @return TextureRegion region
     */
    public TextureRegion getFrame(float deltaTime){
        currState = getState();
        TextureRegion region;

        switch(currState){
            case DEAD:
                region = marioDead;
                break;
            case GROWING:
                region = (TextureRegion) growMario.getKeyFrame(stateTimer);
                if(growMario.isAnimationFinished(stateTimer))
                {
                    runBigAnimation = false;
                }
                break;
            case JUMPING:
                if(isBigMario)
                    region = bigMarioJump;
                else
                    region = marioJump;
                break;
            case RUNNING:
                if(isBigMario)
                    region = (TextureRegion) bigMarioRun.getKeyFrame(stateTimer, true);
                else
                    region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                if(isBigMario)
                    region = bigMarioStand;
                else
                    region = marioStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        if(currState == prevState)
            stateTimer += deltaTime;
        else
            stateTimer = 0;
        prevState = currState;
        return region;

    }

    /**
     * Method to get the state of mario
     * @return State of mario
     */
    public State getState(){
        if(idMarioDead)
            return State.DEAD;
        else if(runBigAnimation)
            return State.GROWING;
        else if((b2body.getLinearVelocity().y > 0 && currState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && prevState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    /**
     * Function to make Mario grow
     */
    public void grow(){
        if( !isBig() ) {
            runBigAnimation = true;
            isBigMario = true;
            defineBigMario = true;
            setBounds(getX(), getY(), getWidth(), getHeight() * 2);
            SuperMario.manager.get("audio/sounds/powerup.wav", Sound.class).play();
        }
    }

    /**
     * Function to kill Mario
     */
    public void die() {
        if (!isDead()) {
            SuperMario.manager.get("audio/music/music.ogg", Music.class).stop();
            SuperMario.manager.get("audio/sounds/mariodie.wav", Sound.class).play();
            idMarioDead = true;
            Filter filter = new Filter();
            filter.maskBits = SuperMario.DEAD;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    /**
     * Function to check if Mario is dead or alive
     * @return true if Mario is dead, false otherwise
     */
    public boolean isDead() {
        return idMarioDead;
    }

    /**
     * Method to get the state timer
     * @return stateTimer
     */
    public float getStateTimer(){
        return stateTimer;
    }

    /**
     * Function to check if Mario is big or small
     * @return true if Mario is big, false otherwise
     */
    public boolean isBig(){
        return isBigMario;
    }

    /**
     * Function to makes Mario jump
     */
    public void jump(){
        if ( currState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currState = State.JUMPING;
        }
    }

    /**
     * Function that deals with hit on enemies
     * @param enemy
     */
    public void hit(Enemy enemy){
        if(enemy instanceof Turtle && ((Turtle) enemy).currState == Turtle.State.STAND_SHELL)
            ((Turtle) enemy).kick(enemy.b2body.getPosition().x > b2body.getPosition().x ? Turtle.KICK_RIGHT : Turtle.KICK_LEFT);
        else {
            if (isBigMario) {
                isBigMario = false;
                redefineMario = true;
                setBounds(getX(), getY(), getWidth(), getHeight() / 2);
                SuperMario.manager.get("audio/sounds/powerdown.wav", Sound.class).play();
            } else {
                die();
            }
        }
    }

    /**
     * Function to redefine Mario
     */
    public void redefineMario(){
        Vector2 position = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMario.PPM);
        fixtureDef.filter.categoryBits = SuperMario.MARIO;
        fixtureDef.filter.maskBits = SuperMario.GROUND | SuperMario.COIN | SuperMario.BRICK | SuperMario.ENEMY | SuperMario.OBJECT | SuperMario.ENEMY_HEAD | SuperMario.ENTITY;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / SuperMario.PPM, 6 / SuperMario.PPM), new Vector2(2 / SuperMario.PPM, 6 / SuperMario.PPM));
        fixtureDef.filter.categoryBits = SuperMario.MARIO_HEAD;
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData(this);

        redefineMario = false;

    }

    /**
     * Function to define big Mario
     */
    public void defineBigMario(){
        Vector2 currentPosition = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bdef = new BodyDef();
        bdef.position.set(currentPosition.add(0, 10 / SuperMario.PPM));
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMario.PPM);
        fdef.filter.categoryBits = SuperMario.MARIO;
        fdef.filter.maskBits = SuperMario.GROUND | SuperMario.COIN | SuperMario.BRICK | SuperMario.ENEMY | SuperMario.OBJECT |  SuperMario.ENEMY_HEAD | SuperMario.ENTITY;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        shape.setPosition(new Vector2(0, -14 / SuperMario.PPM));
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / SuperMario.PPM, 6 / SuperMario.PPM), new Vector2(2 / SuperMario.PPM, 6 / SuperMario.PPM));
        fdef.filter.categoryBits = SuperMario.MARIO_HEAD;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
        defineBigMario = false;
    }

    /**
     * Function to define Mario
     */
    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / SuperMario.PPM, 32 / SuperMario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMario.PPM);
        fdef.filter.categoryBits = SuperMario.MARIO;
        fdef.filter.maskBits = SuperMario.GROUND | SuperMario.COIN | SuperMario.BRICK |  SuperMario.ENEMY | SuperMario.OBJECT | SuperMario.ENEMY_HEAD | SuperMario.ENTITY;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / SuperMario.PPM, 6 / SuperMario.PPM), new Vector2(2 / SuperMario.PPM, 6 / SuperMario.PPM));
        fdef.filter.categoryBits = SuperMario.MARIO_HEAD;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Function that allows Mario to fire fireballs
     */
    public void fire(){
        fireballs.add(new FireBall(screen, b2body.getPosition().x, b2body.getPosition().y, runningRight ? true : false));
    }

    /**
     * Function to draw fireballs and Mario
     * @param batch
     */
    public void draw(Batch batch){
        super.draw(batch);
        for(FireBall ball : fireballs)
            ball.draw(batch);
    }
}
