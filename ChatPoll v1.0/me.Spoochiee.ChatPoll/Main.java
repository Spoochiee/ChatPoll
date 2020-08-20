package me.Spoochiee.ChatPoll;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.Ansi;

import me.Spoochiee.ChatPoll.GUI.PollResultsGUI;
import me.Spoochiee.ChatPoll.GUI.PollVoteGui;
import me.Spoochiee.ChatPoll.Metrics.MetricsLite;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class Main extends JavaPlugin implements Listener {
	
	public Inventory ResultsGui;
	public Inventory VoteGUI;
	
	public boolean activePoll = false;

	public Map<String, Boolean> playerVoted = new HashMap<String, Boolean>();

	
	private String enableMessage = "Thank you for using, ";
	private String plugin = "ChatPoll";
	private String versionMessage = "You are running, ";
	private String version = "Version: 1.0";
	private String creator = "Spoochiee!";

	
	public PollVoteGui pollvotegui;
	public PollResultsGUI pollresultsgui;
	public PollCreate pollcreate;
	private Utils utils;
		
	@Override
	public void onEnable() {
		//Initialise Classes
		pollcreate = new PollCreate(this, pollvotegui, utils, pollresultsgui);
		pollvotegui = new PollVoteGui(this, pollresultsgui, utils);
		pollresultsgui = new PollResultsGUI(this);
		utils = new Utils();
		
		//StartUp Message
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.GREEN).boldOff().a(enableMessage).fg(Ansi.Color.MAGENTA).a(plugin).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a(versionMessage).fg(Ansi.Color.CYAN).a(version).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a("Plugin created by: ").fg(Ansi.Color.CYAN).a(creator).fg(Ansi.Color.WHITE).toString());
		
		
		//Create a Poll Command
		this.getCommand("pollcreate").setExecutor(new PollCreate(this, pollvotegui, utils, pollresultsgui));
		//Vote on current Poll Command
		this.getCommand("pollvote").setExecutor(new PollVote(this, utils, pollvotegui));
		//Reset Poll Command
		this.getCommand("pollfinish").setExecutor(new PollReset(this, pollresultsgui, pollvotegui, utils));
		//Open Results GUI
		this.getCommand("pollresults").setExecutor(new PollResults(this, utils, pollresultsgui));
		
		//Register Listeners
		this.getServer().getPluginManager().registerEvents(new PollVoteGui(this, pollresultsgui, utils), this);
		this.getServer().getPluginManager().registerEvents(new PollResultsGUI(this), this);
		
		//Enable bStats
		int pluginId = 8495;
		@SuppressWarnings("unused")
		MetricsLite metrics = new MetricsLite(this, pluginId);
	}
	
	@Override
	public void onDisable() {
		//Disable Message
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.GREEN).boldOff().a(enableMessage).fg(Ansi.Color.MAGENTA).a(plugin).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a(versionMessage).fg(Ansi.Color.CYAN).a(version).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a("Plugin created by: ").fg(Ansi.Color.CYAN).a(creator).fg(Ansi.Color.WHITE).toString());
		
	}
}
