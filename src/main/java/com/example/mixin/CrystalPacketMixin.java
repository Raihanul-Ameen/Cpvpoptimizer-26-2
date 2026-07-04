package net.fabricmc.example.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class CrystalPacketMixin {
    @Inject(method = "attack", at = @At("HEAD"))
    private void zeroBreakDelay(Entity target, CallbackInfo ci) {
        if (target instanceof EndCrystal) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.gameMode != null) {
                // Instantly discards the crystal locally so you never wait for server delay to break it
                target.discard(); 
            }
        }
    }
}
