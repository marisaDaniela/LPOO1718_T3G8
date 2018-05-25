package com.mygdx.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Aunios;
import com.mygdx.Screens.PlayScreen;

public class Goomba extends Enemy {
    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Goomba(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        frames = new Array<TextureRegion>();
        for(int i= 0; i < 2; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"),i*16,0,16,16));
        }
        walkAnimation = new Animation(0.4f,frames);
        stateTime = 0;
        setBounds(getX(),getY(),16/Aunios.PPM,16/Aunios.PPM);
    }
    public void update(float deltaTime){
        stateTime += deltaTime;
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Aunios.PPM);
        fdef.filter.categoryBits = Aunios.ENEMY_BIT;
        fdef.filter.maskBits = Aunios.DEFAULT_BIT | Aunios.COIN_BIT | Aunios.BRICK_BIT | Aunios.ENEMY_BIT | Aunios.OBJECT_BIT | Aunios.MARIO_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
