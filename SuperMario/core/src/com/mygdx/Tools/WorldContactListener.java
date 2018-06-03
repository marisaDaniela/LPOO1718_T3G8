package com.mygdx.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.Sprites.Enemies.Enemy;
import com.mygdx.Sprites.Entities.Entity;
import com.mygdx.Sprites.Entities.FireBall;
import com.mygdx.Sprites.Mario;
import com.mygdx.Sprites.Objects.InteractiveObject;
import com.mygdx.SuperMario;

/**
 * WorldContactListener - class to begin contact with the world
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int cDef = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (cDef){
            case SuperMario.MARIO_HEAD | SuperMario.BRICK:
            case SuperMario.MARIO_HEAD | SuperMario.COIN:
                if(fixtureA.getFilterData().categoryBits == SuperMario.MARIO_HEAD) {
                    ((InteractiveObject) fixtureB.getUserData()).onHeadHit((Mario) fixtureA.getUserData());
                }
                else {
                    ((InteractiveObject) fixtureA.getUserData()).onHeadHit((Mario) fixtureB.getUserData());
                }
                break;
            case SuperMario.ENEMY_HEAD | SuperMario.MARIO:
                if(fixtureA.getFilterData().categoryBits == SuperMario.ENEMY_HEAD) {
                    ((Enemy) fixtureA.getUserData()).killEnemy((Mario) fixtureB.getUserData());
                }
                else {
                    ((Enemy) fixtureB.getUserData()).killEnemy((Mario) fixtureA.getUserData());
                }
                break;
            case SuperMario.ENEMY | SuperMario.OBJECT:
                if(fixtureA.getFilterData().categoryBits == SuperMario.ENEMY) {
                    ((Enemy) fixtureA.getUserData()).reverseVelocity(true, false);
                }
                else {
                    ((Enemy) fixtureB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case SuperMario.MARIO | SuperMario.ENEMY:
                if(fixtureA.getFilterData().categoryBits == SuperMario.MARIO) {
                    ((Mario) fixtureA.getUserData()).hit((Enemy) fixtureB.getUserData());
                }
                else {
                    ((Mario) fixtureB.getUserData()).hit((Enemy) fixtureA.getUserData());
                }
                break;
            case SuperMario.ENEMY | SuperMario.ENEMY:
                ((Enemy) fixtureA.getUserData()).hitByEnemy((Enemy)fixtureB.getUserData());
                ((Enemy)fixtureB.getUserData()).hitByEnemy((Enemy) fixtureA.getUserData());
                break;
            case SuperMario.ENTITY | SuperMario.OBJECT:
                if(fixtureA.getFilterData().categoryBits == SuperMario.ENTITY) {
                    ((Entity) fixtureA.getUserData()).reverseVelocity(true, false);
                }
                else {
                    ((Entity) fixtureB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case SuperMario.ENTITY | SuperMario.MARIO:
                if(fixtureA.getFilterData().categoryBits == SuperMario.ENTITY) {
                    ((Entity) fixtureA.getUserData()).use((Mario) fixtureB.getUserData());
                }
                else {
                    ((Entity) fixtureB.getUserData()).use((Mario) fixtureA.getUserData());
                }
                break;
            case SuperMario.FIREBALL | SuperMario.OBJECT:
                if(fixtureA.getFilterData().categoryBits == SuperMario.FIREBALL) {
                    ((FireBall) fixtureA.getUserData()).setToDestroy();
                }
                else {
                    ((FireBall) fixtureB.getUserData()).setToDestroy();
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
