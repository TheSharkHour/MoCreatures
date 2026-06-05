package net.kozibrodka.mocreatures.mocreatures;

import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MoCTools {

    public static void addHeartParticles(World world, LivingEntity entity) {
        for (int i = 0; i < 7; ++i) {
            double pX = world.random.nextGaussian() * 0.02D;
            double pY = world.random.nextGaussian() * 0.02D;
            double pZ = world.random.nextGaussian() * 0.02D;
            world.addParticle("heart",
                    entity.x + (double) (world.random.nextFloat() * entity.width * 2.0F) - (double) entity.width,
                    entity.y + 0.5D + (double) (world.random.nextFloat() * entity.height),
                    entity.z + (double) (world.random.nextFloat() * entity.width * 2.0F) - (double) entity.width,
                    pX, pY, pZ);
        }
    }

    public static void addFlameParticles(World world, LivingEntity entity) {
        for (int i = 0; i < 7; ++i) {
            double pX = world.random.nextGaussian() * 0.02D;
            double pY = world.random.nextGaussian() * 0.02D;
            double pZ = world.random.nextGaussian() * 0.02D;
            world.addParticle("flame",
                    entity.x + (double) (world.random.nextFloat() * entity.width * 2.0F) - (double) entity.width,
                    entity.y + 0.5D + (double) (world.random.nextFloat() * entity.height),
                    entity.z + (double) (world.random.nextFloat() * entity.width * 2.0F) - (double) entity.width,
                    pX, pY, pZ);
        }
    }

    public static void destroyDrops(Entity entity, double radius) {
        if (mod_mocreatures.mocGlass.huntercreatures.destroyitems) {
            List<Entity> entities = entity.world.getEntities(entity, entity.boundingBox.expand(radius, radius, radius));
            for (Entity ent : entities) {
                if (ent instanceof ItemEntity item) {
                    if (item.age < 50) {
                        item.markDead();
                    }
                }
            }
        }
    }

    public static void MoveCreatureToXYZ(MobEntity movingEntity, int x, int y, int z, float f) {
        Path path = movingEntity.world.findPath(movingEntity, x, y, z, f);
        if (path != null)
            movingEntity.setPath(path);
    }

    public static void MoveToWater(MobEntity entity) {
        int[] ai = ReturnNearestMaterialCoord(entity, Material.WATER, 20D, 2D);
        if (ai[1] != -1) {
            MoveCreatureToXYZ(entity, ai[0], ai[1], ai[2], 24.0F);
        }
    }

    public static int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double xzOff, Double yOff) {
        double shortestDistance = -1.0D;
        double distance = 0.0D;
        // ZMIANA LOGIKI było -9999 i sprawdzanie czy x > - 1000, z tym że wtedy na ogromnej czesci świata nie działa LOL
        // CHANGED LOGIC - This was -9999 and checking if x was above -1000. This made it not work in a large chunk of the world. LOL
        int x = -1;
        int y = -1;
        int z = -1;

        Box aabb = entity.boundingBox.expand(xzOff, yOff, xzOff);

        int minX = MathHelper.floor(aabb.minX);
        int maxX = MathHelper.floor(aabb.maxX + 1.0D);
        int minY = MathHelper.floor(aabb.minY);
        int maxY = MathHelper.floor(aabb.maxY + 1.0D);
        int minZ = MathHelper.floor(aabb.minZ);
        int maxZ = MathHelper.floor(aabb.maxZ + 1.0D);

        for (int xx = minX; xx < maxX; ++xx) {
            for (int yy = minY; yy < maxY; ++yy) {
                for (int zz = minZ; zz < maxZ; ++zz) {
                    int blockID = entity.world.getBlockId(xx, yy, zz);
                    if (blockID != 0 && Block.BLOCKS[blockID].material == material) {
                        distance = getSqDistanceTo(entity, xx, yy, zz);
                        if (shortestDistance == -1.0D) {
                            x = xx;
                            y = yy;
                            z = zz;
                            shortestDistance = distance;
                        }

                        if (distance < shortestDistance) {
                            x = xx;
                            y = yy;
                            z = zz;
                            shortestDistance = distance;
                        }
                    }
                }
            }
        }

        x -= entity.x > (double) x ? 2 : -2;
        z -= entity.z > (double) z ? 2 : -2;

        return new int[]{x, y, z};
    }

    public static int[] ReturnNearestBlockCoord(Entity entity, String tileName, Double double1, Double yOff) {
        double shortestDistance = -1.0D;
        double distance;
        int x = -1;
        int y = -1;
        int z = -1;

        Box aabb = entity.boundingBox.expand(double1, yOff, double1);

        int minX = MathHelper.floor(aabb.minX);
        int maxX = MathHelper.floor(aabb.maxX + 1.0D);
        int minY = MathHelper.floor(aabb.minY);
        int maxY = MathHelper.floor(aabb.maxY + 1.0D);
        int minZ = MathHelper.floor(aabb.minZ);
        int maxZ = MathHelper.floor(aabb.maxZ + 1.0D);

        for (int xx = minX; xx < maxX; ++xx) {
            for (int yy = minY; yy < maxY; ++yy) {
                for (int zz = minZ; zz < maxZ; ++zz) {
                    int blockID = entity.world.getBlockId(xx, yy, zz);
                    if (blockID != 0 && Objects.equals(Block.BLOCKS[blockID].getTranslationKey(), tileName)) {
                        distance = getSqDistanceTo(entity, xx, yy, zz);
                        if (shortestDistance == -1.0D) {
                            x = xx;
                            y = yy;
                            z = zz;
                            shortestDistance = distance;
                        }

                        if (distance < shortestDistance) {
                            x = xx;
                            y = yy;
                            z = zz;
                            shortestDistance = distance;
                        }
                    }
                }
            }
        }
        return (new int[]{
                x, y, z
        });
    }

    public static double getSqDistanceTo(Entity entity, int x, int y, int z) {
        double xx = entity.x - (double) x;
        double yy = entity.y - (double) y;
        double zz = entity.z - (double) z;
        return Math.sqrt(xx * xx + yy * yy + zz * zz);
    }

    public static float realAngle(float origAngle) {
        return origAngle % 360.0F;
    }

    public static float distanceToSurface(Entity entity) {
        int mX = MathHelper.floor(entity.x);
        int mY = MathHelper.floor(entity.y);
        int mZ = MathHelper.floor(entity.z);
        int blockID = entity.world.getBlockId(mX, mY, mZ);

        if (blockID != 0 && Block.BLOCKS[blockID].material == Material.WATER) {
            // Oryginalnie patrzy jedynie x < 5 (5 bloków wody w górę) co powoduje utknięcie na pewnej głębokości.
            // Originally this only checked 5 blocks up for water, meaning it got stuck at certain depths.
            for (int x = 1; x < 10; ++x) {
                blockID = entity.world.getBlockId(mX, mY + x, mZ);
                if (blockID == 0 || Block.BLOCKS[blockID].material != Material.WATER) {
                    return (float) x;
                }
            }
        }

        return 0.0F;
    }

    public static boolean isSharkUnderIce(@NotNull Entity entity) {
        int mX = MathHelper.floor(entity.x);
        int mY = MathHelper.floor(entity.y);
        int mZ = MathHelper.floor(entity.z);

        for (int x = 0; x < 11; ++x) {
            int blockID = entity.world.getBlockId(mX, mY + x, mZ);
            if (Block.BLOCKS[blockID].material == Material.ICE) {
                return true;
            }
        }

        return false;
    }

    public static boolean NearMaterialWithDistance(Entity entity, Double radius, Material mat) {
        Box aabb = entity.boundingBox.expand(radius, radius, radius);

        int minX = MathHelper.floor(aabb.minX);
        int maxX = MathHelper.floor(aabb.maxX + 1.0D);
        int minY = MathHelper.floor(aabb.minY);
        int maxY = MathHelper.floor(aabb.maxY + 1.0D);
        int minZ = MathHelper.floor(aabb.minZ);
        int maxZ = MathHelper.floor(aabb.maxZ + 1.0D);

        for (int xx = minX; xx < maxX; xx++) {
            for (int yy = minY; yy < maxY; yy++) {
                for (int zz = minZ; zz < maxZ; zz++) {
                    int blockID = entity.world.getBlockId(xx, yy, zz);
                    if (blockID != 0 && Block.BLOCKS[blockID].material == mat) {
                        return true;
                    }
                }

            }

        }

        return false;
    }

    // Może 8 kratek za dużo???
    // 12 squares might be too many??? (was 8)
    public static boolean isNearWater(Entity entity) {
        return isNearBlockName(entity, 12D, "tile.water"); // 8.0D
    }

    public static boolean isNearTorch(Entity entity) {
        if (mod_mocreatures.mocGlass.huntercreatures.huntersSpawnOnTorch) return false;

        // return isNearBlockName(entity, 8.0D, "tile.torch");
        return isNearTorch(entity, 8.0D);
    }

    public static boolean isNearTorch(Entity entity, Double dist) {
        return isNearBlockName(entity, dist, "tile.torch");
    }

    public static boolean isNearBlockName(Entity entity, Double dist, String blockName) {
        Box aabb = entity.boundingBox.expand(dist, dist / 2.0D, dist);

        int minX = MathHelper.floor(aabb.minX);
        int maxX = MathHelper.floor(aabb.maxX + 1.0D);
        int minY = MathHelper.floor(aabb.minY);
        int maxY = MathHelper.floor(aabb.maxY + 1.0D);
        int minZ = MathHelper.floor(aabb.minZ);
        int maxZ = MathHelper.floor(aabb.maxZ + 1.0D);

        for (int xx = minX; xx < maxX; ++xx) {
            for (int yy = minY; yy < maxY; ++yy) {
                for (int zz = minZ; zz < maxZ; ++zz) {
                    int blockID = entity.world.getBlockId(xx, yy, zz);
                    if (blockID == 0) continue;

                    String nameToCheck;
                    nameToCheck = Block.BLOCKS[blockID].getTranslationKey();
                    if (nameToCheck != null && !nameToCheck.isEmpty() && nameToCheck.equals(blockName))
                        return true;
                }
            }
        }

        return false;
    }

    public static void disorientEntity(Entity entity) {
        double rotD = 0.0D;
        double motD = 0.0D;

        double d = entity.world.random.nextGaussian();
        double d1 = 0.1D * d;
        motD = 0.2D * d1 + 0.8D * motD;
        entity.velocityX += motD;
        entity.velocityZ += motD;

        double d2 = 0.78D * d;
        rotD = 0.125D * d2 + 0.875D * rotD;

        entity.yaw = (float) ((double) entity.yaw + rotD);
        entity.pitch = (float) ((double) entity.pitch + rotD);
    }

    public static void slowEntity(Entity entity) {
        entity.velocityX *= 0.8D;
        entity.velocityZ *= 0.8D;
    }

    public static ItemEntity getClosestItem(Entity entity, double radius, int firstItem, int secondItem) {
        double distance = -1D;
        ItemEntity entityitem = null;
        List<Entity> entities = entity.world.getEntities(entity, entity.boundingBox.expand(radius, radius, radius));
        for (Entity ent : entities) {
            if (!(ent instanceof ItemEntity))
                continue;

            ItemEntity item = (ItemEntity) ent;
            if (item.stack.itemId != firstItem && secondItem != 0 && item.stack.itemId != secondItem)
                continue;

            double itemDist = item.getSquaredDistance(entity.x, entity.y, entity.z);
            if ((radius < 0.0D || itemDist < radius * radius) && (distance == -1D || itemDist < distance)) {
                distance = itemDist;
                entityitem = item;
            }
        }

        return entityitem;
    }

    /**
     * Fixes entities stuck in a death animation while still alive.
     * Called after a crocodile death roll attack.
     */
    public static void checkForTwistedEntities(World world) {
        // SPRAWDZA wszystkie entitities na mapie... czy nie za duzo.
        // Checks for ALL entities on the map... if there aren't too many. - This is wrong I think?
        List<Entity> entities = world.entities;
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity twistedEntity) {
                if (twistedEntity.deathTime > 0 && twistedEntity.vehicle == null && twistedEntity.health > 0) {
                    twistedEntity.deathTime = 0;
                }
            }
        }
    }

    /**
     * <p>A list of normal entities to ignore when targeting.</p>
     *
     * @param hunter The targeting entity.
     * @param victim The  targeted entity.
     * @return true if the targeted mob should be ignored.
     * @deprecated - Use {@link #shouldIgnoreAsTarget(Entity, Entity)} instead.
     */
    @Deprecated(since = "1.0.4")
    public static boolean entitiesToIgnore(Entity hunter, Entity victim) {
        return !(victim instanceof LivingEntity)
                || (victim instanceof MonsterEntity) && !(hunter instanceof EntityBigCat) && !(victim instanceof EntityWWolf)
                || victim == hunter
                || victim == hunter.passenger
                || victim == hunter.vehicle
                || (victim instanceof PlayerEntity)
                || (victim instanceof EntityKittyBed)
                || (victim instanceof EntityLitterBox)
                || (victim instanceof WolfEntity) && ((WolfEntity) victim).isTamed() && !mod_mocreatures.mocGlass.huntercreatures.attackwolves
                || (victim instanceof EntityHorse) && ((EntityHorse) victim).getTamed() && !mod_mocreatures.mocGlass.huntercreatures.attackhorses
                || (victim instanceof EntityDolphin) && ((EntityDolphin) victim).getTamed() && !mod_mocreatures.mocGlass.huntercreatures.attackdolphins
                || (victim instanceof EntityCollie) && ((EntityCollie) victim).isTamed() && !mod_mocreatures.mocGlass.huntercreatures.attackwolves;
    }

    /**
     * <p>A list of tamed mobs to ignore when targeting, this is controled by the config options.</p>
     *
     * @param hunter The targeting entity.
     * @param victim The targeted entity.
     * @return true if the targeted mob should be ignored.
     * @deprecated - Use {@link #shouldIgnoreAsTarget} instead.
     */
    @Deprecated(since = "1.0.4")
    // opcje dla nie-atakowania bigcats tamed i cats na razie nie ma.
    // There are no options to ignore tamed BigCats or Kitties yet.
    public static boolean entitiesTamedIgnore(Entity hunter, Entity victim) {
        return (hunter instanceof MoCreatureNamed
                && victim instanceof MoCreatureNamed
                && ((MoCreatureNamed) hunter).getTamed()
                && ((MoCreatureNamed) victim).getTamed());
    }

    /**
     * A list of entities to ignore when targeting.
     *
     * @param hunter The targeting entity.
     * @param target The targeted entity.
     * @return true if the targeted entity should be ignored.
     */
    public static boolean shouldIgnoreAsTarget(Entity hunter, Entity target) {
        // Don't target non-living entities.
        if (!(target instanceof LivingEntity)) return true;

        // What was the point of this call? Most neutral/hostile mobs can attack players anyway.
        // if (victim instanceof PlayerEntity) return true;

        // If the victim is a monster entity, and
        // the hunter is a big cat: return false, they can attack monsters.
        // the target is a wild wolf: return false, everyone can attack wolves.
        // otherwise return true for all other monsters.
        if (target instanceof MonsterEntity) {
            if (hunter instanceof EntityBigCat) return false;
            if (target instanceof EntityWWolf) return false;

            return true;
        }

        // Don't target yourself (the hunter), or your passenger/vehicle.
        if (target == hunter) return true;
        if (target == hunter.passenger || target == hunter.vehicle) return true;

        // Don't target 'furniture' entities.
        if (target instanceof EntityKittyBed || target instanceof EntityLitterBox) return true;

        // Don't target tamed entities. This is config dependent.
        if (target instanceof EntityHorse
                && ((EntityHorse) target).getTamed()
                && !mod_mocreatures.mocGlass.huntercreatures.attackhorses) return true;

        if (target instanceof EntityDolphin && ((EntityDolphin) target).getTamed()
                && !mod_mocreatures.mocGlass.huntercreatures.attackdolphins) return true;

        if (target instanceof WolfEntity
                && ((WolfEntity) target).isTamed()
                && !mod_mocreatures.mocGlass.huntercreatures.attackwolves) return true;

        if (target instanceof EntityCollie
                && ((EntityCollie) target).isTamed()
                && !mod_mocreatures.mocGlass.huntercreatures.attackwolves) return true;

        // Don't target another tamed creature if you're ALSO tamed.
        return hunter instanceof MoCreatureNamed
                && target instanceof MoCreatureNamed
                && ((MoCreatureNamed) hunter).getTamed()
                && ((MoCreatureNamed) target).getTamed();
    }
}
