package net.firemuffin303.muffinsEnchancement.mixin.enchantmentLimit;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.common.MuffinUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = EnchantmentHelper.class,priority = 1500)
public abstract class EnchantmentHelperMixin {
    @Unique
    private static ItemStack anotherCachedStack;

    @TargetHandler(mixin = "moriyashiine.enchancement.mixin.vanillachanges.enchantmentlimit.EnchantmentHelperMixin",name = "enchancement$enchantmentLimit(Ljava/util/Map;Lnet/minecraft/item/ItemStack;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V",prefix = "handler")
    @Inject(method = "@MixinSquared:Handler" ,at = @At(value = "HEAD"))
    private static void muffins_enchancement$storeCacheStack(Map<Enchantment, Integer> enchantments, ItemStack stack, CallbackInfo ci,CallbackInfo callbackInfo){
        anotherCachedStack = stack;
    }


    @TargetHandler(mixin = "moriyashiine.enchancement.mixin.vanillachanges.enchantmentlimit.EnchantmentHelperMixin",name = "enchancement$enchantmentLimit",prefix = "localvar")
    @ModifyExpressionValue(method = "@MixinSquared:Handler" ,at = @At(value = "INVOKE", target = "moriyashiine/enchancement/common/util/EnchancementUtil.limitCheck (ZZ)Z"))
    private static boolean muffins_enchancement$toolEnchantmentLimit(boolean original, @Local(ordinal = 1) Map<Enchantment,Integer> map){
        if(ModConfig.toolEnchantmentAdditional){
            if(EnchancementUtil.limitCheck(true, MuffinUtil.getNonDefaultNonToolEnchantment(anotherCachedStack,map.size()))){
                return true;
            }
        }


        return original;
    }

}
