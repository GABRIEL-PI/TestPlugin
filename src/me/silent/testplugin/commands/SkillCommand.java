package me.silent.testplugin.commands;

import me.silent.testplugin.Main;
import me.silent.testplugin.compoanents.Skills;
import me.silent.testplugin.managers.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static me.silent.testplugin.Utils.color;
import static me.silent.testplugin.Utils.log;

public class SkillCommand implements TabExecutor {

    private ProfileManager profileManager;
    private ItemStack pointsItem, healthItem, intelItem, agilityItem;

    public SkillCommand(Main main) {
        profileManager = main.getProfileManager();

        pointsItem = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta pointsItemMeta = pointsItem.getItemMeta();
        pointsItemMeta.setDisplayName(color("&eSkill points left"));
        pointsItemMeta.setLore(Arrays.asList(color("&f Voce tem X pontos agora!"), color("&7"), color(
                "Pontos para adicionar em skills")));
        pointsItem.setItemMeta(pointsItemMeta);

        healthItem = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta healthItemMeta = healthItem.getItemMeta();
        pointsItemMeta.setDisplayName(color("&cVida"));
        pointsItemMeta.setLore(Arrays.asList(color("&7 Pontos: &30"), color("&7"), color(
                "Voca ganha 1/2 coracao a cada ponto adicionado"), color("&f Pontos maximos: 10")));
        healthItem.setItemMeta(pointsItemMeta);

        intelItem = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta intelItemMeta = intelItem.getItemMeta();
        pointsItemMeta.setDisplayName(color("&dInteligencia"));
        pointsItemMeta.setLore(Arrays.asList(color("&7 Pontos: &30"), color("&7"), color(
                "Pontos de vida adiciona regeneração de mana 5% mais rápida"), color("&f Pontos maximos: 10")));
        intelItem.setItemMeta(pointsItemMeta);

        agilityItem = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta agilityItemMeta = agilityItem.getItemMeta();
        pointsItemMeta.setDisplayName(color("&aAgilidade"));
        pointsItemMeta.setLore(Arrays.asList(color("&7 Pontos: &30"), color("&7"), color(
                "Pontos de vida adiciona regeneração de mana 5% mais rápida"), color("&f Pontos maximos: 10")));
        agilityItem.setItemMeta(pointsItemMeta);

}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] strings) {
        if (!(sender instanceof Player)){
            log("Este recurso é permitido apenas para jogadores!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("testplugin.skills")) {
            player.sendMessage(color("&Você não tem permissão para usar este recurso!"));
            return true;
        }

        Skills skills = profileManager.getPlayerProfile(player.getUniqueId()).getSkills();

        Inventory skillsGUI = Bukkit.createInventory(null, 54, color("&e&Skills GUI"));
        skillsGUI.setItem(4, editItem(pointsItem.clone(), skills.getPoints(), Arrays.asList(color("teste"),
                skills.getPoints() + " pontos!" ), color("&7"), color("&7pontos restantes")));

        skillsGUI.setItem(19, editItem(healthItem.clone(), skills.getHealth(), Arrays.asList(color("teste" +
        skills.getHealth()), color("&7"), color("&7Each points of health adds"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));

        skillsGUI.setItem(20, editItem(intelItem.clone(), skills.getIntelligence(), Arrays.asList(color("teste" +
                skills.getIntelligence()), color("&7"), color("&7Each points of health adds"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));

        skillsGUI.setItem(21, editItem(agilityItem.clone(), skills.getAgility(), Arrays.asList(color("teste" +
                skills.getAgility()), color("&7"), color("&7Each points of health adds"), color("&fMaximum Points 10")), color("&7"), color("&7pontos restantes")));

        player.openInventory(skillsGUI);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

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
