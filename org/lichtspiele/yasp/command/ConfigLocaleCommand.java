package org.lichtspiele.yasp.command;

import org.bukkit.command.CommandSender ;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationFileNotFoundException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;

public class ConfigLocaleCommand extends PluginCommandBase {
	
	protected String permission		= "yasp.admin.locale";
			
	public ConfigLocaleCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	public void call(Messages messages, String args[])
			throws InvalidCommandException, TranslationNotFoundException, TranslationFileNotFoundException {
		
		sender.sendMessage("len: "+ args.length);
		
		if (args.length == 0) {
			messages.locale(sender);
		
		} else if (args.length == 1) {	
			String old_locale	= this.config.getString("locale");
			String locale 		= args[0].toString();
			
			sender.sendMessage("old: " + old_locale + " - set to: " + locale);
			
			this.config.set("locale", locale);
			
			try {
				messages.reloadTranslation();
			} catch (TranslationFileNotFoundException e) {
				this.config.set("locale", old_locale);
				throw new TranslationFileNotFoundException(e.getMessage());
			}
			this.plugin.saveConfig();
			
			sender.sendMessage("success");
			
			this.call(messages, new String[0]);
			
		} else {
			throw new InvalidCommandException();
			
		}
	}

}
