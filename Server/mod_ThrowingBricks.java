package net.minecraft.src;

import java.util.Map;

import net.minecraft.src.forge.*;

public class mod_ThrowingBricks extends NetworkMod
{
    public static final Item Brick = (new ItemBrick(80)).setItemName("Brick").setIconCoord(6, 1);
    public static final Item BrickPiece = (new Item(551)).setItemName("BrickPiece");

    public void load()
    {
        ModLoader.addName(Brick, "Brick");
        ModLoader.addName(BrickPiece, "Brick Chunk");
        BrickPiece.iconIndex = ModLoader.addOverride("/gui/items.png", "/images/BrickChunk.png");
        ModLoader.addRecipe(new ItemStack(Brick, 1), new Object[] {"@@", "@@", '@', BrickPiece});
        ModLoader.addRecipe(new ItemStack(Brick, 1), new Object[] {"@", '@', Block.dirt});
        
        MinecraftForge.registerEntity(EntityBrick.class, this, 0, 40, 5, true);
    }


    public String getVersion()
    {
        return "1.2.5";
    }
    
    public String getName()
    {
        return "Throwing Bricks";
    }
    
    @Override
    public boolean serverSideRequired()
    {
    	return false;
    }
    
    @Override
    public boolean clientSideRequired()
    {
    	return false;
    }
}
