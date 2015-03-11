package org.empathy.soulsplit.woodcutting;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Game;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.methods.Players;
import org.soulsplit.api.wrappers.Tile;
import org.soulsplit.api.wrappers.TilePath;

/**
 * A class that walks to trees for EWoodcutter.
 * @author Masood
 * 
 */
public class Walking implements Strategy {
	
	public static TilePath treePath;
	
	public static boolean teled;
	
	public static boolean startChop;
	
	private boolean setPath;
	
	
	@Override
	public boolean activate() {
		return !Inventory.isFull() && !teled && Inventory.getCount(Constants.AXE_HEAD) < 1;
	}

	@Override
	public void execute() {
		EWoodcutter.status = "Walking...";
		
		System.out.println("Getting to " + EWoodcutter.treeData.getName() + " trees...");
		
		if (EWoodcutter.treeData.getName().contains("maple")) {

			if (Game.getOpenBackDialogId() != 2492) {
				Menu.sendAction(315, -1, -1, 1164);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() == 2492;
					}
				}, 5000);
			}

			if (Game.getOpenBackDialogId() == 2492) {
				Menu.sendAction(315, -1, -1, 2498);
				// static sleep because back dialog remains the same
				Time.sleep(500);
				Menu.sendAction(315, -1, -1, 2496);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Players.getMyPlayer().getLocation().equals(new Tile(2757, 3477));
					}
				}, 5000);
				Time.sleep(1000);
				teled = true;
			}

		} else if (EWoodcutter.treeData.getName().contains("magic")) {
			
			if (Game.getOpenBackDialogId() != 2492) {
				Menu.sendAction(315, -1, -1, 1541);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() == 2492;
					}
				}, 5000);
			}
			if (Game.getOpenBackDialogId() == 2492) {
				Menu.sendAction(315, -1, -1, 2497);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Players.getMyPlayer().getLocation().equals(new Tile(2316, 3834));
					}
				}, 5000);
				Time.sleep(1000);
				teled = true;
			}
			
		} else {

				if (Game.getOpenBackDialogId() != 2492) {
					Menu.sendAction(315, -1, -1, 1541);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Game.getOpenBackDialogId() == 2492;
						}
					}, 5000);
				}

				if (Game.getOpenBackDialogId() == 2492) {
					Menu.sendAction(315, -1, -1, 2498);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Game.getOpenBackDialogId() == 2469;
						}
					}, 5000);
				}
				
				if (Game.getOpenBackDialogId() == 2469) {
					Menu.sendAction(315, -1, -1, 2471);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Players.getMyPlayer().getLocation().equals(new Tile(2804, 3432));
						}
					}, 5000);
					Time.sleep(1000);
					teled = true;
			}
		}

		if (teled = true) {
			
			if (!setPath) {
				treePath = new TilePath(EWoodcutter.treeData.getWalkTiles());
				setPath = true;
			}
			
			try {
				while (!treePath.hasReached()) {
					treePath.traverse();
					if (Players.getMyPlayer().getAnimation() == 836 || !Game.isLoggedIn()) {
						break;
					}
					Time.sleep(2500);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			startChop = true;
		}
	}
}
