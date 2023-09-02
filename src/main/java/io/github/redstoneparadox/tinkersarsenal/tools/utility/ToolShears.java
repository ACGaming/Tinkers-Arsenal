package io.github.redstoneparadox.tinkersarsenal.tools.utility;

import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

/**
 * Created by RedstoneParadox on 10/8/2018.
 */
public class ToolShears extends TinkerToolCore {
    public ToolShears() {
        super(PartMaterialType.handle(TinkerTools.binding), PartMaterialType.head(TinkerTools.knifeBlade));
        this.setRegistryName("toolshears").setTranslationKey("toolshears");

        addCategory(Category.NO_MELEE);
        setHarvestLevel("shears", 0);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.getCreativeTab() == tab) {
            addDefaultSubItems(subItems, null, null);
        }
    }

    @Override
    public float damagePotential() {
        return 0;
    }

    @Override
    public double attackSpeed() {
        return 0;
    }

    @Override
    public ToolNBT buildTagData(List<Material> materials) {
        ToolNBT data = new ToolNBT();

        data.handle(materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
        data.head(materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD));

        return data;
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        // only run AOE on shearable entities
        if (target instanceof IShearable) {
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            if (shearEntity(stack, player.getEntityWorld(), player, target, fortune)) {
                swingTool(player, hand);
                return true;
            }
        }
        return false;
    }

    public boolean shearEntity(ItemStack stack, World world, EntityPlayer player, Entity entity, int fortune) {
        if(!(entity instanceof IShearable)) {
            return false;
        }

        IShearable shearable = (IShearable) entity;
        if(shearable.isShearable(stack, world, entity.getPosition())) {
            if(!world.isRemote) {
                List<ItemStack> drops = shearable.onSheared(stack, world, entity.getPosition(), fortune);
                Random rand = world.rand;
                for(ItemStack drop : drops) {
                    EntityItem entityItem = entity.entityDropItem(drop, 1.0F);
                    if(entityItem != null) {
                        entityItem.motionY += rand.nextFloat() * 0.05F;
                        entityItem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                        entityItem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    }
                }
            }
            ToolHelper.damageTool(stack, 1, player);

            return true;
        }

        return false;
    }

    protected void swingTool(EntityPlayer player, EnumHand hand) {
        player.swingArm(hand);
        player.spawnSweepParticles();
    }
}
