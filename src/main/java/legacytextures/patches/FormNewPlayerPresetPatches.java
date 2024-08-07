package legacytextures.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.forms.components.componentPresets.FormNewPlayerPreset;
import net.bytebuddy.asm.Advice;

import java.awt.*;

public class FormNewPlayerPresetPatches {

    @ModMethodPatch(target = FormNewPlayerPreset.class, name = "getSkinFaceDrawOffset", arguments = {})
    public static class SkinDrawOffsetPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.Return(readOnly = false) Point out) {
            out = new Point(-3, 0);
        }
    }

    @ModMethodPatch(target = FormNewPlayerPreset.class, name = "getEyeTypeFaceDrawOffset", arguments = {})
    public static class EyeTypeDrawOffsetPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.Return(readOnly = false) Point out) {
            out = new Point(-10, -18);
        }
    }

    @ModMethodPatch(target = FormNewPlayerPreset.class, name = "getEyeColorFaceDrawOffset", arguments = {})
    public static class EyeColorDrawOffsetPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.Return(readOnly = false) Point out) {
            out = new Point(-10, -18);
        }
    }

}
