package io.github.redstoneparadox.tinkersarsenal.materials;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.misc.TAConfig;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;

import java.util.ArrayList;

public class ArsenalMaterials {
	//Harvest Levels:
	protected static final int HV0ROCK = HarvestLevels.STONE;
	protected static final int HV1FE = HarvestLevels.IRON;
	protected static final int HV2DIA = HarvestLevels.DIAMOND;
	protected static final int HV3OBI = HarvestLevels.OBSIDIAN;
	protected static final int HV4CO = HarvestLevels.COBALT;
	//Part Types:
	protected static final String PART_HEAD = MaterialTypes.HEAD;
	protected static final String PART_HANDLE = MaterialTypes.HANDLE;
	protected static final String PART_EXTRA = MaterialTypes.EXTRA;
	protected static final String PART_BOW = MaterialTypes.BOW;
	protected static final String PART_BOWSTRING = MaterialTypes.BOWSTRING;
	protected static final String PART_SHAFT = MaterialTypes.SHAFT;
	protected static final String PART_FLETCHING = MaterialTypes.FLETCHING;

	public static void initMaterials() {
		if (isRegistrable("gold")) {
			ArsenalMaterial gold = new ArsenalMaterial("gold", 0xeaee57);
			setCraftability(gold, "ingotGold", TinkerFluids.gold);
			gold.addTrait(ArsenalToolTraits.MALLEABLE, PART_HEAD);
			gold.addTrait(ArsenalToolTraits.MALLEABLE, PART_EXTRA);
			addStats(gold, 14, 12.00f, 2.00f, HV0ROCK, 0.1f, -10, -7, 0.5f, 1.5f, 1f);
			gold.setRepresentativeItem(new ItemStack(Items.GOLD_INGOT));
			TinkerRegistry.addMaterial(gold);
		}

		if (isRegistrable("diamond")) {
			ArsenalMaterial diamond = new ArsenalMaterial("diamond", 0x33ebcb);
			setCraftability(diamond, "gemDiamond");
			diamond.addTrait(ArsenalToolTraits.DIAMOND_EDGE, PART_HEAD);
			diamond.addTrait(ArsenalToolTraits.RESILIENCE, PART_HANDLE);
			diamond.addTrait(ArsenalToolTraits.RESILIENCE, PART_EXTRA);
			addStats(diamond, 1270, 6.00f, 5.00f, HV3OBI, 1.1f, 100, 269, 0.5f, 1.5f, 1.75f);
			diamond.setRepresentativeItem(new ItemStack(Items.DIAMOND));
			TinkerRegistry.addMaterial(diamond);
		}

		if (isRegistrable("tin") && OreDictionary.doesOreNameExist("ingotTin")) {
			ArsenalMaterial tin = new ArsenalMaterial("tin", 0xC3E6E6);
			setCraftability(tin, "ingotTin");
			addTraits(tin, ArsenalToolTraits.MISSINGNO);
			addStats(tin, 75, 4.50f, 4.00f, HV1FE, 0.5f, -25, 75, 0.5f, 1.5f, 1.75f);
		}

		if (isRegistrable("aluminum") && OreDictionary.doesOreNameExist("ingotAluminum")) {
			ArsenalMaterial aluminum = new ArsenalMaterial("aluminum", 0xE6E6F2);
			setCraftability(aluminum, "ingotAluminum", TinkerFluids.aluminum);
			aluminum.addTrait(ArsenalToolTraits.MALLEABLE, PART_HEAD);
			addStats(aluminum, 150, 10, 4.00f, HV1FE, 0.5f, -75, 50, 0.5f, 1.5f, 1.75f);
		}

		if (isRegistrable("constantan") && OreDictionary.doesOreNameExist("ingotConstantan")) {
			ArsenalMaterial constantan = new ArsenalMaterial("constantan", 0xF9D384);
			setCraftability(constantan, "ingotConstantan");
			addTraits(constantan, ArsenalToolTraits.MISSINGNO);
			addStats(constantan, 175, 6, 4.50f, HV2DIA, 1.3f, 150, 100, 0.5f, 1.5f, 1.75f);
		}

		if (isRegistrable("nickel") && OreDictionary.doesOreNameExist("ingotNickle")) {
			ArsenalMaterial nickel = new ArsenalMaterial("nickel", 0xF6F8BD);
			setCraftability(nickel, "ingotNickel");
			addTraits(nickel, ArsenalToolTraits.MISSINGNO);
			addStats(nickel, 100, 6.5f, 5.5f, HV2DIA, 1.3f, 100, 200, 0.5f, 1.5f, 1.75f);
		}

		if (isRegistrable("platinum") && OreDictionary.doesOreNameExist("ingotPlatinum")) {
			ArsenalMaterial platinum = new ArsenalMaterial("platinum", 0x6FEAEF);
			setCraftability(platinum, "ingotPlatinum");
			addTraits(platinum, ArsenalToolTraits.MISSINGNO);
			addStats(platinum, 650, 9, 6.50f, HV4CO, 1.6f, 250, 750, 0.5f, 1.5f, 1.75f);
		}

		if (isRegistrable("iridium") && OreDictionary.doesOreNameExist("ingotIridium")) {
			ArsenalMaterial iridium = new ArsenalMaterial("iridium", 0xC4C2DC);
			setCraftability(iridium, "ingotIridium");
			addTraits(iridium, ArsenalToolTraits.MISSINGNO);
		}

		if (isRegistrable("mithril") && OreDictionary.doesOreNameExist("ingotMithril")) {
			ArsenalMaterial mithril = new ArsenalMaterial("mithril", 0x6DA7C9);
			setCraftability(mithril, "ingotMithril");
			addTraits(mithril, ArsenalToolTraits.MISSINGNO);
		}

		if (isRegistrable("invar") && OreDictionary.doesOreNameExist("ingotInvar")) {
			ArsenalMaterial invar = new ArsenalMaterial("invar", 0xE1E7E5);
			setCraftability(invar, "ingotInvar");
			addTraits(invar, ArsenalToolTraits.MISSINGNO);
			addStats(invar, 175, 6.50f, 5.50f, HV2DIA, 1.5f, 50, 250, 0.5f, 1.5f, 1.75f);
		}
	}

	protected static boolean isRegistrable(String id) {
		if (TinkerRegistry.getMaterial(id) == Material.UNKNOWN && TAConfig.isMaterialAllowed(id)) {
			return true;
		}
		return false;
	}

	protected static void setCraftability(Material material, String item, Fluid fluid) {
		String suffix = removePrefix(item);

		if (suffix != null) {
			material.addItemIngot(suffix);
			material.setCastable(true).setCraftable(false);
			material.setFluid(fluid);
			TinkerRegistry.integrate(material, fluid, suffix).toolforge();
		}
	}

	protected static void setCraftability(Material material, String item) {
		String suffix = removePrefix(item);
		String blockString = "block" + suffix;

		if (suffix != null) {
			material.addItem(item, 1, Material.VALUE_Ingot);
			material.addItem(blockString, 1, Material.VALUE_Block);
			material.setCraftable(true).setCastable(false);
			TinkerRegistry.integrate(material, suffix);
		}
	}

	@Deprecated
	private static void addTraits(Material material, AbstractTrait mainTrait, AbstractTrait secondTrait, String partType) {
		material.addTrait(mainTrait, partType);
		material.addTrait(secondTrait, partType);
	}

	@Deprecated
	private static void addTraits(Material material, AbstractTrait trait) {
		material.addTrait(trait, PART_HANDLE);
		material.addTrait(trait, PART_EXTRA);
	}

	@Deprecated
	private static void addTraits(Material material, ArrayList<String> parts, ArrayList<AbstractTrait> traits) {
		for (int ia = 0; ia<=parts.size(); ia++) {
			for (int ib = 0; ib<=traits.size(); ib++) {
				material.addTrait(traits.get(ib), parts.get(ia));
			}
		}
	}

	@Deprecated
	private static void addTraits(Material material, String part, ArrayList<AbstractTrait> traits) {
		for (int i = 0; i<=traits.size(); i++) {
			material.addTrait(traits.get(i), part);
		}
	}

	@Deprecated
	private static void addTraits(Material material, ArrayList<String> parts, AbstractTrait trait) {
		for (int i = 0; i<=parts.size(); i++) {
			material.addTrait(trait, parts.get(i));
		}
	}

	private static void addTraits(Material material, AbstractTrait trait, String part) {
		material.addTrait(trait, part);
	}

	//Finally, we add the stats here.
	private static void addStats(Material material, int dur, float spd, float dmg, int harvest, float mod, int handleDur, int exDur, float draw, float range, float bonusDmg) {
		TinkerRegistry.addMaterialStats(material,
				new HeadMaterialStats(dur, spd, dmg, harvest),
				new HandleMaterialStats(mod, handleDur),
				new ExtraMaterialStats(exDur),
				new BowMaterialStats(draw, range, bonusDmg)
		);
	}

	private static void addBowstringStats() {
	}

	private static void addShaftStats() {
	}

	private static void addFletchingStats() {
	}

	private static void addGuitarStats() {
	}

	private static String removePrefix(String item) {
		if (item.contains("gem")) {
			return item.substring(item.indexOf("gem") + 3);
		}
		else if (item.contains("ingot")) {
			return item.substring(item.indexOf("ingot") + 5);
		}
		else {
			TinkersArsenal.logger.debug("Prefix for the inputted ore dictionary entry was not found! This material will not be properly registered!");
			return null;
		}
	}
}