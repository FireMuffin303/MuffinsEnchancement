package net.firemuffin303.muffinsEnchancement.mixin.movementStun;

import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.firemuffin303.muffinsEnchancement.common.registry.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z",ordinal = 2))
    public void muffins_enchancement$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        Entity entity = source.getAttacker();
        if(entity instanceof LivingEntity livingEntity && ((LivingEntity)(Object)this) instanceof PlayerEntity player){
            if(livingEntity.disablesShield() && ModConfig.axeDisableMovement){
                ModComponents.MOVEMENT_STUN.get(player).setStunned(true);
            }
        }
    }
}
