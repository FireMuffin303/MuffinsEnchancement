package net.firemuffin303.muffinsEnchancement.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModEnchantTagProvider::new);
        pack.addProvider(ModLangProvider::new);
    }
}
