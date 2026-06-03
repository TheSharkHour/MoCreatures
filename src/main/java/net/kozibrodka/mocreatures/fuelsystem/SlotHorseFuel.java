package net.kozibrodka.mocreatures.fuelsystem;

import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SlotHorseFuel extends Slot {
    public SlotHorseFuel(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack itemstack)
    {
        return itemstack.getItem().id == mod_mocreatures.sugarlump.id || itemstack.getItem().id == mod_mocreatures.haystack.id;
    }
}
