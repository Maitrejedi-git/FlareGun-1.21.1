package net.maitrejedi.flaregunmod.datagen;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.item.ModItems;
import net.maitrejedi.flaregunmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FlareGunMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.FLARE_GUN_AMMO)
                .add(ModItems.FLARE_BLACK.get())
                .add(ModItems.FLARE_BLUE.get())
                .add(ModItems.FLARE_BROWN.get())
                .add(ModItems.FLARE_CYAN.get())
                .add(ModItems.FLARE_GRAY.get())
                .add(ModItems.FLARE_GREEN.get())
                .add(ModItems.FLARE_LIGHT_BLUE.get())
                .add(ModItems.FLARE_LIGHT_GRAY.get())
                .add(ModItems.FLARE_LIME.get())
                .add(ModItems.FLARE_MAGENTA.get())
                .add(ModItems.FLARE_ORANGE.get())
                .add(ModItems.FLARE_PINK.get())
                .add(ModItems.FLARE_PURPLE.get())
                .add(ModItems.FLARE_RED.get())
                .add(ModItems.FLARE_WHITE.get())
                .add(ModItems.FLARE_YELLOW.get());
    }
}
