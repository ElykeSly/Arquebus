package com.mrslyfoxjr.arquebus.items;

import com.mrslyfoxjr.arquebus.Main;
import com.mrslyfoxjr.arquebus.init.ModItems;
import com.mrslyfoxjr.arquebus.util.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.tabArquebus);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
