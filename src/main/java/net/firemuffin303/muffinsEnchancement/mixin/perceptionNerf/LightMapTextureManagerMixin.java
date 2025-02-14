package net.firemuffin303.muffinsEnchancement.mixin.perceptionNerf;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LightmapTextureManager.class,priority = 1500)
public abstract class LightMapTextureManagerMixin {
    @TargetHandler(mixin = "moriyashiine.enchancement.mixin.perception.client.LightmapTextureManagerMixin",name = "enchancement$perception")
    @ModifyReturnValue(method = "@MixinSquared:Handler",at = @At(value = "RETURN",ordinal = 0))
    private float muffins_enchancement$perceptionNerf(float original, @Local(argsOnly = true) float trueOriginal){
        if(ModConfig.perceptionNerf){
            return trueOriginal;
        }
        return original;
    }
}
