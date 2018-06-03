package com.mygdx.Sprites.Objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * InteractiveObject - abstract class for the objects of the map, like bricks and coins that mario interacts with
 */
public abstract class InteractiveObject {
    protected Body body;
    protected Play screen;
    protected MapObject object;
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Fixture fixture;

    /**
     * Constructor for the interactiveTileObject
     * @param screen
     * @param object
     */
    public InteractiveObject(Play screen, MapObject object){
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / SuperMario.PPM, (bounds.getY() + bounds.getHeight() / 2) / SuperMario.PPM);

        body = world.createBody(bodyDef);

        shape.setAsBox(bounds.getWidth() / 2 / SuperMario.PPM, bounds.getHeight() / 2 / SuperMario.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

    }

    /*+
    Function that checks if the mario hits the interactive tile object with his head
     */
    public abstract void onHeadHit(Mario mario);

    /**
     * Function that set the category filter of the interactive tile object
     * @param filterBit
     */
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    /**
     * Function to gets tiledMapTileLayer Cell of the map
     * @return cell of the map
     */
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);

        return layer.getCell((int)(body.getPosition().x * SuperMario.PPM / 16),
                (int)(body.getPosition().y * SuperMario.PPM / 16));
    }

}
