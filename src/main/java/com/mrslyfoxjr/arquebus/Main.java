package com.mrslyfoxjr.arquebus;

import org.apache.logging.log4j.Logger;

import com.mrslyfoxjr.arquebus.init.ModItems;
import com.mrslyfoxjr.arquebus.proxy.CommonProxy;
import com.mrslyfoxjr.arquebus.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod (modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	public static Logger LOG;
	
	@Instance(Reference.MOD_ID)
	public static Main instance;
	
	@SidedProxy(modId = Reference.MOD_ID, clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		LOG = event.getModLog();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public static void Postinit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static CreativeTabs tabArquebus = new CreativeTabs("tab_arquebus")
			{
				@Override
				public ItemStack getTabIconItem() 
				{
					return new ItemStack(ModItems.AMMO_MUSKET);
				}
			};
	
}
