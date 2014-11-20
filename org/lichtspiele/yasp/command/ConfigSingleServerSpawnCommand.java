package org.lichtspiele.yasp.command;

import org.bukkit.command.CommandSender; 
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;

public class ConfigSingleServerSpawnCommand extends PluginCommandBase {
	
	protected String permission		= "yasp.admin.singleserverspawn";
	
	public ConfigSingleServerSpawnCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	public void call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 0) {
			messages.singleServerSpawn(this.sender, this.config.getBoolean("single-server-spawn"));
			
		} else if (args.length == 1){ 
			if (args[0].equalsIgnoreCase("enable")) {
				this.config.set("single-server-spawn", true);
			} else if (args[0].equalsIgnoreCase("disable")) {
				this.config.set("single-server-spawn", false);
			} else {
				throw new InvalidCommandException();
			}
			this.plugin.saveConfig();
			this.call(messages, new String[0]);
			
		} else {
			throw new InvalidCommandException();
		}
	}

}
