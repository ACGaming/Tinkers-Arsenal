package io.github.redstoneparadox.tinkersarsenal.materials;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalConfig;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalArmorTraits;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import java.util.ArrayList;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class ArsenalArmorMaterials {
    protected static final String PART_CORE = ArmorMaterialType.CORE;
    protected static final String PART_PLATE = ArmorMaterialType.PLATES;
    protected static final String PART_TRIM = ArmorMaterialType.TRIM;

    public static void initArmorMaterials() {
        //Gold:
        if (isRegistrable("gold")) {
            Material gold = TinkerRegistry.getMaterial("gold");
            addTraits(gold, ArsenalToolTraits.MALLEABLE, PART_CORE);
            addTraits(gold, ArsenalToolTraits.MALLEABLE);
            addArmorStats(gold, 8.8f, 11f, 0.1f, 5.8f, 0.0f, 4.5f);
        }
        //Diamond:
        if (isRegistrable("diamond")) {
            Material diamond = TinkerRegistry.getMaterial("diamond");
            addTraits(diamond, ArsenalArmorTraits.RIGIDITY, PART_CORE);
            addTraits(diamond, ArsenalArmorTraits.RESILIENCE);
            addArmorStats(diamond, 15f, 20f, 1.1f, 4.5f, 2f, 11f);
        }

        if (isRegistrable("tin")) {
            Material tin = TinkerRegistry.getMaterial("tin");
            tin.addTrait(ArsenalArmorTraits.ANTI_CORROSIVE, PART_CORE);
            addTraits(tin, ArsenalArmorTraits.ANTI_CORROSIVE);
            addArmorStats(tin, 1f, 9f, 0.5f, 1f, 0f, 1f);
        }

        if (isRegistrable("aluminum")) {
            Material aluminum = TinkerRegistry.getMaterial("aluminum");
            aluminum.addTrait(ArsenalArmorTraits.CRUMPLING, PART_CORE);
            addTraits(aluminum, ArsenalArmorTraits.CRUMPLING);
            addArmorStats(aluminum, 1f, 10f, 0.7f, 1f, 0f, 1f);
        }

        if (isRegistrable("constantan")) {
            Material constantan = TinkerRegistry.getMaterial("constantan");
            addTraits(constantan, ArsenalArmorTraits.FIRE_RESISTANCE);
            addArmorStats(constantan, 1f, 12f, 1.0f, 1f, 0f, 1f);
        }

        if (isRegistrable("nickel")) {
            Material nickel = TinkerRegistry.getMaterial("nickel");
            addTraits(nickel, ArsenalToolTraits.MISSINGNO);
            addArmorStats(nickel, 1f, 14f, 1.3f, 1f, 0f, 1f);
        }

        if (isRegistrable("platinum")) {
            Material platinum = TinkerRegistry.getMaterial("platinum");
            addTraits(platinum, ArsenalToolTraits.MISSINGNO);
            addArmorStats(platinum, 1f, 20f, 1.1f, 1f, 2f, 1f);
        }

        if (isRegistrable("iridium")) {
            Material iridium = TinkerRegistry.getMaterial("iridium");
            iridium.addTrait(ArmorTraits.heavy, PART_CORE);
            addTraits(iridium, ArmorTraits.heavy);
            addArmorStats(iridium, 15f, 20f, 1.2f, 1f, 2f, 1f);
        }

        if (isRegistrable("mithril")) {
            Material mithril = TinkerRegistry.getMaterial("mithril");
            addTraits(mithril, ArsenalToolTraits.MISSINGNO);
        }

        if (isRegistrable("invar")) {
            Material invar = TinkerRegistry.getMaterial("invar");
            invar.addTrait(ArsenalArmorTraits.ENDURING, PART_CORE);
            addTraits(invar, ArsenalArmorTraits.ENDURING);
            addArmorStats(invar, 13f, 16f, 1.3f, 3.5f, 1f, 1f);
        }
    }

    protected static boolean isRegistrable(String id) {
        return TinkerRegistry.getMaterial(id) instanceof ArsenalMaterial && ArsenalConfig.isMaterialAllowed(id);
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
    public static void addTraits(Material material, AbstractTrait mainTrait, AbstractTrait secondTrait, String partType) {
        material.addTrait(mainTrait, partType);
        material.addTrait(secondTrait, partType);
    }

    @Deprecated
    public static void addTraits(Material material, AbstractTrait trait) {
        material.addTrait(trait, PART_PLATE);
        material.addTrait(trait, PART_TRIM);
    }

    @Deprecated
    public static void addTraits(Material material, ArrayList<String> parts, ArrayList<AbstractTrait> traits) {
        for (int ia = 0; ia<=parts.size(); ia++) {
            for (int ib = 0; ib<=traits.size(); ib++) {
                material.addTrait(traits.get(ib), parts.get(ia));
            }
        }
    }

    @Deprecated
    public static void addTraits(Material material, String part, ArrayList<AbstractTrait> traits) {
        for (int i = 0; i<=traits.size(); i++) {
            material.addTrait(traits.get(i), part);
        }
    }

    @Deprecated
    public static void addTraits(Material material, ArrayList<String> parts, AbstractTrait trait) {
        for (int i = 0; i<=parts.size(); i++) {
            material.addTrait(trait, parts.get(i));
        }
    }

    public static void addTraits(Material material, AbstractTrait trait, String part) {
        material.addTrait(trait, part);
    }

    //Finally, we add the stats here.
    public static void addArmorStats(Material material, float coreDurability, float defense, float plateModifier, float plateDurability, float plateToughness, float trimDurability) {
        TinkerRegistry.addMaterialStats(material,
                new CoreMaterialStats(coreDurability, defense),
                new PlatesMaterialStats(plateModifier, plateDurability, plateToughness),
                new TrimMaterialStats(trimDurability)
        );
    }

    public static String removePrefix(String item) {
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
