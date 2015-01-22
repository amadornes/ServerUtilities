package com.amadornes.disabler.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.amadornes.disabler.ModInfo;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

    public static Configuration cfg;

    public static void init(File configFile) {

        if (cfg == null) {
            cfg = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {

        Config.clearUnusableList();

        cfg.get("0", "disabled", new String[] { "modid:itemorblockid###optionalmetadata" });

        for (String cat : cfg.getCategoryNames()) {
            try {
                int dim = Integer.parseInt(cat);
                for (String s : cfg.getStringList("disabled", dim + "", new String[] {}, "Disabled blocks/items")) {
                    String[] item = s.split("###");
                    if (item.length == 2) {
                        Config.addUnusableItem(dim, item[0], Integer.parseInt(item[1]));
                    } else {
                        Config.addUnusableItem(dim, s);
                    }
                }
            } catch (Exception ex) {
            }
        }

        if (cfg.hasChanged())
            cfg.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equalsIgnoreCase(ModInfo.MODID)) {
            loadConfiguration();
        }
    }
}
