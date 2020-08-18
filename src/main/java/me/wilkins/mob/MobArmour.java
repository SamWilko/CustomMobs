package me.wilkins.mob;

import org.bukkit.inventory.ItemStack;

public class MobArmour {

	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;

	public ItemStack getChestplate() {
		return chestplate;
	}

	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}

	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	@Override
	public String toString() {
		return "Armour{" +
				"chestplate=" + chestplate +
				", leggings=" + leggings +
				", boots=" + boots +
				'}';
	}
}
