package com.tt.game.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.tt.game.MyGame;
import com.tt.game.views.CardView;
import com.tt.game.views.Zone;
import com.tt.game.engine.rules.BasicFlipRule;
import com.tt.game.engine.rules.DoubleFlipRule;
import com.tt.game.engine.rules.FlipRule;
import com.tt.game.engine.rules.HiddenHandTurnRule;
import com.tt.game.engine.rules.TurnRule;

public class GameScreen implements Screen {

	private CardView selected;
	public ArrayList<CardView> handOne;
	public ArrayList<CardView> handTwo;
	private Zone[][] zones;
	private ArrayList<FlipRule> flipRules;
	private ArrayList<TurnRule> turnRules;
	public MyGame myGame;
	private Image shittyArrow; //TODO REMAKE!!
	public int turn = 0;
	private int playerOnePoints = 0;
	private int playerTwoPoints = 0;
	private Label playerOneScoreLabel, playerTwoScoreLabel, winner;
	
	
	//Delete
	//private Zone zone;
	
	
	public GameScreen(MyGame myGame) {	
		handOne = new ArrayList<CardView>();
		handTwo = new ArrayList<CardView>();
		flipRules = new ArrayList<FlipRule>();
		turnRules = new ArrayList<TurnRule>();
		turnRules.add(new HiddenHandTurnRule());
		
		this.myGame = myGame;
		Gdx.input.setInputProcessor(myGame.stage);
		myGame.stage.addActor(new Image(myGame.manager.get("background2.png", Texture.class)));
		shittyArrow = new Image(myGame.manager.get("shittyArrow.png", Texture.class));
		shittyArrow.setX(800);
		shittyArrow.setY(1200);
		myGame.stage.addActor(shittyArrow);
		
		Random randomGenerator = new Random();
		for (int i = 0; i < 5; i++) {
			int[] tempPowerArray =  new int[4];
			tempPowerArray[0] = randomGenerator.nextInt(10);
			tempPowerArray[1] = randomGenerator.nextInt(10);
			tempPowerArray[2] = randomGenerator.nextInt(10);
			tempPowerArray[3] = randomGenerator.nextInt(10);		
			
			handOne.add(new CardView(this.myGame, 1, tempPowerArray, "Derpy", myGame.manager.get("cardArt/default.jpg", Texture.class)));
			tempPowerArray =  new int[4];
			tempPowerArray[0] = randomGenerator.nextInt(10);
			tempPowerArray[1] = randomGenerator.nextInt(10);
			tempPowerArray[2] = randomGenerator.nextInt(10);
			tempPowerArray[3] = randomGenerator.nextInt(10);
			handTwo.add(new CardView(this.myGame, 2, tempPowerArray, "Derpy", myGame.manager.get("cardArt/default.jpg", Texture.class)));
			
			handOne.get(i).setPlayer(1);
			handTwo.get(i).setPlayer(2);
		}

		flipRules.add(new BasicFlipRule());
		flipRules.add(new DoubleFlipRule());
		
		
		zones = new Zone [3][3];
		
		for (int i = 0; i < zones.length; i++) {
			for (int j = 0; j < zones[i].length; j++) {
				zones[i][j] = new Zone(myGame.manager.get("ZoneTest.png",Texture.class));
				zones[i][j].setBounds(400+(350*i), 100+(350*j), 350, 350);
				zones[i][j].x = i;
				zones[i][j].y = j;
			}
		}
		
		
		winner = new Label("", new LabelStyle(new BitmapFont(), Color.BLACK));
		winner.setFontScale(5);
		winner.setX(800);
		winner.setY(1300);	
		
		playerOneScoreLabel = new Label(Integer.toString(playerOnePoints), new LabelStyle(new BitmapFont(), Color.BLACK));
		playerOneScoreLabel.setFontScale(5);
		playerOneScoreLabel.setX(600);
		playerOneScoreLabel.setY(1200);		
		myGame.stage.addActor(playerOneScoreLabel);
		
		playerTwoScoreLabel = new Label(Integer.toString(playerTwoPoints), new LabelStyle(new BitmapFont(), Color.BLACK));
		playerTwoScoreLabel.setFontScale(5);
		playerTwoScoreLabel.setX(1000);
		playerTwoScoreLabel.setY(1200);		
		myGame.stage.addActor(playerTwoScoreLabel);
		

		for (int i = 0; i < zones.length; i++) {
			for (int j = 0; j < zones[i].length; j++) {
				zones[i][j].addListener(new ZoneClickListener(zones[i][j]));
				myGame.stage.addActor(zones[i][j]);
			}
		}
		
		for (CardView card : handOne) {
			card.setBounds(50, 300*handOne.indexOf(card), 300, 300);
			card.addListener(new SelectCardListener(card));
			myGame.stage.addActor(card);
		}
		for (CardView card : handTwo) {
			card.setBounds(myGame.stage.getWidth()-400, 300*handTwo.indexOf(card), 300, 300);
			card.addListener(new SelectCardListener(card));
			myGame.stage.addActor(card);
		}
		
		setTurn();
		
		
		
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
	
	public void playCard(Zone zone) {
		
		if(selected != null && zone.isEmpty){			
			zone.playCard(selected);
			
			applyAllRules(zone);
			
			selected = null;
			turn++;
			setTurn();
			if(turn >= 9){
				endGame();
			}
			
		}
	}
	
	
	public void applyAllRules(Zone zone){
		
		for (FlipRule flipRule : flipRules) {
			flipRule.applyRule(zone, zones, this);
		}
		
	}
	
	
	
	private void endGame() {
		shittyArrow.remove();
		String winnerString;
		if(playerOnePoints > playerTwoPoints){
			winnerString = "Player One Won";
		}else{
			winnerString = "Player Two Won";
		}
		winner.setText(winnerString);
		myGame.stage.addActor(winner);
		TextButtonStyle textButtonStyle = myGame.skin.get(TextButtonStyle.class);	 
		Table buttonTable = new Table();
		TextButton backButton = new TextButton("Back",textButtonStyle);
		buttonTable.setFillParent(true);
		buttonTable.add(backButton).expand().bottom().right();
		myGame.stage.addActor(buttonTable);
		backButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				dispose();
				myGame.setScreen(new TestMenu(myGame));				
			}
			
		}
		);
	}


	private void countPoints() {
		int playerOne = 0, playerTwo = 0;
		
		for (CardView cardView : handOne) {
			if(cardView.getSide() == 1){
				playerOne++;
			}else{
				playerTwo++;
			}
		}
		
		for (CardView cardView : handTwo) {
			if(cardView.getSide() == 1){
				playerOne++;
			}else{
				playerTwo++;
			}
		}
		
		this.playerOnePoints = playerOne;
		this.playerTwoPoints = playerTwo;
		this.playerOneScoreLabel.setText(Integer.toString(playerOnePoints));
		this.playerTwoScoreLabel.setText(Integer.toString(playerTwoPoints));
	}


	private void setTurn() {
		countPoints();
		ArrayList<CardView> activeHand, notActiveHand;
		shittyArrow.rotateBy(180);
		if(turn % 2 == 0){
			shittyArrow.setX(shittyArrow.getX()+shittyArrow.getWidth());
			shittyArrow.setY(shittyArrow.getY()+shittyArrow.getHeight());
			activeHand = handOne;
			notActiveHand = handTwo;
		}else{
			shittyArrow.setX(shittyArrow.getX()-shittyArrow.getWidth());
			shittyArrow.setY(shittyArrow.getY()-shittyArrow.getHeight());
			activeHand = handTwo;
			notActiveHand = handOne;
		}
		
		for (CardView cardView : notActiveHand) {
			cardView.setTouchable(Touchable.disabled);
		}
		
		for (TurnRule rule : turnRules) {
			rule.applyRule(this);
		}
		
		for (CardView cardView : activeHand) {
			if(!cardView.isPlayed)
			cardView.setTouchable(Touchable.enabled);
		}
		
		
	}




	public class SelectCardListener extends InputListener {
		
		CardView card;
		
		public SelectCardListener(CardView card){			
			this.card = card;			
		}
		
		@Override
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
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
	
	public class ZoneClickListener extends InputListener{
		
		Zone zone;
		
		public ZoneClickListener(Zone zone){
			this.zone = zone;
		}
		
		@Override
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			
			playCard(zone);
			
	        return true;
	    }
		
	}
	
}
