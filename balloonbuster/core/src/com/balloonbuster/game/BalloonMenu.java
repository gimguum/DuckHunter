package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class BalloonMenu extends BaseScreen {
    private BaseActor bg;
    private BaseActor title;

    private Label text;

    public BalloonMenu(Game game) {
        super(game);
    }

    @Override
    protected void create() {

        bg = ActorManger.createActor(mainStage, "menu-background.png", viewWidth / 2, viewHeight / 2);
        title = ActorManger.createActor(mainStage, "title.png", viewWidth / 2, viewHeight / 2);

        BitmapFont font = new BitmapFont();
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        text = new Label("LET'S HUNT!", style);
        text.setPosition(viewWidth / 2 - text.getWidth() / 2, 170);
        text.setFontScale(2);
        text.setAlignment(Align.center);

        uiStage.addActor(text);
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new BalloonLevel(game));
        return false;
    }
}
