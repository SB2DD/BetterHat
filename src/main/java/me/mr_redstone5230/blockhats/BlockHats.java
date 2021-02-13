package me.mr_redstone5230.blockhats;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockHats extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public static void onInvInteract(InventoryClickEvent event) {
        //System.out.println("ff");
        if (event.getCursor().getType().equals(Material.AIR))
            return;
        if (!event.getSlotType().equals(InventoryType.SlotType.ARMOR))
            return;
        if (event.getRawSlot() != 5)
            return;

        ItemStack old = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        if(event.getClick().equals(ClickType.RIGHT)) {
            event.setCancelled(true);

            ItemStack old2 = old.clone();
            old2.setAmount(1);
            ItemStack cursor2 = cursor.clone();
            cursor2.setAmount(1);

            if (!old2.equals(cursor2)) {
                event.setCancelled(true);
                event.setCurrentItem(cursor);
                event.getWhoClicked().setItemOnCursor(old);
                return;
            }

            //same item
            int amount = event.getCursor().getAmount();
            cursor.setAmount(amount - 1);
            int amount2 = event.getCurrentItem().getAmount();
            old.setAmount(amount2 + 1);
            return;

        }

        if(event.getClick().equals(ClickType.LEFT)) {
            event.setCancelled(true);

            ItemStack old2 = old.clone();
            old2.setAmount(1);
            ItemStack cursor2 = cursor.clone();
            cursor2.setAmount(1);

            if (!old2.equals(cursor2)) {
                event.setCancelled(true);
                event.setCurrentItem(cursor);
                event.getWhoClicked().setItemOnCursor(old);
                return;
            }

            if ((old.getAmount() + cursor.getAmount()) > 64) {
                int dif = 64 - old.getAmount();
                old.setAmount(64);
                cursor.setAmount(cursor.getAmount() - dif);
                event.setCurrentItem(old);
                event.getWhoClicked().setItemOnCursor(cursor);
                return;
            }

            old.setAmount(old.getAmount() + cursor.getAmount());
            cursor.setAmount(0);

            event.setCurrentItem(old);
            event.getWhoClicked().setItemOnCursor(cursor);

            return;

        }

        if (event.getClick().equals(ClickType.SHIFT_LEFT) || event.getClick().equals(ClickType.SHIFT_RIGHT)) {
            return;
        }

        event.setCancelled(true);
        event.setCurrentItem(cursor);
        event.getWhoClicked().setItemOnCursor(old);


    }
}
