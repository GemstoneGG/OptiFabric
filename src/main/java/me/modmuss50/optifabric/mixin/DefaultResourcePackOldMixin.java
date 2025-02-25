package me.modmuss50.optifabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import me.modmuss50.optifabric.mod.OptifineResources;

@Mixin(value = DefaultResourcePack.class, priority = 400)
abstract class DefaultResourcePackOldMixin {
	@Shadow
	private static native String getPath(ResourceType type, Identifier id);

	@Inject(method = "contains", at = @At("HEAD"), cancellable = true)
	public void doesContain(ResourceType type, Identifier id, CallbackInfoReturnable<Boolean> callback) {
		String path = getPath(type, id);

		if (OptifineResources.INSTANCE.hasResource(path)) callback.setReturnValue(true);
	}
}