package com.mygdx.Sprites.Objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.Scenes.Hud;
import com.mygdx.Screens.Play;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;

/**
 * Class for the brick object
 * @see InteractiveObject
 */
public class Brick extends InteractiveObject {
    /**
     * Constructor of the brick
     * @param screen
     * @param object
     */
    public Brick(Play screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig())
        {
            setCategoryFilter(SuperMario.DESTROYED_BRICK);
            getCell().setTile(null);

            Hud.addScore(300);
            SuperMario.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        SuperMario.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}
