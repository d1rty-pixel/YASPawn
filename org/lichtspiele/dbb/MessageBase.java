package org.lichtspiele.dbb;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class MessageBase {
	
	Translation translation	 	= null;
	
	String message_prefix		= null;
	
	public MessageBase(Translation translation) {
		this.translation = translation;
	}
	
	public void setMessagePrefix(String prefix) {
		this.message_prefix = prefix;
	}
	
	public void send(CommandSender sender, String message, boolean prefix) {
		if (prefix) message = this.message_prefix.toString() + message;
		if (!(sender instanceof Player)) message = ChatColor.stripColor(message);
		sender.sendMessage(message);
	}
	
	public void send(CommandSender sender, String message) {
		this.send(sender, message, true);
	}
	
	public void missingPermission(CommandSender sender, String permission) {
		String message =				
			this.translation.getTranslation("error") + 
			this.translation.getTranslation("missing_permission", new String[] {"permission", permission} );
		this.send(sender, message);		
	}	
	
}
