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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

		if (cmd.getName().equalsIgnoreCase("zilantactive")) {
			if (sender instanceof Player) {
				plugin.updateTimePlayed();
				Player player = (Player) sender;
				if (args.length == 1) {
					String input = args[0];
					UUID uuid = Bukkit.getPlayer(input).getUniqueId();
					Long timeplayed = (Long) plugin.getConfig().get(uuid.toString());
					String string = plugin.getDurationBreakdown(timeplayed);
					player.sendMessage(plugin.prefix + " " + input + " has played for: " + string);
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
