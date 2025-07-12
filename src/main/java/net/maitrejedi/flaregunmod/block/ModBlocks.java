package net.maitrejedi.flaregunmod.block;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.block.custom.InvisibleLightBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FlareGunMod.MOD_ID);

    public static final DeferredBlock<Block> INVISIBLE_LIGHT_BLOCK = BLOCKS.register("invisible_light_block",
            () -> new InvisibleLightBlock(BlockBehaviour.Properties.of().noCollission().noOcclusion().lightLevel(l -> 15)
                    .air().destroyTime(0.0F).pushReaction(PushReaction.IGNORE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
