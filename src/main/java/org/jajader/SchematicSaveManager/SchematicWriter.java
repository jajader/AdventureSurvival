package org.jajader.SchematicSaveManager;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jajader.AdventureSurvival;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SchematicWriter {

    public void writeSchematics(String schematicName, Location loc1, Location loc2) {

        //TODO Exceptions 처리
        if (loc1.getWorld() != loc1.getWorld()) return;

        File schematic = new File(JavaPlugin.getPlugin(AdventureSurvival.class).getDataFolder(), "schematics/"+ schematicName +".schematic");
        CuboidRegion region = new CuboidRegion(BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ()),BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ()));
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(loc1.getWorld()), -1);



        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
        forwardExtentCopy.setCopyingEntities(true);
        try {
            Operations.complete(forwardExtentCopy);
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }

        try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(schematic))) {
            writer.write(clipboard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
