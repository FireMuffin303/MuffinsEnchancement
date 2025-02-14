package net.firemuffin303.muffinsEnchancement.mixin.warpNerf;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsEnchancement.ModConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PersistentProjectileEntity.class,priority = 1500)
public abstract class PersistentProjectileEntityMixin {

    @Shadow protected abstract ItemStack asItemStack();

    @TargetHandler(mixin = "moriyashiine.enchancement.mixin.warp.PersistentProjectileEntityMixin",name ="enchancement$warp")
    @Inject(method = "@MixinSquared:Handler",at = @At(value = "INVOKE", target = "net/minecraft/world/World.sendEntityStatus (Lnet/minecraft/entity/Entity;B)V"),remap = false)
    private void muffins_enchancement$cooldownTrident(BlockHitResult blockHitResult, CallbackInfo ci, CallbackInfo callbackInfo, @Local LivingEntity livingEntity){
        if(livingEntity instanceof PlayerEntity player && ModConfig.warpNerf){
            player.getItemCooldownManager().set(this.asItemStack().getItem(),20* ModConfig.warpCooldownSec);
        }

    }
}
