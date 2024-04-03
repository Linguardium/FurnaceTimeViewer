package mod.linguardium.furnacetimeviewer.mixin;

import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static mod.linguardium.furnacetimeviewer.FurnaceTimeViewer.renderFurnaceText;

@Mixin(AbstractFurnaceScreen.class)
public abstract class FurnaceScreenMixin extends HandledScreen {
	public FurnaceScreenMixin(ScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Inject(at = @At("RETURN"), method = "drawBackground")
	private void renderTimer(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
		if (this.handler instanceof AbstractFurnaceScreenHandler abstractFurnaceScreenHandler && this.textRenderer != null) {

			renderFurnaceText(this.textRenderer,matrices,abstractFurnaceScreenHandler, this, this.x , this.y);
		}
	}
}