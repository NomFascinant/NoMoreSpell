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

public record VerdantHaloRenderPayload(int casterEntityId, boolean active, int startAge, int durationTicks) implements CustomPayload
{
    public static final CustomPayload.class_9154<VerdantHaloRenderPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"verdant_halo_render"));
    public static final PacketCodec<RegistryByteBuf, VerdantHaloRenderPayload> CODEC = PacketCodec.of((value, buf) -> {
        PacketCodecs.VAR_INT.encode(buf, (Object)value.casterEntityId());
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.active() ? 1 : 0)));
        PacketCodecs.VAR_INT.encode(buf, (Object)value.startAge());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.durationTicks());
    }, buf -> new VerdantHaloRenderPayload((Integer)PacketCodecs.VAR_INT.decode(buf), (Byte)PacketCodecs.BYTE.decode(buf) != 0, (Integer)PacketCodecs.VAR_INT.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf)));

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

