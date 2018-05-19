package me.AwesomeFishh.ActivityTracker;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.AwesomeFishh.ActivityTracker.Commands.ZilantActive;
import me.AwesomeFishh.ActivityTracker.Commands.ZilantTop;
import me.AwesomeFishh.ActivityTracker.Events.Events;

public class Main extends JavaPlugin {

	public HashMap<UUID, Long> playersMap = new HashMap<UUID, Long>();
	public String prefix = ChatColor.GREEN + "[" + ChatColor.LIGHT_PURPLE + "Zilant" + ChatColor.YELLOW + "MC" + ChatColor.GREEN + "]" + ChatColor.AQUA;

	@Override
	public void onEnable() {
		loadConfig();
		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand("zilantactive").setExecutor(new ZilantActive());
		getCommand("zilanttop").setExecutor(new ZilantTop());
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin enabled!");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin disabled!");
		saveConfig();
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public Player getKeyFromValue(HashMap<Player, Long> players, Object value) {
	    for (Player o : players.keySet()) {
	      if (players.get(o).equals(value)) {
	        return o;
	      }
	    }
	    return null;
	  }
}
