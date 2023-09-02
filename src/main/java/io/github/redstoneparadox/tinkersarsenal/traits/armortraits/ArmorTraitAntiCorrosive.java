package io.github.redstoneparadox.tinkersarsenal.traits.armortraits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import java.util.Objects;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by RedstoneParadox on 8/17/2018.
 */
public class ArmorTraitAntiCorrosive extends AbstractArmorTrait {
    public ArmorTraitAntiCorrosive() {
        super("anti_corrosive", 0xeaee57);
    }

    @Override
    public void onAbilityTick(int level, World world, EntityPlayer player) {
        Random chance = new Random();

        if (!world.isRemote && (chance.nextInt(20) + 1) == 1) {
            if (player.getActivePotionEffect(MobEffects.POISON) != null) {
                reduceCorrosion(MobEffects.POISON, player);
            }
            if (player.getActivePotionEffect(MobEffects.WITHER) != null) {
                reduceCorrosion(MobEffects.WITHER, player);
            }
        }
    }

    public void reduceCorrosion(Potion potion, EntityPlayer player) {
        int currentDuration = Objects.requireNonNull(player.getActivePotionEffect(potion)).getDuration();
        int newDuration = currentDuration - 100;
        int potionLevel = Objects.requireNonNull(player.getActivePotionEffect(potion)).getAmplifier();

        player.removePotionEffect(potion);
        if (newDuration > 0) {
            player.addPotionEffect(new PotionEffect(potion, newDuration, potionLevel));
        }
    }
}
