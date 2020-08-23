package me.Spoochiee.ChatPoll.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandTabCompleter implements TabCompleter {
	
	List<String> arguments = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("chatpoll")) {
			if(arguments.isEmpty()) {
				arguments.add("reload");
			}
			
			List<String> result = new ArrayList<String>();
			if(args.length == 1) {
				for(String a : arguments) {
					if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			}
		}
		return null;
	}
}
