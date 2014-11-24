package org.lichtspiele.yaspawn.config.locale;

import java.io.IOException;
import java.util.HashMap;

import org.lichtspiele.dbb.BukkitPlugin;
import org.lichtspiele.dbb.config.CustomConfigurationFile;

public class en_US extends CustomConfigurationFile {

	public en_US(BukkitPlugin plugin) throws IOException {
		super(plugin, "locale/en_US.yml", en_US.data());
	}
	
	private static HashMap<String,Object> data() {
		HashMap<String,Object> data		= new HashMap<String,Object>();
		
		data.put("error", 								"&cERROR");
		data.put("missing_translation",					"&4Translation for local &b%locale%&4 not found");		
		data.put("missing_permission", 					"&4Not enough permissions (%spermission%)");
		data.put("invalid_command", 					"Invalid command. See &e/spawn help &cfor a complete list");
		
		data.put("unknown_world", 						"&4World &f%world% &4does not exist");
		
		data.put("enabled",								"&aenabled");
		data.put("disabled",							"&cdisabled");
			
		data.put("world_spawn_state", 					"&fSpawning on world &b%world% &fis %state%");
		data.put("world_spawn_state_already_applied", 	"&fSpawning on world &b%world% &fis already %state%");

		data.put("default_world_worldname", 			"&fDefault spawn world set to &b%world%");
		data.put("spawn_message", 						"&eTeleported to spawn");
		data.put("spawn_message_world", 				"&eTeleported to spawn in world &b%world%");
		data.put("world_list_header", 					"&bWorld spawn states:");
		data.put("world_list_entry", 					"&f%world%: &b%state%");
		data.put("say_world_name_state", 				"&fSaying world name is %state%");
		data.put("prefix_on_spawn_state", 				"&fUsing plugin prefix is %state%");
		data.put("single_spawn_state", 					"&fSingle spawn %state%");
		data.put("set_locale", 							"&fLocale set to &b%locale%");
		
		data.put("reload", 								"&fReload complete");
		
		data.put("help.header", 						"&lPlugin-Help:");
		data.put("help.spawn", 							"Teleport to Spawn");
		data.put("help.spawn_world", 					"Teleport to spawn of <world>");
		data.put("help.defaultworld", 					"Get the current default world name");
		data.put("help.defaultworld_modify", 			"Set the default world name");
		data.put("help.singleserverspawn", 				"Get the current value of singleservespawn");
		data.put("help.singleserverspawn_modify", 		"Enable or disable singleserverspawn");
		data.put("help.sayworldname", 					"Get the current value of sayworldname");
		data.put("help.sayworldname_modify", 			"Enable or disable sayworldname");
		data.put("help.prefixonspawn", 					"Get the current value of prefixonspawn");
		data.put("help.prefixonspawn_modify", 			"Enable or disable prefixonspawn");		
		data.put("help.locale", 						"Get the current locale");
		data.put("help.locale_modify", 					"Set the new locale");		
		data.put("help.config", 						"Prints out the plugin configuration");
		data.put("help.config_header",					"&lPlugin Configuration:");
		data.put("help.world_disable",					"Disable spawning in the given world");		
		data.put("help.world_enable", 					"Enable spawning in the given world");
		data.put("help.worldlist", 						"Display spawning enabled or disabled for all worlds");		
		data.put("help.reload", 						"Reload the plugin configuration");

		return data;
	}
	
}
