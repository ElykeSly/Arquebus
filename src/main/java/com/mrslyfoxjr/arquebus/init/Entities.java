package com.mrslyfoxjr.arquebus.init;

import com.mrslyfoxjr.arquebus.entities.projectile.EntityBulletBlunderbuss;
import com.mrslyfoxjr.arquebus.entities.projectile.EntityBulletMusket;
import com.mrslyfoxjr.arquebus.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class Entities {
	public static int ID = 0;
	
	public static final EntityEntry BULLETMUSKET = EntityEntryBuilder.create()
			.entity(EntityBulletMusket.class)
			.id(new ResourceLocation(Reference.MOD_ID, "bullet_musket"), ID++)
			.name("bullet_musket")
			.tracker(128, 1, true)
			.build();
	
	public static final EntityEntry BULLETBLUNDERBUSS = EntityEntryBuilder.create()
			.entity(EntityBulletBlunderbuss.class)
			.id(new ResourceLocation(Reference.MOD_ID, "bullet_blunderbuss"), ID++)
			.name("bullet_blunderbuss")
			.tracker(128, 1, true)
			.build();

	public static final EntityEntry[] ENTITIES = {
			//@formatter:on
			BULLETMUSKET, BULLETBLUNDERBUSS
			};
}
