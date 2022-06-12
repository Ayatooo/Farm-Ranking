package fr.ayato.farmranking.data;

import fr.ayato.farmranking.Main;
import java.util.ArrayList;
import java.util.List;
import static fr.ayato.farmranking.Main.instance;

public class GetBuyingMenuConfig {

    public static List<String> getBuyingMenuName() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".name"));
        }
        return items;
    }

    public static List<String> getBuyingMenuItemsName() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".name"));
        }
        return items;
    }

    public static List<String> getBuyingMenuMaterial() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".material"));
        }
        return items;
    }

    public static int getOneBuyingMenuPrice(int i) {
        return instance.getConfig().getInt("BuyingMenu.items." + i + ".price");
    }

    public static List<String> getBuyingMenuLore() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".lore"));
        }
        return items;
    }

    public static List<Integer> getBuyingMenuPlace() {
        int size = getBuyingMenuItemsSize();
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getInt("BuyingMenu.items." + i + ".place"));
        }
        return items;
    }

    public static int getBuyingMenuSize() {
        return instance.getConfig().getInt("BuyingMenu.size");
    }

    public static int getBuyingMenuItemsSize() {
        return Main.getInstance().getConfig().getConfigurationSection("BuyingMenu.items").getKeys(false).size();
    }

    public static int getBuyingMenuNumberOfPoints(int i) {
        return instance.getConfig().getInt("BuyingMenu.items." + i + ".numberOfPoints");
    }

}
