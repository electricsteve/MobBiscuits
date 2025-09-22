package dev.electricsteve.mobbiscuits.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.electricsteve.mobbiscuits.MobBiscuits;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.RegistryKeys;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(MobBiscuits.MOD_ID, RegistryKeys.RECIPE_SERIALIZER);

    public static final RegistrySupplier<RecipeSerializer<BiscuitRecipe>> BISCUIT_RECIPE = RECIPE_SERIALIZERS.register(
            "biscuit_pressing",
            () -> new SpecialCraftingRecipe.SpecialRecipeSerializer<>(BiscuitRecipe::new)
    );

    public static void initialize() {
        RECIPE_SERIALIZERS.register();
        MobBiscuits.LOGGER.info("Initializing MobBiscuits recipe serializers");
    }
}
