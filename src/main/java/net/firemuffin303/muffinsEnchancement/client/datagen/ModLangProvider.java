package net.firemuffin303.muffinsEnchancement.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModLangProvider extends FabricLanguageProvider {
    protected ModLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("muffins_enchancement.midnightconfig.title","Muffin's Enchancement");
        translationBuilder.add("muffins_enchancement.midnightconfig.toolEnchantmentAdditional","Tool Enchantment Additional");
        translationBuilder.add("muffins_enchancement.midnightconfig.toolEnchantmentLimit","Tool Enchantment Limit");
        translationBuilder.add("muffins_enchancement.midnightconfig.axeDisableMovement","Axe disables Movement Enchants");
        translationBuilder.add("muffins_enchancement.midnightconfig.axeDisableMovement.tooltip","Anything that disable shield cause Movement Stun. Movement Stun disable most of movement by enchantment for couple of seconds.");
        translationBuilder.add("muffins_enchancement.midnightconfig.movementStunCooldown","Movement Stun Cooldown");
        translationBuilder.add("muffins_enchancement.midnightconfig.perceptionNerf","Weaker Perception");
        translationBuilder.add("muffins_enchancement.midnightconfig.warpNerf","Weaker Warp");
        translationBuilder.add("muffins_enchancement.midnightconfig.warpCooldownSec","Warp Cooldown (Seconds)");

        translationBuilder.add("modmenu.nameTranslation.muffins_enchancement","Muffin's Enchancement");
        translationBuilder.add("modmenu.descriptionTranslation.muffins_enchancement","A addon mod for MoriyaShiine's Enchancement. Add a little adjustment for some enchantment to be even more enjoyable.");
        translationBuilder.add("modmenu.summaryTranslation.muffins_enchancement","A addon mod for MoriyaShiine's Enchancement");
    }
}
