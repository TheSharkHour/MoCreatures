
package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

/**
 * Class for the Polar Bear entity.
 * <p>Not sure why DrZhark didn't go with an enum, but too late!</p>
 */
public class EntityPolarBear extends EntityBear implements MobSpawnDataProvider {

    public EntityPolarBear(World world) {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/polarbear.png";
        attackRange = 5.0D;
        health = 30;
    }

    @Override
    protected Entity getTargetInRange() {
        if (world.difficulty > 0) {
            attackRange = world.difficulty > 1 ? 8.0D : 5.0D;

            PlayerEntity player = world.getClosestPlayer(this, attackRange);
            if (player != null)
                return player;

            if (random.nextInt(20) == 0)
                return getClosestTarget(this, 8D);
        }

        return null;
    }

    @Override
    protected void attack(Entity other, float distance) {
        if ((double) distance >= 2.5D || other.boundingBox.maxY <= boundingBox.minY || other.boundingBox.minY >= boundingBox.maxY)
            return;

        attackCooldown = 20;
        force = world.difficulty > 1 ? 5 : 3;

        other.damage(this, force);
        if (!(other instanceof PlayerEntity))
            MoCTools.destroyDrops(this, 3D);
    }

    @Override
    protected void dropItems() {
        super.dropItems();

        // Seemingly unused item? But we'll keep this for now.
        int leatherAmount = random.nextInt(2);
        for (int i = 0; i < leatherAmount; i++) {
            if (world.difficulty > 0)
                dropItem(new ItemStack(mod_mocreatures.polarleather.id, 1, 0), 0.0F);
        }
    }

    @Override
    public boolean canSpawn() {
        return MoCTools.NearMaterialWithDistance(this, 1.0D, Material.SNOW_LAYER)
                && mod_mocreatures.mocGlass.huntercreatures.pbearfreq > 0
                && !MoCTools.isNearTorch(this)
                && super.animalCanSpawn();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "PolarBear");
    }
}
