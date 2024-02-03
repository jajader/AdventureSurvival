package org.jajader.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jajader.AdventureSurvival;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AnvilListener implements Listener {

    final static Material[] table = {
    Material.WOODEN_AXE,Material.WOODEN_HOE,Material.WOODEN_SWORD,Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
    Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SWORD, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
    Material.IRON_AXE, Material.IRON_HOE, Material.IRON_SWORD, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
    Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_SWORD, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
    Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,

    Material.CHAINMAIL_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET,
    Material.CHAINMAIL_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.NETHERITE_CHESTPLATE,
    Material.CHAINMAIL_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS, Material.NETHERITE_LEGGINGS,
    Material.CHAINMAIL_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.LEATHER_BOOTS, Material.NETHERITE_BOOTS,
    Material.SHEARS, Material.FISHING_ROD, Material.WARPED_FUNGUS_ON_A_STICK, Material.CARROT_ON_A_STICK,
    Material.FLINT_AND_STEEL, Material.BOW, Material.TRIDENT, Material.ELYTRA, Material.SHIELD
    };



    @EventHandler
    public void onAddinanvil(PrepareAnvilEvent e){
            AnvilInventory ai = e.getInventory();
            ItemStack firstItem = ai.getFirstItem();
            ItemStack secondItem = ai.getSecondItem();
            if (firstItem == null) return;
            if (secondItem == null) return;

            ItemStack enchant = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = enchant.getItemMeta();
            im.setLore(Collections.singletonList("§7불멸의 조각"));
            enchant.setItemMeta(im);

            if(Arrays.asList(table).contains(firstItem.getType()) && secondItem.isSimilar(enchant)){
                ItemStack clone = firstItem.clone();
                ItemMeta imm = clone.getItemMeta();
                imm.setUnbreakable(true);
                clone.setItemMeta(imm);
                e.setResult(clone);

            }


    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getType() == InventoryType.ANVIL) {
            AnvilInventory ai = (AnvilInventory) e.getInventory();
            ItemStack enchant = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = enchant.getItemMeta();
            im.setLore(Collections.singletonList("§7불멸의 조각"));
            enchant.setItemMeta(im);
            if (ai.getSecondItem() == null) return;
            if (ai.getSecondItem().isSimilar(enchant) && ai.getResult() != null) {
                if (e.getSlot() != 2) return;
                if (e.getWhoClicked().getItemOnCursor().getType() != Material.AIR) return;
                e.getWhoClicked().setItemOnCursor(ai.getResult().clone());
                ai.setFirstItem(null);
                ai.setSecondItem(null);
                e.getWhoClicked().getLocation().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 0.3f, 1f);
            }
        }
    }

}
