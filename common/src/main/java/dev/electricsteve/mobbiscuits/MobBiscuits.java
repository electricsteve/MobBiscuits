package dev.electricsteve.mobbiscuits;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static java.util.Map.entry;

public final class MobBiscuits {
    public static final String MOD_ID = "mobbiscuits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final int NUTRITION = 4;
    private static final float SATURATION_MODIFIER = 0.2F;

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);

    private static RegistrySupplier<Item> registerBiscuit(String name, FoodComponent foodComponent, BiscuitUseFunction biscuitUseFunction) {
        return ITEMS.register(name, () -> new BiscuitItem(new Item.Settings().food(foodComponent).arch$tab(ItemGroups.FOOD_AND_DRINK), biscuitUseFunction));
    }

    public static final RegistrySupplier<Item> COW_BISCUIT = registerBiscuit("cow_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    livingEntity.clearStatusEffects();
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> CREAKING_BISCUIT = registerBiscuit("creaking_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 20 * 30));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> CREEPER_BISCUIT = registerBiscuit("creeper_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    level.createExplosion(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0F, World.ExplosionSourceType.MOB);
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> PIG_BISCUIT = registerBiscuit("pig_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION + 1).saturationModifier(SATURATION_MODIFIER + 0.1F).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 30, 1));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SHEEP_BISCUIT = registerBiscuit("sheep_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 10, 1));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SKELETON_BISCUIT = registerBiscuit("skeleton_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient && livingEntity instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.getInventory().insertStack(new ItemStack(Items.SPECTRAL_ARROW, 8));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> SLIME_BISCUIT = registerBiscuit("slime_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 10, 3));
                }
                return itemStack;
            }));

    public static final RegistrySupplier<Item> ZOMBIE_BISCUIT = registerBiscuit("zombie_biscuit",
            new FoodComponent.Builder().nutrition(NUTRITION).saturationModifier(SATURATION_MODIFIER).build(),
            ((itemStack, level, livingEntity) -> {
                if (!level.isClient) {
                    if (livingEntity.getRandom().nextFloat() < 0.9F) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20 * 10));
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
            () -> new BiscuitPressItem(new Item.Settings().arch$tab(ItemGroups.TOOLS)));

    public static void init() {
        ITEMS.register();
    }
}
