package net.fabricmc.Beginner;

import net.fabricmc.Beginner.items.ExplosiveBow;
import net.fabricmc.Beginner.items.ShotgunHomemade;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Main implements ModInitializer {

	static public final Item SHOTGUN_HOMEMADE = new ShotgunHomemade(new FabricItemSettings().group(ItemGroup.MISC));
	static public final Item EXPLOSIVE_BOW = new ExplosiveBow(new FabricItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");

		Registry.register(Registry.ITEM, new Identifier("beginnermod", "shotgun_homemade"), SHOTGUN_HOMEMADE);
		Registry.register(Registry.ITEM, new Identifier("beginnermod", "explosive_bow"), EXPLOSIVE_BOW);
	}
}
