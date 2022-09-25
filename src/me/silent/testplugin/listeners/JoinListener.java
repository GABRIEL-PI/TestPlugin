package me.silent.testplugin.listeners;

import me.silent.testplugin.Main;
import me.silent.testplugin.compoanents.Profile;
import me.silent.testplugin.managers.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.silent.testplugin.Utils.*;


public class JoinListener implements Listener {

    private Main main;
    private ProfileManager profileManager;

    public JoinListener(Main main) {
        this.main = main;
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //Verifica a existencia do players na database...

        Profile profile = profileManager.getPlayerProfile(player.getUniqueId());
        if (profile == null) {
            profile = profileManager.createNewProfile(player);
            log("Novo Jogador " + player.getName() + " entrou no servidor!");

        }
    }
}
