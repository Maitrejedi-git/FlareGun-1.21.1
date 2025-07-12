package net.maitrejedi.flaregunmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.maitrejedi.flaregunmod.FlareGunMod;
import net.maitrejedi.flaregunmod.entity.custom.FlareProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FlareProjectileModel extends EntityModel<FlareProjectileEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(FlareGunMod.MOD_ID, "flare"), "main");
    private final ModelPart flare;

    public FlareProjectileModel(ModelPart root) {
        this.flare = root.getChild("flare");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition flare = partdefinition.addOrReplaceChild("flare", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -3.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 3).addBox(-10.0F, 0.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-10.0F, -2.0F, -2.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-10.0F, -2.0F, 1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 12).addBox(-10.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(FlareProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        flare.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
