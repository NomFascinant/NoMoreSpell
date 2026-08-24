/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 *  net.minecraft.Formatting
 *  net.minecraft.DamageSource
 *  net.minecraft.PassiveEntity
 *  net.minecraft.Entity
 *  net.minecraft.LivingEntity
 *  net.minecraft.EnderDragonEntity
 *  net.minecraft.WitherEntity
 *  net.minecraft.HostileEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ProjectileEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Text
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  net.minecraft.SoundEvents
 *  net.minecraft.MathHelper
 *  net.minecraft.Tameable
 *  net.minecraft.WardenEntity
 *  net.minecraft.Ownable
 *  net.minecraft.server.MinecraftServer
 */
package nomorespell_rvknbyie.spell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.Formatting;
import net.minecraft.DamageSource;
import net.minecraft.PassiveEntity;
import net.minecraft.Entity;
import net.minecraft.LivingEntity;
import net.minecraft.EnderDragonEntity;
import net.minecraft.WitherEntity;
import net.minecraft.HostileEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ProjectileEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Text;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import net.minecraft.SoundEvents;
import net.minecraft.MathHelper;
import net.minecraft.Tameable;
import net.minecraft.WardenEntity;
import net.minecraft.Ownable;
import net.minecraft.server.MinecraftServer;
import nomorespell_rvknbyie.item.NomorespellItem;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellBookData;
import nomorespell_rvknbyie.spell.SpellRegistry;

public final class CombatXpTracker {
    private static final Map<UUID, Integer> PENDING_XP = new HashMap<UUID, Integer>();
    private static final Map<UUID, SpellKillAttribution> RECENT_DAMAGE = new HashMap<UUID, SpellKillAttribution>();
    private static final int DAMAGE_ATTRIBUTION_TICKS = 600;

    private CombatXpTracker() {
    }

    public static void markSpellDamage(LivingEntity target, PlayerEntity owner, String spellId) {
        if (target == null || owner == null || spellId == null || spellId.isEmpty()) {
            return;
        }
        World class_19372 = target.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld)class_19372;
        Spell spell = SpellRegistry.getSpell(spellId);
        if (spell == null) {
            return;
        }
        RECENT_DAMAGE.put(target.getUuid(), new SpellKillAttribution(owner.getUuid(), spellId, spell.getRank(), serverWorld.getTime()));
    }

    public static void handleSpellKill(LivingEntity victim, DamageSource source) {
        World class_19372;
        if (victim == null || !((class_19372 = victim.getWorld()) instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld)class_19372;
        SpellKillAttribution attribution = CombatXpTracker.resolveAttribution(victim, source, serverWorld);
        RECENT_DAMAGE.remove(victim.getUuid());
        if (attribution == null) {
            return;
        }
        int xp = CombatXpTracker.computeKillXp(victim, attribution.rank());
        CombatXpTracker.addXp(attribution.playerId(), xp);
    }

    public static void pruneExpiredAttributions(ServerWorld world) {
        long time = world.getTime();
        Iterator<Map.Entry<UUID, SpellKillAttribution>> iterator = RECENT_DAMAGE.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, SpellKillAttribution> entry = iterator.next();
            if (time - entry.getValue().worldTime() <= 600L) continue;
            iterator.remove();
        }
    }

    private static SpellKillAttribution resolveAttribution(LivingEntity victim, DamageSource source, ServerWorld world) {
        Entity direct = source.getSource();
        Entity attacker = source.getAttacker();
        SpellKillAttribution directAttribution = CombatXpTracker.createAttributionFromEntity(direct, world);
        if (directAttribution != null) {
            return directAttribution;
        }
        SpellKillAttribution attackerAttribution = CombatXpTracker.createAttributionFromEntity(attacker, world);
        if (attackerAttribution != null) {
            return attackerAttribution;
        }
        SpellKillAttribution recent = RECENT_DAMAGE.get(victim.getUuid());
        if (recent == null) {
            return null;
        }
        if (world.getTime() - recent.worldTime() > 600L) {
            return null;
        }
        return recent;
    }

    private static SpellKillAttribution createAttributionFromEntity(Entity entity, ServerWorld world) {
        PlayerEntity player;
        if (entity == null) {
            return null;
        }
        if (entity instanceof PlayerEntity) {
            return null;
        }
        String spellId = null;
        if (entity instanceof SpellAttributedEntity) {
            SpellAttributedEntity spellAttributedEntity = (SpellAttributedEntity)entity;
            spellId = spellAttributedEntity.nomorespell$getSpellId();
        }
        if ((player = CombatXpTracker.resolveOwningPlayer(entity, world)) == null || spellId == null || spellId.isEmpty()) {
            return null;
        }
        Spell spell = SpellRegistry.getSpell(spellId);
        if (spell == null) {
            return null;
        }
        return new SpellKillAttribution(player.getUuid(), spellId, spell.getRank(), world.getTime());
    }

    private static PlayerEntity resolveOwningPlayer(Entity entity, ServerWorld world) {
        PlayerEntity player;
        Tameable tameable;
        PlayerEntity nested;
        Entity owner;
        if (entity == null) {
            return null;
        }
        if (entity instanceof PlayerEntity) {
            PlayerEntity player2 = (PlayerEntity)entity;
            return player2;
        }
        if (entity instanceof ProjectileEntity) {
            ProjectileEntity projectile = (ProjectileEntity)entity;
            owner = projectile.getOwner();
            if (owner instanceof PlayerEntity) {
                PlayerEntity player3 = (PlayerEntity)owner;
                return player3;
            }
            if (owner != null && (nested = CombatXpTracker.resolveOwningPlayer(owner, world)) != null) {
                return nested;
            }
        }
        if (entity instanceof Tameable && (owner = (tameable = (Tameable)entity).getOwner()) instanceof PlayerEntity) {
            player = (PlayerEntity)owner;
            return player;
        }
        if (entity instanceof Ownable) {
            Ownable ownable = (Ownable)entity;
            owner = ownable.getOwner();
            if (owner instanceof PlayerEntity) {
                player = (PlayerEntity)owner;
                return player;
            }
            if (owner != null && (nested = CombatXpTracker.resolveOwningPlayer(owner, world)) != null) {
                return nested;
            }
        }
        return null;
    }

    private static int computeKillXp(LivingEntity victim, Spell.SpellRank rank) {
        int max;
        int min;
        float maxHealth = victim.getMaxHealth();
        if (CombatXpTracker.isBoss(victim)) {
            min = 500;
            max = 1000;
        } else if (victim instanceof HostileEntity) {
            min = 30;
            max = 50;
        } else if (victim instanceof PassiveEntity || !(victim instanceof HostileEntity)) {
            min = 2;
            max = 10;
        } else {
            min = 2;
            max = 10;
        }
        double healthFactor = MathHelper.clamp((double)(((double)maxHealth - 4.0) / 96.0), (double)0.0, (double)1.0);
        int lower = min + (int)Math.round((double)(max - min) * healthFactor * 0.35);
        int upper = min + (int)Math.round((double)(max - min) * Math.min(1.0, 0.45 + healthFactor * 0.55));
        upper = Math.max(lower, upper);
        int base = lower + victim.getRandom().nextBetween(0, Math.max(0, upper - lower));
        return Math.max(1, Math.round((float)base * CombatXpTracker.getRankMultiplier(rank)));
    }

    private static boolean isBoss(LivingEntity victim) {
        return victim instanceof WitherEntity || victim instanceof EnderDragonEntity || victim instanceof WardenEntity || victim.getMaxHealth() >= 200.0f;
    }

    private static float getRankMultiplier(Spell.SpellRank rank) {
        return switch (rank) {
            default -> throw new MatchException(null, null);
            case Spell.SpellRank.E -> 1.0f;
            case Spell.SpellRank.D -> 1.5f;
            case Spell.SpellRank.C -> 2.0f;
            case Spell.SpellRank.B -> 3.0f;
            case Spell.SpellRank.A -> 5.0f;
            case Spell.SpellRank.S -> 10.0f;
        };
    }

    public static void addXp(UUID playerId, int amount) {
        if (amount <= 0) {
            return;
        }
        PENDING_XP.merge(playerId, amount, Integer::sum);
    }

    public static void flush(MinecraftServer server) {
        if (PENDING_XP.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<UUID, Integer>> iterator = PENDING_XP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            ServerPlayerEntity player = server.getPlayerManager().getPlayer(entry.getKey());
            if (player == null) {
                iterator.remove();
                continue;
            }
            ItemStack stack = player.getMainHandStack();
            if (!(stack.getItem() instanceof NomorespellItem)) {
                stack = player.getOffHandStack();
            }
            if (!(stack.getItem() instanceof NomorespellItem)) {
                iterator.remove();
                continue;
            }
            SpellBookData.initializeIfNeeded(stack);
            int pending = entry.getValue();
            if (pending > 0) {
                SpellBookData.LevelUpResult result = SpellBookData.addXpAndProcess(stack, pending);
                World class_19372 = player.getWorld();
                if (class_19372 instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)class_19372;
                    serverWorld.spawnParticles((ParticleEffect)ParticleTypes.field_11211, player.getX(), player.getBodyY(0.7), player.getZ(), Math.min(4, pending / 3 + 1), 0.2, 0.3, 0.2, 0.0);
                    if (result.levelsGained() > 0) {
                        serverWorld.spawnParticles((ParticleEffect)ParticleTypes.field_11214, player.getX(), player.getBodyY(0.8), player.getZ(), 18, 0.6, 0.6, 0.6, 0.05);
                        serverWorld.spawnParticles((ParticleEffect)ParticleTypes.field_11207, player.getX(), player.getBodyY(0.8), player.getZ(), 10, 0.4, 0.4, 0.4, 0.01);
                        player.playSound(SoundEvents.field_14627, 0.9f, 1.3f);
                        if (result.soulsAwarded() > 0) {
                            player.sendMessage((Text)Text.literal((String)"Level Up! +1 Soul earned").formatted(Formatting.field_1065), true);
                        }
                    }
                }
                if (player instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayer = player;
                    serverPlayer.playerScreenHandler.sendContentUpdates();
                }
            }
            iterator.remove();
        }
    }

    private record SpellKillAttribution(UUID playerId, String spellId, Spell.SpellRank rank, long worldTime) {
    }

    public static interface SpellAttributedEntity {
        public String nomorespell$getSpellId();
    }
}

