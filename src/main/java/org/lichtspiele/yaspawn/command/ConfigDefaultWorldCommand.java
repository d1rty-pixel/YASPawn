package org.lichtspiele.yaspawn.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;
import org.lichtspiele.yaspawn.Worlds;

public class ConfigDefaultWorldCommand extends PluginCommandBase {
		
	public ConfigDefaultWorldCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yaspawn.admin.defaultworld");
	}
	
	@SuppressWarnings("unchecked")
	public boolean call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 0) {
			messages.defaultWorld(this.sender, this.config.getString("default-world"));
			
		} else if (args.length == 1 ) { 
			String world = args[0].toString().trim();
			Worlds worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
						
			if (!worlds.worldExists(world)) {
				messages.unknownWorld(sender, world);
				return true;
			}

			this.config.set("default-world", world);
			this.plugin.saveConfig();			
			
			this.call(messages, new String[0]);
		} else {
			throw new InvalidCommandException();
		}
		
		return true;
	}

}
