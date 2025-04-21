package fuzs.armorstatues.fabric.mixin;

import fuzs.armorstatues.ArmorStatues;
import fuzs.armorstatues.init.ModRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.impl.registry.sync.packet.DirectRegistryPacketHandler;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Consumer;

@Mixin(DirectRegistryPacketHandler.class)
public abstract class DontSyncOurRegistryMixin {

    @Shadow public abstract void receivePayload(DirectRegistryPacketHandler.Payload payload);

    @Inject(method = "sendPacket", at = @At("HEAD"), remap = false)
    public void sendPacketMixin(Consumer<DirectRegistryPacketHandler.Payload> sender, Map<ResourceLocation, Object2IntMap<ResourceLocation>> registryMap, CallbackInfo ci){

        boolean hasMenu = false;
        ResourceLocation menuLocation = null;
        for(ResourceLocation l : registryMap.keySet()){
            if(!l.getPath().equals("menu")){ continue; }
            hasMenu = true;
            menuLocation = l;
            break;
        }

        if(!hasMenu){
            return;
        }

        Object2IntMap<ResourceLocation> menuEntry = registryMap.get(menuLocation);

        menuEntry.remove(ResourceLocation.fromNamespaceAndPath(ArmorStatues.MOD_ID, "armor_stand"));


    }

}
