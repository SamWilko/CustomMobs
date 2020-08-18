package me.wilkins.mob;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.mineacademy.fo.remain.CompAttribute;

import java.util.Random;

public class MobListener implements Listener {

	@EventHandler
	public static void onMobSpawn(CreatureSpawnEvent event){

		// Only affects mobs that spawn naturally
		if(event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL)
			return;

		LivingEntity entity = event.getEntity();

		CustomMob customMob = MobSettings.findCustomMob(entity.getType());
		if(customMob == null)
			return;

		// Checks that the health that is set in the config is between 1 and the max health for that particular entity
		if (customMob.getHealth() > 0 && customMob.getHealth() <= CompAttribute.GENERIC_MAX_HEALTH.get(entity)) {
			CompAttribute.GENERIC_MAX_HEALTH.set(entity, customMob.getHealth());
			entity.setHealth(customMob.getHealth());
		}

		// Sets the mob's armour and skull
		EntityEquipment equipment = entity.getEquipment();
		MobArmour mobArmour = customMob.getMobArmour();
		if(equipment != null) {

			if(customMob.getMobSkull() != null)
				equipment.setHelmet(customMob.getMobSkull().getItemStack());

			if(mobArmour != null) {
				if(mobArmour.getChestplate() != null)
					equipment.setChestplate(mobArmour.getChestplate());
				if(mobArmour.getLeggings() != null)
					equipment.setLeggings(mobArmour.getLeggings());
				if(mobArmour.getBoots() != null)
					equipment.setBoots(mobArmour.getBoots());
			}
		}
	}

	@EventHandler
	public static void onMobDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();

		CustomMob customMob = MobSettings.findCustomMob(entity.getType());
		if (customMob == null)
			return;

		MobArmour mobArmour = customMob.getMobArmour();

		// Changes skull drop chances
		if(customMob.getDropType() == CustomMob.DropType.DEFAULT)
			return;

		if(customMob.getDropType() == CustomMob.DropType.PRESET_DEFAULT && mobArmour != null){

			// Removes any armour / skull items that may be in the default drop
			if(customMob.getMobSkull().getItemStack() != null)
				event.getDrops().remove(customMob.getMobSkull().getItemStack());
			if(mobArmour.getChestplate() != null)
				event.getDrops().remove(mobArmour.getChestplate());
			if(mobArmour.getLeggings() != null)
				event.getDrops().remove(mobArmour.getLeggings());
			if(mobArmour.getBoots() != null)
				event.getDrops().remove(mobArmour.getBoots());
		}else
			event.getDrops().clear();

		// If the user actually specified a drop chance
		if (customMob.getSkullDropChance() >= 0)

			// If the user specified for the mob to have a skull
			if (customMob.getMobSkull() != null) {
				if (chance(customMob.getSkullDropChance()))
					event.getDrops().add(customMob.getMobSkull().getItemStack());
			}

		// Changes armour drop chances
		if (mobArmour != null) {

			if (mobArmour.getChestplate() != null && customMob.getChestplateDropChance() >= 0) {
				if (chance(customMob.getChestplateDropChance()))
					event.getDrops().add(mobArmour.getChestplate());
			}

			if (mobArmour.getLeggings() != null && customMob.getLeggingsDropChance() >= 0) {
				if (chance(customMob.getLeggingsDropChance()))
					event.getDrops().add(mobArmour.getLeggings());
			}

			if (mobArmour.getBoots() != null && customMob.getBootsDropChance() >= 0) {
				if (chance(customMob.getBootsDropChance()))
					event.getDrops().add(mobArmour.getBoots());
			}
		}
	}

	private static boolean chance(double percentage){
		return new Random().nextInt(100 - 1) + 1 <= percentage;
	}
}
