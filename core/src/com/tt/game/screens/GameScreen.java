package com.tt.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.tt.game.MyGame;
import com.tt.game.views.CardView;
import com.tt.game.views.Zone;
import com.tt.game.engine.rules.FlipRule;

public class GameScreen implements Screen {

	private CardView selected;
	private ArrayList<CardView> handOne, handTwo;
	private Zone[][] zones;
	private ArrayList<FlipRule> flipRules;
	private MyGame myGame;
	private Image shittyArrow; //TODO REMAKE!!
	private int turn = 0, playerOnePoints = 0, playerTwoPoints = 0;
	private Label playerOneScoreLabel, playerTwoScoreLabel, winner;
	
	//Delete
	//private Zone zone;
	
	
	public GameScreen(MyGame myGame, ArrayList<CardView> handOne, ArrayList<CardView> handTwo, ArrayList<FlipRule> flipRules) {
		this.myGame = myGame;
		myGame.music.play();
		Gdx.input.setInputProcessor(myGame.stage);
		myGame.stage.addActor(new Image(myGame.manager.get("background2.png", Texture.class)));
		shittyArrow = new Image(myGame.manager.get("shittyArrow.png", Texture.class));
		shittyArrow.setX(800);
		shittyArrow.setY(1200);
		myGame.stage.addActor(shittyArrow);
		
		
		this.handOne = handOne;
		this.handTwo = handTwo;
		this.flipRules = flipRules;
		
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
		

		for (CardView card : handOne) {
			card.setBounds(50, 300*handOne.indexOf(card), 300, 300);			
		}
		for (CardView card : handTwo) {
			card.setBounds(myGame.stage.getWidth()-400, 300*handTwo.indexOf(card), 300, 300);
		}
		
		setTurn();
		
	}


	@Override
	public void show() {
		
		for (int i = 0; i < zones.length; i++) {
			for (int j = 0; j < zones[i].length; j++) {
				zones[i][j].addListener(new ZoneClickListener(zones[i][j]));
				myGame.stage.addActor(zones[i][j]);
			}
		}
		for (CardView cardView : handOne) {			
			cardView.addListener(new SelectCardListener(cardView));
			myGame.stage.addActor(cardView);
		}
		for (CardView cardView : handTwo) {
			cardView.addListener(new SelectCardListener(cardView));
			myGame.stage.addActor(cardView);
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
