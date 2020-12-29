package io.github.redstoneparadox.tinkersarsenal.traits.armortraits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

public class ArmorTraitEnduring extends AbstractArmorTrait {
	public ArmorTraitEnduring() {
		super("enduring", 0x7E8D88);
	}

	@Override
	public void apply(NBTTagCompound root) {
		super.apply(root);

		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("useCount", 0);
		nbt.setInteger("countdown", 100);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		assert tool.getTagCompound() != null;
		NBTTagCompound nbt = tool.getTagCompound().getCompoundTag("enduringData");
		int useCount = nbt.getInteger("useCount");
		int countdown = nbt.getInteger("countdown");

		if (useCount > 0) {
			if (countdown <= 0) {
				nbt.setInteger("useCount", useCount - 1);
				nbt.setInteger("countdown", 100);
			}
			else {
				nbt.setInteger("countdown", countdown - 1);
			}
		}
	}

	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		assert tool.getTagCompound() != null;
		NBTTagCompound nbt = tool.getTagCompound().getCompoundTag("enduringData");
		int useCount = nbt.getInteger("useCount");

		if (useCount < 50) {
			nbt.setInteger("useCount", useCount + 1);
		}

		if (useCount <= 0) {
			return newDamage;
		}

		float x = useCount/50.0f;
		float y;
		if (x >= 0.0f && x < 0.5f) {
			y = (float) (3.05 * Math.pow(x, 3));
		}
		else if (x >= 0.5f && x <= 1.0f) {
			y = (float) (5.f * Math.pow(x - 0.9f, 3) + 0.7f);
		}
		else {
			throw new IllegalStateException("Expected x value between 0 and 1, instead got " + x + ".");
		}
		float chance = Math.min(0.7f, y);
		Random random = new Random();

		if (random.nextFloat() <= chance) {
			return 0;
		}

		return newDamage;
	}
}
