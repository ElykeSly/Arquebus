package com.mrslyfoxjr.arquebus.items.weapons;

import com.mrslyfoxjr.arquebus.items.ItemBase;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FlintlockBlunderbuss extends ItemBase {

	public FlintlockBlunderbuss(String name) {
		super(name);
		setMaxStackSize(1);
		
	}
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {

		return (repair.getItem() == Items.IRON_INGOT);
	}

	public int getItemEnchantability() {

		return ToolMaterial.IRON.getEnchantability();
	}

	public int getItemEnchantability(ItemStack stack) {
		return ToolMaterial.IRON.getEnchantability();
	}
}
