package net.arzinist.supermod.entity;

import net.arzinist.supermod.ArzinMod;
import net.arzinist.supermod.entity.custom.NeighborEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<NeighborEntity> NEIGHBOR = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(ArzinMod.MOD_ID, "neighbor"),
            EntityType.Builder.create(NeighborEntity::new, SpawnGroup.CREATURE).dimensions(0.8f,3f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,Identifier.of(ArzinMod.MOD_ID,"neighbor")))
    );

    public static void registerModEntities() {
        ArzinMod.LOGGER.info("Registering Mod Entities for " + ArzinMod.MOD_ID);

        FabricDefaultAttributeRegistry.register(NEIGHBOR, NeighborEntity.createMobAttributes());
    }
}
