package me.wilkins.mob;

import org.bukkit.entity.EntityType;

public class CustomMob {

	private final EntityType type;

	private double health;
	private MobArmour mobArmour;
	private MobSkull mobSkull;

	private DropType dropType = DropType.PRESET_DEFAULT;

	private double skullDropChance = -1;
	private double chestplateDropChance = -1;
	private double leggingsDropChance = -1;
	private double bootsDropChance = -1;

	public CustomMob(EntityType entityType){
		type = entityType;
	}

	public EntityType getType() {
		return type;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public MobArmour getMobArmour() {
		return mobArmour;
	}

	public void setMobArmour(MobArmour mobArmour) {
		this.mobArmour = mobArmour;
	}

	public MobSkull getMobSkull() {
		return mobSkull;
	}

	public void setMobSkull(MobSkull mobSkull) {
		this.mobSkull = mobSkull;
	}

	public double getSkullDropChance() {
		return skullDropChance;
	}

	public void setSkullDropChance(double skullDropChance) {
		this.skullDropChance = skullDropChance;
	}

	public double getChestplateDropChance() {
		return chestplateDropChance;
	}

	public void setChestplateDropChance(double chestplateDropChance) {
		this.chestplateDropChance = chestplateDropChance;
	}

	public double getLeggingsDropChance() {
		return leggingsDropChance;
	}

	public void setLeggingsDropChance(double leggingsDropChance) {
		this.leggingsDropChance = leggingsDropChance;
	}

	public double getBootsDropChance() {
		return bootsDropChance;
	}

	public void setBootsDropChance(double bootsDropChance) {
		this.bootsDropChance = bootsDropChance;
	}

	public DropType getDropType() {
		return dropType;
	}

	public void setDropType(DropType dropType) {
		this.dropType = dropType;
	}

	@Override
	public String toString() {
		return "CustomMob{" +
				"type=" + type +
				", health=" + health +
				", armour=" + mobArmour +
				", skull=" + mobSkull +
				", dropType=" + dropType +
				", skullDropChance=" + skullDropChance +
				", chestplateDropChance=" + chestplateDropChance +
				", leggingsDropChance=" + leggingsDropChance +
				", bootsDropChance=" + bootsDropChance +
				'}';
	}

	public enum DropType {
		PRESET_DEFAULT,
		PRESET,
		DEFAULT,
		NONE
	}
}
