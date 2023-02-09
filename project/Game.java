package project;

import java.util.Scanner;
import java.lang.Math;
import java.util.InputMismatchException;

class Game {
	// Game Attributes
	private Player player;
	private Enemy[] enemies;
	private boolean inBattle;
	static final int ENEMY_LENGTH = 3;

	// Constructor
	public Game() {
		player = new Player();
		enemies = new Enemy[ENEMY_LENGTH];
		inBattle = false;
	}

	// Method to start game
	public void start() {
		System.out.println("Welcome to the text-based adventure RPG game!");
		showPlayerStatus();
		populateEnemies();
		mainMenu();
	}

	// Method to get a random enemy for player
	public Enemy getRandomEnemy() {
		int min = 0;
		int max = ENEMY_LENGTH - 1;
		Enemy chosen = enemies[(int) (Math.random() * (max - min + 1) + min)];
		return chosen;
	}

	// Method to populate the enemies
	private void populateEnemies() {
		enemies[0] = new Enemy("Zombie", 50, 50, 10, 20);
		enemies[1] = new Enemy("Skeleton", 40, 40, 12, 15);
		enemies[2] = new Enemy("Goblin", 40, 40, 8, 10);
	}

	// Method to show main menu and get user's choice to execute user's selected
	// action
	private void mainMenu() {
		// Show choices in main menu
		System.out.println("What would you like to do next?");
		System.out.println("1. Fight");
		System.out.println("2. Access shop");
		System.out.println("3. Exit game");
		System.out.println("Enter action: ");
		Scanner scanner = new Scanner(System.in);
		// Try catch to check the input mismatch exception
		try {
			int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					inBattle = true;
					// Call fight method
					fight();
					break;
				case 2:
					// Call accessShop method
					accessShop();
					break;
				case 3:
					// Exit game
					System.out.println("Exiting game... Thank you for playing!");
					System.exit(0);
					break;
				default:
					// Invalid choice
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		} catch (InputMismatchException e) {
			// Handle the exception
			scanner.nextLine();
			System.out.println("Please enter an integer.");
		}
		mainMenu();
	}

	// Method to show shop's operation and get user's choice to execute user's
	// selected action
	private void accessShop() {
		// Show the shop operation and player's status
		System.out.println("Welcome to the shop!");
		showPlayerStatus();
		System.out.println("1. Buy HP potion (50 gold)");
		System.out.println("2. Buy weapon upgrade (100 gold)");
		System.out.println("3. Exit shop");
		System.out.println("Enter action: ");

		Scanner scanner = new Scanner(System.in);
		// Try catch to check the input mismatch exception
		try {
			int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					// Buy potion
					// Check if player has enough gold
					if (player.getGold() >= 50) {
						player.setGold(player.getGold() - 50);
						player.setPotionQty(player.getPotionQty() + 1);
						System.out.println("You have bought a health potion.");
					} else {
						System.out.println("You do not have enough gold to buy a health potion.");
					}
					break;
				case 2:
					// Upgrade weapon
					// Check if player has enough gold
					if (player.getGold() >= 100) {
						player.setGold(player.getGold() - 100);
						player.upgradeWeapon();
						System.out.println("You have bought a weapon upgrade and your weapon damage has increased.");
					} else {
						System.out.println("You do not have enough gold to buy a weapon upgrade.");
					}
					break;
				case 3:
					// Exit from shop
					System.out.println("Exiting shop...");
					showPlayerStatus();
					mainMenu();
					break;
				default:
					// Invalid choice
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		} catch (InputMismatchException e) {
			// Handle the exception
			scanner.nextLine();
			System.out.println("Please enter an integer.");
		}
		accessShop();
	}

	private void fight() {
		// Get a random Enemy
		Enemy enemy = getRandomEnemy();
		Scanner scanner = new Scanner(System.in);
		// Enemy appeared
		System.out.println(enemy.getName() + " has appeared!");
		// Show both Player and Enemy Status
		showPlayerStatus();
		showEnemyStatus(enemy);

		// Battle phase
		while (player.isAlive() && enemy.isAlive()) {
			// Show battle menu
			showBattleMenu();
			// "again" label to handle usePotion's failure
			again: {
				// Try catch to check the input mismatch exception
				try {
					int choice = scanner.nextInt();
					if (choice == 1)
						// Player attacks Enemy
						player.attack(enemy);
					else if (choice == 2) {
						// If Player failed to use potion, break out of "again" label and prevent Enemy
						// from attacking
						if (!player.usePotion()) {
							break again;
						}
					} else {
						// Invalid choice, break out of "again" label and prevent Enemy from attacking
						System.out.println("Invalid choice. Please try again.");
						break again;
					}
					// If enemy is alive, attack the player
					if (enemy.isAlive())
						enemy.attack(player);
				} catch (InputMismatchException e) {
					// Handle the exception
					scanner.nextLine();
					System.out.println("Please enter an integer.");
				}
			}
			showPlayerStatus();
			showEnemyStatus(enemy);
		}
		// Battle phase ends
		inBattle = false;
		// If Player is not alive, Game over.
		if (!player.isAlive()) {
			System.out.println("You have been defeated. Game over.");
			System.exit(0);
		} else {
			// Player wins the battle and gets the gold that Enemy carrying
			System.out.println("You have defeated the enemy! You earned " + enemy.getGold() + " gold.");
			// Reset the Enemy health
			enemy.setHealth(enemy.getMaxHealth());
			// Player gets the gold that Enemy carrying
			player.setGold(player.getGold() + enemy.getGold());
			// show player status and return to main menu
			showPlayerStatus();
			mainMenu();
		}
	}

	// Method to show battle menu
	private void showBattleMenu() {
		System.out.println("Select action:");
		System.out.println("1. Attack");
		System.out.println("2. Use Health Potion (Restore Full HP)");
		System.out.println("Enter action: ");
	}

	// Method to show Player status
	private void showPlayerStatus() {
		// Print player status
		System.out.print("Your Status:\n\tHP " + player.getHealth() + " | DMG " + player.getWeaponDamage()
				+ " | HP Potion(s): " + player.getPotionQty());
		// If player is not in Battle phase, do not print the gold
		if (!inBattle)
			System.out.println(" | Gold: " + player.getGold());
		else
			System.out.println();
	}

	// Method to show Enemy status
	private void showEnemyStatus(Enemy enemy) {
		// Print enemy status
		System.out.println(
				enemy.getName() + "'s Status:\n\tHP " + enemy.getHealth() + " | DMG " + enemy.getWeaponDamage());
	}

	// Main Method
	public static void main(String[] args) {
		// Initialize a game & Start the game
		Game game = new Game();
		game.start();
	}
}
