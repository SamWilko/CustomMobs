package me.wilkins;

import me.wilkins.mob.MobListener;
import me.wilkins.mob.MobSettings;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Collections;
import java.util.List;

public class CustomMobsPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {

		registerEvents(new MobListener());
	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Collections.singletonList(MobSettings.class);
	}
}
