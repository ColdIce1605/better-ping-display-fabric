package com.vladmarica.betterpingdisplay.hud;

import com.vladmarica.betterpingdisplay.mixin.PlayerListHudAccessor;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;

public class PlayerListHudUtil {
  /** Calls {@link net.minecraft.client.gui.hud.PlayerListHud#renderLatencyIcon}. */
  static void renderLatencyIcon(PlayerListHud hud, MatrixStack stack, int x, int offsetX, int y, PlayerListEntry player) {
    ((PlayerListHudAccessor) hud).invokeRenderLatencyIcon(stack, x, offsetX, y, player);
  }

  /** Calls {@link net.minecraft.client.gui.hud.PlayerListHud#renderScoreboardObjective} */
  static void renderScoreboardObjective(PlayerListHud hud, MatrixStack stack, ScoreboardObjective obj, int i, String str, int j, int k, PlayerListEntry player) {
    ((PlayerListHudAccessor) hud).invokeRenderScoreboardObjective(obj, i, str, j, k, player, stack);
  }

  static Text getHeader(PlayerListHud hud) {
    return ((PlayerListHudAccessor) hud).getHeader();
  }

  static Text getFooter(PlayerListHud hud) {
    return ((PlayerListHudAccessor) hud).getFooter();
  }
}
