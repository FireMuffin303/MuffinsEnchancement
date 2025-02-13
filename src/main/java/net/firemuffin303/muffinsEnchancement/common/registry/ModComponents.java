package net.firemuffin303.muffinsEnchancement.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.firemuffin303.muffinsEnchancement.MuffinsEnchancementMod;
import net.firemuffin303.muffinsEnchancement.common.MovementStunComponent;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<MovementStunComponent> MOVEMENT_STUN = ComponentRegistry.getOrCreate(MuffinsEnchancementMod.modid("movement_stun"), MovementStunComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerForPlayers(MOVEMENT_STUN,MovementStunComponent::new, RespawnCopyStrategy.LOSSLESS_ONLY);
    }
}
