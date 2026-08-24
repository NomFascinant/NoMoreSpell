/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 *  net.minecraft.Formatting
 *  net.minecraft.BossBar$class_1260
 *  net.minecraft.BossBar$class_1261
 *  net.minecraft.DamageSource
 *  net.minecraft.StatusEffectInstance
 *  net.minecraft.StatusEffects
 *  net.minecraft.Entity
 *  net.minecraft.EntityType
 *  net.minecraft.EquipmentSlot
 *  net.minecraft.MobEntity
 *  net.minecraft.LivingEntity
 *  net.minecraft.PathAwareEntity
 *  net.minecraft.SwimGoal
 *  net.minecraft.Goal
 *  net.minecraft.MeleeAttackGoal
 *  net.minecraft.LookAroundGoal
 *  net.minecraft.RevengeGoal
 *  net.minecraft.HostileEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.Item
 *  net.minecraft.ItemStack
 *  net.minecraft.Items
 *  net.minecraft.ItemConvertible
 *  net.minecraft.World
 *  net.minecraft.Blocks
 *  net.minecraft.Block
 *  net.minecraft.BlockPos
 *  net.minecraft.Position
 *  net.minecraft.Box
 *  net.minecraft.Vec3i
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.Team
 *  net.minecraft.BlockState
 *  net.minecraft.ServerBossBar
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  net.minecraft.SoundEvent
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 *  net.minecraft.MathHelper
 *  net.minecraft.EntityAttributes
 *  net.minecraft.TeleportTarget
 *  net.minecraft.DyedColorComponent
 *  net.minecraft.DataComponentTypes
 *  net.minecraft.server.MinecraftServer
 */
package nomorespell_rvknbyie.spell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.Formatting;
import net.minecraft.BossBar;
import net.minecraft.DamageSource;
import net.minecraft.StatusEffectInstance;
import net.minecraft.StatusEffects;
import net.minecraft.Entity;
import net.minecraft.EntityType;
import net.minecraft.EquipmentSlot;
import net.minecraft.MobEntity;
import net.minecraft.LivingEntity;
import net.minecraft.PathAwareEntity;
import net.minecraft.SwimGoal;
import net.minecraft.Goal;
import net.minecraft.MeleeAttackGoal;
import net.minecraft.LookAroundGoal;
import net.minecraft.RevengeGoal;
import net.minecraft.HostileEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Items;
import net.minecraft.ItemConvertible;
import net.minecraft.World;
import net.minecraft.Blocks;
import net.minecraft.Block;
import net.minecraft.BlockPos;
import net.minecraft.Position;
import net.minecraft.Box;
import net.minecraft.Vec3i;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.Team;
import net.minecraft.BlockState;
import net.minecraft.ServerBossBar;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import net.minecraft.SoundEvent;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import net.minecraft.MathHelper;
import net.minecraft.EntityAttributes;
import net.minecraft.TeleportTarget;
import net.minecraft.DyedColorComponent;
import net.minecraft.DataComponentTypes;
import net.minecraft.server.MinecraftServer;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.spell.SpellBookData;

public final class DomainExpansionManager {
    public static final double CAPTURE_HORIZONTAL_RADIUS = 30.0;
    public static final double CAPTURE_VERTICAL_RADIUS = 20.0;
    public static final double DOMAIN_RADIUS = 25.0;
    private static final double OUTER_VISUAL_RADIUS = 27.5;
    private static final double TARGET_MIN_RADIUS = 0.5;
    private static final double TARGET_MAX_RADIUS = 5.0;
    private static final double GUARDIAN_MIN_RADIUS = 2.5;
    private static final double GUARDIAN_MAX_RADIUS = 5.0;
    private static final double CONTEST_RADIUS = 70.0;
    private static final int FREEZE_TICKS = 100;
    private static final int PLATFORM_START = 1;
    private static final int PLATFORM_END = 100;
    private static final int DOME_START = 1;
    private static final int DOME_END = 100;
    private static final int ACTIVE_START = 1;
    private static final int ACTIVE_END = 600;
    private static final int SHRINK_START = 501;
    private static final int SHRINK_END = 600;
    private static final int TOTAL_TICKS = 600;
    private static final int VISUAL_GROWTH_TICKS = 100;
    private static final int VISUAL_SHRINK_TICKS = 100;
    private static final int THEME_SHRINK_TICKS = 50;
    private static final int PULSE_INTERVAL = 180;
    private static final int PULSE_FREEZE_TICKS = 20;
    private static final int CONTEST_DURATION = 300;
    private static final int GUARDIAN_COUNT = 6;
    private static final int NO_FALL_DURATION = 100;
    private static final int AMBIENT_INTERVAL = 60;
    private static final int OWNER_NIGHT_VISION_TICKS = 700;
    private static final int ENEMY_NIGHT_VISION_TICKS = 300;
    private static final Map<UUID, DomainState> ACTIVE_DOMAINS = new HashMap<UUID, DomainState>();
    private static final Map<BlockPos, UUID> PROTECTED_BLOCKS = new HashMap<BlockPos, UUID>();
    private static final Map<UUID, Integer> NO_FALL_PROTECTION = new HashMap<UUID, Integer>();
    private static final DomainPalette[] PALETTES = new DomainPalette[]{new DomainPalette(Blocks.field_38420.getDefaultState(), Blocks.field_23874.getDefaultState(), Blocks.field_22423.getDefaultState(), Blocks.field_27115.getDefaultState(), Blocks.field_10399.getDefaultState()), new DomainPalette(Blocks.field_10540.getDefaultState(), Blocks.field_10458.getDefaultState(), Blocks.field_10038.getDefaultState(), Blocks.field_27115.getDefaultState(), Blocks.field_10555.getDefaultState()), new DomainPalette(Blocks.field_28896.getDefaultState(), Blocks.field_28892.getDefaultState(), Blocks.field_10206.getDefaultState(), Blocks.field_27115.getDefaultState(), Blocks.field_10399.getDefaultState()), new DomainPalette(Blocks.field_23869.getDefaultState(), Blocks.field_23880.getDefaultState(), Blocks.field_27159.getDefaultState(), Blocks.field_27115.getDefaultState(), Blocks.field_9997.getDefaultState())};

    private DomainExpansionManager() {
    }

    public static boolean start(ServerWorld world, ServerPlayerEntity caster, List<LivingEntity> initialTargets) {
        DomainState existing;
        DomainState state2;
        if (world == null || caster == null || !caster.isAlive() || caster.isDisconnected()) {
            return false;
        }
        DomainState contested = null;
        for (DomainState state2 : ACTIVE_DOMAINS.values()) {
            if (state2.world != world || state2.finished || !(state2.center.squaredDistanceTo(caster.getPos()) <= 4900.0)) continue;
            contested = state2;
            break;
        }
        if (contested != null && !contested.isContestOverrideAllowed()) {
            return false;
        }
        if (contested != null) {
            contested.startContestOverride();
        }
        if ((existing = ACTIVE_DOMAINS.remove(caster.getUuid())) != null) {
            existing.cleanup();
        }
        state2 = new DomainState(world, caster, initialTargets == null ? List.of() : initialTargets);
        ACTIVE_DOMAINS.put(caster.getUuid(), state2);
        return true;
    }

    public static void tickServer(MinecraftServer server) {
        DomainExpansionManager.tickNoFallProtection();
        Iterator<Map.Entry<UUID, DomainState>> iterator = ACTIVE_DOMAINS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, DomainState> entry = iterator.next();
            DomainState state = entry.getValue();
            if (state != null && !state.tick()) continue;
            iterator.remove();
        }
    }

    public static boolean isProtectedDomainBlock(World world, BlockPos pos) {
        if (!(world instanceof ServerWorld)) {
            return false;
        }
        ServerWorld serverWorld = (ServerWorld)world;
        UUID id = PROTECTED_BLOCKS.get(pos.toImmutable());
        if (id == null) {
            return false;
        }
        DomainState state = ACTIVE_DOMAINS.get(id);
        return state != null && !state.finished && state.world == serverWorld;
    }

    public static boolean isAnyProtectedDomainBlock(BlockPos pos) {
        UUID id = PROTECTED_BLOCKS.get(pos.toImmutable());
        if (id == null) {
            return false;
        }
        DomainState state = ACTIVE_DOMAINS.get(id);
        return state != null && !state.finished;
    }

    public static boolean shouldCancelFallDamage(LivingEntity entity) {
        if (entity == null) {
            return false;
        }
        Integer ticks = NO_FALL_PROTECTION.get(entity.getUuid());
        return ticks != null && ticks > 0;
    }

    public static boolean isFriendlyToAnyDomain(ServerPlayerEntity player) {
        for (DomainState state : ACTIVE_DOMAINS.values()) {
            if (state.finished || !state.isFriendly((PlayerEntity)player)) continue;
            return true;
        }
        return false;
    }

    public static boolean isInsideOwnDomain(ServerPlayerEntity player) {
        if (player == null || !player.isAlive()) {
            return false;
        }
        DomainState state = ACTIVE_DOMAINS.get(player.getUuid());
        return state != null && !state.finished && state.world == player.getServerWorld() && state.contains((LivingEntity)player);
    }

    public static List<DomainRenderState> getRenderStates(World world) {
        ArrayList<DomainRenderState> list = new ArrayList<DomainRenderState>();
        for (DomainState state : ACTIVE_DOMAINS.values()) {
            if (state.finished || state.world != world) continue;
            list.add(state.createRenderState());
        }
        return list;
    }

    private static void tickNoFallProtection() {
        Iterator<Map.Entry<UUID, Integer>> iterator = NO_FALL_PROTECTION.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            int next = entry.getValue() - 1;
            if (next <= 0) {
                iterator.remove();
                continue;
            }
            entry.setValue(next);
        }
    }

    private static void grantNoFallProtection(LivingEntity entity) {
        if (entity == null) {
            return;
        }
        NO_FALL_PROTECTION.put(entity.getUuid(), 100);
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5906, 100, 0, false, false));
        entity.fallDistance = 0.0;
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

    private static final class DomainState {
        private final UUID casterId;
        private final ServerWorld world;
        private final Vec3d center;
        private final BlockPos centerBlock;
        private final ArenaLayout layout;
        private final DomainPalette palette;
        private final int totalDurationTicks;
        private final ServerBossBar bossBar;
        private final Map<UUID, CapturedEntityData> capturedEntities = new HashMap<UUID, CapturedEntityData>();
        private final List<UUID> guardianIds = new ArrayList<UUID>();
        private final Map<BlockPos, BlockState> replacedBlocks = new HashMap<BlockPos, BlockState>();
        private final Set<BlockPos> domeBlocks = new HashSet<BlockPos>();
        private final Set<BlockPos> themeBlocks = new HashSet<BlockPos>();
        private final List<List<BlockPos>> platformLayers;
        private final List<List<BlockPos>> domeLayers;
        private final List<List<BlockPos>> themeRemovalLayers;
        private final List<List<BlockPos>> domeRemovalLayers;
        private boolean platformGenerated;
        private boolean teleported;
        private boolean guardiansSpawned;
        private boolean cleanupStarted;
        private boolean finished;
        private boolean clashWindowOpened;
        private boolean cleanupCompleted;
        private boolean returningEntities;
        private int tick;
        private int contestTicks = -1;

        private DomainState(ServerWorld world, ServerPlayerEntity caster, List<LivingEntity> targets) {
            this.world = world;
            this.casterId = caster.getUuid();
            this.center = caster.getPos();
            this.centerBlock = BlockPos.ofFloored((Position)this.center);
            this.layout = ArenaLayout.values()[world.random.nextInt(ArenaLayout.values().length)];
            this.palette = PALETTES[world.random.nextInt(PALETTES.length)];
            this.totalDurationTicks = this.contestedDurationFor(caster);
            this.bossBar = new ServerBossBar((Text)Text.literal((String)"Domain Expansion"), BossBar.class_1260.field_5783, BossBar.class_1261.field_5795);
            this.bossBar.addPlayer(caster);
            this.platformLayers = this.buildPlatformLayers();
            this.domeLayers = this.buildDomeLayers();
            this.themeRemovalLayers = this.buildThemeRemovalLayers();
            this.domeRemovalLayers = this.buildDomeRemovalLayers();
            for (LivingEntity target : targets) {
                ServerPlayerEntity player;
                if (target == null || !target.isAlive() || target.getUuid().equals(this.casterId)) continue;
                this.capturedEntities.put(target.getUuid(), new CapturedEntityData(target.getUuid(), target.getPos()));
                if (!(target instanceof ServerPlayerEntity) || (player = (ServerPlayerEntity)target).isDisconnected()) continue;
                this.bossBar.addPlayer(player);
            }
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 200, 2, false, true));
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5925, 700, 0, false, false));
        }

        private int contestedDurationFor(ServerPlayerEntity caster) {
            for (DomainState state : ACTIVE_DOMAINS.values()) {
                if (state.world != this.world || state.finished || !(state.center.squaredDistanceTo(caster.getPos()) <= 4900.0)) continue;
                return 300;
            }
            return 600;
        }

        private boolean tick() {
            ServerPlayerEntity caster = this.world.getServer().getPlayerManager().getPlayer(this.casterId);
            if (caster == null || !caster.isAlive() || caster.isDisconnected()) {
                this.cleanup();
                return true;
            }
            if (this.contestTicks >= 0) {
                --this.contestTicks;
                if (this.contestTicks <= 0) {
                    this.cleanup();
                    return true;
                }
            }
            this.syncBossBar(caster);
            this.tagEntities(caster);
            this.handleTimeline(caster);
            ++this.tick;
            if (this.tick > this.totalDurationTicks + 1) {
                this.cleanup();
                return true;
            }
            return this.finished;
        }

        private void syncBossBar(ServerPlayerEntity caster) {
            this.bossBar.addPlayer(caster);
            int remainingTicks = Math.max(0, this.totalDurationTicks - this.tick + 1);
            this.bossBar.setPercent(MathHelper.clamp((float)((float)remainingTicks / (float)Math.max(1, this.totalDurationTicks)), (float)0.0f, (float)1.0f));
            this.bossBar.setName((Text)Text.empty().append((Text)Text.literal((String)caster.getName().getString()).formatted(Formatting.field_1076)).append((Text)Text.literal((String)" - ").formatted(Formatting.field_1080)).append((Text)Text.literal((String)"Domain Expansion").formatted(Formatting.field_1068)).append((Text)Text.literal((String)" [").formatted(Formatting.field_1080)).append((Text)Text.literal((String)((remainingTicks + 19) / 20 + "s")).formatted(remainingTicks <= 100 ? Formatting.field_1061 : Formatting.field_1065)).append((Text)Text.literal((String)"]").formatted(Formatting.field_1080)));
            this.bossBar.clearPlayers();
            Box visibleBox = Box.of((Vec3d)this.center, (double)70.0, (double)70.0, (double)70.0);
            for (ServerPlayerEntity player : this.world.getEntitiesByClass(ServerPlayerEntity.class, visibleBox, p -> !p.isDisconnected())) {
                double horizontalDistance = Math.sqrt(player.squaredDistanceTo(this.center.x, player.getY(), this.center.z));
                if (!(horizontalDistance <= 35.0)) continue;
                this.bossBar.addPlayer(player);
            }
            Iterator<Map.Entry<UUID, CapturedEntityData>> iterator = this.capturedEntities.entrySet().iterator();
            while (iterator.hasNext()) {
                ServerPlayerEntity player;
                LivingEntity living;
                Map.Entry<UUID, CapturedEntityData> entry = iterator.next();
                Entity entity = this.world.getEntity(entry.getKey());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) {
                    iterator.remove();
                    continue;
                }
                if (!(living instanceof ServerPlayerEntity) || (player = (ServerPlayerEntity)living).isDisconnected()) continue;
                this.bossBar.addPlayer(player);
            }
        }

        private void handleTimeline(ServerPlayerEntity caster) {
            int themeShrinkStart = Math.max(1, this.totalDurationTicks - 100 + 1);
            int teardownEnd = this.totalDurationTicks;
            if (this.tick <= 100) {
                this.freezeCapturedEntities(false);
                if (this.tick == 0) {
                    this.playActivationSound();
                }
                if (this.tick == 100) {
                    this.playFormationImpact();
                }
            }
            if (this.tick >= 1 && this.tick <= 100) {
                this.buildPlatformProgressively(this.tick - 1);
                if (this.tick == 1) {
                    this.playAmbientPulse();
                }
                if (this.tick == 100 && !this.teleported) {
                    this.generateRemainingPlatform();
                    this.teleportCapturedToArena();
                    this.teleportCasterToArena(caster);
                    this.spawnGuardians(caster);
                    this.teleported = true;
                }
            }
            if (this.tick >= 1 && this.tick <= 100) {
                this.buildDomeProgressively(this.tick - 1);
                if (this.tick == 1) {
                    this.generateRemainingPlatform();
                }
            }
            if (this.tick >= 1 && this.tick <= this.totalDurationTicks) {
                this.enforceContainment(caster);
                this.updateGuardians(caster);
                this.applyNightVisionEffects(caster);
                this.tryStartDomainClash(caster);
                this.playAmbientLoop();
                if ((this.tick - 1) % 180 == 0) {
                    this.pulseEnemies();
                    this.playAmbientPulse();
                }
                if (this.isPulseFreezeWindow()) {
                    this.freezeCapturedEntities(true);
                    this.jitterEnemies();
                }
            }
            if (this.tick >= themeShrinkStart && !this.cleanupStarted) {
                this.cleanupStarted = true;
                this.removeGuardians();
                this.returningEntities = true;
            }
            if (this.tick >= themeShrinkStart && this.tick < teardownEnd) {
                int localTick = this.tick - themeShrinkStart;
                if (this.returningEntities) {
                    this.returnCapturedEntities(caster);
                    this.returningEntities = false;
                }
                this.removeThemeProgressively(localTick);
                this.removeDomeProgressively(localTick);
            }
            if (this.tick >= teardownEnd && !this.cleanupCompleted) {
                if (this.returningEntities) {
                    this.returnCapturedEntities(caster);
                    this.returningEntities = false;
                }
                this.restoreAllBlocks();
                this.cleanupCompleted = true;
            }
            if (this.tick > teardownEnd) {
                this.finished = true;
                this.bossBar.clearPlayers();
            }
        }

        private void applyNightVisionEffects(ServerPlayerEntity caster) {
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5925, 700, 0, false, false));
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || !this.isEnemy(living)) continue;
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5925, 300, 0, false, false));
            }
        }

        private boolean contains(LivingEntity entity) {
            double dz;
            if (entity == null || !entity.isAlive()) {
                return false;
            }
            double dx = entity.getX() - this.center.x;
            return dx * dx + (dz = entity.getZ() - this.center.z) * dz <= 625.0 && Math.abs(entity.getY() - this.center.y) <= 20.0;
        }

        private void tryStartDomainClash(ServerPlayerEntity caster) {
            if (this.clashWindowOpened || this.totalDurationTicks <= 300) {
                return;
            }
            if (this.tick < this.totalDurationTicks / 2) {
                return;
            }
            this.clashWindowOpened = true;
            for (CapturedEntityData data : this.capturedEntities.values()) {
                ItemStack stack;
                ServerPlayerEntity player;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof ServerPlayerEntity) || (player = (ServerPlayerEntity)entity).isDisconnected() || this.isFriendly((PlayerEntity)player) || (stack = this.findSpellBook(player)) == null) continue;
                String slot1 = SpellBookData.getEquippedSlot1(stack);
                String slot2 = SpellBookData.getEquippedSlot2(stack);
                boolean canClash = "domain_expansion".equals(slot1) || "domain_expansion".equals(slot2);
                if (!canClash) continue;
                DomainState replaced = ACTIVE_DOMAINS.remove(this.casterId);
                if (replaced != null) {
                    replaced.cleanup();
                }
                DomainExpansionManager.start(this.world, player, this.collectValidTargets(player));
                return;
            }
        }

        private ItemStack findSpellBook(ServerPlayerEntity player) {
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack stack = player.getInventory().getStack(i);
                if (stack.getItem() != Nomorespell.Nomorespell_ITEM) continue;
                SpellBookData.initializeIfNeeded(stack);
                return stack;
            }
            return null;
        }

        private List<LivingEntity> collectValidTargets(ServerPlayerEntity caster) {
            Box searchBox = new Box(this.center.x - 30.0, this.center.y - 20.0, this.center.z - 30.0, this.center.x + 30.0, this.center.y + 20.0, this.center.z + 30.0);
            ArrayList<LivingEntity> targets = new ArrayList<LivingEntity>();
            for (LivingEntity entity : this.world.getEntitiesByClass(LivingEntity.class, searchBox, living -> living != null && living.isAlive() && !living.getUuid().equals(caster.getUuid()) && this.isEnemy((LivingEntity)living))) {
                targets.add(entity);
            }
            return targets;
        }

        private void playActivationSound() {
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_SPHERE_SOUND, SoundCategory.field_15248, 1.3f, 0.65f);
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_EXPANSION_SOUND, SoundCategory.field_15248, 1.0f, 0.85f);
        }

        private void playFormationImpact() {
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_SPHERE_SOUND, SoundCategory.field_15248, 1.6f, 0.55f);
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_38830, SoundCategory.field_15248, 0.9f, 0.6f);
        }

        private void playAmbientLoop() {
            if (this.tick % 60 == 0) {
                this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_SPHERE_SOUND, SoundCategory.field_15248, 0.45f, 0.75f);
            }
        }

        private void playAmbientPulse() {
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, Nomorespell.DOMAIN_SPHERE_SOUND, SoundCategory.field_15248, 1.1f, 0.5f);
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_14703, SoundCategory.field_15248, 0.8f, 0.7f);
        }

        private void freezeCapturedEntities(boolean pulseWindow) {
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) continue;
                living.setVelocity(0.0, 0.0, 0.0);
                living.velocityDirty = true;
                living.fallDistance = 0.0;
                int duration = pulseWindow ? 25 : 110;
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, duration, 10, false, false));
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5901, duration, 2, false, false));
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5911, duration, 1, false, false));
            }
        }

        private List<List<BlockPos>> buildPlatformLayers() {
            int layerCount = 100;
            ArrayList<List<BlockPos>> result = new ArrayList<List<BlockPos>>();
            for (int i = 0; i < layerCount; ++i) {
                result.add(new ArrayList());
            }
            HashMap<Integer, List> grouped = new HashMap<Integer, List>();
            for (BlockPos pos : this.getThemePositions()) {
                int key = MathHelper.floor((double)pos.getSquaredDistance((Vec3i)this.centerBlock));
                grouped.computeIfAbsent(key, ignored -> new ArrayList()).add(pos.toImmutable());
            }
            ArrayList ordered = new ArrayList(grouped.keySet());
            ordered.sort(Integer::compareTo);
            for (int i = 0; i < ordered.size(); ++i) {
                int index = MathHelper.clamp((int)((int)Math.floor((double)i / (double)Math.max(1, ordered.size()) * (double)layerCount)), (int)0, (int)(layerCount - 1));
                ((List)result.get(index)).addAll((Collection)grouped.get(ordered.get(i)));
            }
            return result;
        }

        private Set<BlockPos> getThemePositions() {
            return switch (this.layout.ordinal()) {
                default -> throw new MatchException(null, null);
                case 2 -> this.createVoidPillars();
                case 1 -> this.createShatteredMountains();
                case 3 -> this.createAbyssalSanctum();
                case 0 -> this.createChaoticVoid();
            };
        }

        private Set<BlockPos> createVoidPillars() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            int floorY = this.centerBlock.getY() - 2;
            for (int x = -24; x <= 24; ++x) {
                for (int z = -24; z <= 24; ++z) {
                    int y;
                    double dist = Math.sqrt(x * x + z * z);
                    if (dist > 24.0) continue;
                    for (y = -4; y <= 0; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                    if ((Math.abs(x) + Math.abs(z)) % 6 == 0) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + 1, z));
                    }
                    if (!(dist > 6.0) || !(dist < 21.0) || !this.nearRingAnchor(x, z, 9, 2.8)) continue;
                    for (y = -2; y <= 21; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                }
            }
            return positions;
        }

        private Set<BlockPos> createShatteredMountains() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            int floorY = this.centerBlock.getY() - 3;
            for (int x = -24; x <= 24; ++x) {
                for (int z = -24; z <= 24; ++z) {
                    double dist = Math.sqrt(x * x + z * z);
                    if (dist > 24.0) continue;
                    double noise = this.smoothNoise(x, z);
                    int height = dist < 7.0 ? 1 : 3 + (int)Math.round(noise * 5.0 * (1.0 - dist / 25.0));
                    for (int y = -4; y <= height; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                    int hanging = dist < 8.0 ? 0 : (int)Math.round(this.smoothNoise(x - 8, z + 6) * 5.0 * (1.0 - dist / 25.0));
                    for (int y = 0; y < hanging; ++y) {
                        positions.add(this.centerBlock.add(x, 13 - y, z));
                    }
                }
            }
            return positions;
        }

        private Set<BlockPos> createAbyssalSanctum() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            int floorY = this.centerBlock.getY() - 2;
            for (int x = -24; x <= 24; ++x) {
                for (int z = -24; z <= 24; ++z) {
                    double dist = Math.sqrt(x * x + z * z);
                    if (dist > 24.0) continue;
                    for (int y = -3; y <= 0; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                    int ring = (int)Math.round(dist);
                    if (ring != 6 && ring != 12 && ring != 18) continue;
                    positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + 1, z));
                    positions.add(this.centerBlock.add(x, 9, z));
                    positions.add(this.centerBlock.add(x, -9, z));
                }
            }
            return positions;
        }

        private Set<BlockPos> createChaoticVoid() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            int floorY = this.centerBlock.getY() - 4;
            for (int x = -24; x <= 24; ++x) {
                for (int z = -24; z <= 24; ++z) {
                    double dist = Math.sqrt(x * x + z * z);
                    if (dist > 24.0) continue;
                    double noise = this.smoothNoise(x * 2, z * 2);
                    if (dist < 6.0 || noise > 0.48) {
                        int height = dist < 6.0 ? 1 : 1 + (int)Math.round(noise * 4.0);
                        for (int y = -2; y <= height; ++y) {
                            positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                        }
                    }
                    if (!(this.smoothNoise(x - 13, z + 9) > 0.65) || !(dist > 8.0)) continue;
                    for (int y = 0; y <= 3; ++y) {
                        positions.add(this.centerBlock.add(x, 10 - y, z));
                    }
                }
            }
            return positions;
        }

        private Set<BlockPos> createGravityCore() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            int floorY = this.centerBlock.getY() - 6;
            for (int x = -24; x <= 24; ++x) {
                for (int z = -24; z <= 24; ++z) {
                    double dist = Math.sqrt(x * x + z * z);
                    if (dist > 24.0) continue;
                    int bowlHeight = (int)Math.round(Math.max(0.0, 6.0 - dist * 0.28));
                    for (int y = -2; y <= bowlHeight; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                    int ring = (int)Math.round(dist);
                    if (ring == 8 || ring == 16) {
                        positions.add(this.centerBlock.add(x, 8, z));
                        positions.add(this.centerBlock.add(x, -8, z));
                    }
                    if (!(dist < 4.0)) continue;
                    for (int y = 1; y <= 9; ++y) {
                        positions.add(this.centerBlock.add(x, floorY - this.centerBlock.getY() + y, z));
                    }
                }
            }
            return positions;
        }

        private boolean nearRingAnchor(int x, int z, int count, double radius) {
            for (int i = 0; i < count; ++i) {
                double pz;
                double dz;
                double angle = Math.PI * 2 * (double)i / (double)count;
                double px = Math.cos(angle) * 17.0;
                double dx = (double)x - px;
                if (!(Math.sqrt(dx * dx + (dz = (double)z - (pz = Math.sin(angle) * 17.0)) * dz) <= radius)) continue;
                return true;
            }
            return false;
        }

        private double smoothNoise(int x, int z) {
            double value = Math.sin((double)x * 0.18) + Math.cos((double)z * 0.23) + Math.sin((double)(x + z) * 0.11);
            return MathHelper.clamp((double)((value + 3.0) / 6.0), (double)0.0, (double)1.0);
        }

        private void buildPlatformProgressively(int localTick) {
            if (this.platformGenerated) {
                return;
            }
            if (localTick < 0 || localTick >= this.platformLayers.size()) {
                return;
            }
            List<BlockPos> layer = this.platformLayers.get(localTick);
            for (BlockPos pos : layer) {
                this.placeThemeBlock(pos);
            }
            if (localTick >= this.platformLayers.size() - 1) {
                this.platformGenerated = true;
            }
        }

        private void generateRemainingPlatform() {
            if (this.platformGenerated) {
                return;
            }
            for (List<BlockPos> layer : this.platformLayers) {
                for (BlockPos pos : layer) {
                    this.placeThemeBlock(pos);
                }
            }
            this.platformGenerated = true;
        }

        private void placeThemeBlock(BlockPos pos) {
            BlockState state = this.getThemeBlockState(pos);
            this.saveAndPlace(pos, state, false);
        }

        private BlockState getThemeBlockState(BlockPos pos) {
            int relX = pos.getX() - this.centerBlock.getX();
            int relY = pos.getY() - this.centerBlock.getY();
            int relZ = pos.getZ() - this.centerBlock.getZ();
            int ring = Math.round((float)Math.sqrt(relX * relX + relZ * relZ));
            return switch (this.layout.ordinal()) {
                default -> throw new MatchException(null, null);
                case 2 -> {
                    if (relY > 8) {
                        yield this.palette.accent();
                    }
                    if ((Math.abs(relX) + Math.abs(relZ)) % 5 == 0) {
                        yield this.palette.secondary();
                    }
                    yield this.palette.primary();
                }
                case 1 -> {
                    if (relY > 7) {
                        yield this.palette.secondary();
                    }
                    if (relY > 0) {
                        yield this.palette.accent();
                    }
                    if (ring % 5 == 0) {
                        yield this.palette.secondary();
                    }
                    yield this.palette.primary();
                }
                case 3 -> {
                    if (ring == 6 || ring == 12 || ring == 18 || relY == 9 || relY == -9) {
                        yield this.palette.accent();
                    }
                    if (Math.abs(relX) == Math.abs(relZ) || relX == 0 || relZ == 0) {
                        yield this.palette.secondary();
                    }
                    yield this.palette.primary();
                }
                case 0 -> relY > 5 ? this.palette.accent() : ((Math.abs(relX) + Math.abs(relZ) + Math.abs(relY)) % 4 == 0 ? this.palette.secondary() : this.palette.primary());
            };
        }

        private List<List<BlockPos>> buildDomeLayers() {
            int layerCount = 100;
            ArrayList<List<BlockPos>> result = new ArrayList<List<BlockPos>>();
            for (int i = 0; i < layerCount; ++i) {
                result.add(new ArrayList());
            }
            HashSet<BlockPos> unique = new HashSet<BlockPos>();
            int radius = 25;
            double minShell = 24.0;
            double shellThickness = Math.max(1.0E-4, 25.0 - minShell);
            for (int y = -radius; y <= radius; ++y) {
                for (int x = -radius; x <= radius; ++x) {
                    for (int z = -radius; z <= radius; ++z) {
                        BlockPos pos;
                        double d = Math.sqrt(x * x + y * y + z * z);
                        if (d > 25.0 || d < 24.0 || !unique.add(pos = this.centerBlock.add(x, y, z).toImmutable())) continue;
                        float progress = (float)((d - minShell) / shellThickness);
                        int layerIndex = MathHelper.clamp((int)((int)Math.floor(progress * (float)layerCount)), (int)0, (int)(layerCount - 1));
                        ((List)result.get(layerIndex)).add(pos);
                    }
                }
            }
            return result;
        }

        private void buildDomeProgressively(int localTick) {
            if (localTick < 0 || localTick >= this.domeLayers.size()) {
                return;
            }
            for (BlockPos pos : this.domeLayers.get(localTick)) {
                this.saveAndPlace(pos, this.getDomeBlockState(pos), true);
            }
        }

        private List<List<BlockPos>> buildThemeRemovalLayers() {
            return this.buildRemovalLayers(this.themeBlocksFromPlatform(), 100, true);
        }

        private List<List<BlockPos>> buildDomeRemovalLayers() {
            return this.buildRemovalLayers(this.domeBlocksFromLayers(), 100, false);
        }

        private Set<BlockPos> themeBlocksFromPlatform() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            for (List<BlockPos> layer : this.platformLayers) {
                positions.addAll(layer);
            }
            return positions;
        }

        private Set<BlockPos> domeBlocksFromLayers() {
            HashSet<BlockPos> positions = new HashSet<BlockPos>();
            for (List<BlockPos> layer : this.domeLayers) {
                positions.addAll(layer);
            }
            return positions;
        }

        private List<List<BlockPos>> buildRemovalLayers(Set<BlockPos> positions, int layerCount, boolean theme) {
            ArrayList<List<BlockPos>> result = new ArrayList<List<BlockPos>>();
            for (int i = 0; i < layerCount; ++i) {
                result.add(new ArrayList());
            }
            if (positions.isEmpty()) {
                return result;
            }
            ArrayList<BlockPos> ordered = new ArrayList<BlockPos>(positions);
            ordered.sort(Comparator.comparingDouble(pos -> theme ? this.themeRemovalPriority((BlockPos)pos) : this.domeRemovalPriority((BlockPos)pos)).thenComparingInt(Vec3i::getY).thenComparingInt(Vec3i::getX).thenComparingInt(Vec3i::getZ));
            for (int i = 0; i < ordered.size(); ++i) {
                int index = MathHelper.clamp((int)((int)Math.floor((double)i / (double)ordered.size() * (double)layerCount)), (int)0, (int)(layerCount - 1));
                ((List)result.get(index)).add((BlockPos)ordered.get(i));
            }
            return result;
        }

        private double themeRemovalPriority(BlockPos pos) {
            double dx = (double)pos.getX() - this.center.x;
            double dz = (double)pos.getZ() - this.center.z;
            return -Math.sqrt(dx * dx + dz * dz);
        }

        private double domeRemovalPriority(BlockPos pos) {
            double dx = (double)pos.getX() - this.center.x;
            double dy = Math.abs((double)pos.getY() - this.center.y);
            double dz = (double)pos.getZ() - this.center.z;
            double horizontal = Math.sqrt(dx * dx + dz * dz);
            return -(dy * 3.0 + horizontal);
        }

        private void buildRemainingDome() {
            for (List<BlockPos> layer : this.domeLayers) {
                for (BlockPos pos : layer) {
                    if (this.domeBlocks.contains(pos)) continue;
                    this.saveAndPlace(pos, this.getDomeBlockState(pos), true);
                }
            }
        }

        private BlockState getDomeBlockState(BlockPos pos) {
            boolean stylizedPattern;
            int dx = pos.getX() - this.centerBlock.getX();
            int dy = pos.getY() - this.centerBlock.getY();
            int dz = pos.getZ() - this.centerBlock.getZ();
            int ring = Math.round((float)Math.sqrt(dx * dx + dz * dz));
            boolean bl = stylizedPattern = ring % 6 == 0 || Math.abs(dx) == Math.abs(dz) || dx == 0 || dz == 0 || Math.abs(dy) % 5 == 0;
            if (stylizedPattern && (Math.abs(dx) + Math.abs(dy) + Math.abs(dz)) % 10 < 3) {
                return (ring + Math.abs(dy)) % 2 == 0 ? this.palette.secondary() : this.palette.accent();
            }
            return (ring + Math.abs(dy) & 1) == 0 ? this.palette.glassA() : this.palette.glassB();
        }

        private void saveAndPlace(BlockPos pos, BlockState state, boolean dome) {
            BlockPos immutable = pos.toImmutable();
            this.replacedBlocks.putIfAbsent(immutable, this.world.getBlockState(immutable));
            this.world.setBlockState(immutable, state, 3);
            PROTECTED_BLOCKS.put(immutable, this.casterId);
            if (dome) {
                this.domeBlocks.add(immutable);
            } else {
                this.themeBlocks.add(immutable);
            }
        }

        private void removeThemeProgressively(int localTick) {
            if (localTick < 0 || localTick >= this.themeRemovalLayers.size()) {
                return;
            }
            for (BlockPos pos : this.themeRemovalLayers.get(localTick)) {
                this.restoreSingleBlock(pos, false);
            }
        }

        private void removeDomeProgressively(int localTick) {
            if (localTick < 0 || localTick >= this.domeRemovalLayers.size()) {
                return;
            }
            for (BlockPos pos : this.domeRemovalLayers.get(localTick)) {
                this.restoreSingleBlock(pos, true);
            }
        }

        private void restoreSingleBlock(BlockPos pos, boolean dome) {
            BlockPos immutable = pos.toImmutable();
            BlockState original = this.replacedBlocks.remove(immutable);
            if (original == null) {
                return;
            }
            this.world.setBlockState(immutable, original, 3);
            PROTECTED_BLOCKS.remove(immutable);
            if (dome) {
                this.domeBlocks.remove(immutable);
            } else {
                this.themeBlocks.remove(immutable);
            }
        }

        private void teleportCapturedToArena() {
            Iterator<Map.Entry<UUID, CapturedEntityData>> iterator = this.capturedEntities.entrySet().iterator();
            while (iterator.hasNext()) {
                ServerPlayerEntity player;
                LivingEntity living;
                Map.Entry<UUID, CapturedEntityData> entry = iterator.next();
                Entity entity = this.world.getEntity(entry.getKey());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) {
                    iterator.remove();
                    continue;
                }
                if (living instanceof ServerPlayerEntity && (player = (ServerPlayerEntity)living).isSpectator()) {
                    this.bossBar.removePlayer(player);
                    iterator.remove();
                    continue;
                }
                this.teleportLivingSafely(living, this.randomArenaPoint(5.0, 0.5));
            }
        }

        private void teleportCasterToArena(ServerPlayerEntity caster) {
            this.teleportLivingSafely((LivingEntity)caster, this.randomArenaPoint(5.0, 0.5));
        }

        private void teleportLiving(LivingEntity living, double x, double y, double z) {
            this.teleportLivingSafely(living, new Vec3d(x, y, z));
        }

        private void teleportLivingSafely(LivingEntity living, Vec3d preferredPos) {
            Vec3d safePos = this.findSafeTeleportPos(preferredPos, living);
            if (living instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity)living;
                player.teleportTo(new TeleportTarget(this.world, safePos, Vec3d.ZERO, player.getYaw(), player.getPitch(), TeleportTarget.NO_OP));
            } else {
                living.teleport(safePos.x, safePos.y, safePos.z, false);
            }
            living.setVelocity(0.0, 0.0, 0.0);
            living.velocityDirty = true;
            living.fallDistance = 0.0;
            DomainExpansionManager.grantNoFallProtection(living);
        }

        private Vec3d randomArenaPoint(double maxRadius, double minRadius) {
            for (int attempt = 0; attempt < 32; ++attempt) {
                double angle = this.world.random.nextDouble() * Math.PI * 2.0;
                double radius = minRadius + this.world.random.nextDouble() * Math.max(0.1, maxRadius - minRadius);
                double x = this.center.x + Math.cos(angle) * radius;
                double z = this.center.z + Math.sin(angle) * radius;
                BlockPos sample = BlockPos.ofFloored((double)x, (double)this.center.y, (double)z);
                for (int y = this.centerBlock.getY() + 18; y >= this.centerBlock.getY() - 12; --y) {
                    BlockPos floor = new BlockPos(sample.getX(), y, sample.getZ());
                    Vec3d candidate = new Vec3d((double)floor.getX() + 0.5, (double)floor.getY() + 2.0, (double)floor.getZ() + 0.5);
                    Vec3d valid = this.validateSafePosition(candidate, null);
                    if (valid == null) continue;
                    return valid;
                }
            }
            return new Vec3d(this.center.x, (double)this.centerBlock.getY() + 2.0, this.center.z);
        }

        private Vec3d findSafeTeleportPos(Vec3d preferredPos, LivingEntity living) {
            for (int vertical = 0; vertical <= 8; ++vertical) {
                for (int spread = 0; spread < 24; ++spread) {
                    double angle = Math.PI * 2 * (double)spread / 24.0;
                    double offset = spread == 0 ? 0.0 : 0.75 + (double)spread / 24.0 * 4.0;
                    Vec3d candidate = new Vec3d(preferredPos.x + Math.cos(angle) * offset, preferredPos.y + (double)vertical, preferredPos.z + Math.sin(angle) * offset);
                    Vec3d safe = this.validateSafePosition(candidate, living);
                    if (safe == null) continue;
                    return safe;
                }
            }
            return new Vec3d(this.center.x, (double)this.centerBlock.getY() + 3.0, this.center.z);
        }

        private Vec3d validateSafePosition(Vec3d candidate, LivingEntity living) {
            float requiredHeight;
            double dx = candidate.x - this.center.x;
            double dz = candidate.z - this.center.z;
            if (dx * dx + dz * dz > 540.5625) {
                return null;
            }
            BlockPos feet = BlockPos.ofFloored((double)candidate.x, (double)candidate.y, (double)candidate.z);
            BlockPos head = feet.up();
            BlockPos above = feet.up(2);
            BlockPos below = feet.down();
            float f = requiredHeight = living == null ? 1.8f : living.getHeight();
            if (!this.world.getBlockState(feet).isAir() || !this.world.getBlockState(head).isAir()) {
                return null;
            }
            if (!this.world.getBlockState(above).isAir() && requiredHeight > 1.8f) {
                return null;
            }
            if (this.world.getBlockState(below).isAir()) {
                return null;
            }
            if (!this.world.getBlockState(below).blocksMovement()) {
                return null;
            }
            Box entityBox = living != null ? living.getBoundingBox().offset(candidate.x - living.getX(), candidate.y - living.getY(), candidate.z - living.getZ()) : new Box(candidate.x - 0.3, candidate.y, candidate.z - 0.3, candidate.x + 0.3, candidate.y + 1.8, candidate.z + 0.3);
            if (!this.world.isSpaceEmpty(entityBox)) {
                return null;
            }
            return new Vec3d((double)feet.getX() + 0.5, (double)feet.getY(), (double)feet.getZ() + 0.5);
        }

        private void spawnGuardians(ServerPlayerEntity caster) {
            if (this.guardiansSpawned) {
                return;
            }
            this.guardiansSpawned = true;
            for (int i = 0; i < 6; ++i) {
                DomainGuardianEntity guardian = new DomainGuardianEntity(this.world, this.casterId);
                Vec3d safePos = this.findSafeTeleportPos(this.randomArenaPoint(5.0, 2.5), (LivingEntity)guardian);
                double angle = Math.atan2(safePos.z - this.center.z, safePos.x - this.center.x);
                guardian.refreshPositionAndAngles(safePos.x, safePos.y, safePos.z, (float)Math.toDegrees(angle), 0.0f);
                this.equipGuardian(guardian);
                this.world.spawnEntity((Entity)guardian);
                this.guardianIds.add(guardian.getUuid());
            }
            this.world.playSound(null, this.center.x, this.center.y, this.center.z, SoundEvents.field_14792, SoundCategory.field_15248, 0.7f, 1.25f);
        }

        private void equipGuardian(DomainGuardianEntity guardian) {
            ItemStack helmet = new ItemStack((ItemConvertible)Item.fromBlock((Block)Blocks.field_10458));
            guardian.equipStack(EquipmentSlot.field_6169, helmet);
            guardian.equipStack(EquipmentSlot.field_6174, this.dyedBlackLeather(Items.field_8577));
            guardian.equipStack(EquipmentSlot.field_6172, this.dyedBlackLeather(Items.field_8570));
            guardian.equipStack(EquipmentSlot.field_6166, this.dyedBlackLeather(Items.field_8370));
        }

        private ItemStack dyedBlackLeather(Item item) {
            ItemStack stack = new ItemStack((ItemConvertible)item);
            stack.set(DataComponentTypes.field_49644, (Object)new DyedColorComponent(0x111111));
            return stack;
        }

        private void updateGuardians(ServerPlayerEntity caster) {
            Iterator<UUID> iterator = this.guardianIds.iterator();
            while (iterator.hasNext()) {
                DomainGuardianEntity guardian;
                UUID guardianId = iterator.next();
                Entity entity = this.world.getEntity(guardianId);
                if (!(entity instanceof DomainGuardianEntity) || !(guardian = (DomainGuardianEntity)entity).isAlive()) {
                    iterator.remove();
                    continue;
                }
                LivingEntity target = this.findBestEnemy(caster, guardian.getPos());
                if (target != null) {
                    guardian.setTarget(target);
                    continue;
                }
                if (!(guardian.getTarget() instanceof DomainGuardianEntity)) continue;
                guardian.setTarget(null);
            }
        }

        private LivingEntity findBestEnemy(ServerPlayerEntity caster, Vec3d from) {
            LivingEntity bestPlayer = null;
            double bestPlayerDistance = Double.MAX_VALUE;
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || !this.isEnemy(living)) continue;
                double distance = living.squaredDistanceTo(from);
                if (!(living instanceof PlayerEntity) || !(distance < bestPlayerDistance)) continue;
                bestPlayerDistance = distance;
                bestPlayer = living;
            }
            if (bestPlayer != null) {
                return bestPlayer;
            }
            LivingEntity best = null;
            double bestDistance = Double.MAX_VALUE;
            Box box = Box.of((Vec3d)this.center, (double)50.0, (double)50.0, (double)50.0);
            for (LivingEntity living : this.world.getEntitiesByClass(LivingEntity.class, box, this::isEnemy)) {
                double distance = living.squaredDistanceTo(from);
                if (!(distance < bestDistance)) continue;
                bestDistance = distance;
                best = living;
            }
            return best;
        }

        private boolean isEnemy(LivingEntity living) {
            if (living == null || !living.isAlive()) {
                return false;
            }
            if (living.getUuid().equals(this.casterId)) {
                return false;
            }
            if (living instanceof DomainGuardianEntity) {
                return false;
            }
            if (living instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)living;
                return !this.isFriendly(player);
            }
            return living instanceof HostileEntity;
        }

        private void pulseEnemies() {
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || !this.isEnemy(living)) continue;
                living.damage(this.world, this.world.getDamageSources().magic(), 6.0f);
            }
        }

        private boolean isPulseFreezeWindow() {
            if (this.tick < 1) {
                return false;
            }
            int local = this.tick - 1;
            return local % 180 < 20;
        }

        private void jitterEnemies() {
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || !this.isEnemy(living)) continue;
                double vx = (this.world.random.nextDouble() - 0.5) * 0.3;
                double vz = (this.world.random.nextDouble() - 0.5) * 0.3;
                living.setVelocity(vx, 0.08, vz);
                living.velocityDirty = true;
            }
        }

        private void enforceContainment(ServerPlayerEntity caster) {
            LivingEntity living;
            Iterator<Map.Entry<UUID, CapturedEntityData>> iterator = this.capturedEntities.entrySet().iterator();
            while (iterator.hasNext()) {
                ServerPlayerEntity player;
                Map.Entry<UUID, CapturedEntityData> entry = iterator.next();
                Entity entity = this.world.getEntity(entry.getKey());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) {
                    iterator.remove();
                    continue;
                }
                if (living instanceof ServerPlayerEntity && (player = (ServerPlayerEntity)living).isSpectator()) {
                    this.bossBar.removePlayer(player);
                    iterator.remove();
                    continue;
                }
                if (!(living.squaredDistanceTo(this.center) > 552.25)) continue;
                this.teleportLivingSafely(living, this.randomArenaPoint(5.0, 0.5));
            }
            if (!caster.isSpectator() && caster.squaredDistanceTo(this.center) > 552.25) {
                this.teleportLivingSafely((LivingEntity)caster, this.randomArenaPoint(5.0, 0.5));
            }
            Iterator<UUID> guardianIterator = this.guardianIds.iterator();
            while (guardianIterator.hasNext()) {
                Entity guardianEntity = this.world.getEntity(guardianIterator.next());
                if (!(guardianEntity instanceof LivingEntity) || !(living = (LivingEntity)guardianEntity).isAlive() || !(living.squaredDistanceTo(this.center) > 552.25)) continue;
                this.teleportLivingSafely(living, this.randomArenaPoint(5.0, 2.5));
            }
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5907, 30, 1, false, false));
        }

        private void tagEntities(ServerPlayerEntity caster) {
            for (CapturedEntityData data : this.capturedEntities.values()) {
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive()) continue;
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 30, 0, false, false));
            }
            caster.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5912, 30, 0, false, false));
        }

        private void returnCapturedEntities(ServerPlayerEntity caster) {
            for (CapturedEntityData data : this.capturedEntities.values()) {
                ServerPlayerEntity player;
                LivingEntity living;
                Entity entity = this.world.getEntity(data.id());
                if (!(entity instanceof LivingEntity) || !(living = (LivingEntity)entity).isAlive() || living instanceof ServerPlayerEntity && (player = (ServerPlayerEntity)living).isSpectator()) continue;
                this.teleportLiving(living, data.originalPos().x, data.originalPos().y, data.originalPos().z);
            }
            if (caster != null && caster.isAlive() && !caster.isSpectator()) {
                this.teleportLiving((LivingEntity)caster, this.center.x, this.center.y, this.center.z);
            }
        }

        private void removeGuardians() {
            Iterator<UUID> iterator = this.guardianIds.iterator();
            while (iterator.hasNext()) {
                UUID guardianId = iterator.next();
                Entity entity = this.world.getEntity(guardianId);
                if (entity != null) {
                    entity.discard();
                }
                iterator.remove();
            }
        }

        private void restoreAllBlocks() {
            for (Map.Entry<BlockPos, BlockState> entry : this.replacedBlocks.entrySet()) {
                this.world.setBlockState(entry.getKey(), entry.getValue(), 3);
                PROTECTED_BLOCKS.remove(entry.getKey());
            }
            this.replacedBlocks.clear();
            this.domeBlocks.clear();
            this.themeBlocks.clear();
        }

        private DomainRenderState createRenderState() {
            float radius;
            int endAge;
            float alpha = 0.9019608f;
            int shrinkStart = Math.max(501, this.totalDurationTicks - 100 + 1);
            boolean ending = this.tick >= shrinkStart;
            int n = endAge = ending ? this.tick - shrinkStart : 0;
            if (this.tick <= 100) {
                float progress = MathHelper.clamp((float)((float)this.tick / 100.0f), (float)0.0f, (float)1.0f);
                radius = (float)(27.5 * (double)progress);
            } else if (this.tick >= shrinkStart) {
                float progress = MathHelper.clamp((float)((float)(this.tick - shrinkStart) / 100.0f), (float)0.0f, (float)1.0f);
                radius = (float)(27.5 * (double)(1.0f - progress));
                alpha *= 1.0f - progress;
            } else {
                radius = 27.5f;
            }
            return new DomainRenderState(this.casterId, this.center, this.tick, Math.max(0.0f, radius), Math.max(0.0f, alpha), ending, Math.max(0, endAge));
        }

        private boolean isFriendly(PlayerEntity player) {
            if (player == null) {
                return false;
            }
            if (player.getUuid().equals(this.casterId)) {
                return true;
            }
            ServerPlayerEntity caster = this.world.getServer().getPlayerManager().getPlayer(this.casterId);
            return caster != null && DomainExpansionManager.areAllied((PlayerEntity)caster, player);
        }

        private boolean isContestOverrideAllowed() {
            return this.tick >= this.totalDurationTicks / 2;
        }

        private void startContestOverride() {
            this.contestTicks = 300;
        }

        private void cleanup() {
            if (this.finished) {
                return;
            }
            if (!this.cleanupStarted) {
                ServerPlayerEntity caster = this.world.getServer().getPlayerManager().getPlayer(this.casterId);
                this.returnCapturedEntities(caster);
                this.restoreAllBlocks();
                this.removeGuardians();
                this.cleanupStarted = true;
                this.returningEntities = false;
            }
            this.finished = true;
            this.bossBar.clearPlayers();
        }
    }

    public static final class DomainRenderState {
        public final UUID casterId;
        public final Vec3d center;
        public final int age;
        public final float radius;
        public final float alpha;
        public final boolean ending;
        public final int endAge;

        public DomainRenderState(UUID casterId, Vec3d center, int age, float radius, float alpha, boolean ending, int endAge) {
            this.casterId = casterId;
            this.center = center;
            this.age = age;
            this.radius = radius;
            this.alpha = alpha;
            this.ending = ending;
            this.endAge = endAge;
        }
    }

    private record DomainPalette(BlockState primary, BlockState secondary, BlockState accent, BlockState glassA, BlockState glassB) {
    }

    public static class DomainGuardianEntity
    extends HostileEntity {
        private final UUID ownerId;

        public DomainGuardianEntity(ServerWorld world, UUID ownerId) {
            super(EntityType.field_6051, (World)world);
            this.ownerId = ownerId;
            this.setCustomName((Text)Text.literal((String)"Domain Guardian").formatted(Formatting.field_1064));
            this.setCustomNameVisible(true);
            if (this.getAttributeInstance(EntityAttributes.field_23716) != null) {
                this.getAttributeInstance(EntityAttributes.field_23716).setBaseValue(40.0);
            }
            if (this.getAttributeInstance(EntityAttributes.field_23721) != null) {
                this.getAttributeInstance(EntityAttributes.field_23721).setBaseValue(5.0);
            }
            if (this.getAttributeInstance(EntityAttributes.field_23719) != null) {
                this.getAttributeInstance(EntityAttributes.field_23719).setBaseValue(0.35);
            }
            this.setHealth(40.0f);
        }

        protected void initGoals() {
            this.goalSelector.add(0, (Goal)new SwimGoal((MobEntity)this));
            this.goalSelector.add(1, (Goal)new MeleeAttackGoal((PathAwareEntity)this, 1.2, false));
            this.goalSelector.add(7, (Goal)new LookAroundGoal((MobEntity)this));
            this.targetSelector.add(1, (Goal)new RevengeGoal((PathAwareEntity)this, new Class[0]));
        }

        private boolean canTargetEntity(LivingEntity living) {
            if (living == null || !living.isAlive()) {
                return false;
            }
            if (living instanceof DomainGuardianEntity) {
                DomainGuardianEntity guardian = (DomainGuardianEntity)living;
                if (guardian.ownerId.equals(this.ownerId)) {
                    return false;
                }
            }
            if (living.getUuid().equals(this.ownerId)) {
                return false;
            }
            if (living instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)living;
                Entity owner = this.getWorld().getEntity(this.ownerId);
                if (owner instanceof PlayerEntity) {
                    PlayerEntity ownerPlayer = (PlayerEntity)owner;
                    return !DomainExpansionManager.areAllied(ownerPlayer, player);
                }
            }
            return living instanceof HostileEntity;
        }

        public void tick() {
            super.tick();
            if (!this.getWorld().isClient) {
                if (this.getTarget() instanceof DomainGuardianEntity) {
                    this.setTarget(null);
                }
                if (this.getTarget() == null) {
                    Box search = this.getBoundingBox().expand(24.0);
                    List candidates = this.getWorld().getEntitiesByClass(LivingEntity.class, search, this::canTargetEntity);
                    LivingEntity best = null;
                    double bestDistance = Double.MAX_VALUE;
                    for (LivingEntity candidate : candidates) {
                        double distance = candidate.squaredDistanceTo((Entity)this);
                        if (!(distance < bestDistance)) continue;
                        bestDistance = distance;
                        best = candidate;
                    }
                    if (best != null) {
                        this.setTarget(best);
                    }
                }
            }
        }

        protected SoundEvent getAmbientSound() {
            return SoundEvents.field_15214;
        }

        protected SoundEvent getHurtSound(DamageSource source) {
            return SoundEvents.field_15027;
        }

        protected SoundEvent getDeathSound() {
            return SoundEvents.field_15122;
        }

        public boolean canTarget(LivingEntity target) {
            return super.canTarget(target) && this.canTargetEntity(target);
        }
    }

    private static enum ArenaLayout {
        FLAT,
        MOUNTAINS,
        PILLARS,
        RUINS;

    }

    private record CapturedEntityData(UUID id, Vec3d originalPos) {
    }
}

