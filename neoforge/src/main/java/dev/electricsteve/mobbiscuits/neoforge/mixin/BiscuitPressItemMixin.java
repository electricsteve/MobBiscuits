package dev.electricsteve.mobbiscuits.neoforge.mixin;

import dev.electricsteve.mobbiscuits.BiscuitPressItem;
import dev.electricsteve.mobbiscuits.MobBiscuits;
import dev.electricsteve.mobbiscuits.component.ModComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BiscuitPressItem.class)
public class BiscuitPressItemMixin implements IItemExtension {

    // IDK why, but it said it was required
    @Override
    public boolean isCombineRepairable(@NotNull ItemStack arg) {
        return false;
    }

    @Override
    public @NotNull ItemStack getCraftingRemainder(@NotNull ItemStack stack) {
        ItemStack remainder = new ItemStack(MobBiscuits.BISCUIT_PRESS.get());
        Identifier id = stack.get(ModComponents.MOB_COMPONENT_TYPE.get());
        if (id != null) {
            remainder.set(ModComponents.MOB_COMPONENT_TYPE.get(), id);
        }
        return remainder;
    }
}
