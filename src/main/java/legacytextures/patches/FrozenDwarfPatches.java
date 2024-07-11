package legacytextures.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.hostile.FrozenDwarfMob;
import net.bytebuddy.asm.Advice;

public class FrozenDwarfPatches {

    @ModMethodPatch(target = FrozenDwarfMob.class, name = "init", arguments = {})
    public static class InitPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.This FrozenDwarfMob thiz) {
            thiz.hasHair = false;
        }
    }
}
