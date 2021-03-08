package io.github.frqnny.tacocraft;

import io.github.frqnny.tacocraft.client.gui.FurnaceGUI;
import io.github.frqnny.tacocraft.client.gui.FurnaceScreen;
import io.github.frqnny.tacocraft.client.render.ComalBlockEntityRenderer;
import io.github.frqnny.tacocraft.init.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

@SuppressWarnings("unused")
public class TacoCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COMAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_BLOCK, RenderLayer.getCutout());
        ScreenRegistry.Factory<FurnaceGUI, FurnaceScreen> screenFactory = (gui, inventory, title) -> new FurnaceScreen(gui, inventory.player, title);
        ScreenRegistry.register(TacoCraft.Furnace, screenFactory);

        BlockEntityRendererRegistry.INSTANCE.register(ModBlocks.COMAL_BLOCK_ENTITY, ComalBlockEntityRenderer::new);
    }
}