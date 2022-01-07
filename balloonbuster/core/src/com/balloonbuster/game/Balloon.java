package com.balloonbuster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Balloon extends BaseActor {
    private float speed;
    private float amplitude;
    private float oscillation;
    private float initialY;
    private float time;
    private int offsetX;
    private boolean isPopped = false;

    BalloonLevel bl;

    public Balloon(BalloonLevel ballonLevel) {
        this.bl = ballonLevel;

        speed = 200 * MathUtils.random(0.5f, 2.0f);
        amplitude = 100 * MathUtils.random(0.5f, 2.0f);
        oscillation = 0.01f * MathUtils.random(0.5f, 2.0f);
        initialY = 120 * MathUtils.random(0.5f, 2.0f);
        time = 0;
        offsetX = -100;

        setTexture(new Texture(Gdx.files.internal("duck.png")));
        //setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);

        setX(offsetX);
    }


    @Override
    public void act(float dt) {
        super.act(dt);

        time += dt;

        float xPos = speed * time + offsetX;
        float yPos = amplitude * MathUtils.sin(oscillation * xPos) + initialY;
        setPosition(xPos, yPos);

        if (!isPopped && getX() > bl.mapWidth) {
            bl.escapedInc();
            remove();
        }
    }

    public void popBalloon() {
        isPopped = true;

        Action popped = Actions.sequence(
                Actions.parallel(
                        Actions.fadeOut(1),
                        Actions.scaleTo(0,0, 2)),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        remove();
                    }
                })
        );

        addAction(popped);
    }
}
