package github.jcsmecabricks.magicmod.entity.custom;

import github.jcsmecabricks.magicmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BroomEntity extends LivingEntity implements Mount {
    private double fallVelocity;
    private ServerWorld world;

    public BroomEntity(EntityType<BroomEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        boolean damaged = super.damage(world, source, amount);

        if (this.getHealth() <= 0) {
            this.onDeath(source);
        }

        return damaged;
    }


    public static DefaultAttributeContainer.Builder createBroomAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 1D)
                .add(EntityAttributes.ARMOR_TOUGHNESS, 0.4F)
                .add(EntityAttributes.MOVEMENT_SPEED, 1D)
                .add(EntityAttributes.ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GRAVITY, 0);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        setRiding(player);
        return ActionResult.SUCCESS;
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        super.onDamaged(damageSource);
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        this.fallVelocity = this.getVelocity().y;
        if (!this.hasVehicle()) {
            if (onGround) {
                this.onLanding();
            } else if (!this.getEntityWorld().getFluidState(this.getBlockPos().down()).isIn(FluidTags.WATER) && heightDifference < 0.0) {
                this.fallDistance -= (float)heightDifference;
            }
        }
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.hasPassengers() && getControllingPassenger() instanceof PlayerEntity player) {
            this.setYaw(player.getYaw());
            this.lastBodyYaw = this.getYaw();

            this.setPitch(player.getPitch() * 0.5F);
            this.setRotation(this.getYaw(), this.getPitch());
            this.bodyYaw = this.getYaw();
            this.headYaw = this.bodyYaw;

            float forward = player.forwardSpeed;
            float sideways = player.sidewaysSpeed;
            float yawRad = (float) Math.toRadians(this.getYaw());

            double x = -Math.sin(yawRad) * forward + Math.cos(yawRad) * sideways;
            double z = Math.cos(yawRad) * forward + Math.sin(yawRad) * sideways;

            float speed = 1.5f;
            if (MinecraftClient.getInstance().options.sprintKey.isPressed()) {
                speed *= 2.0f;
            }

            double y;
            if (MinecraftClient.getInstance().options.jumpKey.isPressed()) {
                y = 1f;
            } else if (MinecraftClient.getInstance().options.swapHandsKey.isPressed()) {
                y = -1f;
            } else {
                y = 0f;
            }

            Vec3d horizontal = new Vec3d(x, 0, z);
            if (horizontal.lengthSquared() > 0) {
                horizontal = horizontal.normalize().multiply(speed);
            }

            Vec3d movement = horizontal.add(0, y, 0);
            this.setVelocity(movement);
            this.move(MovementType.SELF, this.getVelocity());
            this.velocityDirty = true;
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    private void setRiding(PlayerEntity playerEntity) {
        playerEntity.setPitch(this.getPitch());
        playerEntity.setYaw(this.getYaw());
        playerEntity.startRiding(this);
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Direction direction = this.getMovementDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] offsets = Dismounting.getDismountOffsets(direction);
            BlockPos blockPos = this.getBlockPos();
            BlockPos.Mutable blockPosMutable = new BlockPos.Mutable();

            for (EntityPose pose : passenger.getPoses()) {
                Box boundingBox = passenger.getBoundingBox(pose);
                for (int[] offset : offsets) {
                    blockPosMutable.set(blockPos.getX() + offset[0], blockPos.getY(), blockPos.getZ() + offset[1]);
                    BlockState state = this.getEntityWorld().getBlockState(blockPosMutable);
                    VoxelShape shape = state.getCollisionShape(this.getEntityWorld(), blockPosMutable);
                    double d0 = shape.isEmpty() ? 0.0 : shape.getMax(Direction.Axis.Y);
                    if (Dismounting.canDismountInBlock(d0)) {
                        Vec3d vec3d = Vec3d.ofBottomCenter(blockPosMutable).add(d0);
                        if (Dismounting.canPlaceEntityAt(this.getEntityWorld(), passenger, boundingBox.offset(vec3d))) {
                            passenger.setPose(pose);
                            return vec3d;
                        }
                    }
                }
            }
        }
        return super.updatePassengerForDismount(passenger);
    }
}
