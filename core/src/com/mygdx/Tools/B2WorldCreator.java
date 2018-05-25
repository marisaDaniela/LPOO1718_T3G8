package com.mygdx.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Aunios;
import com.mygdx.Screens.PlayScreen;
import com.mygdx.Sprites.Brick;
import com.mygdx.Sprites.Coin;
import com.mygdx.Sprites.Mario;

public class B2WorldCreator {

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef =  new FixtureDef();
        Body body;

        // ground
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ Aunios.PPM, (rect.getY() + rect.getHeight() / 2)/ Aunios.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2/ Aunios.PPM, rect.getHeight() / 2/ Aunios.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);
        }

        // pipes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ Aunios.PPM, (rect.getY() + rect.getHeight() / 2)/ Aunios.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2/ Aunios.PPM, rect.getHeight() / 2/ Aunios.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = Aunios.OBJECT_BIT;

            body.createFixture(fdef);
        }

        // coins
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Coin(screen,rect);
        }

        // bricks
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen,rect);
        }
    }
}
