package org.empathy.soulsplit.utils;

import org.parabot.environment.api.utils.Timer;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Skill;

/**
 * A wrapper class used to time a skill.
 * @author Empathy
 *
 */
public class ScriptTimer extends Timer {

	/**
	 * The skill to time.
	 */
	private final Skill skill;

	/**
	 * The start exp.
	 */
	private final double startExp;

	/**
	 * The current amount.
	 */
	private int currentAmount;

	/**
	 * The start amount.
	 */
	private int startAmount;
	
	/**
	 * The product.
	 */
	private final int product;

	/**
	 * Constructs a new script timer.
	 * @param skill the skill to time.
	 * @Param expPerProduct the exp per product.
	 */
	public ScriptTimer(Skill skill, int productId) {
		this.skill = skill;
		this.product = productId;
		this.startExp = skill.getExperience();
		this.startAmount = Inventory.getCount(true, productId);
	}
	
	/**
	 * @return the gained amount
	 */
	public int getGainedAmount() {
		return getCurrentAmount() + getInventoryAmount() - startAmount;
	}
	
	/**
	 * @return the inventory amount
	 */
	public int getInventoryAmount() {
		return Inventory.getCount(true, product);
	}
	
	/**
	 * Sets the currentAmount
	 * @param currentAmount
	 */
	public void setCurrentAmount(int currentAmount) {
		this.currentAmount += currentAmount;
	}
	/**
	 * @return the current amount.
	 */
	public int getCurrentAmount() {
		return currentAmount;
	}
	
	/**
	 * Gets the exp gained over time.
	 * @return the exp gained.
	 */
	public int getXpGained() {
		return (int) (skill.getExperience() - startExp);
	}
	
	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * @return the startExp
	 */
	public double getStartExp() {
		return startExp;
	}

}
