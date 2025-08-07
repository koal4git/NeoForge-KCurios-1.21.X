package net.koala.kcurios.entity.custom;

import net.koala.kcurios.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BulletEntity extends ThrowableItemProjectile {

    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(
            BulletEntity.class, EntityDataSerializers.ITEM_STACK
    );

    public BulletEntity(EntityType<? extends BulletEntity> entityType, Level level) {
        super(entityType, level);
    }

    public BulletEntity(EntityType<? extends BulletEntity> entityType, double x, double y, double z, Level level) {
        super(entityType, x, y, z, level);
    }

    public BulletEntity(EntityType<? extends BulletEntity> entityType, LivingEntity shooter, Level level) {
        super(entityType, shooter, level);
    }

    public void setItem(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    @Override
    public ItemStack getItem() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BULLET.get();
    }


    public void onHitBlock(LivingEntity entity) {

        discard();
    }

}
