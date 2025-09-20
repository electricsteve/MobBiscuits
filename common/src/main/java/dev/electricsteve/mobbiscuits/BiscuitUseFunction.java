package dev.electricsteve.mobbiscuits;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@FunctionalInterface
public interface BiscuitUseFunction {
    /**
     * Applies the biscuit's effect when used.
     *
     * @param itemStack The ItemStack after being used. (player has effects and items tack has shrunk)
     * @param world The current game level.
     * @param livingEntity The LivingEntity using the biscuit.
     * @return The modified ItemStack after applying the effect.
     */
    ItemStack apply(ItemStack itemStack, World world, LivingEntity livingEntity);
}
