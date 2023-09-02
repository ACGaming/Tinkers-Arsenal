package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitThermallyActive extends AbstractTraitLeveled {
    public TraitThermallyActive(int levels) {
        super("thermally_active", 0xff8080, 3, levels);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if (wasHit) {
            ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(tool, name));

            target.attackEntityFrom(DamageSource.HOT_FLOOR, damageDealt * 0.1f * data.level);
        }
    }
}
