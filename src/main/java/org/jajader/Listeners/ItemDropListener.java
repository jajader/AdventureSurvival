package org.jajader.Listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ItemDropListener implements Listener {

    @EventHandler
    public void OnIllusionDeath(EntityDeathEvent e) {
        if (e.getEntity().getType() == EntityType.ILLUSIONER) {
            double k = Math.random();
            ItemStack enchant = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = enchant.getItemMeta();
            im.setLore(Collections.singletonList("§7불멸의 조각"));
            enchant.setItemMeta(im);
            if (k<0.2) e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), enchant);
            if (0.1 < k) {
                for (int i = 0; i<Math.random()*5; i++) {
                    ItemStack eb = new ItemStack(Material.ENCHANTED_BOOK);
                    EnchantmentStorageMeta meta = (EnchantmentStorageMeta) eb.getItemMeta();
                    for (Enchantment em : Enchantment.values()) {
                        if (Math.random()<0.1) {
                            meta.addStoredEnchant(em, new Random().nextInt(em.getMaxLevel())+1, true);
                        }
                    }
                    eb.setItemMeta(meta);
                    e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), eb);
                }
            }

        }
    }
}
