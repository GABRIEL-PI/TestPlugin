package me.silent.testplugin.listeners;

import me.silent.testplugin.Main;
import me.silent.testplugin.compoanents.Skills;
import me.silent.testplugin.managers.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

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

        Skills skills = profileManager.getPlayerProfile(player.getUniqueId()).getSkills();
        int points = skills.getPoints(), intel = skills.getIntelligence(), agil = skills.getAgility(), health = skills.getHealth();

        switch (slot){
            case 19:
                //Vida Slot
                if (health == 0 && click == ClickType.RIGHT || health == 10 && click == ClickType.LEFT){
                    //Se o jogador tentar ir abaixo do mínimo/ ir acima dos pontos máximos.
                    return;
                }
                if (click == ClickType.LEFT){
                     //Os jogadores têm pontos suficientes para adicionar?
                    if (points > 0){
                        // ele ou ela não faz nada e termina o código aqui
                        return;
                    } else {
                        //players permite atualizar as variáveis
                        skills.setPoints(points - 1);
                        skills.setHealth(health +1);
                    }
                } else {
                    // é uma diminuição de clique direito
                 skills.setHealth(health -1);
                 skills.setPoints(points + 1);
                }
                break;
            case 20:
                //inteligencia Slot
                if (intel == 0 && click == ClickType.RIGHT || intel == 10 && click == ClickType.LEFT){
                    //Se o jogador tentar ir abaixo do mínimo/ ir acima dos pontos máximos.
                    return;
                }
                if (click == ClickType.LEFT){
                    //Os jogadores têm pontos suficientes para adicionar?
                    if (points > 0){
                        // ele ou ela não faz nada e termina o código aqui
                        return;
                    } else {
                        //players permite atualizar as variáveis
                        skills.setPoints(points - 1);
                        skills.setIntelligence(intel +1);
                    }
                } else {
                    // é uma diminuição de clique direito
                    skills.setIntelligence(intel -1);
                    skills.setPoints(points + 1);
                }
                break;
            case 21:
                //Vida Slot
                if (agil == 0 && click == ClickType.RIGHT || agil == 10 && click == ClickType.LEFT){
                    //Se o jogador tentar ir abaixo do mínimo/ ir acima dos pontos máximos.
                    return;
                }
                if (click == ClickType.LEFT){
                    //Os jogadores têm pontos suficientes para adicionar?
                    if (points > 0){
                        // ele ou ela não faz nada e termina o código aqui
                        return;
                    } else {
                        //players permite atualizar as variáveis
                        skills.setPoints(points - 1);
                        skills.setAgility(agil +1);
                    }
                } else {
                    // é uma diminuição de clique direito
                    skills.setAgility(agil -1);
                    skills.setPoints(points + 1);
                }
                break;
        }
        ItemStack pointsItem = inv.getItem(4);
        ItemStack healthItem = inv.getItem(19);
        ItemStack intelItem = inv.getItem(20);
        ItemStack agilItem = inv.getItem(21);
        inv.setItem(4, editItem(pointsItem.clone(), skills.getPoints(), Arrays.asList(color("teste"),
                skills.getPoints() + " pontos!" ), color("&7"), color("&7pontos restantes")));
        inv.setItem(19, editItem(healthItem.clone(), skills.getHealth(), Arrays.asList(color("teste" +
                skills.getHealth()), color("&7"), color("&7Each points of health adds 1/2 hearts to your"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));

        inv.setItem(20, editItem(intelItem.clone(), skills.getIntelligence(), Arrays.asList(color("teste" +
                skills.getIntelligence()), color("&7"), color("&7Each points of health adds"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));

        inv.setItem(21, editItem(agilItem.clone(), skills.getAgility(), Arrays.asList(color("teste" +
                skills.getAgility()), color("&7"), color("&7Each points of health adds"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));




    }
    public ItemStack editItem(ItemStack item, int amount, List<String> lore, String color, String s){
        if (amount == 0){
            amount = 1;
        }
        item.setAmount(amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
