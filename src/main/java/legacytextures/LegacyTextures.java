package legacytextures;

import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.ItemRegistry;
import necesse.engine.registries.TileRegistry;
import necesse.inventory.item.Item;
import necesse.inventory.item.armorItem.ArmorItem;
import necesse.inventory.item.armorItem.HelmetArmorItem;
import necesse.level.gameTile.GameTile;
import necesse.level.gameTile.LiquidTile;
import necesse.level.gameTile.TerrainSplatterTile;

@ModEntry
public class LegacyTextures {

    public void init() {
        System.out.println("Hello from LegacyTextures mod!");
        // Use legacy splatting
        for (GameTile tile : TileRegistry.getTiles()) {
            if (tile instanceof TerrainSplatterTile) {
                ((TerrainSplatterTile) tile).preferLegacySplatting = true;
            } else if (tile instanceof LiquidTile) {
                ((LiquidTile) tile).preferLegacySplatting = true;
            }
        }
    }

    public void initResources() {
    }

    public void postInit() {
        // Clear hair mask textures
        for (Item item : ItemRegistry.getItems()) {
            // Only non modded items
            if (ItemRegistry.getItemMod(item.getID()) == null) {
                if (item instanceof HelmetArmorItem) {
                    HelmetArmorItem helmetItem = (HelmetArmorItem) item;
                    helmetItem.hairMaskTexture = null;
                    helmetItem.hairDrawOptions = ArmorItem.HairDrawMode.NO_HAIR;
                    helmetItem.facialFeatureDrawOptions = ArmorItem.FacialFeatureDrawMode.OVER_FACIAL_FEATURE;
                }
            }
        }
        // Fix hair draw options
        setHelmetHairOptions("voidmask", ArmorItem.HairDrawMode.UNDER_HAIR, null);
        setHelmetHairOptions("bloodplatecowl", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("ivycirclet", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("glacialcirclet", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("myceliumscarf", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("ancientfossilmask", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("nightsteelveil", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("spideritecrown", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("sunglasses", ArmorItem.HairDrawMode.OVER_HAIR, null);
        setHelmetHairOptions("empressmask", ArmorItem.HairDrawMode.OVER_HAIR, null);
    }

    private void setHelmetHairOptions(String itemStringID, ArmorItem.HairDrawMode hairMode, ArmorItem.FacialFeatureDrawMode facialMode) {
        HelmetArmorItem helmetArmorItem = (HelmetArmorItem) ItemRegistry.getItem(itemStringID);
        helmetArmorItem.hairDrawOptions = hairMode;
        helmetArmorItem.facialFeatureDrawOptions = facialMode;
    }

}
