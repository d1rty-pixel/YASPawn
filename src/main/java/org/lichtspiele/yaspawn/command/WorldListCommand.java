package org.lichtspiele.yaspawn.command;

import java.util.List; 

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;
import org.lichtspiele.yaspawn.Worlds;

public class WorldListCommand extends PluginCommandBase {

	private Worlds worlds				= null;
	
	@SuppressWarnings("unchecked")
	public WorldListCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yaspawn.admin.worldlist");
		
		this.worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
	}
	
	public boolean call(Messages messages, String[] args) throws TranslationNotFoundException {		
		messages.worldListTitle(this.sender);
		
		List <?> dw = this.worlds.getDisabledWorlds();
		for (World w : Bukkit.getServer().getWorlds()) {
			messages.worldListEntry(this.sender, w.getName(), dw.contains(w.getName()));						
		}
		
		return true;
	}

}