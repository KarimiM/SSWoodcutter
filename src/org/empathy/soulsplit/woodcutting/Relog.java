package org.empathy.soulsplit.woodcutting;

import java.awt.event.KeyEvent;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Game;


/**
 * A class that relogs for Soulsplit.
 * @author Masood
 * 
 */
public class Relog implements Strategy {

	@Override
	public boolean activate() {
		return !Game.isLoggedIn();

	}

	@Override
	public void execute() {
		
		EWoodcutter.status = "Relogging...";
		
		Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
		Time.sleep(500);
		Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return Game.isLoggedIn();
			}
		}, 5000);
		Time.sleep(1000);
		Walking.teled = false;
		Walking.startChop = false;
	}
	
}
