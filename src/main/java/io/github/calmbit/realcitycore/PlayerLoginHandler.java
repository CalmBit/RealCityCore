package io.github.calmbit.realcitycore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginHandler implements Listener {
	RealCityCore plugin;
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event, RealCityCore plugin)
	{
		this.plugin = plugin;
		event.getPlayer().sendMessage(plugin.getConfig().getString("MOTD"));
	}
}
