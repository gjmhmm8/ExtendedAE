package com.glodblock.github.ae2netanalyser.client.render;

import com.glodblock.github.ae2netanalyser.common.items.ItemNetworkAnalyser;
import com.glodblock.github.ae2netanalyser.common.me.AnalyserMode;
import com.glodblock.github.ae2netanalyser.common.me.NetworkData;
import com.glodblock.github.glodium.client.render.ColorData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class NetworkDataHandler {

    private static NetworkData DATA = null;
    private static ItemNetworkAnalyser.AnalyserConfig CONFIG = new ItemNetworkAnalyser.AnalyserConfig(
            AnalyserMode.FULL,
            0.4f,
            ItemNetworkAnalyser.defaultColors
    );
    private static final ColorData WHITE = new ColorData(1f, 1f, 1f);

    public static void receiveData(NetworkData data) {
        if (data != null && data.isCorrupt()) {
            DATA = null;
        } else {
            DATA = data;
        }
    }

    @Nullable
    public static NetworkData pullData() {
        return DATA;
    }

    public static ColorData getColorByConfig(Enum<?> mode) {
        return CONFIG.colors().getOrDefault(mode, WHITE);
    }

    public static float getNodeSize() {
        return CONFIG.nodeSize();
    }

    public static AnalyserMode getMode() {
        return CONFIG.mode();
    }

    public static void updateConfig(ItemNetworkAnalyser.AnalyserConfig config) {
        CONFIG = config;
    }

}
