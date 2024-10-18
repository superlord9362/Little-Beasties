package superlord.little_beasties.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.CommonProxy;
import superlord.little_beasties.init.LBBlocks;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void init() {
	}

	@SuppressWarnings("deprecation")
	public static void setupBlockRenders() {
		RenderType cutoutRenderType = RenderType.cutout();
		ItemBlockRenderTypes.setRenderLayer(LBBlocks.TEARTANG.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(LBBlocks.COINFROG_SPAWN.get(), cutoutRenderType);
	}

}
