package net.kozibrodka.mocreatures.renderentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

/**
 * Client-side renderer for the bear.
 */
@Environment(EnvType.CLIENT)
public class RenderBear extends LivingEntityRenderer {
    public RenderBear(EntityModel entityModel, EntityModel decorationModel, float shadowRadius) {
        super(entityModel, shadowRadius);
        setDecorationModel(decorationModel);
    }

    @Override
    protected boolean bindDecorationTexture(LivingEntity mob, int layer, float tickDelta) {
        bindTexture("/assets/mocreatures/stationapi/textures/mob/bearb.png");
        return layer == 0;
    }

    @Override
    protected boolean bindTexture(LivingEntity mob, int layer, float tickDelta) {
        return bindDecorationTexture(mob, layer, tickDelta);
    }
}
