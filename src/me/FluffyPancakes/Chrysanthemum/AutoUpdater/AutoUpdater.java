package me.FluffyPancakes.Chrysanthemum.AutoUpdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
}
