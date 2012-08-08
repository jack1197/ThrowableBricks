package net.minecraft.src;

import cpw.mods.fml.client.*;

public class ItemBrick extends Item
{
    public ItemBrick(int var1)
    {
        super(var1);
        this.setMaxStackSize(64);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var3.capabilities.isCreativeMode)
        {
            --var1.stackSize;
        }

        var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!var2.isRemote)
        {
            var2.spawnEntityInWorld(new EntityBrick(var2, var3));
        }

        return var1;
        
    }
    
    
}
