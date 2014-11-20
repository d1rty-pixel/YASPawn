package org.lichtspiele.yasp.command;

import java.util.List; 

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;
import org.lichtspiele.yasp.Worlds;

public class WorldListCommand extends PluginCommandBase {

	protected String permission			= "yasp.admin.worldlist";
	
	protected boolean mustBePlayer		= true;
	
	private Worlds worlds				= null;
	
	@SuppressWarnings("unchecked")
	public WorldListCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		
		super(plugin, sender);
		
		this.worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
	}
	
	public void call(Messages messages, String[] args) throws TranslationNotFoundException {		
		messages.worldListTitle(this.sender);
		
		List <?> dw = this.worlds.getDisabledWorlds();
		for (World w : Bukkit.getServer().getWorlds()) {
			messages.worldListEntry(this.sender, w.getName(), dw.contains(w.getName()));						
		}
	}

}
