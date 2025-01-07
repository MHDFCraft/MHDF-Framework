package cn.chengzhiya.mhdfframework.hook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

public final class PlaceholderAPIHook {
    /**
     * PAPI变量解析
     *
     * @param player  玩家实例
     * @param message 未解析的文本
     * @return 解析后的文本
     */
    public static String placeholder(OfflinePlayer player, String message) {
        return PlaceholderAPI.setPlaceholders(player, message);
    }
}
