package org.empathy.soulsplit.woodcutting;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Game;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Players;
import org.soulsplit.api.methods.SceneObjects;
import org.soulsplit.api.wrappers.SceneObject;

/**
 * A class that chops wood for EWoodcutter.
 * @author Masood
 * 
 */
public class Chop implements Strategy {
	
	private Timer treeTime = new Timer();
	
	@Override
	public boolean activate() {
		SceneObject[] tree = SceneObjects.getNearest(EWoodcutter.treeData.getTreeId());
		return tree.length > 0 && tree[0] != null && Players.getMyPlayer().getAnimation() == -1 &&  !EWoodcutter.brokeHatchet && !Inventory.isFull() && Walking.startChop;

	}

	@Override
	public void execute() {

		EWoodcutter.status = "Chopping...";

		System.out.println("Chopping " + EWoodcutter.treeData.getName() + " logs...");

		final SceneObject[] tree = SceneObjects.getNearest(EWoodcutter.treeData.getTreeId());

		if (tree[0] != null && tree.length > 0 && tree[0].distanceTo() < 12) {

			try {

				tree[0].interact(0);
				
				treeTime.restart();
				
			} catch (ArrayIndexOutOfBoundsException e) {

				e.printStackTrace();
			}
			Time.sleep(new SleepCondition() {

				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getAnimation() == 867;
				}
			}, 30000);
			
			//Must do it like this cause Soulsplit is actually Soulshit. Animation changes to -1 mid chop.
			while (treeTime.getElapsedTime() < 1500) {
				if (Players.getMyPlayer().getAnimation() == 867) {
					
					treeTime.restart();
				}
				
				if (Players.getMyPlayer().getAnimation() == 836 || !Game.isLoggedIn() || EWoodcutter.brokeHatchet) {
					break;
				}
			}
			if (EWoodcutter.brokeHatchet) {
				System.out.println("Hatchet broke :(");
			}
		}
	}

}
