package net.firemuffin303.muffinsEnchancement;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {

    //Tool Enchantment Limit
    @Entry
    public static boolean toolEnchantmentAdditional = true;

    @Entry(min = 1)
    public static int toolEnchantmentLimit = 2;
    //Enchantment nerf
    @Entry
    public static boolean axeDisableMovement = true;

    @Entry
    public static int movementStunCooldown = 5;

    @Entry
    public static boolean perceptionNerf = true;

    @Entry
    public static boolean warpNerf = true;

    @Entry
    public static int warpCooldownSec = 5;

}
