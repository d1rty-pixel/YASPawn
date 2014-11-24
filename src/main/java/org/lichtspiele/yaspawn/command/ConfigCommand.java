package org.lichtspiele.yaspawn.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.Permission;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationFileNotFoundException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;

public class ConfigCommand extends PluginCommandBase {

	public ConfigCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, null);
	}

	public boolean call(Messages messages, String args[])
			throws TranslationNotFoundException, InvalidCommandException, CommandSenderIsNotPlayerException, TranslationFileNotFoundException {
	
		if (	Permission.hasPermission(sender, "yaspawn.admin.defaultworld") || 
				Permission.hasPermission(sender, "yaspawn.admin.singleservespawn") ||
				Permission.hasPermission(sender, "yaspawn.admin.sayworldname") ||
				Permission.hasPermission(sender, "yaspawn.admin.prefixonspawn") ||
				Permission.hasPermission(sender, "yaspawn.admin.locale")
			) {
			messages.configTitle(this.sender);
		}
		
		try {
			ConfigSingleServerSpawnCommand csssc = new ConfigSingleServerSpawnCommand(this.plugin, this.sender);
			csssc.call(messages, new String[0]);
		} catch (InsufficientPermissionException e) { }
		try {			
			ConfigDefaultWorldCommand cdwc = new ConfigDefaultWorldCommand(this.plugin, this.sender);
			cdwc.call(messages, new String[0]);
		} catch (InsufficientPermissionException e) { }
		try {
			ConfigSayWorldNameCommand cswnc = new ConfigSayWorldNameCommand(this.plugin, this.sender);
			cswnc.call(messages, new String[0]);
		} catch (InsufficientPermissionException e) { }
		try {
			ConfigPrefixOnSpawnCommand cposc = new ConfigPrefixOnSpawnCommand(this.plugin, this.sender);
			cposc.call(messages, new String[0]);
		} catch (InsufficientPermissionException e) { }
		try {
			ConfigLocaleCommand clc = new ConfigLocaleCommand(this.plugin, this.sender);
			clc.call(messages, new String[0]);
		} catch (InsufficientPermissionException e) { }
		
		return true;
	}

}
