package examplemod;

import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class ExampleMod {

    public void init() {
        System.out.println("Hello from LegacyTextures mod!");
    }

    public void initResources() {
    }

    public void postInit() {
    }

}
