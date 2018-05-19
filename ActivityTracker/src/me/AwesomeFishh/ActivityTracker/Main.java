package me.AwesomeFishh.ActivityTracker;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public HashMap<UUID, Long> playersMap = new HashMap<UUID, Long>();

	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin enabled!");
		loadConfig();
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ActivityTracker] Plugin disabled!");
		saveConfig();
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		if (!p.hasPlayedBefore()) {
			getConfig().set(uuid.toString(), 0);
		}

		playersMap.put(uuid, System.currentTimeMillis());
	}

	public void onPlayerQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		long timePlayed = getConfig().getLong(uuid.toString());
		long newTimePlayed = timePlayed + (System.currentTimeMillis() - playersMap.get(uuid));
		getConfig().set(uuid.toString(), newTimePlayed);
	}
}
