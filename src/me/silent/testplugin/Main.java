package me.silent.testplugin;

import me.silent.testplugin.commands.SkillCommand;
import me.silent.testplugin.listeners.InvClickListener;
import me.silent.testplugin.listeners.JoinListener;
import me.silent.testplugin.managers.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static me.silent.testplugin.Utils.log;
public class Main extends JavaPlugin {
    private static Logger logger;
    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        logger = getLogger();

        profileManager = new ProfileManager(this);

        SkillCommand skillCommand = new SkillCommand( this);
        getCommand("skills").setExecutor(skillCommand);
        getCommand("skills").setTabCompleter(skillCommand);

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new InvClickListener(this), this);


        log( "Plugin Ativado");
    }

    @Override
    public void onDisable() {

        log( "Plugin Desativado");
    }

    public static Logger getPluginLogger() {  return logger;  }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}