package dev.electricsteve.mobbiscuits;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BiscuitPressItem extends Item {
    public BiscuitPressItem(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        if (MobBiscuits.BISCUIT_ITEMS.containsKey(target.getType())) {
            ItemStack biscuit = new ItemStack(MobBiscuits.BISCUIT_ITEMS.get(target.getType()));
            if (attacker instanceof ServerPlayer serverPlayer) {
                // TODO: implement pressing mechanism
            }
        }
        super.hurtEnemy(itemStack, target, attacker);
    }
}
