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

public class GroupHealSpell
extends Spell {
    public static final String SPELL_ID = "group_heal";

    public GroupHealSpell() {
        super(SPELL_ID, "Group Heal", "Heal all nearby players within 5 blocks for 6 hearts.", Spell.SpellRank.D, Spell.SpellCategory.HEAL, 300, 1);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        if (player == null || !player.isAlive()) {
            return;
        }
        SpellVisualsManager.startGroupHeal(player, this.scaleAmountForDomain(player, 12.0f));
    }
}

