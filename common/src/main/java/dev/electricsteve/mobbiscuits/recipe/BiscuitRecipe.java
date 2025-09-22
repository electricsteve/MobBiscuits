package dev.electricsteve.mobbiscuits.recipe;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import dev.electricsteve.mobbiscuits.component.ModComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class BiscuitRecipe extends SpecialCraftingRecipe {
    public BiscuitRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    private static final List<Item> INGREDIENT_LIST = List.of(
            Items.WHEAT,
            Items.COCOA_BEANS,
            Items.WHEAT,
            Items.AIR,
            MobBiscuits.BISCUIT_PRESS.get(),
            Items.AIR
    );

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        String string = "size: " + input.size() + ", width: " + input.getWidth() + ", height: " + input.getHeight() + ", stacks: [" + input.getStacks() + "]";
        MobBiscuits.LOGGER.info("matches called, input: {}", string);
        if (input.size() != INGREDIENT_LIST.size()) {
            return false;
        }
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            Item requiredItem = INGREDIENT_LIST.get(i);
            if (stack.isEmpty() && requiredItem == Items.AIR) {
                continue; // Both are empty, continue to next slot
            }
            if (stack.getItem() != requiredItem) {
                return false; // Mismatch found
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        String string = "size: " + input.size() + ", width: " + input.getWidth() + ", height: " + input.getHeight() + ", stacks: [" + input.getStacks() + "]";
        MobBiscuits.LOGGER.info("craft called, input: {}", string);
        if (input.size() != INGREDIENT_LIST.size()) {
            MobBiscuits.LOGGER.info("craft failed, input size does not match ingredient list size");
            return ItemStack.EMPTY;
        }
        ItemStack outputStack = new ItemStack(Items.AIR);
        for (int i = 0; i < input.size(); i++) {
            ItemStack stackInSlot = input.getStackInSlot(i);
            Item requiredItem = INGREDIENT_LIST.get(i);
            if (stackInSlot.isEmpty() && requiredItem == Items.AIR) {
                continue; // Both are empty, continue to next slot
            }
            if (stackInSlot.getItem() != requiredItem) {
                MobBiscuits.LOGGER.info("craft failed, item mismatch at slot {}: expected {}, found {}", i, requiredItem, stackInSlot.getItem());
                return ItemStack.EMPTY; // Mismatch found
            }
            if (stackInSlot.isOf(MobBiscuits.BISCUIT_PRESS.get())) {
                MobBiscuits.LOGGER.info("crafting with biscuit press, checking for mob data");
                Identifier identifier = stackInSlot.get(ModComponents.MOB_COMPONENT_TYPE.get());
                Optional<EntityType<?>> entityType = Registries.ENTITY_TYPE.getOptionalValue(identifier);
                MobBiscuits.LOGGER.info("identifier: {}, entityType: {}", identifier, entityType);
                if (identifier != null && entityType.isPresent() && MobBiscuits.BISCUIT_ITEMS.containsKey(entityType.get())) {
                    MobBiscuits.LOGGER.info("mob data found: {}, crafting biscuit for {}", identifier, entityType.get().arch$registryName());
                    outputStack = new ItemStack(MobBiscuits.BISCUIT_ITEMS.get(entityType.get()).get());
                }
            }
        }
        return outputStack;
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return ModRecipeTypes.BISCUIT_RECIPE.get();
    }
}
