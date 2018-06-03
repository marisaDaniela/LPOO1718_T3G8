package com.mygdx.Sprites.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * Class for the mushroom to mario grow
 * @see Entity
 */
public class Mushroom extends Entity {
    /**
     * Constructor to create an entity of mushroom
     * @param screen - actual screen of the game
     * @param x - x position
     * @param y - y position
     */
    public Mushroom(Play screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("mushroom"), 0, 0, 16, 16);
        velocity = new Vector2(0.7f, 0);
    }

    @Override
    public void createEntity() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMario.PPM);
        fixtureDef.filter.categoryBits = SuperMario.ENTITY;
        fixtureDef.filter.maskBits = SuperMario.MARIO | SuperMario.OBJECT | SuperMario.GROUND | SuperMario.COIN | SuperMario.BRICK;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void use(Mario mario) {
        destroy();
        mario.grow();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}
