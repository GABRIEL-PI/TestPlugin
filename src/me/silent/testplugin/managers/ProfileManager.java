package me.silent.testplugin.managers;

import me.silent.testplugin.Main;
import me.silent.testplugin.compoanents.Profile;
import me.silent.testplugin.compoanents.Skills;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    private Main main;
    private Map<UUID, Profile> profiles = new HashMap<>();


    public ProfileManager(Main main) {
        this.main = main;
    }

    public Profile createNewProfile(Player player){
        Skills skills = new Skills(10,0,0,0);
        Profile profile = new Profile(skills);
        profiles.put(player.getUniqueId(), profile);

        return profile;

    }

    public Profile getPlayerProfile(UUID uuid){
       return profiles.get(uuid);
    }

}
