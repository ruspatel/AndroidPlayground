package com.rushil.firstgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyFirstGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture background;
	Texture mario;
	float velocity = 0;
	float gravity = 0.2f;
	float manY = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("mario_background.jpg");
		mario = new Texture("frame-1.png");
		manY = Gdx.graphics.getHeight() / 2;

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth() / 2 - mario.getWidth() / 2, Gdx.graphics.getHeight());


		if(Gdx.input.justTouched()){
			velocity -= 10;
		}
			velocity += gravity;
			manY -= velocity;


		if(manY <= 0){
			manY = 0;
		}else if(manY > Gdx.graphics.getHeight()){
			manY = Gdx.graphics.getHeight();
		}


		batch.draw(mario, Gdx.graphics.getWidth() / 2, manY);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
