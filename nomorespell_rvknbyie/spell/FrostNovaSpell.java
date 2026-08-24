/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.StatusEffect
 *  net.minecraft.StatusEffectInstance
 *  net.minecraft.StatusEffects
 *  net.minecraft.LivingEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.Box
 *  net.minecraft.Vec3d
 *  net.minecraft.Identifier
 *  net.minecraft.ServerWorld
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 *  net.minecraft.RegistryKey
 *  net.minecraft.Registries
 *  net.minecraft.RegistryKeys
 */
package nomorespell_rvknbyie.spell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.StatusEffect;
import net.minecraft.StatusEffectInstance;
import net.minecraft.StatusEffects;
import net.minecraft.LivingEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.Box;
import net.minecraft.Vec3d;
import net.minecraft.Identifier;
import net.minecraft.ServerWorld;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import net.minecraft.RegistryKey;
import net.minecraft.Registries;
import net.minecraft.RegistryKeys;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellVisualsManager;

public class FrostNovaSpell
extends Spell {
    public static final String SPELL_ID = "frost_nova";
    private static final double EFFECT_RADIUS = 7.0;
    private static final float DAMAGE = 10.0f;
    private static final int SLOW_DURATION_TICKS = 100;
    private static final int FROST_DURATION_TICKS = 100;
    private static final float HIT_CHANCE = 0.9f;
    private static final Identifier FROST_EFFECT_ID = Identifier.of((String)"minecraft", (String)"frost");
    private static final RegistryKey<StatusEffect> FROST_EFFECT_KEY = RegistryKey.of((RegistryKey)RegistryKeys.STATUS_EFFECT, (Identifier)FROST_EFFECT_ID);

    public FrostNovaSpell() {
        super(SPELL_ID, "Frost Nova", "Summons jagged ice spikes under nearby enemies, dealing damage and freezing them.", Spell.SpellRank.D, Spell.SpellCategory.ATTACK, 120, 1);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        if (!(world instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld)world;
        Vec3d center = player.getPos();
        Box box = new Box(center.add(-7.0, -7.0, -7.0), center.add(7.0, 7.0, 7.0));
        List targets = serverWorld.getEntitiesByClass(LivingEntity.class, box, entity -> {
            PlayerEntity target;
            return entity.isAlive() && entity != player && (!(entity instanceof PlayerEntity) || !(target = (PlayerEntity)entity).isSpectator());
        });
        Optional frostEffect = Registries.STATUS_EFFECT.getOptionalValue(FROST_EFFECT_KEY);
        ArrayList<Vec3d> hitPositions = new ArrayList<Vec3d>();
        float damage = this.scaleAmountForDomain(player, 10.0f);
        int slowDuration = this.scaleDurationForDomain(player, 100);
        int frostDuration = this.scaleDurationForDomain(player, 100);
        boolean castSoundPlayed = false;
        for (LivingEntity target : targets) {
            if (target.getPos().squaredDistanceTo(center) > 49.0 || serverWorld.random.nextFloat() > 0.9f) continue;
            if (!castSoundPlayed) {
                castSoundPlayed = true;
                float pitch = 0.92f + (serverWorld.random.nextFloat() - 0.5f) * 0.16f;
                serverWorld.playSound(null, center.x, center.y, center.z, SoundEvents.field_15081, SoundCategory.field_15248, 0.8f, pitch);
                serverWorld.playSound(null, center.x, center.y, center.z, SoundEvents.field_15165, SoundCategory.field_15248, 0.8f, 0.75f + serverWorld.random.nextFloat() * 0.1f);
                serverWorld.playSound(null, center.x, center.y, center.z, SoundEvents.field_15045, SoundCategory.field_15248, 0.25f, 0.7f + serverWorld.random.nextFloat() * 0.1f);
            }
            target.damage(serverWorld, serverWorld.getDamageSources().magic(), damage);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5909, slowDuration, 0, false, true));
            frostEffect.ifPresent(effect -> target.addStatusEffect(new StatusEffectInstance(Registries.STATUS_EFFECT.getEntry(effect), frostDuration, 0, false, true)));
            hitPositions.add(target.getPos());
        }
        SpellVisualsManager.startFrostNova(player, hitPositions);
    }
}

