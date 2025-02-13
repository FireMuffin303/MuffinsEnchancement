package net.firemuffin303.muffinsEnchancement.mixin.slideNerf;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import moriyashiine.enchancement.common.component.entity.SlideComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(SlideComponent.class)
public abstract class SlideComponentMixin {
    
    @Shadow private Vec3d velocity;

    @Shadow public abstract boolean isSliding();

    @Unique int slideCooldown = 0;

    @Inject(method = "readFromNbt", at = @At("TAIL"),remap = false)
    public void muffins_enchancement$readFromNBT(NbtCompound tag, CallbackInfo ci){
        this.slideCooldown = tag.getInt("slideCooldown");
    }

    @Inject(method = "writeToNbt", at = @At("TAIL"),remap = false)
    public void muffins_enchancement$writeToNBT(NbtCompound tag, CallbackInfo ci){
        tag.putInt("slideCooldown",this.slideCooldown);
    }
    
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setVelocity(DDD)V",ordinal = 0),remap = false)
    public void muffins_enchancement$setVelocity(CallbackInfo ci){
        this.velocity = new Vec3d(this.velocity.getX() * 0.958424,this.velocity.getY(),this.velocity.getZ()* 0.958424);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lmoriyashiine/enchancement/common/component/entity/SlideComponent;isSliding()Z"),remap = false)
    public void muffins_enchancement$slideCooldown(CallbackInfo ci){
        if(this.slideCooldown > 0 && !this.isSliding()){
            this.slideCooldown--;
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lmoriyashiine/enchancement/mixin/slide/EntityAccessor;enchancement$spawnSprintingParticles()V"),remap = false)
    public void muffins_enchancement$setSlideCooldownWhenSlide(CallbackInfo ci){
        this.slideCooldown = 10;
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lmoriyashiine/enchancement/common/component/entity/SlideComponent;ticksSliding:I",ordinal = 6),remap = false)
    public void muffins_enchancement$setSlideCooldown(CallbackInfo ci){
        this.slideCooldown = 0;
    }



    @ModifyReturnValue(method = "canSlide",at = @At("RETURN"),remap = false)
    public boolean muffins_enchancement$canSlide(boolean original){
        return original && this.slideCooldown <= 0;
    }
}
