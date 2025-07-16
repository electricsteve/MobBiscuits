package dev.electricsteve.mobbiscuits.neoforge;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.neoforged.fml.common.Mod;

@Mod(MobBiscuits.MOD_ID)
public final class MobBiscuitsNeoForge {
    public MobBiscuitsNeoForge() {
        // Run our common setup.
        MobBiscuits.init();
    }
}
