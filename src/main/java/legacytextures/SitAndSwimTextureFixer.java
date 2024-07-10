package legacytextures;

import necesse.engine.GameLog;
import necesse.engine.GlobalData;
import necesse.gfx.gameTexture.GameTexture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;

public class SitAndSwimTextureFixer {

    // This script is made to fix and extend all armor and skin files to have sit column
    // The idle column is simply copied over to the swim column as well as the sit column
    // No actual sit texture is made

    public static void main(String[] args) {
        GameLog.startLogging();
        GlobalData.setup(args);
        String[] folders = new String[] {
                "player/skin",
                "player/eyes",
                "player/hair",
                "player/holditems",
                "player/armor"
        };
        String[] files = new String[] {
                // Nothing yet
        };
        String[] excludeFiles = new String[] {
                "eyecolors",
                "skincolors",
                "wigs",
                "haircolors"
        };
        HashSet<String> excludesMap = new HashSet<>(Arrays.asList(excludeFiles));
        String prefixPath = "src/main/resources/";
        for (String folder : folders) {
            handleFolder(new File(prefixPath + folder), excludesMap);
        }
        for (String file : files) {
            handleFile(new File(prefixPath + file + ".png"), excludesMap);
        }
    }

    private static void handleFolder(File folder, HashSet<String> excludeFiles) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) handleFolder(file, excludeFiles);
                else handleFile(file, excludeFiles);
            }
        }
    }

    private static void handleFile(File file, HashSet<String> excludeFiles) {
        String name = file.getName();
        if (name.endsWith(".png")) {
            name = name.substring(0, name.length() - 4);
            if (excludeFiles.contains(name) || excludeFiles.contains(file.getParent() + "/" + name)) return;
            try {
                GameTexture texture = handleTexture(GameTexture.fromFileRawOutside(file.getAbsolutePath(), true), file.getPath());
                if (texture != null) {
                    texture.saveTextureImage(file.getAbsolutePath(), false);
                    System.out.println("Handled and saved file " + file.getPath());
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error finding game texture: " + file.getPath());
            }
        }
    }

    private static GameTexture handleTexture(GameTexture texture, String path) {
        for (int i = 1; i <= 4; i++) {
            int resolution = 32 * i;
            int[] expectedWidths = new int[] {
                    6 * resolution
            };
            int[] expectedHeights = new int[] {
                    4 * resolution,
                    5 * resolution
            };
            boolean valid = false;
            for (int width : expectedWidths) {
                if (texture.getWidth() == width) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                for (int height : expectedHeights) {
                    if (texture.getHeight() == height) {
                        valid = true;
                        break;
                    }
                }
            }
            if (valid) {
                GameTexture out = new GameTexture(texture.debugName + "_fixed", 7 * resolution, texture.getHeight());
                out.copy(texture, 0, 0, 0, 0, 5 * resolution, texture.getHeight());
                out.copy(texture, 5 * resolution, 0, 0, 0, resolution, 4 * resolution);
                out.copy(texture, 6 * resolution, 0, 0, 0, resolution, 4 * resolution);
                return out;
            }
        }
        GameLog.warn.println("Ignored file because it did not have the expected widths/heights: " + path);
        return null;
    }
}
