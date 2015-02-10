package com.tt.game.screens;

import com.badlogic.gdx.Screen;
import com.tt.game.MyGame;

public class LoadingScreen implements Screen {
	
	MyGame myGame;
	
	public LoadingScreen(MyGame myGame){
		this.myGame = myGame;		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if(myGame.manager.update()) {
			myGame.setScreen(new GameScreen(myGame));
			//myGame.setScreen(new SelectCardsScreen(myGame));
	      }

	      // display loading information
	      float progress = myGame.manager.getProgress();
	      System.out.println(progress);
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
