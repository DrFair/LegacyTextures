package legacytextures.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.drawOptions.human.HumanDrawOptions;
import necesse.inventory.InventoryItem;
import net.bytebuddy.asm.Advice;

public class HumanDrawOptionsPatches {

    @ModMethodPatch(target = HumanDrawOptions.class, name = "pos", arguments = {int.class, int.class})
    public static class PosPatch {
        @Advice.OnMethodEnter()
        static void onEnter(@Advice.This HumanDrawOptions thiz) {
            thiz.eyelidsTexture(null);
            thiz.blinking(false);
        }
    }

    @ModMethodPatch(target = HumanDrawOptions.class, name = "holdItem", arguments = {InventoryItem.class})
    public static class HoldItemPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.This HumanDrawOptions thiz, @Advice.FieldValue("dir") int dir) {
            if (dir == 0 || dir == 2) {
                thiz.rightArmSprite(4);
            } else if (dir == 3) {
                thiz.leftArmSprite(2);
            } else {
                thiz.rightArmSprite(2);
            }
        }
    }

    @ModMethodPatch(target = HumanDrawOptions.class, name = "isSpriteXOffset", arguments = {int.class})
    public static class IsSpriteXOffsetPatch {
        @Advice.OnMethodExit()
        static void onExit(@Advice.Argument(0) int spriteX, @Advice.Return(readOnly = false) boolean out) {
            out = spriteX == 2 || spriteX == 4;
        }
    }

}
