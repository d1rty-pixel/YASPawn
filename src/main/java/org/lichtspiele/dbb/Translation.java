package org.lichtspiele.dbb;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Translation {

	String default_language	= "en_US";
	
	String locale_path		= "locale";
	
	String language			= null;
	
	JavaPlugin plugin 		= null;
		
	YamlConfiguration data	= null;
	
	public Translation(JavaPlugin plugin, String language) throws TranslationFileNotFoundException {
		this.plugin = plugin;
		this.loadTranslation(language);
	}
	
	private void loadTranslation(String language) throws TranslationFileNotFoundException {		
		String path = this.plugin.getDataFolder().getAbsolutePath() +
			java.lang.System.getProperty("file.separator") + 
			this.locale_path + 
			java.lang.System.getProperty("file.separator");


		YamlConfiguration yc = null;
		String[] language_files = { language, this.default_language };
		
		for ( String file : language_files ) {
			File yc_file = new File(path, file + ".yml");
			System.out.println("[DirtySpawn] Loading locale file "+ yc_file.getName());
			if (!yc_file.exists()) continue;
			
			yc = YamlConfiguration.loadConfiguration(yc_file);
			if (yc != null) {
				this.language = file;
				break;
			}
		}

		if (yc == null)
			throw new TranslationFileNotFoundException("Could not find a suitable locale file");
		
		this.data = yc;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public YamlConfiguration getTranslation() {
		return this.data;
	}
	
	public String getTranslation(String path) {
		String s = this.data.getString(path);
		if (s == null) return "Missing translation for " + path;
		s = ChatColor.translateAlternateColorCodes('&', s);
		return s;
	}
	
	public String getTranslation(String path, String[] args) {
		String s = this.data.getString(path);
		
		 // check even		
    	if (args.length % 2 == 1)
            return s;
        
        for(int i = 0; i < args.length; i++){
            s = s.replaceAll("%" + args[i] + "%", args[i+1]);
            i++;
        }
		s = ChatColor.translateAlternateColorCodes('&', s);        
        return s;
	}	

}
