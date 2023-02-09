package project;

public class Player extends Living {
	// Player Attributes
	private int potionQty;

	// Constructor
	public Player() {
		super();
		potionQty = 1;
	}

	// Method to upgrade weapon
	public void upgradeWeapon() {
		weaponDamage += 5;
	}

	// potionQty getter method
	public int getPotionQty() {
		return potionQty;
	}

	// potionQty mutator method
	public void setPotionQty(int potionQty) {
		this.potionQty = potionQty;
	}

	// Method to use potion
	public boolean usePotion() {
		boolean success = true;
		// If there is no any potion left
		if (potionQty <= 0) {
			// usePotion failed, print message to tell user that he/she does not have potion
			success = false;
			System.out.println("You don't have any potions left!");
		}
		// If health of player is full
		else if (health == maxHealth) {
			// usePotion failed, print message to tell user that his/her hp is full
			success = false;
			System.out.println("Your HP is full!");
		} else {
			// Set player health to full hp and a potion is used
			setHealth(maxHealth);
			potionQty -= 1;
		}
		// Return whether the usePotion is successful
		return success;
	}

}