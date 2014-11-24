package org.lichtspiele.yaspawn;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.lichtspiele.dbb.BukkitPlugin;
import org.lichtspiele.dbb.command.*;
import org.lichtspiele.dbb.exception.*;
import org.lichtspiele.dbb.registry.CustomConfigurationRegistry;
import org.lichtspiele.yaspawn.command.*;
import org.lichtspiele.yaspawn.config.Config;
import org.lichtspiele.yaspawn.config.locale.de_DE;
import org.lichtspiele.yaspawn.config.locale.en_US;

public class YASPawnPlugin extends BukkitPlugin {
		
	protected Messages messages				= null;
		
	public YASPawnPlugin() {
		super();
	}
	
	public String version() {
		return "1.1 (" + super.version() + ")";
	}
	
	@SuppressWarnings("unchecked")
	public void onEnable() {
		
		this.enableMetrics();		
		this.enable();
		
		Worlds worlds = new Worlds((List<Object>) this.getConfig().getList("disabled-worlds"));
		List<?> dw = worlds.getDisabledWorlds();
						
		this.logger.info(ChatColor.stripColor(this.getMessagePrefix()) + 
			"Found " + dw.size() + " disabled Worlds: " + StringUtils.join(dw, ","));		
	}

	public void enable() {
		try {
			this.loadCustomConfiguration();
			this.messages = new Messages(this);		
		} catch (IOException e) {
			this.disable(e);
		}
	}
	
	private void loadCustomConfiguration() throws IOException {
		CustomConfigurationRegistry.register(new Config(this));
		CustomConfigurationRegistry.register(new en_US(this));
		CustomConfigurationRegistry.register(new de_DE(this));
	}
		
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
		
		if (command.getName().equalsIgnoreCase("spawn")) {
			try {
				try {
					if (args.length == 0) {
						SpawnCommand cmd = new SpawnCommand(this, sender);
						return cmd.call(this.messages, args);
					
					} else if (args.length == 1) {					
						if (args[0].equalsIgnoreCase("help")) {
							HelpCommand cmd = new HelpCommand(this, sender);
							return cmd.call(this.messages, args);				
					
						} else if (args[0].equalsIgnoreCase("config")) {
							ConfigCommand cmd = new ConfigCommand(this, sender);
							return cmd.call(this.messages, args);
						
						} else if (args[0].equalsIgnoreCase("worldlist")) {
							WorldListCommand cmd = new WorldListCommand(this, sender);
							return cmd.call(this.messages, args);

						} else if (args[0].equalsIgnoreCase("reload")) {
							ReloadCommand cmd = new ReloadCommand(this, sender);
							this.enable();
							return cmd.call(this.messages, args);
									
						} else if (args[0].equalsIgnoreCase("version")) {
							VersionCommand cmd = new VersionCommand(this, sender);
							return cmd.call(this.messages, new String[] { this.version() } );
							
						} else {
							SpawnWorldCommand cmd = new SpawnWorldCommand(this, sender);
							return cmd.call(this.messages, args);
						}
					
					} else if (args.length >= 2 && args.length <= 4) {
					
						if (args[0].equalsIgnoreCase("config")) {
							if (args[1].equalsIgnoreCase("singleserverspawn")) {
								ConfigSingleServerSpawnCommand cmd = new ConfigSingleServerSpawnCommand(this, sender);
								if (args.length == 2) 
									return cmd.call(this.messages, new String[0]);
								else 
									return cmd.call(this.messages, new String[] { args[2].toLowerCase()} );
							
							} else if (args[1].equalsIgnoreCase("defaultworld")) {
								ConfigDefaultWorldCommand cmd = new ConfigDefaultWorldCommand(this, sender);
								if (args.length == 2)
									return cmd.call(this.messages, new String[0]);
								else 
									return cmd.call(this.messages, new String[] { args[2].toLowerCase() } );
							
							} else if (args[1].equalsIgnoreCase("sayworldname")) {								
								ConfigSayWorldNameCommand cmd = new ConfigSayWorldNameCommand(this, sender);
								if (args.length == 2) 
									return cmd.call(this.messages, new String[0]);
								else 
									return cmd.call(this.messages, new String[] { args[2].toLowerCase() } );						
							
							} else if (args[1].equalsIgnoreCase("prefixonspawn")) {
								ConfigPrefixOnSpawnCommand cmd = new ConfigPrefixOnSpawnCommand(this, sender);
								if (args.length == 2) 
									return cmd.call(this.messages, new String[0]);
								else 
									return cmd.call(this.messages, new String[] { args[2].toLowerCase() } );						
							
							} else if (args[1].equalsIgnoreCase("locale")) {
								ConfigLocaleCommand cmd = new ConfigLocaleCommand(this, sender);
								if (args.length == 2) 
									return cmd.call(this.messages, new String[0]);
								else 
									return cmd.call(this.messages, new String[] { args[2] } );													
						
							} else if (args[1].equalsIgnoreCase("world")) {
								if (args.length == 4) {
									if (args[3].equalsIgnoreCase("enable")) {
										ConfigEnableWorldCommand cmd = new ConfigEnableWorldCommand(this, sender);
										return cmd.call(this.messages, new String[] { args[2] } );
									
									} else if (args[3].equalsIgnoreCase("disable")) {
										ConfigDisableWorldCommand cmd = new ConfigDisableWorldCommand(this, sender);
										return cmd.call(this.messages, new String[] { args[2]} );
									
									} // enable/disable
								} // args.length == 4
							} // world command
						} // config command
					} // args length

					throw new InvalidCommandException();

				} catch (InvalidCommandException e) { 
					this.messages.invalidCommand(sender, StringUtils.join(args, " "));
				} catch (CommandSenderIsNotPlayerException e) {
					this.messages.mustBePlayer(sender);
				} catch (InsufficientPermissionException e) {
					this.messages.insufficientPermission(sender, e.getMessage());
				} catch (TranslationFileNotFoundException e) {
					this.messages.missingTranslationFile(sender, e.getMessage());
				}
			
			} catch (TranslationNotFoundException e) {
				this.messages.missingTranslation(sender, e.getMessage());				
			}
		}
		return true;
	}

}

