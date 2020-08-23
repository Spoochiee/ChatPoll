package me.Spoochiee.ChatPoll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.ChatPoll.GUI.PollVoteGui;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PollVote implements CommandExecutor {
	
	private Main plugin;
	private Utils utils;
	private PollVoteGui pollvotegui;
	
	public PollVote(Main plugin, Utils utils, PollVoteGui pollvotegui) {
		this.plugin = plugin;
		this.utils = utils;
		this.pollvotegui = pollvotegui;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(label.equalsIgnoreCase("pollvote") || label.equalsIgnoreCase("pv")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rPerform this Command in game!"));
				return true;
			}
			Player player = (Player) sender;
			
			if(this.plugin.activePoll == true) {
				if(player.hasPermission("ChatPoll.vote") || player.hasPermission("ChatPoll.*")) {
					if(!this.plugin.playerVoted.containsKey(player.getName())) {
						//Open Poll GUI
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou can now vote!"));
						
						//Open the GUI
						player.openInventory(this.plugin.VoteGUI);
						
						//Tell the player the two options
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&r&bOption 1: " + this.pollvotegui.option1));
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&r&6Option 2: " + this.pollvotegui.option2));
						return true;	
					}
					else {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou have already voted on this poll!"));
						return true;
						}
					}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou do not have permission to vote on this poll!"));
					return true;
					}
				}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is no active poll, create one to be able to vote!"));
					return true;
			}
		}
		return false;
	}

}
