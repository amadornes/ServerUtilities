package com.amadornes.disabler.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class Config {

    public static Map<Integer, List<ItemStack>> unusableItems = new HashMap<Integer, List<ItemStack>>();

    public static void clearUnusableList() {

        for (List<ItemStack> i : unusableItems.values())
            i.clear();
        unusableItems.clear();
    }

    public static boolean canUseItem(ItemStack item, World world) {

        if (item == null)
            return true;
        if (!unusableItems.containsKey(world.provider.dimensionId))
            return true;

        for (ItemStack is : unusableItems.get(world.provider.dimensionId))
            if ((is.getItem().equals(item.getItem()) && is.getItemDamage() == Integer.MAX_VALUE) || is.isItemEqual(item))
                return false;

        return true;
    }

    public static void addUnusableItem(int dimension, String regname) {

        addUnusableItem(dimension, regname, Integer.MAX_VALUE);
    }

    public static void addUnusableItem(int dimension, String regname, int meta) {

        Item item = GameRegistry.findItem(regname.split(":")[0], regname.split(":")[1]);
        if (item == null) {
            Block b = GameRegistry.findBlock(regname.split(":")[0], regname.split(":")[1]);
            if (b == null)
                return;
            item = Item.getItemFromBlock(b);
        }

        if (item == null)
            return;

        List<ItemStack> l = unusableItems.get(dimension);
        if (l == null) {
            l = new ArrayList<ItemStack>();
            unusableItems.put(dimension, l);
        }

        l.add(new ItemStack(item, meta));
    }

}
