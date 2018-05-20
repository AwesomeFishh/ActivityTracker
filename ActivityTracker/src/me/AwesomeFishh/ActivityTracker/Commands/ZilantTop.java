package me.AwesomeFishh.ActivityTracker.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AwesomeFishh.ActivityTracker.Main;

public class ZilantTop implements CommandExecutor {

	private Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

		if (cmd.getName().equalsIgnoreCase("zilanttop")) {

			// Get top20 long values (time played) and sort from high to low
			if (sender instanceof Player) {
				Player player = (Player) sender;
				plugin.updateTimePlayed();
				if (args.length == 0) {
					
				} else {
					player.sendMessage(plugin.prefix + " Too many arguments! /zilanttop");
				}
			} else {
				sender.sendMessage(plugin.prefix + " Only players can use this command!");
			}
		}

		return false;
	}

}