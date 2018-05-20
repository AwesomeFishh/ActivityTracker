package me.AwesomeFishh.ActivityTracker;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.AwesomeFishh.ActivityTracker.Commands.ZilantActive;
import me.AwesomeFishh.ActivityTracker.Commands.ZilantTop;
import me.AwesomeFishh.ActivityTracker.Events.Events;

public class Main extends JavaPlugin {

	public HashMap<UUID, Long> playersMap = new HashMap<UUID, Long>();
	public String prefix = ChatColor.GREEN + "[" + ChatColor.LIGHT_PURPLE + "Zilant" + ChatColor.YELLOW + "MC"
			+ ChatColor.GREEN + "]" + ChatColor.AQUA;

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
		updateTimePlayed();
		saveConfig();
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void updateTimePlayed() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Long timeplayed = System.currentTimeMillis() - playersMap.get(p.getUniqueId());
			getConfig().set("players." + p.getUniqueId().toString(), (getConfig().getLong("players." + p.getUniqueId().toString()) + timeplayed));
			playersMap.put(p.getUniqueId(), System.currentTimeMillis());
			saveConfig();
		}
	}

	public String getDurationBreakdown(long millis) {
		if (millis < 0) {
			throw new IllegalArgumentException("Duration must be greater than zero!");
		}

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

		StringBuilder sb = new StringBuilder(64);
		sb.append(days);
		sb.append(" days, ");
		sb.append(hours);
		sb.append(" hours, ");
		sb.append(minutes);
		sb.append(" minutes, ");
		sb.append(seconds);
		sb.append(" seconds");

		return (sb.toString());
	}
}
