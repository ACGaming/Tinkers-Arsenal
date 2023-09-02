package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

public class TraitSwiftFlight extends AbstractProjectileTrait {
    public TraitSwiftFlight() {
        super("swift_flight", 0xffff99);
    }

    @Override
    public void onLaunch(EntityProjectileBase projectileBase, World world, @Nullable EntityLivingBase shooter) {
        projectileBase.motionX *= 2d;
        projectileBase.motionY *= 2d;
        projectileBase.motionZ *= 2d;
    }
}
