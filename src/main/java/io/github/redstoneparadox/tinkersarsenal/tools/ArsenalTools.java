package io.github.redstoneparadox.tinkersarsenal.tools;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.geometry.Vector2;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.AmmoBoomstickShot;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.ToolBoomstick;
import io.github.redstoneparadox.tinkersarsenal.tools.utility.ToolShears;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

/**
 * Created by RedstoneParadox on 7/31/2018.
 */

public class ArsenalTools {
    public static ToolCore boomstick;
    public static ToolCore boomstickShot;
    public static ToolCore shears;

    public static ToolPart boomstickBarrel;
    public static ToolPart boomstickHandle;
    public static ToolPart bayonet;
    public static ToolPart bullet;
    public static ToolPart bulletShell;

    private static final List<ToolCore> TOOLS = new ArrayList<>();
    private static final List<IToolPart> TOOL_PARTS = new ArrayList<>();

    public static void initToolParts(IForgeRegistry<Item> registry) {
        boomstickBarrel = registerToolPart(boomstickBarrel, "boomstick_barrel", 3, registry);
        boomstickHandle = registerToolPart(boomstickHandle, "boomstick_handle", 2, registry);
        bayonet = registerToolPart(bayonet, "bayonet", 2, registry);
        bullet = registerToolPart(bullet, "bullet", 1, registry);
        bulletShell = registerToolPart(bulletShell, "bullet_shell", 1, registry);

        initTools(registry);
    }

    protected static ToolPart registerToolPart(ToolPart part, String name, int cost, IForgeRegistry<Item> registry) {
        part = new ToolPart(Material.VALUE_Ingot * cost);
        part.setRegistryName(name).setTranslationKey(name);
        registry.register(part);
        TinkerRegistry.registerToolPart(part);
        TinkersArsenal.proxy.registerToolPartModel(part);
        TOOL_PARTS.add(part);

        return part;
    }

    public static void initTools(IForgeRegistry<Item> registry) {
        boomstick = new ToolBoomstick();
        boomstickShot = new AmmoBoomstickShot();
        shears = new ToolShears();

        registerTool(boomstick, true, registry);
        registerTool(boomstickShot, true, registry);
        registerTool(shears, false, registry);

        registerToolBuilding();
    }

    protected static void registerTool(ToolCore toolCore, boolean forge, IForgeRegistry<Item> registry) {
        registry.register(toolCore);
        if (forge) {
            TinkerRegistry.registerToolForgeCrafting(toolCore);
        }
        else {
            TinkerRegistry.registerToolCrafting(toolCore);
        }
        TinkersArsenal.proxy.registerToolModel(toolCore);
        TOOLS.add(toolCore);
    }

    public static void initToolGUIs() {
        ToolBuildGuiInfo boomstickInfo = new ToolBuildGuiInfo(boomstick);
        boomstickInfo.addSlotPosition(32 + 12, 41 + 12);
        boomstickInfo.addSlotPosition(32 - 12, 41 - 12);
        boomstickInfo.addSlotPosition(32 - 12, 41 + 12);
        TinkerRegistryClient.addToolBuilding(boomstickInfo);

        ToolBuildGuiInfo boomstickShotInfo = new ToolBuildGuiInfo(boomstickShot);
        boomstickShotInfo.addSlotPosition(32 + 20, 41 - 8);
        boomstickShotInfo.addSlotPosition(32, 41 + 12);
        TinkerRegistryClient.addToolBuilding(boomstickShotInfo);

        ToolBuildGuiInfo shearInfo = new ToolBuildGuiInfo(shears);
        shearInfo.addSlotPosition(32 - 12, 41 + 12); // bot left
        shearInfo.addSlotPosition(32 + 12, 41 - 12); // top left
        TinkerRegistryClient.addToolBuilding(shearInfo);
    }

    protected static void registerToolGUI(ToolCore toolCore, ArrayList<Vector2> vector2s) {
        ToolBuildGuiInfo info = new ToolBuildGuiInfo(toolCore);

        for (Vector2 vector2 : vector2s) {
            int xPos = vector2.getXLength();
            int yPos = vector2.getYLength();

            info.addSlotPosition(xPos, yPos);
        }
        TinkerRegistryClient.addToolBuilding(info);
    }

    protected static void registerToolBuilding() {
        for (final IToolPart part: TOOL_PARTS) {
            for (final ToolCore tool: TOOLS) {
                for (final PartMaterialType pmt: tool.getRequiredComponents()) {
                    if (pmt.getPossibleParts().contains(part)) {
                        TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), (Item)part));
                    }
                }
            }
        }
    }
}
