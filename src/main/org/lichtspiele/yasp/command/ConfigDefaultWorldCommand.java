package org.lichtspiele.yasp.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;
import org.lichtspiele.yasp.Worlds;

public class ConfigDefaultWorldCommand extends PluginCommandBase {
	
	protected String permission		= "yasp.admin.defaultworld";
	
	public ConfigDefaultWorldCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	@SuppressWarnings("unchecked")
	public void call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 0) {
			messages.defaultWorld(this.sender, this.config.getString("default-world"));
			
		} else if (args.length == 1 ) { 
			String world = args[0].toString().trim();
			Worlds worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
			
			sender.sendMessage(Bukkit.getServer().getWorlds().toString());
			
			if (!worlds.worldExists(world)) {
				messages.unknownWorld(sender, world);
				return;
			}

			this.config.set("default-world", world);
			this.plugin.saveConfig();			
			
			this.call(messages, new String[0]);
			
		} else {
			throw new InvalidCommandException();
		}
	}

}
