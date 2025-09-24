package dev.electricsteve.mobbiscuits.fabric.mixin;

import dev.electricsteve.mobbiscuits.BiscuitPressItem;
import dev.electricsteve.mobbiscuits.MobBiscuits;
import dev.electricsteve.mobbiscuits.component.ModComponents;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BiscuitPressItem.class)
public class BiscuitPressItemMixin implements FabricItem {
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack remainder = new ItemStack(MobBiscuits.BISCUIT_PRESS.get());
        Identifier id = stack.get(ModComponents.MOB_COMPONENT_TYPE.get());
        if (id != null) {
            remainder.set(ModComponents.MOB_COMPONENT_TYPE.get(), id);
        }
        return remainder;
    }
}
