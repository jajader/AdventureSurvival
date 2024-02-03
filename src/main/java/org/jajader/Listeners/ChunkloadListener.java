package org.jajader.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jajader.SchematicSaveManager.SchematicLoader;

import java.util.HashMap;

public class ChunkloadListener implements Listener {

    public static HashMap<String, Integer> KEY = new HashMap<>();

    public ChunkloadListener() {
        KEY.put("temple", 1200);
        KEY.put("pg", 2400);
        KEY.put("Junsung2", 3600);
        KEY.put("Junsung", 4800);

    }

    @EventHandler
    public void Chunkload(ChunkLoadEvent e) {
        if (e.getChunk().getInhabitedTime() == 0) {
            for (String k : KEY.keySet()) {
                    if (Math.abs(e.getChunk().getChunkKey() % 5000) == KEY.get(k)) {
                        int max = getHighestBlock(e.getChunk());
                        SchematicLoader sl = new SchematicLoader();
                        sl.loadSchematics(k, e.getChunk().getBlock(0,max,0).getLocation());
                        e.getChunk().setInhabitedTime(1);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(k + " "+ e.getChunk().getBlock(0,max,0).getLocation());
                        }
                    }
            }
        }
    }

    public int getHighestBlock(Chunk c) {
        int max = -64;
        for (int i = -64; i<320; i++) {
            if (c.getBlock(7,i,7).getType() != Material.AIR) {
                max = i;
            }
        }

        return max;

    }

}
