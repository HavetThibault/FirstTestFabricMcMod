package net.fabricmc.Beginner.items;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class WeirdProjectileEntity extends PersistentProjectileEntity
{
    private long timeTicks = 0;

    public WeirdProjectileEntity(World world, double x, double y, double z) {
        super(EntityType.ARROW, x, y, z, world);
        setNoGravity(true);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    public void tick()
    {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            float f = MathHelper.sqrt(squaredHorizontalLength(vec3d));
            this.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
            this.pitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * 57.2957763671875D);
            this.prevYaw = this.yaw;
            this.prevPitch = this.pitch;
        }

        if(timeTicks < 100)
        {
            setVelocity(0.1, 0.1, 0);
        }
        else
        {
            setVelocity(-0.1, -0.1, 0);
        }

        timeTicks++;
        if(timeTicks > 200)
            timeTicks = 0;
    }
}
