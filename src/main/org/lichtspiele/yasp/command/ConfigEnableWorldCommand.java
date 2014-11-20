package org.lichtspiele.yasp.command;

import java.util.List; 

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;
import org.lichtspiele.yasp.Worlds;

public class ConfigEnableWorldCommand extends PluginCommandBase {
	
	protected String permission		= "yasp.admin.enableworld";
			
	private Worlds worlds			= null;			
	
	@SuppressWarnings("unchecked")
	public ConfigEnableWorldCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
		
		this.worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
	}
	
	public void call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 1 ) { 
			String world = args[0].toString();
			
			if (!this.worlds.worldExists(world)) {
				messages.unknownWorld(sender, world);
				return;
			}
			
			if (this.worlds.enableWorld(world)) {
				this.config.set("disabled-worlds", this.worlds.getDisabledWorlds());
				this.plugin.saveConfig();
				messages.worldSpawnState(sender, world, true);
			} else {
				messages.worldSpawnStateAlreadyApplied(sender, world, true);
			}
		} else {
			throw new InvalidCommandException();
		}
	}

}
