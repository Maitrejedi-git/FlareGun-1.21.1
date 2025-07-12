package net.maitrejedi.flaregunmod.block.custom;

import net.maitrejedi.flaregunmod.entity.custom.FlareProjectileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class InvisibleLightBlock extends Block {
    public InvisibleLightBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        List<FlareProjectileEntity> entities = level.getEntitiesOfClass(FlareProjectileEntity.class, new AABB(pos));
        boolean hasFlareInside = !entities.isEmpty();
        if (hasFlareInside && entities.stream().anyMatch(FlareProjectileEntity::isActive)) {
            level.scheduleTick(pos, this, 1);
        } else {
            level.removeBlock(pos, false);
        }
    }
}
