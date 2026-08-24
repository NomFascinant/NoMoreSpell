/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ItemStack
 *  net.minecraft.MathHelper
 */
package nomorespell_rvknbyie.spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import nomorespell_rvknbyie.spell.SpellBookComponents;
import nomorespell_rvknbyie.spell.SpellRegistry;

public final class SpellBookData {
    private static final Map<Integer, Integer> XP_CACHE = new HashMap<Integer, Integer>();
    private static final Map<Integer, Integer> SACRIFICE_XP_CACHE = new HashMap<Integer, Integer>();
    private static final int MAX_LEVEL_CAP = 1000;
    private static final int MAX_XP_CAP = 2000000000;
    private static final int MAX_SOULS_CAP = 2000000000;
    private static final long COOLDOWN_DAY_GUARD = 24000L;

    public static void initializeIfNeeded(ItemStack stack) {
        if (stack == null) {
            return;
        }
        if (!stack.contains(SpellBookComponents.EQUIPPED_SLOT1)) {
            stack.set(SpellBookComponents.EQUIPPED_SLOT1, (Object)"");
        }
        if (!stack.contains(SpellBookComponents.EQUIPPED_SLOT2)) {
            stack.set(SpellBookComponents.EQUIPPED_SLOT2, (Object)"");
        }
        if (!stack.contains(SpellBookComponents.PURCHASED_SPELLS)) {
            ArrayList<String> purchased = new ArrayList<String>();
            purchased.add("fireball");
            stack.set(SpellBookComponents.PURCHASED_SPELLS, purchased);
        }
        if (!stack.contains(SpellBookComponents.CURRENT_XP)) {
            stack.set(SpellBookComponents.CURRENT_XP, (Object)0);
        }
        if (!stack.contains(SpellBookComponents.CURRENT_LEVEL)) {
            stack.set(SpellBookComponents.CURRENT_LEVEL, (Object)1);
        }
        if (!stack.contains(SpellBookComponents.SOULS_POINTS)) {
            stack.set(SpellBookComponents.SOULS_POINTS, (Object)0);
        }
        if (!stack.contains(SpellBookComponents.COOLDOWN_SLOT1)) {
            stack.set(SpellBookComponents.COOLDOWN_SLOT1, (Object)0L);
        }
        if (!stack.contains(SpellBookComponents.COOLDOWN_SLOT2)) {
            stack.set(SpellBookComponents.COOLDOWN_SLOT2, (Object)0L);
        }
        if (!stack.contains(SpellBookComponents.OWNER_NAME)) {
            stack.set(SpellBookComponents.OWNER_NAME, (Object)"");
        }
        SpellBookData.sanitizeRuntimeData(stack);
    }

    public static void sanitizeRuntimeData(ItemStack stack) {
        if (stack == null) {
            return;
        }
        long slot1Cooldown = (Long)stack.get(SpellBookComponents.COOLDOWN_SLOT1);
        long slot2Cooldown = (Long)stack.get(SpellBookComponents.COOLDOWN_SLOT2);
        if (slot1Cooldown < 0L) {
            slot1Cooldown = 0L;
        }
        if (slot2Cooldown < 0L) {
            slot2Cooldown = 0L;
        }
        stack.set(SpellBookComponents.COOLDOWN_SLOT1, (Object)slot1Cooldown);
        stack.set(SpellBookComponents.COOLDOWN_SLOT2, (Object)slot2Cooldown);
        int level = (Integer)stack.get(SpellBookComponents.CURRENT_LEVEL);
        level = MathHelper.clamp((int)level, (int)1, (int)1000);
        stack.set(SpellBookComponents.CURRENT_LEVEL, (Object)level);
        int xp = (Integer)stack.get(SpellBookComponents.CURRENT_XP);
        xp = MathHelper.clamp((int)xp, (int)0, (int)2000000000);
        stack.set(SpellBookComponents.CURRENT_XP, (Object)xp);
        int souls = (Integer)stack.get(SpellBookComponents.SOULS_POINTS);
        souls = MathHelper.clamp((int)souls, (int)0, (int)2000000000);
        stack.set(SpellBookComponents.SOULS_POINTS, (Object)souls);
        String slot1Spell = (String)stack.get(SpellBookComponents.EQUIPPED_SLOT1);
        String slot2Spell = (String)stack.get(SpellBookComponents.EQUIPPED_SLOT2);
        if (slot1Spell == null) {
            slot1Spell = "";
        }
        if (slot2Spell == null) {
            slot2Spell = "";
        }
        if (!slot1Spell.isEmpty() && !SpellRegistry.exists(slot1Spell)) {
            slot1Spell = "";
        }
        if (!slot2Spell.isEmpty() && !SpellRegistry.exists(slot2Spell)) {
            slot2Spell = "";
        }
        stack.set(SpellBookComponents.EQUIPPED_SLOT1, (Object)slot1Spell);
        stack.set(SpellBookComponents.EQUIPPED_SLOT2, (Object)slot2Spell);
        String ownerName = (String)stack.get(SpellBookComponents.OWNER_NAME);
        if (ownerName == null) {
            ownerName = "";
        }
        stack.set(SpellBookComponents.OWNER_NAME, (Object)ownerName);
        ArrayList purchased = (ArrayList)stack.get(SpellBookComponents.PURCHASED_SPELLS);
        if (purchased == null) {
            purchased = new ArrayList();
        }
        ArrayList<String> cleaned = new ArrayList<String>();
        HashSet<String> seen = new HashSet<String>();
        for (String id : purchased) {
            if (id == null || id.isEmpty() || !SpellRegistry.exists(id) || !seen.add(id)) continue;
            cleaned.add(id);
        }
        if (cleaned.isEmpty()) {
            cleaned.add("fireball");
        }
        stack.set(SpellBookComponents.PURCHASED_SPELLS, cleaned);
        if (!slot1Spell.isEmpty() && !SpellBookData.hasPurchasedSpell(stack, slot1Spell)) {
            stack.set(SpellBookComponents.EQUIPPED_SLOT1, (Object)"");
        }
        if (!slot2Spell.isEmpty() && !SpellBookData.hasPurchasedSpell(stack, slot2Spell)) {
            stack.set(SpellBookComponents.EQUIPPED_SLOT2, (Object)"");
        }
    }

    public static String getEquippedSlot1(ItemStack stack) {
        String value = (String)stack.get(SpellBookComponents.EQUIPPED_SLOT1);
        return value == null ? "" : value;
    }

    public static String getEquippedSlot2(ItemStack stack) {
        String value = (String)stack.get(SpellBookComponents.EQUIPPED_SLOT2);
        return value == null ? "" : value;
    }

    public static void setEquippedSlot1(ItemStack stack, String spellId) {
        stack.set(SpellBookComponents.EQUIPPED_SLOT1, (Object)(spellId != null ? spellId : ""));
    }

    public static void setEquippedSlot2(ItemStack stack, String spellId) {
        stack.set(SpellBookComponents.EQUIPPED_SLOT2, (Object)(spellId != null ? spellId : ""));
    }

    public static boolean canModifySlot(ItemStack stack, int slotIndex, long currentTick) {
        if (stack == null) {
            return false;
        }
        if (slotIndex != 1 && slotIndex != 2) {
            return false;
        }
        return !SpellBookData.isOnCooldown(stack, slotIndex, currentTick);
    }

    public static boolean isSpellCoolingDown(ItemStack stack, String spellId, long currentTick) {
        if (stack == null || spellId == null || spellId.isEmpty()) {
            return false;
        }
        if (spellId.equals(SpellBookData.getEquippedSlot1(stack)) && SpellBookData.isOnCooldown(stack, 1, currentTick)) {
            return true;
        }
        return spellId.equals(SpellBookData.getEquippedSlot2(stack)) && SpellBookData.isOnCooldown(stack, 2, currentTick);
    }

    public static int getCurrentXp(ItemStack stack) {
        int xp = (Integer)stack.get(SpellBookComponents.CURRENT_XP);
        if (xp < 0) {
            xp = 0;
        }
        return Math.min(xp, 2000000000);
    }

    public static void setCurrentXp(ItemStack stack, int xp) {
        int clamped = MathHelper.clamp((int)xp, (int)0, (int)2000000000);
        stack.set(SpellBookComponents.CURRENT_XP, (Object)clamped);
    }

    public static int getCurrentLevel(ItemStack stack) {
        int level = (Integer)stack.get(SpellBookComponents.CURRENT_LEVEL);
        if (level < 1) {
            level = 1;
        }
        return Math.min(level, 1000);
    }

    public static void setCurrentLevel(ItemStack stack, int level) {
        int clamped = MathHelper.clamp((int)level, (int)1, (int)1000);
        stack.set(SpellBookComponents.CURRENT_LEVEL, (Object)clamped);
    }

    public static int getSoulsPoints(ItemStack stack) {
        int souls = (Integer)stack.get(SpellBookComponents.SOULS_POINTS);
        if (souls < 0) {
            souls = 0;
        }
        return Math.min(souls, 2000000000);
    }

    public static void setSoulsPoints(ItemStack stack, int souls) {
        int clamped = MathHelper.clamp((int)souls, (int)0, (int)2000000000);
        stack.set(SpellBookComponents.SOULS_POINTS, (Object)clamped);
    }

    public static long getCooldownEndTick(ItemStack stack) {
        return 0L;
    }

    public static void setCooldownEndTick(ItemStack stack, long tick) {
        SpellBookData.setCooldownEndTick(stack, 1, tick);
        SpellBookData.setCooldownEndTick(stack, 2, tick);
    }

    public static long getCooldownEndTick(ItemStack stack, int slotIndex) {
        if (slotIndex == 2) {
            return (Long)stack.get(SpellBookComponents.COOLDOWN_SLOT2);
        }
        return (Long)stack.get(SpellBookComponents.COOLDOWN_SLOT1);
    }

    public static void setCooldownEndTick(ItemStack stack, int slotIndex, long tick) {
        if (slotIndex == 2) {
            stack.set(SpellBookComponents.COOLDOWN_SLOT2, (Object)Math.max(0L, tick));
        } else {
            stack.set(SpellBookComponents.COOLDOWN_SLOT1, (Object)Math.max(0L, tick));
        }
    }

    public static String getOwnerName(ItemStack stack) {
        String value = (String)stack.get(SpellBookComponents.OWNER_NAME);
        return value == null ? "" : value;
    }

    public static void setOwnerName(ItemStack stack, String ownerName) {
        stack.set(SpellBookComponents.OWNER_NAME, (Object)(ownerName != null ? ownerName : ""));
    }

    public static boolean isOnCooldown(ItemStack stack, long currentTick) {
        return currentTick < SpellBookData.getCooldownEndTick(stack, 1) || currentTick < SpellBookData.getCooldownEndTick(stack, 2);
    }

    public static boolean isOnCooldown(ItemStack stack, int slotIndex, long currentTick) {
        return currentTick < SpellBookData.getCooldownEndTick(stack, slotIndex);
    }

    public static void synchronizeCooldowns(ItemStack stack, long currentTick) {
        if (stack == null) {
            return;
        }
        long slot1 = Math.max(0L, SpellBookData.getCooldownEndTick(stack, 1));
        long slot2 = Math.max(0L, SpellBookData.getCooldownEndTick(stack, 2));
        String slot1Spell = SpellBookData.getEquippedSlot1(stack);
        String slot2Spell = SpellBookData.getEquippedSlot2(stack);
        if (!slot1Spell.isEmpty() && slot1Spell.equals(slot2Spell)) {
            long sharedEnd = Math.max(slot1, slot2);
            SpellBookData.setCooldownEndTick(stack, 1, sharedEnd);
            SpellBookData.setCooldownEndTick(stack, 2, sharedEnd);
            return;
        }
        if (slot1 > 0L && currentTick > slot1 + 24000L) {
            SpellBookData.setCooldownEndTick(stack, 1, currentTick);
        }
        if (slot2 > 0L && currentTick > slot2 + 24000L) {
            SpellBookData.setCooldownEndTick(stack, 2, currentTick);
        }
    }

    public static void triggerSpellCooldown(ItemStack stack, String spellId, long cooldownEndTick) {
        if (stack == null || spellId == null || spellId.isEmpty()) {
            return;
        }
        if (spellId.equals(SpellBookData.getEquippedSlot1(stack))) {
            SpellBookData.setCooldownEndTick(stack, 1, cooldownEndTick);
        }
        if (spellId.equals(SpellBookData.getEquippedSlot2(stack))) {
            SpellBookData.setCooldownEndTick(stack, 2, cooldownEndTick);
        }
    }

    public static int getXpNeededForLevel(int level) {
        if (level < 1) {
            level = 1;
        }
        int lvl = level;
        return XP_CACHE.computeIfAbsent(lvl, key -> {
            int base = 100 + (key - 1) * 10;
            int bonus = (key - 1) / 5 * 50;
            return base + bonus;
        });
    }

    public static LevelUpResult addXpAndProcess(ItemStack stack, int amount) {
        if (amount <= 0) {
            return new LevelUpResult(0, 0);
        }
        int level = SpellBookData.getCurrentLevel(stack);
        int xp = SpellBookData.getCurrentXp(stack) + amount;
        int souls = SpellBookData.getSoulsPoints(stack);
        int levelsGained = 0;
        int soulsAwarded = 0;
        int xpNeeded = SpellBookData.getXpNeededForLevel(level);
        while (xp >= xpNeeded) {
            xp -= xpNeeded;
            ++levelsGained;
            xpNeeded = SpellBookData.getXpNeededForLevel(++level);
            if (level % 5 != 0) continue;
            ++souls;
            ++soulsAwarded;
        }
        SpellBookData.setCurrentLevel(stack, level);
        SpellBookData.setCurrentXp(stack, xp);
        SpellBookData.setSoulsPoints(stack, souls);
        return new LevelUpResult(levelsGained, soulsAwarded);
    }

    public static int getSacrificeXpForLevel(int level) {
        int lvl = MathHelper.clamp((int)level, (int)1, (int)1000);
        return SACRIFICE_XP_CACHE.computeIfAbsent(lvl, key -> {
            if (key >= 90) {
                return 500;
            }
            if (key >= 80) {
                return 425;
            }
            if (key >= 70) {
                return 400;
            }
            if (key >= 60) {
                return 350;
            }
            if (key >= 50) {
                return 300;
            }
            if (key >= 40) {
                return 250;
            }
            if (key >= 30) {
                return 200;
            }
            if (key >= 20) {
                return 150;
            }
            if (key >= 10) {
                return 100;
            }
            return 75;
        });
    }

    public static boolean hasPurchasedSpell(ItemStack stack, String spellId) {
        if (spellId == null || spellId.isEmpty()) {
            return false;
        }
        List list = (List)stack.get(SpellBookComponents.PURCHASED_SPELLS);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return list.contains(spellId);
    }

    public static void addPurchasedSpell(ItemStack stack, String spellId) {
        if (spellId == null || spellId.isEmpty()) {
            return;
        }
        if (!SpellRegistry.exists(spellId)) {
            return;
        }
        if (SpellBookData.hasPurchasedSpell(stack, spellId)) {
            return;
        }
        ArrayList<String> list = (ArrayList<String>)stack.get(SpellBookComponents.PURCHASED_SPELLS);
        if (list == null) {
            list = new ArrayList<String>();
        }
        list = new ArrayList(list);
        list.add(spellId);
        stack.set(SpellBookComponents.PURCHASED_SPELLS, list);
    }

    public static boolean isSpellAvailable(ItemStack stack, String spellId) {
        if (spellId == null || spellId.isEmpty()) {
            return false;
        }
        if (!SpellRegistry.exists(spellId)) {
            return false;
        }
        return SpellBookData.hasPurchasedSpell(stack, spellId);
    }

    public static void clearEquippedSpell(ItemStack stack, String spellId) {
        if (spellId == null || spellId.isEmpty()) {
            return;
        }
        String slot1 = SpellBookData.getEquippedSlot1(stack);
        String slot2 = SpellBookData.getEquippedSlot2(stack);
        boolean changed = false;
        if (spellId.equals(slot1)) {
            SpellBookData.setEquippedSlot1(stack, "");
            changed = true;
        }
        if (spellId.equals(slot2)) {
            SpellBookData.setEquippedSlot2(stack, "");
            changed = true;
        }
        if (changed) {
            SpellBookData.setCooldownEndTick(stack, 1, 0L);
            SpellBookData.setCooldownEndTick(stack, 2, 0L);
        }
    }

    public static void resetAll(ItemStack stack) {
        stack.remove(SpellBookComponents.PURCHASED_SPELLS);
        stack.remove(SpellBookComponents.EQUIPPED_SLOT1);
        stack.remove(SpellBookComponents.EQUIPPED_SLOT2);
        stack.remove(SpellBookComponents.CURRENT_XP);
        stack.remove(SpellBookComponents.CURRENT_LEVEL);
        stack.remove(SpellBookComponents.SOULS_POINTS);
        stack.remove(SpellBookComponents.COOLDOWN_SLOT1);
        stack.remove(SpellBookComponents.COOLDOWN_SLOT2);
        stack.remove(SpellBookComponents.OWNER_NAME);
        SpellBookData.initializeIfNeeded(stack);
        SpellBookData.sanitizeRuntimeData(stack);
    }

    public record LevelUpResult(int levelsGained, int soulsAwarded) {
    }
}

