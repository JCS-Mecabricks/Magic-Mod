package github.jcsmecabricks.magicmod.item.custom;

import github.jcsmecabricks.magicmod.entity.custom.BroomEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BroomItem extends Item {
    private final EntityType<? extends BroomEntity> broomEntityType;

    public BroomItem(EntityType<? extends BroomEntity> broomEntityType, Item.Settings settings) {
        super(settings);
        this.broomEntityType = broomEntityType;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return ActionResult.PASS;
        } else {
            Vec3d vec3d = user.getRotationVec(1.0F);
            double d = 5.0;
            List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5.0)).expand(1.0), EntityPredicates.CAN_HIT);

            if (!list.isEmpty()) {
                Vec3d vec3d2 = user.getEyePos();

                for (Entity entity : list) {
                    Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                    if (box.contains(vec3d2)) {
                        return ActionResult.PASS;
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BroomEntity broomEntity = this.createEntity(world, hitResult, itemStack, user);
                if (broomEntity == null) {
                    return ActionResult.FAIL;
                } else {
                    broomEntity.setYaw(user.getYaw());
                    broomEntity.setPitch(user.getPitch());

                    if (!world.isSpaceEmpty(broomEntity, broomEntity.getBoundingBox())) {
                        return ActionResult.FAIL;
                    } else {
                        if (!world.isClient()) {
                            world.spawnEntity(broomEntity);
                            world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getPos());
                            itemStack.decrement(1);
                        }

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return ActionResult.SUCCESS;
                    }
                }
            } else {
                return ActionResult.PASS;
            }
        }
    }

    @Nullable
    private BroomEntity createEntity(World world, HitResult hitResult, ItemStack stack, PlayerEntity player) {
        BroomEntity broomEntity = this.broomEntityType.create(world, SpawnReason.LOAD);
        if (broomEntity != null) {
            Vec3d pos = hitResult.getPos();
            broomEntity.refreshPositionAndAngles(pos.x, pos.y, pos.z, 0.0F, 0.0F);

            broomEntity.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(1.0);
            broomEntity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(1.0);

        }
        return broomEntity;
    }
}
