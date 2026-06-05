package net.kozibrodka.mocreatures.item;


import net.kozibrodka.mocreatures.entity.*;
import net.kozibrodka.mocreatures.events.TextureListener;
import net.kozibrodka.mocreatures.mocreatures.MoCTools;
import net.kozibrodka.mocreatures.mocreatures.MoCreatureRacial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class MocreaturesEggItem extends TemplateItem {
    public MocreaturesEggItem(Identifier identifier, int i) {
        super(identifier);
        this.maxCount = 16;
        ide = i;
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {

        if(world.isRemote){
            return true;
        }

        if(ide == 1){
            huj = new EntityBear(world);
        }
        if(ide == 2){
            huj = new EntityBigCat(world);
            /// anty agro spawn for testing
//            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
//            huj.setPosition(i + 12, j + 1, k);
//            arg3.spawnEntity(huj);
//            ((EntityBigCat)huj).setTypeSpawn();
//            --arg.count;
//            return false;
        }
        if(ide == 3){
            huj = new EntityBird(world);
        }
        if(ide == 4){
            huj = new EntityBoar(world);
        }
        if(ide == 5){
            huj = new EntityBunny(world);
        }
        if(ide == 6){
            huj = new EntityCaveOgre(world);
        }
        if(ide == 7){
            huj = new EntityDeer(world);
        }
        if(ide == 8){
            huj = new EntityDolphin(world);
        }
        if(ide == 9){
            huj = new EntityDuck(world);
        }
        if(ide == 10){
            huj = new EntityFireOgre(world);
        }
        if(ide == 11){
            huj = new EntityFishy(world);
        }
        if(ide == 12){
            huj = new EntityFishyEgg(world);
        }
        if(ide == 13){
            huj = new EntityFlameWraith(world);
        }
        if(ide == 14){
            huj = new EntityFox(world);
        }
        if(ide == 15){
            huj = new EntityHellRat(world);
        }
        if(ide == 16){
            huj = new EntityHorse(world);
        }
        if(ide == 17){
            huj = new EntityKitty(world);
        }
        if(ide == 18){
            huj = new EntityKittyBed(world);
        }
        if(ide == 19){
            huj = new EntityLitterBox(world);
        }
        if(ide == 20){
            huj = new EntityMouse(world);
        }
        if(ide == 21){
            huj = new EntityOgre(world);
        }
        if(ide == 22){
            huj = new EntityPolarBear(world);
        }
        if(ide == 23){
            huj = new EntityRat(world);
        }
        if(ide == 24){
            huj = new EntityShark(world);
            huj.setPositionAndAnglesKeepPrevAngles(x, y, z, world.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(x, y + 1, z);
            world.spawnEntity(huj);
            ((EntityShark)huj).setType(1);
            ((EntityShark)huj).setAge(1.0F + random.nextFloat());;
            --stack.count;
            return false; ///anty megalodon from spawn-egg
        }
        if(ide == 25){
            huj = new EntitySharkEgg(world, "null");
        }
        if(ide == 26){
            huj = new EntityWerewolf(world);
        }
        if(ide == 27){
            huj = new EntityWraith(world);
        }
        if(ide == 28){
            huj = new EntityWWolf(world);
        }
        if(ide == 60){
            huj = new EntityZebra(world);
        }
        if(ide == 61){
            huj = new EntityElephant(world);
        }
        if(ide == 62){
            huj = new EntityHippo(world);
        }
        if(ide == 63){
            huj = new EntityGiraffe(world);
        }
        if(ide == 64){
            huj = new EntityCrocodile(world);
            /// anty agro spawn for testing
//            huj.setPositionAndAnglesKeepPrevAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
//            huj.setPosition(i + 12, j + 1, k);
//            arg3.spawnEntity(huj);
//            ((EntityCrocodile)huj).setTypeSpawn();
//            --arg.count;
//            return false;
        }
        if(ide == 65){
            huj = new EntityCamel(world);
        }
        if(ide == 66){
            huj = new EntityMummy(world);
        }
        if(ide == 67){
            huj = new EntityScorpion(world);
        }
        if(ide == 68){
            huj = new EntityTurtle(world);
        }
        if(ide == 69){
            huj = new EntityRay(world);
        }
        if(ide == 70){
            huj = new EntityCollie(world);
        }
        if(ide == 71){
            huj = new EntitySheep(world);
        }
        if(ide == 72){
//            huj = new EntityAirShipHorse(arg3);
        }


        if(ide >= 30 && ide < 40){
            huj = new EntityHorse(world);
            huj.setPositionAndAnglesKeepPrevAngles(x, y, z, world.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(x, y + 1, z);
            world.spawnEntity(huj);
            --stack.count;
            ((EntityHorse)huj).setType(ide - 30 + 1);
            ((EntityHorse)huj).setAdult(true);
            ((EntityHorse) huj).health = ((EntityHorse) huj).maxhealth;
            return false;
        }

        if(ide >= 40 && ide < 50){
            huj = new EntityBigCat(world);
            huj.setPositionAndAnglesKeepPrevAngles(x, y, z, world.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(x, y + 1, z);
            world.spawnEntity(huj);
            --stack.count;
            ((EntityBigCat)huj).setType(ide - 40 + 1);
            if (random.nextInt(4) == 0) {
                ((EntityBigCat)huj).setAdult(false);
                ((EntityBigCat)huj).killedByOtherEntity = true;
                ((EntityBigCat)huj).setAge(0.35F);
            }else{
                ((EntityBigCat)huj).setAdult(true);
                ((EntityBigCat)huj).setAge(1.0F);
            }
            ((EntityBigCat) huj).health = ((EntityBigCat) huj).maxhealth;
            return false;
        }

        if(ide >= 50 && ide < 60){
            huj = new EntityDolphin(world);
            huj.setPositionAndAnglesKeepPrevAngles(x, y, z, world.random.nextFloat() * 360F, 0.0F);
            huj.setPosition(x, y + 1, z);
            world.spawnEntity(huj);
            --stack.count;
            ((EntityDolphin)huj).setType(ide - 50 + 1);
            ((EntityDolphin) huj).setAge((0.8F + random.nextFloat()));
            if(((EntityDolphin) huj).getAge() < 1.5F){
                ((EntityDolphin) huj).setAge(1.7F);
            }
            ((EntityDolphin)huj).setAdult(true);
//            ((EntityDolphin) huj).health = ((EntityDolphin) huj).maxhealth; /// delfin zaczyna nisko
            return false;
        }


        if(huj == null){
            return false;
        }

        huj.setPositionAndAnglesKeepPrevAngles(x, y, z, world.random.nextFloat() * 360F, 0.0F);
        huj.setPosition(x, y + 1, z);
        world.spawnEntity(huj);
        --stack.count;
        if(huj instanceof MoCreatureRacial){
            //LOADING PROPS by EGG-SPAWN
            ((MoCreatureRacial)huj).setTypeSpawn();
        }

        return false;
    }

    public int ide;
    Entity huj;
}
