package net.maitrejedi.flaregunmod.event;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.entity.client.FlareProjectileModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = FlareGunMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FlareProjectileModel.LAYER_LOCATION, FlareProjectileModel::createBodyLayer);
    }
}
