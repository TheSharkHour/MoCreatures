package net.kozibrodka.mocreatures.mocreatures;

import net.minecraft.entity.Entity;

/**
 * @author TheSharkHour
 * <p>A custom interface for cross-entity methods.</p>
 */
public interface MoCEntity {

    default boolean shouldIgnore(Entity hunter, Entity target) {
        return false;
    }
}