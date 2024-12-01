package net.arzinist.supermod.item;

import net.arzinist.supermod.ArzinMod;
import net.arzinist.supermod.item.custom.Red_KeyItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MARIOS_ITEM = registerItem("marios_item", new Item(new Item.Settings()
            .maxCount(99)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM,Identifier.of(ArzinMod.MOD_ID,"marios_item")))));
    public static final Item RED_KEY = registerItem("red_key", new Red_KeyItem(new Item.Settings()
            .maxCount(1)

            .registryKey(RegistryKey.of(RegistryKeys.ITEM,Identifier.of(ArzinMod.MOD_ID,"red_key")))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ArzinMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArzinMod.LOGGER.info("Registering Mod Items for "+ArzinMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(MARIOS_ITEM);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(RED_KEY);
        });
    }
}
