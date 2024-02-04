package org.jajader.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jajader.SchematicSaveManager.SchematicLoader;
import org.jajader.SchematicSaveManager.SchematicWriter;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SchematicCommands implements CommandExecutor {

    public static HashMap<Player, Location> pos1 = new HashMap<>();
    public static HashMap<Player, Location> pos2 = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (p.isOp()) {
                if (strings == null) {
                    Component component = Component.text("올바른 명령어 사용법이 아닙니다.").color(TextColor.color(255, 0, 0));
                    p.sendMessage(component);

                    return false;
                }

                if (command.getName().equals("save")) {
                    if (pos1.get(p) == null) {
                        Component component = Component.text("첫 번째 위치가 지정되지 않았습니다.").color(TextColor.color(255, 0, 0));
                        p.sendMessage(component);
                        return false;
                    }

                    if (pos2.get(p) == null) {
                        Component component = Component.text("두 번째 위치가 지정되지 않았습니다.").color(TextColor.color(255, 0, 0));
                        p.sendMessage(component);
                        return false;
                    }


                    SchematicWriter sw = new SchematicWriter();
                    sw.writeSchematics(strings[0], pos1.get(p), pos2.get(p));

                    p.sendMessage("구조물 "+strings[0]+ "(이)가 정상적으로 저장되었습니다.");
                }

                if (command.getName().equals("load")) {
                    SchematicLoader sl = new SchematicLoader();
                    sl.loadSchematics(strings[0], p.getLocation());
                    p.sendMessage("구조물 "+strings[0]+ "(이)가 정상적으로 로드되었습니다.");

                }

                if (command.getName().equals("pos1")) {
                    pos1.put(p, p.getLocation());
                    Component component = Component.text("첫 번째 위치가 지정되었습니다.").color(TextColor.color(200, 50, 200));
                    p.sendMessage(component);

                }

                if (command.getName().equals("pos2")) {
                    pos2.put(p, p.getLocation());
                    Component component = Component.text("두 번째 위치가 지정되었습니다.").color(TextColor.color(200, 50, 200));
                    p.sendMessage(component);

                }

                if (command.getName().equals("test")) {
                    Location l = p.getLocation();
                    Illusioner il = (Illusioner) l.getWorld().spawnEntity(l, EntityType.ILLUSIONER);
                    il.setMaxHealth(300);
                    il.setHealth(250);

                }


            } else {
                Component component = Component.text("명령어를 사용할 권한이 없습니다.").color(TextColor.color(255, 0, 0));
                p.sendMessage(component);
            }

        }
        return true;
    }


}
