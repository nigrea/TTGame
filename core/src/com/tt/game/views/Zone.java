package com.tt.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Zone extends Actor {

	Sprite zonePic;
	CardView placedCard;
	public int x, y;
	public boolean isEmpty = true;

	public Zone(Texture zoneImage) {

		zonePic = new Sprite(zoneImage);
		setBounds(0, 0, zonePic.getWidth(), zonePic.getHeight());

	}

	public Zone(int i, int j) {
		x = i;
		y = j;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//if(isEmpty){
			zonePic.setBounds(getX(), getY(), getWidth(), getHeight());
			zonePic.draw(batch);
		//}
		
	}

	public void playCard(CardView selected) {
		isEmpty = false;
		placedCard = selected;
		selected.setBounds(getX(), getY(), getWidth(), getHeight());
		
	}

	public int getCardPowerOn(int side) {
		return placedCard.getPowerOn(side);
	}

	public int getCardSide() {
		return placedCard.side;
	}

	public void flip() {
		if (!isEmpty) {
			placedCard.flip();
		}
	}

}
