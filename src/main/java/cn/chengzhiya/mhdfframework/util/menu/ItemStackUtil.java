package cn.chengzhiya.mhdfframework.util.menu;

import cn.chengzhiya.mhdfframework.util.message.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SuppressWarnings("unused")
public final class ItemStackUtil {
    /**
     * 构建物品
     *
     * @param type            物品类型
     * @param displayName     物品名称
     * @param lore            物品简介
     * @param customModelData 物品自定义模型数据
     * @param amount          数量
     * @return 物品实例
     */
    public static ItemStack getItem(String type, String displayName, List<String> lore, int customModelData, int amount) {
        if (type != null) {
            displayName = ColorUtil.color(displayName);

            lore = lore.stream().map(s -> ColorUtil.color(s)).toList();

            if (type.startsWith("randomBed")) {
                return getRandomBed(displayName, lore, customModelData, amount);
            }
            if (type.startsWith("head-")) {
                return getPlayerHead(type.replace("head-", ""), displayName, lore, customModelData, amount);
            }
            return getItemStack(Material.getMaterial(type.toUpperCase(Locale.ROOT)), displayName, lore, customModelData, amount);
        } else {
            return new ItemStack(Material.AIR);
        }
    }

    /**
     * 构建玩家头颅
     *
     * @param player          玩家实例
     * @param displayName     物品名称
     * @param lore            物品简介
     * @param customModelData 物品自定义模型数据
     * @param amount          数量
     * @return 物品实例
     */
    public static ItemStack getPlayerHead(OfflinePlayer player, String displayName, List<String> lore, Integer customModelData, Integer amount) {
        ItemStack item = getItemStack(Material.PLAYER_HEAD, displayName, lore, customModelData, amount);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (displayName != null) {
            meta.setOwningPlayer(player);
        }
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 构建玩家头颅
     *
     * @param playerName      玩家名称
     * @param displayName     物品名称
     * @param lore            物品简介
     * @param customModelData 物品自定义模型数据
     * @param amount          数量
     * @return 物品实例
     */
    public static ItemStack getPlayerHead(String playerName, String displayName, List<String> lore, Integer customModelData, Integer amount) {
        return getPlayerHead(Bukkit.getOfflinePlayer(playerName), displayName, lore, customModelData, amount);
    }

    /**
     * 构建随机床物品
     *
     * @param displayName     物品名称
     * @param lore            物品简介
     * @param customModelData 物品自定义模型数据
     * @param amount          数量
     * @return 物品实例
     */
    public static ItemStack getRandomBed(String displayName, List<String> lore, int customModelData, int amount) {
        List<Material> bedList = Arrays.asList(
                Material.BLACK_BED,
                Material.BLUE_BED,
                Material.BROWN_BED,
                Material.CYAN_BED,
                Material.GREEN_BED,
                Material.LIGHT_BLUE_BED,
                Material.LIGHT_GRAY_BED,
                Material.MAGENTA_BED,
                Material.ORANGE_BED,
                Material.LIME_BED,
                Material.PINK_BED,
                Material.PURPLE_BED,
                Material.RED_BED,
                Material.WHITE_BED,
                Material.YELLOW_BED
        );
        return getItemStack(bedList.get(new Random().nextInt(bedList.size())), displayName, lore, customModelData, amount);
    }

    /**
     * 构建普通物品
     *
     * @param material        物品类型
     * @param displayName     物品名称
     * @param lore            物品简介
     * @param customModelData 物品自定义模型数据
     * @param amount          数量
     * @return 物品实例
     */
    public static ItemStack getItemStack(Material material, String displayName, List<String> lore, int customModelData, int amount) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        if (amount >= 1) {
            item.setAmount(amount);
        }
        return item;
    }
}
