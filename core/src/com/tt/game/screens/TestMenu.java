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
	Skin skin;
	Stage stage;

 
	final MyGame myGame;
	
	public TestMenu(MyGame myGame){
		this.myGame = myGame;
		myGame.music.play();
		create();
	}
 
	public void create(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage); //Receives touch and input events
		skin = new Skin();
		Pixmap pixmap = new Pixmap(200, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		BitmapFont bitfont=new BitmapFont();
		bitfont.scale(1);
		skin.add("default",bitfont);
 
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
 
		skin.add("default", textButtonStyle);
 
		final TextButton textButton=new TextButton("Play",textButtonStyle);
		final TextButton textButtonTwo=new TextButton("Card Select",textButtonStyle);
		textButton.setPosition(412, 434);
		textButtonTwo.setPosition(412, 234);
		
		Table table = new Table();
		table.add(textButton).pad(50);
		table.row();
		table.add(textButtonTwo).pad(50);
		//table.debug();    //Uncomment for Debug lines, helpful for sizing widgets
		table.setFillParent(true);
		stage.addActor(table);
 
		textButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				myGame.setScreen(new GameScreen(myGame));
				
			}
			
		}
		);
		textButtonTwo.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				myGame.setScreen(new SelectCardsScreen(myGame));
				
			}
			
		}
		);
	}
 
	public void render (float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();

	}
 
	@Override
	public void resize (int width, int height) {

	}
 
	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
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