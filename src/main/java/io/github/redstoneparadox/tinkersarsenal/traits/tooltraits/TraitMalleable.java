package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.tools.traits.TraitWritable;

/**
 * Yes, this is {@link TraitWritable} with a few minor changes
 */
public class TraitMalleable extends AbstractTraitLeveled {
    public TraitMalleable(int levels) {
        super("malleable", String.valueOf(levels), 0xdc7613, 2, 1);
    }

    @Override
    public void applyModifierEffect(NBTTagCompound rootCompound) {
        // yaaay, modifiers
        NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
        int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + levels;
        toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
        TagUtil.setToolTag(rootCompound, toolTag);
    }
}
