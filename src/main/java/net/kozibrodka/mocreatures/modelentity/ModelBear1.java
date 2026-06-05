package net.kozibrodka.mocreatures.modelentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

/**
 * Client-side model for the bear.
 * <p>This one handles the ears and tail.</p>
 */
@Environment(EnvType.CLIENT)
public class ModelBear1 extends QuadrupedEntityModel {

    public ModelBear1() {
        super(12, 0.0F);

        // Ears
        head = new ModelPart(0, 0);
        head.addCuboid(-5F, -8F, -2F, 10, 4, 1, 0.0F);
        head.setPivot(0.0F, 4F, -8F);

        // Tail
        body = new ModelPart(32, 0);
        body.addCuboid(-2F, 9F, -4F, 4, 2, 4, 0.0F);
        body.setPivot(0.0F, 5F, 2.0F);
    }
}
