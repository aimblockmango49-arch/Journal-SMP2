
package dev.journalsmp.mixin;

import dev.journalsmp.JournalItems;
import dev.journalsmp.JournalKind;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PlayerExtraSaturationMixin {

    @Inject(method = "eat", at = @At("TAIL"))
    private void journalsmp$grantExtraSaturation(
            Level level,
            ItemStack stack,
            FoodProperties foodProperties,
            CallbackInfo callback
    ) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!level.isClientSide()
                && entity instanceof ServerPlayer serverPlayer
                && JournalItems.kindOf(serverPlayer.getOffhandItem()) == JournalKind.ARBY) {

            float extraSaturation =
                    foodProperties.nutrition() * foodProperties.saturation();

            var foodData = serverPlayer.getFoodData();

            foodData.setSaturation(
                    Math.min(
                            foodData.getFoodLevel(),
                            foodData.getSaturationLevel() + extraSaturation
                    )
            );
        }
    }
}
