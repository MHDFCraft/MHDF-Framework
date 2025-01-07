package cn.chengzhiya.mhdfframework.util.message;

import cn.chengzhiya.mhdfframework.hook.PlaceholderAPIHook;
import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class ColorUtil {
    /**
     * RGB彩色符号(例如: #ffffff)处理
     *
     * @param message 文本
     */
    private static String rgb(@NotNull String message) {
        Matcher matcher = Pattern.compile("#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})").matcher(message);
        StringBuilder sb = new StringBuilder(message.length());
        while (matcher.find()) {
            String hex = matcher.group(1);
            ChatColor color = ChatColor.of("#" + hex);
            matcher.appendReplacement(sb, color.toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 旧版彩色符号(例如: &e)处理
     *
     * @param message 文本
     */
    private static String legacy(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * 彩色符号处理
     *
     * @param message 文本
     */
    public static String color(@NotNull String message) {
        return legacy(rgb(message));
    }

    /**
     * 彩色符号处理并处理PAPI变量
     *
     * @param player  玩家实例
     * @param message 文本
     */
    public static String color(@NonNull OfflinePlayer player, @NotNull String message) {
        return color(PlaceholderAPIHook.placeholder(player, message));
    }
}
