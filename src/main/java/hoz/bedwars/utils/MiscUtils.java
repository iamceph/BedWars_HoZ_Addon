package hoz.bedwars.utils;

import hoz.bedwars.Main;
import misat11.bw.api.events.BedwarsApplyPropertyToBoughtItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MiscUtils {

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static ItemStack isMaterialArmor(ItemStack itemStack) {
        Material material = itemStack.getType();
        if (material.toString().contains("CHESTPLATE")
                || material.toString().contains("BOOTS")
                || material.toString().contains("HELMET")
                || material.toString().contains("LEGGINGS")) {
            return itemStack;
        }
        return null;
    }

    public static int getIntFromProperty(String name, String fallback, BedwarsApplyPropertyToBoughtItem event) {
        try {
            return event.getIntProperty(name);
        } catch (NullPointerException e) {
            return Main.getConfigurator().config.getInt(fallback);
        }
    }

    public static double getDoubleFromProperty(String name, String fallback, BedwarsApplyPropertyToBoughtItem event) {
        try {
            return event.getDoubleProperty(name);
        } catch (NullPointerException e) {
            return Main.getConfigurator().config.getDouble(fallback);
        }
    }

    public static boolean getBooleanFromProperty(String name, String fallback, BedwarsApplyPropertyToBoughtItem event) {
        try {
            return event.getBooleanProperty(name);
        } catch (NullPointerException e) {
            return Main.getConfigurator().config.getBoolean(fallback);
        }
    }

    public static String getStringFromProperty(String name, String fallback, BedwarsApplyPropertyToBoughtItem event) {
        try {
            return event.getStringProperty(name);
        } catch (NullPointerException e) {
            return Main.getConfigurator().config.getString(fallback);
        }
    }

    public static String getMaterialFromProperty(String name, String fallback, BedwarsApplyPropertyToBoughtItem event) {
        try {
            return event.getStringProperty(name);
        } catch (NullPointerException e) {
            return Main.getConfigurator().config.getString(fallback, "SANDSTONE");
        }
    }

    public static Material getMaterialFromString(String path, String fallback) {
        Material material = Material.getMaterial(fallback);
        if (path != null) {
            try {
                Material mat = Material.getMaterial(path);
                if (mat != null) {
                    material = mat;
                }
            } catch (NullPointerException e) {
                System.out.println("Wrong material configured: " + path);
                e.printStackTrace();
            }
        }
        return material;
    }
}
