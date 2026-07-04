package net.fabricmc.example.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class CrystalPlaceMixin {
    @Shadow private int rightClickDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    private void zeroPlaceDelay(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && mc.player.isHolding(net.minecraft.world.item.Items.END_CRYSTAL)) {
            // Forces your right click action delay to absolute 0 every single game tick
            this.rightClickDelay = 0; 
        }
    }
}
