package org.lichtspiele.yaspawn.config.locale;

import java.io.IOException;
import java.util.HashMap;

import org.lichtspiele.dbb.BukkitPlugin;
import org.lichtspiele.dbb.config.CustomConfigurationFile;

public class de_DE extends CustomConfigurationFile {

	public de_DE(BukkitPlugin plugin) throws IOException {
		super(plugin, "locale/de_DE.yml", de_DE.data());
	}
	
	private static HashMap<String,Object> data() {
		HashMap<String,Object> data		= new HashMap<String,Object>();
		
		data.put("error", 								"&cFEHLER");
		data.put("missing_translation",					"&4Übersetzung für Gebietsschema &b%locale%&4 nicht gefunden");		
		data.put("missing_permission", 					"&4Nicht genügend Rechte (%spermission%)");
		data.put("invalid_command", 					"Ungültiger Befehl. Siehe &e/spawn help &cfür eine vollständige Liste");
		
		data.put("unknown_world", 						"&4Welt &f%world% &4existiert nicht");
		
		data.put("enabled",								"&aaktiviert");
		data.put("disabled",							"&cdeaktiviert");
			
		data.put("world_spawn_state", 					"&fSpawnen auf Welt &b%world% &fist %state%");
		data.put("world_spawn_state_already_applied", 	"&fSpawnen auf Welt &b%world% &fist bereits %state%");

		data.put("default_world_worldname", 			"&fStandard-Welt ist auf &b%world% &fgesetzt");
		data.put("spawn_message", 						"&eWillkommen am Spawn");
		data.put("spawn_message_world", 				"&eWillkommen am Spawn der Welt &b%world%");
		data.put("world_list_header", 					"&bWelt-Spawn Stati:");
		data.put("world_list_entry", 					"&f%world%: &b%state%");
		data.put("say_world_name_state", 				"&fWeltennamen anzeigen %state%");
		data.put("prefix_on_spawn_state", 				"&fPluginprefix anzeigen %state%");
		data.put("single_spawn_state", 					"&fSingle-Server-Spawn %state%");
		data.put("set_locale", 							"&fGebietsschema auf &b%locale% &fgesetzt");
		
		data.put("reload", 								"&fNeu geladen");
		
		data.put("help.header", 						"&lPlugin-Hilfe:");
		data.put("help.spawn", 							"Zum Spawn teleportieren");
		data.put("help.spawn_world", 					"Zum Spawn der Welt <world> teleportieren");
		data.put("help.defaultworld", 					"Die Standardwelt anzeigen");
		data.put("help.defaultworld_modify", 			"Die Standardwelt festlegen");
		data.put("help.singleserverspawn", 				"Single-Server-Spawn Status anzeigen");
		data.put("help.singleserverspawn_modify", 		"Single-Server-Spawn Status aktivieren/deaktivieren");
		data.put("help.sayworldname", 					"Weltnamen Status anzeigen");
		data.put("help.sayworldname_modify", 			"Weltnamen Status Status aktivieren/dekativieren");
		data.put("help.prefixonspawn", 					"Plugin-Prefix Status anzeigen");
		data.put("help.prefixonspawn_modify", 			"Plugin-Prefix anzeigen Status aktivieren/deaktivieren");		
		data.put("help.locale", 						"Gebietsschema anzeigen");
		data.put("help.locale_modify", 					"Gebietsschema setzen");		
		data.put("help.config", 						"Zeigt die Plugin Konfiguration an");
		data.put("help.config_header",					"&lPlugin Konfiguration:");
		data.put("help.world_disable",					"Deaktiviert Spawnen auf einer Welt");		
		data.put("help.world_enable", 					"Aktiviert Spawnen auf einer Welt");
		data.put("help.worldlist", 						"Zeigt für jede Welt an, ob Spawnen aktiviert/deaktiviert ist");		
		data.put("help.reload", 						"Die Plugin Konfiguration neu laden");

		return data;
	}
	
}
