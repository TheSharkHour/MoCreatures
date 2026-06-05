package net.kozibrodka.mocreatures.modelentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

/**
 * Client-side model for the bear.
 * <p>This one handles the head, snout, and body.</p>
 */
@Environment(EnvType.CLIENT)
public class ModelBear2 extends QuadrupedEntityModel {
    public ModelPart snout;

    public ModelBear2() {
        super(12, 0.8F);

        head = new ModelPart(0, 0);
        head.addCuboid(-4F, -4F, -6F, 8, 8, 6, 0.0F);
        head.setPivot(0.0F, 4F, -8F);

        snout = new ModelPart(23, 0);
        snout.addCuboid(-2F, 0.0F, -9F, 4, 4, 6, 0.0F);
        snout.setPivot(0.0F, 4F, -8F);

        body = new ModelPart(32, 10);
        body.addCuboid(-4F, -8F, -5F, 8, 14, 8, 3F);
        body.setPivot(0.0F, 5F, 2.0F);
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        snout.render(scale);
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        snout.yaw = head.yaw;
        snout.pitch = head.pitch;
    }
}
