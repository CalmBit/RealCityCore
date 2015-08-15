package io.github.calmbit.realcitycore;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.block.Block;

public class GeneratorStateHandler {
	RealCityCore plugin;
	public GeneratorStateHandler(RealCityCore plugin)
	{
		this.plugin = plugin;
	}
	public  void update() 
	{
		Iterator it = plugin.generatorState.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs =(Map.Entry)it.next();
			if(plugin.generators.containsKey(pairs.getKey()) && plugin.generatorState.get(pairs.getKey()) == true)
			{
				Block block = plugin.generators.get(pairs.getKey()).getBlock();
				block.getWorld().createExplosion((double)block.getX(),(double)block.getY(),(double)block.getZ(),10f,true);
						
			}
		}
	}
}
