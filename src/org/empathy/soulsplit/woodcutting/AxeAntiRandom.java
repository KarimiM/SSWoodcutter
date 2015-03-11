package org.empathy.soulsplit.woodcutting;

import org.empathy.soulsplit.utils.Utils;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.GroundItems;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.wrappers.GroundItem;

/**
 * A woodcutting anti macro class for EWoodcutter.
 * @author Masood
 * 
 */
public class AxeAntiRandom implements Strategy {

	@Override
	public boolean activate() {

		GroundItem[] toPickup = GroundItems.getNearest(Constants.AXE_HEADS);
		return (toPickup.length > 0 && toPickup[0] != null && toPickup[0].distanceTo() < 7) || Inventory.getCount(Constants.AXE_HEAD) > 0;
	}

	@Override
	public void execute() {

		EWoodcutter.status = "Completing Random...";
		System.out.println("Completing Woodcutting Random...");
		
		final GroundItem[] toPickup = GroundItems.getNearest(Constants.AXE_HEADS);

		if (toPickup.length > 0 && !Inventory.isFull() && toPickup[0] != null) {
			System.out.println("Picking up axe head");
			Menu.sendAction(234, toPickup[0].getId(), toPickup[0].getX(), toPickup[0].getY());
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return toPickup[0] == null;
				}
			}, 1500);
		}

		if (Inventory.getCount(Constants.AXE_HEAD) > 0) {
			
			if (Inventory.getCount(Constants.AXE_HANDLE) < 1) {
				Menu.sendAction(632, Constants.AXE_HANDLE - 1, 3, 1688);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.getCount(Constants.AXE_HANDLE) > 0;
					}
				}, 5000);
			}

			if (Inventory.getCount(Constants.AXE_HANDLE) > 0) {
				Utils.combines(Constants.AXE_HANDLE, Constants.AXE_HEAD_ONE);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.getCount(Constants.AXE) > 0;
					}
				}, 5000);
			}
		}
		
		if (Inventory.getCount(Constants.AXE) > 0) {
			Utils.clickItem(Constants.AXES);
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Inventory.getCount(Constants.AXE) < 1;
				}
			}, 5000);
			EWoodcutter.brokeHatchet = false;
		}
	}
}
