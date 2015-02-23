package com.tt.game.engine.ai;

import java.util.ArrayList;
import java.util.Random;

import com.tt.game.screens.GameScreen;
import com.tt.game.views.CardView;
import com.tt.game.views.Zone;

public class BasicAI implements AI {

	@Override
	public void takeTurn(GameScreen gameScreen) {
		ArrayList<Zone> emptyZones = new ArrayList<Zone>();
		ArrayList<CardView> cardsInHand = new ArrayList<CardView>();

		for (int i = 0; i < gameScreen.zones.length; i++) {
			for (int j = 0; j < gameScreen.zones[i].length; j++) {
				if (gameScreen.zones[i][j].isEmpty) {
					emptyZones.add(gameScreen.zones[i][j]);
				}
			}
		}

		for (CardView cardView : gameScreen.handTwo) {
			if (!cardView.isPlayed) {
				cardsInHand.add(cardView);
			}
		}

		Random randomGenerator = new Random();

		if (cardsInHand.size() > 0 && emptyZones.size() > 0) {
			gameScreen.selected = cardsInHand.get(randomGenerator
					.nextInt(cardsInHand.size()));
			gameScreen.playCard(emptyZones.get(randomGenerator
					.nextInt(emptyZones.size())));
		}

	}

}
