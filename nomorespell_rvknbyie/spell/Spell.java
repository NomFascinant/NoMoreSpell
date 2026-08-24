/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Formatting
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.ServerPlayerEntity
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.Formatting;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.ServerPlayerEntity;
import nomorespell_rvknbyie.spell.DomainExpansionManager;

public abstract class Spell {
    private final String id;
    private final String name;
    private final String description;
    private final SpellRank rank;
    private final SpellCategory category;
    private final int cooldownTicks;
    private final int soulsCost;

    public Spell(String id, String name, String description, SpellRank rank, SpellCategory category, int cooldownTicks, int soulsCost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rank = rank;
        this.category = category;
        this.cooldownTicks = cooldownTicks;
        this.soulsCost = soulsCost;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public SpellRank getRank() {
        return this.rank;
    }

    public SpellCategory getCategory() {
        return this.category;
    }

    public int getCooldownTicks() {
        return this.cooldownTicks;
    }

    public int getSoulsCost() {
        return this.soulsCost;
    }

    public abstract void cast(World var1, PlayerEntity var2, ItemStack var3);

    protected float scaleAmountForDomain(PlayerEntity player, float baseAmount) {
        ServerPlayerEntity serverPlayer;
        if (player instanceof ServerPlayerEntity && DomainExpansionManager.isInsideOwnDomain(serverPlayer = (ServerPlayerEntity)player)) {
            return baseAmount * 2.0f;
        }
        return baseAmount;
    }

    protected int scaleDurationForDomain(PlayerEntity player, int baseTicks) {
        ServerPlayerEntity serverPlayer;
        if (player instanceof ServerPlayerEntity && DomainExpansionManager.isInsideOwnDomain(serverPlayer = (ServerPlayerEntity)player)) {
            return baseTicks * 2;
        }
        return baseTicks;
    }

    protected boolean isDomainEmpowered(PlayerEntity player) {
        ServerPlayerEntity serverPlayer;
        return player instanceof ServerPlayerEntity && DomainExpansionManager.isInsideOwnDomain(serverPlayer = (ServerPlayerEntity)player);
    }

    public static enum SpellRank {
        E("E", Formatting.field_1080),
        D("D", Formatting.field_1077),
        C("C", Formatting.field_1078),
        B("B", Formatting.field_1065),
        A("A", Formatting.field_1061),
        S("S", Formatting.field_1076);

        private final String label;
        private final Formatting color;

        private SpellRank(String label, Formatting color) {
            this.label = label;
            this.color = color;
        }

        public String getLabel() {
            return this.label;
        }

        public Formatting getColor() {
            return this.color;
        }
    }

    public static enum SpellCategory {
        ATTACK,
        HEAL,
        SUPPORT,
        UTILITY;

    }
}

