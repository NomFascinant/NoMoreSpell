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

public class MeteorStrikeSpell
extends Spell {
    public static final String SPELL_ID = "meteor_strike";

    public MeteorStrikeSpell() {
        super(SPELL_ID, "Meteor Strike", "Summon a devastating meteor. Paralyzes enemies in your gaze during charge.", Spell.SpellRank.B, Spell.SpellCategory.ATTACK, 300, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startMeteorStrike(player);
    }
}

