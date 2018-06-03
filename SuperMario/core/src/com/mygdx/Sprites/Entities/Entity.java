package com.mygdx.Sprites.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * Entity - abstract class to define entities like mushroom
 */
public abstract class Entity extends Sprite {
    protected Play screen;
    protected World world;
    protected Vector2 velocity;
    protected boolean toDestroy, destroyed;
    protected Body body;

    /**
     * Constructor for entity
     * @param screen - actual screen of the game
     * @param x - x position
     * @param y - y position
     */
    public Entity(Play screen, float x, float y){
        this.screen = screen;
        this.world = screen.getWorld();
        toDestroy = false;
        destroyed = false;

        setPosition(x, y);
        setBounds(getX(), getY(), 16 / SuperMario.PPM, 16 / SuperMario.PPM);
        createEntity();
    }

    /**
     * Function to define an entity
     */
    public abstract void createEntity();

    /**
     * Function to use an entity
     * @param mario - actual player
     */
    public abstract void use(Mario mario);

    /**
     * Function that updates an entity over a period of time
     * @param deltaTime
     */
    public void update(float deltaTime){
        if(!destroyed && toDestroy){
            world.destroyBody(body);
            destroyed = true;
        }
    }

    /**
     * Function that draws an entity
     * @param batch
     */
    public void draw(Batch batch){
        if(!destroyed)
            super.draw(batch);
    }

    /**
     * Function that removes an entity
     */
    public void destroy(){
        toDestroy = true;
    }

    /**
     * Function that changes the direction of the entity
     * @param x - x position
     * @param y - y position
     */
    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}
