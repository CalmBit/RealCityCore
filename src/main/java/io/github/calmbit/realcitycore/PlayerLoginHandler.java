package io.github.calmbit.realcitycore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginHandler implements Listener {
	RealCityCore plugin;
	public PlayerLoginHandler(RealCityCore plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void normalLogin(PlayerLoginEvent event)
	{
		event.getPlayer().sendMessage(plugin.getConfig().getString("MOTD"));
	}
}
