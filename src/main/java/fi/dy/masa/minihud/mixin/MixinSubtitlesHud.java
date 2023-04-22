package fi.dy.masa.minihud.mixin;

import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.hud.SubtitlesHud;
import net.minecraft.client.util.math.MatrixStack;
import fi.dy.masa.minihud.event.RenderHandler;

@Mixin(SubtitlesHud.class)
public abstract class MixinSubtitlesHud
{
    @Inject(method = "render",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
    private void nudgeSubtitleOverlay(DrawContext drawContext, CallbackInfo ci)
    {
        int offset = RenderHandler.getInstance().getSubtitleOffset();

        if (offset != 0)
        {
            drawContext.getMatrices().translate(0, offset, 0);
        }
    }
}
