package me.FluffyPancakes.Chrysanthemum;

import java.io.IOException;

import org.mcstats.Metrics;

import me.FluffyPancakes.Chrysanthemum.Config;
import me.FluffyPancakes.Chrysanthemum.AutoUpdater.AutoUpdater;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static Main plugin;
    public static Economy econ = null;
	
	@Override
	public void onEnable() {
		plugin = this;
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
		getLogger().info("- Enabled");
		Config.saveDefaultConfig();
		try {
			getLogger().info("Latest Version: " + AutoUpdater.checkUpdate());
			if (Config.getConfig().getString("Config.Version") == AutoUpdater.checkUpdate()) {
				getLogger().info("You Are Using The Latest Version :D");
			}
		} catch (IOException e1) {
			getLogger().info("Latest Version:" + "Unknown");
		}
		try {  
			Metrics metrics = new Metrics(this);
		    metrics.start();
		    getLogger().info("Attemting To Connect To Metrics");
		} catch (IOException e) {
			getLogger().info("Failed To Connect To Metrics D:");
			
		}
		if (!setupEconomy() ) {
            getLogger().info("No Vault Dependency Found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
	}
	
	@Override
	public void onDisable() {
		plugin = null;
		getLogger().info("- Disabled");
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		Player player = (Player)sender;		
		if (cmd.getName().equalsIgnoreCase("chrysanthemum")) {
			if (a.length == 0) {
				player.sendMessage("&d&m-------------&5&l[&d&lChrysanthemum&5&l]&d&m-------------".replaceAll("&", "§"));
				player.sendMessage("&e/Chrysanthemum help - displays help information&d".replaceAll("&", "§"));
				player.sendMessage("&e/Chrysanthemum info - displays plugin information&d".replaceAll("&", "§"));
				player.sendMessage("&e/Chrysanthemum reload - reloads the plugin&d".replaceAll("&", "§"));
				player.sendMessage("&d&m-----------------------------------------".replaceAll("&", "§"));
				return true;
			}
			if (a.length == 1) {
				if (a[0].equalsIgnoreCase("help") && player.hasPermission("chrysanthemum.help")) {
					
				}
				if (a[0].equalsIgnoreCase("info") && player.hasPermission("chrysanthemum.info")) {
					
				}
				if (a[0].equalsIgnoreCase("reload") && player.hasPermission("chrysanthemum.reload")) {
					
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("hats")) {
			
		}
		return false;	
	}
	

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
