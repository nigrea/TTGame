package com.tt.game.engine.rules;

import com.tt.game.screens.GameScreen;
import com.tt.game.views.Zone;

public interface FlipRule {
	
	public void applyRule(Zone zone, Zone[][] zones, GameScreen gameScreen);
	
	
}
