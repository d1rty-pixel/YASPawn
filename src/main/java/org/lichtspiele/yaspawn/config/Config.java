package org.lichtspiele.yaspawn.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.lichtspiele.dbb.BukkitPlugin;
import org.lichtspiele.dbb.config.CustomConfigurationFile;

public class Config extends CustomConfigurationFile {
	public Config(BukkitPlugin plugin) throws IOException {
		super(plugin, "config.yml", Config.data());
	}
	
	private static HashMap<String,Object> data() {
		HashMap<String,Object> data		= new HashMap<String,Object>();
		
		ArrayList<String> dw = new ArrayList<String>();
		dw.add("world_nether");
		dw.add("world_the_end");
		
		data.put("single-server-spawn",						"true");
		data.put("default-world",							"world");
		data.put("disabled-worlds",							dw);
		data.put("say-world-name", 							"false");
		data.put("prefix-on-spawn",							"false");
		data.put("locale",									"en_US");
		data.put("use-metrics",								"true");
		
		return data;
	}
}
