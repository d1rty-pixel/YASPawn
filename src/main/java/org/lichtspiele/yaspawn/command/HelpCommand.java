package org.lichtspiele.yaspawn.command;

import org.bukkit.command.CommandSender; 
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.Permission;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;

public class HelpCommand extends PluginCommandBase {

	public HelpCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yaspawn.spawn");
	}
	
	public boolean call(Messages messages, String[] args) throws TranslationNotFoundException {		
		
		messages.helpTitle(sender);
		messages.helpEntry(sender, "spawn", new String[0], "help.spawn");

		if (Permission.hasPermission(sender, "yaspawn.spawn.world")) {
			messages.helpEntry(sender, "spawn", new String[] { "<world>" } , "help.spawn_world");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.defaultworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "defaultworld" }, "help.defaultworld");
			messages.helpEntry(sender, "spawn", new String[] { "config", "defaultworld <world>" }, "help.defaultworld_modify");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.singleserverspawn")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "singleserverspawn" }, "help.singleserverspawn");
			messages.helpEntry(sender, "spawn", new String[] { "config", "singleservespawn", "<enable|disable>" }, "help.singleserverspawn_modify");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.sayworldname")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "sayworldmname" }, "help.sayworldname");
			messages.helpEntry(sender, "spawn", new String[] { "config", "sayworldname", "<enable|disable>" }, "help.sayworldname_modify");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.prefixonspawn")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "prefixonspawn" }, "help.prefixonspawn");
			messages.helpEntry(sender, "spawn", new String[] { "config", "prefixonspawn", "<enable|disable>" }, "help.prefixonspawn_modify");
		}		
		
		if (Permission.hasPermission(sender, "yaspawn.admin.locale")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "locale" }, "help.locale");
			messages.helpEntry(sender, "spawn", new String[] { "config", "locale", "<locale>" }, "help.locale_modify");
		}
		
		if (	Permission.hasPermission(sender, "yaspawn.admin.defaultworld") || 
				Permission.hasPermission(sender, "yaspawn.admin.singleservespawn") ||
				Permission.hasPermission(sender, "yaspawn.admin.sayworldname") ||
				Permission.hasPermission(sender, "yaspawn.admin.prefixonspawn") ||
				Permission.hasPermission(sender, "yaspawn.admin.locale")
				) {
			messages.helpEntry(sender, "spawn", new String[] { "config" }, "help.config");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.disableworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "world", "<world>", "disable" }, "help.world_disable");
		}
		if (Permission.hasPermission(sender, "yaspawn.admin.enableworld")) {
			messages.helpEntry(sender, "spawn", new String[] { "config", "world", "<world>", "enable" }, "help.world_enable");
		}
		
		if (Permission.hasPermission(sender, "yaspawn.admin.worldlist")) {
			messages.helpEntry(sender, "spawn", new String[] { "worldlist" }, "help.worldlist");
		}
		if (Permission.hasPermission(sender, "yaspawn.admin.reload")) {
			messages.helpEntry(sender, "spawn", new String[] { "reload" }, "help.reload");
		}
		
		return true;
	}

}