package dev.electricsteve.mobbiscuits;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public final class MobBiscuits {
    public static final String MOD_ID = "mobbiscuits";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    private static RegistrySupplier<Item> registerBiscuit(String name, FoodProperties foodProperties, BiscuitUseFunction biscuitUseFunction) {
        return ITEMS.register(name, () -> new BiscuitItem(new Item.Properties().food(foodProperties), biscuitUseFunction));
    }

    public static final RegistrySupplier<Item> CREEPER_BISCUIT = registerBiscuit("creeper_biscuit",
            new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0F, Level.ExplosionInteraction.MOB);
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> COW_BISCUIT = registerBiscuit("cow_biscuit",
            new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build(),
            ((itemStack, level, livingEntity) -> {
                // WIP
                return itemStack;
            }));

    public static void init() {
        ITEMS.register();
    }
}
