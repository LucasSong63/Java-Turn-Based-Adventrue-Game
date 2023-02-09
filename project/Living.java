package project;

public class Living {
	// Living Attributes
	protected int health;
	protected int maxHealth;
	protected int weaponDamage;
	protected int gold;

	// Constructor
	public Living() {
		health = 100;
		maxHealth = 100;
		weaponDamage = 10;
		gold = 50;
	}

	// Parameterized Constructor
	public Living(int health, int maxHealth, int weaponDamage, int gold) {
		this.health = health;
		this.maxHealth = maxHealth;
		this.weaponDamage = weaponDamage;
		this.gold = gold;
	}

	// health getter method
	public int getHealth() {
		return health;
	}

	// health mutator method
	public void setHealth(int health) {
		this.health = health;
	}

	// maxHealth getter method
	public int getMaxHealth() {
		return maxHealth;
	}

	// weaponDamage getter method
	public int getWeaponDamage() {
		return weaponDamage;
	}

	// gold getter method
	public int getGold() {
		return gold;
	}

	// gold mutator method
	public void setGold(int gold) {
		this.gold = gold;
	}

	// method to check if this Living is ALIVE
	public boolean isAlive() {
		return health > 0;
	}

	// attack method
	public void attack(Object o) {
		// Set the health of Living that this Living is attacking to decrease the health
		// of the Living being attacked
		((Living) o).setHealth(((Living) o).getHealth() - weaponDamage);
	}

}
