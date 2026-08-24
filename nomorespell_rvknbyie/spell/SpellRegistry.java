/*
 * Decompiled with CFR 0.152.
 */
package nomorespell_rvknbyie.spell;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import nomorespell_rvknbyie.spell.BloodEclipseSpell;
import nomorespell_rvknbyie.spell.DomainExpansionSpell;
import nomorespell_rvknbyie.spell.FireballSpell;
import nomorespell_rvknbyie.spell.FrostNovaSpell;
import nomorespell_rvknbyie.spell.HealingTouchSpell;
import nomorespell_rvknbyie.spell.RainOfPicksSpell;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SwiftnessAuraSpell;
import nomorespell_rvknbyie.spell.VerdantHaloSpell;

public final class SpellRegistry {
    private static final Map<String, Spell> SPELLS = new HashMap<String, Spell>();

    public static void register(Spell spell) {
        SPELLS.put(spell.getId(), spell);
    }

    public static Spell getSpell(String id) {
        return SPELLS.get(id);
    }

    public static Collection<Spell> getAllSpells() {
        return SPELLS.values();
    }

    public static boolean exists(String id) {
        return SPELLS.containsKey(id);
    }

    public static void init() {
        SpellRegistry.register(new FireballSpell());
        SpellRegistry.register(new HealingTouchSpell());
        SpellRegistry.register(new SwiftnessAuraSpell());
        SpellRegistry.register(new FrostNovaSpell());
        SpellRegistry.register(new RainOfPicksSpell());
        SpellRegistry.register(new VerdantHaloSpell());
        SpellRegistry.register(new BloodEclipseSpell());
        SpellRegistry.register(new DomainExpansionSpell());
    }
}

