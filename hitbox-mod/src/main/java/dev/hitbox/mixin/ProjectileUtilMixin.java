package dev.hitbox.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.hitbox.HitboxConfig;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// class_1675 = ProjectileUtil
@Mixin(targets = "net.minecraft.class_1675", remap = false)
public class ProjectileUtilMixin {
    @ModifyExpressionValue(
        method = "method_75215(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;Lnet/minecraft/class_243;Lnet/minecraft/class_243;Lnet/minecraft/class_238;Ljava/util/function/Predicate;FLnet/minecraft/class_3959$class_3960;Z)Ljava/util/Collection;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5829()Lnet/minecraft/class_238;"),
        remap = false
    )
    private static Box hitbox$expand(Box original, @Local(ordinal = 1) Entity entity2) {
        double v = HitboxConfig.getEntityValue(entity2);
        return v == 0 ? original : original.expand(v);
    }
}
