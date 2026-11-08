package dev.hitbox.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.hitbox.HitboxConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.ToDoubleFunction;

// class_12392 = AttackRange (data component của vũ khí, 1.21.11)
@Mixin(targets = "net.minecraft.class_12392", remap = false)
public class AttackRangeMixin {
    @ModifyExpressionValue(
        method = "method_76737(Lnet/minecraft/class_1309;Ljava/util/function/ToDoubleFunction;D)Z",
        at = @At(value = "FIELD", target = "Lnet/minecraft/class_12392;comp_5262:F", opcode = 180),
        remap = false
    )
    private float hitbox$margin(float original, LivingEntity entity,
                                ToDoubleFunction<Vec3d> squaredDistanceFunction, double extraHitboxMargin) {
        return original + (float) HitboxConfig.getEntityValue(entity);
    }
}
