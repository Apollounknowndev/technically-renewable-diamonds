package dev.worldgen.renewable.diamonds.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(
        method = "hasRandomTicks",
        at = @At("HEAD"),
        cancellable = true
    )
    private void renewableDiamonds$enableCoalBlockTicking(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(Blocks.COAL_ORE)) {
            cir.setReturnValue(true);
        }
    }
}
