package fuzs.armorstatues.init;

import fuzs.armorstatues.ArmorStatues;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.statuemenus.api.v1.world.entity.decoration.ArmorStandDataProvider;
import fuzs.statuemenus.api.v1.world.inventory.ArmorStandMenu;
import fuzs.statuemenus.api.v1.world.inventory.data.ArmorStandScreenType;
import net.minecraft.core.Holder;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(ArmorStatues.MOD_ID);
    public static final Holder.Reference<MenuType<ArmorStandMenu>> ARMOR_STAND_MENU_TYPE = REGISTRIES.registerExtendedMenuType(
            "armor_stand",
            () -> (containerId, inventory, data) -> {
                return new ArmorStandMenu(ModRegistry.ARMOR_STAND_MENU_TYPE.value(),
                        containerId,
                        inventory,
                        data,
                        ModRegistry.ARMOR_STAND_DATA_PROVIDER);
            });

    public static final ArmorStandScreenType POSITION_SCREEN_TYPE = new ArmorStandScreenType(
            "commandsCompatiblePosition",
            new ItemStack(Items.GRASS_BLOCK));
    public static final ArmorStandScreenType ALIGNMENTS_SCREEN_TYPE = new ArmorStandScreenType("alignments",
            new ItemStack(Items.DIAMOND_PICKAXE));
    public static final ArmorStandScreenType VANILLA_TWEAKS_SCREEN_TYPE = new ArmorStandScreenType("vanillaTweaks",
            new ItemStack(Items.WRITTEN_BOOK));
    public static final ArmorStandDataProvider ARMOR_STAND_DATA_PROVIDER = new ArmorStandDataProvider() {

        @Override
        public ArmorStandScreenType[] getScreenTypes() {
            return new ArmorStandScreenType[]{
                    ArmorStandScreenType.ROTATIONS,
                    ArmorStandScreenType.POSES,
                    ArmorStandScreenType.STYLE,
                    POSITION_SCREEN_TYPE,
                    ALIGNMENTS_SCREEN_TYPE,
                    ArmorStandScreenType.EQUIPMENT
            };
        }
    };

    public static void bootstrap() {
        // NO-OP
    }
}
