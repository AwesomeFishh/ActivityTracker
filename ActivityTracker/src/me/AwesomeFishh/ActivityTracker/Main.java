package me.AwesomeFishh.ActivityTracker;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin enabled!");
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin disabled!");
	}
	
}
