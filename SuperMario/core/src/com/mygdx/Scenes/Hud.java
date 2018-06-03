package com.mygdx.Scenes;

import com.mygdx.SuperMario;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Hud - class to display information about the time, level and score.
 */
public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;
    private boolean timeUp;
    private float counter;
    private static Integer score;
    private static Label scoreLabel;
    private Label time, level, world, marioLabel, countdown;
    private Integer timer;

    /**
     * Constructor for head-up display
     * @param spriteBatch - object that draws images to the screen
     */
    public Hud(SpriteBatch spriteBatch) {
        score = 0;
        timer = 200;
        counter = 0;
        viewport = new FitViewport(SuperMario.WIDTH_V, SuperMario.HEIGHT_V, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        time = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        world = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        countdown = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        level = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(marioLabel).expandX().padTop(2);
        table.add(world).expandX().padTop(2);
        table.add(time).expandX().padTop(2);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(level).expandX();
        table.add(countdown).expandX();

        stage.addActor(table);
    }

    /**
     * Function to updates the HUD over time
     * @param deltaTime - time period
     */
    public void update(float deltaTime){
        counter += deltaTime;
        if(counter >= 1){
            if (timer > 0)
                timer--;
            else
                timeUp = true;
            countdown.setText(String.format("%03d", timer));
            counter = 0;
        }
    }

    /**
     * Function to increment the score over time
     * @param value - value to show in the score label
     */
    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    /**
     * Method to dispose the stage
     */
    public void dispose() { stage.dispose(); }

    /**
     * Method to return check the time is over or not
     * @return true if the time is over, false otherwise
     */
    public boolean isTimeUp() { return timeUp; }
}
