package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Screens.Play;

/**
 * SuperMario - class for the flags bits and load the musics for the game
 */
public class SuperMario extends Game {
	public static final short DEAD = 0;
	public static final short GROUND = 1;
	public static final short MARIO = 2;
	public static final short BRICK = 4;
	public static final short COIN = 8;
	public static final short DESTROYED_BRICK = 16;
	public static final short OBJECT = 32;
	public static final short ENEMY = 64;
	public static final short ENEMY_HEAD = 128;
	public static final short ENTITY = 256;
	public static final short MARIO_HEAD = 512;
	public static final short FIREBALL = 1024;

    public static final int WIDTH_V = 400;
    public static final int HEIGHT_V = 208;
    public static final float PPM = 100;


    public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
        manager.load("audio/sounds/powerup.wav", Sound.class);
        manager.load("audio/sounds/powerdown.wav", Sound.class);
        manager.load("audio/sounds/stomp.wav", Sound.class);
        manager.load("audio/sounds/mariodie.wav", Sound.class);
		manager.load("audio/music/music.ogg", Music.class);
		manager.load("audio/sounds/coin.wav", Sound.class);
		manager.load("audio/sounds/bump.wav", Sound.class);
		manager.load("audio/sounds/breakblock.wav", Sound.class);
		manager.load("audio/sounds/mushroom.wav", Sound.class);


		manager.finishLoading();

		setScreen(new Play(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
		super.render();
	}
}
