package com.mygdx.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.SuperMario;

/**
 * GameOver - class to show game Over state
 */
public class GameOver implements Screen {
    private Game game;
    private Viewport viewport;
    private Stage stage;

    /**
     * Constructor for gameOver that defines the style of game over screen
     * @param game
     */
    public GameOver(Game game){
        this.game = game;
        viewport = new FitViewport(SuperMario.WIDTH_V, SuperMario.HEIGHT_V, new OrthographicCamera());
        stage = new Stage(viewport, ((SuperMario) game).batch);

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.RED);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label game_over = new Label("GAME OVER", style);
        Label press_to_play_again = new Label("Press to play again", style);

        table.add(game_over).expandX();
        table.row();
        table.add(press_to_play_again).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    /**
     * Render function - function that checks if the screen is touched, if yes, calls a new game
     * @param deltaTime
     */
    public void render(float deltaTime) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new Play((SuperMario) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    /**
     * Function to dispose the stage
     */
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
