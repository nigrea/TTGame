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
		zonePic.setBounds(getX(), getY(), getWidth(), getHeight());
		zonePic.draw(batch);
		if (!isEmpty) {
			placedCard.labels[0].setBounds(getX() + 50, getY() + 70, 100, 100);
			placedCard.labels[0].draw(batch, parentAlpha);
			placedCard.labels[1].setBounds(getX() + 80, getY() + 40, 100, 100);
			placedCard.labels[1].draw(batch, parentAlpha);
			placedCard.labels[2].setBounds(getX() + 50, getY() + 10, 100, 100);
			placedCard.labels[2].draw(batch, parentAlpha);
			placedCard.labels[3].setBounds(getX() + 20, getY() + 40, 100, 100);
			placedCard.labels[3].draw(batch, parentAlpha);
		}
	}

	public void playCard(CardView selected) {
		isEmpty = false;
		placedCard = selected;
		zonePic = selected.picture;
		
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
