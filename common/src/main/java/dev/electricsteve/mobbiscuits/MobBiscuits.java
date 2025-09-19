package dev.electricsteve.mobbiscuits;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Map;

import static java.util.Map.entry;

public final class MobBiscuits {
    public static final String MOD_ID = "mobbiscuits";

    private static final int NUTRITION = 4;
    private static final float SATURATION_MODIFIER = 0.2F;

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    private static RegistrySupplier<Item> registerBiscuit(String name, FoodProperties foodProperties, BiscuitUseFunction biscuitUseFunction) {
        return ITEMS.register(name, () -> new BiscuitItem(new Item.Properties().food(foodProperties).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS), biscuitUseFunction));
    }

    public static final RegistrySupplier<Item> COW_BISCUIT = registerBiscuit("cow_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    livingEntity.removeAllEffects();
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> CREAKING_BISCUIT = registerBiscuit("creaking_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 30));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> CREEPER_BISCUIT = registerBiscuit("creeper_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0F, Level.ExplosionInteraction.MOB);
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> PIG_BISCUIT = registerBiscuit("pig_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION + 1).saturationModifier(SATURATION_MODIFIER + 0.1F).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, 20 * 30, 1));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SHEEP_BISCUIT = registerBiscuit("sheep_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 10, 1));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SKELETON_BISCUIT = registerBiscuit("skeleton_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide && livingEntity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.getInventory().add(new ItemStack(Items.SPECTRAL_ARROW, 8));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SLIME_BISCUIT = registerBiscuit("slime_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 20 * 10, 3));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> ZOMBIE_BISCUIT = registerBiscuit("zombie_biscuit",
            new FoodProperties.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClientSide) {
                    if (livingEntity.getRandom().nextFloat() < 0.9F) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20 * 10));
                    }
                }
                return itemStack;
            }));

    public static final Map<EntityType<? extends LivingEntity>, RegistrySupplier<Item>> BISCUIT_ITEMS = Map.ofEntries(
            entry(EntityType.COW, COW_BISCUIT),
            entry(EntityType.CREAKING, CREAKING_BISCUIT),
            entry(EntityType.CREEPER, CREEPER_BISCUIT),
            entry(EntityType.PIG, PIG_BISCUIT),
            entry(EntityType.SHEEP, SHEEP_BISCUIT),
            entry(EntityType.SKELETON, SKELETON_BISCUIT),
            entry(EntityType.SLIME, SLIME_BISCUIT),
            entry(EntityType.ZOMBIE, ZOMBIE_BISCUIT)
    );

    public static final RegistrySupplier<Item> BISCUIT_PRESS = ITEMS.register("biscuit_press",
            () -> new BiscuitPressItem(new Item.Properties().arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));

    public static void init() {
        ITEMS.register();
    }
}
