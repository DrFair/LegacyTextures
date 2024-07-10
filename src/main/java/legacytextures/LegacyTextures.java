package legacytextures;

import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.TileRegistry;
import necesse.level.gameTile.GameTile;
import necesse.level.gameTile.LiquidTile;
import necesse.level.gameTile.TerrainSplatterTile;

@ModEntry
public class LegacyTextures {

    public void init() {
        System.out.println("Hello from LegacyTextures mod!");
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
    }

}
