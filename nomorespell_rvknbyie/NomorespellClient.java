/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
 *  net.minecraft.MovingSoundInstance
 *  net.minecraft.PositionedSoundInstance
 *  net.minecraft.SoundInstance
 *  net.minecraft.Formatting
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.RenderLayer
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.KeyBinding
 *  net.minecraft.MinecraftClient
 *  net.minecraft.SoundEvent
 *  net.minecraft.SoundCategory
 *  net.minecraft.MathHelper
 *  net.minecraft.InputUtil$class_307
 *  net.minecraft.Screen
 *  net.minecraft.MatrixStack
 *  net.minecraft.VertexConsumer
 *  net.minecraft.VertexConsumerProvider
 *  net.minecraft.OverlayTexture
 *  net.minecraft.Random
 *  net.minecraft.EmptyEntityRenderer
 *  net.minecraft.AbstractClientPlayerEntity
 *  net.minecraft.RotationAxis
 *  net.minecraft.CustomPayload
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 */
package nomorespell_rvknbyie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.MovingSoundInstance;
import net.minecraft.PositionedSoundInstance;
import net.minecraft.SoundInstance;
import net.minecraft.Formatting;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.RenderLayer;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.KeyBinding;
import net.minecraft.MinecraftClient;
import net.minecraft.SoundEvent;
import net.minecraft.SoundCategory;
import net.minecraft.MathHelper;
import net.minecraft.InputUtil;
import net.minecraft.Screen;
import net.minecraft.MatrixStack;
import net.minecraft.VertexConsumer;
import net.minecraft.VertexConsumerProvider;
import net.minecraft.OverlayTexture;
import net.minecraft.Random;
import net.minecraft.EmptyEntityRenderer;
import net.minecraft.AbstractClientPlayerEntity;
import net.minecraft.RotationAxis;
import net.minecraft.CustomPayload;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.RainOfPicksRenderer;
import nomorespell_rvknbyie.SpellBookScreen;
import nomorespell_rvknbyie.VerdantHaloRenderer;
import nomorespell_rvknbyie.item.NomorespellItem;
import nomorespell_rvknbyie.network.BloodEclipseRenderPayload;
import nomorespell_rvknbyie.network.CastSpellPayload;
import nomorespell_rvknbyie.network.DomainRenderSyncPayload;
import nomorespell_rvknbyie.network.RainOfPicksRenderPayload;
import nomorespell_rvknbyie.network.VerdantHaloRenderPayload;
import nomorespell_rvknbyie.spell.DomainExpansionManager;
import nomorespell_rvknbyie.spell.SpellBookData;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

@Environment(value=EnvType.CLIENT)
public class NomorespellClient
implements ClientModInitializer {
    private static final int SPHERE_SEGMENTS = 48;
    private static final int BLOOD_ECLIPSE_LASER_SEGMENTS = 64;
    private static final float TWO_PI = (float)Math.PI * 2;
    private static final float PI = (float)Math.PI;
    private static final float[] SIN_THETA = new float[49];
    private static final float[] COS_THETA = new float[49];
    private static final float[] SIN_PHI = new float[49];
    private static final float[] COS_PHI = new float[49];
    public static KeyBinding OPEN_SPELLBOOK_KEY;
    public static KeyBinding CAST_SLOT1_KEY;
    public static KeyBinding CAST_SLOT2_KEY;
    private static final Map<UUID, DomainExpansionManager.DomainRenderState> DOMAIN_RENDERS;
    private static final Map<UUID, BloodEclipseRenderState> BLOOD_ECLIPSE_RENDERS;
    private static final Map<Integer, RainOfPicksRenderState> RAIN_OF_PICKS_RENDERS;
    private static final Map<Integer, VerdantHaloRenderState> VERDANT_HALO_RENDERS;
    private static final Map<Integer, VerdantHaloLoopSoundInstance> ACTIVE_VERDANT_HALO_SOUNDS;
    private static final Set<UUID> ACTIVE_SPHERE_SOUNDS;
    private static int bloodEclipseShakeTicks;

    public void onInitializeClient() {
        EntityRendererRegistry.register(Nomorespell.FIREBALL_ENTITY_TYPE, EmptyEntityRenderer::new);
        OPEN_SPELLBOOK_KEY = KeyBindingHelper.registerKeyBinding((KeyBinding)new KeyBinding("Open SSpellBook", InputUtil.class_307.field_1668, 71, "NoMoreSpell"));
        CAST_SLOT1_KEY = KeyBindingHelper.registerKeyBinding((KeyBinding)new KeyBinding("Keybind Slot 1", InputUtil.class_307.field_1668, 82, "NoMoreSpell"));
        CAST_SLOT2_KEY = KeyBindingHelper.registerKeyBinding((KeyBinding)new KeyBinding("Keybind Slot 2", InputUtil.class_307.field_1668, 70, "NoMoreSpell"));
        ClientPlayNetworking.registerGlobalReceiver(DomainRenderSyncPayload.ID, (payload, context) -> context.client().execute(() -> {
            if (!payload.active()) {
                DOMAIN_RENDERS.remove(payload.casterId());
                ACTIVE_SPHERE_SOUNDS.remove(payload.casterId());
            } else {
                DOMAIN_RENDERS.put(payload.casterId(), new DomainExpansionManager.DomainRenderState(payload.casterId(), new Vec3d(payload.x(), payload.y(), payload.z()), payload.age(), payload.radius(), payload.alpha(), payload.ending(), payload.endAge()));
            }
        }));
        ClientPlayNetworking.registerGlobalReceiver(BloodEclipseRenderPayload.ID, (payload, context) -> context.client().execute(() -> {
            if (!payload.active()) {
                BLOOD_ECLIPSE_RENDERS.remove(payload.casterId());
            } else {
                BLOOD_ECLIPSE_RENDERS.put(payload.casterId(), new BloodEclipseRenderState(payload.casterId(), new Vec3d(payload.x(), payload.y(), payload.z()), new Vec3d(payload.dirX(), payload.dirY(), payload.dirZ()), payload.age(), payload.laserActive(), payload.intensity(), payload.fade()));
            }
        }));
        ClientPlayNetworking.registerGlobalReceiver(RainOfPicksRenderPayload.ID, (payload, context) -> context.client().execute(() -> {
            if (!payload.active()) {
                RAIN_OF_PICKS_RENDERS.remove(payload.casterEntityId());
            } else {
                RAIN_OF_PICKS_RENDERS.put(payload.casterEntityId(), new RainOfPicksRenderState(payload.casterEntityId(), payload.startAge(), payload.durationTicks(), payload.seed(), payload.projectileCount(), payload.homingProjectileCount()));
            }
        }));
        ClientPlayNetworking.registerGlobalReceiver(VerdantHaloRenderPayload.ID, (payload, context) -> context.client().execute(() -> {
            if (!payload.active()) {
                VERDANT_HALO_RENDERS.remove(payload.casterEntityId());
                ACTIVE_VERDANT_HALO_SOUNDS.remove(payload.casterEntityId());
            } else {
                VERDANT_HALO_RENDERS.put(payload.casterEntityId(), new VerdantHaloRenderState(payload.casterEntityId(), payload.startAge(), payload.durationTicks()));
            }
        }));
        WorldRenderEvents.START.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null || bloodEclipseShakeTicks <= 0) {
                return;
            }
            float strength = 0.035f + 0.01f * (float)bloodEclipseShakeTicks;
            float yawOffset = (client.world.random.nextFloat() - 0.5f) * strength;
            float pitchOffset = (client.world.random.nextFloat() - 0.5f) * strength;
            client.player.setYaw(client.player.getYaw() + yawOffset);
            client.player.setPitch(MathHelper.clamp((float)(client.player.getPitch() + pitchOffset), (float)-90.0f, (float)90.0f));
            --bloodEclipseShakeTicks;
        });
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null) {
                return;
            }
            Vec3d cameraPos = context.camera().getPos();
            MatrixStack matrices = context.matrixStack();
            float tickDelta = context.tickCounter().getTickProgress(true);
            for (AbstractClientPlayerEntity player : client.world.getPlayers()) {
                VerdantHaloRenderState state;
                RainOfPicksRenderState rainState = RAIN_OF_PICKS_RENDERS.get(player.getId());
                if (rainState != null) {
                    float rainAge = (float)(player.age - rainState.startAge()) + tickDelta;
                    if (rainAge > (float)rainState.durationTicks()) {
                        RAIN_OF_PICKS_RENDERS.remove(player.getId());
                    } else {
                        RainOfPicksRenderer.render(matrices, context.consumers(), cameraPos, player, rainState, tickDelta);
                    }
                }
                if ((state = VERDANT_HALO_RENDERS.get(player.getId())) == null) continue;
                float age = (float)(player.age - state.startAge()) + tickDelta;
                if (age > (float)state.durationTicks()) {
                    VERDANT_HALO_RENDERS.remove(player.getId());
                    ACTIVE_VERDANT_HALO_SOUNDS.remove(player.getId());
                    continue;
                }
                NomorespellClient.tickVerdantHaloSound(client, player, state);
                VerdantHaloRenderer.render(matrices, context.consumers(), cameraPos, player, state, tickDelta);
            }
        });
        WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null) {
                return;
            }
            Vec3d cameraPos = context.camera().getPos();
            MatrixStack matrices = context.matrixStack();
            for (DomainExpansionManager.DomainRenderState domainRenderState : DOMAIN_RENDERS.values()) {
                NomorespellClient.tickSphereSounds(client, domainRenderState);
                NomorespellClient.renderDomainSphere(matrices, cameraPos, domainRenderState, context.consumers());
            }
            for (BloodEclipseRenderState bloodEclipseRenderState : BLOOD_ECLIPSE_RENDERS.values()) {
                NomorespellClient.renderBloodEclipse(matrices, cameraPos, bloodEclipseRenderState, context.consumers());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ItemStack bookStack;
            if (client.player == null) {
                return;
            }
            BloodEclipseRenderState selfState = BLOOD_ECLIPSE_RENDERS.get(client.player.getUuid());
            if (selfState != null && selfState.laserActive && selfState.fade < 0.95f) {
                bloodEclipseShakeTicks = Math.max(bloodEclipseShakeTicks, 2);
            }
            if (OPEN_SPELLBOOK_KEY.wasPressed()) {
                boolean holdingBook;
                ItemStack mainHand = client.player.getMainHandStack();
                ItemStack offHand = client.player.getOffHandStack();
                boolean bl = holdingBook = mainHand.getItem() instanceof NomorespellItem || offHand.getItem() instanceof NomorespellItem;
                if (holdingBook) {
                    ItemStack bookStack2 = mainHand.getItem() instanceof NomorespellItem ? mainHand : offHand;
                    SpellBookData.initializeIfNeeded(bookStack2);
                    if (client.player != null && client.world != null) {
                        this.spawnOpenParticles(client.player.getPos());
                        Nomorespell.setGuiOpen((PlayerEntity)client.player, true);
                    }
                    client.setScreen((Screen)new SpellBookScreen(bookStack2));
                } else {
                    client.player.sendMessage((Text)Text.literal((String)"Hold the SSpellBook to open it!").formatted(Formatting.field_1061), true);
                }
            }
            while (CAST_SLOT1_KEY.wasPressed()) {
                if (client.currentScreen != null || Nomorespell.isGuiOpen((PlayerEntity)client.player)) continue;
                bookStack = null;
                for (int i = 0; i < client.player.getInventory().size(); ++i) {
                    ItemStack candidate = client.player.getInventory().getStack(i);
                    if (!(candidate.getItem() instanceof NomorespellItem)) continue;
                    bookStack = candidate;
                    break;
                }
                if (bookStack == null) continue;
                SpellBookData.initializeIfNeeded(bookStack);
                ClientPlayNetworking.send((CustomPayload)new CastSpellPayload(1));
            }
            while (CAST_SLOT2_KEY.wasPressed()) {
                if (client.currentScreen != null || Nomorespell.isGuiOpen((PlayerEntity)client.player)) continue;
                bookStack = null;
                for (int i = 0; i < client.player.getInventory().size(); ++i) {
                    ItemStack candidate = client.player.getInventory().getStack(i);
                    if (!(candidate.getItem() instanceof NomorespellItem)) continue;
                    bookStack = candidate;
                    break;
                }
                if (bookStack == null) continue;
                SpellBookData.initializeIfNeeded(bookStack);
                ClientPlayNetworking.send((CustomPayload)new CastSpellPayload(2));
            }
        });
        Nomorespell.LOGGER.info("NoMoreSpell Client initialized!");
    }

    private static void renderDomainSphere(MatrixStack matrices, Vec3d cameraPos, DomainExpansionManager.DomainRenderState state, VertexConsumerProvider consumers) {
        if (state.radius <= 0.01f || state.alpha <= 0.01f) {
            return;
        }
        matrices.push();
        matrices.translate(state.center.x - cameraPos.x, state.center.y - cameraPos.y, state.center.z - cameraPos.z);
        VertexConsumer consumer = consumers.getBuffer(RenderLayer.getLightning());
        NomorespellClient.renderSphereLayers(matrices, consumer, state);
        matrices.pop();
    }

    private static void renderSphereLayers(MatrixStack matrices, VertexConsumer vc, DomainExpansionManager.DomainRenderState state) {
        NomorespellClient.renderSingleSphere(matrices, vc, state, state.radius, 1.0f, 1.1f, 0.65f, 1.0f);
        NomorespellClient.renderSingleSphere(matrices, vc, state, state.radius * 1.015f, 0.92f, 1.25f, 0.82f, 1.0f);
    }

    private static void renderSingleSphere(MatrixStack matrices, VertexConsumer vc, DomainExpansionManager.DomainRenderState state, float radius, float alphaFactor, float ySpeed, float xSpeed, float pulseFactor) {
        matrices.push();
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)state.age * ySpeed));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)state.age * xSpeed));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        for (int i = 0; i < 48; ++i) {
            for (int j = 0; j < 48; ++j) {
                Vector3f p1 = NomorespellClient.getSpherePoint(radius, i, j, state.age, pulseFactor);
                Vector3f p2 = NomorespellClient.getSpherePoint(radius, i + 1, j, state.age, pulseFactor);
                Vector3f p3 = NomorespellClient.getSpherePoint(radius, i + 1, j + 1, state.age, pulseFactor);
                Vector3f p4 = NomorespellClient.getSpherePoint(radius, i, j + 1, state.age, pulseFactor);
                NomorespellClient.drawQuad(vc, matrix, p1, p2, p3, p4, alphaFactor, state, i, j);
            }
        }
        matrices.pop();
    }

    private static Vector3f getSpherePoint(float baseRadius, int thetaIndex, int phiIndex, int age, float pulseFactor) {
        int wrappedTheta = MathHelper.floorMod((int)thetaIndex, (int)48);
        int wrappedPhi = MathHelper.clamp((int)phiIndex, (int)0, (int)48);
        float theta = (float)Math.PI * 2 * (float)wrappedTheta / 48.0f;
        float animatedRadius = baseRadius * (1.0f + 0.012f * pulseFactor * MathHelper.sin((float)((float)age * 0.12f)));
        float x = animatedRadius * SIN_PHI[wrappedPhi] * COS_THETA[wrappedTheta];
        float y = animatedRadius * COS_PHI[wrappedPhi];
        float z = animatedRadius * SIN_PHI[wrappedPhi] * SIN_THETA[wrappedTheta];
        return new Vector3f(x, y, z);
    }

    private static void drawQuad(VertexConsumer vc, Matrix4f matrix, Vector3f p1, Vector3f p2, Vector3f p3, Vector3f p4, float alphaScale, DomainExpansionManager.DomainRenderState state, int thetaIndex, int phiIndex) {
        float theta1 = (float)Math.PI * 2 * (float)thetaIndex / 48.0f;
        float theta2 = (float)Math.PI * 2 * (float)(thetaIndex + 1) / 48.0f;
        float phi1 = (float)Math.PI * (float)phiIndex / 48.0f;
        float phi2 = (float)Math.PI * (float)(phiIndex + 1) / 48.0f;
        NomorespellClient.addQuadVertex(vc, matrix, p1, alphaScale, state, theta1, phi1);
        NomorespellClient.addQuadVertex(vc, matrix, p2, alphaScale, state, theta2, phi1);
        NomorespellClient.addQuadVertex(vc, matrix, p3, alphaScale, state, theta2, phi2);
        NomorespellClient.addQuadVertex(vc, matrix, p4, alphaScale, state, theta1, phi2);
        NomorespellClient.addQuadVertex(vc, matrix, p4, alphaScale, state, theta1, phi2);
        NomorespellClient.addQuadVertex(vc, matrix, p3, alphaScale, state, theta2, phi2);
        NomorespellClient.addQuadVertex(vc, matrix, p2, alphaScale, state, theta2, phi1);
        NomorespellClient.addQuadVertex(vc, matrix, p1, alphaScale, state, theta1, phi1);
    }

    private static void addQuadVertex(VertexConsumer vc, Matrix4f matrix, Vector3f point, float alphaScale, DomainExpansionManager.DomainRenderState state, float theta, float phi) {
        float pulse = 0.5f + 0.5f * MathHelper.sin((float)((float)state.age * 0.08f + phi * 1.35f + theta * 0.2f));
        int red = MathHelper.clamp((int)((int)(28.0f + 20.0f * pulse)), (int)0, (int)255);
        int green = MathHelper.clamp((int)((int)(6.0f + 10.0f * pulse)), (int)0, (int)255);
        int blue = MathHelper.clamp((int)((int)(64.0f + 42.0f * pulse)), (int)0, (int)255);
        int alpha = MathHelper.clamp((int)((int)(230.0f * alphaScale * state.alpha)), (int)0, (int)255);
        vc.vertex(matrix, point.x, point.y, point.z).color(red, green, blue, alpha).light(0xF000F0);
    }

    private static void tickSphereSounds(MinecraftClient client, DomainExpansionManager.DomainRenderState state) {
        if (client.player == null || client.getSoundManager() == null) {
            return;
        }
        if (ACTIVE_SPHERE_SOUNDS.add(state.casterId)) {
            client.getSoundManager().play((SoundInstance)PositionedSoundInstance.master((SoundEvent)Nomorespell.DOMAIN_SPHERE_SOUND, (float)0.9f));
        }
        if (state.age % 60 == 0 && !state.ending) {
            client.getSoundManager().play((SoundInstance)PositionedSoundInstance.master((SoundEvent)Nomorespell.DOMAIN_SPHERE_AMBIENCE_SOUND, (float)0.45f));
        }
        if (state.ending && state.endAge == 1) {
            client.getSoundManager().play((SoundInstance)PositionedSoundInstance.master((SoundEvent)Nomorespell.DOMAIN_SPHERE_SOUND, (float)1.2f));
        }
    }

    private static void tickVerdantHaloSound(MinecraftClient client, AbstractClientPlayerEntity player, VerdantHaloRenderState state) {
        if (client.getSoundManager() == null || player == null) {
            return;
        }
        VerdantHaloLoopSoundInstance sound = ACTIVE_VERDANT_HALO_SOUNDS.get(player.getId());
        if (sound == null || sound.isDone()) {
            sound = new VerdantHaloLoopSoundInstance(player, state);
            ACTIVE_VERDANT_HALO_SOUNDS.put(player.getId(), sound);
            client.getSoundManager().play((SoundInstance)sound);
            return;
        }
        sound.updateState(player, state);
    }

    private static void renderBloodEclipse(MatrixStack matrices, Vec3d cameraPos, BloodEclipseRenderState state, VertexConsumerProvider consumers) {
        if (state.intensity <= 0.01f) {
            return;
        }
        matrices.push();
        matrices.translate(state.center.x - cameraPos.x, state.center.y - cameraPos.y + 0.03, state.center.z - cameraPos.z);
        VertexConsumer consumer = consumers.getBuffer(RenderLayer.getLightning());
        NomorespellClient.renderMagicCircle(matrices, consumer, state);
        if (state.laserActive) {
            NomorespellClient.renderLaserCylinder(matrices, consumer, state);
        }
        matrices.pop();
    }

    private static void renderMagicCircle(MatrixStack matrices, VertexConsumer vc, BloodEclipseRenderState state) {
        matrices.push();
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)state.age * 3.2f));
        float alpha = MathHelper.clamp((float)((1.0f - state.fade) * state.intensity), (float)0.0f, (float)1.0f);
        float pulse = 1.0f + 0.06f * MathHelper.sin((float)((float)state.age * 0.16f));
        NomorespellClient.drawFilledDisc(matrices, vc, 0.0f, 3.05f * pulse, 48, 10, 0, 0, alpha * 0.92f, 0.001f);
        NomorespellClient.drawMagicCircleLayer(matrices, vc, 1.7f * pulse, 2.45f * pulse, 48, 255, 18, 18, alpha, 0.003f);
        NomorespellClient.drawMagicCircleLayer(matrices, vc, 2.78f * pulse, 3.15f * pulse, 48, 255, 40, 40, alpha * 0.95f, 0.005f);
        NomorespellClient.drawMagicCircleLayer(matrices, vc, 3.15f * pulse, 3.35f * pulse, 48, 70, 0, 0, alpha * 0.82f, 0.007f);
        NomorespellClient.drawGlyphSpokes(matrices, vc, 2.15f * pulse, alpha, state.age);
        NomorespellClient.drawGlyphArcs(matrices, vc, 2.8f * pulse, alpha, state.age);
        NomorespellClient.drawSparkOrbit(matrices, vc, 3.0f * pulse, alpha, state.age);
        matrices.pop();
    }

    private static void drawFilledDisc(MatrixStack matrices, VertexConsumer vc, float innerRadius, float outerRadius, int segments, int red, int green, int blue, float alpha, float y) {
        NomorespellClient.drawMagicCircleLayer(matrices, vc, innerRadius, outerRadius, segments, red, green, blue, alpha, y);
    }

    private static void drawMagicCircleLayer(MatrixStack matrices, VertexConsumer vc, float innerRadius, float outerRadius, int segments, int red, int green, int blue, float alpha, float y) {
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int a = MathHelper.clamp((int)((int)(255.0f * alpha)), (int)0, (int)255);
        for (int i = 0; i < segments; ++i) {
            float theta1 = (float)Math.PI * 2 * (float)i / (float)segments;
            float theta2 = (float)Math.PI * 2 * (float)(i + 1) / (float)segments;
            Vector3f p1 = new Vector3f(innerRadius * MathHelper.cos((float)theta1), y, innerRadius * MathHelper.sin((float)theta1));
            Vector3f p2 = new Vector3f(outerRadius * MathHelper.cos((float)theta1), y, outerRadius * MathHelper.sin((float)theta1));
            Vector3f p3 = new Vector3f(outerRadius * MathHelper.cos((float)theta2), y, outerRadius * MathHelper.sin((float)theta2));
            Vector3f p4 = new Vector3f(innerRadius * MathHelper.cos((float)theta2), y, innerRadius * MathHelper.sin((float)theta2));
            NomorespellClient.addVertex(vc, position, normal, p1, red, green, blue, a, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, position, normal, p2, red, green, blue, a, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, position, normal, p3, red, green, blue, a, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, position, normal, p4, red, green, blue, a, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        }
    }

    private static void drawGlyphSpokes(MatrixStack matrices, VertexConsumer vc, float radius, float alpha, int age) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int spokes = 18;
        for (int i = 0; i < spokes; ++i) {
            float angle = (float)Math.PI * 2 * (float)i / (float)spokes + (float)age * 0.03f;
            float width = 0.11f;
            float length = 0.62f + 0.12f * MathHelper.sin((float)((float)age * 0.12f + (float)i));
            Vector3f center = new Vector3f(MathHelper.cos((float)angle) * radius, 0.001f, MathHelper.sin((float)angle) * radius);
            Vector3f tangent = new Vector3f(-MathHelper.sin((float)angle) * width, 0.0f, MathHelper.cos((float)angle) * width);
            Vector3f radial = new Vector3f(MathHelper.cos((float)angle) * length, 0.0f, MathHelper.sin((float)angle) * length);
            int a = MathHelper.clamp((int)((int)(235.0f * alpha)), (int)0, (int)255);
            NomorespellClient.addVertex(vc, matrix, normal, new Vector3f((Vector3fc)center).sub((Vector3fc)tangent), 255, 30, 30, a, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, new Vector3f((Vector3fc)center).add((Vector3fc)tangent), 255, 30, 30, a, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, new Vector3f((Vector3fc)center).add((Vector3fc)tangent).add((Vector3fc)radial), 40, 0, 0, a, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, new Vector3f((Vector3fc)center).sub((Vector3fc)tangent).add((Vector3fc)radial), 40, 0, 0, a, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        }
    }

    private static void drawGlyphArcs(MatrixStack matrices, VertexConsumer vc, float radius, float alpha, int age) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int arcCount = 8;
        int steps = 6;
        float width = 0.09f;
        int a = MathHelper.clamp((int)((int)(220.0f * alpha)), (int)0, (int)255);
        for (int arc = 0; arc < arcCount; ++arc) {
            float start = (float)Math.PI * 2 * (float)arc / (float)arcCount + (float)age * 0.015f;
            float end = start + 0.42f;
            for (int step = 0; step < steps; ++step) {
                float t1 = MathHelper.lerp((float)((float)step / (float)steps), (float)start, (float)end);
                float t2 = MathHelper.lerp((float)((float)(step + 1) / (float)steps), (float)start, (float)end);
                Vector3f p1 = new Vector3f(MathHelper.cos((float)t1) * (radius - width), 0.006f, MathHelper.sin((float)t1) * (radius - width));
                Vector3f p2 = new Vector3f(MathHelper.cos((float)t1) * (radius + width), 0.006f, MathHelper.sin((float)t1) * (radius + width));
                Vector3f p3 = new Vector3f(MathHelper.cos((float)t2) * (radius + width), 0.006f, MathHelper.sin((float)t2) * (radius + width));
                Vector3f p4 = new Vector3f(MathHelper.cos((float)t2) * (radius - width), 0.006f, MathHelper.sin((float)t2) * (radius - width));
                NomorespellClient.addVertex(vc, matrix, normal, p1, 255, 24, 24, a, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p2, 255, 24, 24, a, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p3, 90, 0, 0, a, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p4, 90, 0, 0, a, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
            }
        }
    }

    private static void drawSparkOrbit(MatrixStack matrices, VertexConsumer vc, float radius, float alpha, int age) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int count = 12;
        int a = MathHelper.clamp((int)((int)(alpha * 210.0f)), (int)0, (int)255);
        for (int i = 0; i < count; ++i) {
            float angle = (float)Math.PI * 2 * (float)i / (float)count + (float)age * 0.08f;
            float x = radius * MathHelper.cos((float)angle);
            float z = radius * MathHelper.sin((float)angle);
            float size = 0.07f;
            Vector3f p1 = new Vector3f(x - size, 0.09f, z - size);
            Vector3f p2 = new Vector3f(x + size, 0.09f, z - size);
            Vector3f p3 = new Vector3f(x + size, 0.16f, z + size);
            Vector3f p4 = new Vector3f(x - size, 0.16f, z + size);
            NomorespellClient.addVertex(vc, matrix, normal, p1, 255, 80, 80, a, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p2, 255, 80, 80, a, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p3, 60, 0, 0, a, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p4, 60, 0, 0, a, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        }
    }

    private static void renderLaserCylinder(MatrixStack matrices, VertexConsumer vc, BloodEclipseRenderState state) {
        matrices.push();
        Vec3d dir = state.direction.lengthSquared() > 1.0E-6 ? state.direction.normalize() : new Vec3d(0.0, 0.0, 1.0);
        float yaw = (float)Math.atan2(dir.x, dir.z);
        float pitch = (float)(-Math.asin(dir.y));
        matrices.translate(0.0, 1.15, 0.0);
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotation(yaw));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotation(pitch));
        matrices.translate(0.0, 0.0, 0.18);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int segments = 64;
        float radius = 1.0f + 0.02f * MathHelper.sin((float)((float)state.age * 0.22f));
        float length = 20.0f;
        float visualAlpha = 0.85f + 0.15f * MathHelper.sin((float)((float)state.age * 3.0f));
        float alpha = MathHelper.clamp((float)((1.0f - state.fade) * visualAlpha), (float)0.0f, (float)1.0f);
        NomorespellClient.drawOpenBeamLayer(vc, matrix, normal, segments, radius, length, Math.min(1.0f, alpha), 255, 40, 40, 210, 8, 8, 0.002f, true, 1.0f, 0.6f, 2.8f);
        NomorespellClient.drawOpenBeamLayer(vc, matrix, normal, segments, radius * 0.82f, length, Math.min(1.0f, alpha * 0.94f), 180, 8, 8, 88, 0, 0, 0.008f, true, 1.0f, 0.52f, 2.6f);
        NomorespellClient.drawOpenBeamLayer(vc, matrix, normal, segments, radius * 0.58f, length, Math.min(1.0f, alpha * 0.72f), 58, 0, 0, 18, 0, 0, 0.014f, true, 1.0f, 0.42f, 2.4f);
        matrices.pop();
    }

    private static void drawOpenBeamLayer(VertexConsumer vc, Matrix4f matrix, Matrix3f normal, int segments, float radius, float length, float alpha, int startR, int startG, int startB, int endR, int endG, int endB, float radialOffset, boolean addInnerInvertedLayer, float startScale, float endScale, float taperLength) {
        NomorespellClient.drawOpenBeamTube(vc, matrix, normal, segments, radius, length, alpha, startR, startG, startB, endR, endG, endB, radialOffset, startScale, endScale, taperLength);
        if (addInnerInvertedLayer) {
            NomorespellClient.drawOpenBeamTubeInverted(vc, matrix, normal, segments, radius * 0.985f, length, alpha * 0.92f, startR, startG, startB, endR, endG, endB, radialOffset + 8.0E-4f, startScale, endScale, taperLength);
        }
    }

    private static void drawOpenBeamTube(VertexConsumer vc, Matrix4f matrix, Matrix3f normal, int segments, float radius, float length, float alpha, int startR, int startG, int startB, int endR, int endG, int endB, float radialOffset, float startScale, float endScale, float taperLength) {
        int a = MathHelper.clamp((int)((int)(255.0f * alpha)), (int)0, (int)255);
        for (int i = 0; i < segments; ++i) {
            float a1 = (float)Math.PI * 2 * (float)i / (float)segments;
            float a2 = (float)Math.PI * 2 * (float)(i + 1) / (float)segments;
            float cos1 = MathHelper.cos((float)a1);
            float sin1 = MathHelper.sin((float)a1);
            float cos2 = MathHelper.cos((float)a2);
            float sin2 = MathHelper.sin((float)a2);
            float outerNear = radius * startScale + radialOffset;
            float outerFar = radius * endScale + radialOffset;
            Vector3f p1 = new Vector3f(cos1 * outerNear, sin1 * outerNear, 0.0f);
            Vector3f p2 = new Vector3f(cos2 * outerNear, sin2 * outerNear, 0.0f);
            Vector3f p3 = new Vector3f(cos2 * outerFar, sin2 * outerFar, length);
            Vector3f p4 = new Vector3f(cos1 * outerFar, sin1 * outerFar, length);
            float nx1 = cos1;
            float ny1 = sin1;
            float nx2 = cos2;
            float ny2 = sin2;
            int farAlpha = MathHelper.clamp((int)((int)(255.0f * alpha * NomorespellClient.getTipAlpha(length, taperLength, length))), (int)0, (int)255);
            NomorespellClient.addVertex(vc, matrix, normal, p1, startR, startG, startB, a, 0.0f, 0.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p2, startR, startG, startB, a, 1.0f, 0.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p3, endR, endG, endB, farAlpha, 1.0f, 1.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p4, endR, endG, endB, farAlpha, 0.0f, 1.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p4, endR, endG, endB, farAlpha, 0.0f, 1.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p3, endR, endG, endB, farAlpha, 1.0f, 1.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p2, startR, startG, startB, a, 1.0f, 0.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p1, startR, startG, startB, a, 0.0f, 0.0f, nx1, ny1, 0.0f);
        }
        NomorespellClient.drawBeamTipGlow(vc, matrix, normal, segments, radius * endScale, length, alpha, endR, endG, endB, radialOffset, taperLength);
    }

    private static void drawOpenBeamTubeInverted(VertexConsumer vc, Matrix4f matrix, Matrix3f normal, int segments, float radius, float length, float alpha, int startR, int startG, int startB, int endR, int endG, int endB, float radialOffset, float startScale, float endScale, float taperLength) {
        int a = MathHelper.clamp((int)((int)(255.0f * alpha)), (int)0, (int)255);
        for (int i = 0; i < segments; ++i) {
            float a1 = (float)Math.PI * 2 * (float)i / (float)segments;
            float a2 = (float)Math.PI * 2 * (float)(i + 1) / (float)segments;
            float cos1 = MathHelper.cos((float)a1);
            float sin1 = MathHelper.sin((float)a1);
            float cos2 = MathHelper.cos((float)a2);
            float sin2 = MathHelper.sin((float)a2);
            float outerNear = radius * startScale + radialOffset;
            float outerFar = radius * endScale + radialOffset;
            Vector3f p1 = new Vector3f(cos1 * outerNear, sin1 * outerNear, 0.0f);
            Vector3f p2 = new Vector3f(cos2 * outerNear, sin2 * outerNear, 0.0f);
            Vector3f p3 = new Vector3f(cos2 * outerFar, sin2 * outerFar, length);
            Vector3f p4 = new Vector3f(cos1 * outerFar, sin1 * outerFar, length);
            float nx1 = -cos1;
            float ny1 = -sin1;
            float nx2 = -cos2;
            float ny2 = -sin2;
            int farAlpha = MathHelper.clamp((int)((int)(255.0f * alpha * NomorespellClient.getTipAlpha(length, taperLength, length))), (int)0, (int)255);
            NomorespellClient.addVertex(vc, matrix, normal, p1, startR, startG, startB, a, 0.0f, 0.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p4, endR, endG, endB, farAlpha, 0.0f, 1.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p3, endR, endG, endB, farAlpha, 1.0f, 1.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p2, startR, startG, startB, a, 1.0f, 0.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p4, endR, endG, endB, farAlpha, 0.0f, 1.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p1, startR, startG, startB, a, 0.0f, 0.0f, nx1, ny1, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p2, startR, startG, startB, a, 1.0f, 0.0f, nx2, ny2, 0.0f);
            NomorespellClient.addVertex(vc, matrix, normal, p3, endR, endG, endB, farAlpha, 1.0f, 1.0f, nx2, ny2, 0.0f);
        }
    }

    private static float getTipAlpha(float z, float taperLength, float totalLength) {
        float taperStart = Math.max(0.0f, totalLength - taperLength);
        if (z <= taperStart) {
            return 1.0f;
        }
        float progress = (z - taperStart) / Math.max(1.0E-4f, taperLength);
        float smooth = 1.0f - MathHelper.clamp((float)progress, (float)0.0f, (float)1.0f);
        return smooth * smooth;
    }

    private static void drawBeamTipGlow(VertexConsumer vc, Matrix4f matrix, Matrix3f normal, int segments, float radius, float length, float alpha, int red, int green, int blue, float radialOffset, float taperLength) {
        int rings = 6;
        for (int ring = 0; ring < rings; ++ring) {
            float t1 = (float)ring / (float)rings;
            float t2 = (float)(ring + 1) / (float)rings;
            float z1 = length - taperLength + taperLength * t1;
            float z2 = length - taperLength + taperLength * t2;
            float scale1 = MathHelper.lerp((float)t1, (float)1.0f, (float)0.6f);
            float scale2 = MathHelper.lerp((float)t2, (float)1.0f, (float)0.6f);
            float alpha1 = alpha * NomorespellClient.getTipAlpha(z1, taperLength, length) * 0.75f;
            float alpha2 = alpha * NomorespellClient.getTipAlpha(z2, taperLength, length) * 0.45f;
            int a1 = MathHelper.clamp((int)((int)(255.0f * alpha1)), (int)0, (int)255);
            int a2 = MathHelper.clamp((int)((int)(255.0f * alpha2)), (int)0, (int)255);
            for (int i = 0; i < segments; ++i) {
                float theta1 = (float)Math.PI * 2 * (float)i / (float)segments;
                float theta2 = (float)Math.PI * 2 * (float)(i + 1) / (float)segments;
                float cos1 = MathHelper.cos((float)theta1);
                float sin1 = MathHelper.sin((float)theta1);
                float cos2 = MathHelper.cos((float)theta2);
                float sin2 = MathHelper.sin((float)theta2);
                Vector3f p1 = new Vector3f(cos1 * (radius * scale1 + radialOffset), sin1 * (radius * scale1 + radialOffset), z1);
                Vector3f p2 = new Vector3f(cos2 * (radius * scale1 + radialOffset), sin2 * (radius * scale1 + radialOffset), z1);
                Vector3f p3 = new Vector3f(cos2 * (radius * scale2 + radialOffset), sin2 * (radius * scale2 + radialOffset), z2);
                Vector3f p4 = new Vector3f(cos1 * (radius * scale2 + radialOffset), sin1 * (radius * scale2 + radialOffset), z2);
                NomorespellClient.addVertex(vc, matrix, normal, p1, red, green, blue, a1, 0.0f, 0.0f, cos1, sin1, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p2, red, green, blue, a1, 1.0f, 0.0f, cos2, sin2, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p3, red, green, blue, a2, 1.0f, 1.0f, cos2, sin2, 0.0f);
                NomorespellClient.addVertex(vc, matrix, normal, p4, red, green, blue, a2, 0.0f, 1.0f, cos1, sin1, 0.0f);
            }
        }
    }

    private static void addVertex(VertexConsumer vc, Matrix4f positionMatrix, Matrix3f normalMatrix, Vector3f point, int red, int green, int blue, int alpha, float u, float v, float normalX, float normalY, float normalZ) {
        vc.vertex(positionMatrix, point.x, point.y, point.z).color(red, green, blue, alpha).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(0xF000F0).normal(normalX, normalY, normalZ);
    }

    private void spawnOpenParticles(Vec3d pos) {
        if (MinecraftClient.getInstance().world == null) {
            return;
        }
        Random rand = MinecraftClient.getInstance().world.getRandom();
        int count = 8 + rand.nextInt(5);
        for (int i = 0; i < count; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)count;
            double radius = 0.6 + rand.nextDouble() * 0.2;
            double x = pos.x + Math.cos(angle) * radius;
            double z = pos.z + Math.sin(angle) * radius;
            double y = pos.y + 1.1 + rand.nextDouble() * 0.4;
            MinecraftClient.getInstance().particleManager.addParticle((ParticleEffect)ParticleTypes.field_11207, x, y, z, 0.0, 0.02, 0.0);
        }
    }

    static {
        DOMAIN_RENDERS = new HashMap<UUID, DomainExpansionManager.DomainRenderState>();
        BLOOD_ECLIPSE_RENDERS = new HashMap<UUID, BloodEclipseRenderState>();
        RAIN_OF_PICKS_RENDERS = new HashMap<Integer, RainOfPicksRenderState>();
        VERDANT_HALO_RENDERS = new HashMap<Integer, VerdantHaloRenderState>();
        ACTIVE_VERDANT_HALO_SOUNDS = new HashMap<Integer, VerdantHaloLoopSoundInstance>();
        ACTIVE_SPHERE_SOUNDS = new HashSet<UUID>();
        bloodEclipseShakeTicks = 0;
        for (int i = 0; i <= 48; ++i) {
            float theta = (float)Math.PI * 2 * (float)i / 48.0f;
            float phi = (float)Math.PI * (float)i / 48.0f;
            NomorespellClient.SIN_THETA[i] = MathHelper.sin((float)theta);
            NomorespellClient.COS_THETA[i] = MathHelper.cos((float)theta);
            NomorespellClient.SIN_PHI[i] = MathHelper.sin((float)phi);
            NomorespellClient.COS_PHI[i] = MathHelper.cos((float)phi);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static final class VerdantHaloLoopSoundInstance
    extends MovingSoundInstance {
        private AbstractClientPlayerEntity player;
        private VerdantHaloRenderState state;
        private boolean done;

        private VerdantHaloLoopSoundInstance(AbstractClientPlayerEntity player, VerdantHaloRenderState state) {
            super(Nomorespell.VERDANT_HALO_ANGELIC_SOUND, SoundCategory.field_15248, SoundInstance.createRandom());
            this.player = player;
            this.state = state;
            this.repeat = true;
            this.repeatDelay = 0;
            this.relative = false;
            this.volume = 0.55f;
            this.pitch = 1.0f;
            this.updatePosition();
        }

        private void updateState(AbstractClientPlayerEntity player, VerdantHaloRenderState state) {
            this.player = player;
            this.state = state;
            this.updatePosition();
        }

        public boolean isDone() {
            return this.done;
        }

        public void tick() {
            if (this.player == null || this.state == null || this.player.isRemoved()) {
                this.done = true;
                this.setDone();
                return;
            }
            float age = this.player.age - this.state.startAge();
            if (age < 0.0f || age > (float)this.state.durationTicks()) {
                this.done = true;
                this.setDone();
                return;
            }
            float fadeIn = MathHelper.clamp((float)(age / 18.0f), (float)0.0f, (float)1.0f);
            float fadeOut = MathHelper.clamp((float)(((float)this.state.durationTicks() - age) / 20.0f), (float)0.0f, (float)1.0f);
            float envelope = Math.min(fadeIn, fadeOut);
            this.volume = 0.22f + 0.33f * envelope;
            this.pitch = 0.98f + 0.02f * MathHelper.sin((float)(age * 0.05f));
            this.updatePosition();
        }

        private void updatePosition() {
            if (this.player == null) {
                return;
            }
            this.x = this.player.getX();
            this.y = this.player.getY() + 0.08;
            this.z = this.player.getZ();
        }
    }

    @Environment(value=EnvType.CLIENT)
    public record VerdantHaloRenderState(int casterEntityId, int startAge, int durationTicks) {
    }

    @Environment(value=EnvType.CLIENT)
    private record BloodEclipseRenderState(UUID casterId, Vec3d center, Vec3d direction, int age, boolean laserActive, float intensity, float fade) {
    }

    @Environment(value=EnvType.CLIENT)
    public record RainOfPicksRenderState(int casterEntityId, int startAge, int durationTicks, int seed, int projectileCount, int homingProjectileCount) {
    }
}

