package org.lichtspiele.yaspawn.command;

import org.bukkit.command.CommandSender; 
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;

public class ConfigSayWorldNameCommand extends PluginCommandBase {
		
	public ConfigSayWorldNameCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yasp.admin.sayworldname");
	}
	
	public boolean call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 0) {
			messages.sayWorldName(this.sender, this.config.getBoolean("say-world-name"));
			
		} else if (args.length == 1){ 
			if (args[0].equalsIgnoreCase("enable")) {
				this.config.set("say-world-name", true);		
			} else if (args[0].equalsIgnoreCase("disable")) {
				this.config.set("say-world-name", false);
			} else {
				throw new InvalidCommandException();				
			}
			
			this.plugin.saveConfig();
			this.call(messages, new String[0]);
			
		} else {
			throw new InvalidCommandException();
		}
		
		return true;
	}

}
