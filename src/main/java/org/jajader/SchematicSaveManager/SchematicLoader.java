package org.jajader.SchematicSaveManager;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jajader.AdventureSurvival;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SchematicLoader {



    public void loadSchematics(String schematicName, Location pasteLoc) {

        File schematic = new File(JavaPlugin.getPlugin(AdventureSurvival.class).getDataFolder() , "schematics/"+ schematicName +".schematic");


            ClipboardFormat format = ClipboardFormats.findByFile(schematic);
            try (ClipboardReader reader = format.getReader(new FileInputStream(schematic))) {
                Clipboard clipboard = reader.read();
                try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(pasteLoc.getWorld()), -1)) {
                    Operation operation = new ClipboardHolder(clipboard)
                            .createPaste(editSession)
                            .to(BlockVector3.at(pasteLoc.getX(),pasteLoc.getY(),pasteLoc.getZ()))
                            .ignoreAirBlocks(true)
                            .build();
                    Operations.complete(operation);
                } catch (WorldEditException e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



    }


}
