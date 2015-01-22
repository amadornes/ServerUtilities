package com.amadornes.disabler;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import com.amadornes.disabler.config.ConfigurationHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION, acceptableRemoteVersions = "*")
public class PlacementDisabler {

    public static File file;

    @EventHandler
    public void preInit(FMLPreInitializationEvent ev) {

        ConfigurationHandler.init(file = ev.getModConfigurationDirectory());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent ev) {

        MinecraftForge.EVENT_BUS.register(new com.amadornes.disabler.EventHandler());
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent ev) {

    }
}
