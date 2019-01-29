package com.mrslyfoxjr.arquebus.init;

import java.util.ArrayList;
import java.util.List;

import com.mrslyfoxjr.arquebus.items.ItemBase;
import com.mrslyfoxjr.arquebus.items.weapons.FlintlockBlunderbuss;
import com.mrslyfoxjr.arquebus.items.weapons.FlintlockMusket;
import com.mrslyfoxjr.arquebus.items.weapons.FlintlockPistol;

import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// Item Format
	// public static final Item ALLCAPSNAME = new ItemBase("");
		// Ammo
		public static final Item AMMO_MUSKET = new ItemBase("ammo_musket");
		public static final Item AMMO_BLUNDERBUSS = new ItemBase("ammo_blunderbuss");
		
		//Mechanism Parts
		public static final Item PART_FLINTLOCK = new ItemBase("part_flintlock");
		
		//Misc Parts
		public static final Item BARREL_RIFLE = new ItemBase("barrel_rifle");
		public static final Item BARREL_BLUNDERBUSS = new ItemBase("barrel_blunderbuss");
		public static final Item BARREL_RIFLE_SHORT = new ItemBase("barrel_rifle_short");
		public static final Item STOCK_RIFLE = new ItemBase("stock_rifle");
		public static final Item STOCK_PISTOL = new ItemBase("stock_pistol");
		
		//Weapon Items
		public static final Item FLINTLOCK_MUSKET = new FlintlockMusket("flintlock_musket");
		public static final Item FLINTLOCK_PISTOL = new FlintlockPistol("flintlock_pistol");
		public static final Item FLINTLOCK_BLUNDERBUSS = new FlintlockBlunderbuss("flintlock_blunderbuss");
		
		
}
