package io.github.redstoneparadox.tinkersarsenal.modules.base.tools.ranged;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ProjectileNBT;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by RedstoneParadox on 9/21/2018.
 */
public class ToolChakram extends ProjectileCore {
    @Override
    public ProjectileNBT buildTagData(List<Material> materials) {
        return null;
    }

    @Override
    public float damagePotential() {
        return 0;
    }

    @Override
    public EntityProjectileBase getProjectile(@Nonnull ItemStack stack, @Nonnull ItemStack launcher, World world, EntityPlayer player, float speed, float inaccuracy, float power, boolean usedAmmo) {
        return null;
    }
}
