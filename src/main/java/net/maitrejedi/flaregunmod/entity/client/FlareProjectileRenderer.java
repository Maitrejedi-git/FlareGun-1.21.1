package net.maitrejedi.flaregunmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.entity.custom.FlareProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FlareProjectileRenderer extends EntityRenderer<FlareProjectileEntity> {
    private final FlareProjectileModel model;

    public FlareProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FlareProjectileModel(context.bakeLayer(FlareProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(FlareProjectileEntity pEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        if (!pEntity.isGrounded()) {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTicks, pEntity.xRotO, pEntity.getXRot()) + 90.0F));
            poseStack.translate(0, 0f, 0);
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.groundedOffset.x));
            poseStack.translate(0, 0f, 0);
        }

        poseStack.scale(0.5F, 0.5F, 0.5F);

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FlareProjectileEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(FlareGunMod.MOD_ID, "textures/entity/flare/flare.png");
    }
}
