package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityBrick extends EntityThrowable
{
    int currentAge;
    double currentX;
    double currentY;
    double currentZ;

    Minecraft mc = ModLoader.getMinecraftInstance();
    
    public EntityBrick(World var1)
    {
        super(var1);
        this.currentX = this.posX;
        this.currentY = this.posY;
        this.currentZ = this.posZ;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        this.motionY -= 0.04D;
        ++this.currentAge;
    }

    public EntityBrick(World var1, EntityLiving var2)
    {
        super(var1, var2);
        this.currentX = this.posX;
        this.currentY = this.posY;
        this.currentZ = this.posZ;
        
    }

    public EntityBrick(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
        this.currentX = this.posX;
        this.currentY = this.posY;
        this.currentZ = this.posZ;
        
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition var1)
    {
   		
        boolean var2 = true;
        boolean var3 = false;
        int var5;
        
        int block = this.worldObj.getBlockId(var1.blockX, var1.blockY, var1.blockZ);
        
        if(block == Block.glass.blockID || block == Block.thinGlass.blockID)
        {
        	this.worldObj.setBlockWithNotify(var1.blockX, var1.blockY, var1.blockZ, 0);     	
        	this.mc.sndManager.playSound("random.glass", (float)currentX + 0.5F, (float)currentY + 0.5F, (float)currentZ + 0.5F, 2.0F, 1.0F);
        }
        
        if (var1.entityHit != null && var1.typeOfHit == EnumMovingObjectType.ENTITY)
        {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), 6);
            this.setDead();

            if (this.rand.nextInt(100) != 0)
            {
                var5 = this.rand.nextInt(2) + 1;
                var3 = false;
            }
            else
            {
                var2 = false;
                var3 = true;
            }
        }

        int var4;

        if (!this.worldObj.isRemote)
        {
            byte var6;

            if (this.currentAge <= 4)
            {
                if (this.rand.nextInt(40) == 0)
                {
                    var5 = this.rand.nextInt(2) + 1;
                    var6 = 0;
                }
                else
                {
                    var5 = 0;
                    var6 = 1;
                }
            }
            else if (this.rand.nextInt(100) != 0)
            {
                var5 = this.rand.nextInt(2) + 1;
                var6 = 0;
            }
            else
            {
                var5 = 0;
                var6 = 1;
            }

            for (var4 = 0; var4 < var5; ++var4)
            {
                this.dropItem(mod_ThrowingBricks.BrickPiece.shiftedIndex, 1);
            }

            for (var4 = 0; var4 < var6; ++var4)
            {
                this.dropItem(mod_ThrowingBricks.Brick.shiftedIndex, 1);
            }
        }

        for (var4 = 0; var4 < 8; ++var4)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
          this.setDead();
        }
    
    }
}
