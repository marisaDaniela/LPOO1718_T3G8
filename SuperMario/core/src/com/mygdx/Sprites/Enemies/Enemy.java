package com.mygdx.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;

/**
 * Enemy - abstract class to implement methods for the enemies of Mario.
 */
public abstract class Enemy extends Sprite {
    protected World world;
    protected Play screen;
    public Body b2body;
    public Vector2 velocity;

    /**
     * Constuctor to Mario's enemy
     * @param screen
     * @param x
     * @param y
     */
    public Enemy(Play screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        createEnemy();
        velocity = new Vector2(-1, -2);
        b2body.setActive(false);
    }

    /**
     * function to define the diferent enemies of Mario
     */
    protected abstract void createEnemy();

    /**
     * Function to updates Mario enemies, like movement, state (killed, or alive)
     * @param deltaTime
     */
    public abstract void update(float deltaTime);

    /**
     * Function that verifies if Mario hit on head of the enemy
     * @param mario
     */
    public abstract void killEnemy(Mario mario);

    /**
     * Function that verifies if Mario is hit by the enemy
     * @param enemy
     */
    public abstract void hitByEnemy(Enemy enemy);

    /**
     * Function that reverses the velocity of the enemy
     * @param x
     * @param y
     */
    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}
