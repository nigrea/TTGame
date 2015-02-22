package com.tt.game.views;

import static com.badlogic.gdx.scenes.scene2d.utils.Align.bottom;
import static com.badlogic.gdx.scenes.scene2d.utils.Align.left;
import static com.badlogic.gdx.scenes.scene2d.utils.Align.right;
import static com.badlogic.gdx.scenes.scene2d.utils.Align.top;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.tt.game.Card;
import com.tt.game.MyGame;

public class CardView extends Actor {

	public Sprite picture;
	int id, player, x, y, side;
	int[] power;
	String name;
	public Label[] labels;
	MyGame myGame;
	public boolean isPlayed; 

	public CardView(MyGame myGame, int id, int[] power, String name,
			Texture picture) {
		this.myGame = myGame;
		this.picture = new Sprite(picture);
		this.id = id;				
		this.power = power;
		this.name = name;
		initiate();
		
	}

	public CardView(MyGame myGame, Card card, Texture picture) {
		
		this(myGame, card.getId(), card.getPower(), card.getName(), picture);
		
	}
	
	private void initiate(){
		this.labels = new Label[4];
		isPlayed = false;

		int counter = 0;
		for (int i : power) {
			String label = Integer.toString(power[counter]);
			labels[counter] = new Label(label, new LabelStyle(new BitmapFont(),
					Color.BLACK));
			labels[counter].setFontScale(4);
			counter++;
		}

		if (player == 2) {
			this.picture.flip(true, false);
		}

		setBounds(0, 0, picture.getWidth(), picture.getHeight());
		this.picture.setBounds(getX(), getY(), getWidth(), getHeight());
	}
	
	public void setPlayer(int player){
		this.player = player;
		this.side = player;
	}

	public void isPlayed(int x, int y) {
		this.x = x;
		this.y = y;
		this.setTouchable(Touchable.disabled);
		isPlayed = true;
	}

	public int getPowerOn(int side) {
		return power[side];
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		picture.draw(batch);

		labels[0].draw(batch, parentAlpha);
		labels[1].draw(batch, parentAlpha);
		labels[2].draw(batch, parentAlpha);
		labels[3].draw(batch, parentAlpha);
	}

	public void wasSelected() {
		// For testing:
		setHeight(getHeight() + 20);
		setWidth(getWidth() + 20);
	}

	public void wasUnselected() {
		setHeight(getHeight() - 20);
		setWidth(getWidth() - 20);
	}

	public void flip() {
		if (side == 1) {
			side = 2;
		} else {
			side = 1;
		}

		flipAnimation();		
	}
	
	private void flipAnimation(){
		
		Sound sound = myGame.manager.get("flip.mp3", Sound.class);
		sound.play(1.0F);
		
		SizeToAction contractSize = new SizeToAction();
		contractSize.setSize(0, getHeight());
		contractSize.setDuration(0.2F);

		MoveToAction contractMove = new MoveToAction();
		contractMove.setPosition(getX() + (getWidth() / 2), getY());
		contractMove.setDuration(0.2F);

		SizeToAction expandSize = new SizeToAction();
		expandSize.setSize(getWidth(), getHeight());
		expandSize.setDuration(0.2F);

		MoveToAction expandMove = new MoveToAction();
		expandMove.setPosition(getX(), getY());
		expandMove.setDuration(0.2F);

		FlipAction flipAction = new FlipAction();
		flipAction.setDuration(0.01F);
		
		SequenceAction moveActions = new SequenceAction(contractMove,flipAction,expandMove);
		SequenceAction sizeActions = new SequenceAction(contractSize,expandSize);
		
		ParallelAction parallelAction = new ParallelAction(moveActions,sizeActions);
		
		this.addAction(parallelAction);
	}

	public int getSide() {
		return side;
	}

	private void updateLabels() {
		labels[0].setBounds(getX() + (getWidth() / 6), getY()
				+ (getHeight() / 4.3F), getWidth() / 3, getHeight() / 3);
		labels[1].setBounds(getX() + (getWidth() / 3.75F), getY()
				+ (getHeight() / 7.5F), getWidth() / 3, getHeight() / 3);
		labels[2].setBounds(getX() + (getWidth() / 6), getY()
				+ (getHeight() / 30), getWidth() / 3, getHeight() / 3);
		labels[3].setBounds(getX() + (getWidth() / 15), getY()
				+ (getHeight() / 7.5F), getWidth() / 3, getHeight() / 3);
	}

	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width, height);
		picture.setBounds(x, y, width, height);
		updateLabels();
	}

	@Override
	public void setX(float x) {
		super.setX(x);
		picture.setX(x);
		updateLabels();
	}

	@Override
	public void setY(float Y) {
		super.setY(y);
		picture.setY(y);
		updateLabels();
	}

	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		picture.setSize(width, picture.getHeight());
		updateLabels();
	}

	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		picture.setSize(picture.getWidth(), height);
		updateLabels();
	}

	@Override
	public void setScale(float scaleXY) {
		super.setScale(scaleXY);
		picture.setScale(scaleXY);
		updateLabels();
	}

	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);
		picture.setScale(scaleX, scaleY);
		updateLabels();
	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		picture.setSize(width, height);
		updateLabels();
	}

	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		picture.setPosition(x, y);
		updateLabels();
	}

	public class FlipAction extends TemporalAction {

		@Override
		protected void update(float percent) {
			picture.flip(true, false);
		}

	}

}
