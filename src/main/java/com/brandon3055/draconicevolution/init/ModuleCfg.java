package com.brandon3055.draconicevolution.init;

import codechicken.lib.config.*;
import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.brandonscore.utils.DataUtils;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.api.modules.Module;
import com.brandon3055.draconicevolution.api.modules.lib.BaseModule;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModuleCfg {
    private static ConfigCategory moduleStats;

    //@formatter:off
    //Default Values
    private static Integer[] WYVERN_TOOLS            = new Integer[] {4, 4};    // 4 x 4, 16 slots total
    private static Integer[] DRACONIC_TOOLS          = new Integer[] {6, 5};    // 6 x 5, 30 slots total
    private static Integer[] CHAOTIC_TOOLS           = new Integer[] {8, 6};    // 8 x 6, 48 slots total

    private static Integer[] DRACONIC_STAFF          = new Integer[] {8, 6};    // 8 x 6, 48 slots total
    private static Integer[] CHAOTIC_STAFF           = new Integer[] {10, 8};   // 10 x 8, 80 slots total

    private static Integer[] WYVERN_CHESTPIECE       = new Integer[] {6, 5};    // 6 x 5, 30 slots total
    private static Integer[] DRACONIC_CHESTPIECE     = new Integer[] {8, 6};    // 8 x 6, 48 slots total
    private static Integer[] CHAOTIC_CHESTPIECE      = new Integer[] {10, 8};   // 10 x 8, 80 slots total

    private static Integer[] WYVERN_CAPACITOR        = new Integer[] {4, 4};    // 4 x 4, 16 slots total
    private static Integer[] DRACONIC_CAPACITOR      = new Integer[] {5, 5};    // 5 x 5, 25 slots total
    private static Integer[] CHAOTIC_CAPACITOR       = new Integer[] {8, 6};    // 8 x 6, 48 slots total


    public static int[] wyvernTools;
    public static int[] draconicTools;
    public static int[] chaoticTools;

    public static int[] draconicStaff;
    public static int[] chaoticStaff;

    public static int[] wyvernChestpiece;
    public static int[] draconicChestpiece;
    public static int[] chaoticChestpiece;

    public static int[] wyvernCapacitor;
    public static int[] draconicCapacitor;
    public static int[] chaoticCapacitor;

    public static boolean removeInvalidModules = false;

    //@formatter:on

    public static void loadConfig(ConfigCategory configTag) {
        loadModuleStatsConfig();

        ConfigCategory moduleTag = configTag.getCategory("Module Grids");
        moduleTag.setComment("These settings allow you to override the base module grid sizes for DE's modular items.",
                "The format is:",
                "\tI:\"configTag\" <",
                "\t\twidth",
                "\t\theight",
                "\t>",
                "Leave blank to use the internal default value.",
                "This value is listed in each properties description but may not be valid if this config was generated by a previous version of DE.",
                "Please note reducing grid size will not remove modules from existing items but will instead leave modules in an invalid state where they are",
                "still fully functional but installed outside of the grid bounds.");

        moduleTag.getValue("removeInvalidModules")
                .setComment("(read category description)", "Setting this to true will cause invalid modules to be deleted from the module grid.")
                .setDefaultBoolean(false)
                .onSync((tag, type) -> removeInvalidModules = tag.getBoolean());

        moduleTag.getValueList("wyvernTools")
                .setComment("Internal Default Value: " + WYVERN_TOOLS[0] + " x " + WYVERN_TOOLS[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> wyvernTools = getSize(tag, WYVERN_TOOLS));
        moduleTag.getValueList("draconicTools")
                .setComment("Internal Default Value: " + DRACONIC_TOOLS[0] + " x " + DRACONIC_TOOLS[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> draconicTools = getSize(tag, DRACONIC_TOOLS));
        moduleTag.getValueList("chaoticTools")
                .setComment("Internal Default Value: " + CHAOTIC_TOOLS[0] + " x " + CHAOTIC_TOOLS[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> chaoticTools = getSize(tag, CHAOTIC_TOOLS));

        moduleTag.getValueList("draconicStaff")
                .setComment("Internal Default Value: " + DRACONIC_STAFF[0] + " x " + DRACONIC_STAFF[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> draconicStaff = getSize(tag, DRACONIC_STAFF));
        moduleTag.getValueList("chaoticStaff")
                .setComment("Internal Default Value: " + CHAOTIC_STAFF[0] + " x " + CHAOTIC_STAFF[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> chaoticStaff = getSize(tag, CHAOTIC_STAFF));

        moduleTag.getValueList("wyvernChestpiece")
                .setComment("Internal Default Value: " + WYVERN_CHESTPIECE[0] + " x " + WYVERN_CHESTPIECE[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> wyvernChestpiece = getSize(tag, WYVERN_CHESTPIECE));
        moduleTag.getValueList("draconicChestpiece")
                .setComment("Internal Default Value: " + DRACONIC_CHESTPIECE[0] + " x " + DRACONIC_CHESTPIECE[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> draconicChestpiece = getSize(tag, DRACONIC_CHESTPIECE));
        moduleTag.getValueList("chaoticChestpiece")
                .setComment("Internal Default Value: " + CHAOTIC_CHESTPIECE[0] + " x " + CHAOTIC_CHESTPIECE[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> chaoticChestpiece = getSize(tag, CHAOTIC_CHESTPIECE));

        moduleTag.getValueList("wyvernCapacitor")
                .setComment("Internal Default Value: " + WYVERN_CAPACITOR[0] + " x " + WYVERN_CAPACITOR[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> wyvernCapacitor = getSize(tag, WYVERN_CAPACITOR));
        moduleTag.getValueList("draconicCapacitor")
                .setComment("Internal Default Value: " + DRACONIC_CAPACITOR[0] + " x " + DRACONIC_CAPACITOR[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> draconicCapacitor = getSize(tag, DRACONIC_CAPACITOR));
        moduleTag.getValueList("chaoticCapacitor")
                .setComment("Internal Default Value: " + CHAOTIC_CAPACITOR[0] + " x " + CHAOTIC_CAPACITOR[1])
                .setDefaultInts(Collections.emptyList())
                .onSync((tag, type) -> chaoticCapacitor = getSize(tag, CHAOTIC_CAPACITOR));


        moduleTag.syncTagToClient();
    }

    private static int[] getSize(ConfigValueList tag, Integer[] defaultValue) {
        List<Integer> tagValue = tag.getInts();
        return DataUtils.toPrimitive(tagValue.size() == 2 ? tagValue.toArray(new Integer[0]) : defaultValue);
    }

    public static int toolWidth(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernTools[0];
            case DRACONIC:
                return draconicTools[0];
            case CHAOTIC:
                return chaoticTools[0];
        }
        return 1;
    }

    public static int toolHeight(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernTools[1];
            case DRACONIC:
                return draconicTools[1];
            case CHAOTIC:
                return chaoticTools[1];
        }
        return 1;
    }

    public static int staffWidth(TechLevel techLevel) {
        switch (techLevel) {
            case DRACONIC:
                return draconicStaff[0];
            case CHAOTIC:
                return chaoticStaff[0];
        }
        return 1;
    }

    public static int staffHeight(TechLevel techLevel) {
        switch (techLevel) {
            case DRACONIC:
                return draconicStaff[1];
            case CHAOTIC:
                return chaoticStaff[1];
        }
        return 1;
    }

    public static int chestpieceWidth(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernChestpiece[0];
            case DRACONIC:
                return draconicChestpiece[0];
            case CHAOTIC:
                return chaoticChestpiece[0];
        }
        return 1;
    }

    public static int chestpieceHeight(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernChestpiece[1];
            case DRACONIC:
                return draconicChestpiece[1];
            case CHAOTIC:
                return chaoticChestpiece[1];
        }
        return 1;
    }

    public static int capacitorWidth(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernCapacitor[0];
            case DRACONIC:
                return draconicCapacitor[0];
            case CHAOTIC:
                return chaoticCapacitor[0];
        }
        return 1;
    }

    public static int capacitorHeight(TechLevel techLevel) {
        switch (techLevel) {
            case WYVERN:
                return wyvernCapacitor[1];
            case DRACONIC:
                return draconicCapacitor[1];
            case CHAOTIC:
                return chaoticCapacitor[1];
        }
        return 1;
    }

    //Generated module stats

    private static void loadModuleStatsConfig() {
        ConfigCategory cat = new ConfigFile(DraconicEvolution.MODID+":modules")
                .path(Paths.get("./config/brandon3055/ModuleStats.cfg"))
                .load();
        moduleStats = cat.getCategory("Module Stats");
        moduleStats.setComment("All of the values in this file are the defaults as of the time the file was generated.",
                "If you wish to set custom values you must set the \"override\" field to true then specify your custom values.");
        moduleStats.syncTagToClient();
        moduleStats.onSync((configTag, syncType) -> DEModules.moduleItemMap.keySet().forEach(BaseModule::reloadData));
    }

    public static void saveStateConfig(){
        moduleStats.save();
    }

    public static ConfigCategory getModuleTag(ResourceLocation moduleName) {
        ConfigCategory tag = moduleStats.getCategory(moduleName.toString());
        return tag;
    }

    public static boolean getOverride(ConfigCategory tag) {
        if (tag.has("override")) {
            return tag.getValue("override").getBoolean();
        }

        return tag.getValue("override")
                .setComment("Set this to true if you wish to override this module's default stats.")
                .setDefaultBoolean(false)
                .getBoolean();
    }

    public static long getModuleLong(Module<?> module, String tagName, long defaultValue) {
        ConfigCategory tag = getModuleTag(Objects.requireNonNull(module.getRegistryName()));
        boolean override = getOverride(tag);
        ConfigValue longTag = tag.getValue(tagName).setDefaultLong(defaultValue);
        if (!override) {
            longTag.setLong(defaultValue);
        }
        return longTag.getLong();
    }

    public static int getModuleInt(Module<?> module, String tagName, int defaultValue) {
        ConfigCategory tag = getModuleTag(Objects.requireNonNull(module.getRegistryName()));
        boolean override = getOverride(tag);
        ConfigValue valueTag = tag.getValue(tagName).setDefaultInt(defaultValue);
        if (!override) {
            valueTag.setInt(defaultValue);
        }
        return valueTag.getInt();
    }

    public static double getModuleDouble(Module<?> module, String tagName, double defaultValue) {
        ConfigCategory tag = getModuleTag(Objects.requireNonNull(module.getRegistryName()));
        boolean override = getOverride(tag);
        ConfigValue valueTag = tag.getValue(tagName).setDefaultDouble(defaultValue);
        if (!override) {
            valueTag.setDouble(defaultValue);
        }
        return valueTag.getDouble();
    }

    public static boolean getModuleBoolean(Module<?> module, String tagName, boolean defaultValue) {
        ConfigCategory tag = getModuleTag(Objects.requireNonNull(module.getRegistryName()));
        boolean override = getOverride(tag);
        ConfigValue valueTag = tag.getValue(tagName).setDefaultBoolean(defaultValue);
        if (!override) {
            valueTag.setBoolean(defaultValue);
        }
        return valueTag.getBoolean();
    }
}
