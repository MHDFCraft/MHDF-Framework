package cn.chengzhiya.mhdfframework.util.message;

import cn.chengzhiya.mhdfframework.MHDFPlugin;
import org.bukkit.Bukkit;

@SuppressWarnings("unused")
public final class LogUtil {
    private static final String PLUGIN_NAME = MHDFPlugin.instance.getDescription().getName();
    private static final String CONSOLE_PREFIX = "[" + PLUGIN_NAME + "] ";
    private static final String DEBUG_PREFIX = "[" + PLUGIN_NAME + "-调试] ";

    /**
     * 日志消息
     *
     * @param message 内容
     * @param args    参数
     */
    public static void log(String message, String... args) {
        for (Object var : args) {
            message = message.replaceFirst("\\{}", var.toString());
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(CONSOLE_PREFIX + message));
    }

    /**
     * 调试消息
     *
     * @param message 内容
     * @param args    参数
     */
    public static void debug(String message, String... args) {
        log(DEBUG_PREFIX + message, args);
    }
}
