package com.tt.game.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.tt.game.MyGame;
import com.tt.game.engine.rules.BasicFlipRule;
import com.tt.game.engine.rules.FlipRule;
import com.tt.game.views.CardView;

public class SelectCardsScreen implements Screen {

	MyGame myGame;
	ArrayList<CardView> allCards, handOne, handTwo;
	private ArrayList<FlipRule> flipRules;

	public SelectCardsScreen(MyGame myGame) {
		this.myGame = myGame;

		allCards = new ArrayList<CardView>();
		handOne = new ArrayList<CardView>();
		handTwo = new ArrayList<CardView>();
		flipRules = new ArrayList<FlipRule>();

		allCards.add(new CardView(1, 1, new int[] { 1, 2, 3, 4 }, "Derpy",
				myGame.manager.get("sampleCard2.jpg", Texture.class)));
		allCards.add(new CardView(1, 1, new int[] { 3, 2, 3, 1 }, "DerpyTwo",
				myGame.manager.get("sampleCard.png", Texture.class)));

		int counter = 0;
		for (CardView card : allCards) {
			card.setBounds(100 + (300 * counter), 400, 300, 300);
			counter++;
		}

		// For testing
		
		Random randomGenerator = new Random();
		int[] tempPowerArray =  new int[4];
		for (int i = 0; i < 5; i++) {
			tempPowerArray[0] = randomGenerator.nextInt(10);
			tempPowerArray[1] = randomGenerator.nextInt(10);
			tempPowerArray[2] = randomGenerator.nextInt(10);
			tempPowerArray[3] = randomGenerator.nextInt(10);		
			
			handOne.add(new CardView(1, 1, tempPowerArray, "Derpy",
					myGame.manager.get("sampleCard2.jpg", Texture.class)));
			
			tempPowerArray[0] = randomGenerator.nextInt(10);
			tempPowerArray[1] = randomGenerator.nextInt(10);
			tempPowerArray[2] = randomGenerator.nextInt(10);
			tempPowerArray[3] = randomGenerator.nextInt(10);
			
			handTwo.add(new CardView(1, 2, tempPowerArray, "Derpy",
					myGame.manager.get("sampleCard2.jpg", Texture.class)));
		}

		flipRules.add(new BasicFlipRule());

		

	}

	@Override
	public void show() {
		for (CardView card : allCards) {
			myGame.stage.addActor(card);
		}
		// For testing
		myGame.setScreen(new GameScreen(myGame, handOne, handTwo, flipRules));
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
