package net.firemuffin303.muffinsEnchancement.mixin.movementStun;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import moriyashiine.enchancement.common.component.entity.DashComponent;
import net.firemuffin303.muffinsEnchancement.common.registry.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DashComponent.class)
public abstract class DashComponentMixin {
    @Shadow @Final private PlayerEntity obj;

    @WrapOperation(method = "tick",at = @At(value = "FIELD", target = "Lmoriyashiine/enchancement/common/component/entity/DashComponent;hasDash:Z",ordinal = 1),remap = false)
    public boolean muffins_enchancement$tick(DashComponent instance, Operation<Boolean> original){
        return original.call(instance) && !ModComponents.MOVEMENT_STUN.get(this.obj).isStunned();
    }

    @ModifyReturnValue(method = "canUse",at = @At("RETURN"),remap = false)
    public boolean muffins_enchancement$canUse(boolean original){
        return original && !ModComponents.MOVEMENT_STUN.get(this.obj).isStunned();
    }
}
