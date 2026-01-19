package io.github.redstoneparadox.tinkersarsenal.misc;

import io.github.redstoneparadox.tinkersarsenal.Tags;
import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import java.util.HashMap;
import java.util.Map;
import net.minecraftforge.common.config.Config;

/**
 * Created by RedstoneParadox on 8/23/2018.
 */
@Config(modid = Tags.MOD_ID, type = Config.Type.INSTANCE, name = "TinkersArsenal")
public class ArsenalConfig {
    //Misc:
    @Config.Ignore
    private static int version = 1;

    @Config.Ignore
    private static final String disableText = "Set to false to disable ";
    @Config.Ignore
    private static final String shaftText  = " as a shaft material.";
    @Config.Ignore
    private static final String notImp = " (Not implemented yet!)";
    @Config.Ignore
    private static final String setCompat = "Enable/disable material compatibility for ";

    @Config.LangKey("category.misc.name")
    @Config.Comment("Like it says on the tin.")
    public static final MiscellaneousOptions MISCELLANEOUS_OPTIONS = new MiscellaneousOptions();
    @Config.LangKey("category.base.name")
    @Config.Comment("Enable/disable materials added by Tinker's Arsenal")
    public static final BaseCategory BASE_CATEGORY = new BaseCategory();
    @Config.LangKey("category.ore.name")
    @Config.Comment(setCompat + "common modded materials.")
    public static final OreDictionaryCategory ORE_DICTIONARY_CATEGORY = new OreDictionaryCategory();
    @Config.LangKey("category.foundation.name")
    @Config.Comment(setCompat + "Thermal Foundation")
    public static final ThermalExpansionCategory THERMAL_EXPANSION_CATEGORY = new ThermalExpansionCategory();

    public static class MiscellaneousOptions {

    }

    public static class BaseCategory {
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Gold."})
        public boolean gold = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Diamond."})
        public boolean diamond = true;
    }

    public static class OreDictionaryCategory {
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Tin."})
        public boolean tin = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Aluminum."})
        public boolean aluminum = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Constantan."})
        public boolean constantan = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Nickel."})
        public boolean nickel = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Platinum."})
        public boolean platinum = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Iridium."})
        public boolean iridium = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Mithril/Mana Infused Metal." + notImp})
        public boolean mithril = false;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Invar."})
        public boolean invar = true;
    }

    public static class ThermalExpansionCategory {
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Luminum." + notImp})
        public boolean luminum = false;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Signalium." + notImp})
        public boolean signalium = false;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Enderium." + notImp})
        public boolean enderium = false;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Blizz Rod" + shaftText})
        public boolean blizz = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Blitz Rod" + shaftText})
        public boolean blitz = true;
        @Config.RequiresMcRestart
        @Config.Comment({disableText + "Basalz Rod" + shaftText})
        public boolean basalz = true;
    }


    //Config access stuff:
    public static boolean isMaterialAllowed(String material) {
        if (getMaterialTypes().containsKey(material)) {
            return getMaterialTypes().get(material);
        }
        TinkersArsenal.logger.error("Could not find config option for " + material + "!");
        return false;
    }

    private static Map<String, Boolean> getMaterialTypes() {
        Map<String, Boolean> map = new HashMap<>();
        //Base materials:
        map.put("gold", BASE_CATEGORY.gold);
        map.put("diamond", BASE_CATEGORY.diamond);
        //Common ore-dict materials:
        map.put("tin", ORE_DICTIONARY_CATEGORY.tin);
        map.put("aluminum", ORE_DICTIONARY_CATEGORY.aluminum);
        map.put("constantan", ORE_DICTIONARY_CATEGORY.constantan);
        map.put("nickel", ORE_DICTIONARY_CATEGORY.nickel);
        map.put("platinum", ORE_DICTIONARY_CATEGORY.platinum);
        map.put("iridium", ORE_DICTIONARY_CATEGORY.iridium);
        map.put("manaInfusedMetal", ORE_DICTIONARY_CATEGORY.mithril);
        map.put("mithril", ORE_DICTIONARY_CATEGORY.mithril);
        map.put("invar", ORE_DICTIONARY_CATEGORY.invar);
        //Thermal Foundation Materials:
        map.put("luminum", THERMAL_EXPANSION_CATEGORY.luminum);
        map.put("signalium", THERMAL_EXPANSION_CATEGORY.signalium);
        map.put("enderium", THERMAL_EXPANSION_CATEGORY.enderium);
        //Thermal Foundation Shaft Materials:
        map.put("blizz_rod", THERMAL_EXPANSION_CATEGORY.blizz);
        map.put("blitz_rod", THERMAL_EXPANSION_CATEGORY.blitz);
        map.put("basalz_rod", THERMAL_EXPANSION_CATEGORY.basalz);
        return map;
    }
}
