package org.lichtspiele.yasp.command;

import org.bukkit.command.CommandSender; 
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.Permission;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;

public class HelpCommand extends PluginCommandBase {

	protected String permission		= "yasp.spawn";
	
	public HelpCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	public void call(Messages messages, String[] args) throws TranslationNotFoundException {		
		
		messages.helpTitle(sender);
		messages.helpEntry(sender, "spawn", new String[0], "help_spawn");

		if (Permission.hasPermission(sender, "yasp.spawn.world")) {
			messages.helpEntry(sender, "spawn", new String[] { "<world>" } , "help_spawn_world");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.defaultworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "defaultworld" }, "help_defaultworld");
			messages.helpEntry(sender, "spawn", new String[] { "config", "defaultworld <world>" }, "help_defaultworld_modify");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.singleserverspawn")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "singleserverspawn" }, "help_singleserverspawn");
			messages.helpEntry(sender, "spawn", new String[] { "config", "singleservespawn", "<enable|disable>" }, "help_singleserverspawn_modify");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.sayworldname")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "sayworldmname" }, "help_sayworldname");
			messages.helpEntry(sender, "spawn", new String[] { "config", "sayworldname", "<enable|disable>" }, "help_sayworldname_modify");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.prefixonspawn")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "prefixonspawn" }, "help_prefixonspawn");
			messages.helpEntry(sender, "spawn", new String[] { "config", "prefixonspawn", "<enable|disable>" }, "help_prefixonspawn_modify");
		}		
		
		if (Permission.hasPermission(sender, "yasp.admin.locale")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "locale" }, "help_locale");
			messages.helpEntry(sender, "spawn", new String[] { "config", "locale", "<locale>" }, "help_locale_modify");
		}
		
		if (	Permission.hasPermission(sender, "yasp.admin.defaultworld") || 
				Permission.hasPermission(sender, "singleservespawn") ||
				Permission.hasPermission(sender, "yasp.admin.sayworldname") ||
				Permission.hasPermission(sender, "yasp.admin.prefixonspawn") ||
				Permission.hasPermission(sender, "yasp.admin.locale")
				) {
			messages.helpEntry(sender, "spawn", new String[] { "config" }, "help_config");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.disableworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "world", "<world>", "disable" }, "help_world_disable");
		}
		if (Permission.hasPermission(sender, "yasp.admin.enableworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "world", "<world>", "enable" }, "help_world_enable");
		}
		
		if (Permission.hasPermission(sender, "yasp.admin.worldlist")) {
			messages.helpEntry(sender, "spawn", new String[] { "worldlist" }, "help_worldlist");
		}
		if (Permission.hasPermission(sender, "yasp.admin.reload")) {
			messages.helpEntry(sender, "spawn", new String[] { "reload" }, "help_reload");
		}	
	}

}
