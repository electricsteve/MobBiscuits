package dev.electricsteve.mobbiscuits;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public final class MobBiscuits {
    public static final String MOD_ID = "mobbiscuits";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> CREEPER_BISCUIT = ITEMS.register("creeper_biscuit", () -> new Item(new Item.Properties()));

    public static void init() {
        ITEMS.register();
    }
}
