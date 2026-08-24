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

public record RainOfPicksRenderPayload(int casterEntityId, boolean active, int startAge, int durationTicks, int seed, int projectileCount, int homingProjectileCount) implements CustomPayload
{
    public static final CustomPayload.class_9154<RainOfPicksRenderPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"rain_of_picks_render"));
    public static final PacketCodec<RegistryByteBuf, RainOfPicksRenderPayload> CODEC = PacketCodec.of((value, buf) -> {
        PacketCodecs.VAR_INT.encode(buf, (Object)value.casterEntityId());
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.active() ? 1 : 0)));
        PacketCodecs.VAR_INT.encode(buf, (Object)value.startAge());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.durationTicks());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.seed());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.projectileCount());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.homingProjectileCount());
    }, buf -> new RainOfPicksRenderPayload((Integer)PacketCodecs.VAR_INT.decode(buf), (Byte)PacketCodecs.BYTE.decode(buf) != 0, (Integer)PacketCodecs.VAR_INT.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf)));

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

