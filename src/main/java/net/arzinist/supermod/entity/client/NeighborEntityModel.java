package net.arzinist.supermod.entity.client;

import net.arzinist.supermod.ArzinMod;
import net.arzinist.supermod.entity.custom.NeighborEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NeighborEntityModel extends GeoModel<NeighborEntity>{
	private final Identifier model = Identifier.of(ArzinMod.MOD_ID, "geo/neighbor/neighbormodel.geo.json");
	private final Identifier texture = Identifier.of(ArzinMod.MOD_ID, "textures/entity/neighbor/neighbor.png");
	private final Identifier animation = Identifier.of(ArzinMod.MOD_ID, "animations/neighbor.animation.json");

	@Override
	public Identifier getModelResource(NeighborEntity neighborEntity, @Nullable GeoRenderer<NeighborEntity> geoRenderer) {
		return this.model;
	}

	@Override
	public Identifier getTextureResource(NeighborEntity neighborEntity, @Nullable GeoRenderer<NeighborEntity> geoRenderer) {
		return this.texture;
	}

	@Override
	public Identifier getAnimationResource(NeighborEntity neighborEntity) {
		return this.animation;
	}
}