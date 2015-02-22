package com.tt.game.screens;

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
import com.tt.game.MyGame;

 
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

		Table table = new Table();
		table.add(textButton).pad(50);
		table.row();
		table.add(textButtonTwo).pad(50);
		//table.debug();    //Uncomment for Debug lines, helpful for sizing widgets
		table.setFillParent(true);
		myGame.stage.addActor(table);
 
		textButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				dispose();
				myGame.setScreen(new GameScreen(myGame));				
			}
			
		}
		);
		textButtonTwo.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {	
				dispose();
				myGame.setScreen(new SelectCardsScreen(myGame));
			}
			
		}
		);
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
}