package com.tt.game.engine.rules;

import com.tt.game.screens.GameScreen;
import com.tt.game.views.Zone;

public class BasicFlipRule implements FlipRule {

	GameScreen gameScreen;
	
	@Override
	public void applyRule(Zone zone, Zone[][] zones, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		if (zone.y < 2 && !zones[zone.x][zone.y + 1].isEmpty) {
			checkFlip(zone, zones[zone.x][zone.y + 1], 0, zones);
		}

		if (zone.x < 2 && !zones[zone.x + 1][zone.y].isEmpty) {
			checkFlip(zone, zones[zone.x + 1][zone.y], 1, zones);
		}

		if (zone.y > 0 && !zones[zone.x][zone.y - 1].isEmpty) {
			checkFlip(zone, zones[zone.x][zone.y - 1], 2, zones);
		}

		if (zone.x > 0 && !zones[zone.x - 1][zone.y].isEmpty) {
			checkFlip(zone, zones[zone.x - 1][zone.y], 3, zones);
		}
	}
	
	public void checkFlip(Zone flipper, Zone toFlip, int attackingSide, Zone[][] zones) {
		if (flipper.getCardSide() != toFlip.getCardSide() && flipper.getCardPowerOn(attackingSide) > toFlip.getCardPowerOn((attackingSide + 2) % 4)) {
			toFlip.flip();
			gameScreen.applyAllRules(toFlip);
		}
	}

}
