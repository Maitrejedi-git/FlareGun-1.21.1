package net.maitrejedi.flaregunmod.entity.custom;

import net.maitrejedi.flaregunmod.block.ModBlocks;
import net.maitrejedi.flaregunmod.entity.ModEntities;
import net.maitrejedi.flaregunmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;

import java.util.Map;

public class FlareProjectileEntity extends AbstractArrow {
    private static final Map<FlareColor, Block> BLOCK_BY_COLOR = Map.ofEntries(
            Map.entry(FlareColor.BLACK, Blocks.BLACK_CONCRETE),
            Map.entry(FlareColor.BLUE, Blocks.BLUE_CONCRETE),
            Map.entry(FlareColor.BROWN, Blocks.BROWN_CONCRETE),
            Map.entry(FlareColor.CYAN, Blocks.CYAN_CONCRETE),
            Map.entry(FlareColor.GRAY, Blocks.GRAY_CONCRETE),
            Map.entry(FlareColor.GREEN, Blocks.GREEN_CONCRETE),
            Map.entry(FlareColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_CONCRETE),
            Map.entry(FlareColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_CONCRETE),
            Map.entry(FlareColor.LIME, Blocks.LIME_CONCRETE),
            Map.entry(FlareColor.MAGENTA, Blocks.MAGENTA_CONCRETE),
            Map.entry(FlareColor.ORANGE, Blocks.ORANGE_CONCRETE),
            Map.entry(FlareColor.PINK, Blocks.PINK_CONCRETE),
            Map.entry(FlareColor.PURPLE, Blocks.PURPLE_CONCRETE),
            Map.entry(FlareColor.RED, Blocks.RED_CONCRETE),
            Map.entry(FlareColor.WHITE, Blocks.WHITE_CONCRETE),
            Map.entry(FlareColor.YELLOW, Blocks.YELLOW_CONCRETE)
    );

    private FlareColor color;
    private int particleCounter = 0;
    private int customInGroundTime = 0;
    private boolean isActive = true;
    public Vec2 groundedOffset;

    public FlareProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public FlareProjectileEntity(LivingEntity shooter, Level level, FlareColor color)  {
        super(ModEntities.FLARE.get(), shooter, level, new ItemStack(ModItems.FLARE.get()), null);
        this.color = color;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @Override
    protected boolean tryPickup(Player player) {
        return false;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
        if (isActive) {
            entity.igniteForSeconds(2);
        }

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if(result.getDirection() == Direction.SOUTH) {
            groundedOffset = new Vec2(215f,180f);
        }
        if(result.getDirection() == Direction.NORTH) {
            groundedOffset = new Vec2(215f, 0f);
        }
        if(result.getDirection() == Direction.EAST) {
            groundedOffset = new Vec2(215f,-90f);
        }
        if(result.getDirection() == Direction.WEST) {
            groundedOffset = new Vec2(215f,90f);
        }

        if(result.getDirection() == Direction.DOWN) {
            groundedOffset = new Vec2(115f,180f);
        }
        if(result.getDirection() == Direction.UP) {
            groundedOffset = new Vec2(285f,180f);
        }
    }

    @Override
    protected void tickDespawn() {
        if (++customInGroundTime >= 10800) {
            super.tickDespawn();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void tick() {
        super.tick();

        if (!isActive) {
            return;
        }

        if (!level().isClientSide) {
            if (this.isInLiquid()) {
                isActive = false;
                return;
            }

            if (++particleCounter >= 5) {
                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, BLOCK_BY_COLOR.get(color).defaultBlockState()),
                        this.getX(), this.getY(),
                        this.getZ(), 5, 0, 0, 0, 1);
                particleCounter = 0;
            }

            BlockPos pos = this.blockPosition();
            if (this.level().getBlockState(pos).isAir()) {
                this.level().setBlockAndUpdate(pos, ModBlocks.INVISIBLE_LIGHT_BLOCK.get().defaultBlockState());
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putShort("customInGroundTime", (short) this.customInGroundTime);
        compound.putBoolean("isActive", this.isActive);
        compound.putString("color", this.color.name());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.customInGroundTime = compound.getShort("customInGroundTime");
        this.isActive = compound.getBoolean("isActive");
        this.color = FlareColor.valueOf(compound.getString("color"));
    }
}
