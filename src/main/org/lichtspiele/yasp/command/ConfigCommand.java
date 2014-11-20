package org.lichtspiele.yasp.command;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.Permission;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.yasp.Messages;

public class ConfigCommand extends PluginCommandBase {
				
	public ConfigCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	@SuppressWarnings("unchecked")
	public void call(Messages messages, String args[])
			throws InvalidCommandException, InsufficientPermissionException, CommandSenderIsNotPlayerException, InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException {
		
		HashMap<String,String> cfg = new HashMap<String, String>();
		cfg.put("yasp.admin.singleserverspawn", 	"ConfigSingleServerSpawnCommand");
		cfg.put("yasp.admin.defaultworld", 			"ConfigDefaultWorldCommand");
		cfg.put("yasp.admin.sayworldname", 			"ConfigSayWorldNameCommand");
		cfg.put("yasp,admin.prefixonspawn", 		"ConfigPrefixOnSpawnCommand");
		cfg.put("yasp.admin.locale", 				"ConfigLocaleCommand");
		
		this.sender.sendMessage("checking permissions");
		
		for (String permission : cfg.keySet()) {
			if (!Permission.hasPermission(this.sender, permission)) {
				throw new InsufficientPermissionException(permission);
			}
		}
		
		this.sender.sendMessage("perms ok");
		
		for (Map.Entry<String, String> entry : cfg.entrySet()) {
			String permission 		= entry.getKey();
			String classname		= entry.getValue();
			String fq_classname		= "org.lichtspiele.yasp.command." + classname;;
			
			this.sender.sendMessage("creating class " + classname + " from " + fq_classname);
			
			if (Permission.hasPermission(this.sender, permission)) {
				
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(fq_classname);
				@SuppressWarnings("rawtypes")
				Class[] parameters = new Class[] { JavaPlugin.class, CommandSender.class };
				
				@SuppressWarnings("rawtypes")
				Constructor constructor = null;
				try {
					constructor = clazz.getConstructor(parameters);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object o = null;
				try {
					o = constructor.newInstance(new Object[] { this.plugin, this.sender });
				} catch (IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				((ConfigCommand) o).call(messages, new String[0]);
				
				
			}
		}
	}

}
