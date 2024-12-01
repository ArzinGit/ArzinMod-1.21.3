package net.arzinist.supermod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;

public class Red_Key_BlockBlock extends Block {
    public Red_Key_BlockBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onDestroyedByExplosion(ServerWorld world, BlockPos pos, Explosion explosion) {
        super.onDestroyedByExplosion(world, pos, explosion);

        TntEntity tnt = new TntEntity(EntityType.TNT, world);
        tnt.setPosition(pos.toCenterPos());
        tnt.setFuse(0);
        world.spawnEntity(tnt);
    }
}
