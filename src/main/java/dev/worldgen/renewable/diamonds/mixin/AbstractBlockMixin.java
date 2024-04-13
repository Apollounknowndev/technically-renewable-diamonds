package dev.worldgen.renewable.diamonds.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
	@Inject(at = @At("HEAD"), method = "randomTick")
	private void init(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (state.isOf(Blocks.COAL_ORE) && random.nextInt(3822222) == 9) {
			world.setBlockState(pos, Blocks.DIAMOND_ORE.getDefaultState());
		}
	}
}