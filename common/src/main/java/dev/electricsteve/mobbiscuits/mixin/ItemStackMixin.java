package dev.electricsteve.mobbiscuits.mixin;

import dev.electricsteve.mobbiscuits.MobBiscuits;
import dev.electricsteve.mobbiscuits.component.ModComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getTooltip", at = @At(value = "RETURN", ordinal = 1))
    private void getTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        List<Text> tooltip = cir.getReturnValue();
        ItemStack itemStack = (ItemStack) (Object) this;
        if (!itemStack.isOf(MobBiscuits.BISCUIT_PRESS.get())) {
            return;
        }
        Identifier identifier = itemStack.get(ModComponents.MOB_COMPONENT_TYPE.get());
        if (identifier != null) {
            tooltip.add(Text.translatable("item.mobbiscuits.biscuit_press.tooltip", Text.translatable(EntityType.get(identifier.toString()).get().getTranslationKey()).withColor(Colors.YELLOW)).withColor(Colors.GRAY));
        }
    }
}
