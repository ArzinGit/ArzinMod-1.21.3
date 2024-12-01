package net.arzinist.supermod.item.custom;

import net.arzinist.supermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class Red_KeyItem extends Item {
    private static final Map<Block, Block> UNLOCK_MAP =
            Map.of(
                    ModBlocks.RED_KEY_BLOCK, Blocks.AIR
            );

    public Red_KeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(UNLOCK_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), UNLOCK_MAP.get(clickedBlock).getDefaultState());

                world.playSound(null,context.getBlockPos(), SoundEvents.BLOCK_VAULT_INSERT_ITEM, SoundCategory.BLOCKS);

                TntEntity tnt = new TntEntity(EntityType.TNT, world);
                tnt.setPosition(context.getBlockPos().toCenterPos());
                tnt.setFuse(0);
                world.spawnEntity(tnt);

            }
        }

        return ActionResult.SUCCESS;
    }
}
