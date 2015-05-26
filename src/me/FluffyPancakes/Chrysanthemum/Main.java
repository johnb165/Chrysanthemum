package me.FluffyPancakes.Chrysanthemum;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
		getLogger().info("- Enabled");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("- Disabled");
		plugin = null;
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		Player player = (Player)sender;		
		if (cmd.getName().equalsIgnoreCase("chrysanthemum")) {
			if (a.length == 0) {
				player.sendMessage("&d&m------------&5&l[&d&lChrysanthemum&5&l]&c&m------------".replaceAll("&", "§"));
				player.sendMessage("   &d".replaceAll("&", "§"));
				player.sendMessage("   &d".replaceAll("&", "§"));
				player.sendMessage("   &d".replaceAll("&", "§"));
				player.sendMessage("&d&m-------------------------------------------".replaceAll("&", "§"));
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("hats")) {
			
		}
		return false;	
	}
}
