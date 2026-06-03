package net.kozibrodka.mocreatures.events;

import net.kozibrodka.mocreatures.mixin.AchievementPageAccessor;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.achievement.Achievements;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.achievement.TemplateAchievement;
import net.modificationstation.stationapi.api.util.Namespace;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class AchievementListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    // Achievements
    public static Achievement Indiana;
    public static Achievement BunnyKilla;
    public static Achievement WilfFlyingWest;
    public static Achievement RobertMaklowicz;

    public static final ArrayList<Achievement> ACHIEVEMENTS = new ArrayList<>();

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        Indiana = create("indiana", mod_mocreatures.whip, -4, -4, Achievements.OPEN_INVENTORY);
        BunnyKilla = create("bunnykilla", mod_mocreatures.whip, -5, -5, Achievements.OPEN_INVENTORY);
        WilfFlyingWest = create("wildflyingwest", mod_mocreatures.horsesaddle, -6, -6, Achievements.OPEN_INVENTORY);
        RobertMaklowicz = create("robertmaklowicz", Block.FLOWING_WATER, -7, -7, Achievements.OPEN_INVENTORY);

        WilfFlyingWest.challenge();
        RobertMaklowicz.challenge();

        event.achievements.addAll(ACHIEVEMENTS);

        // Weird achievement page getter.
        AchievementPage vanillaPage = null;
        List<AchievementPage> list = AchievementPageAccessor.getPAGES();
        for (AchievementPage achievementPage : list) {
            if (Objects.equals(achievementPage.name(), "stationapi.minecraft")) {
                vanillaPage = achievementPage;
            }
        }

        if(vanillaPage != null) {
            vanillaPage.addAchievements(ACHIEVEMENTS.toArray(Achievement[]::new));
        }
    }

    public static Achievement create(String name, Item icon, int x, int y, Achievement parent) {
        Achievement achievement = new TemplateAchievement(NAMESPACE.id(name), "mocreatures" + "." + name, x, y, new ItemStack(icon), parent);
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }

    public static Achievement create(String name, Block icon, int x, int y, Achievement parent) {
        Achievement achievement = new TemplateAchievement(NAMESPACE.id(name), "mocreatures" + "." + name, x, y, new ItemStack(icon), parent);
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }
}
