package com.mrslyfoxjr.arquebus.util;

import java.util.Collections;
import java.util.List;

import com.mrslyfoxjr.arquebus.entities.EntityBullet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class CommonUtils 
{
    public static DamageSource causeBulletDamage(EntityBullet bullet, Entity indirectEntity)
    {
        return new EntityDamageSourceIndirect(Reference.MOD_ID + ".bullet", bullet, indirectEntity).setProjectile();
    }

    public static void sortStringList(List<String> list)
    {
        Collections.sort(list);
    }


    /**
     * Capitalises the first letter of every word in the string
     */
    public static String capitaliseAllFirstLetters(String text)
    {
        String[] textArray = text.split("\\s");
        String output = "";
        for(String t : textArray)
        {
            String space = output.equals("") ? "" : " ";
            output += space + capitaliseFirstLetter(t);
        }
        return output;
    }

    /**
     * Capitalises the first letter of the string
     */
    public static String capitaliseFirstLetter(String text)
    {
        if(text == null || text.length() <= 0)
            return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static boolean isUsableByPlayer(TileEntity te, EntityPlayer player)
    {
        BlockPos pos = te.getPos();
        return te.getWorld().getTileEntity(pos) == te && player.getDistanceSq((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;
    }

    public static boolean isStackListEmpty(NonNullList<ItemStack> list)
    {
        if(list.isEmpty()) return true;
        for(ItemStack stack : list)
            if(!stack.isEmpty())
                return false;
        return true;
    }
}