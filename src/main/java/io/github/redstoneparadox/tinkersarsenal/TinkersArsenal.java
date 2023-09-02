package io.github.redstoneparadox.tinkersarsenal;

import io.github.redstoneparadox.tinkersarsenal.proxy.CommonProxy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:tconstruct@[1.12.2-2.13.0.183,);" +
                "after:conarm;" +
                "after:tinkerscompendium;" +
                "after:plustic;" +
                "after:moartinkers;" +
                "after:thermalfoundation;" +
                "after:basemetals;" +
                "after:modernmetals;",
        useMetadata = true
)
public class TinkersArsenal {
    public static Logger logger;

    @SidedProxy(clientSide = "io.github.redstoneparadox.tinkersarsenal.proxy.ClientProxy", serverSide = "io.github.redstoneparadox.tinkersarsenal.proxy.ServerProxy")
    public static CommonProxy proxy;

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(Tags.MOD_ID)
    public static TinkersArsenal INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        proxy.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
