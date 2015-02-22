package com.tt.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tt.game.screens.MenuScreen;

public class MyGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public AssetManager manager;
	public Stage stage;
	public Music music;
	public ArrayList<Card> cards;	
	
	@Override
	public void create() {
		stage = new Stage(new FitViewport(1920, 1440));		
		batch = new SpriteBatch();
		font = new BitmapFont();
		manager = new AssetManager();
		setAssets();
		this.setScreen(new MenuScreen(this));
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
