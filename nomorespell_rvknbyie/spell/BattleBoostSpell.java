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

public class BattleBoostSpell
extends Spell {
    public static final String SPELL_ID = "battle_boost";

    public BattleBoostSpell() {
        super(SPELL_ID, "Battle Boost", "Empower all nearby allies with combat buffs for 20 seconds.", Spell.SpellRank.D, Spell.SpellCategory.SUPPORT, 700, 1);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startBattleBoost(player);
    }
}

