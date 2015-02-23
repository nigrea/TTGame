package com.tt.game.screens;

import java.util.ArrayList;

import com.tt.game.MyGame;
import com.tt.game.engine.ai.AI;
import com.tt.game.views.CardView;

public class AIGameScreen extends GameScreen {

	AI ai;

	public AIGameScreen(MyGame myGame, ArrayList<CardView> handOne,
			ArrayList<CardView> handTwo, AI ai) {
		super(myGame, handOne, handTwo);
		this.ai = ai;
	}

	@Override
	protected void setTurn() {
		super.setTurn();
		if (turn % 2 == 1) {
			ai.takeTurn(this);
		}
	}

}
