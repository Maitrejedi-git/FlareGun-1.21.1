package net.maitrejedi.flaregunmod.util;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> FLARE_GUN_AMMO = createTag("flare_gun_ammo");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FlareGunMod.MOD_ID, name));
        }
    }
}
