package net.firemuffin303.muffinsEnchancement.mixin.movementStun;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.common.registry.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V",ordinal = 1))
    public void muffins_enchancement$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) boolean bl){
        Entity entity = source.getAttacker();
        if(entity instanceof LivingEntity livingEntity && ((LivingEntity)(Object)this) instanceof PlayerEntity player && !bl){
            if(livingEntity.disablesShield() && ModConfig.axeDisableMovement){
                ModComponents.MOVEMENT_STUN.get(player).setStunned(true);
            }
        }
    }
}
