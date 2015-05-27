package me.FluffyPancakes.Chrysanthemum.AutoUpdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.FluffyPancakes.Chrysanthemum.Config;

import org.bukkit.entity.Player;

public class AutoUpdater {
	
	public static String checkUpdate() throws IOException {
        URL url = new URL("http://pancake.space/api/plugins/Chrysanthemum/Chrysanthemum_Version_Checker.php");

        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        while ((line = br.readLine()) != null) {
            return line;
        }
		return line;
    }
	
	public static void onJoinCheck(Player player) {
		if (player.isOp()) {
			try {
				String latest = AutoUpdater.checkUpdate();
				String version = Config.getConfig().getString("Config.Version");
				if (!version.equals(latest)) {
					player.sendMessage("&5&l[&d&lChrysanthemum&5&l] &eYou are running version &6&l".replaceAll("&", "§") + version + "&e of this plugin. The latest version is &6&l".replaceAll("&", "§") + latest + "&e. Type &6&l/Chrysanthemum update &eto update the plugin.".replaceAll("&", "§"));
				}
				if (version.equals(latest)) {
					player.sendMessage("&5&l[&d&lChrysanthemum&5&l] &eYou are running version &6&l".replaceAll("&", "§") + version + "&e of this plugin. This is the latest version!".replaceAll("&", "§"));
				}
			} catch (IOException e1) {
				
			}
		}
	}
}
