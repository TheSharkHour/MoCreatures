package net.kozibrodka.mocreatures.entity;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.mocreatures.mocreatures.MoCEntity;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Set;

/**
 * Class for the Bear entity.
 */
public class EntityBear extends AnimalEntity implements MobSpawnDataProvider, MoCEntity {
    protected double attackRange;
    protected int force;

    public EntityBear(World world) {
        super(world);
        texture = "/assets/mocreatures/stationapi/textures/mob/bear.png";
        setBoundingBoxSpacing(0.9F, 1.3F);
        health = 25;
        force = 5;
        attackRange = 16D;
    }

    @Override
    protected Entity getTargetInRange() {
        if (world.difficulty > 0) {
            float brightness = getBrightnessAtEyes(1.0F);
            if (brightness < 0.5F) {
                PlayerEntity player = world.getClosestPlayer(this, attackRange);
                if (player != null) return player;
            }

            if (random.nextInt(80) == 0) {
                return getClosestTarget(this, 10D);
            }
        }

        return null;
    }

    public LivingEntity getClosestTarget(Entity entity, double searchRadius) {
        double closestDist = -1D;
        LivingEntity closestTarget = null;

        List<Entity> nearby = world.getEntities(this, boundingBox.expand(searchRadius, searchRadius, searchRadius));
        for (Entity candidate : nearby) {
            // A bit redundant, but this is needed for the return. I blame Mojang.
            if (!(candidate instanceof LivingEntity living)) continue;

            if (shouldIgnore(this, candidate)) continue;
            if (MoCTools.shouldIgnoreAsTarget(this, candidate)) continue;

            double distSq = candidate.getSquaredDistance(entity.x, entity.y, entity.z);
            if (distSq > searchRadius * searchRadius) continue;
            if (closestDist != -1D && distSq >= closestDist) continue;
            if (this.canSee(entity)) continue;

            closestDist = distSq;
            closestTarget = living;
        }

        return closestTarget;
    }

    /**
     * A list of entities to ignore being targeted.
     * <p>Used by {@link #shouldIgnore(Entity, Entity)}</p>
     */
    private static final Set<Class<? extends Entity>> IGNORED_ENTITIES = Set.of(
            EntityBear.class,
            EntityBigCat.class,
            EntityShark.class,
            EntityWWolf.class,
            EntityDolphin.class,
            EntityCrocodile.class,
            EntityHippo.class,
            EntityElephant.class
    );

    @Override
    public boolean shouldIgnore(Entity hunter, Entity target) {
        return IGNORED_ENTITIES.stream().anyMatch(c -> c.isInstance(target));
    }

    @Override
    public boolean damage(Entity damageSource, int amount) {
        if (!super.damage(damageSource, amount)) return false;

        // If the vehicle is a crocodile, or the bear's passenger is the damageSource, target it.
        boolean isBeingEatenByCroc = vehicle == damageSource && damageSource instanceof EntityCrocodile;
        boolean isRiderDamage = passenger == damageSource || (vehicle == damageSource && !isBeingEatenByCroc);

        if (!isRiderDamage && damageSource != this && world.difficulty > 0 && !shouldIgnore(this, damageSource))
            target = damageSource;

        return true;
    }

    @Override
    protected void attack(Entity other, float distance) {
        if ((double) distance >= 2.5D || other.boundingBox.maxY <= boundingBox.minY || other.boundingBox.minY >= boundingBox.maxY)
            return;

        attackCooldown = 20;
        other.damage(this, force);

        if (!(other instanceof PlayerEntity))
            MoCTools.destroyDrops(this, 3D);
    }

    @Override
    protected String getRandomSound() {
        return "mocreatures:beargrunt";
    }

    @Override
    protected String getHurtSound() {
        return "mocreatures:bearhurt";
    }

    @Override
    protected String getDeathSound() {
        return "mocreatures:beardeath";
    }

    @Override
    protected void dropItems() {
        int fishAmount = random.nextInt(3);
        for (int i = 0; i < fishAmount; i++) {
            dropItem(new ItemStack(getDroppedItemId(), 1, 0), 0.0F);
        }
    }

    @Override
    protected int getDroppedItemId() {
        return Item.RAW_FISH.id;
    }

    @Override
    public int getLimitPerChunk() {
        return 2;
    }

    /**
     * A hack to return the AnimalEntity 'canSpawn' for Polar Bears.
     * @return AnimalEntity:canSpawn()
     */
    public boolean animalCanSpawn() {
        return super.canSpawn();
    }

    @Override
    public boolean canSpawn() {
        return mod_mocreatures.mocGlass.huntercreatures.bearfreq > 0 && !MoCTools.isNearTorch(this) && super.canSpawn();
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(mod_mocreatures.MOD_ID, "Bear");
    }
}
