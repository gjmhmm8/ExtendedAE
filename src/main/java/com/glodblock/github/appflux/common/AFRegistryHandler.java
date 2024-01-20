package com.glodblock.github.appflux.common;

import appeng.api.behaviors.ContainerItemStrategy;
import appeng.api.behaviors.GenericSlotCapacities;
import appeng.api.client.StorageCellModels;
import appeng.api.stacks.AEKeyTypes;
import appeng.api.storage.StorageCells;
import appeng.items.AEBaseItem;
import appeng.parts.automation.StackWorldBehaviors;
import com.glodblock.github.appflux.AppFlux;
import com.glodblock.github.appflux.common.me.cell.FECellHandler;
import com.glodblock.github.appflux.common.me.cell.GTEUCellHandler;
import com.glodblock.github.appflux.common.me.key.FluxKey;
import com.glodblock.github.appflux.common.me.key.type.FluxKeyType;
import com.glodblock.github.appflux.common.me.strategy.FEContainerItemStrategy;
import com.glodblock.github.appflux.common.me.strategy.FEExternalStorageStrategy;
import com.glodblock.github.glodium.registry.RegistryHandler;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;

public class AFRegistryHandler extends RegistryHandler {

    public static final AFRegistryHandler INSTANCE = new AFRegistryHandler();

    public AFRegistryHandler() {
        super(AppFlux.MODID);
    }

    @SuppressWarnings("UnstableApiUsage")
    public void init() {
        StackWorldBehaviors.registerExternalStorageStrategy(FluxKeyType.TYPE, FEExternalStorageStrategy::new);
        ContainerItemStrategy.register(FluxKeyType.TYPE, FluxKey.class, new FEContainerItemStrategy());
        GenericSlotCapacities.register(FluxKeyType.TYPE, 1000000L);
        StorageCells.addCellHandler(FECellHandler.HANDLER);
        StorageCellModels.registerModel(AFItemAndBlock.FE_CELL_1k, AppFlux.id("block/drive/fe_cell"));
        StorageCellModels.registerModel(AFItemAndBlock.FE_CELL_4k, AppFlux.id("block/drive/fe_cell"));
        StorageCellModels.registerModel(AFItemAndBlock.FE_CELL_16k, AppFlux.id("block/drive/fe_cell"));
        StorageCellModels.registerModel(AFItemAndBlock.FE_CELL_64k, AppFlux.id("block/drive/fe_cell"));
        StorageCellModels.registerModel(AFItemAndBlock.FE_CELL_256k, AppFlux.id("block/drive/fe_cell"));
        if (ModList.get().isLoaded("gtceu")) {
            StorageCells.addCellHandler(GTEUCellHandler.HANDLER);
            StorageCellModels.registerModel(AFItemAndBlock.GTEU_CELL_1k, AppFlux.id("block/drive/gteu_cell"));
            StorageCellModels.registerModel(AFItemAndBlock.GTEU_CELL_4k, AppFlux.id("block/drive/gteu_cell"));
            StorageCellModels.registerModel(AFItemAndBlock.GTEU_CELL_16k, AppFlux.id("block/drive/gteu_cell"));
            StorageCellModels.registerModel(AFItemAndBlock.GTEU_CELL_64k, AppFlux.id("block/drive/gteu_cell"));
            StorageCellModels.registerModel(AFItemAndBlock.GTEU_CELL_256k, AppFlux.id("block/drive/gteu_cell"));
        }
    }

    public void register(RegisterEvent event) {
        super.register(event);
        AEKeyTypes.register(FluxKeyType.TYPE);
    }

    public void registerTab(Registry<CreativeModeTab> registry) {
        var tab = CreativeModeTab.builder()
                .icon(() -> new ItemStack(AFItemAndBlock.FE_CELL_1k))
                .title(Component.translatable("itemGroup.af"))
                .displayItems((__, o) -> {
                    for (Pair<String, Item> entry : items) {
                        if (entry.getRight() instanceof AEBaseItem aeItem) {
                            aeItem.addToMainCreativeTab(o);
                        } else {
                            o.accept(entry.getRight());
                        }
                    }
                    for (Pair<String, Block> entry : blocks) {
                        o.accept(entry.getRight());
                    }
                })
                .build();
        Registry.register(registry, AppFlux.id("tab_main"), tab);
    }

}
