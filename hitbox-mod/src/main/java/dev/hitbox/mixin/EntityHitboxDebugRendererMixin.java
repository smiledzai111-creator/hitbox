package dev.hitbox.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.hitbox.HitboxConfig;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// class_12155 = EntityHitboxDebugRenderer (F3+B) - chỉ để hiển thị hitbox đã phóng to
@Mixin(targets = "net.minecraft.class_12155", remap = false)
public class EntityHitboxDebugRendererMixin {
    @ModifyExpressionValue(
        method = "method_75432",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5829()Lnet/minecraft/class_238;", ordinal = 0),
        remap = false
    )
    private Box hitbox$debugBox(Box original, Entity entity, float tickProgress, boolean inLocalServer) {
        double v = HitboxConfig.getEntityValue(entity);
        return v == 0 ? original : original.expand(v);
    }
}
