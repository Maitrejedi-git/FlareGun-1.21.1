package net.maitrejedi.flaregunmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.maitrejedi.flaregunmod.FlareGunMod;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIFlareGunModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FlareGunMod.MOD_ID, "jei_plugin");
    }
}
