package org.empathy.soulsplit.woodcutting;

import org.empathy.soulsplit.utils.Utils;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Inventory;


/**
 * A class that drops logs for EWoodcutter.
 * @author Masood
 * 
 */
public class Drop implements Strategy {
	
	public static boolean powerCut;
	
	@Override
	public boolean activate() {
		return Inventory.isFull() && powerCut;

	}

	@Override
	public void execute() {
		
		EWoodcutter.status = "Dropping " + EWoodcutter.treeData.getName() + " logs...";
		
		EWoodcutter.time.setCurrentAmount(Inventory.getCount(true, EWoodcutter.treeData.getLogId()));
		
		Utils.dropAllExcept(Constants.AXES);
		
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return Inventory.isEmpty();
			}
		}, 5000);
	}
	
}
