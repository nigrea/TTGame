package com.tt.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tt.game.MyGame;
import com.tt.game.views.CardView;
import com.tt.game.views.Zone;

public class GameScreen implements Screen {

	private CardView selected;
	private ArrayList<CardView> handOne, handTwo;
	private Zone[][] zones;
	private MyGame myGame;
	
	//Delete
	//private Zone zone;
	
	
	public GameScreen(MyGame myGame) {
		this.myGame = myGame;
		Gdx.input.setInputProcessor(myGame.stage);
		myGame.stage.addActor(new Image(myGame.manager.get("background2.png", Texture.class)));
		handOne = new ArrayList<CardView>();
		handTwo = new ArrayList<CardView>();
		zones = new Zone [3][3];
		
		for (int i = 0; i < zones.length; i++) {
			for (int j = 0; j < zones[i].length; j++) {
				System.out.println("["+i+"] ["+j+"]");
				zones[i][j] = new Zone(myGame.manager.get("ZoneTest.png",Texture.class));
				zones[i][j].setBounds(400+(350*i), 100+(350*j), 350, 350);
				zones[i][j].x = i;
				zones[i][j].y = j;
			}
		}
		

		//For testing		
		int[] tempPowerArray = {1,2,3,4};
		
		for (int i = 0; i < 5; i++) {
			handOne.add(new CardView(1, 1, tempPowerArray, "Derpy", myGame.manager.get("sampleCard2.jpg", Texture.class)));
			handTwo.add(new CardView(1, 2, tempPowerArray, "Derpy", myGame.manager.get("sampleCard2.jpg", Texture.class)));
		}

		for (CardView card : handOne) {
			card.setBounds(50, 50+(300*handOne.indexOf(card)), 300, 300);
		}
		for (CardView card : handTwo) {
			card.setBounds(myGame.stage.getWidth()-450, 50+(300*handTwo.indexOf(card)), 300, 300);
		}
	}


	@Override
	public void show() {
		for (CardView cardView : handOne) {
			cardView.addListener(new SelectCardListener(cardView));
			myGame.stage.addActor(cardView);
		}
		for (CardView cardView : handTwo) {
			cardView.addListener(new SelectCardListener(cardView));
			myGame.stage.addActor(cardView);
		}
		for (int i = 0; i < zones.length; i++) {
			for (int j = 0; j < zones[i].length; j++) {
				zones[i][j].addListener(new ZoneClickListener(zones[i][j]));
				myGame.stage.addActor(zones[i][j]);
			}
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
			selected.remove();
			doFlips(zone);
			selected = null;					
			
		}
	}

	public void doFlips(Zone zone) {
		if (zone.y < 2 && !zones[zone.x][zone.y + 1].isEmpty) {
			checkFlip(zone, zones[zone.x][zone.y + 1], 0);
		}

		if (zone.x < 2 && !zones[zone.x + 1][zone.y].isEmpty) {
			checkFlip(zone, zones[zone.x + 1][zone.y], 1);
		}

		if (zone.y > 0 && !zones[zone.x][zone.y - 1].isEmpty) {
			checkFlip(zone, zones[zone.x][zone.y - 1], 2);
		}

		if (zone.x > 0 && !zones[zone.x - 1][zone.y].isEmpty) {
			checkFlip(zone, zones[zone.x - 1][zone.y], 3);
		}

	}
	
	public void checkFlip(Zone flipper, Zone toFlip, int attackingSide) {
		if (flipper.getCardSide() != toFlip.getCardSide() && flipper.getCardPowerOn(attackingSide) > toFlip.getCardPowerOn((attackingSide + 2) % 4)) {
			toFlip.flip();
			doFlips(toFlip);
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
