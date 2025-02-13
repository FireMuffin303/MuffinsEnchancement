package net.firemuffin303.muffinsEnchancement;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MuffinsEnchancementMod implements ModInitializer {
    public static final String MODID = "muffins_enchancement";

    @Override
    public void onInitialize() {
        MidnightConfig.init(MODID,ModConfig.class);
    }

    public static Identifier modid(String id){
        return new Identifier(MODID,id);
    }
}
