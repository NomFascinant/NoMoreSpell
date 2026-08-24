/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellVisualsManager;

public class DivineShieldSpell
extends Spell {
    public static final String SPELL_ID = "divine_shield";

    public DivineShieldSpell() {
        super(SPELL_ID, "Divine Shield", "Instant heal plus temporary shield and resistance.", Spell.SpellRank.B, Spell.SpellCategory.HEAL, 500, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startDivineShield(player);
    }
}

