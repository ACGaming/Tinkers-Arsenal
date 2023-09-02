package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

public class TraitGrounding extends AbstractProjectileTrait {
    public TraitGrounding() {
        super("grounding", 0x554B45);
    }

    @Override
    public void afterHit(EntityProjectileBase projectile, World world, ItemStack ammoStack, EntityLivingBase attacker, Entity target, double impactSpeed) {
        super.afterHit(projectile, world, ammoStack, attacker, target, impactSpeed);

        if (target.isEntityAlive() && target instanceof EntityLiving) {
            ((EntityLiving) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 101));
        }
    }
}
