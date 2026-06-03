package net.kozibrodka.mocreatures.item;

import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class GreenApple extends TemplateFoodItem
{
    public GreenApple(Identifier identifier, int healAmount, boolean isWolfFood) {
        super(identifier, healAmount, isWolfFood);
    }
}
