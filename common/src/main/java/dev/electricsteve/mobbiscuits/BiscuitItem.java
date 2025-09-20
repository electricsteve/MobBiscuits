package dev.electricsteve.mobbiscuits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BiscuitItem extends Item {
    private final BiscuitUseFunction biscuitUseFunction;
    public BiscuitItem(Settings properties, BiscuitUseFunction biscuitUseFunction) {
        super(properties);
        this.biscuitUseFunction = biscuitUseFunction;
    }

    @Override
    public @NotNull ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity livingEntity) {
        ItemStack itemStack1 = super.finishUsing(itemStack, world, livingEntity);
        if (biscuitUseFunction != null) {
            return biscuitUseFunction.apply(itemStack1, world, livingEntity);
        }
        return itemStack1;
    }
}
