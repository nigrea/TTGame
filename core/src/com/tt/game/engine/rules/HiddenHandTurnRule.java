package com.tt.game.engine.rules;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.tt.game.screens.GameScreen;
import com.tt.game.views.CardView;

public class HiddenHandTurnRule implements TurnRule {

	@Override
	public void applyRule(GameScreen gameScreen) {
		
		
		if(gameScreen.turn % 2 == 0){
			for (CardView card : gameScreen.handTwo) {
				if(!card.isPlayed){
					card.setVisible(false);
				}
			}
		}else{
			for (CardView card : gameScreen.handOne) {
				if(!card.isPlayed){
					card.setVisible(false);
				}
			}
		}
		
		Dialog dialog = new Dialog("Next Player", gameScreen.myGame.skin);
		
		dialog.setBounds(gameScreen.myGame.stage.getWidth()/2 - 250, gameScreen.myGame.stage.getHeight() - 300, 500, 250);
		
		TextButton textButton=new TextButton("Play",gameScreen.myGame.skin.get(TextButtonStyle.class));
		
		textButton.addListener(new ConfirmButtonListener(gameScreen));		
		
		dialog.button(textButton);
		
		gameScreen.myGame.stage.addActor(dialog);
		
	}
	
	private class ConfirmButtonListener extends InputListener{
		
		GameScreen gameScreen;
		
		public ConfirmButtonListener(GameScreen gameScreen){
			this.gameScreen = gameScreen;			
		}

		@Override
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			
			if(gameScreen.turn % 2 == 0){
				for (CardView card : gameScreen.handOne) {
					if(!card.isPlayed){
						card.setVisible(true);
					}
				}
			}else{
				for (CardView card : gameScreen.handTwo) {
					if(!card.isPlayed){
						card.setVisible(true);
					}
				}
			}
			
			return false;
		}
		
		
	}

}
