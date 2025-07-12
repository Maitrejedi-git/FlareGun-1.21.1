package net.maitrejedi.flaregunmod.item;

import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.item.custom.FlareGunItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FlareGunMod.MOD_ID);

    public static final DeferredItem<Item> FLARE_GUN = ITEMS.register("flare_gun",
            () -> new FlareGunItem(new Item.Properties().stacksTo(1).durability(384)));

    public static final DeferredItem<Item> FLARE = ITEMS.register("flare",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_BLACK = ITEMS.register("flare_black",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_BLUE = ITEMS.register("flare_blue",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_BROWN = ITEMS.register("flare_brown",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_CYAN = ITEMS.register("flare_cyan",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_GRAY = ITEMS.register("flare_gray",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_GREEN = ITEMS.register("flare_green",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_LIGHT_BLUE = ITEMS.register("flare_light_blue",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_LIGHT_GRAY = ITEMS.register("flare_light_gray",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_LIME = ITEMS.register("flare_lime",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_MAGENTA = ITEMS.register("flare_magenta",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_ORANGE = ITEMS.register("flare_orange",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_PINK = ITEMS.register("flare_pink",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_PURPLE = ITEMS.register("flare_purple",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_RED = ITEMS.register("flare_red",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_WHITE = ITEMS.register("flare_white",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLARE_YELLOW = ITEMS.register("flare_yellow",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
