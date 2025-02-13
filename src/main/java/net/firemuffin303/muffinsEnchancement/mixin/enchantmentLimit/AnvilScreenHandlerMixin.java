package net.firemuffin303.muffinsEnchancement.mixin.enchantmentLimit;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.client.datagen.ModTagDataGen;
import net.firemuffin303.muffinsEnchancement.common.MuffinUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Iterator;
import java.util.Map;

@Debug(export = true)
@Mixin(value = AnvilScreenHandler.class,priority = 1500)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    //TODO : GET CACHE ENCHANTMENT HERE


    @TargetHandler(mixin = "moriyashiine.enchancement.mixin.vanillachanges.enchantmentlimit.AnvilScreenHandlerMixin",name = "enchancement$enchantmentLimit",prefix = "modifyExpressionValue")
    @ModifyExpressionValue(method = "@MixinSquared:Handler",at = @At(value = "INVOKE", target = "moriyashiine/enchancement/common/util/EnchancementUtil.limitCheck (ZZ)Z"),remap = false)
    public boolean muffins_enchancement$toolEnchantment(boolean original, @Local ItemStack stack){
        if(EnchancementUtil.limitCheck(false, MuffinUtil.getNonDefaultNonToolEnchantment(stack,stack.getEnchantments().size() + 1))){
            return false;
        }
        return original;
    }
}
