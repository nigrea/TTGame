package com.tt.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tt.game.Card;
import com.tt.game.MyGame;
import com.tt.game.engine.rules.BasicFlipRule;
import com.tt.game.engine.rules.DoubleFlipRule;
import com.tt.game.engine.rules.FlipRule;
import com.tt.game.views.CardView;

public class SelectCardsScreen implements Screen {

	MyGame myGame;
	ArrayList<CardView> allCards, handOne, handTwo;
	private ArrayList<FlipRule> flipRules;
	private CardView selected;
	
	public SelectCardsScreen(MyGame myGame) {
		this.myGame = myGame;

		allCards = new ArrayList<CardView>();
		handOne = new ArrayList<CardView>();
		handTwo = new ArrayList<CardView>();
		flipRules = new ArrayList<FlipRule>();
		Gdx.input.setInputProcessor(myGame.stage);

		for (Card card : myGame.cards) {
			Texture pic;
			if(myGame.manager.isLoaded("cardArt/"+card.getId()+".jpg")){
				pic = myGame.manager.get("cardArt/"+card.getId()+".jpg", Texture.class);				
			}else{
				pic = myGame.manager.get("cardArt/default.jpg", Texture.class);				
			}
			allCards.add(new CardView(myGame, card, pic));
		}


		Table table = new Table();
		myGame.stage.addActor(table);
		int counter = 0;
		for (CardView card : allCards) {
			card.setBounds(200 + (300 * counter), 400, 300, 300);
			table.add(card).pad(25);
			if((counter + 1)%5 == 0){
				table.row();
			}
			card.addListener(new SelectCardListener(card));
			counter++;
		}
			table.debug();
			table.setFillParent(true);

		
		

		flipRules.add(new BasicFlipRule());
		flipRules.add(new DoubleFlipRule());

	}

	@Override
	public void show() {
		
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

	public class SelectCardListener extends InputListener {
		
		CardView card;
		
		public SelectCardListener(CardView card){			
			this.card = card;
			System.out.println("test2");
		}
		
		@Override
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			System.out.println("test");
			if(selected != card){
				
				if(selected != null){
					selected.wasUnselected();
				}
				selected = card;				
				card.wasSelected();
					
			}else{
				selected = null;
				card.wasUnselected();
			}
	        return true;
	    }						
	}
}
