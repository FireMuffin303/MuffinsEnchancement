package net.firemuffin303.muffinsEnchancement.mixin.enchantmentLimit;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.client.datagen.ModTagDataGen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @ModifyReturnValue(method = "canAccept",at = @At("RETURN"))
    public boolean muffins_enchancement$canAccept(boolean original, @Local(ordinal = 1, argsOnly = true) Enchantment other){
        if(ModConfig.toolEnchantmentAdditional){
            return original && Registries.ENCHANTMENT.getEntry((Enchantment)(Object) this).isIn(ModTagDataGen.TOOL_ENCHANTMENT) && Registries.ENCHANTMENT.getEntry(other).isIn(ModTagDataGen.TOOL_ENCHANTMENT);
        }
        return original;
    }
}
