package com.mygdx.Sprites.Objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Scenes.Hud;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Entities.EntityDef;
import com.mygdx.Sprites.Entities.Mushroom;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * Class for the coin object
 * @see InteractiveObject
 */
public class Coin extends InteractiveObject {
    private static TiledMapTileSet tileSet;
    private final int WITHOUT_COIN_PLACE = 28;

    /**
     * Constructor for the coin
     * @param screen
     * @param object
     */
    public Coin(Play screen, MapObject object){
        super(screen, object);

        tileSet = map.getTileSets().getTileSet("world_sprites");
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.COIN);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(getCell().getTile().getId() == WITHOUT_COIN_PLACE)
            SuperMario.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom"))
            {
                screen.spawnItem(new EntityDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / SuperMario.PPM),Mushroom.class));
                SuperMario.manager.get("audio/sounds/mushroom.wav", Sound.class).play();
            }
            else
                SuperMario.manager.get("audio/sounds/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(WITHOUT_COIN_PLACE));
            Hud.addScore(200);
        }
    }
}
