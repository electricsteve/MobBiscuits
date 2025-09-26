package dev.electricsteve.mobbiscuits.fabric.client;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MobBiscuitsRecipeProvider extends FabricRecipeProvider {
    public MobBiscuitsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.FOOD, Items.COOKIE, 8)
                        .pattern("WCW")
                        .pattern(" B ")
                        .input('W', Items.WHEAT)
                        .input('C', Items.COCOA_BEANS)
                        .input('B', MobBiscuits.BISCUIT_PRESS.get())
                        .group("mobbiscuits")
                        .criterion(hasItem(MobBiscuits.BISCUIT_PRESS.get()), conditionsFromItem(MobBiscuits.BISCUIT_PRESS.get()))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return "MobBiscuitsRecipeProvider";
    }
}
