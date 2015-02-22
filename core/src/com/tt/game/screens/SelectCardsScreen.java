package com.tt.game.screens;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.tt.game.Card;
import com.tt.game.MyGame;
import com.tt.game.engine.rules.BasicFlipRule;
import com.tt.game.engine.rules.DoubleFlipRule;
import com.tt.game.engine.rules.FlipRule;
import com.tt.game.views.CardView;

public class SelectCardsScreen implements Screen {

	MyGame myGame;
	ArrayList<CardView> allCards, handOne, handTwo;


	public SelectCardsScreen(MyGame myGame) {
		this.myGame = myGame;

		allCards = new ArrayList<CardView>();



		for (Card card : myGame.cards) {
			Texture pic;
			if(myGame.manager.isLoaded("cardArt/"+card.getId()+".jpg")){
				pic = myGame.manager.get("cardArt/"+card.getId()+".jpg", Texture.class);				
			}else{
				pic = myGame.manager.get("cardArt/default.jpg", Texture.class);				
			}
			allCards.add(new CardView(myGame, card, pic));
		}


		int counter = 0;
		for (CardView card : allCards) {
			card.setBounds(100 + (300 * counter), 400, 300, 300);
			counter++;
		}

		// For testing
		
		

	}

	@Override
	public void show() {
		for (CardView card : allCards) {
			myGame.stage.addActor(card);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		myGame.stage.draw();
		myGame.stage.act(delta);
	}

	@Override
	public void resize(int width, int height) {
		myGame.stage.getViewport().update(width, height, true);
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
		myGame.stage.dispose();
	}

}
