package net.arzinist.supermod;

import net.arzinist.supermod.block.ModBlocks;
import net.arzinist.supermod.entity.ModEntities;
import net.arzinist.supermod.item.ModItemGroups;
import net.arzinist.supermod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArzinMod implements ModInitializer {
	public static final String MOD_ID = "arzinmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier CHASE_MUSIC = Identifier.of("arzinmod:chasemusic");
	public static SoundEvent CHASE_MUSIC_EVENT = SoundEvent.of(CHASE_MUSIC);
	public static final Identifier CAUGHT = Identifier.of("arzinmod:caught");
	public static SoundEvent CAUGHT_EVENT = SoundEvent.of(CAUGHT);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		Registry.register(Registries.SOUND_EVENT, ArzinMod.CHASE_MUSIC, CHASE_MUSIC_EVENT);
		Registry.register(Registries.SOUND_EVENT, ArzinMod.CAUGHT, CAUGHT_EVENT);
	}
}