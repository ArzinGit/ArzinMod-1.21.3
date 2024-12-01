package net.arzinist.supermod;

import net.arzinist.supermod.entity.ModEntities;
import net.arzinist.supermod.entity.client.NeighborEntityModel;
import net.arzinist.supermod.entity.client.NeighborEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.util.Identifier;

public class ArzinModClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_NEIGHBOR_LAYER = new EntityModelLayer(Identifier.of(ArzinMod.MOD_ID, "neighbor"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NEIGHBOR, (context) -> {
            return new NeighborEntityRenderer(context, new NeighborEntityModel());
        });
    }
}
