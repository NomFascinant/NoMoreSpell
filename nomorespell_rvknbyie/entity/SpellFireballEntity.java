/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.DamageSource
 *  net.minecraft.Entity
 *  net.minecraft.Entity$class_5529
 *  net.minecraft.EntityType
 *  net.minecraft.LivingEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.BlockView
 *  net.minecraft.World
 *  net.minecraft.Blocks
 *  net.minecraft.BlockPos
 *  net.minecraft.Position
 *  net.minecraft.Box
 *  net.minecraft.DustParticleEffect
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Vec3d
 *  net.minecraft.NbtCompound
 *  net.minecraft.BlockState
 *  net.minecraft.TrackedData
 *  net.minecraft.TrackedDataHandler
 *  net.minecraft.TrackedDataHandlerRegistry
 *  net.minecraft.DataTracker
 *  net.minecraft.DataTracker$class_9222
 *  net.minecraft.ServerWorld
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 *  net.minecraft.MathHelper
 *  net.minecraft.AffineTransformation
 *  net.minecraft.Random
 *  net.minecraft.DisplayEntity$class_8115
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package nomorespell_rvknbyie.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityType;
import net.minecraft.LivingEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.BlockView;
import net.minecraft.World;
import net.minecraft.Blocks;
import net.minecraft.BlockPos;
import net.minecraft.Position;
import net.minecraft.Box;
import net.minecraft.DustParticleEffect;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Vec3d;
import net.minecraft.NbtCompound;
import net.minecraft.BlockState;
import net.minecraft.TrackedData;
import net.minecraft.TrackedDataHandler;
import net.minecraft.TrackedDataHandlerRegistry;
import net.minecraft.DataTracker;
import net.minecraft.ServerWorld;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import net.minecraft.MathHelper;
import net.minecraft.AffineTransformation;
import net.minecraft.Random;
import net.minecraft.DisplayEntity;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.spell.CombatXpTracker;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SpellFireballEntity
extends Entity
implements CombatXpTracker.SpellAttributedEntity {
    private static final float DEFAULT_DAMAGE = 5.0f;
    private static final int DEFAULT_FIRE_SECONDS = 4;
    private static final double MAX_RANGE_SQ = 400.0;
    private static final double SPEED = 1.8;
    private static final double ENTITY_HIT_RADIUS = 0.7;
    private static final double CORE_SPHERE_RADIUS = 0.36;
    private static final double DETAIL_RING_RADIUS = 0.58;
    private static final int CORE_SEGMENTS = 10;
    private static final int DETAIL_SEGMENTS = 14;
    private static final float CORE_VISUAL_SCALE = 0.11f;
    private static final float DETAIL_VISUAL_SCALE = 0.07f;
    private static final int VISUAL_LIFETIME_TICKS = 30;
    private static final TrackedData<String> OWNER_UUID = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<Float> DATA_DAMAGE = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> DATA_FIRE_SECONDS = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> DATA_VISUAL_SCALE = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> DATA_GLOW_COLOR = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> DATA_VISUAL_SEED = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> DATA_VISUAL_ACTIVE = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<String> DATA_SPELL_ID = DataTracker.registerData(SpellFireballEntity.class, (TrackedDataHandler)TrackedDataHandlerRegistry.STRING);
    private Vec3d startPos;
    private Vec3d direction;
    private DisplayEntity.class_8115 markerEntity;
    private final List<DisplayEntity.class_8115> sphereDisplays = new ArrayList<DisplayEntity.class_8115>();
    private final List<Vec3d> sphereOffsets = new ArrayList<Vec3d>();
    private boolean cleanedUp = false;
    private boolean hitProcessed = false;
    private int collisionCooldown = 0;
    private LivingEntity cachedOwner;
    private Vec3d lastPos;
    private int visualTicks = 0;

    public SpellFireballEntity(EntityType<? extends SpellFireballEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
        this.noClip = true;
    }

    public SpellFireballEntity(ServerWorld world, LivingEntity owner) {
        this(Nomorespell.FIREBALL_ENTITY_TYPE, (World)world);
        this.setOwnerEntity(owner);
    }

    protected void initDataTracker(DataTracker.class_9222 builder) {
        builder.add(OWNER_UUID, (Object)"");
        builder.add(DATA_DAMAGE, (Object)Float.valueOf(5.0f));
        builder.add(DATA_FIRE_SECONDS, (Object)4);
        builder.add(DATA_VISUAL_SCALE, (Object)Float.valueOf(0.11f));
        builder.add(DATA_GLOW_COLOR, (Object)16747551);
        builder.add(DATA_VISUAL_SEED, (Object)0);
        builder.add(DATA_VISUAL_ACTIVE, (Object)true);
        builder.add(DATA_SPELL_ID, (Object)"fireball");
    }

    public void init(Vec3d spawnPos, Vec3d direction) {
        this.startPos = spawnPos;
        this.direction = direction.normalize();
        this.setPosition(spawnPos.x, spawnPos.y, spawnPos.z);
        this.lastPos = spawnPos;
        this.setVelocity(this.direction.multiply(1.8));
        this.collisionCooldown = 0;
        this.dataTracker.set(DATA_VISUAL_SEED, (Object)this.random.nextInt());
        this.dataTracker.set(DATA_VISUAL_ACTIVE, (Object)true);
        this.visualTicks = 0;
    }

    public void setSpellPower(float damage, int fireSeconds) {
        this.dataTracker.set(DATA_DAMAGE, (Object)Float.valueOf(damage));
        this.dataTracker.set(DATA_FIRE_SECONDS, (Object)fireSeconds);
    }

    private void setOwnerEntity(LivingEntity owner) {
        this.cachedOwner = owner;
        if (owner != null) {
            this.dataTracker.set(OWNER_UUID, (Object)owner.getUuidAsString());
        } else {
            this.dataTracker.set(OWNER_UUID, (Object)"");
        }
    }

    @Override
    public String nomorespell$getSpellId() {
        return (String)this.dataTracker.get(DATA_SPELL_ID);
    }

    private LivingEntity getOwnerEntity() {
        World class_19372;
        if (this.cachedOwner != null && this.cachedOwner.isAlive()) {
            return this.cachedOwner;
        }
        String id = (String)this.dataTracker.get(OWNER_UUID);
        if (id != null && !id.isEmpty() && (class_19372 = this.getWorld()) instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)class_19372;
            try {
                UUID uuid = UUID.fromString(id);
                Entity entity = serverWorld.getEntity(uuid);
                if (entity instanceof LivingEntity) {
                    LivingEntity living;
                    this.cachedOwner = living = (LivingEntity)entity;
                    return living;
                }
            }
            catch (IllegalArgumentException ignored) {
                this.dataTracker.set(OWNER_UUID, (Object)"");
            }
        }
        return null;
    }

    public void tick() {
        if (this.getWorld().isClient) {
            return;
        }
        World class_19372 = this.getWorld();
        if (!(class_19372 instanceof ServerWorld)) {
            this.cleanup();
            this.discard();
            return;
        }
        ServerWorld serverWorld = (ServerWorld)class_19372;
        try {
            if (this.direction == null) {
                Vec3d velocity = this.getVelocity();
                Vec3d VanillaChestLootTableGenerator = this.direction = velocity.lengthSquared() > 1.0E-4 ? velocity.normalize() : new Vec3d(0.0, 0.0, 0.0);
            }
            if (this.startPos == null) {
                this.startPos = this.getPos();
            }
            this.setVelocity(this.direction.multiply(1.8));
            Vec3d fromPos = this.getPos();
            Vec3d nextPos = fromPos.add(this.getVelocity());
            this.setPosition(nextPos.x, nextPos.y, nextPos.z);
            if (((Boolean)this.dataTracker.get(DATA_VISUAL_ACTIVE)).booleanValue()) {
                this.ensureDisplays(serverWorld);
                this.updateDisplays(nextPos, this.visualTicks);
                this.spawnFlameParticles(serverWorld, nextPos);
                ++this.visualTicks;
                if (this.visualTicks >= 30) {
                    this.cleanupDisplays();
                    this.dataTracker.set(DATA_VISUAL_ACTIVE, (Object)false);
                }
            }
            if (this.startPos.squaredDistanceTo(nextPos) > 400.0) {
                this.cleanup();
                this.discard();
                return;
            }
            if (this.collisionCooldown > 0) {
                --this.collisionCooldown;
            }
            if (!this.hitProcessed && this.checkEntityCollision(serverWorld, fromPos, nextPos)) {
                this.hitProcessed = true;
                this.cleanup();
                this.discard();
                return;
            }
            if (!this.hitProcessed && this.checkBlockCollision(serverWorld, this.lastPos != null ? this.lastPos : fromPos, nextPos)) {
                this.hitProcessed = true;
                this.cleanup();
                this.discard();
                return;
            }
            this.lastPos = nextPos;
        }
        catch (Exception ignored) {
            this.cleanup();
            this.discard();
        }
    }

    private void ensureDisplays(ServerWorld world) {
        if (!((Boolean)this.dataTracker.get(DATA_VISUAL_ACTIVE)).booleanValue()) {
            return;
        }
        if (this.markerEntity != null && this.markerEntity.isAlive()) {
            return;
        }
        this.cleanupDisplays();
        this.buildOffsets();
        this.markerEntity = new DisplayEntity.class_8115(EntityType.field_42460, (World)world);
        this.markerEntity.setBlockState(Blocks.field_10124.getDefaultState());
        this.markerEntity.setPosition(this.getX(), this.getY(), this.getZ());
        this.markerEntity.setNoGravity(true);
        this.markerEntity.setShadowRadius(0.0f);
        this.markerEntity.setInterpolationDuration(1);
        this.markerEntity.setTransformation(new AffineTransformation(new Vector3f(0.0f, 0.0f, 0.0f), new Quaternionf(), new Vector3f(0.01f, 0.01f, 0.01f), new Quaternionf()));
        world.spawnEntity((Entity)this.markerEntity);
        for (int i = 0; i < this.sphereOffsets.size(); ++i) {
            int roll = this.getColorRoll(i);
            DisplayEntity.class_8115 orb = new DisplayEntity.class_8115(EntityType.field_42460, (World)world);
            BlockState blockState = this.pickFireBlock(roll);
            orb.setBlockState(blockState);
            orb.setPosition(this.getX(), this.getY(), this.getZ());
            orb.setNoGravity(true);
            orb.setShadowRadius(0.0f);
            orb.setInterpolationDuration(1);
            orb.setGlowing(true);
            orb.setGlowColorOverride(this.pickGlowColor(roll));
            float scale = i < 110 ? 0.11f : 0.07f;
            orb.setTransformation(new AffineTransformation(new Vector3f(0.0f, 0.0f, 0.0f), new Quaternionf(), new Vector3f(scale, scale, scale), new Quaternionf()));
            world.spawnEntity((Entity)orb);
            this.sphereDisplays.add(orb);
        }
        world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.field_14970, SoundCategory.field_15248, 0.6f, 1.2f);
    }

    private void buildOffsets() {
        if (!this.sphereOffsets.isEmpty()) {
            return;
        }
        for (int lat = 0; lat <= 10; ++lat) {
            double v = (double)lat / 10.0;
            double theta = v * Math.PI;
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);
            for (int lon = 0; lon < 10; ++lon) {
                double u = (double)lon / 10.0;
                double phi = u * Math.PI * 2.0;
                double sinPhi = Math.sin(phi);
                double cosPhi = Math.cos(phi);
                double x = cosPhi * sinTheta;
                double y = cosTheta;
                double z = sinPhi * sinTheta;
                this.sphereOffsets.add(this.jitterOffset(new Vec3d(x, y, z).multiply(0.36)));
            }
        }
        for (int i = 0; i < 14; ++i) {
            double angle = Math.PI * 2 * (double)i / 14.0;
            double x = Math.cos(angle) * 0.58;
            double z = Math.sin(angle) * 0.58;
            double y = Math.sin(angle * 2.0) * 0.08;
            this.sphereOffsets.add(this.jitterOffset(new Vec3d(x, y, z)));
        }
        this.sphereOffsets.add(new Vec3d(0.0, 0.3596, 0.0));
        this.sphereOffsets.add(new Vec3d(0.0, -0.3016, 0.0));
    }

    private Vec3d jitterOffset(Vec3d base) {
        double jx = (this.random.nextDouble() - 0.5) * 0.018;
        double jy = (this.random.nextDouble() - 0.5) * 0.018;
        double jz = (this.random.nextDouble() - 0.5) * 0.018;
        return new Vec3d(base.x + jx, base.y + jy, base.z + jz);
    }

    private int getColorRoll(int index) {
        int seed = (Integer)this.dataTracker.get(DATA_VISUAL_SEED);
        Random seeded = Random.create((long)((long)seed ^ (long)index * 7349L));
        return seeded.nextInt(100);
    }

    private BlockState pickFireBlock(int roll) {
        if (roll < 68) {
            return Blocks.field_10210.getDefaultState();
        }
        if (roll < 88) {
            return Blocks.field_10542.getDefaultState();
        }
        return Blocks.field_10058.getDefaultState();
    }

    private int pickGlowColor(int roll) {
        if (roll < 68) {
            return 16747551;
        }
        if (roll < 88) {
            return 16765786;
        }
        return 16734756;
    }

    private void updateDisplays(Vec3d center, int tick) {
        if (this.markerEntity != null && this.markerEntity.isAlive()) {
            this.markerEntity.setPosition(center.x, center.y, center.z);
        }
        if (this.sphereDisplays.isEmpty()) {
            return;
        }
        float angle = (float)tick * 0.2f;
        float cos = MathHelper.cos((float)angle);
        float sin = MathHelper.sin((float)angle);
        for (int i = 0; i < this.sphereDisplays.size(); ++i) {
            DisplayEntity.class_8115 orb = this.sphereDisplays.get(i);
            if (orb == null || !orb.isAlive()) continue;
            Vec3d offset = this.sphereOffsets.get(i);
            double rx = offset.x * (double)cos - offset.z * (double)sin;
            double rz = offset.x * (double)sin + offset.z * (double)cos;
            double ry = offset.y + Math.sin((double)(tick + i) * 0.2) * (i < 110 ? 0.015 : 0.035);
            orb.setPosition(center.x + rx, center.y + ry, center.z + rz);
        }
    }

    private void spawnFlameParticles(ServerWorld world, Vec3d center) {
        int count = 6 + world.getRandom().nextInt(3);
        for (int i = 0; i < count; ++i) {
            double ox = (world.getRandom().nextDouble() - 0.5) * 0.4;
            double oy = (world.getRandom().nextDouble() - 0.5) * 0.4;
            double oz = (world.getRandom().nextDouble() - 0.5) * 0.4;
            int color = this.pickParticleColor(world.getRandom());
            float size = 0.55f + world.getRandom().nextFloat() * 0.35f;
            double speed = 0.01 + world.getRandom().nextDouble() * 0.01;
            world.spawnParticles((ParticleEffect)new DustParticleEffect(color, size), center.x + ox, center.y + oy, center.z + oz, 1, 0.0, 0.0, 0.0, speed);
            if (!(world.getRandom().nextFloat() < 0.4f)) continue;
            world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, center.x + ox, center.y + oy, center.z + oz, 1, 0.0, 0.0, 0.0, 0.01);
        }
    }

    private int pickParticleColor(Random random) {
        int roll = random.nextInt(100);
        if (roll < 55) {
            return 16747551;
        }
        if (roll < 82) {
            return 16765786;
        }
        return 16731933;
    }

    private boolean checkEntityCollision(ServerWorld world, Vec3d from, Vec3d to) {
        LivingEntity owner = this.getOwnerEntity();
        Box box = new Box(from, to).expand(0.7);
        List hits = world.getOtherEntities((Entity)this, box, entity -> {
            LivingEntity living;
            return entity instanceof LivingEntity && (living = (LivingEntity)entity).isAlive() && entity != owner;
        });
        if (hits.isEmpty()) {
            return false;
        }
        Entity entity2 = hits.stream().min(Comparator.comparingDouble(candidate -> candidate.squaredDistanceTo(from))).orElse(null);
        if (entity2 instanceof LivingEntity) {
            LivingEntity target = (LivingEntity)entity2;
            Vec3d impact = target.getBoundingBox().expand(0.25).raycast(from, to).orElse(target.getPos().add(0.0, (double)target.getHeight() * 0.5, 0.0));
            return SpellFireballEntity.applyDirectHit(world, owner, target, impact, ((Float)this.dataTracker.get(DATA_DAMAGE)).floatValue(), (Integer)this.dataTracker.get(DATA_FIRE_SECONDS));
        }
        return false;
    }

    public static boolean applyDirectHit(ServerWorld world, LivingEntity owner, LivingEntity target, Vec3d impactPos, float damage, int fireSeconds) {
        DamageSource source;
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (owner != null && target == owner) {
            return false;
        }
        Vec3d finalImpact = impactPos != null ? impactPos : target.getPos().add(0.0, (double)target.getHeight() * 0.5, 0.0);
        target.setOnFireFor((float)fireSeconds);
        DamageSource class_12822 = source = owner != null ? world.getDamageSources().indirectMagic((Entity)target, (Entity)owner) : world.getDamageSources().magic();
        if (owner instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)owner;
            CombatXpTracker.markSpellDamage(target, player, "fireball");
        }
        target.damage(world, source, damage);
        world.playSound(null, finalImpact.x, finalImpact.y, finalImpact.z, Nomorespell.FIREBALL_EXPLOSION_SOUND, SoundCategory.field_15248, 0.7f, 1.0f);
        world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, finalImpact.x, finalImpact.y, finalImpact.z, 28, 0.22, 0.22, 0.22, 0.03);
        world.spawnParticles((ParticleEffect)ParticleTypes.field_11239, finalImpact.x, finalImpact.y, finalImpact.z, 6, 0.12, 0.12, 0.12, 0.0);
        return true;
    }

    private boolean checkBlockCollision(ServerWorld world, Vec3d from, Vec3d to) {
        double distance = from.distanceTo(to);
        int steps = Math.max(1, (int)Math.ceil(distance * 10.0));
        Vec3d delta = to.subtract(from).multiply(1.0 / (double)steps);
        Vec3d check = from;
        for (int i = 0; i <= steps; ++i) {
            BlockPos pos = BlockPos.ofFloored((Position)check);
            BlockState state = world.getBlockState(pos);
            if (!state.isAir() && !state.getCollisionShape((BlockView)world, pos).isEmpty()) {
                world.playSound(null, check.x, check.y, check.z, Nomorespell.FIREBALL_EXPLOSION_SOUND, SoundCategory.field_15248, 0.6f, 1.1f);
                world.spawnParticles((ParticleEffect)ParticleTypes.field_11240, check.x, check.y, check.z, 24, 0.25, 0.25, 0.25, 0.02);
                return true;
            }
            check = check.add(delta);
        }
        return false;
    }

    private void cleanupDisplays() {
        if (this.markerEntity != null) {
            this.markerEntity.discard();
            this.markerEntity = null;
        }
        for (DisplayEntity.class_8115 orb : this.sphereDisplays) {
            if (orb == null) continue;
            orb.discard();
        }
        this.sphereDisplays.clear();
    }

    private void cleanup() {
        if (this.cleanedUp) {
            return;
        }
        this.cleanedUp = true;
        this.hitProcessed = true;
        if (!this.getWorld().isClient) {
            this.cleanupDisplays();
            this.sphereOffsets.clear();
            this.dataTracker.set(DATA_VISUAL_ACTIVE, (Object)false);
        }
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public void remove(Entity.class_5529 reason) {
        this.cleanup();
        super.remove(reason);
    }
}

