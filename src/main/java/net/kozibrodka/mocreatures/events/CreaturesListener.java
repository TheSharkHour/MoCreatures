package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.entity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

@SuppressWarnings("unused")
public class CreaturesListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerEntities(EntityRegisterEvent event) {
        event.register(Identifier.of(MOD_ID, "Bear"), EntityBear.class);
        event.register(Identifier.of(MOD_ID, "Bird"), EntityBird.class);
        event.register(Identifier.of(MOD_ID, "Boar"), EntityBoar.class);
        event.register(Identifier.of(MOD_ID, "Bunny"), EntityBunny.class);
        event.register(Identifier.of(MOD_ID, "CaveOgre"), EntityCaveOgre.class);
        event.register(Identifier.of(MOD_ID, "Deer"), EntityDeer.class);
        event.register(Identifier.of(MOD_ID, "Duck"), EntityDuck.class);
        event.register(Identifier.of(MOD_ID, "FireOgre"), EntityFireOgre.class);
        event.register(Identifier.of(MOD_ID, "Fishy"), EntityFishy.class);
        event.register(Identifier.of(MOD_ID, "FishyEgg"), EntityFishyEgg.class);
        event.register(Identifier.of(MOD_ID, "FlameWraith"), EntityFlameWraith.class);
        event.register(Identifier.of(MOD_ID, "Fox"), EntityFox.class);
        event.register(Identifier.of(MOD_ID, "HellRat"), EntityHellRat.class);
        event.register(Identifier.of(MOD_ID, "KittyBed"), EntityKittyBed.class);
        event.register(Identifier.of(MOD_ID, "LitterBox"), EntityLitterBox.class);
        event.register(Identifier.of(MOD_ID, "Mouse"), EntityMouse.class);
        event.register(Identifier.of(MOD_ID, "Ogre"), EntityOgre.class);
        event.register(Identifier.of(MOD_ID, "PolarBear"), EntityPolarBear.class);
        event.register(Identifier.of(MOD_ID, "Rat"), EntityRat.class);
        event.register(Identifier.of(MOD_ID, "Shark"), EntityShark.class);
        event.register(Identifier.of(MOD_ID, "SharkEgg"), EntitySharkEgg.class);
        event.register(Identifier.of(MOD_ID, "WereWolf"), EntityWerewolf.class);
        event.register(Identifier.of(MOD_ID, "Wraith"), EntityWraith.class);
        event.register(Identifier.of(MOD_ID, "WildWolf"), EntityWWolf.class);

        event.register(Identifier.of(MOD_ID, "Zebra"), EntityZebra.class);
        event.register(Identifier.of(MOD_ID, "Hippo"), EntityHippo.class);
        event.register(Identifier.of(MOD_ID, "Elephant"), EntityElephant.class);
        event.register(Identifier.of(MOD_ID, "Giraffe"), EntityGiraffe.class);
        event.register(Identifier.of(MOD_ID, "Camel"), EntityCamel.class);
        event.register(Identifier.of(MOD_ID, "Mummy"), EntityMummy.class);
        event.register(Identifier.of(MOD_ID, "Crocodile"), EntityCrocodile.class);
        event.register(Identifier.of(MOD_ID, "Scorpion"), EntityScorpion.class);
        event.register(Identifier.of(MOD_ID, "Turtle"), EntityTurtle.class);
        event.register(Identifier.of(MOD_ID, "Ray"), EntityRay.class);
        event.register(Identifier.of(MOD_ID, "Collie"), EntityCollie.class);

        event.register(Identifier.of(MOD_ID, "Sheep"), EntitySheep.class);
        event.register(Identifier.of(MOD_ID, "BigCat"), EntityBigCat.class);
        event.register(Identifier.of(MOD_ID, "Dolphin"), EntityDolphin.class);
        event.register(Identifier.of(MOD_ID, "Horse"), EntityHorse.class);
        event.register(Identifier.of(MOD_ID, "Kitty"), EntityKitty.class);
    }

    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
        event.register(MOD_ID.id("Bear"), EntityBear::new);
        event.register(MOD_ID.id("Bird"), EntityBird::new);
        event.register(MOD_ID.id("Boar"), EntityBoar::new);
        event.register(MOD_ID.id("Bunny"), EntityBunny::new);
        event.register(MOD_ID.id("CaveOgre"), EntityCaveOgre::new);
        event.register(MOD_ID.id("Deer"), EntityDeer::new);
        event.register(MOD_ID.id("Duck"), EntityDuck::new);
        event.register(MOD_ID.id("FireOgre"), EntityFireOgre::new);
        event.register(MOD_ID.id("Fishy"), EntityFishy::new);
        event.register(MOD_ID.id("FishyEgg"), EntityFishyEgg::new);
        event.register(MOD_ID.id("FlameWraith"), EntityFlameWraith::new);
        event.register(MOD_ID.id("Fox"), EntityFox::new);
        event.register(MOD_ID.id("HellRat"), EntityHellRat::new);
        event.register(MOD_ID.id("KittyBed"), EntityKittyBed::new);
        event.register(MOD_ID.id("LitterBox"), EntityLitterBox::new);
        event.register(MOD_ID.id("Mouse"), EntityMouse::new);
        event.register(MOD_ID.id("Ogre"), EntityOgre::new);
        event.register(MOD_ID.id("PolarBear"), EntityPolarBear::new);
        event.register(MOD_ID.id("Rat"), EntityRat::new);
        event.register(MOD_ID.id("Shark"), EntityShark::new);
        event.register(MOD_ID.id("SharkEgg"), EntitySharkEgg::new);
        event.register(MOD_ID.id("WereWolf"), EntityWerewolf::new);
        event.register(MOD_ID.id("Wraith"), EntityWraith::new);
        event.register(MOD_ID.id("WildWolf"), EntityWWolf::new);

        event.register(MOD_ID.id("Zebra"), EntityZebra::new);
        event.register(MOD_ID.id("Hippo"), EntityHippo::new);
        event.register(MOD_ID.id("Elephant"), EntityElephant::new);
        event.register(MOD_ID.id("Giraffe"), EntityGiraffe::new);
        event.register(MOD_ID.id("Camel"), EntityCamel::new);
        event.register(MOD_ID.id("Mummy"), EntityMummy::new);
        event.register(MOD_ID.id("Crocodile"), EntityCrocodile::new);
        event.register(MOD_ID.id("Scorpion"), EntityScorpion::new);
        event.register(MOD_ID.id("Turtle"), EntityTurtle::new);
        event.register(MOD_ID.id("Ray"), EntityRay::new);
        event.register(MOD_ID.id("Collie"), EntityCollie::new);
        event.register(MOD_ID.id("Sheep"), EntitySheep::new);

        event.register(MOD_ID.id("BigCat"), EntityBigCat::new);
        event.register(MOD_ID.id("Dolphin"), EntityDolphin::new);
        event.register(MOD_ID.id("Horse"), EntityHorse::new);
        event.register(MOD_ID.id("Kitty"), EntityKitty::new);
    }
}
