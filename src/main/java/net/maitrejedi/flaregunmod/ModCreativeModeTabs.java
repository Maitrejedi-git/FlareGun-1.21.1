package net.maitrejedi.flaregunmod;

import net.maitrejedi.flaregunmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FlareGunMod.MOD_ID);

    public static final Supplier<CreativeModeTab> FLARE_GUN_TAB = CREATIVE_MOD_TABS.register("flare_gun_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.FLARE_GUN.get()))
                    .title(Component.translatable("creativetab.flaregunmod.flare_gun"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLARE_GUN);
                        output.accept(ModItems.FLARE);
                        output.accept(ModItems.FLARE_WHITE);
                        output.accept(ModItems.FLARE_LIGHT_GRAY);
                        output.accept(ModItems.FLARE_GRAY);
                        output.accept(ModItems.FLARE_BLACK);
                        output.accept(ModItems.FLARE_BROWN);
                        output.accept(ModItems.FLARE_RED);
                        output.accept(ModItems.FLARE_ORANGE);
                        output.accept(ModItems.FLARE_YELLOW);
                        output.accept(ModItems.FLARE_LIME);
                        output.accept(ModItems.FLARE_GREEN);
                        output.accept(ModItems.FLARE_CYAN);
                        output.accept(ModItems.FLARE_LIGHT_BLUE);
                        output.accept(ModItems.FLARE_BLUE);
                        output.accept(ModItems.FLARE_PURPLE);
                        output.accept(ModItems.FLARE_MAGENTA);
                        output.accept(ModItems.FLARE_PINK);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
