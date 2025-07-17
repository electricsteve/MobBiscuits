package dev.electricsteve.mobbiscuits;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BiscuitItem extends Item {
    private final BiscuitUseFunction biscuitUseFunction;
    public BiscuitItem(Properties properties, BiscuitUseFunction biscuitUseFunction) {
        super(properties);
        this.biscuitUseFunction = biscuitUseFunction;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        ItemStack itemStack1 = super.finishUsingItem(itemStack, level, livingEntity);
        if (biscuitUseFunction != null) {
            return biscuitUseFunction.apply(itemStack1, level, livingEntity);
        }
        return itemStack1;
    }
}
