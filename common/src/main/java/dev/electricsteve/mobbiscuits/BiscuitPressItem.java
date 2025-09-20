package dev.electricsteve.mobbiscuits;

import dev.electricsteve.mobbiscuits.component.ModComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class BiscuitPressItem extends Item {
    public BiscuitPressItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (MobBiscuits.BISCUIT_ITEMS.containsKey(target.getType()) && attacker instanceof ServerPlayerEntity) {
            stack.set(ModComponents.MOB_COMPONENT_TYPE.get(), target.getType().arch$registryName());
        }
        super.postHit(stack, target, attacker);
    }
}
