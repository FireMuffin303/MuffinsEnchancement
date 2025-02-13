package net.firemuffin303.muffinsEnchancement.common;

import com.mojang.logging.LogUtils;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.firemuffin303.muffinsEnchancement.common.registry.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class MovementStunComponent implements AutoSyncedComponent, CommonTickingComponent {
    private int stunCooldown = 20*10;
    private boolean isStunned = false;
    private PlayerEntity player;

    public MovementStunComponent(PlayerEntity player){
        this.player = player;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
        ModComponents.MOVEMENT_STUN.sync(this.player);
    }

    public boolean isStunned() {
        ModComponents.MOVEMENT_STUN.sync(this.player);
        return isStunned;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.stunCooldown = nbtCompound.getInt("stunCooldown");
        this.isStunned = nbtCompound.getBoolean("isStunned");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("isStunned",this.isStunned);
        nbtCompound.putInt("stunCooldown",this.stunCooldown);
    }

    @Override
    public void tick() {
        if(this.isStunned){
            LogUtils.getLogger().info(this.stunCooldown+"");
            if(--this.stunCooldown <= 0){
                this.stunCooldown = 20*10;
                this.setStunned(false);
            }
        }
    }


}
