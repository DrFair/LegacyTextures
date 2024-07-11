package legacytextures.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.registries.MobRegistry;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.HumanTexture;
import necesse.entity.mobs.hostile.pirates.PirateRecruit;
import net.bytebuddy.asm.Advice;

public class PirateRecruitPatches {

    @ModMethodPatch(target = PirateRecruit.class, name = "init", arguments = {})
    public static class InitPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.This PirateRecruit thiz, @Advice.FieldValue(value = "texture", readOnly = false) HumanTexture texture) {
            if (thiz.getLevel() != null) {
                texture = new GameRandom(thiz.getUniqueID())
                        .nextSeeded(23)
                        .getOneOf(MobRegistry.Textures.pirateRecruit1, MobRegistry.Textures.pirateRecruit2);
            }
        }
    }
}
