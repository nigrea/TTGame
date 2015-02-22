package com.tt.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.tt.game.Card;
import com.tt.game.MyGame;
import com.tt.game.engine.rules.BasicFlipRule;
import com.tt.game.engine.rules.DoubleFlipRule;
import com.tt.game.engine.rules.FlipRule;
import com.tt.game.views.CardView;
import com.tt.game.screens.TestMenu;

public class SelectCardsScreen implements Screen {

	private MyGame myGame;
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


			TextButtonStyle textButtonStyle = myGame.skin.get(TextButtonStyle.class);
			Table buttonTable = new Table();
			TextButton backButton = new TextButton("Back",textButtonStyle);
			buttonTable.setFillParent(true);
			buttonTable.add(backButton).expand().bottom().right();
			myGame.stage.addActor(buttonTable);		
			backButton.addListener(new ButtonListener(myGame));		
		
			TextButton pOneSelectButton = new TextButton("To Player 1",textButtonStyle);
			pOneSelectButton.addListener(new PlayerCardSelectListener(myGame, 1));
			TextButton pTwoSelectButton = new TextButton("To Player 2",textButtonStyle);
			pTwoSelectButton.addListener(new PlayerCardSelectListener(myGame, 2));	
			buttonTable.add(pOneSelectButton).bottom();
			buttonTable.add(pTwoSelectButton).bottom();
	
			
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
		myGame.stage.clear();
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

	public class ButtonListener extends ChangeListener{

		MyGame myGame;
		
		public ButtonListener(MyGame myGame){
			this.myGame = myGame;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			myGame.stage.clear();
			myGame.setScreen(new TestMenu(myGame));
		}
	}	
	public class StartGameListener extends ChangeListener{

		MyGame myGame;
			
		public StartGameListener(MyGame myGame){
			this.myGame = myGame;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			myGame.stage.clear();
			myGame.setScreen(new GameScreen(myGame, handOne, handTwo));
		}	
	}
	
	public class PlayerCardSelectListener extends ChangeListener{

		MyGame myGame;
		int player;
		
		public PlayerCardSelectListener(MyGame myGame, int player){
			this.myGame = myGame;
			this.player = player;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			if(player == 1){
				if (handOne.size() <= 4){
					CardView card = new CardView(myGame, selected.card, selected.picture.getTexture());
					card.setPlayer(1);					
					handOne.add(card);
					System.out.println("Reach hand size one");
					System.out.println(handOne.size());
					
				};
			}
			else if(handTwo.size() <= 4){
				System.out.println("Reach hand size two");
				CardView card = new CardView(myGame, selected.card, selected.picture.getTexture());
				card.setPlayer(2);
				handTwo.add(card);
				
				System.out.println(handTwo.size());
				}
				
	
			if(handOne.size() == 5 && handTwo.size() == 5){
				TextButtonStyle textButtonStyle = myGame.skin.get(TextButtonStyle.class);	 
				TextButton startGameButton = new TextButton("StartGame",textButtonStyle);
				startGameButton.addListener(new StartGameListener(myGame));
				Table startGameTable = new Table();
				startGameTable.setFillParent(true);
				startGameTable.add(startGameButton);
				myGame.stage.clear();
				myGame.stage.addActor(startGameTable);	
			}	
			
			}
		
	}
	
}
