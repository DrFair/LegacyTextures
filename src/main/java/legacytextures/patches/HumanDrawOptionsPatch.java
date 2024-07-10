package legacytextures.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.drawOptions.human.HumanDrawOptions;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = HumanDrawOptions.class, name = "pos", arguments = {int.class, int.class})
public class HumanDrawOptionsPatch {

    @Advice.OnMethodEnter()
    static void onEnter(@Advice.This HumanDrawOptions drawOptions) {
        drawOptions.eyelidsTexture(null);
//        drawOptions.facialFeatureTexture(null);
//        drawOptions.backFacialFeatureTexture(null);
        drawOptions.blinking(false);
    }
}
