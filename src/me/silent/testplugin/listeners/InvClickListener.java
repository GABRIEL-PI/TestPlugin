package me.silent.testplugin.listeners;

import me.silent.testplugin.Main;
import me.silent.testplugin.managers.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.silent.testplugin.Utils.color;

public class InvClickListener implements Listener {

    private ProfileManager profileManager;

    public  InvClickListener(Main main) {
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if (!event.getView().getTitle().equalsIgnoreCase(color("&e&Skills GUI"))){
            //player doesn`t have skills GUI opened
            return;
        }
        //Cancel all item moving, editing, dropping etc. (including playes inventory)
        event.setCancelled(true);
        if (event.getClickedInventory().equals(inv)){
            //Playes clicked
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        int slot = event.getSlot();
        ClickType click = event.getClick();
        if (click != ClickType.LEFT && click != ClickType.RIGHT){
            return;
        }
    }


}
