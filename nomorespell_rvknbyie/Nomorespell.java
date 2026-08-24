/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
 *  net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.fabricmc.fabric.api.networking.v1.PlayerLookup
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder
 *  net.minecraft.Formatting
 *  net.minecraft.Hand
 *  net.minecraft.StatusEffect
 *  net.minecraft.EntityType
 *  net.minecraft.LivingEntity
 *  net.minecraft.SpawnGroup
 *  net.minecraft.PlayerEntity
 *  net.minecraft.Item
 *  net.minecraft.Item$class_1793
 *  net.minecraft.ItemStack
 *  net.minecraft.ItemConvertible
 *  net.minecraft.World
 *  net.minecraft.ServerCommandSource
 *  net.minecraft.CommandManager
 *  net.minecraft.Registry
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.Packet
 *  net.minecraft.ExperienceBarUpdateS2CPacket
 *  net.minecraft.Identifier
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  net.minecraft.SoundEvent
 *  net.minecraft.EntityDimensions
 *  net.minecraft.StatusEffectCategory
 *  net.minecraft.RegistryKey
 *  net.minecraft.ItemGroups
 *  net.minecraft.Registries
 *  net.minecraft.RegistryKeys
 *  net.minecraft.DamageTypes
 *  net.minecraft.CustomPayload
 *  net.minecraft.LoreComponent
 *  net.minecraft.DataComponentTypes
 *  net.minecraft.server.MinecraftServer
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package nomorespell_rvknbyie;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.Formatting;
import net.minecraft.Hand;
import net.minecraft.StatusEffect;
import net.minecraft.EntityType;
import net.minecraft.LivingEntity;
import net.minecraft.SpawnGroup;
import net.minecraft.PlayerEntity;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.ItemConvertible;
import net.minecraft.World;
import net.minecraft.ServerCommandSource;
import net.minecraft.CommandManager;
import net.minecraft.Registry;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.Packet;
import net.minecraft.ExperienceBarUpdateS2CPacket;
import net.minecraft.Identifier;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import net.minecraft.SoundEvent;
import net.minecraft.EntityDimensions;
import net.minecraft.StatusEffectCategory;
import net.minecraft.RegistryKey;
import net.minecraft.ItemGroups;
import net.minecraft.Registries;
import net.minecraft.RegistryKeys;
import net.minecraft.DamageTypes;
import net.minecraft.CustomPayload;
import net.minecraft.LoreComponent;
import net.minecraft.DataComponentTypes;
import net.minecraft.server.MinecraftServer;
import nomorespell_rvknbyie.entity.SpellFireballEntity;
import nomorespell_rvknbyie.item.NomorespellItem;
import nomorespell_rvknbyie.network.BloodEclipseRenderPayload;
import nomorespell_rvknbyie.network.CastSpellPayload;
import nomorespell_rvknbyie.network.DomainRenderSyncPayload;
import nomorespell_rvknbyie.network.EquipSpellPayload;
import nomorespell_rvknbyie.network.PurchaseSpellPayload;
import nomorespell_rvknbyie.network.RainOfPicksRenderPayload;
import nomorespell_rvknbyie.network.SacrificeLevelsPayload;
import nomorespell_rvknbyie.network.VerdantHaloRenderPayload;
import nomorespell_rvknbyie.spell.CombatXpTracker;
import nomorespell_rvknbyie.spell.DomainExpansionManager;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellBookComponents;
import nomorespell_rvknbyie.spell.SpellBookData;
import nomorespell_rvknbyie.spell.SpellRegistry;
import nomorespell_rvknbyie.spell.SpellVisualsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nomorespell
implements ModInitializer {
    public static final String MOD_ID = "nomorespell-rvknbyie";
    public static final Logger LOGGER = LoggerFactory.getLogger((String)"nomorespell-rvknbyie");
    public static final Identifier FIREBALL_CAST_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"fireball_cast");
    public static final SoundEvent FIREBALL_CAST_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)FIREBALL_CAST_SOUND_ID, (Object)SoundEvent.of((Identifier)FIREBALL_CAST_SOUND_ID));
    public static final Identifier SACRIFICE_SUCCESS_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"sacrifice_success_chime");
    public static final SoundEvent SACRIFICE_SUCCESS_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)SACRIFICE_SUCCESS_SOUND_ID, (Object)SoundEvent.of((Identifier)SACRIFICE_SUCCESS_SOUND_ID));
    public static final Identifier SACRIFICE_CHIME_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"sacrifice_chime");
    public static final SoundEvent SACRIFICE_CHIME_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)SACRIFICE_CHIME_SOUND_ID, (Object)SoundEvent.of((Identifier)SACRIFICE_CHIME_SOUND_ID));
    public static final Identifier FIREBALL_EXPLOSION_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"fireball_explosion");
    public static final SoundEvent FIREBALL_EXPLOSION_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)FIREBALL_EXPLOSION_SOUND_ID, (Object)SoundEvent.of((Identifier)FIREBALL_EXPLOSION_SOUND_ID));
    public static final Identifier SWIFTNESS_AURA_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"swiftness_aura_sounds");
    public static final SoundEvent SWIFTNESS_AURA_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)SWIFTNESS_AURA_SOUND_ID, (Object)SoundEvent.of((Identifier)SWIFTNESS_AURA_SOUND_ID));
    public static final Identifier FROST_NOVA_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"frost_nova");
    public static final SoundEvent FROST_NOVA_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)FROST_NOVA_SOUND_ID, (Object)SoundEvent.of((Identifier)FROST_NOVA_SOUND_ID));
    public static final Identifier GROUP_HEAL_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"group_heal_sounds");
    public static final SoundEvent GROUP_HEAL_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)GROUP_HEAL_SOUND_ID, (Object)SoundEvent.of((Identifier)GROUP_HEAL_SOUND_ID));
    public static final Identifier BATTLE_BOOST_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"battle_boost_sfx");
    public static final SoundEvent BATTLE_BOOST_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)BATTLE_BOOST_SOUND_ID, (Object)SoundEvent.of((Identifier)BATTLE_BOOST_SOUND_ID));
    public static final Identifier CHAIN_LIGHTNING_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"chain_lightning_sfx");
    public static final SoundEvent CHAIN_LIGHTNING_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)CHAIN_LIGHTNING_SOUND_ID, (Object)SoundEvent.of((Identifier)CHAIN_LIGHTNING_SOUND_ID));
    public static final Identifier SACRED_CIRCLE_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"sacred_circle_sounds");
    public static final SoundEvent SACRED_CIRCLE_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)SACRED_CIRCLE_SOUND_ID, (Object)SoundEvent.of((Identifier)SACRED_CIRCLE_SOUND_ID));
    public static final Identifier TEAM_FORTRESS_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"team_fortress_sfx_pack");
    public static final SoundEvent TEAM_FORTRESS_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)TEAM_FORTRESS_SOUND_ID, (Object)SoundEvent.of((Identifier)TEAM_FORTRESS_SOUND_ID));
    public static final Identifier METEOR_STRIKE_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"meteor_strike_sfx");
    public static final SoundEvent METEOR_STRIKE_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)METEOR_STRIKE_SOUND_ID, (Object)SoundEvent.of((Identifier)METEOR_STRIKE_SOUND_ID));
    public static final Identifier DOMAIN_EXPANSION_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"domain_expansion_sfx");
    public static final SoundEvent DOMAIN_EXPANSION_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)DOMAIN_EXPANSION_SOUND_ID, (Object)SoundEvent.of((Identifier)DOMAIN_EXPANSION_SOUND_ID));
    public static final Identifier DOMAIN_SPHERE_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"domain_sphere_sound");
    public static final SoundEvent DOMAIN_SPHERE_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)DOMAIN_SPHERE_SOUND_ID, (Object)SoundEvent.of((Identifier)DOMAIN_SPHERE_SOUND_ID));
    public static final Identifier DOMAIN_SPHERE_AMBIENCE_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"domain_sphere_ambience");
    public static final SoundEvent DOMAIN_SPHERE_AMBIENCE_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)DOMAIN_SPHERE_AMBIENCE_SOUND_ID, (Object)SoundEvent.of((Identifier)DOMAIN_SPHERE_AMBIENCE_SOUND_ID));
    public static final Identifier BLOOD_ECLIPSE_BEAM_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"blood_eclipse_beam");
    public static final SoundEvent BLOOD_ECLIPSE_BEAM_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)BLOOD_ECLIPSE_BEAM_SOUND_ID, (Object)SoundEvent.of((Identifier)BLOOD_ECLIPSE_BEAM_SOUND_ID));
    public static final Identifier VERDANT_HALO_ANGELIC_SOUND_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"verdant_halo_angelic");
    public static final SoundEvent VERDANT_HALO_ANGELIC_SOUND = (SoundEvent)Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)VERDANT_HALO_ANGELIC_SOUND_ID, (Object)SoundEvent.of((Identifier)VERDANT_HALO_ANGELIC_SOUND_ID));
    public static final Identifier ITEM_ID = Identifier.of((String)"nomorespell-rvknbyie", (String)"nomorespell_rvknbyie");
    public static final RegistryKey<Item> ITEM_KEY = RegistryKey.of((RegistryKey)RegistryKeys.field_41197, (Identifier)ITEM_ID);
    public static Item Nomorespell_ITEM;
    public static final RegistryKey<EntityType<?>> FIREBALL_ENTITY_TYPE_KEY;
    public static final EntityType<SpellFireballEntity> FIREBALL_ENTITY_TYPE;
    public static final StatusEffect HEMORRHAGE_STATUS_EFFECT;
    private static final Map<UUID, Boolean> GUI_OPEN;
    private static boolean performanceMode;
    private static int lowTpsTicks;
    private static int loreTickCounter;
    private static boolean debugCollision;

    public void onInitialize() {
        LOGGER.info("Initializing NoMoreSpell Mod!");
        SpellRegistry.init();
        SpellBookComponents.init();
        SpellVisualsManager.setHemorrhageStatusEffect(HEMORRHAGE_STATUS_EFFECT);
        Nomorespell_ITEM = Nomorespell.registerItem(ITEM_KEY, settings -> new NomorespellItem((Item.class_1793)settings), new Item.class_1793().registryKey(ITEM_KEY).maxCount(1));
        PayloadTypeRegistry.playC2S().register(CastSpellPayload.ID, CastSpellPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(EquipSpellPayload.ID, EquipSpellPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(PurchaseSpellPayload.ID, PurchaseSpellPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SacrificeLevelsPayload.ID, SacrificeLevelsPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BloodEclipseRenderPayload.ID, BloodEclipseRenderPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(DomainRenderSyncPayload.ID, DomainRenderSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(RainOfPicksRenderPayload.ID, RainOfPicksRenderPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(VerdantHaloRenderPayload.ID, VerdantHaloRenderPayload.CODEC);
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            CombatXpTracker.flush(server);
            Nomorespell.updatePerformanceMode(server);
            Nomorespell.updateHeldBookLore(server);
            Nomorespell.tickSpellVisuals(server);
            DomainExpansionManager.tickServer(server);
            for (ServerWorld world : server.getWorlds()) {
                CombatXpTracker.pruneExpiredAttributions(world);
            }
        });
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)world;
                return !DomainExpansionManager.isProtectedDomainBlock((World)serverWorld, pos);
            }
            return true;
        });
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> !source.isOf(DamageTypes.FALL) || !DomainExpansionManager.shouldCancelFallDamage(entity));
        ServerPlayNetworking.registerGlobalReceiver(CastSpellPayload.ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
            if (player == null) {
                return;
            }
            int slot = payload.slotIndex();
            if (slot != 1 && slot != 2) {
                LOGGER.warn("Invalid cast slot index: {}", (Object)slot);
                return;
            }
            ItemStack bookStack = null;
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack candidate = player.getInventory().getStack(i);
                if (!(candidate.getItem() instanceof NomorespellItem)) continue;
                bookStack = candidate;
                break;
            }
            if (bookStack == null) {
                if (context.player().hasPermissionLevel(2)) {
                    context.player().sendMessage((Text)Text.literal((String)"Cast failed: no SSpellBook in inventory").formatted(Formatting.field_1061), true);
                }
                LOGGER.debug("Cast request ignored: no book in inventory");
                return;
            }
            SpellBookData.initializeIfNeeded(bookStack);
            SpellBookData.sanitizeRuntimeData(bookStack);
            NomorespellItem.castSpellFromSlot(player.getWorld(), (PlayerEntity)player, bookStack, slot);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = player;
                serverPlayer.playerScreenHandler.sendContentUpdates();
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(EquipSpellPayload.ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
            if (player == null) {
                return;
            }
            int slot = payload.slotIndex();
            if (slot != 1 && slot != 2) {
                LOGGER.warn("Invalid equip slot index: {}", (Object)slot);
                return;
            }
            String spellId = payload.spellId();
            if (spellId == null) {
                spellId = "";
            }
            ItemStack bookStack = null;
            if (player.getStackInHand(Hand.field_5808).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5808);
            } else if (player.getStackInHand(Hand.field_5810).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5810);
            }
            if (bookStack == null) {
                return;
            }
            SpellBookData.initializeIfNeeded(bookStack);
            SpellBookData.sanitizeRuntimeData(bookStack);
            long currentTick = player.getWorld().getTime();
            if (!SpellBookData.canModifySlot(bookStack, slot, currentTick)) {
                return;
            }
            if (!(spellId.isEmpty() || SpellRegistry.exists(spellId) && SpellBookData.hasPurchasedSpell(bookStack, spellId))) {
                return;
            }
            if (slot == 1) {
                SpellBookData.setEquippedSlot1(bookStack, spellId);
            } else {
                SpellBookData.setEquippedSlot2(bookStack, spellId);
            }
            SpellBookData.sanitizeRuntimeData(bookStack);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = player;
                serverPlayer.playerScreenHandler.sendContentUpdates();
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(PurchaseSpellPayload.ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
            if (player == null) {
                return;
            }
            String spellId = payload.spellId();
            if (spellId == null || spellId.isEmpty() || !SpellRegistry.exists(spellId)) {
                return;
            }
            ItemStack bookStack = null;
            if (player.getStackInHand(Hand.field_5808).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5808);
            } else if (player.getStackInHand(Hand.field_5810).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5810);
            }
            if (bookStack == null) {
                return;
            }
            SpellBookData.initializeIfNeeded(bookStack);
            SpellBookData.sanitizeRuntimeData(bookStack);
            if (SpellBookData.hasPurchasedSpell(bookStack, spellId)) {
                return;
            }
            Spell spell = SpellRegistry.getSpell(spellId);
            if (spell == null) {
                return;
            }
            int souls = SpellBookData.getSoulsPoints(bookStack);
            if (souls < spell.getSoulsCost()) {
                return;
            }
            SpellBookData.setSoulsPoints(bookStack, souls - spell.getSoulsCost());
            SpellBookData.addPurchasedSpell(bookStack, spellId);
            SpellBookData.sanitizeRuntimeData(bookStack);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = player;
                serverPlayer.playerScreenHandler.sendContentUpdates();
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(SacrificeLevelsPayload.ID, (payload, context) -> context.server().execute(() -> {
            ServerPlayerEntity player = context.player();
            if (player == null) {
                return;
            }
            int levels = Math.max(0, payload.levels());
            if (levels <= 0) {
                return;
            }
            ItemStack bookStack = null;
            if (player.getStackInHand(Hand.field_5808).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5808);
            } else if (player.getStackInHand(Hand.field_5810).getItem() instanceof NomorespellItem) {
                bookStack = player.getStackInHand(Hand.field_5810);
            }
            if (bookStack == null) {
                return;
            }
            SpellBookData.initializeIfNeeded(bookStack);
            int playerLevel = player.experienceLevel;
            if (playerLevel <= 0) {
                return;
            }
            int maxAllowed = Math.min(playerLevel, 10000);
            int clampedLevels = Math.min(levels, maxAllowed);
            if (clampedLevels <= 0) {
                return;
            }
            int xpTotal = 0;
            for (int lvl = playerLevel; lvl >= playerLevel - clampedLevels + 1; --lvl) {
                xpTotal += SpellBookData.getSacrificeXpForLevel(lvl);
            }
            if (xpTotal <= 0) {
                return;
            }
            player.addExperienceLevels(-clampedLevels);
            player.addExperience(0);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = player;
                serverPlayer.networkHandler.sendPacket((Packet)new ExperienceBarUpdateS2CPacket(serverPlayer.experienceProgress, serverPlayer.totalExperience, serverPlayer.experienceLevel));
            }
            SpellBookData.LevelUpResult sacrificeResult = SpellBookData.addXpAndProcess(bookStack, xpTotal);
            SpellBookData.sanitizeRuntimeData(bookStack);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = player;
                serverPlayer.sendMessage((Text)Text.literal((String)("+" + xpTotal + " XP")).formatted(Formatting.field_1060), true);
                if (sacrificeResult.soulsAwarded() > 0) {
                    serverPlayer.sendMessage((Text)Text.literal((String)("+" + sacrificeResult.soulsAwarded() + " Souls")).formatted(Formatting.field_1076), true);
                }
                serverPlayer.playerScreenHandler.sendContentUpdates();
            }
            player.playSound(SACRIFICE_SUCCESS_SOUND, 0.8f, 1.15f);
        }));
        ItemGroupEvents.modifyEntriesEvent((RegistryKey)ItemGroups.TOOLS).register(entries -> entries.add((ItemConvertible)Nomorespell_ITEM));
        this.registerCommands();
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal((String)"sspellbook").requires(source -> source.hasPermissionLevel(2))).then(CommandManager.literal((String)"give").executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            ItemStack stack = new ItemStack((ItemConvertible)Nomorespell_ITEM);
            SpellBookData.initializeIfNeeded(stack);
            player.giveItemStack(stack);
            return 1;
        }))).then(CommandManager.literal((String)"add").executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            ItemStack stack = new ItemStack((ItemConvertible)Nomorespell_ITEM);
            SpellBookData.initializeIfNeeded(stack);
            player.giveItemStack(stack);
            return 1;
        }))).then(CommandManager.literal((String)"addsouls").then(CommandManager.argument((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            int amount = IntegerArgumentType.getInteger((CommandContext)context, (String)"amount");
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.setSoulsPoints(stack, SpellBookData.getSoulsPoints(stack) + amount);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        })))).then(CommandManager.literal((String)"setlevel").then(CommandManager.argument((String)"level", (ArgumentType)IntegerArgumentType.integer((int)1)).executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            int level = IntegerArgumentType.getInteger((CommandContext)context, (String)"level");
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.setCurrentLevel(stack, level);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        })))).then(CommandManager.literal((String)"addxp").then(CommandManager.argument((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            int amount = IntegerArgumentType.getInteger((CommandContext)context, (String)"amount");
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.addXpAndProcess(stack, amount);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        })))).then(CommandManager.literal((String)"unlock").then(CommandManager.argument((String)"spell_id", (ArgumentType)StringArgumentType.word()).executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            String spellId = StringArgumentType.getString((CommandContext)context, (String)"spell_id");
            if (!SpellRegistry.exists(spellId)) {
                return 0;
            }
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.addPurchasedSpell(stack, spellId);
            SpellBookData.sanitizeRuntimeData(stack);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        })))).then(CommandManager.literal((String)"reset").executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.resetAll(stack);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        }))).then(CommandManager.literal((String)"cooldown").then(CommandManager.literal((String)"reset").executes(context -> {
            ServerPlayerEntity player = ((ServerCommandSource)context.getSource()).getPlayerOrThrow();
            ItemStack stack = this.getHeldBook(player);
            if (stack == null) {
                return 0;
            }
            SpellBookData.setCooldownEndTick(stack, 0L);
            player.playerScreenHandler.sendContentUpdates();
            return 1;
        })))).then(CommandManager.literal((String)"debug").executes(context -> {
            Nomorespell.toggleDebugCollision();
            return 1;
        }))));
    }

    private ItemStack getHeldBook(ServerPlayerEntity player) {
        if (player.getMainHandStack().getItem() instanceof NomorespellItem) {
            return player.getMainHandStack();
        }
        if (player.getOffHandStack().getItem() instanceof NomorespellItem) {
            return player.getOffHandStack();
        }
        return null;
    }

    public static boolean shouldSpawnParticles(ServerWorld world, Vec3d pos) {
        return !world.getPlayers(player -> player.squaredDistanceTo(pos) <= 4096.0).isEmpty();
    }

    private static void updatePerformanceMode(MinecraftServer server) {
        boolean shouldEnable;
        float tps = 20.0f;
        try {
            tps = server.getTickManager().getTickRate();
        }
        catch (Exception exception) {
            // empty catch block
        }
        lowTpsTicks = tps < 15.0f ? ++lowTpsTicks : 0;
        boolean bl = shouldEnable = lowTpsTicks > 200;
        if (shouldEnable && !performanceMode) {
            performanceMode = true;
            LOGGER.info("NoMoreSpell: Performance mode active");
        } else if (!shouldEnable && performanceMode) {
            performanceMode = false;
        }
    }

    private static void tickSpellVisuals(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            SpellVisualsManager.tick(world);
            for (BloodEclipseRenderPayload bloodEclipseRenderPayload : SpellVisualsManager.getBloodEclipseRenderPayloads(world)) {
                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld)world)) {
                    ServerPlayNetworking.send((ServerPlayerEntity)player, (CustomPayload)bloodEclipseRenderPayload);
                }
            }
            for (RainOfPicksRenderPayload rainOfPicksRenderPayload : SpellVisualsManager.getRainOfPicksRenderPayloads(world)) {
                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld)world)) {
                    ServerPlayNetworking.send((ServerPlayerEntity)player, (CustomPayload)rainOfPicksRenderPayload);
                }
            }
            for (VerdantHaloRenderPayload verdantHaloRenderPayload : SpellVisualsManager.getVerdantHaloRenderPayloads(world)) {
                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld)world)) {
                    ServerPlayNetworking.send((ServerPlayerEntity)player, (CustomPayload)verdantHaloRenderPayload);
                }
            }
            for (DomainExpansionManager.DomainRenderState domainRenderState : DomainExpansionManager.getRenderStates((World)world)) {
                DomainRenderSyncPayload payload = new DomainRenderSyncPayload(domainRenderState.casterId, domainRenderState.center.x, domainRenderState.center.y, domainRenderState.center.z, domainRenderState.age, domainRenderState.radius, domainRenderState.alpha, true, domainRenderState.ending, domainRenderState.endAge);
                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld)world)) {
                    ServerPlayNetworking.send((ServerPlayerEntity)player, (CustomPayload)payload);
                }
            }
        }
    }

    public static boolean isPerformanceMode() {
        return performanceMode;
    }

    public static void setGuiOpen(PlayerEntity player, boolean open) {
        GUI_OPEN.put(player.getUuid(), open);
    }

    public static boolean isGuiOpen(PlayerEntity player) {
        return GUI_OPEN.getOrDefault(player.getUuid(), false);
    }

    private static void toggleDebugCollision() {
        debugCollision = !debugCollision;
    }

    public static boolean isDebugCollision() {
        return debugCollision;
    }

    private static <T extends Item> T registerItem(RegistryKey<Item> key, Function<Item.class_1793, T> factory, Item.class_1793 settings) {
        Item item = (Item)factory.apply(settings);
        return (T)((Item)Registry.register((Registry)Registries.ITEM, key, (Object)item));
    }

    private static void updateHeldBookLore(MinecraftServer server) {
        if (++loreTickCounter < 20) {
            return;
        }
        loreTickCounter = 0;
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (player == null) continue;
            try {
                for (int i = 0; i < player.getInventory().size(); ++i) {
                    ItemStack stack = player.getInventory().getStack(i);
                    if (!(stack.getItem() instanceof NomorespellItem)) continue;
                    SpellBookData.initializeIfNeeded(stack);
                    SpellBookData.sanitizeRuntimeData(stack);
                    SpellBookData.synchronizeCooldowns(stack, player.getWorld().getTime());
                    LoreComponent lore = Nomorespell.buildLoreFor(stack, (PlayerEntity)player, player.getWorld().getTime());
                    stack.set(DataComponentTypes.field_49632, (Object)lore);
                }
                player.playerScreenHandler.sendContentUpdates();
            }
            catch (Exception e) {
                LOGGER.warn("Failed to update SSpellBook lore", (Throwable)e);
            }
        }
    }

    private static LoreComponent buildLoreFor(ItemStack stack, PlayerEntity player, long currentTick) {
        SpellBookData.sanitizeRuntimeData(stack);
        ArrayList<Text> lines = new ArrayList<Text>();
        String separator = "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500";
        if (SpellBookData.getOwnerName(stack).isEmpty() && player != null) {
            SpellBookData.setOwnerName(stack, player.getName().getString());
        }
        lines.add((Text)Text.literal((String)("\u00a75\u00a7l" + separator)));
        lines.add((Text)Text.literal((String)"\u00a7d\u00a7lActive Spell"));
        Nomorespell.addSlotLore(lines, stack, currentTick, 1);
        Nomorespell.addSlotLore(lines, stack, currentTick, 2);
        int level = SpellBookData.getCurrentLevel(stack);
        lines.add((Text)Text.literal((String)("\u00a75\u00a7l" + separator)));
        lines.add((Text)Text.literal((String)("\u00a7bOwned by: \u00a7f" + SpellBookData.getOwnerName(stack))));
        lines.add((Text)Text.literal((String)("\u00a7aLevel: \u00a7f" + level)));
        lines.add((Text)Text.literal((String)("\u00a75\u00a7l" + separator)));
        LoreComponent lore = new LoreComponent(List.of());
        for (Text line : lines) {
            lore = lore.with(line);
        }
        return lore;
    }

    private static void addSlotLore(List<Text> lines, ItemStack stack, long currentTick, int slotIndex) {
        String spellId;
        String string = spellId = slotIndex == 1 ? SpellBookData.getEquippedSlot1(stack) : SpellBookData.getEquippedSlot2(stack);
        if (spellId == null || spellId.isEmpty()) {
            lines.add((Text)Text.literal((String)("\u00a77Slot " + slotIndex + (slotIndex == 1 ? " \u00a78[Key R]" : " \u00a78[Key F]"))));
            lines.add((Text)Text.literal((String)"\u00a78\u2022 Empty"));
            return;
        }
        Spell spell = SpellRegistry.getSpell(spellId);
        if (spell == null) {
            lines.add((Text)Text.literal((String)("\u00a77Slot " + slotIndex + (slotIndex == 1 ? " \u00a78[Key R]" : " \u00a78[Key F]"))));
            lines.add((Text)Text.literal((String)"\u00a7c\u2022 Invalid spell"));
            return;
        }
        String slotTitle = "\u00a77Slot " + slotIndex + (slotIndex == 1 ? " \u00a78[Key R]" : " \u00a78[Key F]");
        lines.add((Text)Text.literal((String)slotTitle));
        long cooldownEnd = SpellBookData.getCooldownEndTick(stack, slotIndex);
        long remainingTicks = Math.max(0L, cooldownEnd - currentTick);
        long seconds = (remainingTicks + 19L) / 20L;
        Object status = remainingTicks <= 0L ? "\u00a77[\u00a7a\u2713 Ready\u00a77]" : "\u00a77[\u00a7c" + seconds + "s\u00a77]";
        lines.add((Text)Text.literal((String)("\u00a7f\u2022 " + spell.getName() + " " + (String)status)));
        lines.add((Text)Text.literal((String)("\u00a77\u2022 Cooldown: " + (String)(remainingTicks <= 0L ? "\u00a7aReady" : "\u00a7c" + seconds + "s"))));
    }

    static {
        FIREBALL_ENTITY_TYPE_KEY = RegistryKey.of((RegistryKey)RegistryKeys.field_41266, (Identifier)Identifier.of((String)MOD_ID, (String)"nomorespell_rvknbyie"));
        FIREBALL_ENTITY_TYPE = (EntityType)Registry.register((Registry)Registries.ENTITY_TYPE, FIREBALL_ENTITY_TYPE_KEY, (Object)FabricEntityTypeBuilder.create((SpawnGroup)SpawnGroup.field_17715, SpellFireballEntity::new).dimensions(EntityDimensions.fixed((float)0.5f, (float)0.5f)).trackRangeBlocks(64).trackedUpdateRate(10).build(FIREBALL_ENTITY_TYPE_KEY));
        HEMORRHAGE_STATUS_EFFECT = (StatusEffect)Registry.register((Registry)Registries.STATUS_EFFECT, SpellVisualsManager.HEMORRHAGE_STATUS_EFFECT_KEY, (Object)((Object)new HemorrhageStatusEffect()));
        GUI_OPEN = new HashMap<UUID, Boolean>();
        performanceMode = false;
        lowTpsTicks = 0;
        loreTickCounter = 0;
        debugCollision = false;
    }

    public static class HemorrhageStatusEffect
    extends StatusEffect {
        public HemorrhageStatusEffect() {
            super(StatusEffectCategory.field_18272, 11999282);
        }

        public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
            entity.damage(world, world.getDamageSources().magic(), 1.0f + (float)amplifier * 0.25f);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, entity.getX(), entity.getBodyY(0.6), entity.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
            return true;
        }

        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return duration % 20 == 0;
        }
    }
}

