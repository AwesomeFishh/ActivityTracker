package me.AwesomeFishh.ActivityTracker.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AwesomeFishh.ActivityTracker.Main;

public class ZilantActive implements CommandExecutor {

	private Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

		// Zilantactive <player>: Gives the time played for <player> in x days, x hours,
		// x minutes, x seconds, no further explanation required (simple AF)
		if (cmd.getName().equalsIgnoreCase("zilantactive")) {
			if (sender instanceof Player) {
				plugin.updateTimePlayed();
				Player player = (Player) sender;
				if (args.length == 1) {
					if (Bukkit.getPlayer(args[0]) != null) {
						String input = args[0];						
						UUID uuid = Bukkit.getPlayer(input).getUniqueId();
						Long timeplayed = plugin.getConfig().getLong("players." + uuid.toString());
						String string = plugin.getDurationBreakdown(timeplayed);
						player.sendMessage(plugin.prefix + " " + input + " has played for: " + string);
					} else {
						player.sendMessage(plugin.prefix + " That player does not exist or has never played on this server before!");
					}
				} else {
					player.sendMessage(plugin.prefix + " Incorrect usage: /zilantactive <player>");
				}
			} else {
				sender.sendMessage(plugin.prefix + " Only players can use this command!");
			}
		}
		return false;
	}

}
