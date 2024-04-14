package dev.worldgen.renewable.diamonds.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Collectors;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
	@Inject(at = @At("HEAD"), method = "randomTick")
	private void init(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (state.isOf(Blocks.BONE_BLOCK) && random.nextInt(1000000) == 10) {
			Box box = Box.enclosing(pos.add(-1, -1, -1), pos.add(1, 1 ,1));
			if (world.getStatesInBox(box)
				.filter(blockState -> !state.isOf(Blocks.STONE))
				.collect(Collectors.toList())
				.isEmpty()
			) {

				world.setBlockState(pos, Blocks.COAL_ORE.getDefaultState());
			}
		} else if (state.isOf(Blocks.COAL_ORE) && random.nextInt(3822222) == 9) {
			world.setBlockState(pos, Blocks.DIAMOND_ORE.getDefaultState());
		}
	}
}