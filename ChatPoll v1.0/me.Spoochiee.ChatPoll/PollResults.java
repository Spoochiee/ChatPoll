package me.Spoochiee.ChatPoll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.ChatPoll.GUI.PollResultsGUI;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PollResults implements CommandExecutor {
	
	private Main plugin;
	private Utils utils;
	private PollResultsGUI pollresultsgui;
	
	public PollResults(Main plugin, Utils utils, PollResultsGUI pollresultsgui) {
		this.plugin = plugin;
		this.utils = utils;
		this.pollresultsgui = pollresultsgui;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(label.equalsIgnoreCase("pollresults") || label.equalsIgnoreCase("pr")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rPlease run this command in game!"));
				return true;
			}
			Player player = (Player) sender;
			
			if(player.hasPermission("ChatPoll.results")) {
				if(this.plugin.activePoll == true) {
					//Open Results GUI
					this.pollresultsgui.createInv();
					player.openInventory(this.plugin.ResultsGui);
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou have opened the Poll Results GUI"));
					return true;	
				}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is no active poll, create one to perform this command!"));
					return true;
				}
			}
			else {
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou do not have permission to perform this command!"));
				return true;
			}
		}
		return false;
	}

}
