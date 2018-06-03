package com.mygdx.Sprites.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Screens.Play;
import com.mygdx.SuperMario;

/**
 * Class for the fireball object
 */
public class FireBall extends Sprite {
    float time;
    boolean destroyed, setToDestroy, fireRight;
    Play screen;
    World world;
    Array<TextureRegion> frames;
    Animation fire;

    Body b2body;

    /**
     * Constructor for the fireball
     * @param screen
     * @param x
     * @param y
     * @param fireRight
     */
    public FireBall(Play screen, float x, float y, boolean fireRight){
        this.fireRight = fireRight;
        this.screen = screen;
        this.world = screen.getWorld();
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("fireball"), i * 8, 0, 8, 8));
        }

        fire = new Animation(0.2f, frames);
        setRegion((Texture) fire.getKeyFrame(0));
        setBounds(x, y, 6 / SuperMario.PPM, 6 / SuperMario.PPM);
        createFireBall();
    }

    /**
     * Function that defines the fireBall.
     */
    public void createFireBall(){
        BodyDef bodyDef = new BodyDef();

        if(fireRight) {
            bodyDef.position.set(getX() + 12 / SuperMario.PPM, getY());
        }
        else {
            bodyDef.position.set(getX() - 12 / SuperMario.PPM, getY());
        }

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if(!world.isLocked())
            b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / SuperMario.PPM);

        fixtureDef.filter.categoryBits = SuperMario.FIREBALL;
        fixtureDef.filter.maskBits = SuperMario.GROUND | SuperMario.COIN |  SuperMario.BRICK | SuperMario.ENEMY | SuperMario.OBJECT;

        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        fixtureDef.friction = 0;
        b2body.createFixture(fixtureDef).setUserData(this);
        b2body.setLinearVelocity(new Vector2(fireRight ? 2 : -2, 2.5f));
    }

    /**
     * Function that updates fireball accordingly to the period of time
     * @param deltaTime
     */
    public void update(float deltaTime){
        time += deltaTime;
        setRegion((Texture) fire.getKeyFrame(time, true));

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if(!destroyed  && (time > 3 || setToDestroy)) {
            world.destroyBody(b2body);
            destroyed = true;
        }

        if(b2body.getLinearVelocity().y > 2f)
            b2body.setLinearVelocity(b2body.getLinearVelocity().x, 2f);
        if((fireRight && b2body.getLinearVelocity().x < 0) || (!fireRight && b2body.getLinearVelocity().x > 0))
            setToDestroy();
    }

    /**
     * Function to sets to destroy
     */
    public void setToDestroy(){
        setToDestroy = true;
    }

    /**
     * Function to return if is destroyed or not
     * @return true if is destroyed, false otherwise
     */
    public boolean isDestroyed(){
        return destroyed;
    }


}
