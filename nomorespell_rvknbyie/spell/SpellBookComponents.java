/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.Registry
 *  net.minecraft.Identifier
 *  net.minecraft.Registries
 *  net.minecraft.RegistryByteBuf
 *  net.minecraft.PacketCodecs
 *  net.minecraft.PacketCodec
 *  net.minecraft.ComponentType
 */
package nomorespell_rvknbyie.spell;

import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.Registry;
import net.minecraft.Identifier;
import net.minecraft.Registries;
import net.minecraft.RegistryByteBuf;
import net.minecraft.PacketCodecs;
import net.minecraft.PacketCodec;
import net.minecraft.ComponentType;

public final class SpellBookComponents {
    public static final ComponentType<String> EQUIPPED_SLOT1 = SpellBookComponents.register("equipped_slot1", Codec.STRING, PacketCodecs.STRING);
    public static final ComponentType<String> EQUIPPED_SLOT2 = SpellBookComponents.register("equipped_slot2", Codec.STRING, PacketCodecs.STRING);
    public static final ComponentType<List<String>> PURCHASED_SPELLS = SpellBookComponents.register("purchased_spells", Codec.list((Codec)Codec.STRING), PacketCodecs.collection(ArrayList::new, (PacketCodec)PacketCodecs.STRING));
    public static final ComponentType<Integer> CURRENT_XP = SpellBookComponents.register("current_xp", Codec.INT, PacketCodecs.VAR_INT);
    public static final ComponentType<Integer> CURRENT_LEVEL = SpellBookComponents.register("current_level", Codec.INT, PacketCodecs.VAR_INT);
    public static final ComponentType<Integer> SOULS_POINTS = SpellBookComponents.register("souls_points", Codec.INT, PacketCodecs.VAR_INT);
    public static final ComponentType<Long> COOLDOWN_SLOT1 = SpellBookComponents.register("slot1_cooldown_end", Codec.LONG, PacketCodecs.VAR_LONG);
    public static final ComponentType<Long> COOLDOWN_SLOT2 = SpellBookComponents.register("slot2_cooldown_end", Codec.LONG, PacketCodecs.VAR_LONG);
    public static final ComponentType<String> OWNER_NAME = SpellBookComponents.register("owner_name", Codec.STRING, PacketCodecs.STRING);

    public static void init() {
    }

    private static <T> ComponentType<T> register(String path, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec) {
        return (ComponentType)Registry.register((Registry)Registries.DATA_COMPONENT_TYPE, (Identifier)Identifier.of((String)"nomorespell-rvknbyie", (String)path), (Object)ComponentType.builder().codec(codec).packetCodec(packetCodec).build());
    }

    private SpellBookComponents() {
    }
}

