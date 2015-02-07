package com.tt.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class CardView extends Actor{
	
	public Sprite picture;
	int id, player, x, y, side;
	int[] power;
	String name;
	public Label[] labels;
	
	public CardView(int id, int player, int[]power, String name ,Texture picture){
		this.picture = new Sprite(picture);
		this.id = id;
		this.player = player;
		this.side = player;
		this.power = power;
		this.name = name;
		this.labels = new Label[4];
		
		int counter = 0;
		for (int i : power) {
			String label = Integer.toString(power[counter]);
			System.out.println(label + " : " + power[counter]);
			labels[counter] = new Label(label, new LabelStyle(new BitmapFont(), Color.BLACK));
			labels[counter].setFontScale(4);
			counter++;
		}
		
		
		
		if(player == 2){
			this.picture.flip(true, false);
		}
		
		setBounds(0, 0, picture.getWidth(), picture.getHeight());
		
	}
	
	public void isPlayed(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getPowerOn(int side){
		return power[side];
	}
	

	@Override
    public void draw (Batch batch, float parentAlpha) {
		picture.setBounds(getX(), getY(), getWidth(), getHeight());
		picture.draw(batch);
		
		labels[0].setBounds(getX()+50, getY()+70, 100, 100);
		labels[0].draw(batch, parentAlpha);
		labels[1].setBounds(getX()+80, getY()+40, 100, 100);
		labels[1].draw(batch, parentAlpha);
		labels[2].setBounds(getX()+50, getY()+10, 100, 100);
		labels[2].draw(batch, parentAlpha);
		labels[3].setBounds(getX()+20, getY()+40, 100, 100);
		labels[3].draw(batch, parentAlpha);
    }

	public void wasSelected() {
		//For testing:
		setHeight(getHeight()+20);
		setWidth(getWidth()+20);
	}
	
	public void wasUnselected(){
		setHeight(getHeight()-20);
		setWidth(getWidth()-20);	
	}

	public void flip(){
		if(side == 1){
			side = 2;
		}else{
			side = 1;
		}
		this.picture.flip(true, false);
	}
	
	public int getSide(){
		return side;
	}
	
}
