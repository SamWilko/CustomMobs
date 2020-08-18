package me.wilkins.mob;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.mineacademy.fo.menu.model.ItemCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MobSkull {

	private final String name;

	public MobSkull(String name){
		this.name = name;
	}

	public ItemStack getItemStack(){

		boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
		Material material = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
		ItemStack itemStack = new ItemStack(material, 1);
		if (!isNewVersion)
			itemStack.setDurability((short) 3);
		SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
		meta.setOwner(name);
		itemStack.setItemMeta(meta);
		return ItemCreator.of(itemStack).build().make();
	}

	@Override
	public String toString() {
		return "Skull{" +
				"name='" + name + '\'' +
				'}';
	}
}
