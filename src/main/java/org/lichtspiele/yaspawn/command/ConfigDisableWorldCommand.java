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

public class ConfigDisableWorldCommand extends PluginCommandBase {
				
	private Worlds worlds			= null;			
	
	@SuppressWarnings("unchecked")
	public ConfigDisableWorldCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yaspawn.admin.disableworld");
		
		this.worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
	}
	
	public boolean call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 1) { 
			String world = args[0].toString();
			
			if (!this.worlds.worldExists(world)) {
				messages.unknownWorld(sender, world);
				return true;
			}
			
			if (this.worlds.disableWorld(world)) {
				this.config.set("disabled-worlds", this.worlds.getDisabledWorlds());
				this.plugin.saveConfig();
				messages.worldSpawnState(sender, world, false);
			} else {
				messages.worldSpawnStateAlreadyApplied(sender, world, false);				
			}
		} else {
			throw new InvalidCommandException();
		}
		
		return true;
	}

}
