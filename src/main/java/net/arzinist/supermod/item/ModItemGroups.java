package net.arzinist.supermod.item;

import net.arzinist.supermod.ArzinMod;
import net.arzinist.supermod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ARZIN_TAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ArzinMod.MOD_ID, "arzin_tab"), FabricItemGroup.builder()
                            .icon(() -> new ItemStack(ModItems.MARIOS_ITEM)).displayName(Text.translatable("itemgroup.arzinmod.arzin_tab"))
                            .entries((displayContext, entries) -> {
                                entries.add(ModItems.MARIOS_ITEM);
                                entries.add(ModItems.RED_KEY);
                                entries.add(ModBlocks.HORROR_BLOCK);
                                entries.add(ModBlocks.RED_KEY_BLOCK);
                            })
                    .build());

    public static void registerItemGroups() {
        ArzinMod.LOGGER.info("Registering Item Groups For"+ArzinMod.MOD_ID);
    }
}
