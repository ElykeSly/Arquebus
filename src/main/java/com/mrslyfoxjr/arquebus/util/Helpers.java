package com.mrslyfoxjr.arquebus.util;

import java.util.List;
import java.util.UUID;

import com.mrslyfoxjr.arquebus.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Helpers {

	/** Checks if the 2 given BlockPositions are equal */
	public static boolean blockPosEqual(BlockPos pos1, BlockPos pos2) {
		int x1 = pos1.getX();
		int x2 = pos2.getX();
		int y1 = pos1.getY();
		int y2 = pos2.getY();
		int z1 = pos1.getZ();
		int z2 = pos2.getZ();
		return (x1 == x2 && y1 == y2 && z1 == z2);
	}

	public static boolean consumeInventoryItem(EntityPlayer player, Item itemIn) {
		int i;
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(itemIn))) {
			i = player.inventory.currentItem;
		} else {
			i = getInventorySlotContainItem(player, itemIn);
		}
		if (i < 0) {
			return false;
		} else {
			ItemStack item = player.inventory.mainInventory.get(i);
			item.shrink(1);
			if (item.getCount() <= 0) {
				player.inventory.mainInventory.set(i, ItemStack.EMPTY);
			}
			return true;
		}
	}

	public static void destroyCurrentEquippedItem(EntityPlayer player) {
		ItemStack orig = player.inventory.getCurrentItem();
		player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
		net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, orig, EnumHand.MAIN_HAND);
	}

	/** Helper method for creating a Rectangle */
	public static void drawRectangle(int left, int top, int right, int bottom, float[] color) {

		if (left < right) {
			int j1 = left;
			left = right;
			right = j1;
		}

		if (top < bottom) {
			int j1 = top;
			top = bottom;
			bottom = j1;
		}

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldrenderer = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(color[0], color[1], color[2], color[3]);

		worldrenderer.begin(7, DefaultVertexFormats.POSITION);
		worldrenderer.pos(left, bottom, 0.0D).endVertex();
		worldrenderer.pos(right, bottom, 0.0D).endVertex();
		worldrenderer.pos(right, top, 0.0D).endVertex();
		worldrenderer.pos(left, top, 0.0D).endVertex();

		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public static int getInventorySlotContainItem(EntityPlayer player, Item itemIn) {
		for (int i = 0; i < player.inventory.mainInventory.size(); ++i) {
			if (player.inventory.mainInventory.get(i).isItemEqualIgnoreDurability(new ItemStack(itemIn))) {
				return i;
			}
		}
		return -1;
	}

	/** Thanks to Jabelar!!! */
	public static RayTraceResult getMouseOverExtended(float distance) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		Entity theRenderViewEntity = mc.getRenderViewEntity();
		AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(theRenderViewEntity.posX - 0.5D,
				theRenderViewEntity.posY - 0.0D, theRenderViewEntity.posZ - 0.5D, theRenderViewEntity.posX + 0.5D,
				theRenderViewEntity.posY + 1.5D, theRenderViewEntity.posZ + 0.5D);
		RayTraceResult returnMOP = null;
		if (mc.world != null) {
			double var2 = distance;
			returnMOP = theRenderViewEntity.rayTrace(var2, 0);
			double calcdist = var2;
			Vec3d pos = theRenderViewEntity.getPositionEyes(0);
			var2 = calcdist;
			if (returnMOP != null) {
				calcdist = returnMOP.hitVec.distanceTo(pos);
			}
			Vec3d lookvec = theRenderViewEntity.getLook(0);
			Vec3d var8 = pos.addVector(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2);
			Entity pointedEntity = null;
			float var9 = 1.0F;
			List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(theRenderViewEntity, theViewBoundingBox
					.expand(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2).expand(var9, var9, var9));
			double d = calcdist;
			for (Entity entity : list) {
				if (entity.canBeCollidedWith()) {
					float bordersize = entity.getCollisionBorderSize();
					AxisAlignedBB aabb = new AxisAlignedBB(entity.posX - entity.width / 2, entity.posY,
							entity.posZ - entity.width / 2, entity.posX + entity.width / 2, entity.posY + entity.height,
							entity.posZ + entity.width / 2);
					aabb.expand(bordersize, bordersize, bordersize);
					RayTraceResult mop0 = aabb.calculateIntercept(pos, var8);
					if (aabb.contains(pos)) {
						if (0.0D < d || d == 0.0D) {
							pointedEntity = entity;
							d = 0.0D;
						}
					} else if (mop0 != null) {
						double d1 = pos.distanceTo(mop0.hitVec);
						if (d1 < d || d == 0.0D) {
							pointedEntity = entity;
							d = d1;
						}
					}
				}
			}
			if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
				returnMOP = new RayTraceResult(pointedEntity);
			}
		}
		return returnMOP;
	}

	public static void playSound(World w, Entity e, String name, double volume, double pitch) {
		playSound(w, e, name, (float) volume, (float) pitch);
	}

	public static void playSound(World w, Entity e, String name, float volume, float pitch) {
		w.playSound(null, e.posX, e.posY, e.posZ, CommonProxy.getSound(name), SoundCategory.MASTER, volume, pitch);
	}
	
    public static boolean hasTag(ItemStack itemStack, String keyName)
    {
        return itemStack != null && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(keyName);
    }

    public static void removeTag(ItemStack itemStack, String keyName)
    {
        if (itemStack.hasTagCompound())
        {
            itemStack.getTagCompound().removeTag(keyName);
        }
    }

    /**
     * Initializes the NBT Tag Compound for the given ItemStack if it is null
     *
     * @param itemStack
     *         The ItemStack for which its NBT Tag Compound is being checked for initialization
     */
    private static void initNBTTagCompound(ItemStack itemStack)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setLong(keyName, keyValue);
    }

    // String
    public static String getString(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setString(itemStack, keyName, "");
        }

        return itemStack.getTagCompound().getString(keyName);
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setString(keyName, keyValue);
    }

    // boolean
    public static boolean getBoolean(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setBoolean(itemStack, keyName, false);
        }

        return itemStack.getTagCompound().getBoolean(keyName);
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setBoolean(keyName, keyValue);
    }

    // byte
    public static byte getByte(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setByte(itemStack, keyName, (byte) 0);
        }

        return itemStack.getTagCompound().getByte(keyName);
    }

    public static void setByte(ItemStack itemStack, String keyName, byte keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setByte(keyName, keyValue);
    }

    // short
    public static short getShort(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setShort(itemStack, keyName, (short) 0);
        }

        return itemStack.getTagCompound().getShort(keyName);
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setShort(keyName, keyValue);
    }

    // int
    public static int getInt(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setInteger(itemStack, keyName, 0);
        }

        return itemStack.getTagCompound().getInteger(keyName);
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setInteger(keyName, keyValue);
    }

    // long
    public static long getLong(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setLong(itemStack, keyName, 0);
        }

        return itemStack.getTagCompound().getLong(keyName);
    }

    // float
    public static float getFloat(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setFloat(itemStack, keyName, 0);
        }

        return itemStack.getTagCompound().getFloat(keyName);
    }

    public static void setFloat(ItemStack itemStack, String keyName, float keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setFloat(keyName, keyValue);
    }

    // double
    public static double getDouble(ItemStack itemStack, String keyName)
    {
        initNBTTagCompound(itemStack);

        if (!itemStack.getTagCompound().hasKey(keyName))
        {
            setDouble(itemStack, keyName, 0);
        }

        return itemStack.getTagCompound().getDouble(keyName);
    }

    public static void setDouble(ItemStack itemStack, String keyName, double keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.getTagCompound().setDouble(keyName, keyValue);
    }
    
    public static void setOwner(ItemStack stack, EntityPlayer player)
	{
		Helpers.setString(stack, "owner", player.getDisplayNameString());
		Helpers.setLong(stack, "uuid_most_sig", player.getUniqueID().getMostSignificantBits());
		Helpers.setLong(stack, "uuid_least_sig", player.getUniqueID().getLeastSignificantBits());
	}
	
	public static UUID getOwner(ItemStack stack)
	{
		if(Helpers.hasTag(stack, "owner") && Helpers.hasTag(stack, "uuid_most_sig") && Helpers.hasTag(stack, "uuid_least_sig"))
		{
			return new UUID(Helpers.getLong(stack, "uuid_most_sig"), Helpers.getLong(stack, "uuid_least_sig"));
		}
		return null;
	}

	/*
    The following methods are added by Bright_Spark so I can read and write tag lists to the NBT.
    Never Forgetti to Credit teh Big boy
     */

    public static void setList(ItemStack stack, String keyName, NBTTagList list)
    {
        initNBTTagCompound(stack);

        stack.getTagCompound().setTag(keyName, list);
    }

    /**
     * Gets a tag list which type is of compound tags.
     */
    public static NBTTagList getList(ItemStack stack, String keyName)
    {
        return getList(stack, keyName, Constants.NBT.TAG_COMPOUND);
    }

    public static NBTTagList getList(ItemStack stack, String keyName, int tagType)
    {
        initNBTTagCompound(stack);

        return stack.getTagCompound().getTagList(keyName, tagType);
    }
}
