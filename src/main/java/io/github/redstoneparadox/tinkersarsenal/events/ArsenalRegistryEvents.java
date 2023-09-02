package io.github.redstoneparadox.tinkersarsenal.events;

import io.github.redstoneparadox.tinkersarsenal.Tags;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalMaterial;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.materials.Material;

/**
 * Created by RedstoneParadox on 8/26/2018.
 */
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class ArsenalRegistryEvents {
    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Tags.MOD_ID))
        {
            ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public void onMaterialRegistry(MaterialEvent.MaterialRegisterEvent event) {
        Material material = event.material;

        if (!(material instanceof ArsenalMaterial) && ArsenalConfig.isMaterialAllowed(material.getIdentifier())) {
            event.setCanceled(true);
        }
        event.setCanceled(false);
    }
}
