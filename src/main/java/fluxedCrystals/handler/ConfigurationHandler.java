package fluxedCrystals.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.reference.Reference;

public class ConfigurationHandler
{

	public static Configuration CONFIGURATION;
	public static boolean enderioAddon;
	public static boolean shard3x3;
	public static boolean normalShardRecipes;
	private static String addonCategory = "Addon Compatability";
	private static String dropCategory = "Drops";

	public static void init (File configFile) {

		if (CONFIGURATION == null) {

			CONFIGURATION = new Configuration(configFile);

			CONFIGURATION.addCustomCategoryComment(addonCategory, null);
			CONFIGURATION.addCustomCategoryComment(dropCategory, null);

			loadConfiguration();

		}

	}

	private static void loadConfiguration () {

		enderioAddon = CONFIGURATION.get(addonCategory, "EnderIO Addon Support", true).getBoolean(true);

		normalShardRecipes = CONFIGURATION.get(dropCategory, "Should materials be crafted in a normal crafting table?", false).getBoolean(false);
		shard3x3 = CONFIGURATION.get(dropCategory, "Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop?", true).getBoolean(true);

		if (CONFIGURATION.hasChanged()) {

			CONFIGURATION.save();

		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event) {

		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {

			loadConfiguration();

		}

	}

}
