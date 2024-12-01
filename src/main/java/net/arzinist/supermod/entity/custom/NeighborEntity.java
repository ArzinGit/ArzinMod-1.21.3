package net.arzinist.supermod.entity.custom;

import net.arzinist.supermod.ArzinMod;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

import static java.lang.Math.*;

public class NeighborEntity extends PathAwareEntity implements GeoEntity {

    private static final TrackedData<Boolean> ANGRY = DataTracker.registerData(NeighborEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> JUMPSCARING = DataTracker.registerData(NeighborEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public boolean shouldMove = true;
    public boolean jumpscaring = false;
    public boolean caughtsoundplayable = true;
    public int freezeTimer = 24;
    public double tpX = 0;
    public double tpZ = 0;
    public int delete_age = 0;

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ANGRY, false);
        builder.add(JUMPSCARING, jumpscaring);
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
        caughtsoundplayable = true;
        shouldMove = false;
    }

    @Override
    public void tickMovement() {
        if (this.shouldMove) {
            super.tickMovement();
        }
        else {
            this.stopMovement();
        }
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (JUMPSCARING.equals(data) && !jumpscaring && caughtsoundplayable) {
            this.getWorld().playSound(this.getX(), this.getEyeY(), this.getZ(), ArzinMod.CAUGHT_EVENT, this.getSoundCategory(), 2.5F, 1.0F, false);
            this.caughtsoundplayable = false;
        }
    }

    @Override
    public void stopMovement() {
        super.stopMovement();
        this.getLookControl().tick();
    }

    @Override
    public void tick() {
        if (!(this.getTarget() == null) && !this.shouldMove) {
            if (this.freezeTimer <= 0) {
                this.freezeTimer = 24;
                this.setInvulnerable(false);
                this.shouldMove = true;
                if (jumpscaring) {
                    jumpscaring = false;
                    this.dataTracker.set(JUMPSCARING, jumpscaring);
                    EntityAttributes.ATTACK_DAMAGE.value().equals(100000);
                    this.getTarget().damage(getServer().getWorld(this.getWorld().getRegistryKey()), getDamageSources().mobAttack(this), 100000);
                    this.delete_age = this.age + 10;
                }
                else {
                    jumpscaring = false;
                    this.dataTracker.set(JUMPSCARING, jumpscaring);
                    this.getTarget().setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24, 255, false, false, false), null);
                    EntityAttributes.ATTACK_DAMAGE.value().equals(0.1);
                }
            }
            if (this.freezeTimer >= 0) {
                this.freezeTimer = this.freezeTimer - 1;
                jumpscaring = true;
                this.dataTracker.set(JUMPSCARING, jumpscaring);
                this.setHeadYaw(getHeadYaw());
                this.setBodyYaw(getBodyYaw());
                this.tpX = this.getLastRenderPos().getX()+cos(toRadians(this.getHeadYaw()+90))*2;
                this.tpZ = this.getLastRenderPos().getZ()+sin(toRadians(this.getHeadYaw()+90))*2;
                this.getTarget().teleport(getServer().getWorld(this.getWorld().getRegistryKey()), this.tpX, this.getLastRenderPos().getY()+1, this.tpZ, PositionFlag.ROT, this.getHeadYaw()-this.getTarget().getHeadYaw()+180, 0, false);
            }
        }
        if (this.age == this.delete_age) {
            this.setRemoved(RemovalReason.DISCARDED);
            this.remove(getRemovalReason());
        }

        super.tick();
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (target == null) {
            this.dataTracker.set(ANGRY, false);
        } else {
            this.dataTracker.set(ANGRY, true);
            }
        }
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("Walk");
    protected static final RawAnimation RUN_ANIM = RawAnimation.begin().thenLoop("Run");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("Idle");
    protected static final RawAnimation JUMPSCARE_ANIM = RawAnimation.begin().thenPlay("Jumpscare");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public NeighborEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0F));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 255.0F));
        this.goalSelector.add(0, new SwimGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 2.15, false));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "walk", 0, this::walkAnimController));
        controllerRegistrar.add(new AnimationController<>(this, "idle", 0, this::idleAnimController));
        controllerRegistrar.add(NeighborEntity.genericAttackAnimation(this, JUMPSCARE_ANIM));
    }

    public  <E extends NeighborEntity>PlayState walkAnimController(final AnimationState<E> event) {
            return event.setAndContinue((this.getDataTracker().get(ANGRY)) ? RUN_ANIM : WALK_ANIM);
        }

    public  <E extends NeighborEntity>PlayState idleAnimController(final AnimationState<E> event) {
        return event.setAndContinue(IDLE_ANIM);
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> genericAttackAnimation(T animatable, RawAnimation attackAnimation) {
        return new AnimationController<>(animatable, "jumpscare", 0, state -> {
            if (animatable.handSwinging)
                return state.setAndContinue(attackAnimation);

            state.getController().forceAnimationReset();

            return PlayState.STOP;
        });
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 255.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23F)
                .add(EntityAttributes.ATTACK_DAMAGE, 0.1F)
                .add(EntityAttributes.ARMOR, 2.0F)
                .add(EntityAttributes.STEP_HEIGHT, 1)
                .add(EntityAttributes.ATTACK_SPEED, 10)
                .add(EntityAttributes.MAX_HEALTH, 80.0F);
    }

}
