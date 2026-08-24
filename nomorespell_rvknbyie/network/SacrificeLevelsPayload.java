/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Identifier
 *  net.minecraft.CustomPayload
 *  net.minecraft.CustomPayload$class_9154
 *  net.minecraft.RegistryByteBuf
 *  net.minecraft.PacketCodecs
 *  net.minecraft.PacketCodec
 */
package nomorespell_rvknbyie.network;

import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.RegistryByteBuf;
import net.minecraft.PacketCodecs;
import net.minecraft.PacketCodec;

public record SacrificeLevelsPayload(int levels) implements CustomPayload
{
    public static final CustomPayload.class_9154<SacrificeLevelsPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"sacrifice_levels"));
    public static final PacketCodec<RegistryByteBuf, SacrificeLevelsPayload> CODEC = PacketCodec.tuple((PacketCodec)PacketCodecs.VAR_INT, SacrificeLevelsPayload::levels, SacrificeLevelsPayload::new);

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

