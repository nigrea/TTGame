package com.tt.game.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.tt.game.MyGame;
import com.tt.game.engine.ai.BasicAI;
import com.tt.game.views.CardView;

 
public class TestMenu implements Screen {
	
	//Skin skin;
 
	MyGame myGame;
	
	public TestMenu(MyGame myGame){
		
		this.myGame = myGame;
		myGame.music.play();
		create();
	}
 
	public void create(){
		
		Gdx.input.setInputProcessor(myGame.stage); //Receives touch and input events
		
		TextButtonStyle textButtonStyle = myGame.skin.get(TextButtonStyle.class);
		
		final TextButton textButton=new TextButton("Play",textButtonStyle);
		final TextButton textButtonTwo=new TextButton("Card Select",textButtonStyle);
		final TextButton textButtonThree=new TextButton("AI-Game",textButtonStyle);
		ArrayList<CardView> handOne = new ArrayList<CardView>();
		ArrayList<CardView> handTwo = new ArrayList<CardView>();
		
		Table table = new Table();
		table.add(textButton).pad(50);
		table.row();
		table.add(textButtonTwo).pad(50);
		table.row();
		table.add(textButtonThree).pad(50);
		//table.debug();    //Uncomment for Debug lines, helpful for sizing widgets
		table.setFillParent(true);
		myGame.stage.addActor(table);
 
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
		
		textButton.addListener(new StartGameListener(myGame, handOne, handTwo));
		
		textButtonTwo.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {	
				dispose();
				myGame.setScreen(new SelectCardsScreen(myGame));
			}
			
		}
		);
		
		textButtonThree.addListener(new StartAIGameListener(myGame, handOne, handTwo));
		
	}
 
	public void render (float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		myGame.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		myGame.stage.draw();

	}
 
	@Override
	public void resize (int width, int height) {

	}
 
	@Override
	public void dispose () {
		myGame.stage.clear();		
	}
 
	@Override
	public void show() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void hide() {
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
	
	public class StartGameListener extends ChangeListener{

		MyGame myGame;
		ArrayList<CardView> handOne;
		ArrayList<CardView> handTwo;
		
		public StartGameListener(MyGame myGame, ArrayList<CardView> handOne, ArrayList<CardView> handTwo){
			this.myGame = myGame;
			this.handOne = handOne;
			this.handTwo = handTwo;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			myGame.stage.clear();
			myGame.setScreen(new GameScreen(myGame, handOne, handTwo));
		}	
	}
	
	public class StartAIGameListener extends ChangeListener{

		MyGame myGame;
		ArrayList<CardView> handOne;
		ArrayList<CardView> handTwo;
		
		public StartAIGameListener(MyGame myGame, ArrayList<CardView> handOne, ArrayList<CardView> handTwo){
			this.myGame = myGame;
			this.handOne = handOne;
			this.handTwo = handTwo;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			myGame.stage.clear();
			myGame.setScreen(new AIGameScreen(myGame, handOne, handTwo, new BasicAI()));
		}	
	}
}