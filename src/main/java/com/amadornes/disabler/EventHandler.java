package com.amadornes.disabler;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.amadornes.disabler.config.Config;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {

        if (!Config.canUseItem(event.entityLiving.getHeldItem(), event.entity.worldObj))
            event.setCanceled(true);
    }

}
