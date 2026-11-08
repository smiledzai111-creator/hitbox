package dev.hitbox;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class HitboxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HitboxConfig.load();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(ClientCommandManager.literal("hitbox")
                .then(ClientCommandManager.literal("toggle").executes(ctx -> {
                    HitboxConfig.enabled = !HitboxConfig.enabled;
                    HitboxConfig.save();
                    ctx.getSource().sendFeedback(Text.literal("Hitbox: " + (HitboxConfig.enabled ? "ON" : "OFF")));
                    return 1;
                }))
                .then(ClientCommandManager.literal("expand")
                    .then(ClientCommandManager.argument("value", DoubleArgumentType.doubleArg(0, 5)).executes(ctx -> {
                        HitboxConfig.expand = DoubleArgumentType.getDouble(ctx, "value");
                        HitboxConfig.save();
                        ctx.getSource().sendFeedback(Text.literal("Hitbox expand = " + HitboxConfig.expand));
                        return 1;
                    })))
                .then(ClientCommandManager.literal("players").executes(ctx -> {
                    HitboxConfig.playersOnly = !HitboxConfig.playersOnly;
                    HitboxConfig.save();
                    ctx.getSource().sendFeedback(Text.literal("Players only: " + HitboxConfig.playersOnly));
                    return 1;
                }))
            ));
    }
}
