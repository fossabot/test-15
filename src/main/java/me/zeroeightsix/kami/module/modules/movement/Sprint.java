package me.zeroeightsix.kami.module.modules.movement;

import me.zeroeightsix.kami.module.Module;
import org.lwjgl.input.Keyboard;

/**
 * Created by 086 on 23/08/2017.
 */
@Module.Info(name = "Sprint", bind = Keyboard.KEY_A, description = "Automatically makes the player sprint", category = Module.Category.MOVEMENT)
public class Sprint extends Module {

    @Override
    public void onUpdate() {
        if (!mc.player.collidedHorizontally && mc.player.moveForward > 0)
            mc.player.setSprinting(true);
        else
            mc.player.setSprinting(false);
    }

}