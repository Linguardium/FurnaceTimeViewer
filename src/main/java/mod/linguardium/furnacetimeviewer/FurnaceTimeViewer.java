package mod.linguardium.furnacetimeviewer;

import mod.linguardium.furnacetimeviewer.mixin.AbstractFurnaceScreenHandlerAccessor;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FurnaceTimeViewer implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("furnace-time-viewer");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
	public static void renderFurnaceText(TextRenderer textRenderer, MatrixStack matrices, AbstractFurnaceScreenHandler abstractFurnaceScreenHandler, HandledScreen screen, int centerX, int centerY) {
		PropertyDelegate propertyDelegate = ((AbstractFurnaceScreenHandlerAccessor) abstractFurnaceScreenHandler).viewer$propertyDelegate();
		float fuelTimeStart = propertyDelegate.get(1);
		float fuelTimeCurrent = propertyDelegate.get(0);
		float cookTimeRequired = propertyDelegate.get(3);
		float cookTimeProgressed = propertyDelegate.get(2);
		int cookTimePercent = cookTimeRequired < 1 ? 0 : (int)(100.0f*cookTimeProgressed/cookTimeRequired);
		int fuelTimeRemaining = fuelTimeStart < 1 ? 0 : (int)(100.0f*fuelTimeCurrent/fuelTimeStart);
		Text cookPercent = Text.translatable("attribute.modifier.equals.1", String.valueOf(cookTimePercent),"");
		Text fuelPercent = Text.translatable("attribute.modifier.equals.1", String.valueOf(fuelTimeRemaining ),"");
		textRenderer.draw(matrices,cookPercent,centerX+56+19,centerY+17+textRenderer.fontHeight/2.0f,0xFF000000);
		textRenderer.draw(matrices,fuelPercent,centerX+56+19,centerY+53+textRenderer.fontHeight/2.0f,0xFF000000);
	}
}