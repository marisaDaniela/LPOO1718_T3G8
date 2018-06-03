package com.mygdx.Sprites.Enemies;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
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
 * Class for the goomba, enemy of Mario
 * @see Enemy
 */
public class Goomba extends Enemy
{
    private boolean setToDestroy, destroyed;
    private float state;
    private Animation walk;
    private Array<TextureRegion> frames;
    float angle;

    /**
     * Goomba's constructor
     * @param screen -actual screen of the game
     * @param x - x position
     * @param y - y position
     */
    public Goomba(Play screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();

        for(int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i * 16, 0, 16, 16));
        walk = new Animation(0.4f, frames);
        state = 0;
        setBounds(getX(), getY(), 16 / SuperMario.PPM, 16 / SuperMario.PPM);
        setToDestroy = false;
        destroyed = false;
        angle = 0;
    }

    /**
     * Function to updates goomba
     * @param deltaTime
     */
    public void update(float deltaTime){
        state += deltaTime;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("goomba"), 32, 0, 16, 16));
            state = 0;
        }
        else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((TextureRegion) walk.getKeyFrame(state, true));
        }
    }

    @Override
    protected void createEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

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
        fixtureDef.restitution = 0.5f;
        fixtureDef.filter.categoryBits = SuperMario.ENEMY_HEAD;

        b2body.createFixture(fixtureDef).setUserData(this);

    }

    /**
     * Function that draws goomba sprites
     * @param batch
     */
    public void draw(Batch batch){
        if(state < 1 || !destroyed)
            super.draw(batch);
    }

    @Override
    public void killEnemy(Mario mario) {
        setToDestroy = true;
        SuperMario.manager.get("audio/sounds/stomp.wav", Sound.class).play();
    }

    @Override
    public void hitByEnemy(Enemy enemy) {
        if(enemy instanceof Turtle && ((Turtle) enemy).currState == Turtle.State.MOV_SHELL)
            setToDestroy = true;
        else
            reverseVelocity(true, false);
    }
}
