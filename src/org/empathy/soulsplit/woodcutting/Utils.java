package org.empathy.soulsplit.woodcutting;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.methods.SceneObjects;
import org.soulsplit.api.wrappers.Item;
import org.soulsplit.api.wrappers.SceneObject;

/**
 * A utility class containing useful methods.
 * @author Masood
 *
 */
public class Utils {

	/**
	 * Deposits all items except the listed ids.
	 * @param itemIDs the item ids to not deposit.
	 */
	public static void dropAllExcept(int... itemIDs) {
		final ArrayList<Integer> dontDrop = new ArrayList<>();
		if (Inventory.getCount(false) <= 2) {
			return;
		} else {
			for (int i : itemIDs) {
				dontDrop.add(i);
			}
		}
		for (Item inventoryItem : Inventory.getItems()) {
			if (!dontDrop.contains(inventoryItem.getId())) {
				Menu.sendAction(847, (int) (inventoryItem.getId() - 1), inventoryItem.getSlot(), 3214);
				Time.sleep(80);
			}
		}
	}


	public static void depositAll() {
		Menu.sendAction(646, -1, -1, 5386);
		Time.sleep(80);
	}

	public static void clickItem(int... id) {
		for (Item i : Inventory.getItems(id)) {
			Menu.sendAction(454, (int) (i.getId() - 1), i.getSlot(), 3214);
			Time.sleep(500);
		}
	}

	public static boolean contains(int id) {
		return Inventory.getCount(id) > 0;
	}

	public static boolean contains(long...ids) {
		return Inventory.getCount(ids) > 0;

	}

	public static void combines(int idOne, int...idTwo) {
		for (Item i : Inventory.getItems(idOne)) {
			for (Item j : Inventory.getItems(idTwo)) {
				if (i != null) {
					if (j != null) {
						Menu.sendAction(447, (int) (i.getId() - 1), i.getSlot(), 3214);
						Time.sleep(500);
						Menu.sendAction(870, (int) (j.getId() - 1), j.getSlot(), 3214);
						Time.sleep(500);
					}
				}
			}
		}
	}

	public static void bankDesk() {

		SceneObject[] desk = SceneObjects.getNearest(Constants.BANK);

		try {
			if (desk[0] != null && desk.length > 0 && !Bank.isOpen()) {

				desk[0].interact(0);

				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Bank.isOpen();
					}
				}, 5000);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Formats a number as a string.
	 * @param number the number to format.
	 * @return the string formatted.
	 */
	public static String formatNumb(int number) {
		String numberString = String.valueOf(number);
		if (Integer.parseInt(numberString) < 1000) {
			return numberString;
		} else if (Integer.parseInt(numberString) > 1000 && Integer.parseInt(numberString) < 10000) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "k";
		} else if (Integer.parseInt(numberString) > 10000 && Integer.parseInt(numberString) < 100000) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "k";
		} else if (Integer.parseInt(numberString) > 100000 && Integer.parseInt(numberString) < 1000000) {
			return numberString.substring(0, 3) + "." + numberString.charAt(3) + "k";
		} else if (Integer.parseInt(numberString) > 1000000 && Integer.parseInt(numberString) < 10000000) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "m";
		} else if (Integer.parseInt(numberString) > 10000000 && Integer.parseInt(numberString) < 100000000) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "m";
		} else if (Integer.parseInt(numberString) > 100000000 && Integer.parseInt(numberString) < 1000000000) {
			return numberString.substring(0, 3) + "." + numberString.charAt(3) + "m";
		} else if (Long.valueOf(numberString) > 1000000000L && Long.valueOf(numberString) < 10000000000L) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "b";
		} else if (Long.valueOf(numberString) > 10000000000L && Long.valueOf(numberString) < 100000000000L) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "b";
		} else {
			return numberString;
		}
	}
}
