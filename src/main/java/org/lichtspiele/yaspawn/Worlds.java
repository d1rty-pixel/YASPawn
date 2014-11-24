package org.lichtspiele.yaspawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Worlds {

	private List<Object> disabled_worlds;
	
	private List<World> bukkit_worlds;
	
	public Worlds(List<Object> disabled_worlds) {
		this.bukkit_worlds = Bukkit.getServer().getWorlds();
		this.disabled_worlds = disabled_worlds;
	}
	
	public boolean worldExists(String world) {
		for (World w : this.bukkit_worlds) {
			if (w.getName().equals(world)) return true;
		}
		return false;
	}
	
	public boolean isWorldDisabled(String world) {
		return this.disabled_worlds.contains(world);		
	}
	
	public boolean disableWorld(String world) {
		if (this.isWorldDisabled(world))
			return false;
		
		List<Object> dw = new ArrayList<Object>(this.getDisabledWorlds());
		dw.add(world);
		
		this.disabled_worlds = dw;
		return true;		
	}
	
	public boolean isWorldEnabled(String world) {
		return !this.isWorldDisabled(world);
	}
	
	public boolean enableWorld(String world) {
		if (this.isWorldEnabled(world))
			return false;
		
		List<Object> dw = new ArrayList<Object>();
		
		for (Object w : this.disabled_worlds) {
			if (w.toString().equals(world)) continue;
			dw.add(w);
		}
		
		this.disabled_worlds = dw;
		return true;		
	}	
	
	public List<Object> getDisabledWorlds() {
		return this.disabled_worlds;
	}
}
