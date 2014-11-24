package org.lichtspiele.yaspawn.command;

import org.bukkit.command.CommandSender ;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationFileNotFoundException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;

public class ConfigLocaleCommand extends PluginCommandBase {
			
	public ConfigLocaleCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender, "yaspawn.admin.locale");
	}
	
	public boolean call(Messages messages, String args[])
			throws InvalidCommandException, TranslationNotFoundException, TranslationFileNotFoundException {
				
		if (args.length == 0) {
			messages.locale(sender);
		
		} else if (args.length == 1) {	
			String old_locale	= this.config.getString("locale");
			String locale 		= args[0].toString();
			
			this.config.set("locale", locale);
			
			try {
				messages.reloadTranslation();
			} catch (TranslationFileNotFoundException e) {
				this.config.set("locale", old_locale);
				throw new TranslationFileNotFoundException(e.getMessage());
			}
			this.plugin.saveConfig();
			
			this.call(messages, new String[0]);
			
		} else {
			throw new InvalidCommandException();			
		}
		
		return true;
	}

}
