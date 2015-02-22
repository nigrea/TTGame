package com.tt.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tt.game.screens.LoadingScreen;


public class MyGame extends Game {
	public Skin skin;
	public SpriteBatch batch;
	public BitmapFont font;
	public AssetManager manager;
	public Stage stage;
	public Music music;
	public ArrayList<Card> cards;	
	
	@Override
	public void create() {
		
		
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
		
		WindowStyle windowStyle = new WindowStyle();
		windowStyle.titleFont = skin.getFont("default");
		windowStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
		skin.add("default", windowStyle);
		
		stage = new Stage(new FitViewport(1920, 1440));		
		batch = new SpriteBatch();
		font = new BitmapFont();
		manager = new AssetManager();
		setAssets();
		this.setScreen(new LoadingScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setVolume(0.5f);                 
		music.setLooping(true);                
				
		
	}

	private void setAssets() {
		//find a better way!!
		manager.load("sampleCard.png", Texture.class);
		manager.load("cardArt/1.jpg", Texture.class);
		manager.load("cardArt/2.jpg", Texture.class);
		manager.load("cardArt/3.jpg", Texture.class);
		manager.load("cardArt/default.jpg", Texture.class);
		manager.load("background.png", Texture.class);
		manager.load("background2.png", Texture.class);
		manager.load("ZoneTest.png", Texture.class);
		manager.load("shittyArrow.png", Texture.class);
		manager.load("flip.mp3", Sound.class);

	}

	@Override
	public void render() {
		super.render(); // important!

	}
}
