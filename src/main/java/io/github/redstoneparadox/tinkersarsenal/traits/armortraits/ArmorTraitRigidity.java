package io.github.redstoneparadox.tinkersarsenal.traits.armortraits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import java.util.Objects;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by RedstoneParadox on 8/16/2018.
 */
public class ArmorTraitRigidity extends AbstractArmorTrait {
    public ArmorTraitRigidity() {
        super("rigidity", 0x33ebcb);
    }

    @Override
    public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
        int armorAmount = Objects.requireNonNull(armor.getTagCompound()).getCompoundTag("Stats").getInteger("defense");

        if (armorAmount > 10) {
            Random rigidChance = new Random();

            if ((rigidChance.nextInt(20) + 1) == 1) {
                newDamage = damage * 0.5f;
                return newDamage;
            }
        } else {
            return newDamage;
        }
        return newDamage;
    }
}
