package org.lichtspiele.yasp;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.*;
import org.lichtspiele.dbb.exception.*;
import org.lichtspiele.yasp.command.*;
import org.mcstats.Metrics;

public class YASP extends JavaPlugin {
		
	protected Messages messages				= null;
		
	protected ConsoleCommandSender console	= null;
	
	@SuppressWarnings("unchecked")
	public void onEnable() {

		this.console = Bukkit.getServer().getConsoleSender();
		this.saveDefaultConfig();
		
		try {
			this.load();
		} catch (TranslationFileNotFoundException e) {
			this.disable(e);
		}
			
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
			this.console.sendMessage(this.messages.getMessagePrefix() + ChatColor.RED + "Failed to submit metrics");
		}
	
		Worlds worlds = new Worlds((List<Object>) this.getConfig().getList("disabled-worlds"));
		List<?> dw = worlds.getDisabledWorlds();
		this.console.sendMessage(this.messages.getMessagePrefix() + 
			"Found " + dw.size() + " disabled Worlds: " + ChatColor.GOLD + StringUtils.join(dw, ","));		
	}

	public void disable(Exception e) {
		this.console.sendMessage(this.messages.getMessagePrefix() + "Unrecoverable error: " + e.getMessage());
		this.console.sendMessage(this.messages.getMessagePrefix() + "Disabling plugin");
        this.getPluginLoader().disablePlugin(this);	
	}

	public void disable(Exception e, CommandSender sender) {
		sender.sendMessage("[YASP] Unrecoverable error. Disabling plugin");
		this.disable(e);
	}
	
	private void load() throws TranslationFileNotFoundException {
		this.messages 			= new Messages(this);
	}
		
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("Your command " + command.getName());
		sender.sendMessage("# " + args.length + " args: " + StringUtils.join(args, " "));
		
		if (command.getName().equalsIgnoreCase("spawn")) {
			try {
			
				try {
			
					if (args.length == 0) {
						sender.sendMessage("performing spawn");
						SpawnCommand cmd = new SpawnCommand(this, sender);
						cmd.call(this.messages, args);
					
					} else if (args.length == 1) {
						if (args[0].equals("help")) {
							HelpCommand cmd = new HelpCommand(this, sender);
							cmd.call(this.messages, args);				
					
						} else if (args[0].equalsIgnoreCase("config")) {
							ConfigCommand cmd = new ConfigCommand(this, sender);
							try {
								cmd.call(this.messages, args);
							} catch (InstantiationException
									| IllegalAccessException
									| ClassNotFoundException e) {
								throw new CorruptedPluginException(e);
							}
						
						} else if (args[0].equalsIgnoreCase("worldlist")) {
							WorldListCommand cmd = new WorldListCommand(this, sender);
							cmd.call(this.messages, args);

						} else if (args[0].equalsIgnoreCase("reload")) {
							ReloadCommand cmd = new ReloadCommand(this, sender);
							this.load();
							cmd.call(this.messages, args);
									
						} else {
							sender.sendMessage("performing spawn world");
							SpawnWorldCommand cmd = new SpawnWorldCommand(this, sender);
							cmd.call(this.messages, args);
						}
					
					} else if (args.length >= 2 && args.length <= 4) {
					
						if (args[0].equalsIgnoreCase("config")) {
							if (args[1].equalsIgnoreCase("singleserverspawn")) {
								ConfigSingleServerSpawnCommand cmd = new ConfigSingleServerSpawnCommand(this, sender);
								if (args.length == 2) 
									cmd.call(this.messages, new String[0]);
								else 
									cmd.call(this.messages, new String[] { args[2].toLowerCase()} );
							
							} else if (args[1].equalsIgnoreCase("defaultworld")) {
								ConfigDefaultWorldCommand cmd = new ConfigDefaultWorldCommand(this, sender);
								if (args.length == 2)
									cmd.call(this.messages, new String[0]);
								else 
									cmd.call(this.messages, new String[] { args[2].toLowerCase() } );
							
							} else if (args[1].equalsIgnoreCase("sayworldname")) {								
								ConfigSayWorldNameCommand cmd = new ConfigSayWorldNameCommand(this, sender);
								if (args.length == 2) 
									cmd.call(this.messages, new String[0]);
								else 
									cmd.call(this.messages, new String[] { args[2].toLowerCase() } );						
							
							} else if (args[1].equalsIgnoreCase("prefixonspawn")) {
								ConfigPrefixOnSpawnCommand cmd = new ConfigPrefixOnSpawnCommand(this, sender);
								if (args.length == 2) 
									cmd.call(this.messages, new String[0]);
								else 
									cmd.call(this.messages, new String[] { args[2].toLowerCase() } );						
							
							} else if (args[1].equalsIgnoreCase("locale")) {
								ConfigLocaleCommand cmd = new ConfigLocaleCommand(this, sender);
								if (args.length == 2) 
									cmd.call(this.messages, new String[0]);
								else 
									cmd.call(this.messages, new String[] { args[2] } );													
						
							} else if (args[1].equalsIgnoreCase("world")) {
								if (args.length == 4) {
									if (args[3].equalsIgnoreCase("enable")) {
										ConfigEnableWorldCommand cmd = new ConfigEnableWorldCommand(this, sender);
										cmd.call(this.messages, new String[] { args[2] } );
									
									} else if (args[3].equalsIgnoreCase("disable")) {
										ConfigDisableWorldCommand cmd = new ConfigDisableWorldCommand(this, sender);
										cmd.call(this.messages, new String[] { args[2]} );
									}
								}
							}
						}

					} else {
						throw new InvalidCommandException();
					}

				} catch (InvalidCommandException e) { 
					this.messages.invalidCommand(sender, StringUtils.join(args, " "));
				} catch (CommandSenderIsNotPlayerException e) {
					this.messages.mustBePlayer(sender);
				} catch (InsufficientPermissionException e) {
					this.messages.insufficientPermission(sender, e.getMessage());
				} catch (TranslationFileNotFoundException e) {
					this.messages.missingTranslationFile(sender, e.getMessage());
				} catch (CorruptedPluginException e) {
					this.disable(e, sender);
				}
			
			} catch (TranslationNotFoundException e) {
				this.messages.missingTranslation(sender, e.getMessage());				
			}
		}
		return true;
	}

}

