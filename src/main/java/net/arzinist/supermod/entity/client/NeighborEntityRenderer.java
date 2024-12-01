package net.arzinist.supermod.entity.client;

import net.arzinist.supermod.entity.custom.NeighborEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.cache.GeckoLibCache;
import software.bernie.geckolib.loading.math.MolangQueries;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NeighborEntityRenderer extends GeoEntityRenderer<NeighborEntity> {
    public NeighborEntityRenderer(EntityRendererFactory.Context renderManager, GeoModel<NeighborEntity> model) {
        super(renderManager, new NeighborEntityModel());
    }
}
