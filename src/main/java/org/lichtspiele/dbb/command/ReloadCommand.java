package org.lichtspiele.dbb.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationFileNotFoundException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;

public class ReloadCommand extends PluginCommandBase {

	protected String permission			= "yasp.admin.reload";
	
	protected boolean mustBePlayer		= true;
	
	public ReloadCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		
		super(plugin, sender);
	}
	
	public void call(Messages messages, String[] args) throws TranslationNotFoundException, TranslationFileNotFoundException {		
		this.plugin.reloadConfig();
		
		this.reload();
		messages.reloadTranslation();
		messages.reload(this.sender);
	}

}
