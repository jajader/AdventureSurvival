package org.jajader;


import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jajader.Commands.SchematicCommands;
import org.jajader.Listeners.AnvilListener;
import org.jajader.Listeners.ChunkloadListener;

public final class AdventureSurvival extends JavaPlugin implements CommandExecutor, Listener {



    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("save").setExecutor(new SchematicCommands());
        this.getCommand("load").setExecutor(new SchematicCommands());
        this.getCommand("pos1").setExecutor(new SchematicCommands());
        this.getCommand("pos2").setExecutor(new SchematicCommands());
        this.getCommand("test").setExecutor(new SchematicCommands());

        getServer().getPluginManager().registerEvents(new ChunkloadListener(), this);
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }











}