
package dev.journalsmp.mixin;

import dev.journalsmp.JournalItems;
import dev.journalsmp.JournalKind;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class PlayerExtraSaturationMixin {

    @Inject(method = "eat", at = @At("TAIL"))
    private void journalsmp$grantExtraSaturation(
            FoodProperties foodProperties,
            CallbackInfo callback
    ) {
        // The actual player is checked by the alternative below.
    }
}
