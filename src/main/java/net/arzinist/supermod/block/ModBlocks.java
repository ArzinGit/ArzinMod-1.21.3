package net.arzinist.supermod.block;

import net.arzinist.supermod.ArzinMod;
import net.arzinist.supermod.block.custom.Red_Key_BlockBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RED_KEY_BLOCK = registerBlock("red_key_block", new Red_Key_BlockBlock(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.COPPER)
            .requiresTool()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(ArzinMod.MOD_ID,"red_key_block")))));
    public static final Block HORROR_BLOCK = registerBlock("horror_block", new Block(AbstractBlock.Settings.create().strength(10f).sounds(BlockSoundGroup.MUD)
            .requiresTool()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(ArzinMod.MOD_ID,"horror_block")))));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ArzinMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ArzinMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM,Identifier.of(ArzinMod.MOD_ID,name)))));
    }

    public static void registerModBlocks() {
        ArzinMod.LOGGER.info("Registering Mod Blocks For "+ArzinMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(RED_KEY_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(HORROR_BLOCK);
        });
    }
}
