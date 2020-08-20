package me.Spoochiee.ChatPoll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.ChatPoll.GUI.PollResultsGUI;
import me.Spoochiee.ChatPoll.GUI.PollVoteGui;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PollReset implements CommandExecutor {
	
	private Main plugin;
	private PollResultsGUI pollresultsgui;
	private PollVoteGui pollvotegui;
	private Utils utils;
	
	public PollReset(Main plugin, PollResultsGUI pollresultsgui, PollVoteGui pollvotegui, Utils utils) {
		this.plugin = plugin;
		this.pollresultsgui = pollresultsgui;
		this.pollvotegui = pollvotegui;
		this.utils = utils;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("pollfinish") || label.equalsIgnoreCase("pf")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rPlease perform that command in game"));
				return true;
			}
			Player player = (Player) sender;
			
			if(player.hasPermission("ChatPoll.finish") || player.hasPermission("ChatPoll.*")) {
				if(this.plugin.activePoll == true) {
					//Tell the player they have reset the Poll
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou have reset the current poll, there is no longer any active polls!"));
					
					//Changes it so there is no active poll
					this.plugin.activePoll = false;
					
					//Clears the HashMap of the players voted
					this.plugin.playerVoted.clear();

					//Reset the poll options to nothing
					this.pollvotegui.option1 = "";
					this.pollvotegui.option2 = "";
					
					//Reset votes to 0
					this.pollresultsgui.setPollOption1(0);
					this.pollresultsgui.setPollOption2(0);
					return true;
				}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is no active poll at the moment, please create one to perform this command!"));
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
