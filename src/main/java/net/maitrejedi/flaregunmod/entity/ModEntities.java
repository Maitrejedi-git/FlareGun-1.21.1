package net.maitrejedi.flaregunmod.entity;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.entity.custom.FlareProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FlareGunMod.MOD_ID);

    public static final Supplier<EntityType<FlareProjectileEntity>> FLARE =
            ENTITY_TYPES.register("flare", () -> EntityType.Builder.<FlareProjectileEntity>of(FlareProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("flare"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
