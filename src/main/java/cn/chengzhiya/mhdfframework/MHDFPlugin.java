package cn.chengzhiya.mhdfframework;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MHDFPlugin extends JavaPlugin implements Plugin {
    public static MHDFPlugin instance;
    @Getter
    private static boolean loadPlaceholderAPI;

    @Override
    public void onLoad() {
        instance = this;

        this.load();
    }

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            loadPlaceholderAPI = true;
        }

        this.enable();
    }

    @Override
    public void onDisable() {
        this.enable();

        instance = null;
    }

    @Override
    public void load() {
    }

    @Override
    public void enable() {
    }

    @Override
    public void disable() {
    }
}
