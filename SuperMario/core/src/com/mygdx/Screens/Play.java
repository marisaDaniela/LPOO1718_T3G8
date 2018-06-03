package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Scenes.Hud;
import com.mygdx.Sprites.Enemies.Enemy;
import com.mygdx.Sprites.Entities.Entity;
import com.mygdx.Sprites.Entities.EntityDef;
import com.mygdx.Sprites.Entities.Mushroom;
import com.mygdx.Sprites.Mario;
import com.mygdx.SuperMario;
import com.mygdx.Tools.WorldCreator;
import com.mygdx.Tools.WorldContactListener;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Play - constructor for the screen of the game, including all entities and objects for playing the game
 */
public class Play implements Screen{
    private SuperMario game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private WorldCreator creator;
    private Mario player;
    private Music music;
    private Array<Entity> items;
    private LinkedBlockingQueue<EntityDef> itemsToSpawn;


    /**
     * Constructor to create a game to play
     * @param game - supermario game
     */
    public Play(SuperMario game){
        atlas = new TextureAtlas("characters.pack");
        this.game = game;
        gamecam = new OrthographicCamera();

        gamePort = new FitViewport(SuperMario.WIDTH_V / SuperMario.PPM, SuperMario.HEIGHT_V / SuperMario.PPM, gamecam);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / SuperMario.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        creator = new WorldCreator(this);
        player = new Mario(this);
        world.setContactListener(new WorldContactListener());
        music = SuperMario.manager.get("audio/music/music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        items = new Array<Entity>();
        itemsToSpawn = new LinkedBlockingQueue<EntityDef>();
    }

    public void spawnItem(EntityDef idef){
        itemsToSpawn.add(idef);
    }


    /**
     * Function to handle spawning items
     */
    public void hadleSpawning(){
        if(!itemsToSpawn.isEmpty()){
            EntityDef entityDef = itemsToSpawn.poll();
            if(entityDef.type == Mushroom.class){
                items.add(new Mushroom(this, entityDef.position.x, entityDef.position.y));
            }
        }
    }

    /**
     * Function to get the textureAtlas
     * @return atlas
     */
    public TextureAtlas getAtlas(){
        return atlas;
    }

    /**
     * Function to handle with input of the user
     * @param deltaTime
     */
    public void handleInput(float deltaTime){
        if(player.currState != Mario.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                player.fire();
        }
    }

    /**
     * Function to update the state of the game
     * @param deltaTime
     */
    public void update(float deltaTime){
        handleInput(deltaTime);
        hadleSpawning();

        world.step(1 / 60f, 6, 2);

        player.update(deltaTime);
        for(Enemy enemy : creator.getEnemies()) {
            enemy.update(deltaTime);
            if(enemy.getX() < player.getX() + 224 / SuperMario.PPM) {
                enemy.b2body.setActive(true);
            }
        }

        for(Entity entity : items)
            entity.update(deltaTime);

        hud.update(deltaTime);

        if(player.currState != Mario.State.DEAD) {
            gamecam.position.x = player.b2body.getPosition().x;
        }

        gamecam.update();
        renderer.setView(gamecam);
    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for (Enemy enemy : creator.getEnemies())
            enemy.draw(game.batch);
        for (Entity entity : items)
            entity.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver()){
            game.setScreen(new GameOver(game));
            dispose();
        }
    }

    /**
     * Function to check if game is over or no
     * @return true if game is over (Mario dead), false otherwise
     */
    public boolean gameOver(){
        if(player.currState == Mario.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    /**
     * Method to get TiledMap
     * @return map
     */
    public TiledMap getMap(){
        return map;
    }
    /**
     * Method to get the world
     * @return world
     */
    public World getWorld(){
        return world;
    }

    /**
     * Method to get the hud
     * @return hud
     */
    public Hud getHud(){
        return hud;
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void show() {}
}
