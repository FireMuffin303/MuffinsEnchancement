package net.firemuffin303.muffinsEnchancement.common;

import com.mojang.logging.LogUtils;
import moriyashiine.enchancement.common.Enchancement;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.client.datagen.ModTagDataGen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.Iterator;

public class MuffinUtil {

    public static boolean getNonDefaultNonToolEnchantment(ItemStack stack, int size){
        int nonDefaultSize = EnchancementUtil.getNonDefaultEnchantmentsSize(stack,size);
        Iterator<Enchantment> enchantmentIterator = EnchantmentHelper.get(stack).keySet().iterator();
        while (enchantmentIterator.hasNext()){
            Enchantment enchantment = enchantmentIterator.next();
            if (!isToolEnchantment(enchantment)){
                return false;
            }
        }

        return nonDefaultSize <= ModConfig.toolEnchantmentLimit;
    }


    public static boolean isToolEnchantment(Enchantment enchantment){
        return Registries.ENCHANTMENT.getEntry(enchantment).isIn(ModTagDataGen.TOOL_ENCHANTMENT);
    }
}
