package com.tt.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tt.game.MyGame;

public class MenuScreen implements Screen{

	final MyGame myGame;
	OrthographicCamera camera;
	
	public MenuScreen(MyGame myGame) {
		
		this.myGame = myGame;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
	}	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        myGame.batch.setProjectionMatrix(camera.combined);

        myGame.batch.begin();
        myGame.font.draw(myGame.batch, "Welcome!!! ", 100, 150);
        myGame.font.draw(myGame.batch, "Tap anywhere to begin!!!!!", 100, 100);
        myGame.batch.end();

        if (Gdx.input.isTouched()) {
        	//myGame.setScreen(new GameScreen(myGame));
        	myGame.setScreen(new LoadingScreen(myGame));
            dispose();
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
