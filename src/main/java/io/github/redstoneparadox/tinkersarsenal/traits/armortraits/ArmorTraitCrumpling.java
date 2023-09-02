package io.github.redstoneparadox.tinkersarsenal.traits.armortraits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ArmorTraitCrumpling extends AbstractArmorTrait {
    public ArmorTraitCrumpling() {
        super("crumpling", 0xD9C3BA);
    }

    @Override
    public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
        Random random = new Random();

        if (random.nextInt(10) + 1 == 1) {
            armor.damageItem(random.nextInt(3) + 1, player);
            return newDamage * 0.75f;
        }

        return super.onHurt(armor, player, source, damage, newDamage, evt);
    }
}
