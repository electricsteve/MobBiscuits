package dev.electricsteve.mobbiscuits.fabric.client;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class MobBiscuitsModelProvider extends FabricModelProvider {
    public MobBiscuitsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        MobBiscuits.ITEMS.forEach(itemRegistrySupplier -> itemModelGenerator.register(itemRegistrySupplier.get(), Models.GENERATED));
    }

    @Override
    public String getName() {
        return "Mob Biscuits Model Provider";
    }
}
