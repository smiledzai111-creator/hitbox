package dev.hitbox;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class HitboxConfig {
    public static boolean enabled = true;
    public static double expand = 0.5;
    public static boolean playersOnly = true;

    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("hitbox.properties");

    private HitboxConfig() {}

    /** Lượng cần phóng to hitbox của entity này (0 = không đổi). */
    public static double getEntityValue(Entity entity) {
        if (!enabled) return 0;
        if (playersOnly && !(entity instanceof PlayerEntity)) return 0;
        return expand;
    }

    public static void load() {
        if (!Files.exists(FILE)) { save(); return; }
        try (Reader r = Files.newBufferedReader(FILE)) {
            Properties p = new Properties();
            p.load(r);
            enabled = Boolean.parseBoolean(p.getProperty("enabled", "true"));
            expand = Double.parseDouble(p.getProperty("expand", "0.5"));
            playersOnly = Boolean.parseBoolean(p.getProperty("playersOnly", "true"));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        Properties p = new Properties();
        p.setProperty("enabled", String.valueOf(enabled));
        p.setProperty("expand", String.valueOf(expand));
        p.setProperty("playersOnly", String.valueOf(playersOnly));
        try (Writer w = Files.newBufferedWriter(FILE)) {
            p.store(w, "Hitbox mod");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
