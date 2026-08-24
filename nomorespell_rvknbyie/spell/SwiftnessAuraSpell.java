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

public class SwiftnessAuraSpell
extends Spell {
    public static final String SPELL_ID = "swiftness_aura";

    public SwiftnessAuraSpell() {
        super(SPELL_ID, "Swiftness Aura", "Grant yourself speed and protection for 30 seconds.", Spell.SpellRank.E, Spell.SpellCategory.SUPPORT, 900, 1);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startSwiftnessAura(player, this.scaleDurationForDomain(player, 600));
    }
}

