package org.empathy.soulsplit.woodcutting;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Players;
import org.soulsplit.api.wrappers.Tile;

/**
 * A class that banks on org.soulsplit.
 * @author Masood
 * 
 */
public class Banking implements Strategy {

	@Override
	public boolean activate() {
		return Inventory.isFull() && !Drop.powerCut;

	}

	@Override
	public void execute() {
		EWoodcutter.status = "Banking...";
		System.out.println("Banking " + EWoodcutter.treeData.getName() + " logs...");
		if (!Players.getMyPlayer().getLocation().equals(new Tile(3161, 3487)) && !Bank.isOpen()) {
			Keyboard.getInstance().sendKeys("::market");
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getLocation().equals(new Tile(3161, 3487));
				}
			}, 5000);
		}
		
		if (!Bank.isOpen()) {
			Utils.bankDesk();
		}
		
		if (Bank.isOpen()) {
			EWoodcutter.time.setCurrentAmount(Inventory.getCount(true, EWoodcutter.treeData.getLogId()));
			Utils.depositAll();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Inventory.isEmpty();
				}
			}, 5000);
		}
		
		if (Inventory.isEmpty()) {
			Bank.close();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return !Bank.isOpen();
				}
			}, 5000);
			Walking.teled = false;
			Walking.startChop = false;
		}
		
	}
}
