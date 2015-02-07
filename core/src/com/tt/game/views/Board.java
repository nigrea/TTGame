package com.tt.game.views;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Board extends Actor {
	
	Zone[][] zones = new Zone[3][3];
	
	public Board() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				zones[i][j] = new Zone(i,j);				
			}
		}
		
	}
	

}
