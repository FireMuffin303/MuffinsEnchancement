package net.firemuffin303.muffinsEnchancement.client.datagen;

import moriyashiine.enchancement.common.init.ModEnchantments;
import moriyashiine.enchancement.common.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsEnchancement.MuffinsEnchancementMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModTagDataGen extends FabricTagProvider<StatusEffect> {
    public static final TagKey<Enchantment> TOOL_ENCHANTMENT = TagKey.of(RegistryKeys.ENCHANTMENT,MuffinsEnchancementMod.modid("tool_enchantment"));
    public ModTagDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.STATUS_EFFECT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ModTags.StatusEffects.CHAOS_UNCHOOSABLE).addOptional(new Identifier("vinery","explosion"));

    }

    public static class ModEnchantTagProvider extends FabricTagProvider<Enchantment>{

        public ModEnchantTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            this.getOrCreateTagBuilder(ModTagDataGen.TOOL_ENCHANTMENT)
                    .add(Enchantments.EFFICIENCY)
                    .add(Enchantments.SILK_TOUCH)
                    .add(ModEnchantments.MOLTEN)
                    .add(ModEnchantments.EXTRACTING)
                    .add(ModEnchantments.BEHEADING)
                    .add(ModEnchantments.LUMBERJACK);
        }
    }
}
