package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

public class TraitHardHitting extends AbstractProjectileTrait {
    public TraitHardHitting() {
        super("hard_hitting", 0x554B45);
    }

    @Override
    public float knockBack(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float knockback, float newKnockback, boolean isCritical) {
        return newKnockback * 2;
    }
}
