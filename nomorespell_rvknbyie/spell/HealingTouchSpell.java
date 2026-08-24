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

public class HealingTouchSpell
extends Spell {
    public static final String SPELL_ID = "healing_touch";

    public HealingTouchSpell() {
        super(SPELL_ID, "Healing Touch", "Instantly restore 4 hearts to yourself.", Spell.SpellRank.E, Spell.SpellCategory.HEAL, 160, 1);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startHealingTouch(player, this.scaleAmountForDomain(player, 8.0f));
    }
}

