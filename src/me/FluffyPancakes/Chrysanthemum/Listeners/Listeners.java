package me.FluffyPancakes.Chrysanthemum.Listeners;

import me.FluffyPancakes.Chrysanthemum.AutoUpdater.AutoUpdater;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		AutoUpdater.onJoinCheck(player);
	}
}
