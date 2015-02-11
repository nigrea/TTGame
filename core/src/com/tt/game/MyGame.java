package com.tt.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
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

	@Override
	public void create() {
		stage = new Stage(new FitViewport(1920, 1440));		
		batch = new SpriteBatch();
		font = new BitmapFont();
		manager = new AssetManager();
		setAssets();
		this.setScreen(new MenuScreen(this));
	}

	private void setAssets() {
		manager.load("sampleCard.png", Texture.class);
		manager.load("sampleCard2.jpg", Texture.class);
		manager.load("background.png", Texture.class);
		manager.load("background2.png", Texture.class);
		manager.load("ZoneTest.png", Texture.class);
		manager.load("shittyArrow.png", Texture.class);

	}

	@Override
	public void render() {
		super.render(); // important!

	}
}
