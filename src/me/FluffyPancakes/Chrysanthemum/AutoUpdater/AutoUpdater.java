package me.FluffyPancakes.Chrysanthemum.AutoUpdater;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
	
	public static void downloadUpdate() throws IOException {
		URL url = new URL("http://pancake.space/api/plugins/Chrysanthemum/Chrysanthemum-" + checkUpdate() +".jar");

		InputStream inStream = url.openStream();
		BufferedInputStream bufIn = new BufferedInputStream(inStream);     
		
		File fileWrite = new File("plugins\\Chrysanthemum\\Chrysanthemum-" + checkUpdate() +".jar");			
        OutputStream out = new FileOutputStream(fileWrite);
        BufferedOutputStream bufOut = new BufferedOutputStream(out);                             
            
        byte buffer[] = new byte[4096];
            
        while (true) {
          	
        	int nRead = bufIn.read(buffer, 0, buffer.length);       

        	if (nRead <= 0)          		
        		break;
              
        	bufOut.write(buffer, 0, nRead);       
        }                                   
        bufOut.flush();
        out.close();
        inStream.close();
	}
}
