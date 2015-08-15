package io.github.calmbit.realcitycore;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockSerializable implements Serializable {
	
	 private static final long serialVersionUID = -5944092517430475805L;
	   
	    private int x, y, z;
	    private String world;
	   
	   public BlockSerializable(Block b)
	    {
	        x = b.getX();
	        y = b.getY();
	        z = b.getZ();
	        world = b.getWorld().getName();
	    }
	   
	    public Block getBlock()
	    {
	        World w = Bukkit.getWorld(world);
	        if (w == null)
	            return null;
	        Block b = w.getBlockAt(x, y, z);
	        return b;
	    }
	    
}
