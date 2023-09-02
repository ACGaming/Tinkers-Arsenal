package io.github.redstoneparadox.tinkersarsenal.proxy;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.entities.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.events.ArsenalRegistryEvents;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalArmorMaterials;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalArmorTraits;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ArsenalRegistryEvents());
        ArsenalToolTraits.initToolTraits();
        ArsenalToolMaterials.initToolMaterials();

        if (Loader.isModLoaded("conarm")) {
            ArsenalArmorTraits.initArmorTraits();
            ArsenalArmorMaterials.initArmorMaterials();
        }

        ArsenalEntities.init();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ArsenalTools.initToolParts(registry);
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
    }

    public void registerModels() {
        if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
            TinkersArsenal.logger.error(
                    "Proxy.registerModels has to be called during preInit. Otherwise the models wont be found on first load.");
        }
    }


    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
    }

    public void registerToolModel(ToolCore toolCore) {
    }
}
