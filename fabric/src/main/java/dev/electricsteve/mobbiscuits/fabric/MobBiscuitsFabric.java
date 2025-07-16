package dev.electricsteve.mobbiscuits.fabric;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.fabricmc.api.ModInitializer;

public final class MobBiscuitsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        MobBiscuits.init();
    }
}
