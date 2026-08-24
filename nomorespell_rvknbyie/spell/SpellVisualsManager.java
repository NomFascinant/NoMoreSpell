/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.networking.v1.PlayerLookup
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.Formatting
 *  net.minecraft.BossBar$class_1260
 *  net.minecraft.BossBar$class_1261
 *  net.minecraft.ItemScatterer
 *  net.minecraft.StatusEffect
 *  net.minecraft.StatusEffectInstance
 *  net.minecraft.StatusEffects
 *  net.minecraft.PassiveEntity
 *  net.minecraft.Entity
 *  net.minecraft.Entity$class_5529
 *  net.minecraft.EntityType
 *  net.minecraft.LivingEntity
 *  net.minecraft.HostileEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.Items
 *  net.minecraft.ItemConvertible
 *  net.minecraft.World
 *  net.minecraft.Blocks
 *  net.minecraft.BlockPos
 *  net.minecraft.BlockPos$class_2339
 *  net.minecraft.FireBlock
 *  net.minecraft.Position
 *  net.minecraft.Box
 *  net.minecraft.Vec3i
 *  net.minecraft.HitResult$class_240
 *  net.minecraft.DustParticleEffect
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Vec2f
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.Team
 *  net.minecraft.BlockState
 *  net.minecraft.Heightmap$class_2903
 *  net.minecraft.Identifier
 *  net.minecraft.ServerBossBar
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  net.minecraft.SoundEvent
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 *  net.minecraft.MathHelper
 *  net.minecraft.RaycastContext
 *  net.minecraft.RaycastContext$class_242
 *  net.minecraft.RaycastContext$class_3960
 *  net.minecraft.BlockHitResult
 *  net.minecraft.StatusEffectCategory
 *  net.minecraft.WorldView
 *  net.minecraft.AffineTransformation
 *  net.minecraft.EntityAttributes
 *  net.minecraft.RegistryKey
 *  net.minecraft.MarkerEntity
 *  net.minecraft.RegistryEntry
 *  net.minecraft.Registries
 *  net.minecraft.RegistryKeys
 *  net.minecraft.DisplayEntity$class_8114
 *  net.minecraft.DisplayEntity$class_8115
 *  net.minecraft.DisplayEntity$class_8122
 *  net.minecraft.DisplayEntity$class_8123
 *  net.minecraft.CustomPayload
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 */
package nomorespell_rvknbyie.spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.Formatting;
import net.minecraft.BossBar;
import net.minecraft.ItemScatterer;
import net.minecraft.StatusEffect;
import net.minecraft.StatusEffectInstance;
import net.minecraft.StatusEffects;
import net.minecraft.PassiveEntity;
import net.minecraft.Entity;
import net.minecraft.EntityType;
import net.minecraft.LivingEntity;
import net.minecraft.HostileEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.Items;
import net.minecraft.ItemConvertible;
import net.minecraft.World;
import net.minecraft.Blocks;
import net.minecraft.BlockPos;
import net.minecraft.FireBlock;
import net.minecraft.Position;
import net.minecraft.Box;
import net.minecraft.Vec3i;
import net.minecraft.HitResult;
import net.minecraft.DustParticleEffect;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Vec2f;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.Team;
import net.minecraft.BlockState;
import net.minecraft.Heightmap;
import net.minecraft.Identifier;
import net.minecraft.ServerBossBar;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import net.minecraft.SoundEvent;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import net.minecraft.MathHelper;
import net.minecraft.RaycastContext;
import net.minecraft.BlockHitResult;
import net.minecraft.StatusEffectCategory;
import net.minecraft.WorldView;
import net.minecraft.AffineTransformation;
import net.minecraft.EntityAttributes;
import net.minecraft.RegistryKey;
import net.minecraft.MarkerEntity;
import net.minecraft.RegistryEntry;
import net.minecraft.Registries;
import net.minecraft.RegistryKeys;
import net.minecraft.DisplayEntity;
import net.minecraft.CustomPayload;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.network.BloodEclipseRenderPayload;
import nomorespell_rvknbyie.network.RainOfPicksRenderPayload;
import nomorespell_rvknbyie.network.VerdantHaloRenderPayload;
import nomorespell_rvknbyie.spell.CombatXpTracker;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public final class SpellVisualsManager {
    public static final RegistryKey<StatusEffect> HEMORRHAGE_STATUS_EFFECT_KEY = RegistryKey.of((RegistryKey)RegistryKeys.STATUS_EFFECT, (Identifier)Identifier.of((String)"nomorespell-rvknbyie", (String)"hemorrhage"));
    public static StatusEffect HEMORRHAGE_STATUS_EFFECT;
    public static RegistryEntry<StatusEffect> HEMORRHAGE_STATUS_EFFECT_ENTRY;
    private static final Map<UUID, HealingTouchState> HEALING_TOUCH;
    private static final Map<UUID, SwiftnessAuraState> SWIFTNESS_AURA;
    private static final Map<UUID, FrostNovaState> FROST_NOVA;
    private static final Map<UUID, GroupHealState> GROUP_HEAL;
    private static final Map<UUID, VerdantHaloState> VERDANT_HALO;
    private static final Map<UUID, BattleBoostState> BATTLE_BOOST;
    private static final Map<UUID, List<OrbitSymbolState>> BATTLE_ORBITS;
    private static final Map<UUID, ChainLightningState> CHAIN_LIGHTNING;
    private static final Map<UUID, SacredCircleState> SACRED_CIRCLE;
    private static final Map<UUID, TeamFortressState> TEAM_FORTRESS;
    private static final Map<UUID, List<FortressRuneState>> FORTRESS_RUNES;
    private static final Map<UUID, MeteorStrikeState> METEOR_STRIKE;
    private static final Map<UUID, DivineShieldState> DIVINE_SHIELD;
    private static final Map<UUID, TitansBlessingState> TITANS_BLESSING;
    private static final Map<UUID, TitanFormState> TITAN_FORMS;
    private static final Map<UUID, MeteorHazardState> METEOR_HAZARDS;
    private static final Map<UUID, CataclysmState> CATACLYSM;
    private static final Map<UUID, CataclysmHazardState> CATACLYSM_HAZARDS;
    private static final Map<UUID, MiracleState> MIRACLE;
    private static final Map<UUID, MiracleBlessingState> MIRACLE_BLESSINGS;
    private static final Map<UUID, DominionState> DOMINION;
    private static final Map<UUID, DominionChampionState> DOMINION_CHAMPIONS;
    private static final Map<UUID, DominionEnemyState> DOMINION_ENEMIES;
    private static final Map<UUID, AnnihilationBeamState> ANNIHILATION_BEAM;
    private static final Map<UUID, GenesisState> GENESIS;
    private static final Map<UUID, GenesisBlessingState> GENESIS_BLESSINGS;
    private static final Map<UUID, OmegaDominanceState> OMEGA_DOMINION;
    private static final Map<UUID, OmegaChampionState> OMEGA_CHAMPIONS;
    private static final Map<UUID, OmegaEnemyState> OMEGA_ENEMIES;
    private static final Map<UUID, BloodEclipseState> BLOOD_ECLIPSE;
    private static final Map<UUID, RainOfPicksState> RAIN_OF_PICKS;
    private static final Map<UUID, DomainExpansionState> DOMAIN_EXPANSIONS;
    private static final Map<UUID, DeathRecord> DEATH_RECORDS;

    public static void setHemorrhageStatusEffect(StatusEffect effect) {
        HEMORRHAGE_STATUS_EFFECT = effect;
        HEMORRHAGE_STATUS_EFFECT_ENTRY = Registries.STATUS_EFFECT.getEntry((Object)effect);
    }

    private SpellVisualsManager() {
    }

    public static void startHealingTouch(PlayerEntity player) {
        SpellVisualsManager.startHealingTouch(player, 8.0f);
    }

    public static void startHealingTouch(PlayerEntity player, float healAmount) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        HealingTouchState old = HEALING_TOUCH.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Healing Touch");
        HealingTouchState state = new HealingTouchState(world, player, healAmount);
        HEALING_TOUCH.put(id, state);
    }

    public static void startSwiftnessAura(PlayerEntity player) {
        SpellVisualsManager.startSwiftnessAura(player, 600);
    }

    public static void startSwiftnessAura(PlayerEntity player, int durationTicks) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        SwiftnessAuraState old = SWIFTNESS_AURA.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Swiftness Aura");
        SwiftnessAuraState state = new SwiftnessAuraState(world, player, durationTicks);
        SWIFTNESS_AURA.put(id, state);
    }

    public static void startFrostNova(PlayerEntity player, List<Vec3d> hitPositions) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        if (hitPositions == null || hitPositions.isEmpty()) {
            return;
        }
        UUID id = player.getUuid();
        FrostNovaState old = FROST_NOVA.remove(id);
        if (old != null) {
            old.cleanup();
        }
        FrostNovaState state = new FrostNovaState(world, hitPositions);
        FROST_NOVA.put(id, state);
    }

    public static void startGroupHeal(PlayerEntity player) {
        SpellVisualsManager.startGroupHeal(player, 12.0f);
    }

    public static void startGroupHeal(PlayerEntity player, float healAmount) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        GroupHealState old = GROUP_HEAL.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Group Heal");
        GroupHealState state = new GroupHealState(world, player, healAmount);
        GROUP_HEAL.put(id, state);
    }

    public static void startVerdantHalo(PlayerEntity player) {
        SpellVisualsManager.startVerdantHalo(player, 2.0f, 5.0f, 400, 600);
    }

    public static void startVerdantHalo(PlayerEntity player, float healPerPulse, float hostileDamage, int regenerationDuration, int absorptionDuration) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        VerdantHaloState old = VERDANT_HALO.remove(id);
        if (old != null) {
            old.cleanup();
        }
        VerdantHaloState state = new VerdantHaloState(world, player, healPerPulse, hostileDamage, regenerationDuration, absorptionDuration);
        VERDANT_HALO.put(id, state);
    }

    public static List<VerdantHaloRenderPayload> getVerdantHaloRenderPayloads(ServerWorld world) {
        ArrayList<VerdantHaloRenderPayload> payloads = new ArrayList<VerdantHaloRenderPayload>();
        for (VerdantHaloState state : VERDANT_HALO.values()) {
            PlayerEntity caster;
            if (state == null || state.finished || state.world != world || (caster = world.getPlayerByUuid(state.playerId)) == null || !caster.isAlive()) continue;
            payloads.add(new VerdantHaloRenderPayload(caster.getId(), true, state.startAge, state.durationTicks));
        }
        return payloads;
    }

    public static void startBattleBoost(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        BattleBoostState old = BATTLE_BOOST.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Battle Boost");
        BattleBoostState state = new BattleBoostState(world, player);
        BATTLE_BOOST.put(id, state);
    }

    public static void startChainLightning(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        ChainLightningState old = CHAIN_LIGHTNING.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Chain Lightning");
        ChainLightningState state = new ChainLightningState(world, player);
        CHAIN_LIGHTNING.put(id, state);
    }

    public static void startSacredCircle(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        SacredCircleState old = SACRED_CIRCLE.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Sacred Circle");
        SacredCircleState state = new SacredCircleState(world, player);
        SACRED_CIRCLE.put(id, state);
    }

    public static void startTeamFortress(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        TeamFortressState old = TEAM_FORTRESS.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Team Fortress");
        TeamFortressState state = new TeamFortressState(world, player);
        TEAM_FORTRESS.put(id, state);
    }

    public static void startMeteorStrike(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        MeteorStrikeState old = METEOR_STRIKE.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Meteor Strike");
        MeteorStrikeState state = new MeteorStrikeState(world, player);
        METEOR_STRIKE.put(id, state);
    }

    public static void startDivineShield(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        DivineShieldState old = DIVINE_SHIELD.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Divine Shield");
        DivineShieldState state = new DivineShieldState(world, player);
        DIVINE_SHIELD.put(id, state);
    }

    public static void startTitansBlessing(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        TitansBlessingState old = TITANS_BLESSING.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Titan's Blessing");
        TitansBlessingState state = new TitansBlessingState(world, player);
        TITANS_BLESSING.put(id, state);
    }

    public static void startCataclysm(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        CataclysmState old = CATACLYSM.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Cataclysm");
        CataclysmState state = new CataclysmState(world, player);
        CATACLYSM.put(id, state);
    }

    public static void startMiracle(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        MiracleState old = MIRACLE.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Miracle");
        MiracleState state = new MiracleState(world, player);
        MIRACLE.put(id, state);
    }

    public static void startAbsoluteDominion(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        DominionState old = DOMINION.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Absolute Dominion");
        DominionState state = new DominionState(world, player);
        DOMINION.put(id, state);
    }

    public static void startAnnihilationBeam(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        AnnihilationBeamState old = ANNIHILATION_BEAM.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Annihilation Beam");
        AnnihilationBeamState state = new AnnihilationBeamState(world, player);
        ANNIHILATION_BEAM.put(id, state);
    }

    public static void startGenesis(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        GenesisState old = GENESIS.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Genesis");
        GenesisState state = new GenesisState(world, player);
        GENESIS.put(id, state);
    }

    public static void startOmegaDominance(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        OmegaDominanceState old = OMEGA_DOMINION.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Omega Dominance");
        OmegaDominanceState state = new OmegaDominanceState(world, player);
        OMEGA_DOMINION.put(id, state);
    }

    public static void startBloodEclipse(PlayerEntity player) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        BloodEclipseState old = BLOOD_ECLIPSE.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Blood Eclipse");
        BloodEclipseState state = new BloodEclipseState(world, player);
        BLOOD_ECLIPSE.put(id, state);
    }

    public static void startRainOfPicks(PlayerEntity player) {
        SpellVisualsManager.startRainOfPicks(player, 30, 0);
    }

    public static void startRainOfPicks(PlayerEntity player, int projectileCount, int homingProjectileCount) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        UUID id = player.getUuid();
        RainOfPicksState old = RAIN_OF_PICKS.remove(id);
        if (old != null) {
            old.cleanup();
        }
        Nomorespell.LOGGER.info("Animation started for Rain of Picks");
        RainOfPicksState state = new RainOfPicksState(world, player, projectileCount, homingProjectileCount);
        RAIN_OF_PICKS.put(id, state);
    }

    public static List<RainOfPicksRenderPayload> getRainOfPicksRenderPayloads(ServerWorld world) {
        ArrayList<RainOfPicksRenderPayload> payloads = new ArrayList<RainOfPicksRenderPayload>();
        for (RainOfPicksState state : RAIN_OF_PICKS.values()) {
            PlayerEntity caster;
            if (state == null || state.finished || state.world != world || (caster = world.getPlayerByUuid(state.playerId)) == null || !caster.isAlive()) continue;
            payloads.add(new RainOfPicksRenderPayload(caster.getId(), true, state.startAge, state.durationTicks, state.seed, state.projectileCount, state.homingProjectileCount));
        }
        return payloads;
    }

    public static void recordPlayerDeath(ServerPlayerEntity player) {
        if (player == null) {
            return;
        }
        World class_19372 = player.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld)class_19372;
        Vec3d pos = player.getPos();
        DEATH_RECORDS.put(player.getUuid(), new DeathRecord(world, pos, world.getTime()));
    }

    public static boolean areAllied(PlayerEntity caster, PlayerEntity other) {
        if (caster == null || other == null) {
            return false;
        }
        if (caster.getUuid().equals(other.getUuid())) {
            return true;
        }
        Team casterTeam = caster.getScoreboardTeam();
        Team otherTeam = other.getScoreboardTeam();
        return casterTeam != null && casterTeam == otherTeam;
    }

    public static boolean startDomainExpansion(ServerWorld world, PlayerEntity caster, List<LivingEntity> initialTargets) {
        DomainExpansionState old;
        DomainExpansionState state2;
        if (world == null || caster == null || !caster.isAlive()) {
            return false;
        }
        DomainExpansionState contested = null;
        for (DomainExpansionState state2 : DOMAIN_EXPANSIONS.values()) {
            if (state2 == null || state2.finished || state2.world != world || !(state2.center.squaredDistanceTo(caster.getPos()) <= 4900.0)) continue;
            contested = state2;
            break;
        }
        if (contested != null && contested.tick < 240) {
            return false;
        }
        if (contested != null) {
            contested.startContestOverride();
        }
        if ((old = DOMAIN_EXPANSIONS.remove(caster.getUuid())) != null) {
            old.cleanup(false);
        }
        state2 = new DomainExpansionState(world, caster, initialTargets == null ? List.of() : initialTargets);
        DOMAIN_EXPANSIONS.put(caster.getUuid(), state2);
        return true;
    }

    public static void tick(ServerWorld world) {
        if (world == null) {
            return;
        }
        SpellVisualsManager.tickStateMap(HEALING_TOUCH, world, HealingTouchState::tick);
        SpellVisualsManager.tickStateMap(SWIFTNESS_AURA, world, SwiftnessAuraState::tick);
        SpellVisualsManager.tickStateMap(FROST_NOVA, world, FrostNovaState::tick);
        SpellVisualsManager.tickStateMap(GROUP_HEAL, world, GroupHealState::tick);
        SpellVisualsManager.tickStateMap(VERDANT_HALO, world, VerdantHaloState::tick);
        SpellVisualsManager.tickStateMap(BATTLE_BOOST, world, BattleBoostState::tick);
        SpellVisualsManager.tickStateMap(CHAIN_LIGHTNING, world, ChainLightningState::tick);
        SpellVisualsManager.tickStateMap(SACRED_CIRCLE, world, SacredCircleState::tick);
        SpellVisualsManager.tickStateMap(TEAM_FORTRESS, world, TeamFortressState::tick);
        SpellVisualsManager.tickStateMap(METEOR_STRIKE, world, MeteorStrikeState::tick);
        SpellVisualsManager.tickStateMap(DIVINE_SHIELD, world, DivineShieldState::tick);
        SpellVisualsManager.tickStateMap(TITANS_BLESSING, world, TitansBlessingState::tick);
        SpellVisualsManager.tickStateMap(TITAN_FORMS, world, TitanFormState::tick);
        SpellVisualsManager.tickStateMap(METEOR_HAZARDS, world, MeteorHazardState::tick);
        SpellVisualsManager.tickStateMap(CATACLYSM, world, CataclysmState::tick);
        SpellVisualsManager.tickStateMap(CATACLYSM_HAZARDS, world, CataclysmHazardState::tick);
        SpellVisualsManager.tickStateMap(MIRACLE, world, MiracleState::tick);
        SpellVisualsManager.tickStateMap(MIRACLE_BLESSINGS, world, MiracleBlessingState::tick);
        SpellVisualsManager.tickStateMap(DOMINION, world, DominionState::tick);
        SpellVisualsManager.tickStateMap(DOMINION_CHAMPIONS, world, DominionChampionState::tick);
        SpellVisualsManager.tickStateMap(DOMINION_ENEMIES, world, DominionEnemyState::tick);
        SpellVisualsManager.tickStateMap(ANNIHILATION_BEAM, world, AnnihilationBeamState::tick);
        SpellVisualsManager.tickStateMap(GENESIS, world, GenesisState::tick);
        SpellVisualsManager.tickStateMap(GENESIS_BLESSINGS, world, GenesisBlessingState::tick);
        SpellVisualsManager.tickStateMap(OMEGA_DOMINION, world, OmegaDominanceState::tick);
        SpellVisualsManager.tickStateMap(OMEGA_CHAMPIONS, world, OmegaChampionState::tick);
        SpellVisualsManager.tickStateMap(OMEGA_ENEMIES, world, OmegaEnemyState::tick);
        SpellVisualsManager.tickStateMap(BLOOD_ECLIPSE, world, BloodEclipseState::tick);
        SpellVisualsManager.tickStateMap(RAIN_OF_PICKS, world, RainOfPicksState::tick);
        SpellVisualsManager.tickStateMap(DOMAIN_EXPANSIONS, world, DomainExpansionState::tick);
        SpellVisualsManager.pruneDeathRecords(world);
        SpellVisualsManager.tickNestedStateLists(BATTLE_ORBITS, world, OrbitSymbolState::tick);
        SpellVisualsManager.tickNestedStateLists(FORTRESS_RUNES, world, FortressRuneState::tick);
    }

    public static List<BloodEclipseRenderPayload> getBloodEclipseRenderPayloads(ServerWorld world) {
        ArrayList<BloodEclipseRenderPayload> payloads = new ArrayList<BloodEclipseRenderPayload>();
        for (BloodEclipseState state : BLOOD_ECLIPSE.values()) {
            if (state == null || state.world != world || state.finished) continue;
            payloads.add(state.createPayload());
        }
        return payloads;
    }

    private static <T> void tickStateMap(Map<UUID, T> states, ServerWorld world, WorldTickState<T> ticker) {
        Iterator<Map.Entry<UUID, T>> iterator = states.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, T> entry = iterator.next();
            T state = entry.getValue();
            if (state != null && !ticker.tick(state, world)) continue;
            iterator.remove();
        }
    }

    private static <T> void tickNestedStateLists(Map<UUID, List<T>> stateLists, ServerWorld world, WorldTickState<T> ticker) {
        Iterator<Map.Entry<UUID, List<T>>> outerIterator = stateLists.entrySet().iterator();
        while (outerIterator.hasNext()) {
            Map.Entry<UUID, List<T>> entry = outerIterator.next();
            List<T> list = entry.getValue();
            if (list == null || list.isEmpty()) {
                outerIterator.remove();
                continue;
            }
            Iterator<T> innerIterator = list.iterator();
            while (innerIterator.hasNext()) {
                T state = innerIterator.next();
                if (state != null && !ticker.tick(state, world)) continue;
                innerIterator.remove();
            }
            if (!list.isEmpty()) continue;
            outerIterator.remove();
        }
    }

    private static void spawnShockwaveParticles(ServerWorld world, Vec3d center, double radius) {
        if (!Nomorespell.shouldSpawnParticles(world, center)) {
            return;
        }
        int count = 24;
        for (int i = 0; i < count; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)count;
            double x = center.x + Math.cos(angle) * radius;
            double z = center.z + Math.sin(angle) * radius;
            world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, x, center.y + 0.08, z, 1, 0.0, 0.0, 0.0, 0.0);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, x, center.y + 0.08, z, 1, 0.04, 0.02, 0.04, 0.01);
        }
    }

    private static List<LivingEntity> collectPrioritizedTargets(ServerWorld world, PlayerEntity caster, double radius) {
        Box box = caster.getBoundingBox().expand(radius, 8.0, radius);
        List candidates = world.getEntitiesByClass(LivingEntity.class, box, entity -> SpellVisualsManager.isValidRainTarget(caster, entity));
        candidates.sort((a, b) -> {
            int bPriority;
            int aPriority = a instanceof PlayerEntity ? 0 : 1;
            int n = bPriority = b instanceof PlayerEntity ? 0 : 1;
            if (aPriority != bPriority) {
                return Integer.compare(aPriority, bPriority);
            }
            return Double.compare(a.squaredDistanceTo((Entity)caster), b.squaredDistanceTo((Entity)caster));
        });
        return candidates;
    }

    private static LivingEntity findClosestTarget(ServerWorld world, PlayerEntity caster, Vec3d center, Vec3d zoneOffset, double radius) {
        Vec3d zoneCenter = center.add(zoneOffset);
        Box box = new Box(zoneCenter.x - radius, center.y - 8.0, zoneCenter.z - radius, zoneCenter.x + radius, center.y + 18.0, zoneCenter.z + radius);
        List targets = world.getEntitiesByClass(LivingEntity.class, box, entity -> SpellVisualsManager.isValidRainTarget(caster, entity));
        LivingEntity best = null;
        double bestSq = Double.MAX_VALUE;
        for (LivingEntity entity2 : targets) {
            double sq = entity2.getPos().squaredDistanceTo(zoneCenter);
            if (!(sq < bestSq)) continue;
            bestSq = sq;
            best = entity2;
        }
        return best;
    }

    private static boolean isValidRainTarget(PlayerEntity caster, LivingEntity entity) {
        if (entity == null || !entity.isAlive() || entity == caster) {
            return false;
        }
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            return !player.isSpectator() && !SpellVisualsManager.areAllied(caster, player);
        }
        return entity instanceof HostileEntity;
    }

    private static DisplayEntity.class_8115 createDisplay(ServerWorld world, BlockState blockState, Vec3d pos, float scale) {
        return SpellVisualsManager.createDisplay(world, blockState, pos, scale, scale, scale);
    }

    private static LivingEntity findTargetInView(ServerWorld world, PlayerEntity player, double range) {
        Vec3d start = player.getCameraPosVec(1.0f);
        Vec3d look = player.getRotationVec(1.0f).normalize();
        LivingEntity closest = null;
        double bestDist = range * range;
        for (LivingEntity target : world.getEntitiesByClass(LivingEntity.class, new Box(start.add(-range, -range, -range), start.add(range, range, range)), entity -> entity.isAlive() && entity != player)) {
            double dist;
            Vec3d toTarget = target.getPos().add(0.0, (double)target.getHeight() * 0.5, 0.0).subtract(start);
            double dot = look.dotProduct(toTarget.normalize());
            if (dot < 0.7 || !((dist = start.squaredDistanceTo(target.getPos())) < bestDist)) continue;
            bestDist = dist;
            closest = target;
        }
        return closest;
    }

    private static List<PlayerEntity> getPlayersAround(ServerWorld world, Vec3d center, double radius) {
        return world.getEntitiesByClass(PlayerEntity.class, new Box(center.add(-radius, -2.0, -radius), center.add(radius, 3.0, radius)), player -> player.isAlive());
    }

    private static DisplayEntity.class_8115 createDisplay(ServerWorld world, BlockState blockState, Vec3d pos, float scaleX, float scaleY, float scaleZ) {
        DisplayEntity.class_8115 display = new DisplayEntity.class_8115(EntityType.field_42460, (World)world);
        display.setBlockState(blockState);
        display.setPosition(pos.x, pos.y, pos.z);
        display.setNoGravity(true);
        display.setShadowRadius(0.0f);
        display.setInterpolationDuration(1);
        display.setGlowing(true);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scaleX, scaleY, scaleZ), new Quaternionf()));
        world.spawnEntity((Entity)display);
        return display;
    }

    private static void updateDisplay(DisplayEntity.class_8115 display, Vec3d pos, float scale) {
        if (display == null || !display.isAlive()) {
            return;
        }
        display.setPosition(pos.x, pos.y, pos.z);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scale, scale, scale), new Quaternionf()));
    }

    private static void updateDisplay(DisplayEntity.class_8115 display, Vec3d pos, float scaleX, float scaleY, float scaleZ) {
        if (display == null || !display.isAlive()) {
            return;
        }
        display.setPosition(pos.x, pos.y, pos.z);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scaleX, scaleY, scaleZ), new Quaternionf()));
    }

    private static void updateDisplayRotated(DisplayEntity.class_8115 display, Vec3d pos, float scaleX, float scaleY, float scaleZ, Quaternionf rotation) {
        if (display == null || !display.isAlive()) {
            return;
        }
        display.setPosition(pos.x, pos.y, pos.z);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf((Quaternionfc)rotation), new Vector3f(scaleX, scaleY, scaleZ), new Quaternionf()));
    }

    private static void removeDisplay(DisplayEntity.class_8115 display) {
        if (display == null) {
            return;
        }
        display.discard();
    }

    private static void removeDisplays(List<DisplayEntity.class_8115> list) {
        for (DisplayEntity.class_8115 display : list) {
            SpellVisualsManager.removeDisplay(display);
        }
    }

    private static void removeItemDisplays(List<DisplayEntity.class_8122> list) {
        for (DisplayEntity.class_8122 display : list) {
            if (display == null) continue;
            display.discard();
        }
    }

    private static void spawnRandomParticles(ServerWorld world, ParticleEffect particle, Vec3d center, int count, double spread) {
        for (int i = 0; i < count; ++i) {
            double x = center.x + (world.random.nextDouble() - 0.5) * spread;
            double y = center.y + (world.random.nextDouble() - 0.5) * spread;
            double z = center.z + (world.random.nextDouble() - 0.5) * spread;
            world.spawnParticles(particle, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    private static void pruneDeathRecords(ServerWorld world) {
        long now = world.getTime();
        DEATH_RECORDS.entrySet().removeIf(entry -> entry.getValue() == null || ((DeathRecord)entry.getValue()).world != world || now - ((DeathRecord)entry.getValue()).time > 240L);
    }

    private static void clearNegativeEffects(PlayerEntity player) {
        ArrayList effects = new ArrayList(player.getStatusEffects());
        for (StatusEffectInstance effect : effects) {
            if (((StatusEffect)effect.getEffectType().comp_349()).getCategory() != StatusEffectCategory.field_18272) continue;
            player.removeStatusEffect(effect.getEffectType());
        }
    }

    private static Vec3d clampGround(ServerWorld world, Vec3d pos) {
        BlockPos start = BlockPos.ofFloored((double)pos.x, (double)pos.y, (double)pos.z);
        BlockPos.class_2339 cursor = new BlockPos.class_2339(start.getX(), start.getY(), start.getZ());
        int minY = world.getBottomY();
        int maxY = world.getTopY(Heightmap.class_2903.field_13197, cursor.getX(), cursor.getZ());
        int y = MathHelper.clamp((int)cursor.getY(), (int)minY, (int)maxY);
        for (int i = 0; i < 40; ++i) {
            BlockPos check = new BlockPos(cursor.getX(), y, cursor.getZ());
            if (!world.getBlockState(check).isAir()) {
                return new Vec3d(pos.x, (double)y + 1.0, pos.z);
            }
            if (--y < minY) break;
        }
        return new Vec3d(pos.x, pos.y, pos.z);
    }

    private static void applyInvulnerability(ServerPlayerEntity player, int ticks) {
        if (player == null) {
            return;
        }
        player.timeUntilRegen = Math.max(player.timeUntilRegen, ticks);
    }

    private static boolean isFragile(BlockState state) {
        if (state == null) {
            return false;
        }
        return state.isOf(Blocks.field_10102) || state.isOf(Blocks.field_10534) || state.isOf(Blocks.field_10566) || state.isOf(Blocks.field_10253) || state.isOf(Blocks.field_28685) || state.isOf(Blocks.field_10219) || state.isOf(Blocks.field_10340) || state.isOf(Blocks.field_10445) || state.isOf(Blocks.field_10115) || state.isOf(Blocks.field_10508) || state.isOf(Blocks.field_10474) || state.isOf(Blocks.field_9979) || state.isOf(Blocks.field_10344) || state.isOf(Blocks.field_10161) || state.isOf(Blocks.field_9975) || state.isOf(Blocks.field_10148) || state.isOf(Blocks.field_10334) || state.isOf(Blocks.field_10218) || state.isOf(Blocks.field_10075) || state.isOf(Blocks.field_10431) || state.isOf(Blocks.field_10037) || state.isOf(Blocks.field_10511) || state.isOf(Blocks.field_10306) || state.isOf(Blocks.field_10533) || state.isOf(Blocks.field_10010) || state.isOf(Blocks.field_10255) || state.isOf(Blocks.field_9989) || state.isOf(Blocks.field_10056) || state.isOf(Blocks.field_29031) || state.isOf(Blocks.field_28888) || state.isOf(Blocks.field_28900) || state.isOf(Blocks.field_27165) || state.isOf(Blocks.field_37576) || state.isOf(Blocks.field_37556);
    }

    private static List<LivingEntity> getLivingEntitiesAround(ServerWorld world, Vec3d center, double radius) {
        return world.getEntitiesByClass(LivingEntity.class, new Box(center.add(-radius, -2.0, -radius), center.add(radius, 3.0, radius)), entity -> entity.isAlive());
    }

    private static List<LivingEntity> getLivingEntitiesAround(ServerWorld world, Vec3d center, double radius, Predicate<LivingEntity> filter) {
        return world.getEntitiesByClass(LivingEntity.class, new Box(center.add(-radius, -2.0, -radius), center.add(radius, 3.0, radius)), entity -> entity.isAlive() && (filter == null || filter.test((LivingEntity)entity)));
    }

    private static List<LivingEntity> getLivingEntitiesInCone(ServerWorld world, PlayerEntity player, double range, double degrees) {
        Vec3d start = player.getCameraPosVec(1.0f);
        Vec3d look = player.getRotationVec(1.0f).normalize();
        double minDot = Math.cos(Math.toRadians(degrees));
        ArrayList<LivingEntity> targets = new ArrayList<LivingEntity>();
        for (LivingEntity entity2 : world.getEntitiesByClass(LivingEntity.class, new Box(start.add(-range, -range, -range), start.add(range, range, range)), entity -> entity.isAlive() && entity != player)) {
            double dot;
            Vec3d toTarget = entity2.getPos().add(0.0, (double)entity2.getHeight() * 0.6, 0.0).subtract(start);
            if (toTarget.lengthSquared() < 0.01 || !((dot = look.dotProduct(toTarget.normalize())) >= minDot)) continue;
            targets.add(entity2);
        }
        return targets;
    }

    private static Vec3d getLookTargetPoint(ServerWorld world, PlayerEntity player, double range) {
        Vec3d look;
        Vec3d end;
        Vec3d start = player.getCameraPosVec(1.0f);
        BlockHitResult hit = world.raycast(new RaycastContext(start, end = start.add((look = player.getRotationVec(1.0f).normalize()).multiply(range)), RaycastContext.class_3960.field_17558, RaycastContext.class_242.field_1347, (Entity)player));
        if (hit != null && hit.getType() != HitResult.class_240.field_1333) {
            Vec3d hitPos = hit.getPos();
            return new Vec3d(hitPos.x, hitPos.y, hitPos.z);
        }
        return new Vec3d(end.x, player.getY(), end.z);
    }

    private static void applyRadialKnockback(ServerWorld world, Vec3d center, double radius, double strength) {
        for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(world, center, radius)) {
            Vec3d delta = entity.getPos().subtract(center);
            if (delta.lengthSquared() < 0.01) continue;
            Vec3d push = delta.normalize().multiply(strength);
            entity.addVelocity(push.x, 0.2, push.z);
            entity.velocityModified = true;
        }
    }

    private static DisplayEntity.class_8123 createTextDisplay(ServerWorld world, String text, int color, Vec3d pos, float scale) {
        DisplayEntity.class_8123 display = new DisplayEntity.class_8123(EntityType.field_42457, (World)world);
        display.setText((Text)Text.literal((String)text).formatted(Formatting.field_1067).withColor(color));
        display.setBillboardMode(DisplayEntity.class_8114.field_42409);
        display.setBackground(0);
        display.setTextOpacity((byte)-1);
        display.setPosition(pos.x, pos.y, pos.z);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scale, scale, scale), new Quaternionf()));
        world.spawnEntity((Entity)display);
        return display;
    }

    private static DisplayEntity.class_8122 createItemDisplay(ServerWorld world, ItemStack stack, Vec3d pos, float scale) {
        DisplayEntity.class_8122 display = new DisplayEntity.class_8122(EntityType.field_42456, (World)world);
        display.setItemStack(stack);
        display.setPosition(pos.x, pos.y, pos.z);
        display.setNoGravity(true);
        display.setShadowRadius(0.0f);
        display.setInterpolationDuration(1);
        display.setGlowing(true);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scale, scale, scale), new Quaternionf()));
        world.spawnEntity((Entity)display);
        return display;
    }

    private static void updateItemDisplay(DisplayEntity.class_8122 display, Vec3d pos, float scale) {
        if (display == null || !display.isAlive()) {
            return;
        }
        display.setPosition(pos.x, pos.y, pos.z);
        display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(scale, scale, scale), new Quaternionf()));
    }

    private static boolean isIndestructible(BlockState state) {
        if (state == null) {
            return true;
        }
        return state.isOf(Blocks.field_9987) || state.isOf(Blocks.field_10499) || state.isOf(Blocks.field_10398) || state.isOf(Blocks.field_10027) || state.isOf(Blocks.field_10613) || state.isOf(Blocks.field_10525) || state.isOf(Blocks.field_10395) || state.isOf(Blocks.field_10263) || state.isOf(Blocks.field_10465) || state.isOf(Blocks.field_16540) || state.isOf(Blocks.field_10316) || state.isOf(Blocks.field_38420) || state.isOf(Blocks.field_10260);
    }

    private static boolean isBloodEclipseProtected(BlockState state) {
        if (state == null) {
            return true;
        }
        if (SpellVisualsManager.isIndestructible(state)) {
            return true;
        }
        return state.isOf(Blocks.field_10540) || state.isOf(Blocks.field_22423) || state.isOf(Blocks.field_23152) || state.isOf(Blocks.field_22108) || state.getHardness(null, BlockPos.ORIGIN) < 0.0f || state.getHardness(null, BlockPos.ORIGIN) > 20.0f;
    }

    private static List<BlockPos> collectBeamBlocks(ServerWorld world, Vec3d origin, Vec3d direction, int range, int halfWidth) {
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        Vec3d dir = direction.normalize();
        BlockPos.class_2339 cursor = new BlockPos.class_2339();
        for (int step = 0; step <= range; ++step) {
            Vec3d center = origin.add(dir.multiply((double)step));
            int cx = MathHelper.floor((double)center.x);
            int cy = MathHelper.floor((double)center.y);
            int cz = MathHelper.floor((double)center.z);
            for (int dx = -halfWidth; dx <= halfWidth; ++dx) {
                for (int dy = -halfWidth; dy <= halfWidth; ++dy) {
                    for (int dz = -halfWidth; dz <= halfWidth; ++dz) {
                        cursor.set(cx + dx, cy + dy, cz + dz);
                        blocks.add(cursor.toImmutable());
                    }
                }
            }
        }
        return blocks;
    }

    private static void applyBeamDamage(ServerWorld world, Vec3d origin, Vec3d direction, int range, double radius, float centerDamage, float outerDamage) {
        Vec3d dir = direction.normalize();
        Vec3d end = origin.add(dir.multiply((double)range));
        Box box = new Box(origin, end).expand(radius + 4.0);
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, box, living -> living.isAlive())) {
            PlayerEntity player;
            if (entity instanceof PlayerEntity && (player = (PlayerEntity)entity).isSpectator()) continue;
            Vec3d point = SpellVisualsManager.closestPointOnSegment(origin, end, entity.getPos().add(0.0, (double)entity.getHeight() * 0.5, 0.0));
            double dist = point.distanceTo(entity.getPos().add(0.0, (double)entity.getHeight() * 0.5, 0.0));
            if (dist <= radius) {
                entity.damage(world, world.getDamageSources().magic(), centerDamage);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11221, entity.getX(), entity.getBodyY(0.6), entity.getZ(), 1, 0.2, 0.2, 0.2, 0.0);
                continue;
            }
            if (!(dist <= radius + 4.0)) continue;
            entity.damage(world, world.getDamageSources().magic(), outerDamage);
            Vec3d push = entity.getPos().subtract(point).normalize().multiply(0.6);
            entity.addVelocity(push.x, 0.2, push.z);
            entity.velocityModified = true;
            entity.setOnFireFor(5.0f);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, entity.getX(), entity.getBodyY(0.6), entity.getZ(), 4, 0.6, 0.6, 0.6, 0.0);
        }
    }

    private static Vec3d closestPointOnSegment(Vec3d start, Vec3d end, Vec3d point) {
        Vec3d segment = end.subtract(start);
        double lenSq = segment.lengthSquared();
        if (lenSq <= 1.0E-4) {
            return start;
        }
        double t = point.subtract(start).dotProduct(segment) / lenSq;
        t = MathHelper.clamp((double)t, (double)0.0, (double)1.0);
        return start.add(segment.multiply(t));
    }

    private static void destroyBeamBlocks(ServerWorld world, List<BlockPos> blocks, float dropChance) {
        if (blocks == null || blocks.isEmpty()) {
            return;
        }
        for (BlockPos pos : blocks) {
            BlockState state = world.getBlockState(pos);
            if (state.isAir() || SpellVisualsManager.isIndestructible(state)) continue;
            if (world.random.nextFloat() < dropChance) {
                world.breakBlock(pos, true);
                continue;
            }
            world.setBlockState(pos, Blocks.field_10124.getDefaultState(), 3);
        }
    }

    private static void spawnBeamParticles(ServerWorld world, Vec3d origin, Vec3d direction, int range, int count, double spread, ParticleEffect type) {
        if (!Nomorespell.shouldSpawnParticles(world, origin)) {
            return;
        }
        Vec3d dir = direction.normalize();
        for (int i = 0; i < count; ++i) {
            double t = world.random.nextDouble() * (double)range;
            Vec3d pos = origin.add(dir.multiply(t));
            double ox = (world.random.nextDouble() - 0.5) * spread;
            double oy = (world.random.nextDouble() - 0.5) * spread;
            double oz = (world.random.nextDouble() - 0.5) * spread;
            world.spawnParticles(type, pos.x + ox, pos.y + oy, pos.z + oz, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    static {
        HEALING_TOUCH = new HashMap<UUID, HealingTouchState>();
        SWIFTNESS_AURA = new HashMap<UUID, SwiftnessAuraState>();
        FROST_NOVA = new HashMap<UUID, FrostNovaState>();
        GROUP_HEAL = new HashMap<UUID, GroupHealState>();
        VERDANT_HALO = new HashMap<UUID, VerdantHaloState>();
        BATTLE_BOOST = new HashMap<UUID, BattleBoostState>();
        BATTLE_ORBITS = new HashMap<UUID, List<OrbitSymbolState>>();
        CHAIN_LIGHTNING = new HashMap<UUID, ChainLightningState>();
        SACRED_CIRCLE = new HashMap<UUID, SacredCircleState>();
        TEAM_FORTRESS = new HashMap<UUID, TeamFortressState>();
        FORTRESS_RUNES = new HashMap<UUID, List<FortressRuneState>>();
        METEOR_STRIKE = new HashMap<UUID, MeteorStrikeState>();
        DIVINE_SHIELD = new HashMap<UUID, DivineShieldState>();
        TITANS_BLESSING = new HashMap<UUID, TitansBlessingState>();
        TITAN_FORMS = new HashMap<UUID, TitanFormState>();
        METEOR_HAZARDS = new HashMap<UUID, MeteorHazardState>();
        CATACLYSM = new HashMap<UUID, CataclysmState>();
        CATACLYSM_HAZARDS = new HashMap<UUID, CataclysmHazardState>();
        MIRACLE = new HashMap<UUID, MiracleState>();
        MIRACLE_BLESSINGS = new HashMap<UUID, MiracleBlessingState>();
        DOMINION = new HashMap<UUID, DominionState>();
        DOMINION_CHAMPIONS = new HashMap<UUID, DominionChampionState>();
        DOMINION_ENEMIES = new HashMap<UUID, DominionEnemyState>();
        ANNIHILATION_BEAM = new HashMap<UUID, AnnihilationBeamState>();
        GENESIS = new HashMap<UUID, GenesisState>();
        GENESIS_BLESSINGS = new HashMap<UUID, GenesisBlessingState>();
        OMEGA_DOMINION = new HashMap<UUID, OmegaDominanceState>();
        OMEGA_CHAMPIONS = new HashMap<UUID, OmegaChampionState>();
        OMEGA_ENEMIES = new HashMap<UUID, OmegaEnemyState>();
        BLOOD_ECLIPSE = new HashMap<UUID, BloodEclipseState>();
        RAIN_OF_PICKS = new HashMap<UUID, RainOfPicksState>();
        DOMAIN_EXPANSIONS = new HashMap<UUID, DomainExpansionState>();
        DEATH_RECORDS = new HashMap<UUID, DeathRecord>();
    }

    private static class HealingTouchState {
        private final UUID playerId;
        private final ServerWorld world;
        private final Vec3d startPos;
        private final Vec3d markerPos;
        private final float healAmount;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> invocationDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> burstDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Float> burstAngles = new ArrayList<Float>();

        private HealingTouchState(ServerWorld world, PlayerEntity player, float healAmount) {
            this.world = world;
            this.playerId = player.getUuid();
            this.startPos = player.getPos();
            this.markerPos = this.startPos.add(0.0, 1.0, 0.0);
            this.healAmount = healAmount;
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(this.markerPos.x, this.markerPos.y, this.markerPos.z);
            world.spawnEntity((Entity)this.marker);
            for (int i = 0; i < 8; ++i) {
                float angle = (float)Math.toRadians((double)i * 45.0);
                Vec3d pos = this.startPos.add(Math.cos(angle) * 1.2, 1.0, Math.sin(angle) * 1.2);
                DisplayEntity.class_8115 display = SpellVisualsManager.createDisplay(world, Blocks.field_10174.getDefaultState(), pos, 0.06f);
                this.invocationDisplays.add(display);
            }
        }

        private boolean tick(ServerWorld world) {
            if (world != this.world) {
                return false;
            }
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            if (this.tick == 0) {
                float oldHealth = player.getHealth();
                float maxHealth = player.getMaxHealth();
                float newHealth = Math.min(maxHealth, oldHealth + this.healAmount);
                player.setHealth(newHealth);
                Nomorespell.LOGGER.info("Healing Touch cast: healed caster from {} to {}", (Object)Float.valueOf(oldHealth), (Object)Float.valueOf(newHealth));
            }
            if (this.tick < 12) {
                Vec3d center = player.getPos();
                progress = (float)this.tick / 12.0f;
                float scale = 0.06f + 0.06f * progress;
                float rotationOffset = (float)this.tick * 30.0f;
                for (int i = 0; i < this.invocationDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((double)i * 45.0 + (double)rotationOffset);
                    Vec3d pos = new Vec3d(center.x + Math.cos(angle) * 1.2, center.y + 1.0, center.z + Math.sin(angle) * 1.2);
                    SpellVisualsManager.updateDisplay(this.invocationDisplays.get(i), pos, scale);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, center.x, center.y + 1.5, center.z, 2, 0.5, 0.5, 0.5, 0.0);
                if (this.tick % 3 == 0) {
                    world.playSound(null, center.x, center.y, center.z, (SoundEvent)SoundEvents.field_15114.comp_349(), SoundCategory.field_15248, 0.5f, 1.5f);
                }
            } else if (this.tick < 20) {
                int phaseTick = this.tick - 12;
                progress = (float)phaseTick / 8.0f;
                radius = 1.2f * (1.0f - progress);
                float height = 1.0f - 0.2f * progress;
                for (int i = 0; i < this.invocationDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((double)i * 45.0);
                    Vec3d pos = new Vec3d(this.startPos.x + Math.cos(angle) * (double)radius, this.startPos.y + (double)height, this.startPos.z + Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay(this.invocationDisplays.get(i), pos, 0.12f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, this.markerPos.x, this.markerPos.y, this.markerPos.z, 4, 0.3, 0.3, 0.3, 0.0);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_14627, SoundCategory.field_15248, 0.3f, 1.0f);
            } else if (this.tick < 24) {
                Vec3d pos;
                int burstTick = this.tick - 20;
                if (burstTick == 0) {
                    for (DisplayEntity.class_8115 display : this.invocationDisplays) {
                        SpellVisualsManager.removeDisplay(display);
                    }
                    this.invocationDisplays.clear();
                    for (int i = 0; i < 12; ++i) {
                        float angle = (float)((double)world.random.nextFloat() * Math.PI * 2.0);
                        double offsetY = world.random.nextDouble() * 0.2 - 0.1;
                        pos = new Vec3d(this.markerPos.x + Math.cos(angle) * 0.1, this.markerPos.y + offsetY, this.markerPos.z + Math.sin(angle) * 0.1);
                        DisplayEntity.class_8115 burst = SpellVisualsManager.createDisplay(world, Blocks.field_10157.getDefaultState(), pos, 0.08f);
                        this.burstDisplays.add(burst);
                        this.burstAngles.add(Float.valueOf(angle));
                    }
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, this.markerPos.x, this.markerPos.y, this.markerPos.z, 20, 0.5, 0.5, 0.5, 0.1);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, player.getX(), player.getY() + 1.0, player.getZ(), 50, 2.0, 2.0, 2.0, 0.0);
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_17265, SoundCategory.field_15248, 1.0f, 1.0f);
                }
                progress = (float)burstTick / 4.0f;
                radius = 0.8f * progress;
                for (int i = 0; i < this.burstDisplays.size(); ++i) {
                    float angle = this.burstAngles.get(i).floatValue();
                    pos = new Vec3d(this.markerPos.x + Math.cos(angle) * (double)radius, this.markerPos.y, this.markerPos.z + Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay(this.burstDisplays.get(i), pos, 0.08f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, this.markerPos.x, this.markerPos.y, this.markerPos.z, 5, 0.5, 0.5, 0.5, 0.0);
            }
            if (this.tick >= 23) {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            for (DisplayEntity.class_8115 display : this.invocationDisplays) {
                SpellVisualsManager.removeDisplay(display);
            }
            for (DisplayEntity.class_8115 display : this.burstDisplays) {
                SpellVisualsManager.removeDisplay(display);
            }
            this.invocationDisplays.clear();
            this.burstDisplays.clear();
            this.burstAngles.clear();
        }
    }

    private static class SwiftnessAuraState {
        private final UUID playerId;
        private final ServerWorld world;
        private final int buffDurationTicks;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> helixPurple = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> helixBlue = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> crownDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Float> crownScales = new ArrayList<Float>();
        private final List<DisplayEntity.class_8115> shieldRingDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> orbitDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> allDisplays = new ArrayList<DisplayEntity.class_8115>();
        private int pulseTicks = 0;
        private int shieldRingTicks = 0;

        private SwiftnessAuraState(ServerWorld world, PlayerEntity player, int buffDurationTicks) {
            this.world = world;
            this.playerId = player.getUuid();
            this.buffDurationTicks = buffDurationTicks;
        }

        private boolean tick(ServerWorld world) {
            float angle;
            int i;
            ServerPlayerEntity sp;
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive() || player.getWorld() != world || player instanceof ServerPlayerEntity && (sp = (ServerPlayerEntity)player).isDisconnected()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            if (this.tick == 0) {
                this.applyBuffs(player);
                for (i = 0; i < 6; ++i) {
                    angle = (float)Math.toRadians((float)i * 60.0f);
                    Vec3d purplePos = base.add(Math.cos(angle) * 0.9, 0.0, Math.sin(angle) * 0.9);
                    Vec3d bluePos = base.add(Math.cos(angle) * 0.7, 0.0, Math.sin(angle) * 0.7);
                    DisplayEntity.class_8115 purple = SpellVisualsManager.createDisplay(world, Blocks.field_27159.getDefaultState(), purplePos, 0.09f);
                    DisplayEntity.class_8115 blue = SpellVisualsManager.createDisplay(world, Blocks.field_22115.getDefaultState(), bluePos, 0.08f);
                    this.helixPurple.add(purple);
                    this.helixBlue.add(blue);
                    this.allDisplays.add(purple);
                    this.allDisplays.add(blue);
                }
            }
            if (this.tick <= 14) {
                Vec3d pos;
                int i2;
                float progress = (float)this.tick / 15.0f;
                float height = progress * 2.5f;
                float purpleRotation = (float)this.tick * 24.0f;
                float blueRotation = (float)this.tick * 30.0f;
                for (i2 = 0; i2 < this.helixPurple.size(); ++i2) {
                    float angle2 = (float)Math.toRadians((float)i2 * 60.0f + purpleRotation);
                    pos = base.add(Math.cos(angle2) * 0.9, (double)height, Math.sin(angle2) * 0.9);
                    SpellVisualsManager.updateDisplay(this.helixPurple.get(i2), pos, 0.09f);
                }
                for (i2 = 0; i2 < this.helixBlue.size(); ++i2) {
                    float angle3 = (float)Math.toRadians((float)i2 * 60.0f - blueRotation);
                    pos = base.add(Math.cos(angle3) * 0.7, (double)height, Math.sin(angle3) * 0.7);
                    SpellVisualsManager.updateDisplay(this.helixBlue.get(i2), pos, 0.08f);
                }
                for (i2 = 0; i2 < 4; ++i2) {
                    double y = base.y + 0.4 + (double)i2 * 0.6;
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, y, base.z, 1, 0.3, 0.2, 0.3, 0.0);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, base.x, base.y + 0.05, base.z, 3, 0.25, 0.1, 0.25, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, base.x, base.y + 1.2, base.z, 2, 0.2, 0.2, 0.2, 0.0);
                if (this.tick % 2 == 0) {
                    float pitch = 0.95f + world.random.nextFloat() * 0.1f;
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15119, SoundCategory.field_15248, 0.6f, pitch);
                }
                if (this.tick == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_43154, SoundCategory.field_15248, 0.7f, 1.4f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_38366, SoundCategory.field_15248, 0.5f, 1.6f);
                }
            } else if (this.tick <= 24) {
                int i3;
                if (this.tick == 15) {
                    this.crownDisplays.clear();
                    this.crownScales.clear();
                    this.crownDisplays.addAll(this.helixPurple);
                    this.crownDisplays.addAll(this.helixBlue);
                    for (i = 0; i < this.helixPurple.size(); ++i) {
                        this.crownScales.add(Float.valueOf(0.09f));
                    }
                    for (i = 0; i < this.helixBlue.size(); ++i) {
                        this.crownScales.add(Float.valueOf(0.08f));
                    }
                    this.helixPurple.clear();
                    this.helixBlue.clear();
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14703, SoundCategory.field_15248, 1.0f, 1.2f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_26942, SoundCategory.field_15248, 0.6f, 1.5f);
                }
                phaseTick = this.tick - 15;
                rotation = (float)phaseTick * 18.0f;
                float radius = 0.6f;
                for (i3 = 0; i3 < this.crownDisplays.size(); ++i3) {
                    float angle4 = (float)Math.toRadians((float)i3 * 30.0f + rotation);
                    pos = base.add(Math.cos(angle4) * (double)radius, 2.5, Math.sin(angle4) * (double)radius);
                    float scale = this.crownScales.size() > i3 ? this.crownScales.get(i3).floatValue() : 0.08f;
                    SpellVisualsManager.updateDisplay(this.crownDisplays.get(i3), pos, scale);
                }
                for (i3 = 0; i3 < 6; ++i3) {
                    float angle5 = (float)Math.toRadians((float)i3 * 60.0f + rotation);
                    pos = base.add(Math.cos(angle5) * 0.7, 2.4, Math.sin(angle5) * 0.7);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, pos.x, pos.y, pos.z, 1, 0.02, 0.02, 0.02, 0.0);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 3, 0.3, 0.2, 0.3, 0.0);
            } else if (this.tick <= 29) {
                Vec3d pos;
                int i4;
                phaseTick = this.tick - 25;
                progress = (float)phaseTick / 5.0f;
                float height = 2.5f - 2.0f * progress;
                float radius = 0.6f + 0.4f * progress;
                for (i4 = 0; i4 < this.crownDisplays.size(); ++i4) {
                    float angle6 = (float)Math.toRadians((float)i4 * 30.0f);
                    pos = base.add(Math.cos(angle6) * (double)radius, (double)height, Math.sin(angle6) * (double)radius);
                    float scale = this.crownScales.size() > i4 ? this.crownScales.get(i4).floatValue() : 0.08f;
                    SpellVisualsManager.updateDisplay(this.crownDisplays.get(i4), pos, scale);
                }
                if (this.tick == 27) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y + 1.0, base.z, 40, 0.8, 0.6, 0.8, 0.1);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.0, base.z, 20, 0.6, 0.6, 0.6, 0.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, base.x, base.y + 0.6, base.z, 12, 0.1, 0.8, 0.1, 0.0);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14917, SoundCategory.field_15248, 1.0f, 1.2f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14931, SoundCategory.field_15248, 0.6f, 1.6f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14891, SoundCategory.field_15248, 0.5f, 1.3f);
                    SpellVisualsManager.removeDisplays(this.shieldRingDisplays);
                    this.shieldRingDisplays.clear();
                    for (i4 = 0; i4 < 8; ++i4) {
                        float angle7 = (float)Math.toRadians((float)i4 * 45.0f);
                        pos = base.add(Math.cos(angle7) * 1.2, 1.0, Math.sin(angle7) * 1.2);
                        DisplayEntity.class_8115 ring = SpellVisualsManager.createDisplay(world, Blocks.field_10271.getDefaultState(), pos, 0.12f);
                        this.shieldRingDisplays.add(ring);
                        this.allDisplays.add(ring);
                    }
                    this.shieldRingTicks = 6;
                }
            } else if (this.tick <= 529) {
                if (this.tick == 30) {
                    SpellVisualsManager.removeDisplays(this.crownDisplays);
                    this.crownDisplays.clear();
                    this.crownScales.clear();
                    for (i = 0; i < 4; ++i) {
                        angle = (float)Math.toRadians((float)i * 90.0f);
                        Vec3d pos = base.add(Math.cos(angle) * 0.5, 1.0, Math.sin(angle) * 0.5);
                        DisplayEntity.class_8115 orbit = SpellVisualsManager.createDisplay(world, Blocks.field_10455.getDefaultState(), pos, 0.04f);
                        this.orbitDisplays.add(orbit);
                        this.allDisplays.add(orbit);
                    }
                }
                int orbitTick = this.tick - 30;
                rotation = (float)orbitTick * 18.0f;
                if (orbitTick % 100 == 0) {
                    this.pulseTicks = 10;
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 20, 0.8, 0.5, 0.8, 0.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, base.x, base.y + 1.0, base.z, 12, 0.7, 0.3, 0.7, 0.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y + 1.0, base.z, 8, 0.6, 0.4, 0.6, 0.0);
                    world.playSound(null, base.x, base.y + 1.0, base.z, (SoundEvent)SoundEvents.field_14725.comp_349(), SoundCategory.field_15248, 0.5f, 1.7f);
                    world.playSound(null, base.x, base.y + 1.0, base.z, SoundEvents.field_15045, SoundCategory.field_15248, 0.4f, 1.3f);
                }
                float scale = this.pulseTicks > 0 ? 0.06f : 0.04f;
                for (int i5 = 0; i5 < this.orbitDisplays.size(); ++i5) {
                    float angle8 = (float)Math.toRadians((float)i5 * 90.0f + rotation);
                    pos = base.add(Math.cos(angle8) * 0.5, 1.0, Math.sin(angle8) * 0.5);
                    SpellVisualsManager.updateDisplay(this.orbitDisplays.get(i5), pos, scale);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 2, 0.4, 0.3, 0.4, 0.0);
                if (!this.orbitDisplays.isEmpty()) {
                    DisplayEntity.class_8115 selected = this.orbitDisplays.get(world.random.nextInt(this.orbitDisplays.size()));
                    Vec3d spark = selected.getPos();
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, spark.x, spark.y, spark.z, 1, 0.05, 0.05, 0.05, 0.0);
                }
                if (this.tick % 5 == 0) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, base.x, base.y + 0.05, base.z, 4, 0.25, 0.1, 0.25, 0.0);
                }
                if (this.pulseTicks > 0) {
                    --this.pulseTicks;
                }
            } else if (this.tick <= 549) {
                int fadeTick = this.tick - 530;
                progress = (float)fadeTick / 20.0f;
                float scale = 0.04f * (1.0f - progress);
                float rotation = (float)(this.tick - 30) * 18.0f;
                for (int i6 = 0; i6 < this.orbitDisplays.size(); ++i6) {
                    float angle9 = (float)Math.toRadians((float)i6 * 90.0f + rotation);
                    Vec3d pos = base.add(Math.cos(angle9) * 0.5, 1.0, Math.sin(angle9) * 0.5);
                    SpellVisualsManager.updateDisplay(this.orbitDisplays.get(i6), pos, scale);
                }
                if (!this.orbitDisplays.isEmpty()) {
                    DisplayEntity.class_8115 selected = this.orbitDisplays.get(world.random.nextInt(this.orbitDisplays.size()));
                    Vec3d spark = selected.getPos();
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, spark.x, spark.y, spark.z, 1, 0.0, 0.0, 0.0, 0.0);
                }
                if (this.tick % 4 == 0) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, base.x, base.y + 1.0, base.z, 2, 0.4, 0.3, 0.4, 0.0);
                }
                if (this.tick >= 549) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.0, base.z, 25, 0.8, 0.6, 0.8, 0.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, base.x, base.y + 1.0, base.z, 12, 0.6, 0.6, 0.6, 0.0);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_26980, SoundCategory.field_15248, 0.6f, 1.8f);
                    this.cleanup();
                    return true;
                }
            }
            if (this.shieldRingTicks > 0 && !this.shieldRingDisplays.isEmpty()) {
                for (int i7 = 0; i7 < this.shieldRingDisplays.size(); ++i7) {
                    angle = (float)Math.toRadians((float)i7 * (360.0f / (float)this.shieldRingDisplays.size()));
                    Vec3d pos = base.add(Math.cos(angle) * 1.2, 1.0, Math.sin(angle) * 1.2);
                    SpellVisualsManager.updateDisplay(this.shieldRingDisplays.get(i7), pos, 0.12f);
                }
                --this.shieldRingTicks;
                if (this.shieldRingTicks <= 0) {
                    SpellVisualsManager.removeDisplays(this.shieldRingDisplays);
                    this.shieldRingDisplays.clear();
                }
            }
            ++this.tick;
            return false;
        }

        private void applyBuffs(PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5904, this.buffDurationTicks, 0, false, false));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, this.buffDurationTicks, 2, false, false));
            Nomorespell.LOGGER.info("Swiftness Aura cast: applied Speed I + Resistance III for {} ticks", (Object)this.buffDurationTicks);
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.allDisplays);
            SpellVisualsManager.removeDisplays(this.helixPurple);
            SpellVisualsManager.removeDisplays(this.helixBlue);
            SpellVisualsManager.removeDisplays(this.crownDisplays);
            SpellVisualsManager.removeDisplays(this.shieldRingDisplays);
            SpellVisualsManager.removeDisplays(this.orbitDisplays);
            this.helixPurple.clear();
            this.helixBlue.clear();
            this.crownDisplays.clear();
            this.crownScales.clear();
            this.shieldRingDisplays.clear();
            this.orbitDisplays.clear();
            this.allDisplays.clear();
        }
    }

    private static class FrostNovaState {
        private static final int TOTAL_TICKS = 30;
        private static final int EMERGE_TICKS = 4;
        private static final int IMPACT_START = 4;
        private static final int IMPACT_END = 10;
        private final ServerWorld world;
        private final List<IceSpikeState> spikes = new ArrayList<IceSpikeState>();
        private int tick = 0;

        private FrostNovaState(ServerWorld world, List<Vec3d> hitPositions) {
            this.world = world;
            for (Vec3d pos : hitPositions) {
                this.spikes.add(new IceSpikeState(world, pos));
            }
        }

        private boolean tick(ServerWorld world) {
            if (this.spikes.isEmpty()) {
                return true;
            }
            for (IceSpikeState spike : this.spikes) {
                spike.tick(this.tick);
            }
            ++this.tick;
            if (this.tick >= 30) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            for (IceSpikeState spike : this.spikes) {
                spike.cleanup();
            }
            this.spikes.clear();
        }

        private static class IceSpikeState {
            private final ServerWorld world;
            private final Vec3d basePos;
            private final List<DisplayEntity.class_8115> parts = new ArrayList<DisplayEntity.class_8115>();
            private final List<Vec3d> offsets = new ArrayList<Vec3d>();
            private final List<Vector3f> scales = new ArrayList<Vector3f>();
            private final List<Float> rotations = new ArrayList<Float>();
            private final float tiltX;
            private final float tiltZ;
            private boolean impactPlayed = false;

            private IceSpikeState(ServerWorld world, Vec3d targetPos) {
                this.world = world;
                this.basePos = new Vec3d(targetPos.x, targetPos.y, targetPos.z);
                this.tiltX = (world.random.nextFloat() - 0.5f) * 0.35f;
                this.tiltZ = (world.random.nextFloat() - 0.5f) * 0.35f;
                this.buildSpike();
                world.playSound(null, this.basePos.x, this.basePos.y, this.basePos.z, SoundEvents.field_26980, SoundCategory.field_15248, 0.6f, 0.8f + world.random.nextFloat() * 0.2f);
            }

            private void buildSpike() {
                int layers = 5 + this.world.random.nextInt(3);
                float height = 1.5f + this.world.random.nextFloat() * 1.0f;
                float segmentHeight = height / (float)layers;
                BlockState[] palette = new BlockState[]{Blocks.field_10295.getDefaultState(), Blocks.field_10225.getDefaultState(), Blocks.field_10491.getDefaultState(), Blocks.field_10271.getDefaultState()};
                for (int i = 0; i < layers; ++i) {
                    float progress = 1.0f - (float)i / (float)layers;
                    float baseSize = 0.35f + progress * 0.25f;
                    float yOffset = (float)i * segmentHeight;
                    float offsetX = (this.world.random.nextFloat() - 0.5f) * 0.15f * progress;
                    float offsetZ = (this.world.random.nextFloat() - 0.5f) * 0.15f * progress;
                    BlockState state = palette[this.world.random.nextInt(palette.length)];
                    DisplayEntity.class_8115 display = SpellVisualsManager.createDisplay(this.world, state, this.basePos, baseSize, segmentHeight * 0.9f, baseSize);
                    this.parts.add(display);
                    this.offsets.add(new Vec3d((double)offsetX, (double)yOffset, (double)offsetZ));
                    this.scales.add(new Vector3f(baseSize, segmentHeight * 0.9f, baseSize));
                    this.rotations.add(Float.valueOf((this.world.random.nextFloat() - 0.5f) * 0.4f));
                }
            }

            private void tick(int tick) {
                float emergeProgress = MathHelper.clamp((float)((float)tick / 4.0f), (float)0.0f, (float)1.0f);
                float growScale = Math.max(0.05f, emergeProgress);
                for (int i = 0; i < this.parts.size(); ++i) {
                    Vec3d offset = this.offsets.get(i);
                    Vector3f scale = this.scales.get(i);
                    float localScale = growScale;
                    Vec3d pos = this.basePos.add(offset.x, offset.y * (double)emergeProgress, offset.z);
                    Quaternionf rotation = new Quaternionf().rotationXYZ(this.tiltX, this.rotations.get(i).floatValue(), this.tiltZ);
                    SpellVisualsManager.updateDisplayRotated(this.parts.get(i), pos, scale.x() * localScale, scale.y() * localScale, scale.z() * localScale, rotation);
                }
                if (tick >= 4 && tick <= 10 && !this.impactPlayed) {
                    this.impactPlayed = true;
                    this.world.playSound(null, this.basePos.x, this.basePos.y, this.basePos.z, SoundEvents.field_15081, SoundCategory.field_15248, 0.7f, 0.9f + this.world.random.nextFloat() * 0.2f);
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_28013, this.basePos.x, this.basePos.y + 0.9, this.basePos.z, 12, 0.4, 0.6, 0.4, 0.0);
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11205, this.basePos.x, this.basePos.y + 0.7, this.basePos.z, 6, 0.3, 0.4, 0.3, 0.0);
                }
                if (tick >= 10 && tick < 30 && tick % 6 == 0) {
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_28013, this.basePos.x, this.basePos.y + 1.1, this.basePos.z, 2, 0.2, 0.2, 0.2, 0.0);
                }
                if (tick == 29) {
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_28013, this.basePos.x, this.basePos.y + 0.8, this.basePos.z, 6, 0.3, 0.4, 0.3, 0.0);
                }
            }

            private void cleanup() {
                SpellVisualsManager.removeDisplays(this.parts);
                this.parts.clear();
                this.offsets.clear();
                this.scales.clear();
                this.rotations.clear();
            }
        }
    }

    private static class GroupHealState {
        private final UUID playerId;
        private final ServerWorld world;
        private final float healAmount;
        private int tick = 0;
        private final MarkerEntity marker;
        private final DisplayEntity.class_8123 textDisplay;
        private final List<DisplayEntity.class_8115> circleDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> columnDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> chainDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> burstDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final Map<UUID, Vec3d> chainTargets = new HashMap<UUID, Vec3d>();
        private boolean healApplied = false;

        private GroupHealState(ServerWorld world, PlayerEntity player, float healAmount) {
            this.world = world;
            this.playerId = player.getUuid();
            this.healAmount = healAmount;
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 1.0, base.z);
            world.spawnEntity((Entity)this.marker);
            this.textDisplay = new DisplayEntity.class_8123(EntityType.field_42457, (World)world);
            this.textDisplay.setPosition(base.x, base.y + 2.5, base.z);
            this.textDisplay.setInterpolationDuration(1);
            this.textDisplay.setBillboardMode(DisplayEntity.class_8114.field_42409);
            this.textDisplay.setText((Text)Text.literal((String)"+").styled(style -> style.withColor(8190976)));
            this.textDisplay.setBackground(0);
            this.textDisplay.setTextOpacity((byte)-1);
            this.textDisplay.setGlowing(true);
            this.textDisplay.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(3.0f, 3.0f, 3.0f), new Quaternionf()));
            world.spawnEntity((Entity)this.textDisplay);
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 1.0, base.z);
            }
            if (this.textDisplay != null && this.textDisplay.isAlive()) {
                this.textDisplay.setPosition(base.x, base.y + 2.5, base.z);
            }
            if (this.tick == 0) {
                world.playSound(null, base.x, base.y, base.z, Nomorespell.GROUP_HEAL_SOUND, SoundCategory.field_15248, 0.9f, 0.6f);
                world.playSound(null, base.x, base.y, base.z, Nomorespell.GROUP_HEAL_SOUND, SoundCategory.field_15248, 0.6f, 1.0f);
            }
            if (this.tick < 20) {
                if (this.tick == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_28392, SoundCategory.field_15248, 0.7f, 0.6f);
                }
                if (this.circleDisplays.size() < 20) {
                    DisplayEntity.class_8115 display = SpellVisualsManager.createDisplay(world, Blocks.field_28681.getDefaultState(), base, 0.15f, 0.03f, 0.15f);
                    this.circleDisplays.add(display);
                }
                for (int i = 0; i < this.circleDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * 18.0f + (float)this.tick * 18.0f);
                    Vec3d pos = base.add(Math.cos(angle) * 5.0, 0.05, Math.sin(angle) * 5.0);
                    SpellVisualsManager.updateDisplay(this.circleDisplays.get(i), pos, 0.15f, 0.03f, 0.15f);
                }
                int columns = 8;
                while (this.columnDisplays.size() < columns) {
                    this.columnDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10174.getDefaultState(), base, 0.1f, 0.4f, 0.1f));
                }
                float pulse = 1.0f + MathHelper.sin((float)((float)this.tick * 0.4f)) * 0.15f;
                for (int i = 0; i < this.columnDisplays.size(); ++i) {
                    Vec3d pos = base.add(0.0, (double)i * 0.46, 0.0);
                    SpellVisualsManager.updateDisplay(this.columnDisplays.get(i), pos, 0.1f * pulse, 0.4f * pulse, 0.1f * pulse);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_17741, base.x, base.y + 0.2, base.z, 3, 1.2, 0.4, 1.2, 0.02);
                world.playSound(null, base.x, base.y, base.z, (SoundEvent)SoundEvents.field_14624.comp_349(), SoundCategory.field_15248, 0.45f, 0.6f + (float)this.tick * 0.02f);
                if (this.tick == 10) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_28392, SoundCategory.field_15248, 0.7f, 1.0f);
                }
            } else if (this.tick < 35) {
                this.updateGroupHealChains(base);
                world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15119, SoundCategory.field_15248, 0.35f, 1.1f);
            } else if (this.tick < 50) {
                int i;
                if (this.tick == 35) {
                    this.applyGroupHeal(world, base);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14703, SoundCategory.field_15248, 0.9f, 1.0f);
                    world.playSound(null, base.x, base.y, base.z, Nomorespell.GROUP_HEAL_SOUND, SoundCategory.field_15248, 0.7f, 1.0f);
                }
                float sink = (float)(this.tick - 35) / 15.0f * 0.5f;
                for (i = 0; i < this.circleDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * 18.0f + (float)this.tick * 18.0f);
                    Vec3d pos = base.add(Math.cos(angle) * 5.0, (double)(-sink), Math.sin(angle) * 5.0);
                    SpellVisualsManager.updateDisplay(this.circleDisplays.get(i), pos, 0.15f, 0.03f, 0.15f);
                }
                for (i = 0; i < this.columnDisplays.size(); ++i) {
                    Vec3d pos = base.add(0.0, (double)i * 0.46 + (double)(this.tick - 35) * 0.2, 0.0);
                    SpellVisualsManager.updateDisplay(this.columnDisplays.get(i), pos, 0.1f, 0.4f, 0.1f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, base.x, base.y + 1.6, base.z, 40, 0.6, 1.6, 0.6, 0.2);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateGroupHealChains(Vec3d base) {
            this.chainTargets.clear();
            List players = this.world.getEntitiesByClass(PlayerEntity.class, new Box(base.add(-5.0, -2.0, -5.0), base.add(5.0, 3.0, 5.0)), player -> player.isAlive());
            for (PlayerEntity target : players) {
                this.chainTargets.put(target.getUuid(), target.getPos().add(0.0, 1.0, 0.0));
            }
            int needed = this.chainTargets.size() * 6;
            while (this.chainDisplays.size() < needed) {
                this.chainDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10357.getDefaultState(), base, 0.04f));
            }
            while (this.chainDisplays.size() > needed && !this.chainDisplays.isEmpty()) {
                SpellVisualsManager.removeDisplay(this.chainDisplays.remove(this.chainDisplays.size() - 1));
            }
            int index = 0;
            float baseT = (float)(this.tick % 10) / 10.0f;
            for (Vec3d targetPos : this.chainTargets.values()) {
                Vec3d start = base.add(0.0, 1.2, 0.0);
                Vec3d direction = targetPos.subtract(start);
                for (int i = 0; i < 6 && index < this.chainDisplays.size(); ++index, ++i) {
                    float t = (float)(i + 1) / 7.0f;
                    Vec3d pos = start.add(direction.multiply((double)t));
                    SpellVisualsManager.updateDisplay(this.chainDisplays.get(index), pos, 0.04f);
                }
                Vec3d heartOne = start.add(direction.multiply((double)baseT));
                float secondT = baseT + 0.5f;
                if (secondT > 1.0f) {
                    secondT -= 1.0f;
                }
                Vec3d heartTwo = start.add(direction.multiply((double)secondT));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, heartOne.x, heartOne.y, heartOne.z, 1, 0.02, 0.02, 0.02, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, heartTwo.x, heartTwo.y, heartTwo.z, 1, 0.02, 0.02, 0.02, 0.0);
            }
        }

        private void applyGroupHeal(ServerWorld world, Vec3d base) {
            List players = world.getEntitiesByClass(PlayerEntity.class, new Box(base.add(-5.0, -2.0, -5.0), base.add(5.0, 3.0, 5.0)), player -> player.isAlive());
            for (PlayerEntity target : players) {
                float oldHealth = target.getHealth();
                float newHealth = Math.min(target.getMaxHealth(), oldHealth + this.healAmount);
                if (newHealth > oldHealth) {
                    target.setHealth(newHealth);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, target.getX(), target.getBodyY(0.6), target.getZ(), 20, 0.6, 0.8, 0.6, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, target.getX(), target.getBodyY(0.8), target.getZ(), 12, 0.25, 0.4, 0.25, 0.0);
            }
            this.spawnGroupHealBurst(players);
            Nomorespell.LOGGER.info("Group Heal cast: healed {} players", (Object)players.size());
        }

        private void spawnGroupHealBurst(List<PlayerEntity> players) {
            for (PlayerEntity target : players) {
                ArrayList<DisplayEntity.class_8115> burst = new ArrayList<DisplayEntity.class_8115>();
                for (int i = 0; i < 12; ++i) {
                    DisplayEntity.class_8115 display = SpellVisualsManager.createDisplay(this.world, Blocks.field_10028.getDefaultState(), target.getPos(), 0.06f);
                    burst.add(display);
                    this.burstDisplays.add(display);
                }
                float radius = 0.6f;
                Vec3d base = target.getPos();
                for (int i = 0; i < burst.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)burst.size()));
                    Vec3d pos = base.add(Math.cos(angle) * (double)radius, 0.6, Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay((DisplayEntity.class_8115)burst.get(i), pos, 0.06f);
                }
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            if (this.textDisplay != null) {
                this.textDisplay.discard();
            }
            SpellVisualsManager.removeDisplays(this.circleDisplays);
            SpellVisualsManager.removeDisplays(this.columnDisplays);
            SpellVisualsManager.removeDisplays(this.chainDisplays);
            SpellVisualsManager.removeDisplays(this.burstDisplays);
            this.circleDisplays.clear();
            this.columnDisplays.clear();
            this.chainDisplays.clear();
            this.burstDisplays.clear();
            this.chainTargets.clear();
        }
    }

    private static class VerdantHaloState {
        private static final int DURATION_TICKS = 160;
        private static final double RADIUS = 5.0;
        private static final double HEAL_RADIUS = 0.5;
        private final UUID playerId;
        private final ServerWorld world;
        private final float healPerPulse;
        private final float hostileDamage;
        private final int regenerationDuration;
        private final int absorptionDuration;
        private final int startAge;
        private final int durationTicks;
        private boolean finished;
        private int tick = 0;

        private VerdantHaloState(ServerWorld world, PlayerEntity player, float healPerPulse, float hostileDamage, int regenerationDuration, int absorptionDuration) {
            this.world = world;
            this.playerId = player.getUuid();
            this.healPerPulse = healPerPulse;
            this.hostileDamage = hostileDamage;
            this.regenerationDuration = regenerationDuration;
            this.absorptionDuration = absorptionDuration;
            this.startAge = player.age;
            this.durationTicks = 160;
        }

        private boolean tick(ServerWorld world) {
            if (world != this.world) {
                return false;
            }
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            this.syncRenderState(caster, true);
            Vec3d center = caster.getPos();
            this.protectNearbyAreaFromFire(center);
            if (this.tick == 0) {
                caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, this.regenerationDuration, 1, false, true, true));
                caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, this.absorptionDuration, 3, false, true, true));
            }
            Box area = new Box(center.x - 5.0, center.y - 5.0, center.z - 5.0, center.x + 5.0, center.y + 5.0, center.z + 5.0);
            for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, LivingEntity::isAlive)) {
                Vec3d entityCenter = entity.getPos().add(0.0, (double)entity.getHeight() * 0.5, 0.0);
                if (entityCenter.squaredDistanceTo(center) > 25.0) continue;
                if (this.shouldHealTarget(entity)) {
                    if (this.tick % 3 == 0) {
                        entity.heal(this.healPerPulse);
                    }
                    this.spawnVerdantOrbit(world, entity, this.tick);
                    continue;
                }
                if (!(entity instanceof HostileEntity)) continue;
                HostileEntity hostile = (HostileEntity)entity;
                if (this.tick % 10 != 0) continue;
                CombatXpTracker.markSpellDamage((LivingEntity)hostile, caster, "verdant_halo");
                hostile.damage(world, world.getDamageSources().indirectMagic((Entity)caster, (Entity)caster), this.hostileDamage);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, hostile.getX(), hostile.getBodyY(0.7), hostile.getZ(), 4, 0.25, 0.25, 0.25, 0.0);
            }
            if (this.tick >= 159) {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void protectNearbyAreaFromFire(Vec3d center) {
            BlockPos origin = BlockPos.ofFloored((Position)center);
            int radius = 3;
            int radiusSq = radius * radius;
            for (BlockPos pos : BlockPos.iterate((BlockPos)origin.add(-radius, -1, -radius), (BlockPos)origin.add(radius, 1, radius))) {
                if (pos.getSquaredDistance((Vec3i)origin) > (double)radiusSq) continue;
                BlockState state = this.world.getBlockState(pos);
                if (state.isOf(Blocks.field_10036) || state.isOf(Blocks.field_22089)) {
                    this.world.breakBlock(pos, false);
                    continue;
                }
                if (!(state.getBlock() instanceof FireBlock)) continue;
                this.world.breakBlock(pos, false);
            }
        }

        private boolean shouldHealTarget(LivingEntity entity) {
            return entity instanceof PlayerEntity || entity instanceof PassiveEntity;
        }

        private void spawnVerdantOrbit(ServerWorld world, LivingEntity entity, int tick) {
            for (int i = 0; i < 3; ++i) {
                float angle = (float)((double)((float)tick * 0.18f) + 2.0943951023931953 * (double)i);
                double x = entity.getX() + Math.cos(angle) * 0.5;
                double y = entity.getBodyY(0.55) + Math.sin((float)tick * 0.08f + (float)i) * 0.08;
                double z = entity.getZ() + Math.sin(angle) * 0.5;
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
            }
        }

        private void cleanup() {
            this.finished = true;
            PlayerEntity caster = this.world.getPlayerByUuid(this.playerId);
            if (caster != null) {
                this.syncRenderState(caster, false);
            }
        }

        private void syncRenderState(PlayerEntity caster, boolean active) {
            VerdantHaloRenderPayload payload = new VerdantHaloRenderPayload(caster.getId(), active, this.startAge, 160);
            for (ServerPlayerEntity viewer : PlayerLookup.tracking((Entity)caster)) {
                ServerPlayNetworking.send((ServerPlayerEntity)viewer, (CustomPayload)payload);
            }
            if (caster instanceof ServerPlayerEntity) {
                ServerPlayerEntity self = (ServerPlayerEntity)caster;
                ServerPlayNetworking.send((ServerPlayerEntity)self, (CustomPayload)payload);
            }
        }
    }

    private static class BattleBoostState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> circleOne = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> circleTwo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> circleThree = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8122> totemDisplays = new ArrayList<DisplayEntity.class_8122>();
        private final List<DisplayEntity.class_8115> rayDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> lightningDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> impactDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<UUID> buffTargets = new ArrayList<UUID>();
        private boolean buffsApplied = false;

        private BattleBoostState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 12; ++i) {
                this.circleOne.add(SpellVisualsManager.createDisplay(world, Blocks.field_10058.getDefaultState(), base, 0.1f, 0.02f, 0.1f));
            }
            for (i = 0; i < 16; ++i) {
                this.circleTwo.add(SpellVisualsManager.createDisplay(world, Blocks.field_10184.getDefaultState(), base, 0.08f, 0.02f, 0.08f));
            }
            for (i = 0; i < 20; ++i) {
                this.circleThree.add(SpellVisualsManager.createDisplay(world, Blocks.field_10490.getDefaultState(), base, 0.06f, 0.02f, 0.06f));
            }
            for (i = 0; i < 4; ++i) {
                DisplayEntity.class_8122 totem = new DisplayEntity.class_8122(EntityType.field_42456, (World)world);
                totem.setItemStack(new ItemStack((ItemConvertible)Items.field_8288));
                totem.setPosition(base.x, base.y, base.z);
                totem.setNoGravity(true);
                totem.setInterpolationDuration(1);
                totem.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(0.5f, 0.5f, 0.5f), new Quaternionf()));
                world.spawnEntity((Entity)totem);
                this.totemDisplays.add(totem);
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y, base.z);
            }
            if (this.tick < 24) {
                float riseProgress = (float)this.tick / 24.0f;
                this.updateBattleCircles(base, this.tick, 1.0f);
                this.updateTotems(base, riseProgress);
                for (int i = 0; i < 4; ++i) {
                    float angle = (float)Math.toRadians((float)i * 90.0f);
                    Vec3d flamePos = base.add(Math.cos(angle) * 6.0, 0.2 + (double)riseProgress * 1.8, Math.sin(angle) * 6.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, flamePos.x, flamePos.y, flamePos.z, 4, 0.2, 0.4, 0.2, 0.01);
                }
                if (this.tick == 0) {
                    world.playSound(null, base.x, base.y, base.z, Nomorespell.BATTLE_BOOST_SOUND, SoundCategory.field_15248, 0.9f, 1.0f);
                }
                if (this.tick % 6 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14792, SoundCategory.field_15248, 0.6f, 1.5f);
                }
            } else if (this.tick < 42) {
                this.updateBattleCircles(base, this.tick, 1.5f);
                this.updateTotems(base, 1.0f);
                if (this.tick == 24) {
                    this.buildBattleRays(base);
                    for (DisplayEntity.class_8122 totem : this.totemDisplays) {
                        totem.setGlowing(true);
                    }
                    world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_17266, SoundCategory.field_15248, 0.9f, 1.1f);
                }
                this.updateBattleRays(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 0.2, base.z, 10, 3.0, 0.4, 3.0, 0.0);
            } else if (this.tick < 60) {
                if (!this.buffsApplied) {
                    this.buffsApplied = true;
                    this.buildBattleLightningTargets(base);
                    world.playSound(null, base.x, base.y, base.z, Nomorespell.BATTLE_BOOST_SOUND, SoundCategory.field_15248, 0.9f, 1.2f);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, base.x, base.y, base.z, 30, 3.0, 0.3, 3.0, 0.0);
                    SpellVisualsManager.removeDisplays(this.circleOne);
                    SpellVisualsManager.removeDisplays(this.circleTwo);
                    SpellVisualsManager.removeDisplays(this.circleThree);
                    SpellVisualsManager.removeItemDisplays(this.totemDisplays);
                    SpellVisualsManager.removeDisplays(this.rayDisplays);
                    this.circleOne.clear();
                    this.circleTwo.clear();
                    this.circleThree.clear();
                    this.totemDisplays.clear();
                    this.rayDisplays.clear();
                }
                this.updateBattleLightning(base, this.tick - 42);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateBattleCircles(Vec3d base, int tick, float speedMultiplier) {
            float rot1 = (float)tick * 30.0f * speedMultiplier;
            float rot2 = (float)(-tick) * 20.0f * speedMultiplier;
            float rot3 = (float)tick * 15.0f * speedMultiplier;
            this.updateCircle(this.circleOne, base, 2.0, rot1, 0.1f, 0.02f);
            this.updateCircle(this.circleTwo, base, 4.0, rot2, 0.08f, 0.02f);
            this.updateCircle(this.circleThree, base, 6.0, rot3, 0.06f, 0.02f);
        }

        private void updateCircle(List<DisplayEntity.class_8115> displays, Vec3d base, double radius, float rotation, float scaleX, float scaleY) {
            for (int i = 0; i < displays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)displays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * radius, 0.05, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(displays.get(i), pos, scaleX, scaleY, scaleX);
            }
        }

        private void updateTotems(Vec3d base, float progress) {
            for (int i = 0; i < this.totemDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * 90.0f);
                Vec3d pos = base.add(Math.cos(angle) * 6.0, 0.2 + (double)progress * 1.8, Math.sin(angle) * 6.0);
                DisplayEntity.class_8122 totem = this.totemDisplays.get(i);
                totem.setPosition(pos.x, pos.y, pos.z);
            }
        }

        private void buildBattleRays(Vec3d base) {
            this.rayDisplays.clear();
            for (int i = 0; i < 4; ++i) {
                float angle = (float)Math.toRadians((float)i * 90.0f);
                Vec3d totemPos = base.add(Math.cos(angle) * 6.0, 1.4, Math.sin(angle) * 6.0);
                for (int j = 0; j < 8; ++j) {
                    DisplayEntity.class_8115 ray = SpellVisualsManager.createDisplay(this.world, Blocks.field_10227.getDefaultState(), totemPos, 0.04f);
                    this.rayDisplays.add(ray);
                }
            }
        }

        private void updateBattleRays(Vec3d base) {
            int index = 0;
            for (int i = 0; i < 4; ++i) {
                float angle = (float)Math.toRadians((float)i * 90.0f);
                Vec3d start = base.add(Math.cos(angle) * 6.0, 1.4, Math.sin(angle) * 6.0);
                Vec3d end = base.add(0.0, 1.2, 0.0);
                Vec3d delta = end.subtract(start);
                for (int j = 0; j < 8 && index < this.rayDisplays.size(); ++index, ++j) {
                    float t = (float)(j + 1) / 9.0f;
                    Vec3d pos = start.add(delta.multiply((double)t));
                    SpellVisualsManager.updateDisplay(this.rayDisplays.get(index), pos, 0.04f);
                }
            }
            while (index < this.rayDisplays.size()) {
                SpellVisualsManager.removeDisplay(this.rayDisplays.get(index));
                ++index;
            }
        }

        private void buildBattleLightningTargets(Vec3d base) {
            this.buffTargets.clear();
            List players = this.world.getEntitiesByClass(PlayerEntity.class, new Box(base.add(-6.0, -2.0, -6.0), base.add(6.0, 3.0, 6.0)), player -> player.isAlive());
            for (PlayerEntity target : players) {
                int i;
                if (target.getPos().distanceTo(base) > 6.0) continue;
                this.buffTargets.add(target.getUuid());
                for (i = 0; i < 10; ++i) {
                    this.lightningDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10205.getDefaultState(), base, 0.06f));
                }
                for (i = 0; i < 16; ++i) {
                    this.impactDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10002.getDefaultState(), base, 0.05f));
                }
            }
        }

        private void updateBattleLightning(Vec3d base, int localTick) {
            int lightningIndex = 0;
            int impactIndex = 0;
            Vec3d[] totemPositions = new Vec3d[]{base.add(6.0, 1.6, 0.0), base.add(-6.0, 1.6, 0.0), base.add(0.0, 1.6, 6.0), base.add(0.0, 1.6, -6.0)};
            for (UUID targetId : this.buffTargets) {
                Vec3d pos;
                int i;
                PlayerEntity target = this.world.getPlayerByUuid(targetId);
                if (target == null || !target.isAlive()) continue;
                Vec3d targetPos = target.getPos().add(0.0, 1.0, 0.0);
                Vec3d start = totemPositions[0];
                double bestDist = targetPos.squaredDistanceTo(start);
                for (int i2 = 1; i2 < totemPositions.length; ++i2) {
                    double dist = targetPos.squaredDistanceTo(totemPositions[i2]);
                    if (!(dist < bestDist)) continue;
                    bestDist = dist;
                    start = totemPositions[i2];
                }
                Vec3d delta = targetPos.subtract(start);
                for (i = 0; i < 10 && lightningIndex < this.lightningDisplays.size(); ++lightningIndex, ++i) {
                    float t = Math.min(1.0f, ((float)localTick * 0.5f + (float)i) / 10.0f);
                    pos = start.add(delta.multiply((double)t));
                    SpellVisualsManager.updateDisplay(this.lightningDisplays.get(lightningIndex), pos, 0.06f);
                }
                if (localTick != 8) continue;
                for (i = 0; i < 16 && impactIndex < this.impactDisplays.size(); ++impactIndex, ++i) {
                    float angle = (float)Math.toRadians((float)i * 22.5f);
                    pos = targetPos.add(Math.cos(angle) * 0.6, 0.1, Math.sin(angle) * 0.6);
                    SpellVisualsManager.updateDisplay(this.impactDisplays.get(impactIndex), pos, 0.05f);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, targetPos.x, targetPos.y, targetPos.z, 15, 0.6, 0.6, 0.6, 0.0);
                this.world.playSound(null, targetPos.x, targetPos.y, targetPos.z, SoundEvents.field_14833, SoundCategory.field_15248, 0.6f, 1.4f);
            }
            if (localTick == 8) {
                this.applyBattleBoostEffects();
            }
            if (localTick >= 16) {
                SpellVisualsManager.removeDisplays(this.lightningDisplays);
                SpellVisualsManager.removeDisplays(this.impactDisplays);
                this.lightningDisplays.clear();
                this.impactDisplays.clear();
            }
        }

        private void applyBattleBoostEffects() {
            Vec3d center = this.marker.getPos();
            int count = 0;
            for (UUID targetId : this.buffTargets) {
                PlayerEntity target = this.world.getPlayerByUuid(targetId);
                if (target == null || !target.isAlive() || target.getPos().distanceTo(center) > 6.0) continue;
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 400, 1, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 400, 1, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5904, 400, 0, false, false));
                this.startBattleOrbit(target);
                ++count;
            }
            Nomorespell.LOGGER.info("Battle Boost cast: applied buffs to {} players", (Object)count);
        }

        private void startBattleOrbit(PlayerEntity player) {
            List orbitList = BATTLE_ORBITS.computeIfAbsent(player.getUuid(), key -> new ArrayList());
            orbitList.clear();
            orbitList.add(new OrbitSymbolState(player, "\u2694", 16769126, 0.0f));
            orbitList.add(new OrbitSymbolState(player, "\u26e8", 11006928, 120.0f));
            orbitList.add(new OrbitSymbolState(player, "\u27a4", 9684477, 240.0f));
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.circleOne);
            SpellVisualsManager.removeDisplays(this.circleTwo);
            SpellVisualsManager.removeDisplays(this.circleThree);
            SpellVisualsManager.removeItemDisplays(this.totemDisplays);
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            SpellVisualsManager.removeDisplays(this.lightningDisplays);
            SpellVisualsManager.removeDisplays(this.impactDisplays);
            this.circleOne.clear();
            this.circleTwo.clear();
            this.circleThree.clear();
            this.totemDisplays.clear();
            this.rayDisplays.clear();
            this.lightningDisplays.clear();
            this.impactDisplays.clear();
            this.buffTargets.clear();
        }
    }

    private static class ChainLightningState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private LivingEntity initialTarget;
        private LivingEntity currentTarget;
        private LivingEntity nextTarget;
        private final List<LivingEntity> hitTargets = new ArrayList<LivingEntity>();
        private final List<DisplayEntity.class_8115> circleDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> columnDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> impactDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> arcDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> bounceImpactDisplays = new ArrayList<DisplayEntity.class_8115>();
        private boolean strikeApplied = false;
        private boolean buildupSoundPlayed = false;
        private boolean strikeSoundPlayed = false;
        private int bounceIndex = 0;
        private int bounceTick = 0;
        private boolean chainDone = false;

        private ChainLightningState(ServerWorld world, PlayerEntity player) {
            this.world = world;
            this.playerId = player.getUuid();
            this.currentTarget = this.initialTarget = SpellVisualsManager.findTargetInView(world, player, 15.0);
            Vec3d start = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(start.x, start.y + 3.0, start.z);
            world.spawnEntity((Entity)this.marker);
            if (this.initialTarget != null) {
                for (int i = 0; i < 16; ++i) {
                    this.circleDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_27171.getDefaultState(), this.initialTarget.getPos().add(0.0, 3.0, 0.0), 0.08f, 0.25f, 0.08f));
                }
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            if (this.initialTarget == null || !this.initialTarget.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d targetPos = this.initialTarget.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(targetPos.x, targetPos.y + 3.0, targetPos.z);
            }
            if (this.tick == 0 && !this.buildupSoundPlayed) {
                this.buildupSoundPlayed = true;
                world.playSound(null, targetPos.x, targetPos.y, targetPos.z, Nomorespell.CHAIN_LIGHTNING_SOUND, SoundCategory.field_15248, 0.8f, 0.4f);
                world.playSound(null, targetPos.x, targetPos.y, targetPos.z, Nomorespell.CHAIN_LIGHTNING_SOUND, SoundCategory.field_15248, 0.6f, 1.2f);
            }
            if (this.tick < 30) {
                float rotation = (float)this.tick * 45.0f;
                float tiltDeg = Math.min(45.0f, (float)this.tick / 29.0f * 45.0f);
                for (int i = 0; i < this.circleDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.circleDisplays.size()) + rotation);
                    Vec3d pos = targetPos.add(Math.cos(angle) * 1.5, 3.0, Math.sin(angle) * 1.5);
                    Quaternionf rot = new Quaternionf().rotationXYZ((float)Math.toRadians(tiltDeg), 0.0f, angle);
                    SpellVisualsManager.updateDisplayRotated(this.circleDisplays.get(i), pos, 0.08f, 0.25f, 0.08f, rot);
                }
                this.initialTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 4, 2, false, true));
                world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, targetPos.x, targetPos.y + 2.5, targetPos.z, 8, 0.6, 0.6, 0.6, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, targetPos.x, targetPos.y + 1.0, targetPos.z, 2, 0.3, 0.3, 0.3, 0.0);
                world.playSound(null, targetPos.x, targetPos.y, targetPos.z, SoundEvents.field_15057, SoundCategory.field_15248, 0.3f, 0.5f + (float)this.tick / 29.0f);
            } else if (this.tick < 42) {
                int i;
                int localTick = this.tick - 30;
                if (this.columnDisplays.isEmpty()) {
                    for (int i2 = 0; i2 < 20; ++i2) {
                        this.columnDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10455.getDefaultState(), targetPos.add(0.0, 3.0, 0.0), 0.06f, 0.15f, 0.06f));
                    }
                }
                float descent = Math.min(1.0f, (float)localTick / 8.0f);
                for (i = 0; i < this.columnDisplays.size(); ++i) {
                    float t = (float)i / 19.0f;
                    double y = targetPos.y + 3.0 - 3.0 * (double)descent * (double)t;
                    double offsetX = Math.sin((double)(this.tick + i) * 0.7) * 0.2;
                    double offsetZ = Math.cos((double)(this.tick + i) * 0.6) * 0.2;
                    Vec3d pos = new Vec3d(targetPos.x + offsetX, y, targetPos.z + offsetZ);
                    SpellVisualsManager.updateDisplay(this.columnDisplays.get(i), pos, 0.06f, 0.15f, 0.06f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, targetPos.x, targetPos.y + 1.5, targetPos.z, 30, 0.4, 1.2, 0.4, 0.0);
                if (localTick >= 8 && !this.strikeApplied) {
                    this.strikeApplied = true;
                    this.initialTarget.damage(world, world.getDamageSources().magic(), 50.0f);
                    this.initialTarget.setOnFireFor(2.0f);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, targetPos.x, targetPos.y + 0.5, targetPos.z, 25, 0.8, 0.4, 0.8, 0.0);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, targetPos.x, targetPos.y + 0.5, targetPos.z, 40, 1.0, 0.6, 1.0, 0.0);
                    world.playSound(null, targetPos.x, targetPos.y, targetPos.z, SoundEvents.field_14865, SoundCategory.field_15248, 0.9f, 1.0f);
                    if (!this.strikeSoundPlayed) {
                        this.strikeSoundPlayed = true;
                        world.playSound(null, targetPos.x, targetPos.y, targetPos.z, Nomorespell.CHAIN_LIGHTNING_SOUND, SoundCategory.field_15248, 1.0f, 1.0f);
                        world.playSound(null, targetPos.x, targetPos.y, targetPos.z, Nomorespell.CHAIN_LIGHTNING_SOUND, SoundCategory.field_15248, 0.7f, 0.3f);
                    }
                    for (i = 0; i < 20; ++i) {
                        this.impactDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10171.getDefaultState(), targetPos, 0.08f));
                    }
                    this.hitTargets.add(this.initialTarget);
                }
                if (localTick >= 8 && localTick < 12) {
                    float radius = 0.8f * ((float)(localTick - 8) / 4.0f);
                    for (int i3 = 0; i3 < this.impactDisplays.size(); ++i3) {
                        float angle = (float)Math.toRadians((float)i3 * (360.0f / (float)this.impactDisplays.size()));
                        Vec3d pos = targetPos.add(Math.cos(angle) * (double)radius, 0.2, Math.sin(angle) * (double)radius);
                        SpellVisualsManager.updateDisplay(this.impactDisplays.get(i3), pos, 0.08f);
                    }
                }
            } else if (this.tick < 84) {
                if (this.chainDone) {
                    if (this.tick % 10 == 0) {
                        for (LivingEntity target : this.hitTargets) {
                            world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, target.getX(), target.getBodyY(0.6), target.getZ(), 2, 0.4, 0.6, 0.4, 0.0);
                        }
                        world.playSound(null, targetPos.x, targetPos.y, targetPos.z, SoundEvents.field_19199, SoundCategory.field_15248, 0.3f, 1.2f);
                    }
                } else {
                    this.handleChainBounce();
                }
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void handleChainBounce() {
            int i;
            if (this.bounceIndex >= 4) {
                this.chainDone = true;
                return;
            }
            if (this.bounceTick == 0) {
                this.nextTarget = this.findNextChainTarget(this.currentTarget, this.hitTargets, 8.0);
                if (this.nextTarget == null) {
                    this.chainDone = true;
                    return;
                }
            }
            if (this.bounceTick < 2) {
                ++this.bounceTick;
                return;
            }
            if (this.bounceTick == 2 && this.arcDisplays.isEmpty()) {
                for (int i2 = 0; i2 < 12; ++i2) {
                    this.arcDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10171.getDefaultState(), this.currentTarget.getPos(), 0.04f));
                }
            }
            if (this.bounceTick >= 2 && this.bounceTick < 8) {
                float progress = (float)(this.bounceTick - 2) / 6.0f;
                Vec3d start = this.currentTarget.getPos().add(0.0, 1.0, 0.0);
                Vec3d end = this.nextTarget.getPos().add(0.0, 1.0, 0.0);
                for (i = 0; i < this.arcDisplays.size(); ++i) {
                    float t = (float)(i + 1) / 13.0f;
                    double curve = Math.sin(Math.PI * (double)t) * 1.2;
                    Vec3d point = start.lerp(end, (double)t).add(0.0, curve * (1.0 - Math.abs(0.5 - (double)t) * 2.0), 0.0);
                    Vec3d animated = start.lerp(point, (double)progress);
                    SpellVisualsManager.updateDisplay(this.arcDisplays.get(i), animated, 0.04f);
                    if (this.world.random.nextInt(2) != 0) continue;
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, animated.x, animated.y, animated.z, 1, 0.05, 0.05, 0.05, 0.0);
                }
                this.world.playSound(null, start.x, start.y, start.z, SoundEvents.field_14956, SoundCategory.field_15248, 0.4f, 0.9f + (float)this.bounceIndex * 0.1f);
            }
            if (this.bounceTick >= 8) {
                float[] damages = new float[]{40.0f, 30.0f, 20.0f, 10.0f};
                float damage = damages[Math.min(this.bounceIndex, damages.length - 1)];
                this.nextTarget.damage(this.world, this.world.getDamageSources().magic(), damage);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, this.nextTarget.getX(), this.nextTarget.getBodyY(0.6), this.nextTarget.getZ(), 15, 0.5, 0.4, 0.5, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, this.nextTarget.getX(), this.nextTarget.getBodyY(0.6), this.nextTarget.getZ(), 5, 0.3, 0.3, 0.3, 0.0);
                if (this.bounceImpactDisplays.isEmpty()) {
                    for (int i3 = 0; i3 < 12; ++i3) {
                        this.bounceImpactDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10542.getDefaultState(), this.nextTarget.getPos(), 0.06f));
                    }
                }
                float radius = 0.6f;
                for (i = 0; i < this.bounceImpactDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.bounceImpactDisplays.size()));
                    Vec3d pos = this.nextTarget.getPos().add(Math.cos(angle) * (double)radius, 0.2, Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay(this.bounceImpactDisplays.get(i), pos, 0.06f);
                }
                SpellVisualsManager.removeDisplays(this.bounceImpactDisplays);
                this.bounceImpactDisplays.clear();
                this.hitTargets.add(this.nextTarget);
                this.currentTarget = this.nextTarget;
                ++this.bounceIndex;
                this.bounceTick = 0;
            } else {
                ++this.bounceTick;
            }
        }

        private LivingEntity findNextChainTarget(LivingEntity source, List<LivingEntity> excluded, double radius) {
            if (source == null) {
                return null;
            }
            Vec3d base = source.getPos();
            List<LivingEntity> candidates = SpellVisualsManager.getLivingEntitiesAround(this.world, base, radius);
            LivingEntity best = null;
            double bestDist = Double.MAX_VALUE;
            for (LivingEntity target : candidates) {
                double dist;
                if (target == source || excluded.contains(target) || !((dist = target.getPos().squaredDistanceTo(base)) < bestDist)) continue;
                bestDist = dist;
                best = target;
            }
            return best;
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.circleDisplays);
            SpellVisualsManager.removeDisplays(this.columnDisplays);
            SpellVisualsManager.removeDisplays(this.impactDisplays);
            SpellVisualsManager.removeDisplays(this.arcDisplays);
            SpellVisualsManager.removeDisplays(this.bounceImpactDisplays);
            this.circleDisplays.clear();
            this.columnDisplays.clear();
            this.impactDisplays.clear();
            this.arcDisplays.clear();
            this.bounceImpactDisplays.clear();
            this.hitTargets.clear();
        }
    }

    private static class SacredCircleState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> rayDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> outerCircle = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> middleCircle = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> innerCircle = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> pillars = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> burstDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Float> burstAngles = new ArrayList<Float>();
        private final List<Float> burstHeights = new ArrayList<Float>();
        private final List<HealingWave> healingWaves = new ArrayList<HealingWave>();
        private boolean initialHealApplied = false;
        private int healWaveTick = 0;

        private SacredCircleState(ServerWorld world, PlayerEntity player) {
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 10.0, base.z);
            world.spawnEntity((Entity)this.marker);
            for (int i = 0; i < 30; ++i) {
                this.rayDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10049.getDefaultState(), base.add(0.0, 10.0, 0.0), 0.15f, 0.2f, 0.15f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 10.0, base.z);
            }
            if (this.tick == 0) {
                world.playSound(null, base.x, base.y, base.z, Nomorespell.SACRED_CIRCLE_SOUND, SoundCategory.field_15248, 1.0f, 1.0f);
            }
            if (this.tick < 40) {
                Vec3d pos;
                int i;
                float progress = (float)this.tick / 39.0f;
                float rotation = (float)this.tick * 10.0f;
                for (i = 0; i < this.rayDisplays.size(); ++i) {
                    float t = (float)i / 29.0f;
                    double y = base.y + 10.0 - (double)progress * 10.0 * (double)t;
                    pos = new Vec3d(base.x, y, base.z);
                    Quaternionf rot = new Quaternionf().rotationY((float)Math.toRadians(rotation));
                    SpellVisualsManager.updateDisplayRotated(this.rayDisplays.get(i), pos, 0.15f, 0.2f, 0.15f, rot);
                }
                for (i = 0; i < 10; ++i) {
                    float angle = (float)Math.toRadians((float)this.tick * 12.0f + (float)i * 36.0f);
                    double radius = 0.6 + (double)i * 0.04;
                    pos = base.add(Math.cos(angle) * radius, 0.6 + (double)i * 0.05, Math.sin(angle) * radius);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, pos.x, pos.y, pos.z, 1, 0.05, 0.05, 0.05, 0.0);
                }
                int outerTarget = MathHelper.clamp((int)MathHelper.ceil((float)(28.0f * ((float)(this.tick + 1) / 40.0f))), (int)1, (int)28);
                int middleTarget = MathHelper.clamp((int)MathHelper.ceil((float)(20.0f * ((float)(this.tick + 1) / 40.0f))), (int)1, (int)20);
                int innerTarget = MathHelper.clamp((int)MathHelper.ceil((float)(12.0f * ((float)(this.tick + 1) / 40.0f))), (int)1, (int)12);
                while (this.outerCircle.size() < outerTarget) {
                    this.outerCircle.add(SpellVisualsManager.createDisplay(world, Blocks.field_10205.getDefaultState(), base, 0.12f, 0.02f, 0.12f));
                }
                while (this.middleCircle.size() < middleTarget) {
                    this.middleCircle.add(SpellVisualsManager.createDisplay(world, Blocks.field_10542.getDefaultState(), base, 0.1f, 0.02f, 0.1f));
                }
                while (this.innerCircle.size() < innerTarget) {
                    this.innerCircle.add(SpellVisualsManager.createDisplay(world, Blocks.field_10171.getDefaultState(), base, 0.08f, 0.02f, 0.08f));
                }
                this.updateCircle(this.outerCircle, base, 7.0, (float)this.tick * 5.0f, 0.12f, 0.02f, 0.02);
                this.updateCircle(this.middleCircle, base, 4.5, (float)(-this.tick) * 6.0f, 0.1f, 0.02f, 0.02);
                this.updateCircle(this.innerCircle, base, 2.0, (float)this.tick * 8.0f, 0.08f, 0.02f, 0.02);
                while (this.pillars.size() < 20) {
                    this.pillars.add(SpellVisualsManager.createDisplay(world, Blocks.field_10174.getDefaultState(), base, 0.12f, 0.3f, 0.12f));
                }
                for (int i2 = 0; i2 < 4; ++i2) {
                    float angle = (float)Math.toRadians((float)i2 * 90.0f);
                    Vec3d pillarBase = base.add(Math.cos(angle) * 7.0, 0.2, Math.sin(angle) * 7.0);
                    for (int j = 0; j < 5; ++j) {
                        int index = i2 * 5 + j;
                        Vec3d pos2 = pillarBase.add(0.0, (double)j * 0.375, 0.0);
                        SpellVisualsManager.updateDisplay(this.pillars.get(index), pos2, 0.12f, 0.3f, 0.12f);
                    }
                }
                if (this.tick % 12 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_17265, SoundCategory.field_15248, 0.6f, 1.1f);
                }
            } else if (this.tick < 60) {
                if (!this.initialHealApplied) {
                    this.initialHealApplied = true;
                    this.applySacredCircleHeal(base, 16.0f, 15, true);
                    world.playSound(null, base.x, base.y, base.z, Nomorespell.SACRED_CIRCLE_SOUND, SoundCategory.field_15248, 1.0f, 1.8f);
                }
                if (this.burstDisplays.isEmpty()) {
                    for (int i = 0; i < 40; ++i) {
                        this.burstDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10087.getDefaultState(), base, 0.12f));
                        this.burstAngles.add(Float.valueOf(world.random.nextFloat() * 360.0f));
                        this.burstHeights.add(Float.valueOf(0.2f + world.random.nextFloat() * 1.2f));
                    }
                }
                float local = (float)(this.tick - 40) / 19.0f;
                float radius = 7.0f * MathHelper.clamp((float)local, (float)0.0f, (float)1.0f);
                for (int i = 0; i < this.burstDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians(this.burstAngles.get(i).floatValue());
                    float height = this.burstHeights.get(i).floatValue();
                    Vec3d pos = base.add(Math.cos(angle) * (double)radius, (double)height, Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay(this.burstDisplays.get(i), pos, 0.12f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, base.x, base.y + 1.2, base.z, 60, 1.4, 0.6, 1.4, 0.2);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, base.x, base.y + 0.8, base.z, 8, 0.4, 0.4, 0.4, 0.0);
            } else if (this.tick < 240) {
                float pulse = 0.85f + 0.15f * (float)Math.sin((double)this.tick * 0.15707963267948966);
                this.updateCircle(this.outerCircle, base, 7.0, (float)this.tick * 5.0f, 0.12f * pulse, 0.02f * pulse, 0.02);
                this.updateCircle(this.middleCircle, base, 4.5, (float)(-this.tick) * 7.0f, 0.1f * pulse, 0.02f * pulse, 0.02);
                this.updateCircle(this.innerCircle, base, 2.0, (float)this.tick * 10.0f, 0.08f * pulse, 0.02f * pulse, 0.02);
                if (this.healWaveTick <= 0) {
                    this.applySacredCircleHeal(base, 2.0f, 0, false);
                    this.healWaveTick = 20;
                    this.spawnHealingWaves(base);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14627, SoundCategory.field_15248, 0.4f, 1.2f);
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, base.x, base.y + 1.0, base.z, 8, 1.2, 0.8, 1.2, 0.0);
                }
                --this.healWaveTick;
                this.updateHealingWaves(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 0.6, base.z, 3, 1.0, 0.3, 1.0, 0.0);
                for (PlayerEntity target : SpellVisualsManager.getPlayersAround(world, base, 7.0)) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, target.getX(), target.getBodyY(0.8), target.getZ(), 1, 0.2, 0.3, 0.2, 0.0);
                }
            } else if (this.tick < 260) {
                float fade = 1.0f - (float)(this.tick - 240) / 20.0f;
                this.updateCircle(this.outerCircle, base, 7.0, (float)this.tick * 5.0f, 0.12f * fade, 0.02f * fade, 0.02);
                this.updateCircle(this.middleCircle, base, 4.5, (float)(-this.tick) * 7.0f, 0.1f * fade, 0.02f * fade, 0.02);
                this.updateCircle(this.innerCircle, base, 2.0, (float)this.tick * 10.0f, 0.08f * fade, 0.02f * fade, 0.02);
                float sink = (float)(this.tick - 240) / 20.0f * 1.5f;
                for (int i = 0; i < 4; ++i) {
                    float angle = (float)Math.toRadians((float)i * 90.0f);
                    Vec3d pillarBase = base.add(Math.cos(angle) * 7.0, 0.2 - (double)sink, Math.sin(angle) * 7.0);
                    for (int j = 0; j < 5; ++j) {
                        int index = i * 5 + j;
                        Vec3d pos = pillarBase.add(0.0, (double)j * 0.375, 0.0);
                        SpellVisualsManager.updateDisplay(this.pillars.get(index), pos, 0.12f * fade, 0.3f * fade, 0.12f * fade);
                    }
                }
                this.updateHealingWaves(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y + 1.0, base.z, 40, 2.0, 1.0, 2.0, 0.0);
                if (this.tick == 240) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_26980, SoundCategory.field_15248, 0.6f, 0.9f);
                }
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void applySacredCircleHeal(Vec3d base, float amount, int heartCount, boolean flash) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 7.0)) {
                float newHealth = Math.min(target.getMaxHealth(), target.getHealth() + amount);
                target.setHealth(newHealth);
                if (heartCount > 0) {
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, target.getX(), target.getBodyY(0.8), target.getZ(), heartCount, 0.6, 0.6, 0.6, 0.0);
                }
                if (!flash) continue;
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, target.getX(), target.getBodyY(0.8), target.getZ(), 1, 0.1, 0.1, 0.1, 0.0);
            }
        }

        private void spawnHealingWaves(Vec3d base) {
            for (int i = 0; i < 4; ++i) {
                float angle = (float)Math.toRadians((float)i * 90.0f);
                Vec3d start = base.add(Math.cos(angle) * 7.0, 0.4, Math.sin(angle) * 7.0);
                for (int j = 0; j < 8; ++j) {
                    DisplayEntity.class_8115 display = SpellVisualsManager.createDisplay(this.world, Blocks.field_10157.getDefaultState(), start, 0.08f);
                    this.healingWaves.add(new HealingWave(this, display, start, base.add(0.0, 0.4, 0.0)));
                }
            }
        }

        private void updateHealingWaves(Vec3d base) {
            if (this.healingWaves.isEmpty()) {
                return;
            }
            this.healingWaves.removeIf(wave -> wave.tick(base));
        }

        private void updateCircle(List<DisplayEntity.class_8115> displays, Vec3d base, double radius, float rotation, float scaleX, float scaleY, double yOffset) {
            for (int i = 0; i < displays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)displays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * radius, yOffset, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(displays.get(i), pos, scaleX, scaleY, scaleX);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            SpellVisualsManager.removeDisplays(this.outerCircle);
            SpellVisualsManager.removeDisplays(this.middleCircle);
            SpellVisualsManager.removeDisplays(this.innerCircle);
            SpellVisualsManager.removeDisplays(this.pillars);
            SpellVisualsManager.removeDisplays(this.burstDisplays);
            this.removeDisplaysFromWaves();
            this.rayDisplays.clear();
            this.outerCircle.clear();
            this.middleCircle.clear();
            this.innerCircle.clear();
            this.pillars.clear();
            this.burstDisplays.clear();
            this.burstAngles.clear();
            this.burstHeights.clear();
            this.healingWaves.clear();
        }

        private void removeDisplaysFromWaves() {
            for (HealingWave wave : this.healingWaves) {
                if (wave == null || wave.display == null) continue;
                wave.display.discard();
            }
        }

        private class HealingWave {
            private final DisplayEntity.class_8115 display;
            private final Vec3d start;
            private Vec3d end;
            private double progress = 0.0;
            private double distance = 0.0;

            private HealingWave(SacredCircleState sacredCircleState, DisplayEntity.class_8115 display, Vec3d start, Vec3d end) {
                this.display = display;
                this.start = start;
                this.end = end;
                this.distance = Math.max(0.1, start.distanceTo(end));
            }

            private boolean tick(Vec3d base) {
                if (this.display == null || !this.display.isAlive()) {
                    return true;
                }
                this.end = base.add(0.0, 0.4, 0.0);
                this.distance = Math.max(0.1, this.start.distanceTo(this.end));
                this.progress += 0.35;
                double t = MathHelper.clamp((double)(this.progress / this.distance), (double)0.0, (double)1.0);
                Vec3d pos = this.start.lerp(this.end, t);
                SpellVisualsManager.updateDisplay(this.display, pos, 0.08f);
                if (t >= 1.0) {
                    this.display.discard();
                    return true;
                }
                return false;
            }
        }
    }

    private static class TeamFortressState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> baseCircle = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> domeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> domePoints = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> pillars = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> energyRing = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> impactDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Float> impactAngles = new ArrayList<Float>();
        private final List<EnergyWave> energyWaves = new ArrayList<EnergyWave>();
        private final List<BuffVisual> buffVisuals = new ArrayList<BuffVisual>();
        private final List<Vec3d> collapseOffsets = new ArrayList<Vec3d>();
        private final List<Double> collapseVelocities = new ArrayList<Double>();
        private boolean buffsApplied = false;
        private boolean collapseInit = false;

        private TeamFortressState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 32; ++i) {
                this.baseCircle.add(SpellVisualsManager.createDisplay(world, Blocks.field_10540.getDefaultState(), base, 0.15f, 0.05f, 0.15f));
            }
            this.buildDomePoints();
            for (i = 0; i < 64; ++i) {
                this.pillars.add(SpellVisualsManager.createDisplay(world, Blocks.field_22423.getDefaultState(), base, 0.1f, 0.3f, 0.1f));
            }
        }

        private void buildDomePoints() {
            int[] counts = new int[]{24, 24, 24, 20, 16, 12};
            float[] heights = new float[]{0.5f, 1.5f, 2.5f, 3.5f, 4.5f, 6.0f};
            for (int ring = 0; ring < counts.length; ++ring) {
                int count = counts[ring];
                float height = heights[ring];
                double radius = Math.cos((double)(height / 6.0f) * 1.5707963267948966) * 8.0;
                for (int i = 0; i < count; ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)count));
                    this.domePoints.add(new Vec3d(Math.cos(angle) * radius, (double)height, Math.sin(angle) * radius));
                }
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y, base.z);
            }
            if (this.tick == 0) {
                world.playSound(null, base.x, base.y, base.z, Nomorespell.TEAM_FORTRESS_SOUND, SoundCategory.field_15248, 0.9f, 0.4f);
            }
            if (this.tick < 50) {
                this.updateCircle(this.baseCircle, base, 8.0, (float)this.tick * 20.0f, 0.15f, 0.05f);
                int targetCount = this.domeTargetCount(this.tick);
                while (this.domeDisplays.size() < targetCount && this.domeDisplays.size() < this.domePoints.size()) {
                    this.domeDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10399.getDefaultState(), base, 0.08f));
                }
                this.updateDome(base, (float)this.tick * 5.0f, 0.08f);
                this.updatePillars(base, MathHelper.clamp((float)((float)this.tick / 49.0f), (float)0.0f, (float)1.0f));
                this.spawnDomeParticles(base, 2);
                if (this.tick % 12 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14703, SoundCategory.field_15248, 0.5f, 0.5f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15163, SoundCategory.field_15248, 0.4f, 0.5f);
                }
            } else if (this.tick < 70) {
                float glowScale = 1.2f;
                this.updateCircle(this.baseCircle, base, 8.0, (float)this.tick * 20.0f, 0.15f * glowScale, 0.05f * glowScale);
                this.updateDome(base, (float)this.tick * 5.0f, 0.08f * glowScale);
                if (this.energyRing.isEmpty()) {
                    for (int i = 0; i < 32; ++i) {
                        this.energyRing.add(SpellVisualsManager.createDisplay(world, Blocks.field_10201.getDefaultState(), base, 0.06f));
                    }
                }
                float ringProgress = MathHelper.clamp((float)((float)(this.tick - 50) / 15.0f), (float)0.0f, (float)1.0f);
                float ringHeight = 6.0f - 6.0f * ringProgress;
                for (int i = 0; i < this.energyRing.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.energyRing.size()));
                    Vec3d pos = base.add(Math.cos(angle) * 8.0, (double)ringHeight, Math.sin(angle) * 8.0);
                    SpellVisualsManager.updateDisplay(this.energyRing.get(i), pos, 0.06f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + (double)ringHeight, base.z, 20, 2.0, 0.4, 2.0, 0.0);
                this.updateImpactBurst(base, this.tick);
            } else if (this.tick < 80) {
                if (!this.buffsApplied) {
                    this.buffsApplied = true;
                    this.applyFortressBuffs(base);
                }
                this.updateDome(base, (float)this.tick * 5.0f, 0.08f);
                this.updateBuffVisuals();
            } else if (this.tick < 330) {
                if (this.tick == 80) {
                    world.playSound(null, base.x, base.y, base.z, Nomorespell.TEAM_FORTRESS_SOUND, SoundCategory.field_15248, 0.7f, 0.6f);
                }
                float pulse = 0.85f + 0.15f * (float)Math.sin((double)this.tick * 0.10471975511965977);
                this.updateCircle(this.baseCircle, base, 8.0, (float)this.tick * 5.0f, 0.15f * pulse, 0.05f * pulse);
                this.updateDome(base, (float)this.tick * 5.0f, 0.08f * pulse);
                if (this.tick % 40 == 0) {
                    this.spawnEnergyWave(base);
                }
                this.updateEnergyWaves(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 2.0, base.z, 5, 2.5, 2.0, 2.5, 0.0);
                this.spawnBoundaryEffects(world, base, 8.0);
                this.spawnPillarFlames(base);
            } else if (this.tick < 350) {
                if (!this.collapseInit) {
                    this.collapseInit = true;
                    this.collapseOffsets.clear();
                    this.collapseVelocities.clear();
                    for (int i = 0; i < this.domeDisplays.size(); ++i) {
                        this.collapseOffsets.add(Vec3d.ZERO);
                        this.collapseVelocities.add(0.0);
                    }
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15081, SoundCategory.field_15248, 0.7f, 0.7f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14869, SoundCategory.field_15248, 0.5f, 0.8f);
                }
                float fade = 1.0f - (float)(this.tick - 330) / 20.0f;
                this.updateCircle(this.baseCircle, base, 8.0, (float)this.tick * 5.0f, 0.15f * fade, 0.05f * fade);
                this.updateCollapse(base, fade, this.tick - 330);
                world.spawnParticles((ParticleEffect)new DustParticleEffect(10181879, 1.0f), base.x, base.y + 2.0, base.z, 30, 3.0, 2.0, 3.0, 0.0);
                world.spawnParticles((ParticleEffect)new DustParticleEffect(4706815, 1.0f), base.x, base.y + 2.0, base.z, 30, 3.0, 2.0, 3.0, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, base.x, base.y + 1.0, base.z, 40, 2.0, 1.0, 2.0, 0.0);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private int domeTargetCount(int tick) {
            if (tick < 10) {
                return 24;
            }
            if (tick < 20) {
                return 48;
            }
            if (tick < 30) {
                return 72;
            }
            if (tick < 40) {
                return 92;
            }
            if (tick < 47) {
                return 108;
            }
            return this.domePoints.size();
        }

        private void updateCircle(List<DisplayEntity.class_8115> displays, Vec3d base, double radius, float rotation, float scaleX, float scaleY) {
            for (int i = 0; i < displays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)displays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * radius, 0.05, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(displays.get(i), pos, scaleX, scaleY, scaleX);
            }
        }

        private void updateDome(Vec3d base, float rotation, float scale) {
            for (int i = 0; i < this.domeDisplays.size(); ++i) {
                Vec3d offset = this.domePoints.get(i);
                float angle = (float)Math.toRadians(rotation);
                double rotatedX = offset.x * Math.cos(angle) - offset.z * Math.sin(angle);
                double rotatedZ = offset.x * Math.sin(angle) + offset.z * Math.cos(angle);
                Vec3d pos = base.add(rotatedX, offset.y, rotatedZ);
                SpellVisualsManager.updateDisplay(this.domeDisplays.get(i), pos, scale);
            }
        }

        private void updatePillars(Vec3d base, float progress) {
            float angle;
            int i;
            for (i = 0; i < 8; ++i) {
                angle = (float)Math.toRadians((float)i * 45.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 8.0, 0.2, Math.sin(angle) * 8.0);
                for (int j = 0; j < 8; ++j) {
                    int index = i * 8 + j;
                    Vec3d pos = pillarBase.add(0.0, (double)j * 0.75 * (double)progress, 0.0);
                    SpellVisualsManager.updateDisplay(this.pillars.get(index), pos, 0.1f, 0.3f, 0.1f);
                }
            }
            for (i = 0; i < 3; ++i) {
                angle = (float)Math.toRadians(this.world.random.nextFloat() * 360.0f);
                double radius = 8.0;
                double height = this.world.random.nextDouble() * 6.0;
                Vec3d pos = base.add(Math.cos(angle) * radius, 0.2 + height, Math.sin(angle) * radius);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, pos.x, pos.y, pos.z, 1, 0.1, 0.2, 0.1, 0.0);
            }
        }

        private void spawnDomeParticles(Vec3d base, int count) {
            for (int i = 0; i < count; ++i) {
                double angle = this.world.random.nextDouble() * Math.PI * 2.0;
                double radius = 4.0 + this.world.random.nextDouble() * 4.0;
                double height = 0.5 + this.world.random.nextDouble() * 5.0;
                Vec3d pos = base.add(Math.cos(angle) * radius, height, Math.sin(angle) * radius);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, pos.x, pos.y, pos.z, 1, 0.05, 0.05, 0.05, 0.0);
            }
        }

        private void updateImpactBurst(Vec3d base, int tick) {
            if (tick == 65 && this.impactDisplays.isEmpty()) {
                for (int i = 0; i < 48; ++i) {
                    this.impactDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10308.getDefaultState(), base, 0.1f));
                    this.impactAngles.add(Float.valueOf(this.world.random.nextFloat() * 360.0f));
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, base.x, base.y, base.z, 50, 2.0, 0.5, 2.0, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y, base.z, 40, 2.0, 0.5, 2.0, 0.0);
                this.world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_15152, SoundCategory.field_15248, 0.9f, 1.0f);
                this.world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14917, SoundCategory.field_15248, 0.7f, 1.1f);
            }
            if (!this.impactDisplays.isEmpty()) {
                float progress = MathHelper.clamp((float)((float)(tick - 65) / 4.0f), (float)0.0f, (float)1.0f);
                float radius = progress * 3.0f;
                for (int i = 0; i < this.impactDisplays.size(); ++i) {
                    float angle = (float)Math.toRadians(this.impactAngles.get(i).floatValue());
                    Vec3d pos = base.add(Math.cos(angle) * (double)radius, 0.2, Math.sin(angle) * (double)radius);
                    SpellVisualsManager.updateDisplay(this.impactDisplays.get(i), pos, 0.1f);
                }
                if (tick >= 69) {
                    SpellVisualsManager.removeDisplays(this.impactDisplays);
                    this.impactDisplays.clear();
                    this.impactAngles.clear();
                }
            }
        }

        private void spawnEnergyWave(Vec3d base) {
            ArrayList<DisplayEntity.class_8115> waveDisplays = new ArrayList<DisplayEntity.class_8115>();
            for (int i = 0; i < 20; ++i) {
                waveDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10294.getDefaultState(), base, 0.05f));
            }
            this.energyWaves.add(new EnergyWave(this, waveDisplays));
        }

        private void updateEnergyWaves(Vec3d base) {
            if (this.energyWaves.isEmpty()) {
                return;
            }
            this.energyWaves.removeIf(wave -> wave.tick(base));
        }

        private void updateBuffVisuals() {
            if (this.buffVisuals.isEmpty()) {
                return;
            }
            this.buffVisuals.removeIf(BuffVisual::tick);
        }

        private void applyFortressBuffs(Vec3d base) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 8.0)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 300, 3, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5918, 300, 0, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 300, 2, false, false));
                this.startFortressRunes(target);
                this.buffVisuals.add(new BuffVisual(target));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, target.getX(), target.getBodyY(0.6), target.getZ(), 25, 0.6, 0.8, 0.6, 0.0);
                this.world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.field_14709, SoundCategory.field_15248, 0.6f, 1.2f);
                this.world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.field_14891, SoundCategory.field_15248, 0.5f, 1.1f);
            }
        }

        private void startFortressRunes(PlayerEntity player) {
            List runes = FORTRESS_RUNES.computeIfAbsent(player.getUuid(), key -> new ArrayList());
            runes.clear();
            runes.add(new FortressRuneState(player, "\ud83d\udee1", 9684477, 0.0f));
            runes.add(new FortressRuneState(player, "\u2764", 16281969, 90.0f));
            runes.add(new FortressRuneState(player, "\ud83d\udd25", 16096779, 180.0f));
            runes.add(new FortressRuneState(player, "\u26e8", 11006928, 270.0f));
        }

        private void spawnBoundaryEffects(ServerWorld world, Vec3d base, double radius) {
            for (PlayerEntity player : world.getPlayers()) {
                double dist = player.getPos().distanceTo(base);
                if (!(dist >= radius) || !(dist <= radius + 0.6)) continue;
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11213, player.getX(), player.getBodyY(0.5), player.getZ(), 10, 0.2, 0.3, 0.2, 0.0);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_15008, SoundCategory.field_15248, 0.4f, 1.1f);
            }
        }

        private void spawnPillarFlames(Vec3d base) {
            for (int i = 0; i < 3; ++i) {
                float angle = (float)Math.toRadians(this.world.random.nextFloat() * 360.0f);
                double radius = 8.0;
                double height = this.world.random.nextDouble() * 6.0;
                Vec3d pos = base.add(Math.cos(angle) * radius, 0.2 + height, Math.sin(angle) * radius);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, pos.x, pos.y, pos.z, 1, 0.1, 0.2, 0.1, 0.0);
            }
        }

        private void updateCollapse(Vec3d base, float fade, int collapseTick) {
            int i;
            int remaining = Math.max(0, this.domeDisplays.size() - collapseTick * 6);
            for (i = this.domeDisplays.size() - 1; i >= remaining; --i) {
                this.domeDisplays.get(i).discard();
                this.domeDisplays.remove(i);
                this.domePoints.remove(i);
                this.collapseOffsets.remove(i);
                this.collapseVelocities.remove(i);
            }
            for (i = 0; i < this.domeDisplays.size(); ++i) {
                double velocity = this.collapseVelocities.get(i) - 0.2;
                this.collapseVelocities.set(i, velocity);
                Vec3d offset = this.collapseOffsets.get(i).add(0.0, velocity, 0.0);
                this.collapseOffsets.set(i, offset);
                Vec3d baseOffset = this.domePoints.get(i).add(offset);
                Vec3d pos = base.add(baseOffset.x, baseOffset.y, baseOffset.z);
                SpellVisualsManager.updateDisplay(this.domeDisplays.get(i), pos, 0.08f * fade);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.baseCircle);
            SpellVisualsManager.removeDisplays(this.domeDisplays);
            SpellVisualsManager.removeDisplays(this.pillars);
            SpellVisualsManager.removeDisplays(this.energyRing);
            SpellVisualsManager.removeDisplays(this.impactDisplays);
            this.baseCircle.clear();
            this.domeDisplays.clear();
            this.pillars.clear();
            this.energyRing.clear();
            this.impactDisplays.clear();
            this.impactAngles.clear();
            this.domePoints.clear();
            this.collapseOffsets.clear();
            this.collapseVelocities.clear();
            this.energyWaves.forEach(EnergyWave::cleanup);
            this.energyWaves.clear();
            this.buffVisuals.forEach(BuffVisual::cleanup);
            this.buffVisuals.clear();
        }

        private class EnergyWave {
            private final List<DisplayEntity.class_8115> displays;
            private int age = 0;

            private EnergyWave(TeamFortressState teamFortressState, List<DisplayEntity.class_8115> displays) {
                this.displays = displays;
            }

            private boolean tick(Vec3d base) {
                double height = 6.0 - (double)this.age * 0.4;
                for (int i = 0; i < this.displays.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.displays.size()));
                    Vec3d pos = base.add(Math.cos(angle) * 8.0, height, Math.sin(angle) * 8.0);
                    SpellVisualsManager.updateDisplay(this.displays.get(i), pos, 0.05f);
                }
                ++this.age;
                if (height <= 0.0) {
                    this.cleanup();
                    return true;
                }
                return false;
            }

            private void cleanup() {
                SpellVisualsManager.removeDisplays(this.displays);
            }
        }

        private class BuffVisual {
            private final UUID playerId;
            private final List<DisplayEntity.class_8115> columns = new ArrayList<DisplayEntity.class_8115>();
            private final List<DisplayEntity.class_8115> armor = new ArrayList<DisplayEntity.class_8115>();
            private int age = 0;

            private BuffVisual(PlayerEntity target) {
                int i;
                this.playerId = target.getUuid();
                for (i = 0; i < 10; ++i) {
                    this.columns.add(SpellVisualsManager.createDisplay(TeamFortressState.this.world, Blocks.field_10271.getDefaultState(), target.getPos(), 0.08f, 0.25f, 0.08f));
                }
                for (i = 0; i < 8; ++i) {
                    this.armor.add(SpellVisualsManager.createDisplay(TeamFortressState.this.world, Blocks.field_10201.getDefaultState(), target.getPos(), 0.06f));
                }
            }

            private boolean tick() {
                Vec3d pos;
                PlayerEntity target = TeamFortressState.this.world.getPlayerByUuid(this.playerId);
                if (target == null || !target.isAlive()) {
                    this.cleanup();
                    return true;
                }
                Vec3d base = target.getPos();
                for (int i = 0; i < this.columns.size(); ++i) {
                    double y = base.y + 6.0 - (double)i * 0.6 - (double)this.age * 0.3;
                    pos = new Vec3d(base.x, y, base.z);
                    SpellVisualsManager.updateDisplay(this.columns.get(i), pos, 0.08f, 0.25f, 0.08f);
                }
                float rotation = (float)this.age * 90.0f;
                for (int i = 0; i < this.armor.size(); ++i) {
                    float angle = (float)Math.toRadians(rotation + (float)i * 45.0f);
                    pos = base.add(Math.cos(angle) * 0.7, 1.0, Math.sin(angle) * 0.7);
                    SpellVisualsManager.updateDisplay(this.armor.get(i), pos, 0.06f);
                }
                ++this.age;
                if (this.age >= 10) {
                    this.cleanup();
                    return true;
                }
                return false;
            }

            private void cleanup() {
                SpellVisualsManager.removeDisplays(this.columns);
                SpellVisualsManager.removeDisplays(this.armor);
                this.columns.clear();
                this.armor.clear();
            }
        }
    }

    private static class MeteorStrikeState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private Vec3d impactPoint;
        private Vec3d meteorPos;
        private Vec3d meteorVelocity = new Vec3d(0.0, -0.5, 0.0);
        private float meteorRotation = 0.0f;
        private float meteorRotationSpeed = 45.0f;
        private final List<DisplayEntity.class_8115> crossDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> rayDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> meteorCore = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> meteorLayerOne = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> meteorLayerTwo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> heatAura = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> trailDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> craterDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> fragmentDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> fragmentVelocities = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> shockwaveOne = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> shockwaveTwo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> shockwaveThree = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> geyserDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> residualFragments = new ArrayList<DisplayEntity.class_8115>();
        private boolean impactApplied = false;
        private boolean shockwaveApplied = false;
        private boolean chargeSoundPlayed = false;
        private boolean descentSoundPlayed = false;
        private boolean impactSoundPlayed = false;

        private MeteorStrikeState(ServerWorld world, PlayerEntity player) {
            BlockState state;
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            this.impactPoint = SpellVisualsManager.getLookTargetPoint(world, player, 40.0);
            this.meteorPos = this.impactPoint.add(0.0, 50.0, 0.0);
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(this.impactPoint.x, this.impactPoint.y, this.impactPoint.z);
            world.spawnEntity((Entity)this.marker);
            this.meteorCore.add(SpellVisualsManager.createDisplay(world, Blocks.field_10092.getDefaultState(), this.meteorPos, 1.2f));
            for (i = 0; i < 20; ++i) {
                this.meteorLayerOne.add(SpellVisualsManager.createDisplay(world, Blocks.field_10515.getDefaultState(), this.meteorPos, 0.3f));
            }
            for (i = 0; i < 40; ++i) {
                state = i % 3 == 0 ? Blocks.field_10164.getDefaultState() : Blocks.field_10092.getDefaultState();
                this.meteorLayerTwo.add(SpellVisualsManager.createDisplay(world, state, this.meteorPos, 0.15f));
            }
            for (i = 0; i < 60; ++i) {
                this.heatAura.add(SpellVisualsManager.createDisplay(world, Blocks.field_10555.getDefaultState(), this.meteorPos, 0.1f));
            }
            for (i = 0; i < 80; ++i) {
                state = i % 2 == 0 ? Blocks.field_10036.getDefaultState() : Blocks.field_10555.getDefaultState();
                this.trailDisplays.add(SpellVisualsManager.createDisplay(world, state, this.meteorPos, 0.12f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            if (this.tick < 60) {
                this.handleTargetLocking(caster);
                this.updateMeteorFormation();
            } else if (this.tick < 100) {
                this.handleMeteorDescent(this.tick - 60);
            } else if (this.tick < 140) {
                this.handleImpactPhase(this.tick - 100);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void handleTargetLocking(PlayerEntity caster) {
            List<LivingEntity> targets = SpellVisualsManager.getLivingEntitiesInCone(this.world, caster, 30.0, 45.0);
            SpellVisualsManager.removeDisplays(this.crossDisplays);
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            this.crossDisplays.clear();
            this.rayDisplays.clear();
            this.meteorRotation += this.meteorRotationSpeed;
            float rotation = (float)this.tick * 30.0f;
            for (LivingEntity target : targets) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 10, 3, false, true));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 10, 2, false, true));
                Vec3d head = target.getPos().add(0.0, (double)target.getHeight() + 0.6, 0.0);
                for (int i = 0; i < 4; ++i) {
                    float angle = (float)Math.toRadians(rotation + (float)i * 90.0f);
                    Vec3d offset = new Vec3d(Math.cos(angle) * 0.4, 0.0, Math.sin(angle) * 0.4);
                    this.crossDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10002.getDefaultState(), head.add(offset), 0.06f, 0.02f, 0.06f));
                }
                Vec3d start = caster.getPos().add(0.0, 1.4, 0.0);
                Vec3d delta = head.subtract(start);
                for (int i = 0; i < 4; ++i) {
                    float t = (float)(i + 1) / 5.0f;
                    Vec3d pos = start.add(delta.multiply((double)t));
                    this.rayDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10455.getDefaultState(), pos, 0.06f, 0.3f, 0.06f));
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, head.x, head.y, head.z, 2, 0.2, 0.2, 0.2, 0.0);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, this.impactPoint.x, this.impactPoint.y + 8.0, this.impactPoint.z, 10, 8.0, 6.0, 8.0, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, 15, 1.5, 1.5, 1.5, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 10, 15.0, 0.4, 15.0, 0.0);
            if (!this.chargeSoundPlayed) {
                this.chargeSoundPlayed = true;
                this.world.playSound(null, caster.getX(), caster.getY(), caster.getZ(), Nomorespell.METEOR_STRIKE_SOUND, SoundCategory.field_15248, 1.0f, 0.2f);
            }
        }

        private void updateMeteorFormation() {
            Vec3d center = this.meteorPos;
            SpellVisualsManager.updateDisplay(this.meteorCore.get(0), center, 1.2f);
            this.scatterLayer(this.meteorLayerOne, center, 2.0, 0.3f, this.meteorRotation * 0.6f, 1.0f, 0.6f, 0.8f);
            this.scatterLayer(this.meteorLayerTwo, center, 2.5, 0.15f, this.meteorRotation, 0.6f, 1.0f, 1.3f);
            this.scatterLayer(this.heatAura, center, 3.5, 0.1f, this.meteorRotation * 0.3f, 0.4f, 0.8f, 0.5f);
        }

        private void handleMeteorDescent(int localTick) {
            double speed = MathHelper.lerp((double)((double)localTick / 40.0), (double)-0.5, (double)-2.5);
            this.meteorVelocity = new Vec3d(0.0, speed, 0.0);
            this.meteorPos = this.meteorPos.add(this.meteorVelocity);
            this.meteorRotationSpeed = MathHelper.lerp((float)((float)localTick / 40.0f), (float)45.0f, (float)90.0f);
            this.meteorRotation += this.meteorRotationSpeed;
            this.updateMeteorFormation();
            this.updateTrail();
            if (localTick >= 30) {
                float ringProgress = (float)(localTick - 30) / 10.0f;
                this.spawnShockwaveRing(this.impactPoint, (double)ringProgress * 12.0, Blocks.field_10210.getDefaultState(), 24, 0.1f);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 5, 1.0, 0.1, 1.0, 0.0);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, 30, 1.2, 1.2, 1.2, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11237, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, 20, 1.5, 1.5, 1.5, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11227, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, 10, 0.4, 0.4, 0.4, 0.0);
            if (!this.descentSoundPlayed) {
                this.descentSoundPlayed = true;
                this.world.playSound(null, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, Nomorespell.METEOR_STRIKE_SOUND, SoundCategory.field_15248, 1.0f, 2.0f);
            }
            if (localTick % 10 == 0) {
                this.world.playSound(null, this.meteorPos.x, this.meteorPos.y, this.meteorPos.z, SoundEvents.field_14869, SoundCategory.field_15248, 0.6f, 1.2f - (float)localTick * 0.01f);
            }
        }

        private void handleImpactPhase(int localTick) {
            if (localTick == 0) {
                int i;
                this.meteorPos = this.impactPoint;
                this.meteorRotation += 90.0f;
                this.updateMeteorFormation();
                for (i = 0; i < 32; ++i) {
                    this.craterDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_23869.getDefaultState(), this.impactPoint, 0.2f, 0.05f, 0.2f));
                }
                for (i = 0; i < 60; ++i) {
                    this.fragmentDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10092.getDefaultState(), this.impactPoint, 0.15f));
                    double angle = this.world.random.nextDouble() * Math.PI * 2.0;
                    double speed = 0.8 + this.world.random.nextDouble() * 0.7;
                    this.fragmentVelocities.add(new Vec3d(Math.cos(angle) * speed, 0.4 + this.world.random.nextDouble() * 0.6, Math.sin(angle) * speed));
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11221, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, 3, 0.2, 0.2, 0.2, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, 100, 2.5, 1.0, 2.5, 0.0);
                this.world.playSound(null, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, (RegistryEntry)SoundEvents.field_15152, SoundCategory.field_15248, 1.4f, 0.7f);
                this.world.playSound(null, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, SoundEvents.field_14833, SoundCategory.field_15248, 1.2f, 0.8f);
                if (!this.impactSoundPlayed) {
                    this.impactSoundPlayed = true;
                    this.world.playSound(null, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, Nomorespell.METEOR_STRIKE_SOUND, SoundCategory.field_15248, 1.0f, 0.3f);
                }
            }
            if (localTick < 5) {
                this.updateCrater(localTick);
                this.updateFragments();
                if (!this.impactApplied) {
                    this.impactApplied = true;
                    this.applyMeteorDamage(this.impactPoint, 2.0, 120.0f);
                }
            } else if (localTick < 15) {
                this.updateFragments();
                float progress = (float)(localTick - 5) / 10.0f;
                this.updateShockwaves(progress);
                if (!this.shockwaveApplied) {
                    this.shockwaveApplied = true;
                    this.applyMeteorDamage(this.impactPoint, 6.0, 60.0f);
                    SpellVisualsManager.applyRadialKnockback(this.world, this.impactPoint, 6.0, 2.0);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 50, 3.0, 0.2, 3.0, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 80, 3.0, 0.2, 3.0, 0.0);
                if (localTick % 3 == 0) {
                    this.world.playSound(null, this.impactPoint.x, this.impactPoint.y, this.impactPoint.z, SoundEvents.field_14865, SoundCategory.field_15248, 0.9f, 0.9f);
                }
            } else if (localTick < 30) {
                this.spawnGeysers(localTick);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11223, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 30, 3.0, 0.2, 3.0, 0.0);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 30, 2.0, 0.2, 2.0, 0.0);
            } else {
                float fade = 1.0f - (float)(localTick - 30) / 10.0f;
                this.updateCraterColors(fade);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 40, 2.0, 0.4, 2.0, 0.0);
                if (this.residualFragments.isEmpty()) {
                    for (int i = 0; i < 12; ++i) {
                        this.residualFragments.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_23869.getDefaultState(), this.impactPoint, 0.1f));
                    }
                }
                for (int i = 0; i < this.residualFragments.size(); ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.residualFragments.size()));
                    Vec3d pos = this.impactPoint.add(Math.cos(angle) * 1.5, 0.1, Math.sin(angle) * 1.5);
                    SpellVisualsManager.updateDisplay(this.residualFragments.get(i), pos, 0.1f * fade);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, this.impactPoint.x, this.impactPoint.y + 0.2, this.impactPoint.z, 2, 1.0, 0.1, 1.0, 0.0);
            }
        }

        private void updateTrail() {
            Vec3d direction = this.meteorVelocity.normalize();
            for (int i = 0; i < this.trailDisplays.size(); ++i) {
                float t = (float)i / (float)this.trailDisplays.size();
                Vec3d pos = this.meteorPos.subtract(direction.multiply((double)t * 20.0));
                SpellVisualsManager.updateDisplay(this.trailDisplays.get(i), pos, 0.12f);
            }
        }

        private void updateCrater(int localTick) {
            float radius = 2.0f + (float)localTick * 0.2f;
            for (int i = 0; i < this.craterDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.craterDisplays.size()));
                Vec3d pos = this.impactPoint.add(Math.cos(angle) * (double)radius, -0.5, Math.sin(angle) * (double)radius);
                SpellVisualsManager.updateDisplay(this.craterDisplays.get(i), pos, 0.2f, 0.05f, 0.2f);
            }
        }

        private void updateCraterColors(float fade) {
            BlockState state = fade > 0.6f ? Blocks.field_10092.getDefaultState() : (fade > 0.3f ? Blocks.field_22091.getDefaultState() : Blocks.field_23869.getDefaultState());
            for (DisplayEntity.class_8115 display : this.craterDisplays) {
                display.setBlockState(state);
            }
        }

        private void updateFragments() {
            for (int i = 0; i < this.fragmentDisplays.size(); ++i) {
                DisplayEntity.class_8115 display = this.fragmentDisplays.get(i);
                Vec3d velocity = this.fragmentVelocities.get(i);
                Vec3d pos = display.getPos().add(velocity);
                this.fragmentVelocities.set(i, velocity.multiply(0.95).add(0.0, -0.05, 0.0));
                SpellVisualsManager.updateDisplay(display, pos, 0.15f);
            }
        }

        private void updateShockwaves(float progress) {
            this.spawnShockwaveRing(this.impactPoint, (double)progress * 6.0, Blocks.field_10058.getDefaultState(), 36, 0.12f, this.shockwaveOne);
            this.spawnShockwaveRing(this.impactPoint, (double)progress * 4.5, Blocks.field_10184.getDefaultState(), 28, 0.1f, this.shockwaveTwo);
            this.spawnShockwaveRing(this.impactPoint, (double)progress * 3.0, Blocks.field_10490.getDefaultState(), 20, 0.08f, this.shockwaveThree);
        }

        private void spawnGeysers(int localTick) {
            int i;
            if (this.geyserDisplays.isEmpty()) {
                for (i = 0; i < 120; ++i) {
                    this.geyserDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10164.getDefaultState(), this.impactPoint, 0.12f, 0.4f, 0.12f));
                }
            }
            for (i = 0; i < 8; ++i) {
                float angle = (float)Math.toRadians((float)i * 45.0f);
                Vec3d base = this.impactPoint.add(Math.cos(angle) * 4.0, 0.0, Math.sin(angle) * 4.0);
                for (int j = 0; j < 15; ++j) {
                    int index = i * 15 + j;
                    float height = localTick < 22 ? (float)j * 0.35f : 5.0f - (float)(localTick - 22) * 0.5f;
                    Vec3d pos = base.add(0.0, (double)Math.max(0.0f, height), 0.0);
                    SpellVisualsManager.updateDisplay(this.geyserDisplays.get(index), pos, 0.12f, 0.4f, 0.12f);
                }
            }
        }

        private void scatterLayer(List<DisplayEntity.class_8115> list, Vec3d center, double radius, float scale, float rotation, float yAmp, float yFreq, float yPhase) {
            for (int i = 0; i < list.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)list.size()) + rotation);
                double y = Math.sin(Math.toRadians(rotation * yFreq + (float)i * 15.0f)) * (double)yAmp + Math.cos(Math.toRadians(rotation * 0.5f + (float)i * yPhase)) * 0.2;
                Vec3d pos = center.add(Math.cos(angle) * radius, y, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(list.get(i), pos, scale);
            }
        }

        private void applyMeteorDamage(Vec3d center, double radius, float damage) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, center, radius)) {
                entity.damage(this.world, this.world.getDamageSources().magic(), damage);
            }
        }

        private void spawnShockwaveRing(Vec3d center, double radius, BlockState state, int count, float scale) {
            ArrayList<DisplayEntity.class_8115> temp = new ArrayList<DisplayEntity.class_8115>();
            this.spawnShockwaveRing(center, radius, state, count, scale, temp);
            SpellVisualsManager.removeDisplays(temp);
        }

        private void spawnShockwaveRing(Vec3d center, double radius, BlockState state, int count, float scale, List<DisplayEntity.class_8115> list) {
            while (list.size() < count) {
                list.add(SpellVisualsManager.createDisplay(this.world, state, center, scale, 0.02f, scale));
            }
            for (int i = 0; i < count; ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)count));
                Vec3d pos = center.add(Math.cos(angle) * radius, 0.1, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(list.get(i), pos, scale, 0.02f, scale);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.crossDisplays);
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            SpellVisualsManager.removeDisplays(this.meteorCore);
            SpellVisualsManager.removeDisplays(this.meteorLayerOne);
            SpellVisualsManager.removeDisplays(this.meteorLayerTwo);
            SpellVisualsManager.removeDisplays(this.heatAura);
            SpellVisualsManager.removeDisplays(this.trailDisplays);
            SpellVisualsManager.removeDisplays(this.craterDisplays);
            SpellVisualsManager.removeDisplays(this.fragmentDisplays);
            SpellVisualsManager.removeDisplays(this.shockwaveOne);
            SpellVisualsManager.removeDisplays(this.shockwaveTwo);
            SpellVisualsManager.removeDisplays(this.shockwaveThree);
            SpellVisualsManager.removeDisplays(this.geyserDisplays);
            SpellVisualsManager.removeDisplays(this.residualFragments);
            SpellVisualsManager.removeDisplays(this.crossDisplays);
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            this.crossDisplays.clear();
            this.rayDisplays.clear();
            this.meteorCore.clear();
            this.meteorLayerOne.clear();
            this.meteorLayerTwo.clear();
            this.heatAura.clear();
            this.trailDisplays.clear();
            this.craterDisplays.clear();
            this.fragmentDisplays.clear();
            this.fragmentVelocities.clear();
            this.shockwaveOne.clear();
            this.shockwaveTwo.clear();
            this.shockwaveThree.clear();
            this.geyserDisplays.clear();
            this.residualFragments.clear();
        }
    }

    private static class DivineShieldState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> domeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> domePoints = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> shellDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> shellPoints = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> auraDisplays = new ArrayList<DisplayEntity.class_8115>();
        private boolean effectsApplied = false;

        private DivineShieldState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 8.0, base.z);
            world.spawnEntity((Entity)this.marker);
            this.buildDomePoints();
            this.buildShellPoints();
            for (i = 0; i < this.domePoints.size(); ++i) {
                this.domeDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10049.getDefaultState(), base, 0.12f));
            }
            for (i = 0; i < this.shellPoints.size(); ++i) {
                this.shellDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10205.getDefaultState(), base, 0.12f));
            }
            for (i = 0; i < 8; ++i) {
                this.auraDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10171.getDefaultState(), base, 0.06f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 8.0, base.z);
            }
            if (this.tick < 10) {
                float progress = (float)this.tick / 10.0f;
                this.updateDome(base.add(0.0, 8.0 * (1.0 - (double)progress), 0.0), 3.0f, 0.12f);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 4.0, base.z, 20, 1.0, 1.0, 1.0, 0.0);
                world.playSound(null, base.x, base.y, base.z, SoundEvents.field_26945, SoundCategory.field_15248, 0.6f, 1.3f);
            } else if (this.tick < 18) {
                float progress = (float)(this.tick - 10) / 8.0f;
                float radius = 3.0f * (1.0f - progress);
                this.updateDome(base, radius, 0.14f + progress * 0.06f);
                if (this.tick == 12 && !this.effectsApplied) {
                    this.effectsApplied = true;
                    float newHealth = Math.min(player.getMaxHealth(), player.getHealth() + 20.0f);
                    player.setHealth(newHealth);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 200, 3, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 200, 3, false, false));
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, base.x, base.y + 1.0, base.z, 50, 1.2, 1.2, 1.2, 0.0);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14931, SoundCategory.field_15248, 0.9f, 1.2f);
                }
            } else if (this.tick < 30) {
                this.updateShell(base, 1.2f, 0.12f);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y + 1.0, base.z, 30, 1.0, 0.6, 1.0, 0.0);
                if (this.tick == 18) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14703, SoundCategory.field_15248, 0.8f, 1.3f);
                }
            } else if (this.tick < 50) {
                float pulse = 1.0f + MathHelper.sin((float)((float)this.tick * 0.3f)) * 0.2f;
                this.updateShell(base, 1.2f * pulse, 0.12f * pulse);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 1.0, base.z, 5, 0.6, 0.6, 0.6, 0.0);
            } else if (this.tick < 70) {
                float fade = 1.0f - (float)(this.tick - 50) / 20.0f;
                this.updateShell(base, 1.2f, 0.12f * fade);
                if (this.tick == 50) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.0, base.z, 20, 1.0, 0.5, 1.0, 0.0);
                }
            } else if (this.tick < 250) {
                this.updateAura(base, this.tick);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.0, base.z, 1, 0.2, 0.2, 0.2, 0.0);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void buildDomePoints() {
            int[] counts = new int[]{12, 12, 12};
            float[] heights = new float[]{0.5f, 1.5f, 2.5f};
            for (int ring = 0; ring < counts.length; ++ring) {
                int count = counts[ring];
                float height = heights[ring];
                double radius = Math.cos((double)(height / 3.0f) * 1.5707963267948966) * 3.0;
                for (int i = 0; i < count; ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)count));
                    this.domePoints.add(new Vec3d(Math.cos(angle) * radius, (double)height, Math.sin(angle) * radius));
                }
            }
        }

        private void buildShellPoints() {
            int count = 24;
            for (int i = 0; i < count; ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)count));
                this.shellPoints.add(new Vec3d(Math.cos(angle) * 1.2, 0.6 + Math.sin(angle * 2.0f) * 0.3, Math.sin(angle) * 1.2));
            }
        }

        private void updateDome(Vec3d base, float radius, float scale) {
            for (int i = 0; i < this.domeDisplays.size(); ++i) {
                Vec3d offset = this.domePoints.get(i);
                Vec3d pos = base.add(offset.x * (double)(radius / 3.0f), offset.y * (double)(radius / 3.0f), offset.z * (double)(radius / 3.0f));
                SpellVisualsManager.updateDisplay(this.domeDisplays.get(i), pos, scale);
            }
        }

        private void updateShell(Vec3d base, float radius, float scale) {
            for (int i = 0; i < this.shellDisplays.size(); ++i) {
                Vec3d offset = this.shellPoints.get(i);
                Vec3d pos = base.add(offset.x * (double)(radius / 1.2f), offset.y, offset.z * (double)(radius / 1.2f));
                SpellVisualsManager.updateDisplay(this.shellDisplays.get(i), pos, scale);
            }
        }

        private void updateAura(Vec3d base, int tick) {
            for (int i = 0; i < this.auraDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.auraDisplays.size()) + (float)tick * 12.0f);
                Vec3d pos = base.add(Math.cos(angle) * 0.8, 0.8 + Math.sin(angle) * 0.2, Math.sin(angle) * 0.8);
                SpellVisualsManager.updateDisplay(this.auraDisplays.get(i), pos, 0.06f);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.domeDisplays);
            SpellVisualsManager.removeDisplays(this.shellDisplays);
            SpellVisualsManager.removeDisplays(this.auraDisplays);
            this.domeDisplays.clear();
            this.shellDisplays.clear();
            this.auraDisplays.clear();
            this.domePoints.clear();
            this.shellPoints.clear();
        }
    }

    private static class TitansBlessingState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> ritualDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8123> glyphs = new ArrayList<DisplayEntity.class_8123>();
        private final List<DisplayEntity.class_8115> totems = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> forgeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> columnDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> essenceDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> rayDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final Map<UUID, Vec3d> targets = new HashMap<UUID, Vec3d>();
        private boolean buffsApplied = false;

        private TitansBlessingState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 60; ++i) {
                this.ritualDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_22423.getDefaultState(), base, 0.15f, 0.03f, 0.15f));
            }
            for (i = 0; i < 16; ++i) {
                this.glyphs.add(SpellVisualsManager.createTextDisplay(world, "\u26a1", 16347926, base, 0.35f));
            }
            for (i = 0; i < 160; ++i) {
                this.totems.add(SpellVisualsManager.createDisplay(world, Blocks.field_10266.getDefaultState(), base, 0.2f, 0.6f, 0.2f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y, base.z);
            }
            if (this.tick < 40) {
                this.updateRitualCircles(base, this.tick);
                this.updateTotems(base, (float)this.tick / 40.0f);
                this.updateGlyphs(base, this.tick);
                this.applyEnemySlow(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, base.x, base.y + 0.2, base.z, 6, 6.0, 0.5, 6.0, 0.0);
                if (this.tick % 10 == 0) {
                    world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_15047, SoundCategory.field_15248, 0.6f, 0.8f);
                }
            } else if (this.tick < 70) {
                this.updateRitualCircles(base, this.tick);
                this.buildForge(base);
                this.updateForge(base, this.tick - 40);
                this.applyEnemySlow(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 6.0, base.z, 20, 2.0, 1.0, 2.0, 0.0);
                if (this.tick % 10 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14833, SoundCategory.field_15248, 0.7f, 1.1f);
                }
            } else if (this.tick < 100) {
                this.updateForge(base, this.tick - 40);
                this.updateRays(base, this.tick - 70);
                if (this.tick % 5 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_38068, SoundCategory.field_15248, 0.6f, 1.0f + (float)(this.tick - 70) * 0.01f);
                }
            } else if (this.tick < 140) {
                if (!this.buffsApplied) {
                    this.buffsApplied = true;
                    this.applyTitanBlessing(base);
                }
                this.updateTransformationBursts(base, this.tick - 100);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateRitualCircles(Vec3d base, int tick) {
            double[] radii = new double[]{12.0, 9.0, 6.0, 3.0};
            int perRing = 15;
            int index = 0;
            for (int ring = 0; ring < radii.length; ++ring) {
                for (int i = 0; i < perRing && index < this.ritualDisplays.size(); ++index, ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)perRing) + (float)(tick * (10 + ring * 5)));
                    Vec3d pos = base.add(Math.cos(angle) * radii[ring], 0.05, Math.sin(angle) * radii[ring]);
                    SpellVisualsManager.updateDisplay(this.ritualDisplays.get(index), pos, 0.15f, 0.03f, 0.15f);
                }
            }
        }

        private void updateGlyphs(Vec3d base, int tick) {
            String[] symbols = new String[]{"\u26a1", "\u26e8", "\u2694", "\u2620", "\u2692"};
            for (int i = 0; i < this.glyphs.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.glyphs.size()));
                Vec3d pos = base.add(Math.cos(angle) * 12.0, 1.0, Math.sin(angle) * 12.0);
                DisplayEntity.class_8123 glyph = this.glyphs.get(i);
                glyph.setPosition(pos.x, pos.y, pos.z);
                if (tick / 3 < i) continue;
                int color = i % 3 == 0 ? 0xEF4444 : (i % 3 == 1 ? 16347926 : 16638023);
                glyph.setText((Text)Text.literal((String)symbols[i % symbols.length]).formatted(Formatting.field_1067).withColor(color));
            }
        }

        private void updateTotems(Vec3d base, float progress) {
            int index = 0;
            for (int i = 0; i < 8; ++i) {
                float angle = (float)Math.toRadians((float)i * 45.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 12.0, 0.2, Math.sin(angle) * 12.0);
                for (int j = 0; j < 20 && index < this.totems.size(); ++index, ++j) {
                    Vec3d pos = pillarBase.add(0.0, (double)j * 0.5 * (double)progress, 0.0);
                    SpellVisualsManager.updateDisplay(this.totems.get(index), pos, 0.2f, 0.6f, 0.2f);
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, pos.x, pos.y + 0.6, pos.z, 1, 0.2, 0.2, 0.2, 0.0);
                }
            }
        }

        private void buildForge(Vec3d base) {
            int i;
            if (!this.forgeDisplays.isEmpty()) {
                return;
            }
            for (i = 0; i < 80; ++i) {
                this.forgeDisplays.add(SpellVisualsManager.createDisplay(this.world, i % 2 == 0 ? Blocks.field_10535.getDefaultState() : Blocks.field_10085.getDefaultState(), base.add(0.0, 10.0, 0.0), 0.15f));
            }
            for (i = 0; i < 200; ++i) {
                this.columnDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10002.getDefaultState(), base.add(0.0, 4.0, 0.0), 0.08f, 0.3f, 0.08f));
            }
            for (i = 0; i < 40; ++i) {
                this.essenceDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_22108.getDefaultState(), base.add(0.0, 10.0, 0.0), 0.1f));
            }
        }

        private void updateForge(Vec3d base, int localTick) {
            float angle;
            for (int i = 0; i < this.forgeDisplays.size(); ++i) {
                float layer = (float)(i % 8) / 8.0f;
                angle = (float)Math.toRadians((float)i * 12.0f + (float)localTick * 30.0f);
                Vec3d pos = base.add(Math.cos(angle) * (2.0 + (double)layer), 10.0 - (double)layer * 3.0, Math.sin(angle) * (2.0 + (double)layer));
                SpellVisualsManager.updateDisplay(this.forgeDisplays.get(i), pos, 0.15f);
            }
            int index = 0;
            for (int i = 0; i < 8; ++i) {
                angle = (float)Math.toRadians((float)i * 45.0f);
                Vec3d start = base.add(Math.cos(angle) * 12.0, 0.2, Math.sin(angle) * 12.0);
                Vec3d end = base.add(0.0, 10.0, 0.0);
                Vec3d delta = end.subtract(start);
                for (int j = 0; j < 25 && index < this.columnDisplays.size(); ++index, ++j) {
                    float t = (float)(j + 1) / 26.0f;
                    Vec3d pos = start.add(delta.multiply((double)t));
                    SpellVisualsManager.updateDisplay(this.columnDisplays.get(index), pos, 0.08f, 0.3f, 0.08f);
                }
            }
            float pulse = 0.1f + MathHelper.sin((float)((float)localTick * 0.3f)) * 0.05f;
            for (int i = 0; i < this.essenceDisplays.size(); ++i) {
                float scale = 0.2f + pulse + (float)(i % 5) * 0.02f;
                Vec3d pos = base.add(0.0, 10.0 + Math.sin((double)(localTick + i) * 0.3) * 0.4, 0.0);
                SpellVisualsManager.updateDisplay(this.essenceDisplays.get(i), pos, scale);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, base.x, base.y + 10.0, base.z, 25, 1.5, 1.0, 1.5, 0.0);
        }

        private void updateRays(Vec3d base, int localTick) {
            this.targets.clear();
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 12.0)) {
                this.targets.put(target.getUuid(), target.getPos());
            }
            if (this.rayDisplays.isEmpty()) {
                for (int i = 0; i < this.targets.size() * 15; ++i) {
                    this.rayDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10205.getDefaultState(), base.add(0.0, 10.0, 0.0), 0.12f, 0.5f, 0.12f));
                }
            }
            int index = 0;
            for (Vec3d target : this.targets.values()) {
                Vec3d start = base.add(0.0, 10.0, 0.0);
                Vec3d end = target.add(0.0, 1.0, 0.0);
                Vec3d delta = end.subtract(start);
                for (int i = 0; i < 15 && index < this.rayDisplays.size(); ++index, ++i) {
                    float t = (float)(i + 1) / 16.0f;
                    Vec3d pos = start.add(delta.multiply((double)t));
                    SpellVisualsManager.updateDisplay(this.rayDisplays.get(index), pos, 0.12f, 0.5f, 0.12f);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, end.x, end.y, end.z, 20, 0.6, 0.6, 0.6, 0.0);
            }
        }

        private void updateTransformationBursts(Vec3d base, int localTick) {
            if (localTick == 0) {
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11221, base.x, base.y + 10.0, base.z, 5, 1.0, 1.0, 1.0, 0.0);
                this.world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_15152, SoundCategory.field_15248, 1.2f, 0.8f);
            }
            for (Vec3d target : this.targets.values()) {
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, target.x, target.y + 1.0, target.z, 50, 1.2, 0.6, 1.2, 0.0);
            }
        }

        private void applyEnemySlow(Vec3d base) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 15.0, living -> !(living instanceof PlayerEntity))) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 20, 1, false, true));
                for (int i = 0; i < 4; ++i) {
                    float angle = (float)Math.toRadians((float)i * 90.0f + (float)this.tick * 10.0f);
                    Vec3d pos = entity.getPos().add(Math.cos(angle) * 0.4, 0.1, Math.sin(angle) * 0.4);
                    DisplayEntity.class_8115 chain = SpellVisualsManager.createDisplay(this.world, Blocks.field_23985.getDefaultState(), pos, 0.08f, 0.04f, 0.08f);
                    SpellVisualsManager.removeDisplay(chain);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11231, entity.getX(), entity.getBodyY(0.2), entity.getZ(), 2, 0.3, 0.2, 0.3, 0.0);
            }
        }

        private void applyTitanBlessing(Vec3d base) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 12.0)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 500, 3, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 500, 4, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5904, 500, 1, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5913, 500, 2, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 500, 4, false, false));
                TitanFormState form = new TitanFormState(this.world, target);
                TITAN_FORMS.put(target.getUuid(), form);
                this.world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.field_14833, SoundCategory.field_15248, 0.8f, 1.1f);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.ritualDisplays);
            SpellVisualsManager.removeDisplays(this.totems);
            SpellVisualsManager.removeDisplays(this.forgeDisplays);
            SpellVisualsManager.removeDisplays(this.columnDisplays);
            SpellVisualsManager.removeDisplays(this.essenceDisplays);
            SpellVisualsManager.removeDisplays(this.rayDisplays);
            for (DisplayEntity.class_8123 glyph : this.glyphs) {
                if (glyph == null) continue;
                glyph.discard();
            }
            this.ritualDisplays.clear();
            this.glyphs.clear();
            this.totems.clear();
            this.forgeDisplays.clear();
            this.columnDisplays.clear();
            this.essenceDisplays.clear();
            this.rayDisplays.clear();
            this.targets.clear();
        }
    }

    private static class CataclysmState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> crackDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> vortexDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> pillarDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> orbDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> ringDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<BlockPos> destructible = new ArrayList<BlockPos>();
        private Vec3d impactPoint;
        private boolean damageApplied = false;
        private boolean destructionPrepared = false;
        private boolean orbBuilt = false;

        private CataclysmState(ServerWorld world, PlayerEntity player) {
            Vec3d base;
            this.world = world;
            this.playerId = player.getUuid();
            this.impactPoint = base = SpellVisualsManager.getLookTargetPoint(world, player, 15.0);
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 0.2, base.z);
            world.spawnEntity((Entity)this.marker);
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = this.impactPoint;
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 0.2, base.z);
            }
            if (this.tick < 50) {
                this.buildCracks(base, this.tick);
                this.applyCataclysmDebuff(base, 20.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, base.x, base.y + 0.1, base.z, 15, 6.0, 0.2, 6.0, 0.0);
                if (this.tick % 10 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14833, SoundCategory.field_15248, 0.4f, 0.5f);
                }
            } else if (this.tick < 90) {
                if (!this.orbBuilt) {
                    this.orbBuilt = true;
                    this.buildVortex(base);
                }
                this.updateVortex(base, this.tick - 50);
                this.updatePillars(base, this.tick - 50);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11214, base.x, base.y + 18.0, base.z, 20, 4.0, 3.0, 4.0, 0.0);
                if (this.tick % 10 == 0) {
                    world.playSound(null, base.x, base.y + 10.0, base.z, SoundEvents.field_14792, SoundCategory.field_15248, 0.5f, 0.7f);
                }
            } else if (this.tick < 140) {
                this.updateVortex(base, this.tick - 50);
                this.updateOrbDescent(base, this.tick - 90);
                this.applyCataclysmDebuff(base, 20.0);
                if (!this.destructionPrepared && this.tick >= 120) {
                    this.destructionPrepared = true;
                    this.prepareDestruction(base, 10);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, base.x, base.y + 2.0, base.z, 5, 2.0, 2.0, 2.0, 0.0);
            } else if (this.tick < 200) {
                int local = this.tick - 140;
                if (!this.damageApplied) {
                    this.damageApplied = true;
                    this.applyCataclysmDamage(base);
                    SpellVisualsManager.applyRadialKnockback(world, base, 12.0, 5.0);
                    this.spawnCataclysmExplosion(base);
                }
                this.updateShockwaves(base, local);
                this.processDestruction(base, local);
                if (local > 30) {
                    CATACLYSM_HAZARDS.put(UUID.randomUUID(), new CataclysmHazardState(world, base));
                }
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void buildCracks(Vec3d base, int tick) {
            int lines = 12;
            int perLine = Nomorespell.isPerformanceMode() ? 12 : 25;
            int total = lines * perLine;
            while (this.crackDisplays.size() < total) {
                this.crackDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_23869.getDefaultState(), base, 0.08f, 0.01f, 0.08f));
            }
            int current = Math.min(perLine, tick / 2 + 1);
            int index = 0;
            for (int i = 0; i < lines; ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)lines));
                for (int j = 0; j < perLine; ++j) {
                    DisplayEntity.class_8115 display = this.crackDisplays.get(index++);
                    if (j >= current) {
                        SpellVisualsManager.updateDisplay(display, base, 0.001f, 0.001f, 0.001f);
                        continue;
                    }
                    double dist = 1.0 + (double)j * (15.0 / (double)perLine);
                    Vec3d pos = base.add(Math.cos(angle) * dist, 0.05, Math.sin(angle) * dist);
                    SpellVisualsManager.updateDisplay(display, pos, 0.08f, 0.01f, 0.08f);
                }
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, base.x, base.y + 0.1, base.z, 10, 4.0, 0.2, 4.0, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, base.x, base.y + 0.1, base.z, 5, 2.0, 0.2, 2.0, 0.0);
        }

        private void buildVortex(Vec3d base) {
            int i;
            int count = Nomorespell.isPerformanceMode() ? 40 : 80;
            for (i = 0; i < count; ++i) {
                this.vortexDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10540.getDefaultState(), base.add(0.0, 18.0, 0.0), 0.12f));
            }
            for (i = 0; i < 180; ++i) {
                this.pillarDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_23869.getDefaultState(), base, 0.25f, 0.8f, 0.25f));
            }
            for (i = 0; i < 60; ++i) {
                this.orbDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_22108.getDefaultState(), base.add(0.0, 18.0, 0.0), 0.15f));
            }
        }

        private void updateVortex(Vec3d base, int localTick) {
            float rotation = (float)localTick * 45.0f;
            for (int i = 0; i < this.vortexDisplays.size(); ++i) {
                float t = (float)i / (float)this.vortexDisplays.size();
                double radius = 8.0 - (double)t * 5.0;
                float angle = (float)Math.toRadians(rotation + (float)i * 12.0f);
                Vec3d pos = base.add(Math.cos(angle) * radius, 18.0 - (double)t * 8.0, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(this.vortexDisplays.get(i), pos, 0.12f);
            }
        }

        private void updatePillars(Vec3d base, int localTick) {
            int index = 0;
            for (int i = 0; i < 6; ++i) {
                float angle = (float)Math.toRadians((float)i * 60.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 15.0, 0.0, Math.sin(angle) * 15.0);
                for (int j = 0; j < 30 && index < this.pillarDisplays.size(); ++index, ++j) {
                    float height = Math.min(25.0f, (float)localTick / 40.0f * 25.0f);
                    Vec3d pos = pillarBase.add(0.0, Math.min((double)height, (double)j * 0.9), 0.0);
                    SpellVisualsManager.updateDisplay(this.pillarDisplays.get(index), pos, 0.25f, 0.8f, 0.25f);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, pillarBase.x, pillarBase.y + 1.0, pillarBase.z, 5, 0.6, 1.0, 0.6, 0.0);
            }
        }

        private void updateOrbDescent(Vec3d base, int localTick) {
            float progress = (float)localTick / 50.0f;
            double y = 18.0 - 3.0 * (double)progress;
            float scale = 0.2f + progress * 0.3f;
            float rotation = (float)localTick * 60.0f;
            for (int i = 0; i < this.orbDisplays.size(); ++i) {
                float angle = (float)Math.toRadians(rotation + (float)i * 12.0f);
                double radius = 1.5 + Math.sin(angle) * 0.4;
                Vec3d pos = base.add(Math.cos(angle) * radius, y, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(this.orbDisplays.get(i), pos, scale);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, base.x, base.y + y, base.z, 10, 1.5, 1.5, 1.5, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, base.x, base.y + y, base.z, 15, 1.5, 1.5, 1.5, 0.0);
        }

        private void applyCataclysmDebuff(Vec3d base, double radius) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, base, radius)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 40, 3, false, true));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, 40, 1, false, true));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 40, 2, false, true));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, entity.getX(), entity.getBodyY(0.6), entity.getZ(), 3, 0.4, 0.4, 0.4, 0.0);
            }
        }

        private void applyCataclysmDamage(Vec3d base) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 15.0)) {
                double dist = entity.getPos().distanceTo(base);
                float damage = dist <= 5.0 ? 200.0f : (dist <= 10.0 ? 120.0f : 60.0f);
                entity.damage(this.world, this.world.getDamageSources().magic(), damage);
            }
        }

        private void spawnCataclysmExplosion(Vec3d base) {
            int i;
            int count = Nomorespell.isPerformanceMode() ? 80 : 200;
            ArrayList<DisplayEntity.class_8115> fragments = new ArrayList<DisplayEntity.class_8115>();
            for (i = 0; i < count; ++i) {
                fragments.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10540.getDefaultState(), base, 0.1f));
            }
            for (i = 0; i < fragments.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)fragments.size()));
                Vec3d pos = base.add(Math.cos(angle) * 2.0, 1.0, Math.sin(angle) * 2.0);
                SpellVisualsManager.updateDisplay((DisplayEntity.class_8115)fragments.get(i), pos, 0.1f);
            }
            SpellVisualsManager.removeDisplays(fragments);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11221, base.x, base.y, base.z, 6, 1.0, 1.0, 1.0, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, base.x, base.y + 1.0, base.z, 120, 6.0, 2.0, 6.0, 0.0);
            this.world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_15152, SoundCategory.field_15248, 2.0f, 0.6f);
            this.world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14865, SoundCategory.field_15248, 2.0f, 0.7f);
        }

        private void updateShockwaves(Vec3d base, int localTick) {
            this.ringDisplays.clear();
            int count = Nomorespell.isPerformanceMode() ? 24 : 48;
            for (int i = 0; i < count; ++i) {
                this.ringDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10058.getDefaultState(), base, 0.15f, 0.3f, 0.15f));
            }
            float radius = MathHelper.clamp((float)((float)localTick / 20.0f), (float)0.0f, (float)1.0f) * 15.0f;
            for (int i = 0; i < this.ringDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.ringDisplays.size()));
                Vec3d pos = base.add(Math.cos(angle) * (double)radius, 0.4, Math.sin(angle) * (double)radius);
                SpellVisualsManager.updateDisplay(this.ringDisplays.get(i), pos, 0.15f, 0.3f, 0.15f);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, base.x, base.y + 0.6, base.z, 30, 6.0, 0.4, 6.0, 0.0);
            SpellVisualsManager.removeDisplays(this.ringDisplays);
            this.ringDisplays.clear();
        }

        private void prepareDestruction(Vec3d base, int radius) {
            this.destructible.clear();
            BlockPos center = BlockPos.ofFloored((double)base.x, (double)base.y, (double)base.z);
            for (int x = -radius; x <= radius; ++x) {
                for (int y = -radius; y <= radius; ++y) {
                    for (int z = -radius; z <= radius; ++z) {
                        BlockState state;
                        BlockPos pos = center.add(x, y, z);
                        if (pos.getSquaredDistance((Vec3i)center) > (double)(radius * radius) || !SpellVisualsManager.isFragile(state = this.world.getBlockState(pos))) continue;
                        this.destructible.add(pos.toImmutable());
                    }
                }
            }
            this.destructible.sort((a, b) -> Double.compare(a.getSquaredDistance((Vec3i)center), b.getSquaredDistance((Vec3i)center)));
        }

        private void processDestruction(Vec3d base, int localTick) {
            int endIndex;
            if (this.destructible.isEmpty()) {
                return;
            }
            int maxPerTick = 10;
            int startIndex = Math.min(this.destructible.size(), localTick * maxPerTick);
            if (startIndex >= (endIndex = Math.min(this.destructible.size(), startIndex + maxPerTick))) {
                return;
            }
            for (int i = startIndex; i < endIndex; ++i) {
                boolean drop;
                BlockPos pos = this.destructible.get(i);
                BlockState state = this.world.getBlockState(pos);
                if (state.isAir()) continue;
                boolean bl = drop = this.world.random.nextFloat() < 0.3f;
                if (drop) {
                    this.world.breakBlock(pos, true);
                } else {
                    this.world.setBlockState(pos, Blocks.field_10124.getDefaultState(), 3);
                }
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5, 0.2, 0.2, 0.2, 0.0);
            }
            if (localTick % 5 == 0) {
                this.world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15026, SoundCategory.field_15248, 0.6f, 0.8f);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.crackDisplays);
            SpellVisualsManager.removeDisplays(this.vortexDisplays);
            SpellVisualsManager.removeDisplays(this.pillarDisplays);
            SpellVisualsManager.removeDisplays(this.orbDisplays);
            SpellVisualsManager.removeDisplays(this.ringDisplays);
            this.crackDisplays.clear();
            this.vortexDisplays.clear();
            this.pillarDisplays.clear();
            this.orbDisplays.clear();
            this.ringDisplays.clear();
            this.destructible.clear();
        }
    }

    private static class MiracleState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> auraDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> portalDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> lightColumn = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8123> symbols = new ArrayList<DisplayEntity.class_8123>();
        private final Map<UUID, Vec3d> targetedPlayers = new HashMap<UUID, Vec3d>();
        private final Map<UUID, DeathRecord> resurrectionTargets = new HashMap<UUID, DeathRecord>();
        private boolean releaseTriggered = false;

        private MiracleState(ServerWorld world, PlayerEntity player) {
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 2.0, base.z);
            world.spawnEntity((Entity)this.marker);
            for (int i = 0; i < 40; ++i) {
                this.auraDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10087.getDefaultState(), base, 0.12f));
            }
            this.symbols.add(SpellVisualsManager.createTextDisplay(world, "\u271e", 16638023, base, 0.35f));
            this.symbols.add(SpellVisualsManager.createTextDisplay(world, "\u2627", 16638023, base, 0.35f));
            this.symbols.add(SpellVisualsManager.createTextDisplay(world, "\u2695", 16638023, base, 0.35f));
            this.symbols.add(SpellVisualsManager.createTextDisplay(world, "\u2624", 16638023, base, 0.35f));
            this.symbols.add(SpellVisualsManager.createTextDisplay(world, "\u271a", 16638023, base, 0.35f));
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 2.0, base.z);
            }
            if (this.tick < 30) {
                this.updateAura(base, 2.0f, (float)this.tick * 10.0f);
                this.updateSymbols(base, this.tick);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 10, 0.8, 0.8, 0.8, 0.0);
                if (this.tick % 10 == 0) {
                    world.playSound(null, base.x, base.y, base.z, (SoundEvent)SoundEvents.field_15114.comp_349(), SoundCategory.field_15248, 0.5f, 1.4f);
                }
            } else if (this.tick < 80) {
                this.buildPortal(base);
                this.updatePortal(base, this.tick - 30);
                this.updateColumn(base, this.tick - 30);
                this.findTargets(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, base.x, base.y + 8.0, base.z, 20, 2.0, 2.0, 2.0, 0.0);
            } else if (this.tick < 120) {
                this.updateAura(base, 5.0f, (float)this.tick * 12.0f);
                this.updatePortal(base, this.tick - 30);
                this.updateColumn(base, this.tick - 30);
                if (!this.releaseTriggered) {
                    this.releaseTriggered = true;
                    this.triggerMiracleRelease(base);
                }
                this.updateResurrections();
            } else if (this.tick < 160) {
                this.updateResurrections();
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateAura(Vec3d base, float radius, float rotation) {
            for (int i = 0; i < this.auraDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.auraDisplays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * (double)radius, 0.8 + Math.sin(angle * 2.0f) * 0.3, Math.sin(angle) * (double)radius);
                SpellVisualsManager.updateDisplay(this.auraDisplays.get(i), pos, 0.12f);
            }
        }

        private void updateSymbols(Vec3d base, int tick) {
            for (int i = 0; i < this.symbols.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.symbols.size()) + (float)tick * 15.0f);
                Vec3d pos = base.add(Math.cos(angle) * 3.0, 0.6, Math.sin(angle) * 3.0);
                this.symbols.get(i).setPosition(pos.x, pos.y, pos.z);
            }
        }

        private void buildPortal(Vec3d base) {
            int i;
            if (!this.portalDisplays.isEmpty()) {
                return;
            }
            int count = Nomorespell.isPerformanceMode() ? 30 : 60;
            for (i = 0; i < count; ++i) {
                this.portalDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10049.getDefaultState(), base.add(0.0, 20.0, 0.0), 0.1f));
            }
            for (i = 0; i < 50; ++i) {
                this.lightColumn.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10542.getDefaultState(), base, 0.15f, 0.4f, 0.15f));
            }
        }

        private void updatePortal(Vec3d base, int localTick) {
            float rotation = (float)localTick * 20.0f;
            for (int i = 0; i < this.portalDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.portalDisplays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * 8.0, 20.0, Math.sin(angle) * 8.0);
                SpellVisualsManager.updateDisplay(this.portalDisplays.get(i), pos, 0.1f);
            }
        }

        private void updateColumn(Vec3d base, int localTick) {
            for (int i = 0; i < this.lightColumn.size(); ++i) {
                float t = (float)i / (float)this.lightColumn.size();
                Vec3d pos = base.add(0.0, 20.0 - (double)t * 20.0, 0.0);
                SpellVisualsManager.updateDisplay(this.lightColumn.get(i), pos, 0.15f, 0.4f, 0.15f);
            }
        }

        private void findTargets(Vec3d base) {
            this.targetedPlayers.clear();
            for (PlayerEntity class_16572 : SpellVisualsManager.getPlayersAround(this.world, base, 20.0)) {
                this.targetedPlayers.put(class_16572.getUuid(), class_16572.getPos());
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, class_16572.getX(), class_16572.getBodyY(0.8), class_16572.getZ(), 3, 0.4, 0.4, 0.4, 0.0);
            }
            this.resurrectionTargets.clear();
            for (Map.Entry entry : DEATH_RECORDS.entrySet()) {
                DeathRecord record = (DeathRecord)entry.getValue();
                if (record == null || record.world != this.world || record.pos.distanceTo(base) > 25.0 || this.world.getTime() - record.time > 200L) continue;
                this.resurrectionTargets.put((UUID)entry.getKey(), record);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_23114, record.pos.x, record.pos.y + 0.2, record.pos.z, 10, 0.6, 0.4, 0.6, 0.0);
            }
        }

        private void triggerMiracleRelease(Vec3d base) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 20.0)) {
                float newHealth = Math.min(target.getMaxHealth(), target.getHealth() + 50.0f);
                target.setHealth(newHealth);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 400, 4, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 400, 3, false, false));
                SpellVisualsManager.clearNegativeEffects(target);
                MiracleBlessingState blessing = new MiracleBlessingState(this.world, target, false);
                MIRACLE_BLESSINGS.put(target.getUuid(), blessing);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, target.getX(), target.getBodyY(0.8), target.getZ(), 30, 1.0, 0.6, 1.0, 0.0);
                this.world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.field_14931, SoundCategory.field_15248, 0.6f, 1.2f);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, base.x, base.y + 1.0, base.z, 120, 6.0, 2.0, 6.0, 0.2);
            this.world.playSound(null, base.x, base.y, base.z, SoundEvents.field_17265, SoundCategory.field_15248, 1.2f, 1.0f);
        }

        private void updateResurrections() {
            if (this.resurrectionTargets.isEmpty()) {
                return;
            }
            for (Map.Entry<UUID, DeathRecord> entry : this.resurrectionTargets.entrySet()) {
                UUID id = entry.getKey();
                DeathRecord record = entry.getValue();
                ServerPlayerEntity player = this.world.getServer().getPlayerManager().getPlayer(id);
                if (player == null) continue;
                if (!player.isAlive()) {
                    player = this.world.getServer().getPlayerManager().respawnPlayer(player, false, Entity.class_5529.DISCARDED);
                }
                Vec3d respawnPos = SpellVisualsManager.clampGround(this.world, record.pos);
                player.teleport(this.world, respawnPos.x, respawnPos.y, respawnPos.z, Set.of(), player.getYaw(), player.getPitch(), true);
                player.setHealth(player.getMaxHealth());
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 400, 4, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 400, 3, false, false));
                SpellVisualsManager.clearNegativeEffects((PlayerEntity)player);
                SpellVisualsManager.applyInvulnerability(player, 100);
                MiracleBlessingState blessing = new MiracleBlessingState(this.world, (PlayerEntity)player, true);
                MIRACLE_BLESSINGS.put(player.getUuid(), blessing);
            }
            this.resurrectionTargets.clear();
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.auraDisplays);
            SpellVisualsManager.removeDisplays(this.portalDisplays);
            SpellVisualsManager.removeDisplays(this.lightColumn);
            for (DisplayEntity.class_8123 symbol : this.symbols) {
                if (symbol == null) continue;
                symbol.discard();
            }
            this.auraDisplays.clear();
            this.portalDisplays.clear();
            this.lightColumn.clear();
            this.symbols.clear();
            this.targetedPlayers.clear();
            this.resurrectionTargets.clear();
        }
    }

    private static class DominionState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> circles = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> totems = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> dome = new ArrayList<DisplayEntity.class_8115>();
        private final Map<UUID, Vec3d> allies = new HashMap<UUID, Vec3d>();
        private final Map<UUID, Vec3d> enemies = new HashMap<UUID, Vec3d>();
        private boolean buffsApplied = false;

        private DominionState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 1.5, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 180; ++i) {
                this.circles.add(SpellVisualsManager.createDisplay(world, Blocks.field_10540.getDefaultState(), base, 0.12f, 0.02f, 0.12f));
            }
            for (i = 0; i < 300; ++i) {
                this.totems.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.3f, 1.0f, 0.3f));
            }
            for (i = 0; i < 120; ++i) {
                this.dome.add(SpellVisualsManager.createDisplay(world, Blocks.field_10399.getDefaultState(), base, 0.12f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 1.5, base.z);
            }
            if (this.tick < 120) {
                this.updateCircles(base, this.tick);
                this.updateTotems(base, (float)this.tick / 120.0f);
                this.imprisonEnemies(base);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, base.x, base.y + 0.2, base.z, 10, 8.0, 0.4, 8.0, 0.0);
                if (this.tick % 20 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_38075, SoundCategory.field_15248, 0.6f, 0.6f);
                }
            } else if (this.tick < 140) {
                if (!this.buffsApplied) {
                    this.buffsApplied = true;
                    this.applyDominionEffects(base);
                }
                this.updateCircles(base, this.tick);
                this.updateTotems(base, 1.0f);
                this.updateDome(base, this.tick);
            } else if (this.tick < 740) {
                this.updateCircles(base, this.tick);
                this.updateTotems(base, 1.0f);
                this.updateDome(base, this.tick);
                if (this.tick % 200 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14891, SoundCategory.field_15248, 0.8f, 0.8f);
                }
            } else if (this.tick < 770) {
                if (this.tick == 740) {
                    this.endDominion();
                }
                this.updateCircles(base, this.tick);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateCircles(Vec3d base, int tick) {
            double[] radii = new double[]{25.0, 15.0, 5.0};
            int perRing = 60;
            int index = 0;
            for (int ring = 0; ring < radii.length; ++ring) {
                for (int i = 0; i < perRing && index < this.circles.size(); ++index, ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)perRing) + (float)(tick * (10 + ring * 5)));
                    Vec3d pos = base.add(Math.cos(angle) * radii[ring], 0.05, Math.sin(angle) * radii[ring]);
                    SpellVisualsManager.updateDisplay(this.circles.get(index), pos, 0.12f, 0.02f, 0.12f);
                }
            }
        }

        private void updateTotems(Vec3d base, float progress) {
            int index = 0;
            for (int i = 0; i < 12; ++i) {
                float angle = (float)Math.toRadians((float)i * 30.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 25.0, 0.2, Math.sin(angle) * 25.0);
                for (int j = 0; j < 25 && index < this.totems.size(); ++index, ++j) {
                    Vec3d pos = pillarBase.add(0.0, (double)j * 0.6 * (double)progress, 0.0);
                    SpellVisualsManager.updateDisplay(this.totems.get(index), pos, 0.3f, 1.0f, 0.3f);
                }
            }
        }

        private void updateDome(Vec3d base, int tick) {
            for (int i = 0; i < this.dome.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.dome.size()) + (float)tick * 5.0f);
                Vec3d pos = base.add(Math.cos(angle) * 25.0, 6.0 + Math.sin(angle) * 4.0, Math.sin(angle) * 25.0);
                SpellVisualsManager.updateDisplay(this.dome.get(i), pos, 0.12f);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11214, base.x, base.y + 6.0, base.z, 5, 12.0, 4.0, 12.0, 0.0);
        }

        private void imprisonEnemies(Vec3d base) {
            for (LivingEntity enemy : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 30.0, living -> !(living instanceof PlayerEntity))) {
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 40, 4, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 40, 4, false, true));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11231, enemy.getX(), enemy.getBodyY(0.5), enemy.getZ(), 3, 0.4, 0.4, 0.4, 0.0);
            }
        }

        private void applyDominionEffects(Vec3d base) {
            this.allies.clear();
            this.enemies.clear();
            for (PlayerEntity ally : SpellVisualsManager.getPlayersAround(this.world, base, 25.0)) {
                this.allies.put(ally.getUuid(), ally.getPos());
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 600, 4, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 600, 4, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5904, 600, 2, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 600, 5, false, false));
                DominionChampionState champion = new DominionChampionState(this.world, ally);
                DOMINION_CHAMPIONS.put(ally.getUuid(), champion);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, ally.getX(), ally.getBodyY(0.8), ally.getZ(), 20, 0.8, 0.6, 0.8, 0.0);
            }
            for (LivingEntity enemy : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 25.0, living -> !(living instanceof PlayerEntity))) {
                this.enemies.put(enemy.getUuid(), enemy.getPos());
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, 600, 4, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 600, 4, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 600, 4, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5919, 600, 0, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 600, 0, false, true));
                DominionEnemyState state = new DominionEnemyState(this.world, enemy);
                DOMINION_ENEMIES.put(enemy.getUuid(), state);
            }
        }

        private void endDominion() {
            for (DominionChampionState champion : DOMINION_CHAMPIONS.values()) {
                if (champion == null) continue;
                champion.cleanup();
            }
            DOMINION_CHAMPIONS.clear();
            for (DominionEnemyState enemyState : DOMINION_ENEMIES.values()) {
                if (enemyState == null) continue;
                enemyState.cleanup();
            }
            DOMINION_ENEMIES.clear();
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.circles);
            SpellVisualsManager.removeDisplays(this.totems);
            SpellVisualsManager.removeDisplays(this.dome);
            this.circles.clear();
            this.totems.clear();
            this.dome.clear();
            this.allies.clear();
            this.enemies.clear();
        }
    }

    private static class AnnihilationBeamState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> circleOne = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> circleTwo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> circleThree = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> pillars = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> axisMarkers = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> beamDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<BlockPos> beamBlocks = new ArrayList<BlockPos>();
        private Vec3d beamDirection = new Vec3d(0.0, 0.0, 1.0);
        private boolean zoneApplied = false;
        private boolean beamPrepared = false;
        private boolean beamFired = false;
        private boolean destructionDone = false;

        private AnnihilationBeamState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 1.0, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 36; ++i) {
                this.circleOne.add(SpellVisualsManager.createDisplay(world, Blocks.field_10540.getDefaultState(), base, 0.12f, 0.02f, 0.12f));
            }
            for (i = 0; i < 48; ++i) {
                this.circleTwo.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.15f, 0.02f, 0.15f));
            }
            for (i = 0; i < 60; ++i) {
                this.circleThree.add(SpellVisualsManager.createDisplay(world, Blocks.field_23869.getDefaultState(), base, 0.18f, 0.02f, 0.18f));
            }
            for (i = 0; i < 120; ++i) {
                this.pillars.add(SpellVisualsManager.createDisplay(world, Blocks.field_22423.getDefaultState(), base, 0.2f, 0.5f, 0.2f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 1.0, base.z);
            }
            if (this.tick < 1200) {
                this.updateBeamCircles(base, this.tick);
                this.updateBeamPillars(base, this.tick);
                this.updateAxisMarkers(base, this.tick);
                if (this.tick % 10 == 0) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, base.x, base.y + 0.2, base.z, 6, 2.0, 0.2, 2.0, 0.0);
                }
                if (this.tick % 60 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_15045, SoundCategory.field_15248, 0.6f, 0.6f);
                }
            } else if (this.tick < 1800) {
                this.updateBeamCircles(base, this.tick);
                this.updateBeamPillars(base, this.tick);
                this.updateAxisMarkers(base, this.tick);
                if (!this.zoneApplied) {
                    this.zoneApplied = true;
                    this.applyTetanization(base, 55.0);
                }
                if (this.tick % 20 == 0) {
                    this.applyTetanization(base, 55.0);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, base.x, base.y + 0.2, base.z, 8, 3.0, 0.4, 3.0, 0.0);
            } else if (this.tick < 1860) {
                if (!this.beamPrepared) {
                    this.beamPrepared = true;
                    this.beamDirection = caster.getRotationVec(1.0f).normalize();
                    this.beamBlocks.addAll(SpellVisualsManager.collectBeamBlocks(world, base.add(0.0, 1.2, 0.0), this.beamDirection, 50, 1));
                }
                this.updateBeamDisplays(base, this.beamDirection, this.tick - 1800);
                if (!this.beamFired && this.tick >= 1805) {
                    this.beamFired = true;
                    SpellVisualsManager.applyBeamDamage(world, base.add(0.0, 1.2, 0.0), this.beamDirection, 50, 2.0, 300.0f, 150.0f);
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14865, SoundCategory.field_15248, 2.0f, 0.6f);
                    world.playSound(null, base.x, base.y, base.z, (RegistryEntry)SoundEvents.field_15152, SoundCategory.field_15248, 2.0f, 0.8f);
                }
                if (!this.destructionDone && this.tick >= 1805) {
                    this.destructionDone = true;
                    SpellVisualsManager.destroyBeamBlocks(world, this.beamBlocks, 0.1f);
                }
                SpellVisualsManager.spawnBeamParticles(world, base.add(0.0, 1.2, 0.0), this.beamDirection, 50, Nomorespell.isPerformanceMode() ? 60 : 120, 2.0, (ParticleEffect)ParticleTypes.field_11239);
                SpellVisualsManager.spawnBeamParticles(world, base.add(0.0, 1.2, 0.0), this.beamDirection, 50, Nomorespell.isPerformanceMode() ? 60 : 120, 2.0, (ParticleEffect)ParticleTypes.field_11236);
            } else if (this.tick < 1920) {
                SpellVisualsManager.spawnBeamParticles(world, base.add(0.0, 1.2, 0.0), this.beamDirection, 50, Nomorespell.isPerformanceMode() ? 30 : 60, 3.0, (ParticleEffect)ParticleTypes.field_11251);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateBeamCircles(Vec3d base, int tick) {
            this.updateCircle(this.circleOne, base, 3.0, (float)tick * 15.0f, 0.12f);
            this.updateCircle(this.circleTwo, base, 6.0, (float)(-tick) * 20.0f, 0.15f);
            this.updateCircle(this.circleThree, base, 10.0, (float)tick * 25.0f, 0.18f);
        }

        private void updateCircle(List<DisplayEntity.class_8115> displays, Vec3d base, double radius, float rotation, float scale) {
            for (int i = 0; i < displays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)displays.size()) + rotation);
                Vec3d pos = base.add(Math.cos(angle) * radius, 0.05, Math.sin(angle) * radius);
                SpellVisualsManager.updateDisplay(displays.get(i), pos, scale, 0.02f, scale);
            }
        }

        private void updateBeamPillars(Vec3d base, int tick) {
            int index = 0;
            for (int i = 0; i < 6; ++i) {
                float angle = (float)Math.toRadians((float)i * 60.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 6.0, 0.0, Math.sin(angle) * 6.0);
                for (int j = 0; j < 20 && index < this.pillars.size(); ++index, ++j) {
                    float height = Math.min(8.0f, (float)tick / 600.0f * 8.0f);
                    Vec3d pos = pillarBase.add(0.0, Math.min((double)height, (double)j * 0.4), 0.0);
                    SpellVisualsManager.updateDisplay(this.pillars.get(index), pos, 0.2f, 0.5f, 0.2f);
                }
            }
        }

        private void updateAxisMarkers(Vec3d base, int tick) {
            int maxMarkers = Math.min(50, tick / 6 + 1);
            while (this.axisMarkers.size() < maxMarkers) {
                this.axisMarkers.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10002.getDefaultState(), base, 0.06f));
            }
            Vec3d dir = this.world.getPlayerByUuid(this.playerId).getRotationVec(1.0f).normalize();
            for (int i = 0; i < this.axisMarkers.size(); ++i) {
                Vec3d pos = base.add(0.0, 1.2, 0.0).add(dir.multiply((double)(i + 1)));
                SpellVisualsManager.updateDisplay(this.axisMarkers.get(i), pos, 0.06f);
            }
        }

        private void updateBeamDisplays(Vec3d base, Vec3d direction, int localTick) {
            int count;
            int n = count = Nomorespell.isPerformanceMode() ? 160 : 300;
            while (this.beamDisplays.size() < count) {
                this.beamDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10164.getDefaultState(), base, 0.15f));
            }
            Vec3d dir = direction.normalize();
            for (int i = 0; i < this.beamDisplays.size(); ++i) {
                double t = (double)(i % 50) + this.world.random.nextDouble() * 0.5;
                double angle = (double)i / 50.0 * Math.PI * 2.0;
                double radius = 1.5 + Math.sin((double)localTick * 0.4 + (double)i) * 0.3;
                Vec3d offset = new Vec3d(Math.cos(angle) * radius, Math.sin(angle) * radius, 0.0);
                Vec3d pos = base.add(0.0, 1.2, 0.0).add(dir.multiply(t)).add(offset);
                SpellVisualsManager.updateDisplay(this.beamDisplays.get(i), pos, 0.12f);
            }
        }

        private void applyTetanization(Vec3d base, double radius) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, base, radius)) {
                PlayerEntity player;
                if (entity instanceof PlayerEntity && (player = (PlayerEntity)entity).getUuid().equals(this.playerId)) continue;
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 80, 5, false, true));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 80, 5, false, true));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5919, 80, 0, false, true));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11209, entity.getX(), entity.getBodyY(0.6), entity.getZ(), 2, 0.3, 0.3, 0.3, 0.0);
            }
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.circleOne);
            SpellVisualsManager.removeDisplays(this.circleTwo);
            SpellVisualsManager.removeDisplays(this.circleThree);
            SpellVisualsManager.removeDisplays(this.pillars);
            SpellVisualsManager.removeDisplays(this.axisMarkers);
            SpellVisualsManager.removeDisplays(this.beamDisplays);
            this.circleOne.clear();
            this.circleTwo.clear();
            this.circleThree.clear();
            this.pillars.clear();
            this.axisMarkers.clear();
            this.beamDisplays.clear();
            this.beamBlocks.clear();
        }
    }

    private static class GenesisState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> seedDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> circleDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> treeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> gardenDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> auraDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final Map<UUID, DeathRecord> resurrectionTargets = new HashMap<UUID, DeathRecord>();
        private boolean releaseTriggered = false;
        private boolean gardenActive = false;

        private GenesisState(ServerWorld world, PlayerEntity player) {
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 0.5, base.z);
            world.spawnEntity((Entity)this.marker);
            this.seedDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_28681.getDefaultState(), base, 0.2f));
            for (int i = 0; i < 24; ++i) {
                this.circleDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10566.getDefaultState(), base, 0.15f, 0.02f, 0.15f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 0.5, base.z);
            }
            if (this.tick < 1200) {
                this.updateSeed(base, this.tick);
                this.updateGenesisCircle(base, this.tick);
                this.updateGenesisTree(base, this.tick);
                if (this.tick % 40 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14653, SoundCategory.field_15248, 0.6f, 1.2f);
                }
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, base.x, base.y + 0.3, base.z, 2, 1.5, 0.2, 1.5, 0.0);
            } else if (this.tick < 1260) {
                if (!this.releaseTriggered) {
                    this.releaseTriggered = true;
                    this.triggerGenesisRelease(base);
                }
                this.updateGenesisGarden(base, this.tick - 1200);
            } else if (this.tick < 2460) {
                if (!this.gardenActive) {
                    this.gardenActive = true;
                }
                this.updateGenesisGarden(base, this.tick - 1200);
                if (this.tick % 20 == 0) {
                    this.applyGenesisHealing(base);
                }
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateSeed(Vec3d base, int tick) {
            float pulse = 1.0f + MathHelper.sin((float)((float)tick * 0.2f)) * 0.1f;
            for (DisplayEntity.class_8115 display : this.seedDisplays) {
                SpellVisualsManager.updateDisplay(display, base.add(0.0, 0.1, 0.0), 0.2f * pulse);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_17741, base.x, base.y + 0.1, base.z, 2, 0.6, 0.2, 0.6, 0.0);
        }

        private void updateGenesisCircle(Vec3d base, int tick) {
            float radius = 3.0f + (float)tick / 1200.0f * 12.0f;
            for (int i = 0; i < this.circleDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.circleDisplays.size()) + (float)tick * 5.0f);
                Vec3d pos = base.add(Math.cos(angle) * (double)radius, 0.02, Math.sin(angle) * (double)radius);
                SpellVisualsManager.updateDisplay(this.circleDisplays.get(i), pos, 0.15f, 0.02f, 0.15f);
            }
        }

        private void updateGenesisTree(Vec3d base, int tick) {
            int trunkCount = Math.min(40, tick / 15 + 1);
            while (this.treeDisplays.size() < trunkCount) {
                this.treeDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10431.getDefaultState(), base, 0.25f, 0.6f, 0.25f));
            }
            for (int i = 0; i < this.treeDisplays.size(); ++i) {
                Vec3d pos = base.add(0.0, (double)i * 0.6, 0.0);
                SpellVisualsManager.updateDisplay(this.treeDisplays.get(i), pos, 0.25f, 0.6f, 0.25f);
            }
            int leavesCount = Math.min(60, tick / 10 + 1);
            while (this.gardenDisplays.size() < leavesCount) {
                this.gardenDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_28673.getDefaultState(), base, 0.2f));
            }
            for (int i = 0; i < this.gardenDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.gardenDisplays.size()) + (float)tick * 4.0f);
                Vec3d pos = base.add(Math.cos(angle) * 5.0, 10.0 + Math.sin(angle * 2.0f) * 1.5, Math.sin(angle) * 5.0);
                SpellVisualsManager.updateDisplay(this.gardenDisplays.get(i), pos, 0.2f);
            }
        }

        private void triggerGenesisRelease(Vec3d base) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 30.0)) {
                float newHealth = Math.min(target.getMaxHealth(), target.getHealth() + 100.0f);
                target.setHealth(newHealth);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 600, 9, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 600, 9, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5914, 600, 4, false, false));
                SpellVisualsManager.clearNegativeEffects(target);
                GenesisBlessingState blessing = new GenesisBlessingState(this.world, target, false);
                GENESIS_BLESSINGS.put(target.getUuid(), blessing);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11220, target.getX(), target.getBodyY(0.8), target.getZ(), 40, 1.2, 0.8, 1.2, 0.0);
            }
            this.findGenesisResurrections(base);
            this.updateGenesisResurrections();
            this.world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14931, SoundCategory.field_15248, 1.2f, 1.1f);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11211, base.x, base.y + 1.0, base.z, 120, 6.0, 2.0, 6.0, 0.2);
        }

        private void updateGenesisGarden(Vec3d base, int localTick) {
            int count;
            int n = count = Nomorespell.isPerformanceMode() ? 120 : 240;
            while (this.auraDisplays.size() < count) {
                this.auraDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10157.getDefaultState(), base, 0.12f));
            }
            float radius = 30.0f;
            for (int i = 0; i < this.auraDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.auraDisplays.size()) + (float)localTick * 4.0f);
                float y = 0.4f + MathHelper.sin((float)((float)(localTick + i) * 0.2f)) * 0.6f;
                Vec3d pos = base.add(Math.cos(angle) * (double)radius, (double)y, Math.sin(angle) * (double)radius);
                SpellVisualsManager.updateDisplay(this.auraDisplays.get(i), pos, 0.12f);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.0, base.z, 12, 6.0, 1.0, 6.0, 0.0);
        }

        private void applyGenesisHealing(Vec3d base) {
            for (PlayerEntity target : SpellVisualsManager.getPlayersAround(this.world, base, 30.0)) {
                float newHealth = Math.min(target.getMaxHealth(), target.getHealth() + 4.0f);
                target.setHealth(newHealth);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 40, 9, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 200, 9, false, false));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5914, 200, 4, false, false));
                SpellVisualsManager.clearNegativeEffects(target);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11201, target.getX(), target.getBodyY(0.8), target.getZ(), 6, 0.6, 0.6, 0.6, 0.0);
            }
        }

        private void findGenesisResurrections(Vec3d base) {
            this.resurrectionTargets.clear();
            for (Map.Entry<UUID, DeathRecord> entry : DEATH_RECORDS.entrySet()) {
                DeathRecord record = entry.getValue();
                if (record == null || record.world != this.world || record.pos.distanceTo(base) > 40.0 || this.world.getTime() - record.time > 600L) continue;
                this.resurrectionTargets.put(entry.getKey(), record);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_23114, record.pos.x, record.pos.y + 0.2, record.pos.z, 12, 0.6, 0.4, 0.6, 0.0);
            }
        }

        private void updateGenesisResurrections() {
            if (this.resurrectionTargets.isEmpty()) {
                return;
            }
            for (Map.Entry<UUID, DeathRecord> entry : this.resurrectionTargets.entrySet()) {
                UUID id = entry.getKey();
                DeathRecord record = entry.getValue();
                ServerPlayerEntity player = this.world.getServer().getPlayerManager().getPlayer(id);
                if (player == null) continue;
                if (!player.isAlive()) {
                    player = this.world.getServer().getPlayerManager().respawnPlayer(player, false, Entity.class_5529.DISCARDED);
                }
                Vec3d respawnPos = SpellVisualsManager.clampGround(this.world, record.pos);
                player.teleport(this.world, respawnPos.x, respawnPos.y, respawnPos.z, Set.of(), player.getYaw(), player.getPitch(), true);
                player.setHealth(player.getMaxHealth());
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 600, 9, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 600, 9, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5914, 600, 4, false, false));
                SpellVisualsManager.clearNegativeEffects((PlayerEntity)player);
                SpellVisualsManager.applyInvulnerability(player, 200);
                GenesisBlessingState blessing = new GenesisBlessingState(this.world, (PlayerEntity)player, true);
                GENESIS_BLESSINGS.put(player.getUuid(), blessing);
            }
            this.resurrectionTargets.clear();
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.seedDisplays);
            SpellVisualsManager.removeDisplays(this.circleDisplays);
            SpellVisualsManager.removeDisplays(this.treeDisplays);
            SpellVisualsManager.removeDisplays(this.gardenDisplays);
            SpellVisualsManager.removeDisplays(this.auraDisplays);
            this.seedDisplays.clear();
            this.circleDisplays.clear();
            this.treeDisplays.clear();
            this.gardenDisplays.clear();
            this.auraDisplays.clear();
            this.resurrectionTargets.clear();
        }
    }

    private static class OmegaDominanceState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final MarkerEntity marker;
        private final List<DisplayEntity.class_8115> circles = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> towers = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> dome = new ArrayList<DisplayEntity.class_8115>();
        private boolean buffsApplied = false;

        private OmegaDominanceState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            this.marker = new MarkerEntity(EntityType.field_33456, (World)world);
            this.marker.setInvisible(true);
            this.marker.setNoGravity(true);
            this.marker.setPosition(base.x, base.y + 2.0, base.z);
            world.spawnEntity((Entity)this.marker);
            for (i = 0; i < 300; ++i) {
                this.circles.add(SpellVisualsManager.createDisplay(world, Blocks.field_10540.getDefaultState(), base, 0.14f, 0.02f, 0.14f));
            }
            for (i = 0; i < 400; ++i) {
                this.towers.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.3f, 1.0f, 0.3f));
            }
            for (i = 0; i < 200; ++i) {
                this.dome.add(SpellVisualsManager.createDisplay(world, Blocks.field_10399.getDefaultState(), base, 0.12f));
            }
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity caster = world.getPlayerByUuid(this.playerId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = caster.getPos();
            if (this.marker != null && this.marker.isAlive()) {
                this.marker.setPosition(base.x, base.y + 2.0, base.z);
            }
            if (this.tick < 1800) {
                this.updateOmegaCircles(base, this.tick);
                this.updateOmegaTowers(base, (float)this.tick / 1800.0f);
                this.imprisonOmegaEnemies(base, 50.0);
                if (this.tick % 40 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_38075, SoundCategory.field_15248, 0.8f, 0.6f);
                }
            } else if (this.tick < 1860) {
                if (!this.buffsApplied) {
                    this.buffsApplied = true;
                    this.applyOmegaEffects(base);
                }
                this.updateOmegaCircles(base, this.tick);
                this.updateOmegaTowers(base, 1.0f);
                this.updateOmegaDome(base, this.tick);
            } else if (this.tick < 3060) {
                this.updateOmegaCircles(base, this.tick);
                this.updateOmegaTowers(base, 1.0f);
                this.updateOmegaDome(base, this.tick);
                if (this.tick % 200 == 0) {
                    world.playSound(null, base.x, base.y, base.z, SoundEvents.field_14891, SoundCategory.field_15248, 0.8f, 0.7f);
                }
            } else if (this.tick < 3120) {
                if (this.tick == 3060) {
                    this.endOmegaDominion();
                }
                this.updateOmegaCircles(base, this.tick);
            } else {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateOmegaCircles(Vec3d base, int tick) {
            double[] radii = new double[]{40.0, 30.0, 20.0, 10.0, 5.0};
            int perRing = 60;
            int index = 0;
            for (int ring = 0; ring < radii.length; ++ring) {
                for (int i = 0; i < perRing && index < this.circles.size(); ++index, ++i) {
                    float angle = (float)Math.toRadians((float)i * (360.0f / (float)perRing) + (float)(tick * (8 + ring * 4)));
                    Vec3d pos = base.add(Math.cos(angle) * radii[ring], 0.05, Math.sin(angle) * radii[ring]);
                    SpellVisualsManager.updateDisplay(this.circles.get(index), pos, 0.14f, 0.02f, 0.14f);
                }
            }
        }

        private void updateOmegaTowers(Vec3d base, float progress) {
            int index = 0;
            for (int i = 0; i < 20; ++i) {
                float angle = (float)Math.toRadians((float)i * 18.0f);
                Vec3d pillarBase = base.add(Math.cos(angle) * 40.0, 0.2, Math.sin(angle) * 40.0);
                for (int j = 0; j < 20 && index < this.towers.size(); ++index, ++j) {
                    Vec3d pos = pillarBase.add(0.0, (double)j * 1.2 * (double)progress, 0.0);
                    SpellVisualsManager.updateDisplay(this.towers.get(index), pos, 0.3f, 1.0f, 0.3f);
                }
            }
        }

        private void updateOmegaDome(Vec3d base, int tick) {
            for (int i = 0; i < this.dome.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.dome.size()) + (float)tick * 4.0f);
                Vec3d pos = base.add(Math.cos(angle) * 40.0, 10.0 + Math.sin(angle) * 8.0, Math.sin(angle) * 40.0);
                SpellVisualsManager.updateDisplay(this.dome.get(i), pos, 0.12f);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11214, base.x, base.y + 10.0, base.z, 6, 16.0, 6.0, 16.0, 0.0);
        }

        private void imprisonOmegaEnemies(Vec3d base, double radius) {
            for (LivingEntity enemy : SpellVisualsManager.getLivingEntitiesAround(this.world, base, radius, living -> !(living instanceof PlayerEntity))) {
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 60, 9, false, true));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11231, enemy.getX(), enemy.getBodyY(0.5), enemy.getZ(), 3, 0.4, 0.4, 0.4, 0.0);
            }
        }

        private void applyOmegaEffects(Vec3d base) {
            for (PlayerEntity ally : SpellVisualsManager.getPlayersAround(this.world, base, 40.0)) {
                ServerPlayerEntity sp;
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 1200, 9, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 1200, 9, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5904, 1200, 4, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5898, 1200, 9, false, false));
                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5914, 1200, 9, false, false));
                SpellVisualsManager.applyInvulnerability(ally instanceof ServerPlayerEntity ? (sp = (ServerPlayerEntity)ally) : null, 200);
                OmegaChampionState champion = new OmegaChampionState(this.world, ally);
                OMEGA_CHAMPIONS.put(ally.getUuid(), champion);
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11248, ally.getX(), ally.getBodyY(0.8), ally.getZ(), 30, 1.0, 0.8, 1.0, 0.0);
            }
            for (LivingEntity enemy : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 40.0, living -> !(living instanceof PlayerEntity))) {
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, 1200, 9, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 1200, 9, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 1200, 9, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5919, 1200, 0, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5916, 1200, 0, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5920, 1200, 1, false, true));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 1200, 0, false, true));
                OmegaEnemyState state = new OmegaEnemyState(this.world, enemy);
                OMEGA_ENEMIES.put(enemy.getUuid(), state);
            }
        }

        private void endOmegaDominion() {
            for (OmegaChampionState champion : OMEGA_CHAMPIONS.values()) {
                if (champion == null) continue;
                champion.cleanup();
            }
            OMEGA_CHAMPIONS.clear();
            for (OmegaEnemyState enemyState : OMEGA_ENEMIES.values()) {
                if (enemyState == null) continue;
                enemyState.cleanup();
            }
            OMEGA_ENEMIES.clear();
        }

        private void cleanup() {
            if (this.marker != null) {
                this.marker.discard();
            }
            SpellVisualsManager.removeDisplays(this.circles);
            SpellVisualsManager.removeDisplays(this.towers);
            SpellVisualsManager.removeDisplays(this.dome);
            this.circles.clear();
            this.towers.clear();
            this.dome.clear();
        }
    }

    private static class BloodEclipseState {
        private static final int CIRCLE_FORM_TICKS = 60;
        private static final int LASER_TICKS = 100;
        private static final int FADE_TICKS = 20;
        private static final int TOTAL_TICKS = 180;
        private static final int DAMAGE_INTERVAL = 2;
        private static final float LASER_DAMAGE = 12.0f;
        private static final double CIRCLE_RADIUS = 3.0;
        private static final double LASER_RADIUS = 1.0;
        private static final double LASER_LENGTH = 20.0;
        private static final double LASER_HEIGHT = 1.5;
        private static final double FIRE_STEP = 0.45;
        private static final double FIRE_RADIUS_STEP = 0.4;
        private static final double SAFE_FIRE_RADIUS = 3.0;
        private final UUID casterId;
        private final ServerWorld world;
        private int tick;
        private boolean finished;

        private BloodEclipseState(ServerWorld world, PlayerEntity player) {
            this.world = world;
            this.casterId = player.getUuid();
        }

        private boolean tick(ServerWorld tickWorld) {
            if (tickWorld != this.world) {
                return false;
            }
            PlayerEntity caster = this.world.getPlayerByUuid(this.casterId);
            if (caster == null || !caster.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d center = caster.getPos();
            Vec3d direction = caster.getRotationVec(1.0f).normalize();
            if (this.tick == 0) {
                this.world.playSound(null, center.x, center.y, center.z, Nomorespell.DOMAIN_SPHERE_SOUND, SoundCategory.field_15248, 0.65f, 1.3f);
            }
            this.spawnCircleParticles(center);
            if (this.isLaserActive()) {
                this.applyLaserTick(caster, center, direction);
            }
            if (this.tick == 60) {
                this.world.playSound(null, center.x, center.y + 1.0, center.z, Nomorespell.BLOOD_ECLIPSE_BEAM_SOUND, SoundCategory.field_15248, 1.15f, 0.68f);
            }
            if (this.tick >= 180) {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void applyLaserTick(PlayerEntity caster, Vec3d center, Vec3d direction) {
            Vec3d origin = center.add(0.0, 1.0, 0.0);
            Vec3d end = origin.add(direction.multiply(20.0));
            Box area = new Box(origin, end).expand(2.5, 1.5, 2.5);
            for (LivingEntity target : this.world.getEntitiesByClass(LivingEntity.class, area, entity -> entity.isAlive() && entity != caster)) {
                PlayerEntity player;
                if (target instanceof PlayerEntity && (player = (PlayerEntity)target).isSpectator()) continue;
                Vec3d sample = target.getPos().add(0.0, (double)target.getHeight() * 0.5, 0.0);
                Vec3d closest = SpellVisualsManager.closestPointOnSegment(origin, end, sample);
                double horizontal = sample.subtract(closest).horizontalLength();
                double vertical = Math.abs(sample.y - closest.y);
                if (!(horizontal <= 1.0) || !(vertical <= 1.5)) continue;
                if ((this.tick - 60) % 2 == 0) {
                    CombatXpTracker.markSpellDamage(target, caster, "blood_eclipse");
                    target.damage(this.world, this.world.getDamageSources().magic(), 12.0f);
                }
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 6, 1, false, true));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5916, 6, 0, false, true));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, 6, 1, false, true));
                target.addVelocity((this.world.random.nextDouble() - 0.5) * 0.04, 0.01, (this.world.random.nextDouble() - 0.5) * 0.04);
                target.velocityModified = true;
                if (this.tick % 4 != 0) continue;
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11205, target.getX(), target.getBodyY(0.5), target.getZ(), 3, 0.25, 0.25, 0.25, 0.0);
            }
            this.igniteLaserBlocks(origin, direction);
            if (this.tick % 9 == 0) {
                this.world.playSound(null, center.x, center.y + 1.0, center.z, Nomorespell.BLOOD_ECLIPSE_BEAM_SOUND, SoundCategory.field_15248, 0.72f, 0.7f + this.world.random.nextFloat() * 0.08f);
            }
            if (this.tick % 3 == 0) {
                for (double d = 0.5; d <= 20.0; d += 1.5) {
                    Vec3d pos = origin.add(direction.multiply(d));
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, pos.x, pos.y + 0.05, pos.z, 1, 0.12, 0.12, 0.12, 0.0);
                    this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, pos.x, pos.y, pos.z, 1, 0.08, 0.08, 0.08, 0.0);
                }
            }
        }

        private void igniteLaserBlocks(Vec3d origin, Vec3d direction) {
            Vec3d dir = direction.normalize();
            Vec3d upReference = Math.abs(dir.y) > 0.92 ? new Vec3d(1.0, 0.0, 0.0) : new Vec3d(0.0, 1.0, 0.0);
            Vec3d right = dir.crossProduct(upReference).normalize();
            Vec3d up = right.crossProduct(dir).normalize();
            BlockPos.class_2339 mutable = new BlockPos.class_2339();
            HashSet<BlockPos> checked = new HashSet<BlockPos>();
            for (double step = 0.0; step <= 20.0; step += 0.45) {
                Vec3d point = origin.add(dir.multiply(step));
                for (double rx = -1.0; rx <= 1.0; rx += 0.4) {
                    for (double ry = -1.0; ry <= 1.0; ry += 0.4) {
                        Vec3d sample;
                        if (rx * rx + ry * ry > 1.0 || (sample = point.add(right.multiply(rx)).add(up.multiply(ry))).squaredDistanceTo(this.world.getPlayerByUuid(this.casterId).getPos()) <= 9.0) continue;
                        mutable.set(MathHelper.floor((double)sample.x), MathHelper.floor((double)sample.y), MathHelper.floor((double)sample.z));
                        BlockPos hitPos = mutable.toImmutable();
                        if (!checked.add(hitPos)) continue;
                        this.tryIgniteAt(hitPos);
                    }
                }
            }
        }

        private void tryIgniteAt(BlockPos hitPos) {
            BlockState hitState = this.world.getBlockState(hitPos);
            if (hitState.isAir()) {
                if (this.canPlaceFire(hitPos)) {
                    this.world.setBlockState(hitPos, Blocks.field_10036.getDefaultState(), 3);
                }
                return;
            }
            BlockPos above = hitPos.up();
            if (this.canPlaceFire(above) && !SpellVisualsManager.isBloodEclipseProtected(hitState)) {
                this.world.setBlockState(above, Blocks.field_10036.getDefaultState(), 3);
            }
        }

        private boolean canPlaceFire(BlockPos pos) {
            if (!this.world.getBlockState(pos).isAir()) {
                return false;
            }
            BlockPos below = pos.down();
            BlockState support = this.world.getBlockState(below);
            if (support.isAir()) {
                return false;
            }
            if (SpellVisualsManager.isBloodEclipseProtected(support)) {
                return false;
            }
            return Blocks.field_10036.getDefaultState().canPlaceAt((WorldView)this.world, pos);
        }

        private void spawnCircleParticles(Vec3d center) {
            if (!Nomorespell.shouldSpawnParticles(this.world, center)) {
                return;
            }
            float progress = Math.min(1.0f, (float)this.tick / 60.0f);
            double radius = 3.0 * (0.3 + 0.7 * (double)progress);
            for (int i = 0; i < 3; ++i) {
                double angle = (double)this.tick * 0.12 + Math.PI * 2 * (double)i / 3.0;
                double x = center.x + Math.cos(angle) * radius;
                double z = center.z + Math.sin(angle) * radius;
                double y = center.y + 0.08 + (double)(this.tick % 6) * 0.02;
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, x, y, z, 1, 0.0, 0.01, 0.0, 0.0);
            }
        }

        private boolean isLaserActive() {
            return this.tick >= 60 && this.tick < 160;
        }

        private float getIntensity() {
            if (this.tick < 60) {
                return MathHelper.clamp((float)((float)this.tick / 60.0f), (float)0.0f, (float)1.0f);
            }
            return 1.0f;
        }

        private float getFade() {
            if (this.tick < 160) {
                return 0.0f;
            }
            return MathHelper.clamp((float)((float)(this.tick - 160) / 20.0f), (float)0.0f, (float)1.0f);
        }

        private BloodEclipseRenderPayload createPayload() {
            PlayerEntity caster = this.world.getPlayerByUuid(this.casterId);
            Vec3d center = caster != null ? caster.getPos() : Vec3d.ZERO;
            Vec3d direction = caster != null ? caster.getRotationVec(1.0f).normalize() : new Vec3d(0.0, 0.0, 1.0);
            return new BloodEclipseRenderPayload(this.casterId, true, center.x, center.y, center.z, direction.x, direction.y, direction.z, this.tick, this.isLaserActive(), this.getIntensity(), this.getFade());
        }

        private void cleanup() {
            this.finished = true;
        }
    }

    private static class RainOfPicksState {
        private static final int DURATION_TICKS = 100;
        private static final int TOTAL_PROJECTILES = 30;
        private static final int MIN_FORCED_HOMING = 21;
        private static final float DAMAGE = 8.0f;
        private static final float AOE_DAMAGE = 5.0f;
        private static final int HEMORRHAGE_DURATION = 100;
        private static final double TARGET_SCAN_RADIUS = 15.0;
        private static final double AOE_RADIUS = 3.0;
        private static final double HITBOX_EXPANSION = 0.45;
        private static final double PROJECTILE_SPEED = 1.55;
        private static final double FINAL_CORRECTION_SPEED = 2.15;
        private static final double GRAVITY = 0.028;
        private final ServerWorld world;
        private final UUID playerId;
        private final int startAge;
        private final int durationTicks;
        private final int seed;
        private final int projectileCount;
        private final int homingProjectileCount;
        private final List<PickProjectileState> projectiles = new ArrayList<PickProjectileState>();
        private boolean finished;
        private int tick;

        private RainOfPicksState(ServerWorld world, PlayerEntity player, int requestedProjectileCount, int requestedHomingProjectileCount) {
            int deterministicHomingCount;
            this.world = world;
            this.playerId = player.getUuid();
            this.startAge = player.age;
            this.durationTicks = 100;
            this.seed = world.random.nextInt();
            this.projectileCount = requestedProjectileCount > 0 ? requestedProjectileCount : 30;
            List<LivingEntity> targets = SpellVisualsManager.collectPrioritizedTargets(world, player, 15.0);
            this.homingProjectileCount = deterministicHomingCount = targets.isEmpty() ? 0 : Math.min(this.projectileCount, Math.max(requestedHomingProjectileCount, Math.min(21, this.projectileCount)));
            this.createProjectiles(player, targets);
        }

        private boolean tick(ServerWorld world) {
            if (world != this.world) {
                return false;
            }
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d center = player.getPos();
            this.spawnGroundAura(world, center, this.tick);
            Iterator<PickProjectileState> iterator = this.projectiles.iterator();
            while (iterator.hasNext()) {
                PickProjectileState projectile = iterator.next();
                if (!projectile.tick(world, player, center, this.tick)) continue;
                iterator.remove();
            }
            ++this.tick;
            if (this.tick >= this.durationTicks) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void createProjectiles(PlayerEntity player, List<LivingEntity> targets) {
            Random random = new Random(this.seed);
            for (int i = 0; i < this.projectileCount; ++i) {
                double radius;
                double angle;
                LivingEntity assignedTarget;
                boolean forcedHoming = i < this.homingProjectileCount && !targets.isEmpty();
                LivingEntity class_13092 = assignedTarget = forcedHoming ? targets.get(i % targets.size()) : null;
                if (assignedTarget != null) {
                    Vec3d toTarget = assignedTarget.getPos().subtract(player.getPos());
                    angle = Math.atan2(toTarget.z, toTarget.x) + (i / targets.size() % 2 == 0 ? 0.2 : -0.2);
                    radius = 11.5 + (double)(i / Math.max(1, targets.size())) * 0.35;
                } else {
                    angle = random.nextDouble() * Math.PI * 2.0;
                    radius = 10.0 + random.nextDouble() * 5.0;
                }
                double zoneX = Math.cos(angle) * radius;
                double zoneZ = Math.sin(angle) * radius;
                Vec3d spawn = player.getPos().add(zoneX + (random.nextDouble() - 0.5) * 1.8, 15.0 + random.nextDouble() * 1.5, zoneZ + (random.nextDouble() - 0.5) * 1.8);
                int spawnDelay = Math.min(58, i * 2);
                this.projectiles.add(new PickProjectileState(this, i, spawnDelay, spawn, new Vec3d(zoneX, 0.0, zoneZ), random, assignedTarget == null ? null : assignedTarget.getUuid(), forcedHoming));
            }
        }

        private void spawnGroundAura(ServerWorld world, Vec3d center, int age) {
            for (int i = 0; i < 12; ++i) {
                double z;
                double angle = Math.PI * 2 * (double)i / 12.0 + (double)age * 0.035;
                double radius = 10.0 + (double)(i & 1) * 4.6;
                double x = center.x + Math.cos(angle) * radius;
                if (!Nomorespell.shouldSpawnParticles(world, new Vec3d(x, center.y + 0.05, z = center.z + Math.sin(angle) * radius))) continue;
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, x, center.y + 0.05, z, 1, 0.12, 0.02, 0.12, 0.0);
                if ((age + i) % 2 != 0) continue;
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, x, center.y + 0.08, z, 1, 0.06, 0.01, 0.06, 0.002);
            }
        }

        private void cleanup() {
            this.finished = true;
            this.projectiles.clear();
        }

        private class PickProjectileState {
            private final int index;
            private final int spawnDelay;
            private final Vec3d zoneOffset;
            private final Vec3d curveAxis;
            private Vec3d position;
            private Vec3d velocity;
            private UUID targetId;
            private final boolean homingEnabled;
            private boolean active;
            private boolean finished;

            private PickProjectileState(RainOfPicksState rainOfPicksState, int index, int spawnDelay, Vec3d spawnPos, Vec3d zoneOffset, Random random, UUID targetId, boolean homingEnabled) {
                this.index = index;
                this.spawnDelay = spawnDelay;
                this.position = spawnPos;
                this.zoneOffset = zoneOffset;
                this.targetId = targetId;
                Vec3d horizontal = new Vec3d(zoneOffset.x, 0.0, zoneOffset.z);
                Vec3d tangent = horizontal.lengthSquared() > 1.0E-4 ? new Vec3d(-horizontal.z, 0.0, horizontal.x).normalize() : new Vec3d(1.0, 0.0, 0.0);
                this.curveAxis = tangent.multiply((random.nextDouble() - 0.5) * 0.08);
                Vec3d initial = new Vec3d(-zoneOffset.x, -13.5, -zoneOffset.z).normalize().multiply(1.55);
                this.velocity = initial.add(this.curveAxis);
                this.homingEnabled = homingEnabled;
            }

            private boolean tick(ServerWorld world, PlayerEntity caster, Vec3d center, int age) {
                if (this.finished) {
                    return true;
                }
                if (age < this.spawnDelay) {
                    return false;
                }
                if (!this.active) {
                    this.active = true;
                    if (this.homingEnabled && this.targetId == null) {
                        this.reacquireTarget(world, caster, center);
                    }
                }
                LivingEntity target = this.getTrackedTarget(world, caster, center);
                Vec3d previous = this.position;
                if (target != null && this.homingEnabled) {
                    Vec3d targetPos = target.getPos().add(0.0, (double)target.getHeight() * 0.55, 0.0);
                    Vec3d direction = targetPos.subtract(this.position);
                    double distance = direction.length();
                    if (distance > 1.0E-4) {
                        double speed = distance <= 2.0 ? 2.15 : 1.55;
                        this.velocity = direction.normalize().multiply(speed);
                        this.velocity = this.velocity.add(0.0, -0.028, 0.0);
                    }
                } else {
                    this.velocity = this.velocity.add(this.curveAxis.multiply(0.08)).add(0.0, -0.028, 0.0);
                }
                double maxSpeed = 2.15;
                if (this.velocity.lengthSquared() > maxSpeed * maxSpeed) {
                    this.velocity = this.velocity.normalize().multiply(maxSpeed);
                }
                this.position = this.position.add(this.velocity);
                this.spawnTrailParticles(world, this.position, this.velocity, age);
                if (this.checkBlockCollision(world, caster, previous, this.position)) {
                    this.impact(world, caster, this.position, false, null);
                    this.finished = true;
                    return true;
                }
                LivingEntity hit = this.findHitTarget(world, caster, previous, this.position);
                if (hit != null) {
                    this.impactEntity(world, caster, hit);
                    this.impact(world, caster, this.position, true, hit);
                    this.finished = true;
                    return true;
                }
                if (this.position.y <= center.y - 1.0 || age - this.spawnDelay > 60) {
                    this.impact(world, caster, this.position, false, null);
                    this.finished = true;
                    return true;
                }
                return false;
            }

            private void reacquireTarget(ServerWorld world, PlayerEntity caster, Vec3d center) {
                LivingEntity target = SpellVisualsManager.findClosestTarget(world, caster, center, this.zoneOffset, 15.0);
                if (target == null) {
                    target = SpellVisualsManager.findClosestTarget(world, caster, center, this.zoneOffset, 20.0);
                }
                this.targetId = target == null ? null : target.getUuid();
            }

            private LivingEntity getTrackedTarget(ServerWorld world, PlayerEntity caster, Vec3d center) {
                LivingEntity living;
                Entity entity;
                if (this.targetId != null && (entity = world.getEntity(this.targetId)) instanceof LivingEntity && SpellVisualsManager.isValidRainTarget(caster, living = (LivingEntity)entity)) {
                    return living;
                }
                if (!this.homingEnabled) {
                    return null;
                }
                this.reacquireTarget(world, caster, center);
                if (this.targetId == null) {
                    return null;
                }
                entity = world.getEntity(this.targetId);
                return entity instanceof LivingEntity && SpellVisualsManager.isValidRainTarget(caster, living = (LivingEntity)entity) ? living : null;
            }

            private void spawnTrailParticles(ServerWorld world, Vec3d pos, Vec3d motion, int age) {
                double speed = motion.length();
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, pos.x, pos.y, pos.z, 1, 0.04, 0.04, 0.04, 0.002 + speed * 0.004);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, pos.x, pos.y, pos.z, 1, 0.02, 0.02, 0.02, 0.0);
                if ((age + this.index) % 2 == 0) {
                    world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, pos.x, pos.y, pos.z, 1, 0.05, 0.05, 0.05, 0.01);
                }
            }

            private boolean checkBlockCollision(ServerWorld world, PlayerEntity caster, Vec3d from, Vec3d to) {
                BlockHitResult hit = world.raycast(new RaycastContext(from, to, RaycastContext.class_3960.field_17558, RaycastContext.class_242.field_1348, (Entity)caster));
                return hit.getType() == HitResult.class_240.field_1332;
            }

            private LivingEntity findHitTarget(ServerWorld world, PlayerEntity caster, Vec3d from, Vec3d to) {
                Box box = new Box(from, to).expand(0.45);
                List hits = world.getEntitiesByClass(LivingEntity.class, box, entity -> SpellVisualsManager.isValidRainTarget(caster, entity));
                LivingEntity closest = null;
                double closestSq = Double.MAX_VALUE;
                for (LivingEntity entity2 : hits) {
                    double sq = entity2.getPos().squaredDistanceTo(this.position);
                    if (!(sq < closestSq)) continue;
                    closestSq = sq;
                    closest = entity2;
                }
                return closest;
            }

            private void impactEntity(ServerWorld world, PlayerEntity caster, LivingEntity target) {
                CombatXpTracker.markSpellDamage(target, caster, "rain_of_picks");
                target.damage(world, world.getDamageSources().indirectMagic((Entity)caster, (Entity)caster), 8.0f);
                target.addStatusEffect(new StatusEffectInstance(HEMORRHAGE_STATUS_EFFECT_ENTRY, 100, 0));
                target.addVelocity(0.0, 0.18, 0.0);
                target.velocityDirty = true;
            }

            private void impact(ServerWorld world, PlayerEntity caster, Vec3d pos, boolean entityHit, LivingEntity directTarget) {
                world.spawnParticles((ParticleEffect)ParticleTypes.field_17909, pos.x, pos.y, pos.z, 1, 0.0, 0.0, 0.0, 0.0);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11236, pos.x, pos.y, pos.z, entityHit ? 5 : 3, 0.18, 0.18, 0.18, 0.02);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_29644, pos.x, pos.y, pos.z, entityHit ? 8 : 5, 0.18, 0.18, 0.18, 0.04);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, pos.x, pos.y, pos.z, entityHit ? 10 : 6, 0.16, 0.16, 0.16, 0.01);
                SpellVisualsManager.spawnShockwaveParticles(world, pos, 3.0);
                this.applyAreaDamage(world, caster, pos, directTarget);
                world.playSound(null, pos.x, pos.y, pos.z, entityHit ? SoundEvents.field_14927 : SoundEvents.field_14785, SoundCategory.field_15248, 0.45f, entityHit ? 1.55f : 1.8f);
            }

            private void applyAreaDamage(ServerWorld world, PlayerEntity caster, Vec3d pos, LivingEntity directTarget) {
                Box aoe = Box.of((Vec3d)pos, (double)6.0, (double)6.0, (double)6.0);
                List hits = world.getEntitiesByClass(LivingEntity.class, aoe, entity -> entity.isAlive() && entity != caster && entity.squaredDistanceTo(pos) <= 9.25);
                for (LivingEntity entity2 : hits) {
                    PlayerEntity targetPlayer;
                    if (entity2 == caster || entity2 == directTarget || entity2 instanceof PlayerEntity && SpellVisualsManager.areAllied(caster, targetPlayer = (PlayerEntity)entity2)) continue;
                    CombatXpTracker.markSpellDamage(entity2, caster, "rain_of_picks");
                    entity2.damage(world, world.getDamageSources().indirectMagic((Entity)caster, (Entity)caster), 5.0f);
                }
            }
        }
    }

    private static class DeathRecord {
        private final ServerWorld world;
        private final Vec3d pos;
        private final long time;

        private DeathRecord(ServerWorld world, Vec3d pos, long time) {
            this.world = world;
            this.pos = pos;
            this.time = time;
        }
    }

    private static class DomainExpansionState {
        private static final int TOTAL_TICKS = 780;
        private static final int SPHERE_END = 40;
        private static final int PLATFORM_START = 41;
        private static final int PLATFORM_END = 80;
        private static final int DOME_START = 81;
        private static final int DOME_END = 120;
        private static final int ACTIVE_START = 121;
        private static final int ACTIVE_END = 749;
        private static final int FADE_START = 750;
        private static final int IMMOBILIZE_END = 100;
        private static final int PULSE_INTERVAL = 180;
        private static final int TREMOR_DURATION = 20;
        private static final double RADIUS = 25.0;
        private static final int SPHERE_SEGMENTS = 64;
        private final UUID casterId;
        private final ServerWorld world;
        private final Vec3d center;
        private final ServerBossBar bossBar;
        private final List<UUID> targetIds = new ArrayList<UUID>();
        private final Set<UUID> killedEscapers = new HashSet<UUID>();
        private final List<DisplayEntity.class_8115> sphereDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec2f> sphereAngles = new ArrayList<Vec2f>();
        private final List<DisplayEntity.class_8115> domeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec2f> domeAngles = new ArrayList<Vec2f>();
        private final List<DisplayEntity.class_8115> platformDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> platformOffsets = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> decorationDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<Vec3d> decorationOffsets = new ArrayList<Vec3d>();
        private final List<DisplayEntity.class_8115> orbitLights = new ArrayList<DisplayEntity.class_8115>();
        private final List<DomainGuardianEntity> guardians = new ArrayList<DomainGuardianEntity>();
        private int tick;
        private int contestTicks = -1;
        private int theme;
        private boolean finished;

        private DomainExpansionState(ServerWorld world, PlayerEntity caster, List<LivingEntity> initialTargets) {
            this.world = world;
            this.casterId = caster.getUuid();
            this.center = caster.getPos();
            this.bossBar = new ServerBossBar((Text)Text.literal((String)"Domain Expansion"), BossBar.class_1260.field_5783, BossBar.class_1261.field_5795);
            this.bossBar.addPlayer((ServerPlayerEntity)caster);
            this.theme = world.random.nextInt(3);
            for (LivingEntity target : initialTargets) {
                if (target == null || !target.isAlive() || target.getUuid().equals(this.casterId)) continue;
                this.targetIds.add(target.getUuid());
                if (!(target instanceof ServerPlayerEntity)) continue;
                ServerPlayerEntity player = (ServerPlayerEntity)target;
                this.bossBar.addPlayer(player);
            }
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 600, 1, false, true));
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 600, 2, false, true));
        }

        private boolean tick(ServerWorld world) {
            ServerPlayerEntity serverCaster;
            block7: {
                block6: {
                    if (this.world != world) {
                        return false;
                    }
                    PlayerEntity caster = world.getPlayerByUuid(this.casterId);
                    if (!(caster instanceof ServerPlayerEntity)) break block6;
                    serverCaster = (ServerPlayerEntity)caster;
                    if (caster.isAlive() && !serverCaster.isDisconnected()) break block7;
                }
                this.cleanup(false);
                return true;
            }
            if (this.contestTicks >= 0 && --this.contestTicks <= 0) {
                this.cleanup(false);
                return true;
            }
            this.updateBossBar();
            this.syncBossBarPlayers();
            this.updateTargetsAndContainment(serverCaster);
            this.applyCasterProtection(serverCaster);
            this.applyPhaseVisuals(serverCaster);
            if (this.tick >= 780) {
                this.cleanup(true);
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateBossBar() {
            int activeRemaining = Math.max(0, 750 - Math.max(this.tick, 121));
            float remaining = (float)Math.max(0, 780 - this.tick) / 780.0f;
            this.bossBar.setPercent(remaining);
            this.bossBar.setName((Text)Text.literal((String)("Domain Expansion - " + (activeRemaining + 19) / 20 + "s")));
        }

        private void syncBossBarPlayers() {
            for (UUID targetId : new ArrayList<UUID>(this.targetIds)) {
                ServerPlayerEntity serverPlayer;
                Entity entity = this.world.getEntity(targetId);
                if (!(entity instanceof ServerPlayerEntity) || !(serverPlayer = (ServerPlayerEntity)entity).isAlive() || serverPlayer.isDisconnected()) continue;
                this.bossBar.addPlayer(serverPlayer);
            }
        }

        private void updateTargetsAndContainment(ServerPlayerEntity caster) {
            LivingEntity living;
            Entity entity;
            this.targetIds.removeIf(targetId -> {
                LivingEntity living;
                Entity entity = this.world.getEntity(targetId);
                return !(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive();
            });
            for (UUID targetId2 : this.targetIds) {
                ServerPlayerEntity player;
                entity = this.world.getEntity(targetId2);
                if (!(entity instanceof LivingEntity)) continue;
                living = (LivingEntity)entity;
                if (this.tick <= 100 || this.isTremorWindow()) {
                    this.immobilize(living);
                }
                if (this.tick == 41) {
                    this.teleportCapturedEntity(living);
                }
                this.applyGlow(living, caster);
                if (this.tick < 121 || !(living instanceof ServerPlayerEntity) || SpellVisualsManager.areAllied((PlayerEntity)caster, (PlayerEntity)(player = (ServerPlayerEntity)living)) || !(player.squaredDistanceTo(this.center) > 625.0) || !this.killedEscapers.add(player.getUuid())) continue;
                this.dropInventoryToCaster(player, caster);
                player.damage(this.world, this.world.getDamageSources().magic(), 1000.0f);
            }
            if (this.tick >= 121 && (this.tick - 121) % 180 == 0) {
                for (UUID targetId2 : this.targetIds) {
                    entity = this.world.getEntity(targetId2);
                    if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) continue;
                    living.damage(this.world, this.world.getDamageSources().magic(), 6.0f);
                }
            }
        }

        private void applyCasterProtection(ServerPlayerEntity caster) {
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 30, 0, false, false));
            for (ServerPlayerEntity other : this.world.getPlayers()) {
                if (!SpellVisualsManager.areAllied((PlayerEntity)caster, (PlayerEntity)other) || !(other.squaredDistanceTo(this.center) <= 625.0)) continue;
                other.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 30, 4, false, false));
                other.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 30, 0, false, false));
            }
        }

        private void applyPhaseVisuals(ServerPlayerEntity caster) {
            if (this.tick == 0) {
                this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_EXPANSION_SOUND, SoundCategory.field_15248, 0.8f, 0.85f);
            }
            if (this.tick <= 40) {
                this.updateSphereGrowth();
                this.updateDecorations();
                this.spawnAmbientSphereParticles();
            } else if (this.tick <= 80) {
                this.ensurePlatform();
                this.updatePlatformGrowth();
                this.updateDecorations();
                if (this.tick == 41) {
                    this.teleportCasterToEdge(caster);
                    this.spawnGuardians(caster);
                    this.world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_26980, SoundCategory.field_15248, 1.0f, 1.1f);
                }
            } else if (this.tick <= 120) {
                this.ensurePlatform();
                this.ensureDome();
                this.ensureOrbitLights();
                this.updateDome();
                this.updateOrbitLights();
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, this.center.x, this.center.y + 2.0, this.center.z, 2, 2.0, 1.0, 2.0, 0.0);
            } else if (this.tick <= 749) {
                this.ensurePlatform();
                this.ensureDome();
                this.ensureOrbitLights();
                this.updateDome();
                this.updateOrbitLights();
                this.updateGuardians(caster);
                if ((this.tick - 121) % 180 == 0) {
                    this.pulseOrbitLights();
                }
            } else {
                this.fadeDisplays();
            }
        }

        private void updateSphereGrowth() {
            if (this.sphereDisplays.isEmpty()) {
                for (int i = 0; i < 64; ++i) {
                    for (int j = 1; j < 32; j += 4) {
                        this.sphereDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_27115.getDefaultState(), this.center, 0.05f));
                        this.sphereAngles.add(new Vec2f((float)(Math.PI * 2 * (double)i / 64.0), (float)(Math.PI * (double)j / 32.0)));
                    }
                }
                this.createDecorations();
            }
            double growth = Math.min(1.0, (double)this.tick / 40.0);
            double radius = 25.0 * growth;
            float xRot = this.tick;
            float yRot = (float)this.tick * 1.5f;
            for (int i = 0; i < this.sphereDisplays.size(); ++i) {
                Vec2f angle = this.sphereAngles.get(i);
                Vec3d rotated = this.spherePoint(radius, angle.x, angle.y, xRot, yRot);
                SpellVisualsManager.updateDisplay(this.sphereDisplays.get(i), this.center.add(rotated), 0.05f);
            }
        }

        private Vec3d spherePoint(double radius, double theta, double phi, float xRotDeg, float yRotDeg) {
            double x = radius * Math.sin(phi) * Math.cos(theta);
            double y = radius * Math.cos(phi);
            double z = radius * Math.sin(phi) * Math.sin(theta);
            Quaternionf rotation = new Quaternionf().rotateX((float)Math.toRadians(xRotDeg)).rotateY((float)Math.toRadians(yRotDeg));
            Vector3f vec = new Vector3f((float)x, (float)y, (float)z).rotate((Quaternionfc)rotation);
            return new Vec3d((double)vec.x, (double)vec.y, (double)vec.z);
        }

        private void createDecorations() {
            if (!this.decorationDisplays.isEmpty()) {
                return;
            }
            if (this.theme == 0) {
                for (int i = 0; i < 12; ++i) {
                    double angle = Math.PI * 2 * (double)i / 12.0;
                    this.decorationOffsets.add(new Vec3d(Math.cos(angle) * 8.0, (double)(i % 3) * 1.5 - 2.0, Math.sin(angle) * 8.0));
                    this.decorationDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10540.getDefaultState(), this.center, 0.2f));
                }
            } else if (this.theme == 1) {
                for (int i = 0; i < 10; ++i) {
                    double angle = Math.PI * 2 * (double)i / 10.0;
                    this.decorationOffsets.add(new Vec3d(Math.cos(angle) * 10.0, -2.5 + (double)(i % 4), Math.sin(angle) * 10.0));
                    this.decorationDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_23869.getDefaultState(), this.center, 0.28f));
                }
            } else {
                for (int x = -3; x <= 3; ++x) {
                    for (int z = -3; z <= 3; ++z) {
                        this.decorationOffsets.add(new Vec3d((double)x * 2.0, -3.0 + (double)(x + z & 1), (double)z * 2.0));
                        this.decorationDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_37568.getDefaultState(), this.center, 0.18f));
                    }
                }
            }
        }

        private void updateDecorations() {
            for (int i = 0; i < this.decorationDisplays.size(); ++i) {
                SpellVisualsManager.updateDisplay(this.decorationDisplays.get(i), this.center.add(this.decorationOffsets.get(i)), this.getDisplayScale(this.decorationDisplays.get(i)));
            }
        }

        private void spawnAmbientSphereParticles() {
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11207, this.center.x, this.center.y + 1.5, this.center.z, 2, 8.0, 4.0, 8.0, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, this.center.x, this.center.y + 0.5, this.center.z, 3, 7.0, 2.0, 7.0, 0.0);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, this.center.x, this.center.y + 1.0, this.center.z, 2, 7.0, 3.0, 7.0, 0.0);
        }

        private void ensurePlatform() {
            if (!this.platformDisplays.isEmpty()) {
                return;
            }
            for (int x = -4; x <= 4; ++x) {
                for (int z = -4; z <= 4; ++z) {
                    this.platformOffsets.add(new Vec3d((double)x, -1.0, (double)z));
                    this.platformDisplays.add(SpellVisualsManager.createDisplay(this.world, ((Math.abs(x) + Math.abs(z)) % 2 == 0 ? Blocks.field_23873 : Blocks.field_10540).getDefaultState(), this.center, 0.45f));
                }
            }
        }

        private void updatePlatformGrowth() {
            float phaseProgress = MathHelper.clamp((float)((float)(this.tick - 41) / 40.0f), (float)0.0f, (float)1.0f);
            for (int i = 0; i < this.platformDisplays.size(); ++i) {
                Vec3d offset = this.platformOffsets.get(i);
                Vec3d target = this.center.add(offset.x, offset.y, offset.z);
                Vec3d start = this.center.add(offset.x, -6.0, offset.z);
                Vec3d pos = start.lerp(target, (double)phaseProgress);
                SpellVisualsManager.updateDisplay(this.platformDisplays.get(i), pos, 0.45f);
            }
        }

        private void ensureDome() {
            if (!this.domeDisplays.isEmpty()) {
                return;
            }
            for (int i = 0; i < 64; i += 2) {
                for (int j = 4; j < 32; j += 6) {
                    this.domeDisplays.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_9997.getDefaultState(), this.center, 0.08f));
                    this.domeAngles.add(new Vec2f((float)(Math.PI * 2 * (double)i / 64.0), (float)(Math.PI * (double)j / 32.0)));
                }
            }
        }

        private void updateDome() {
            float phaseProgress = MathHelper.clamp((float)((float)(this.tick - 81) / 40.0f), (float)0.0f, (float)1.0f);
            float yRot = (float)this.tick * 0.5f;
            for (int i = 0; i < this.domeDisplays.size(); ++i) {
                Vec2f angle = this.domeAngles.get(i);
                Vec3d pos = this.spherePoint(25.0 * (double)phaseProgress, angle.x, angle.y, 0.0f, yRot);
                SpellVisualsManager.updateDisplay(this.domeDisplays.get(i), this.center.add(pos), 0.08f);
            }
        }

        private void ensureOrbitLights() {
            if (!this.orbitLights.isEmpty()) {
                return;
            }
            for (int i = 0; i < 8; ++i) {
                this.orbitLights.add(SpellVisualsManager.createDisplay(this.world, Blocks.field_10455.getDefaultState(), this.center, 0.05f));
            }
        }

        private void updateOrbitLights() {
            for (int i = 0; i < this.orbitLights.size(); ++i) {
                double angle = Math.PI * 2 * (double)i / (double)this.orbitLights.size() + Math.toRadians((double)this.tick * 2.5);
                double y = 2.0 + Math.sin((double)(this.tick + i * 12) * 0.08) * 1.5;
                float scale = this.tick >= 121 && (this.tick - 121) % 180 < 10 ? 0.075f : 0.05f;
                SpellVisualsManager.updateDisplay(this.orbitLights.get(i), this.center.add(Math.cos(angle) * 6.5, y, Math.sin(angle) * 6.5), scale);
            }
        }

        private void pulseOrbitLights() {
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, this.center.x, this.center.y + 2.0, this.center.z, 10, 2.0, 1.0, 2.0, 0.0);
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_26944, SoundCategory.field_15248, 0.5f, 1.5f);
        }

        private void spawnGuardians(ServerPlayerEntity caster) {
            if (!this.guardians.isEmpty()) {
                return;
            }
            for (int i = 0; i < 6; ++i) {
                double angle = Math.PI * 2 * (double)i / 6.0;
                Vec3d pos = this.center.add(Math.cos(angle) * 8.5, 1.0, Math.sin(angle) * 8.5);
                DomainGuardianEntity guardian = new DomainGuardianEntity(this.world, this.casterId);
                guardian.refreshPositionAndAngles(pos.x, pos.y, pos.z, (float)Math.toDegrees(angle) + 180.0f, 0.0f);
                this.world.spawnEntity((Entity)guardian);
                this.guardians.add(guardian);
            }
        }

        private void updateGuardians(ServerPlayerEntity caster) {
            this.guardians.removeIf(guardian -> guardian == null || !guardian.isAlive());
            for (DomainGuardianEntity guardian2 : this.guardians) {
                LivingEntity target = this.findGuardianTarget(caster, guardian2.getPos());
                if (target == null) continue;
                guardian2.setTarget(target);
            }
        }

        private LivingEntity findGuardianTarget(ServerPlayerEntity caster, Vec3d guardianPos) {
            LivingEntity best = null;
            double bestDistance = Double.MAX_VALUE;
            for (UUID targetId : this.targetIds) {
                double distance;
                PlayerEntity player;
                LivingEntity living;
                Entity entity = this.world.getEntity(targetId);
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || living instanceof PlayerEntity && SpellVisualsManager.areAllied((PlayerEntity)caster, player = (PlayerEntity)living) || !((distance = living.squaredDistanceTo(guardianPos)) < bestDistance)) continue;
                bestDistance = distance;
                best = living;
            }
            return best;
        }

        private void applyGlow(LivingEntity living, ServerPlayerEntity caster) {
            PlayerEntity player;
            living.setGlowing(true);
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 30, 0, false, false));
            if (living instanceof PlayerEntity && SpellVisualsManager.areAllied((PlayerEntity)caster, player = (PlayerEntity)living)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 30, 4, false, false));
            }
        }

        private void teleportCapturedEntity(LivingEntity entity) {
            double offsetX = (this.world.random.nextDouble() - 0.5) * 6.0;
            double offsetZ = (this.world.random.nextDouble() - 0.5) * 6.0;
            entity.teleport(this.center.x + offsetX, this.center.y + 1.0, this.center.z + offsetZ, false);
        }

        private void teleportCasterToEdge(ServerPlayerEntity caster) {
            caster.teleport(this.center.x + 25.0 - 2.0, this.center.y + 1.0, this.center.z, false);
        }

        private void immobilize(LivingEntity living) {
            living.setVelocity(0.0, 0.0, 0.0);
            living.velocityDirty = true;
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, 25, 10, false, false));
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11208, living.getX(), living.getBodyY(0.5), living.getZ(), 3, 0.2, 0.3, 0.2, 0.0);
        }

        private boolean isTremorWindow() {
            if (this.tick < 121) {
                return false;
            }
            int phaseTick = this.tick - 121;
            return phaseTick % 180 < 20;
        }

        private void dropInventoryToCaster(ServerPlayerEntity escaped, ServerPlayerEntity caster) {
            for (int i = 0; i < escaped.getInventory().size(); ++i) {
                ItemStack stack = escaped.getInventory().getStack(i);
                if (stack.isEmpty()) continue;
                if (!caster.getInventory().insertStack(stack.copy())) {
                    ItemScatterer.spawn((World)this.world, (double)caster.getX(), (double)caster.getY(), (double)caster.getZ(), (ItemStack)stack.copy());
                }
                escaped.getInventory().setStack(i, ItemStack.EMPTY);
            }
            escaped.currentScreenHandler.sendContentUpdates();
            caster.currentScreenHandler.sendContentUpdates();
        }

        private void fadeDisplays() {
            float scale = Math.max(0.0f, 0.05f * (1.0f - (float)(this.tick - 750) / 30.0f));
            for (DisplayEntity.class_8115 display : this.sphereDisplays) {
                SpellVisualsManager.updateDisplay(display, display.getPos(), scale);
            }
            for (DisplayEntity.class_8115 display : this.domeDisplays) {
                SpellVisualsManager.updateDisplay(display, display.getPos(), scale);
            }
            for (DisplayEntity.class_8115 display : this.orbitLights) {
                SpellVisualsManager.updateDisplay(display, display.getPos(), scale);
            }
            for (DisplayEntity.class_8115 display : this.platformDisplays) {
                SpellVisualsManager.updateDisplay(display, display.getPos(), Math.max(0.0f, 0.45f * (1.0f - (float)(this.tick - 750) / 30.0f)));
            }
        }

        private float getDisplayScale(DisplayEntity.class_8115 display) {
            return 0.18f;
        }

        private void startContestOverride() {
            this.contestTicks = 240;
        }

        private void cleanup(boolean success) {
            this.finished = true;
            this.bossBar.clearPlayers();
            this.sphereDisplays.removeIf(display -> {
                SpellVisualsManager.removeDisplay(display);
                return true;
            });
            this.domeDisplays.removeIf(display -> {
                SpellVisualsManager.removeDisplay(display);
                return true;
            });
            this.platformDisplays.removeIf(display -> {
                SpellVisualsManager.removeDisplay(display);
                return true;
            });
            this.decorationDisplays.removeIf(display -> {
                SpellVisualsManager.removeDisplay(display);
                return true;
            });
            this.orbitLights.removeIf(display -> {
                SpellVisualsManager.removeDisplay(display);
                return true;
            });
            this.guardians.removeIf(guardian -> {
                if (guardian != null && guardian.isAlive()) {
                    guardian.discard();
                }
                return true;
            });
            DOMAIN_EXPANSIONS.remove(this.casterId);
        }
    }

    @FunctionalInterface
    private static interface WorldTickState<T> {
        public boolean tick(T var1, ServerWorld var2);
    }

    private static class OmegaEnemyState {
        private final UUID entityId;
        private final ServerWorld world;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> chains = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;

        private OmegaEnemyState(ServerWorld world, LivingEntity entity) {
            this.world = world;
            this.entityId = entity.getUuid();
            Vec3d base = entity.getPos();
            for (int i = 0; i < 12; ++i) {
                this.chains.add(SpellVisualsManager.createDisplay(world, Blocks.field_23985.getDefaultState(), base, 0.06f, 0.6f, 0.06f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u2620", 8141549, base, 0.3f);
        }

        private boolean tick(ServerWorld world) {
            LivingEntity living;
            LivingEntity entity;
            Entity class_12972 = world.getEntity(this.entityId);
            LivingEntity class_13092 = entity = class_12972 instanceof LivingEntity ? (living = (LivingEntity)class_12972) : null;
            if (entity == null || !entity.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = entity.getPos();
            for (int i = 0; i < this.chains.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.chains.size()) + (float)this.tick * 5.0f);
                Vec3d pos = base.add(Math.cos(angle) * 1.1, 0.2, Math.sin(angle) * 1.1);
                SpellVisualsManager.updateDisplay(this.chains.get(i), pos, 0.06f, 0.6f, 0.06f);
            }
            this.mark.setPosition(base.x, base.y + 2.2, base.z);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, base.x, base.y + 0.5, base.z, 2, 0.3, 0.3, 0.3, 0.0);
            ++this.tick;
            if (this.tick >= 1200) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.chains);
            if (this.mark != null) {
                this.mark.discard();
            }
            this.chains.clear();
        }
    }

    private static class OmegaChampionState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> armor = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> wings = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;
        private final DisplayEntity.class_8122 weapon;

        private OmegaChampionState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            for (i = 0; i < 30; ++i) {
                this.armor.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.24f));
            }
            for (i = 0; i < 24; ++i) {
                this.wings.add(SpellVisualsManager.createDisplay(world, Blocks.field_10271.getDefaultState(), base, 0.1f, 0.28f, 0.1f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u03a9", 14202110, base, 0.4f);
            this.weapon = SpellVisualsManager.createItemDisplay(world, new ItemStack((ItemConvertible)Items.field_22022), base, 2.0f);
        }

        private boolean tick(ServerWorld world) {
            Vec3d pos;
            float angle;
            int i;
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            for (i = 0; i < this.armor.size(); ++i) {
                angle = (float)Math.toRadians((float)i * (360.0f / (float)this.armor.size()) + (float)this.tick * 6.0f);
                pos = base.add(Math.cos(angle) * 1.3, 0.9 + Math.sin(angle * 2.0f) * 0.2, Math.sin(angle) * 1.3);
                SpellVisualsManager.updateDisplay(this.armor.get(i), pos, 0.24f);
            }
            for (i = 0; i < this.wings.size(); ++i) {
                angle = (float)Math.toRadians((double)((float)(i - 12) * 12.0f) + Math.sin((double)this.tick * 0.2) * 20.0);
                pos = base.add(Math.cos(angle) * 1.1, 1.5 + Math.sin((double)this.tick * 0.2) * 0.2, Math.sin(angle) * 0.8);
                SpellVisualsManager.updateDisplay(this.wings.get(i), pos, 0.1f, 0.28f, 0.1f);
            }
            this.mark.setPosition(base.x, base.y + 2.5, base.z);
            SpellVisualsManager.updateItemDisplay(this.weapon, base.add(0.8, 1.1 + Math.sin((double)this.tick * 0.2) * 0.1, 0.0), 2.0f);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 2, 0.4, 0.4, 0.4, 0.0);
            ++this.tick;
            if (this.tick >= 1200) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.armor);
            SpellVisualsManager.removeDisplays(this.wings);
            if (this.mark != null) {
                this.mark.discard();
            }
            if (this.weapon != null) {
                this.weapon.discard();
            }
            this.armor.clear();
            this.wings.clear();
        }
    }

    private static class GenesisBlessingState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final boolean resurrected;
        private final List<DisplayEntity.class_8115> halo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> wings = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;

        private GenesisBlessingState(ServerWorld world, PlayerEntity player, boolean resurrected) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            this.resurrected = resurrected;
            for (i = 0; i < 12; ++i) {
                this.halo.add(SpellVisualsManager.createDisplay(world, Blocks.field_10205.getDefaultState(), player.getPos(), 0.12f, 0.02f, 0.12f));
            }
            for (i = 0; i < 20; ++i) {
                this.wings.add(SpellVisualsManager.createDisplay(world, Blocks.field_28673.getDefaultState(), player.getPos(), 0.1f, 0.25f, 0.1f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u273f", 8843180, player.getPos(), 0.3f);
        }

        private boolean tick(ServerWorld world) {
            Vec3d pos;
            float angle;
            int i;
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            for (i = 0; i < this.halo.size(); ++i) {
                angle = (float)Math.toRadians((float)i * (360.0f / (float)this.halo.size()) + (float)this.tick * 6.0f);
                pos = base.add(Math.cos(angle) * 0.8, 2.4, Math.sin(angle) * 0.8);
                SpellVisualsManager.updateDisplay(this.halo.get(i), pos, 0.12f, 0.02f, 0.12f);
            }
            if (this.resurrected) {
                for (i = 0; i < this.wings.size(); ++i) {
                    angle = (float)Math.toRadians((double)((float)(i - 10) * 8.0f) + Math.sin((double)this.tick * 0.2) * 15.0);
                    pos = base.add(Math.cos(angle) * 1.1, 1.4 + Math.sin((double)this.tick * 0.2) * 0.2, Math.sin(angle) * 0.8);
                    SpellVisualsManager.updateDisplay(this.wings.get(i), pos, 0.1f, 0.25f, 0.1f);
                }
            }
            this.mark.setPosition(base.x, base.y + 2.3, base.z);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.4, base.z, 2, 0.4, 0.4, 0.4, 0.0);
            ++this.tick;
            if (this.tick >= 600) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.halo);
            SpellVisualsManager.removeDisplays(this.wings);
            if (this.mark != null) {
                this.mark.discard();
            }
            this.halo.clear();
            this.wings.clear();
        }
    }

    private static class DominionEnemyState {
        private final UUID entityId;
        private final ServerWorld world;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> chains = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;

        private DominionEnemyState(ServerWorld world, LivingEntity entity) {
            this.world = world;
            this.entityId = entity.getUuid();
            Vec3d base = entity.getPos();
            for (int i = 0; i < 8; ++i) {
                this.chains.add(SpellVisualsManager.createDisplay(world, Blocks.field_23985.getDefaultState(), base, 0.06f, 0.4f, 0.06f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u2716", 0xEF4444, base, 0.3f);
        }

        private boolean tick(ServerWorld world) {
            LivingEntity living;
            LivingEntity entity;
            Entity class_12972 = world.getEntity(this.entityId);
            LivingEntity class_13092 = entity = class_12972 instanceof LivingEntity ? (living = (LivingEntity)class_12972) : null;
            if (entity == null || !entity.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = entity.getPos();
            for (int i = 0; i < this.chains.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.chains.size()) + (float)this.tick * 5.0f);
                Vec3d pos = base.add(Math.cos(angle) * 0.8, 0.2, Math.sin(angle) * 0.8);
                SpellVisualsManager.updateDisplay(this.chains.get(i), pos, 0.06f, 0.4f, 0.06f);
            }
            this.mark.setPosition(base.x, base.y + 2.1, base.z);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_22247, base.x, base.y + 0.5, base.z, 2, 0.3, 0.3, 0.3, 0.0);
            ++this.tick;
            if (this.tick >= 600) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.chains);
            if (this.mark != null) {
                this.mark.discard();
            }
            this.chains.clear();
        }
    }

    private static class DominionChampionState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> armor = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> cape = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;
        private final DisplayEntity.class_8122 weapon;

        private DominionChampionState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            for (i = 0; i < 28; ++i) {
                this.armor.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.2f));
            }
            for (i = 0; i < 12; ++i) {
                this.cape.add(SpellVisualsManager.createDisplay(world, Blocks.field_10368.getDefaultState(), base, 0.15f, 0.3f, 0.15f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u26a1", 11032055, base, 0.35f);
            this.weapon = SpellVisualsManager.createItemDisplay(world, new ItemStack((ItemConvertible)Items.field_22022), base, 1.8f);
        }

        private boolean tick(ServerWorld world) {
            Vec3d pos;
            int i;
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            for (i = 0; i < this.armor.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.armor.size()) + (float)this.tick * 6.0f);
                pos = base.add(Math.cos(angle) * 1.1, 0.8 + Math.sin(angle * 2.0f) * 0.2, Math.sin(angle) * 1.1);
                SpellVisualsManager.updateDisplay(this.armor.get(i), pos, 0.2f);
            }
            for (i = 0; i < this.cape.size(); ++i) {
                float offset = (float)(i - 6) * 0.15f;
                pos = base.add(0.2, 1.0 + (double)offset, -0.6 + Math.sin((double)this.tick * 0.2 + (double)i) * 0.1);
                SpellVisualsManager.updateDisplay(this.cape.get(i), pos, 0.15f, 0.3f, 0.15f);
            }
            this.mark.setPosition(base.x, base.y + 2.3, base.z);
            SpellVisualsManager.updateItemDisplay(this.weapon, base.add(0.8, 1.0 + Math.sin((double)this.tick * 0.2) * 0.1, 0.0), 1.8f);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.2, base.z, 2, 0.3, 0.3, 0.3, 0.0);
            ++this.tick;
            if (this.tick >= 600) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.armor);
            SpellVisualsManager.removeDisplays(this.cape);
            if (this.mark != null) {
                this.mark.discard();
            }
            if (this.weapon != null) {
                this.weapon.discard();
            }
            this.armor.clear();
            this.cape.clear();
        }
    }

    private static class MiracleBlessingState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final boolean resurrected;
        private final List<DisplayEntity.class_8115> aura = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8123 mark;
        private final List<DisplayEntity.class_8115> halo = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> wings = new ArrayList<DisplayEntity.class_8115>();

        private MiracleBlessingState(ServerWorld world, PlayerEntity player, boolean resurrected) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            this.resurrected = resurrected;
            for (i = 0; i < 16; ++i) {
                this.aura.add(SpellVisualsManager.createDisplay(world, Blocks.field_10087.getDefaultState(), player.getPos(), 0.08f));
            }
            this.mark = SpellVisualsManager.createTextDisplay(world, "\u271e", 16638023, player.getPos(), 0.3f);
            if (resurrected) {
                for (i = 0; i < 8; ++i) {
                    this.halo.add(SpellVisualsManager.createDisplay(world, Blocks.field_10205.getDefaultState(), player.getPos(), 0.12f, 0.02f, 0.12f));
                }
                for (i = 0; i < 16; ++i) {
                    this.wings.add(SpellVisualsManager.createDisplay(world, Blocks.field_10446.getDefaultState(), player.getPos(), 0.08f, 0.2f, 0.08f));
                }
            }
        }

        private boolean tick(ServerWorld world) {
            Vec3d pos;
            float angle;
            int i;
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            for (i = 0; i < this.aura.size(); ++i) {
                angle = (float)Math.toRadians((float)i * (360.0f / (float)this.aura.size()) + (float)this.tick * 10.0f);
                pos = base.add(Math.cos(angle) * 1.2, 0.8 + Math.sin(angle * 2.0f) * 0.2, Math.sin(angle) * 1.2);
                SpellVisualsManager.updateDisplay(this.aura.get(i), pos, 0.08f);
            }
            this.mark.setPosition(base.x, base.y + 2.2, base.z);
            if (this.resurrected && this.tick < 400) {
                for (i = 0; i < this.halo.size(); ++i) {
                    angle = (float)Math.toRadians((float)i * (360.0f / (float)this.halo.size()) + (float)this.tick * 6.0f);
                    pos = base.add(Math.cos(angle) * 0.6, 2.4, Math.sin(angle) * 0.6);
                    SpellVisualsManager.updateDisplay(this.halo.get(i), pos, 0.12f, 0.02f, 0.12f);
                }
                for (i = 0; i < this.wings.size(); ++i) {
                    angle = (float)Math.toRadians((double)((float)(i - 8) * 12.0f) + Math.sin((double)this.tick * 0.2) * 20.0);
                    pos = base.add(Math.cos(angle) * 0.9, 1.4 + Math.sin((double)this.tick * 0.2) * 0.2, Math.sin(angle) * 0.6);
                    SpellVisualsManager.updateDisplay(this.wings.get(i), pos, 0.08f, 0.2f, 0.08f);
                }
            }
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.5, base.z, 2, 0.3, 0.3, 0.3, 0.0);
            ++this.tick;
            if (this.tick >= 420) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.aura);
            if (this.mark != null) {
                this.mark.discard();
            }
            SpellVisualsManager.removeDisplays(this.halo);
            SpellVisualsManager.removeDisplays(this.wings);
            this.aura.clear();
            this.halo.clear();
            this.wings.clear();
        }
    }

    private static class CataclysmHazardState {
        private final ServerWorld world;
        private final Vec3d center;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> craterDisplays = new ArrayList<DisplayEntity.class_8115>();

        private CataclysmHazardState(ServerWorld world, Vec3d center) {
            this.world = world;
            this.center = center;
            for (int i = 0; i < 24; ++i) {
                this.craterDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_23869.getDefaultState(), center, 0.1f));
            }
        }

        private boolean tick(ServerWorld world) {
            float pulse = 1.0f + MathHelper.sin((float)((float)this.tick * 0.2f)) * 0.15f;
            for (int i = 0; i < this.craterDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.craterDisplays.size()) + (float)this.tick * 3.0f);
                Vec3d pos = this.center.add(Math.cos(angle) * 3.5, 0.1, Math.sin(angle) * 3.5);
                SpellVisualsManager.updateDisplay(this.craterDisplays.get(i), pos, 0.1f * pulse);
            }
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, this.center.x, this.center.y + 0.2, this.center.z, 10, 2.0, 0.2, 2.0, 0.0);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, this.center.x, this.center.y + 0.2, this.center.z, 5, 1.5, 0.2, 1.5, 0.0);
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(world, this.center, 6.0)) {
                entity.setOnFireFor(1.0f);
                if (this.tick % 40 != 0) continue;
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, 200, 0, false, true));
            }
            if (this.tick % 40 == 0) {
                world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_17483, SoundCategory.field_15248, 0.7f, 0.8f);
            }
            ++this.tick;
            if (this.tick >= 200) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.craterDisplays);
            this.craterDisplays.clear();
        }
    }

    private static class TitanFormState {
        private final UUID playerId;
        private final ServerWorld world;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> armorDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8115> auraDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final List<DisplayEntity.class_8123> runeDisplays = new ArrayList<DisplayEntity.class_8123>();
        private final List<DisplayEntity.class_8115> eyeDisplays = new ArrayList<DisplayEntity.class_8115>();
        private final DisplayEntity.class_8122 weaponDisplay;

        private TitanFormState(ServerWorld world, PlayerEntity player) {
            int i;
            this.world = world;
            this.playerId = player.getUuid();
            Vec3d base = player.getPos();
            for (i = 0; i < 24; ++i) {
                this.armorDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_22108.getDefaultState(), base, 0.18f));
            }
            for (i = 0; i < 40; ++i) {
                this.auraDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10002.getDefaultState(), base, 0.12f));
            }
            this.runeDisplays.add(SpellVisualsManager.createTextDisplay(world, "\u26a1", 16347926, base, 0.3f));
            this.runeDisplays.add(SpellVisualsManager.createTextDisplay(world, "\u2694", 16096779, base, 0.3f));
            this.runeDisplays.add(SpellVisualsManager.createTextDisplay(world, "\u26e8", 16638023, base, 0.3f));
            this.runeDisplays.add(SpellVisualsManager.createTextDisplay(world, "\u2620", 0xEF4444, base, 0.3f));
            this.eyeDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10171.getDefaultState(), base, 0.04f));
            this.eyeDisplays.add(SpellVisualsManager.createDisplay(world, Blocks.field_10171.getDefaultState(), base, 0.04f));
            this.weaponDisplay = SpellVisualsManager.createItemDisplay(world, new ItemStack((ItemConvertible)Items.field_22022), base.add(0.6, 1.0, 0.0), 1.5f);
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            Vec3d base = player.getPos();
            this.updateArmor(base, this.tick);
            this.updateAura(base, this.tick);
            this.updateRunes(base, this.tick);
            this.updateEyes(base, this.tick);
            this.updateWeapon(base, this.tick);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_22246, base.x, base.y + 0.2, base.z, 3, 0.3, 0.2, 0.3, 0.0);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, base.x, base.y + 0.2, base.z, 3, 0.3, 0.2, 0.3, 0.0);
            if (this.tick % 20 == 0) {
                this.applyIntimidation(base);
            }
            if (this.tick >= 500) {
                this.cleanup();
                return true;
            }
            ++this.tick;
            return false;
        }

        private void updateArmor(Vec3d base, int tick) {
            int index = 0;
            double[][] offsets = new double[][]{{0.6, 1.6, 0.4}, {-0.6, 1.6, 0.4}, {0.6, 1.6, -0.4}, {-0.6, 1.6, -0.4}, {0.8, 1.2, 0.6}, {-0.8, 1.2, 0.6}, {0.8, 1.2, -0.6}, {-0.8, 1.2, -0.6}, {0.5, 0.8, 0.4}, {-0.5, 0.8, 0.4}, {0.5, 0.8, -0.4}, {-0.5, 0.8, -0.4}, {0.4, 0.4, 0.3}, {-0.4, 0.4, 0.3}, {0.4, 0.4, -0.3}, {-0.4, 0.4, -0.3}};
            for (int i = 0; i < this.armorDisplays.size(); ++i) {
                double[] offset = offsets[i % offsets.length];
                Vec3d pos = base.add(offset[0], offset[1] + Math.sin((double)(tick + i) * 0.1) * 0.1, offset[2]);
                SpellVisualsManager.updateDisplay(this.armorDisplays.get(i), pos, 0.18f);
                ++index;
            }
        }

        private void updateAura(Vec3d base, int tick) {
            float pulse = 1.0f + MathHelper.sin((float)((float)tick * 0.2f)) * 0.2f;
            for (int i = 0; i < this.auraDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.auraDisplays.size()) + (float)tick * 4.0f);
                Vec3d pos = base.add(Math.cos(angle) * 1.5, 1.0 + Math.sin(angle * 2.0f) * 0.3, Math.sin(angle) * 1.5);
                SpellVisualsManager.updateDisplay(this.auraDisplays.get(i), pos, 0.12f * pulse);
            }
        }

        private void updateRunes(Vec3d base, int tick) {
            for (int i = 0; i < this.runeDisplays.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * 90.0f + (float)tick * 9.0f);
                Vec3d pos = base.add(Math.cos(angle) * 1.2, 2.2, Math.sin(angle) * 1.2);
                this.runeDisplays.get(i).setPosition(pos.x, pos.y, pos.z);
            }
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, base.x, base.y + 1.8, base.z, 2, 0.3, 0.3, 0.3, 0.0);
        }

        private void updateEyes(Vec3d base, int tick) {
            Vec3d left = base.add(0.2, 1.7, 0.3);
            Vec3d right = base.add(-0.2, 1.7, 0.3);
            SpellVisualsManager.updateDisplay(this.eyeDisplays.get(0), left, 0.04f);
            SpellVisualsManager.updateDisplay(this.eyeDisplays.get(1), right, 0.04f);
            this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11251, base.x, base.y + 1.6, base.z, 5, 0.1, 0.1, 0.1, 0.0);
        }

        private void updateWeapon(Vec3d base, int tick) {
            Vec3d pos = base.add(0.8, 1.0 + Math.sin((double)tick * 0.2) * 0.1, 0.0);
            SpellVisualsManager.updateItemDisplay(this.weaponDisplay, pos, 1.5f);
        }

        private void applyIntimidation(Vec3d base) {
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(this.world, base, 5.0, living -> !(living instanceof PlayerEntity))) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, 40, 0, false, true));
                this.world.spawnParticles((ParticleEffect)ParticleTypes.field_11231, entity.getX(), entity.getBodyY(0.5), entity.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
                this.world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.field_14958, SoundCategory.field_15248, 0.3f, 1.2f);
            }
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.armorDisplays);
            SpellVisualsManager.removeDisplays(this.auraDisplays);
            for (DisplayEntity.class_8123 rune : this.runeDisplays) {
                if (rune == null) continue;
                rune.discard();
            }
            SpellVisualsManager.removeDisplays(this.eyeDisplays);
            if (this.weaponDisplay != null) {
                this.weaponDisplay.discard();
            }
            this.armorDisplays.clear();
            this.auraDisplays.clear();
            this.runeDisplays.clear();
            this.eyeDisplays.clear();
        }
    }

    private static class HemorrhageStatusEffect
    extends StatusEffect {
        private HemorrhageStatusEffect() {
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

    private static class MeteorHazardState {
        private final ServerWorld world;
        private final Vec3d center;
        private int tick = 0;
        private final List<DisplayEntity.class_8115> fragments = new ArrayList<DisplayEntity.class_8115>();

        private MeteorHazardState(ServerWorld world, Vec3d center) {
            this.world = world;
            this.center = center;
            for (int i = 0; i < 12; ++i) {
                this.fragments.add(SpellVisualsManager.createDisplay(world, Blocks.field_10092.getDefaultState(), center, 0.1f));
            }
        }

        private boolean tick(ServerWorld world) {
            float pulse = 1.0f + MathHelper.sin((float)((float)this.tick * 0.2f)) * 0.2f;
            for (int i = 0; i < this.fragments.size(); ++i) {
                float angle = (float)Math.toRadians((float)i * (360.0f / (float)this.fragments.size()) + (float)this.tick * 8.0f);
                Vec3d pos = this.center.add(Math.cos(angle) * 1.6, 0.1, Math.sin(angle) * 1.6);
                SpellVisualsManager.updateDisplay(this.fragments.get(i), pos, 0.1f * pulse);
            }
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, this.center.x, this.center.y + 0.2, this.center.z, 5, 2.0, 0.2, 2.0, 0.0);
            for (LivingEntity entity : SpellVisualsManager.getLivingEntitiesAround(world, this.center, 6.0)) {
                if (!(entity.getPos().distanceTo(this.center) <= 6.0)) continue;
                entity.setOnFireFor(1.0f);
            }
            if (this.tick % 20 == 0) {
                world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_17483, SoundCategory.field_15248, 0.6f, 0.9f);
            }
            ++this.tick;
            if (this.tick >= 100) {
                this.cleanup();
                return true;
            }
            return false;
        }

        private void cleanup() {
            SpellVisualsManager.removeDisplays(this.fragments);
            this.fragments.clear();
        }
    }

    private static class OrbitSymbolState {
        private final UUID playerId;
        private final ServerWorld world;
        private final DisplayEntity.class_8123 display;
        private final float angleOffset;
        private int tick = 0;
        private int fadeTick = 0;

        private OrbitSymbolState(PlayerEntity player, String symbol, int color, float angleOffset) {
            this.playerId = player.getUuid();
            this.world = (ServerWorld)player.getWorld();
            this.angleOffset = angleOffset;
            this.display = new DisplayEntity.class_8123(EntityType.field_42457, (World)this.world);
            this.display.setText((Text)Text.literal((String)symbol).formatted(Formatting.field_1067).withColor(color));
            this.display.setBillboardMode(DisplayEntity.class_8114.field_42409);
            this.display.setBackground(0);
            this.display.setTextOpacity((byte)-1);
            this.display.setPosition(player.getX(), player.getY() + 1.5, player.getZ());
            this.display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(0.3f, 0.3f, 0.3f), new Quaternionf()));
            this.world.spawnEntity((Entity)this.display);
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            if (!player.hasStatusEffect(StatusEffects.field_5910)) {
                ++this.fadeTick;
            }
            float rotation = (float)this.tick * 9.0f + this.angleOffset;
            float radius = 0.6f;
            Vec3d pos = player.getPos().add(Math.cos(Math.toRadians(rotation)) * (double)radius, 1.5, Math.sin(Math.toRadians(rotation)) * (double)radius);
            this.display.setPosition(pos.x, pos.y, pos.z);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11205, pos.x, pos.y, pos.z, 1, 0.2, 0.2, 0.2, 0.0);
            if (this.fadeTick > 0) {
                float alpha = MathHelper.clamp((float)(1.0f - (float)this.fadeTick / 20.0f), (float)0.0f, (float)1.0f);
                this.display.setText((Text)Text.literal((String)this.display.getText().getString()).formatted(Formatting.field_1067).withColor(this.display.getText().getStyle().getColor().getRgb()));
                this.display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(0.3f * alpha, 0.3f * alpha, 0.3f * alpha), new Quaternionf()));
                if (this.fadeTick >= 20) {
                    this.cleanup();
                    return true;
                }
            }
            ++this.tick;
            return false;
        }

        private void cleanup() {
            if (this.display != null) {
                this.display.discard();
            }
        }
    }

    private static class FortressRuneState {
        private final UUID playerId;
        private final ServerWorld world;
        private final DisplayEntity.class_8123 display;
        private final float angleOffset;
        private int tick = 0;
        private int fadeTick = 0;

        private FortressRuneState(PlayerEntity player, String symbol, int color, float angleOffset) {
            this.playerId = player.getUuid();
            this.world = (ServerWorld)player.getWorld();
            this.angleOffset = angleOffset;
            this.display = new DisplayEntity.class_8123(EntityType.field_42457, (World)this.world);
            this.display.setText((Text)Text.literal((String)symbol).formatted(Formatting.field_1067).withColor(color));
            this.display.setBillboardMode(DisplayEntity.class_8114.field_42409);
            this.display.setBackground(0);
            this.display.setTextOpacity((byte)-1);
            this.display.setPosition(player.getX(), player.getY() + 2.0, player.getZ());
            this.display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(0.3f, 0.3f, 0.3f), new Quaternionf()));
            this.world.spawnEntity((Entity)this.display);
        }

        private boolean tick(ServerWorld world) {
            PlayerEntity player = world.getPlayerByUuid(this.playerId);
            if (player == null || !player.isAlive()) {
                this.cleanup();
                return true;
            }
            if (!player.hasStatusEffect(StatusEffects.field_5907)) {
                ++this.fadeTick;
            }
            float rotation = (float)this.tick * 6.0f + this.angleOffset;
            float radius = 0.7f;
            Vec3d pos = player.getPos().add(Math.cos(Math.toRadians(rotation)) * (double)radius, 2.0, Math.sin(Math.toRadians(rotation)) * (double)radius);
            this.display.setPosition(pos.x, pos.y, pos.z);
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11215, pos.x, pos.y, pos.z, 1, 0.2, 0.2, 0.2, 0.0);
            if (this.fadeTick > 0) {
                float alpha = MathHelper.clamp((float)(1.0f - (float)this.fadeTick / 20.0f), (float)0.0f, (float)1.0f);
                this.display.setTransformation(new AffineTransformation(new Vector3f(), new Quaternionf(), new Vector3f(0.3f * alpha, 0.3f * alpha, 0.3f * alpha), new Quaternionf()));
                if (this.fadeTick >= 20) {
                    this.cleanup();
                    return true;
                }
            }
            ++this.tick;
            return false;
        }

        private void cleanup() {
            if (this.display != null) {
                this.display.discard();
            }
        }
    }

    public static class DomainGuardianEntity
    extends HostileEntity {
        private final UUID ownerId;

        public DomainGuardianEntity(ServerWorld world, UUID ownerId) {
            super(EntityType.field_6051, (World)world);
            this.ownerId = ownerId;
            this.setCustomName((Text)Text.literal((String)"Domain Guardian").formatted(Formatting.field_1054));
            this.setCustomNameVisible(true);
            this.setGlowing(true);
            if (this.getAttributeInstance(EntityAttributes.field_23716) != null) {
                this.getAttributeInstance(EntityAttributes.field_23716).setBaseValue(40.0);
            }
            if (this.getAttributeInstance(EntityAttributes.field_23721) != null) {
                this.getAttributeInstance(EntityAttributes.field_23721).setBaseValue(5.0);
            }
            this.setHealth(40.0f);
        }

        public boolean canTarget(EntityType<?> type) {
            return true;
        }
    }
}

