package net.maitrejedi.flaregunmod.datagen;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FlareGunMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.FLARE.get());
        basicItem(ModItems.FLARE_BLACK.get());
        basicItem(ModItems.FLARE_BLUE.get());
        basicItem(ModItems.FLARE_BROWN.get());
        basicItem(ModItems.FLARE_CYAN.get());
        basicItem(ModItems.FLARE_GRAY.get());
        basicItem(ModItems.FLARE_GREEN.get());
        basicItem(ModItems.FLARE_LIGHT_BLUE.get());
        basicItem(ModItems.FLARE_LIGHT_GRAY.get());
        basicItem(ModItems.FLARE_LIME.get());
        basicItem(ModItems.FLARE_MAGENTA.get());
        basicItem(ModItems.FLARE_ORANGE.get());
        basicItem(ModItems.FLARE_PINK.get());
        basicItem(ModItems.FLARE_PURPLE.get());
        basicItem(ModItems.FLARE_RED.get());
        basicItem(ModItems.FLARE_WHITE.get());
        basicItem(ModItems.FLARE_YELLOW.get());
    }
}
