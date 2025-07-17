package dev.electricsteve.mobbiscuits;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface BiscuitUseFunction {
    /**
     * Applies the biscuit's effect when used.
     *
     * @param itemStack The ItemStack after being used. (player has effects and items tack has shrunk)
     * @param level The current game level.
     * @param livingEntity The LivingEntity using the biscuit.
     * @return The modified ItemStack after applying the effect.
     */
    ItemStack apply(ItemStack itemStack, Level level, LivingEntity livingEntity);
}
