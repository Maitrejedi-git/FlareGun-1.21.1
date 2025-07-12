package net.maitrejedi.flaregunmod.item.custom;

import net.maitrejedi.flaregunmod.entity.custom.FlareColor;
import net.maitrejedi.flaregunmod.entity.custom.FlareProjectileEntity;
import net.maitrejedi.flaregunmod.item.ModItems;
import net.maitrejedi.flaregunmod.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Predicate;

public class FlareGunItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> FLARE_GUN_ONLY = item -> item.is(ModTags.Items.FLARE_GUN_AMMO);
    private static final Map<DeferredItem<Item>, FlareColor> COLOR_BY_FLARE = Map.ofEntries(
            Map.entry(ModItems.FLARE_BLACK, FlareColor.BLACK),
            Map.entry(ModItems.FLARE_BLUE, FlareColor.BLUE),
            Map.entry(ModItems.FLARE_BROWN, FlareColor.BROWN),
            Map.entry(ModItems.FLARE_CYAN, FlareColor.CYAN),
            Map.entry(ModItems.FLARE_GRAY, FlareColor.GRAY),
            Map.entry(ModItems.FLARE_GREEN, FlareColor.GREEN),
            Map.entry(ModItems.FLARE_LIGHT_BLUE, FlareColor.LIGHT_BLUE),
            Map.entry(ModItems.FLARE_LIGHT_GRAY, FlareColor.LIGHT_GRAY),
            Map.entry(ModItems.FLARE_LIME, FlareColor.LIME),
            Map.entry(ModItems.FLARE_MAGENTA, FlareColor.MAGENTA),
            Map.entry(ModItems.FLARE_ORANGE, FlareColor.ORANGE),
            Map.entry(ModItems.FLARE_PINK, FlareColor.PINK),
            Map.entry(ModItems.FLARE_PURPLE, FlareColor.PURPLE),
            Map.entry(ModItems.FLARE_RED, FlareColor.RED),
            Map.entry(ModItems.FLARE_WHITE, FlareColor.WHITE),
            Map.entry(ModItems.FLARE_YELLOW, FlareColor.YELLOW)
    );

    public FlareGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return FLARE_GUN_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        ItemStack projectile = pPlayer.getProjectile(itemstack);
        if (!pPlayer.hasInfiniteMaterials() && projectile.isEmpty()) {
            return InteractionResultHolder.fail(itemstack);
        }

        FlareColor color = FlareColor.RED;
        for (Map.Entry<DeferredItem<Item>, FlareColor> entry : COLOR_BY_FLARE.entrySet()) {

            if (projectile.getItem().equals(entry.getKey().get())) {
                color = entry.getValue();
                break;
            }
        }

        draw(itemstack, projectile, pPlayer);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F));
        if (!pLevel.isClientSide) {
            FlareProjectileEntity flareProjectile = new FlareProjectileEntity(pPlayer, pLevel, color);
            shootProjectile(pPlayer, flareProjectile, 0, 3F, 0F, 0F, null);
            pLevel.addFreshEntity(flareProjectile);
        }

        itemstack.hurtAndBreak(this.getDurabilityUse(itemstack), pPlayer, LivingEntity.getSlotForHand(pUsedHand));
        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.pass(itemstack);
    }
}
