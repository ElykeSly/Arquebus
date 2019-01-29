package com.mrslyfoxjr.arquebus.items;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IGun
{
    /**
     * Sets the amount of ammo in this gun
     */
    void setAmmoAmount(ItemStack stack, int amount);

    /**
     * Gets the amount of ammo left in this gun
     */
    int getAmmoAmount(ItemStack stack);

    /**
     * Gets the amount of space for ammo in this gun
     */
    int getAmmoSpace(ItemStack stack);

    /**
     * Returns the ammo item to look for when reloading
     */
    @Nonnull
    IShootable getAmmoItem();
}

