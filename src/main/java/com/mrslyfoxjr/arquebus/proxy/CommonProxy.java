package com.mrslyfoxjr.arquebus.proxy;

import java.io.File;

import com.mrslyfoxjr.arquebus.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class CommonProxy
{
	public static float damage_musket; 
	
	public static final String floats = "General";
	public static final String[] sounds = new String[] {"musket_shoot", "shotgun_reload", "shotgun_shoot" };

	public static SoundEvent getSound(String name) {
		return SoundEvent.REGISTRY.getObject(new ResourceLocation(Reference.MOD_ID, name));
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		loadConfig(event);
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {}

	private void loadConfig(FMLPreInitializationEvent e) {
		File configdir = new File(e.getModConfigurationDirectory(), Reference.NAME);
		File configfile = new File(configdir, "arquebus.cfg");
		if (!configfile.exists())
			configdir.mkdirs();
		// Get an instance of Config
		Configuration config = new Configuration(configfile);

		// Load Config
		config.load();

		// Floats
		damage_musket = config.getFloat("Musket Damage", floats, 10, 1, 50, "Damage of the Musket");

		// Save config
		config.save();
	}
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		IForgeRegistry<SoundEvent> reg = event.getRegistry();
		for (String s : sounds) {
			ResourceLocation loc = new ResourceLocation(Reference.MOD_ID, s);
			reg.register(new SoundEvent(loc).setRegistryName(loc));
		}
	}
}

