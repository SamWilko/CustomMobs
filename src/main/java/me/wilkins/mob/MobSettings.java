package me.wilkins.mob;

import org.bukkit.entity.EntityType;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.ArrayList;
import java.util.List;

public class MobSettings extends SimpleSettings {

	private static final List<CustomMob> customMobList = new ArrayList<>();

	public static CustomMob findCustomMob(EntityType type){
		for(CustomMob mob : customMobList){
			if(mob.getType() == type)
				return mob;
		}

		return null;
	}

	public static List<CustomMob> getCustomMobList() {
		return customMobList;
	}

	private static void init(){

		for(String entityName : getMap("CustomMobs").keySet()){

			EntityType type;
			try{
				type = EntityType.valueOf(entityName.toUpperCase());
			}catch(Exception ex){
				Common.logFramed("[" + entityName + "] was not loaded because this Entity Type does not exist...");
				continue;
			}

			CustomMob customMob = new CustomMob(type);

			pathPrefix("CustomMobs." + entityName);

			// Loads the mob's armour if applicable
			MobArmour mobArmour = new MobArmour();
			String chestplateString;
			if(isSet("Armour.Chestplate")) {
				chestplateString = getString("Armour.Chestplate");

				CompMaterial chestplate = findMaterial(chestplateString, "chestplate");
				if (chestplate != null)
					mobArmour.setChestplate(ItemCreator.of(chestplate).build().makeSurvival());
				else
					Common.logFramed("Could not load chestplate for [" + entityName + "] because (" + chestplateString + ") is not a type of chestplate!");
			}

			String leggingsString;
			if(isSet("Armour.Leggings")) {
				leggingsString = getString("Armour.Leggings");

				CompMaterial leggings = findMaterial(leggingsString, "leggings");
				if (leggings != null)
					mobArmour.setLeggings(ItemCreator.of(leggings).build().makeSurvival());
				else
					Common.logFramed("Could not load leggings for [" + entityName + "] because (" + leggingsString + ") is not a type of leggings!");
			}

			String bootsString;
			if(isSet("Armour.Boots")) {
				bootsString = getString("Armour.Boots");

				CompMaterial boots = findMaterial(bootsString, "boots");
				if (boots != null)
					mobArmour.setBoots(ItemCreator.of(boots).build().makeSurvival());
				else
					Common.logFramed("Could not load boots for [" + entityName + "] because (" + bootsString + ") is not a type of boots!");
			}

			customMob.setMobArmour(mobArmour);

			// Loads the mob's skull
			String playerName;
			MobSkull mobSkull = null;
			if(isSet("Skull")) {
				playerName = getString("Skull");

				mobSkull = new MobSkull(playerName);
			}

			if(mobSkull != null)
				customMob.setMobSkull(mobSkull);


			// Loads the mob's health
			double health = 0;
			if(isSet("Health")) {
				try {
					health = getDouble("Health");
					if(health <= 0)
						Common.logFramed("Could not load health for [" + entityName + "] because health cannot be below 0");
				} catch (Exception ex) {
					Common.logFramed("Could not load health for [" + entityName + "] because it is not a valid number. Setting health to default value of the entity type");
				}
			}
			customMob.setHealth(health);

			// Loads the mob's drop chance
			if(isSet("Drop_Chances")) {
				if(isSet("Drop_Default_Items")){
					if(getBoolean("Drop_Default_Items"))
						customMob.setDropType(CustomMob.DropType.PRESET_DEFAULT);
					else
						customMob.setDropType(CustomMob.DropType.PRESET);
				}else
					customMob.setDropType(CustomMob.DropType.PRESET_DEFAULT);

				pathPrefix("CustomMobs." + entityName + ".Drop_Chances");
				if (isSet("Skull"))
					customMob.setSkullDropChance(getDouble("Skull"));
				if (isSet("Chestplate"))
					customMob.setChestplateDropChance(getDouble("Chestplate"));
				if (isSet("Leggings"))
					customMob.setLeggingsDropChance(getDouble("Leggings"));
				if (isSet("Boots"))
					customMob.setBootsDropChance(getDouble("Boots"));
			}else {
				if(isSet("Drop_Default_Items")) {
					if (getBoolean("Drop_Default_Items"))
						customMob.setDropType(CustomMob.DropType.DEFAULT);
					else
						customMob.setDropType(CustomMob.DropType.NONE);
				}else
					customMob.setDropType(CustomMob.DropType.DEFAULT);
			}

			customMobList.add(customMob);
		}
	}

	private static CompMaterial findMaterial(String material, String suffix){

		CompMaterial material1;
		try{
			material1 = CompMaterial.valueOf(material.toUpperCase() + "_" + suffix.toUpperCase());
			return material1;
		}catch(Exception exception){
			return null;
		}
	}

	@Override
	protected int getConfigVersion() {
		return 1;
	}
}
