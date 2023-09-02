package io.github.redstoneparadox.tinkersarsenal.tools.ranged;

import com.google.common.collect.ImmutableList;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalSounds;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.client.BooleanItemPropertyGetter;
import slimeknights.tconstruct.library.client.crosshair.Crosshairs;
import slimeknights.tconstruct.library.client.crosshair.ICrosshair;
import slimeknights.tconstruct.library.client.crosshair.ICustomCrosshairUser;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ranged.BowCore;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 * Created by RedstoneParadox on 7/31/2018.
 */
public class ToolBoomstick extends BowCore implements ICustomCrosshairUser{
    private static final String TAG_Loaded = "Loaded";
    protected static final ResourceLocation PROPERTY_IS_LOADED = new ResourceLocation("loaded");

    public ToolBoomstick() {
        super(PartMaterialType.handle(ArsenalTools.boomstickHandle),
                PartMaterialType.bow(ArsenalTools.boomstickBarrel),
                PartMaterialType.head(ArsenalTools.bayonet));

        this.setRegistryName("boomstick").setTranslationKey("boomstick");

        this.addPropertyOverride(PROPERTY_PULL_PROGRESS, pullProgressPropertyGetter);
        this.addPropertyOverride(PROPERTY_IS_PULLING, isPullingPropertyGetter);
        this.addPropertyOverride(PROPERTY_IS_LOADED, new BooleanItemPropertyGetter() {
            @Override
            public boolean applyIf(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && isLoaded(stack);
            }
        });
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.getCreativeTab() == tab) {
            addDefaultSubItems(subItems, null, null, null);
        }
    }

    @Override
    public float damagePotential() {
        return 0.8f;
    }

    @Override
    public double attackSpeed() {
        return 2;
    }

    @Override
    public float baseProjectileDamage() {
        return 5f;
    }

    @Override
    protected float baseProjectileSpeed() {
        return 9f;
    }

    @Override
    public float projectileDamageModifier() {
        return 1.5f;
    }

    @Override
    public int getDrawTime() {
        return 45;
    }

    public boolean isLoaded(ItemStack stack) {
        return TagUtil.getTagSafe(stack).getBoolean(TAG_Loaded);
    }

    public void setLoaded(ItemStack stack, boolean isLoaded) {
        NBTTagCompound tag = TagUtil.getTagSafe(stack);
        tag.setBoolean(TAG_Loaded, isLoaded);
        stack.setTagCompound(tag);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        // has to be done in onUpdate because onTickUsing is too early and gets overwritten. bleh.
        preventSlowDown(entityIn, 0.195f);

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if(isLoaded(itemStackIn) && !ToolHelper.isBroken(itemStackIn)) {
            super.onPlayerStoppedUsing(itemStackIn, worldIn, playerIn, 0);
            setLoaded(itemStackIn, false);
        }
        else {
            return super.onItemRightClick(worldIn, playerIn, hand);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if(!ToolHelper.isBroken(stack) && (entityLiving instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            int useTime = this.getMaxItemUseDuration(stack) - timeLeft;
            if(getDrawbackProgress(stack, useTime) >= 1f) {
                if (player.capabilities.isCreativeMode) {
                    Sounds.PlaySoundForPlayer(entityLiving, Sounds.crossbow_reload, 1.5f, 0.9f + itemRand.nextFloat() * 0.1f);
                    setLoaded(stack, true);
                }
                else {
                    ItemStack itemStack = findGunpowder(player);
                    if (itemStack != null) {
                        itemStack.shrink(1);
                        if (itemStack.isEmpty()) {
                            player.inventory.deleteStack(itemStack);
                        }
                        Sounds.PlaySoundForPlayer(entityLiving, Sounds.crossbow_reload, 1.5f, 0.9f + itemRand.nextFloat() * 0.1f);
                        setLoaded(stack, true);
                    }
                }
            }
        }
    }

    public ItemStack findGunpowder(EntityPlayer player) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack itemStack = player.inventory.getStackInSlot(i);

            if (itemStack.getItem() == Items.GUNPOWDER)
            {
                return itemStack;
            }
        }
        return null;
    }

    @Override
    public void playShootSound(float power, World world, EntityPlayer entityPlayer) {
        world.playSound(null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, ArsenalSounds.BOOMSTICK_SHOT, SoundCategory.NEUTRAL, 1.0F, 0.5f + itemRand.nextFloat() * 0.1f);
    }

    @Override
    public ItemStack getAmmoToRender(ItemStack weapon, EntityLivingBase player) {
        if(!isLoaded(weapon)) {
            return ItemStack.EMPTY;
        }
        return super.getAmmoToRender(weapon, player);
    }

    private ImmutableList<Item> boltMatches = null;

    @Override
    protected List<Item> getAmmoItems() {
        if(boltMatches == null) {
            ImmutableList.Builder<Item> builder = ImmutableList.builder();
            if(ArsenalTools.boomstickShot != null) {
                builder.add(ArsenalTools.boomstickShot);
            }
            boltMatches = builder.build();
        }
        return boltMatches;
    }

    @Override
    public ProjectileLauncherNBT buildTagData(List<Material> materials) {
        ProjectileLauncherNBT data = new ProjectileLauncherNBT();
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        BowMaterialStats barrel = materials.get(1).getStatsOrUnknown(MaterialTypes.BOW);
        HeadMaterialStats bayonet = materials.get(2).getStatsOrUnknown(MaterialTypes.HEAD);

        data.handle(handle);
        data.limb(barrel);
        data.head(bayonet);

        data.bonusDamage *= 1.5f;

        return data;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICrosshair getCrosshair(ItemStack itemStack, EntityPlayer player) {
        return Crosshairs.T;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCrosshairState(ItemStack itemStack, EntityPlayer player) {
        if(isLoaded(itemStack)) {
            return 1f;
        }
        else if(player.getActiveItemStack() != itemStack) {
            return 0f;
        }
        return getDrawbackProgress(itemStack, player);
    }
}

/*
super(PartMaterialType.handle(TATools.boomstickHandle),
                PartMaterialType.bow(TATools.boomstickBarrel),
                PartMaterialType.head(TATools.bayonet));

        this.addCategory(Category.WEAPON);
        this.setUnlocalizedName("boomstick").setRegistryName("boomstick");
*/
