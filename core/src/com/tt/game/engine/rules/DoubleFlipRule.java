package com.tt.game.engine.rules;

import com.tt.game.views.Zone;

public class DoubleFlipRule implements FlipRule {

	@Override
	public void applyRule(Zone zone, Zone[][] zones) {
		
	}
	
	public void checkFlip(Zone flipper, Zone toFlip, int attackingSide, Zone[][] zones) {
		if (flipper.getCardSide() != toFlip.getCardSide() && flipper.getCardPowerOn(attackingSide) > toFlip.getCardPowerOn((attackingSide + 2) % 4)) {
			toFlip.flip();
			applyRule(toFlip, zones);
		}
	}

}
