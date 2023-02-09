package project;

public class Enemy extends Living {
	// Enemy Attributes
	private String name;

	// Constructor
	public Enemy() {
		super();
		name = "EnemyName";
	}

	// Parmeterized Constructor
	public Enemy(String name, int health, int maxHealth, int weaponDamage, int gold) {
		super(health, maxHealth, weaponDamage, gold);
		this.name = name;
	}

	// Getter Method
	public String getName() {
		return name;
	}

}
