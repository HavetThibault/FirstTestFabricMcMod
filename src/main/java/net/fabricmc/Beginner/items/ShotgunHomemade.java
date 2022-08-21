package net.fabricmc.Beginner.items;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShotgunHomemade extends BowItem {

    public ShotgunHomemade(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            boolean bl = playerEntity.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }
                boolean bl2 = bl && itemStack.getItem() == Items.ARROW;
                if (!world.isClient)
                {
                    /*ArrowItem arrowItem = (ArrowItem)((itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW));
                    PersistentProjectileEntity arrow1 = arrowItem.createArrow(world, itemStack, playerEntity);
                    arrow1.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 3.0F, 1.0F);

                    PersistentProjectileEntity arrow2 = arrowItem.createArrow(world, itemStack, playerEntity);
                    arrow2.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 4.0F, 1.0F);

                    PersistentProjectileEntity arrow3 = arrowItem.createArrow(world, itemStack, playerEntity);
                    arrow3.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 5.0F, 1.0F);

                    PersistentProjectileEntity arrow4 = arrowItem.createArrow(world, itemStack, playerEntity);
                    arrow4.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 3.0F, 2.0F);

                    PersistentProjectileEntity arrow5 = arrowItem.createArrow(world, itemStack, playerEntity);
                    arrow5.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 4.0F, 2.0F);

                    world.spawnEntity(arrow1);
                    world.spawnEntity(arrow2);
                    world.spawnEntity(arrow3);
                    world.spawnEntity(arrow4);
                    world.spawnEntity(arrow5);*/

                    Vec3d playerPos = playerEntity.getPos();
                    WeirdProjectileEntity weirdArrow = new WeirdProjectileEntity(world, playerPos.x, playerPos.y, playerPos.z);
                    weirdArrow.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 3.0F, 1.0F);
                    world.spawnEntity(weirdArrow);
                }

                world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_METAL_STEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!bl2 && !playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                    if (itemStack.isEmpty()) {
                        playerEntity.inventory.removeOne(itemStack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        return super.use(world, playerEntity, hand);
        /*
        Vec3d playerPos = playerEntity.getPos();
        Entity arrow = new ArrowEntity(world, playerPos.x, playerPos.y + 5, playerPos.z);
        arrow.setInvisible(false);
        arrow.setVelocity(10,10,10);
        */
        //playerEntity.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 1.0F, 1.0F);
        //return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
