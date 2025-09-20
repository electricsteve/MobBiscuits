package dev.electricsteve.mobbiscuits.component;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final DeferredRegister<ComponentType<?>> COMPONENT_TYPES = DeferredRegister.create(MobBiscuits.MOD_ID, RegistryKeys.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<ComponentType<Identifier>> MOB_COMPONENT_TYPE = COMPONENT_TYPES.register(
            Identifier.of(MobBiscuits.MOD_ID, "mob_component"),
            () -> ComponentType.<Identifier>builder().codec(Identifier.CODEC).build()
    );

    public static void initialize() {
        COMPONENT_TYPES.register();
        MobBiscuits.LOGGER.info("Initializing MobBiscuits components");
    }
}
