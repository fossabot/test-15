package me.zeroeightsix.kami.module.modules.movement;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zeroeightsix.kami.event.KamiEvent;
import me.zeroeightsix.kami.event.events.EntityEvent;
import me.zeroeightsix.kami.event.events.PacketEvent;
import me.zeroeightsix.kami.module.Module;
import me.zeroeightsix.kami.setting.Setting;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

/**
 * Created by 086 on 16/11/2017.
 */
@Module.Info(name = "Velocity", description = "Modify knockback impact", category = Module.Category.MOVEMENT)
public class Velocity extends Module {

    @Setting(name = "Horizontal")
    private float horizontal = 0;

    @Setting(name = "Vertical")
    private float vertical = 0;

    @EventHandler
    private Listener<PacketEvent.Receive> packetEventListener = new Listener<PacketEvent.Receive>(event -> {
        if (event.getEra() == KamiEvent.Era.PRE) {
            if (event.getPacket() instanceof SPacketEntityVelocity) {
                SPacketEntityVelocity velocity = (SPacketEntityVelocity) event.getPacket();
                if (velocity.getEntityID() == mc.player.entityId) {
                    velocity.motionX *= horizontal;
                    velocity.motionY *= vertical;
                    velocity.motionZ *= horizontal;
                }
            }else if (event.getPacket() instanceof SPacketExplosion) {
                SPacketExplosion velocity = (SPacketExplosion) event.getPacket();
                velocity.motionX *= horizontal;
                velocity.motionY *= vertical;
                velocity.motionZ *= horizontal;
            }
        }
    });

    @EventHandler
    private Listener<EntityEvent.EntityCollision> entityCollisionListener = new Listener<EntityEvent.EntityCollision>(event -> {
        if (event.getEntity() == mc.player) {
            if (horizontal == 0 && vertical == 0) {event.cancel(); return; }
            event.setX(-event.getX()*horizontal);
            event.setY(0);
            event.setZ(-event.getZ()*horizontal);
        }
    });

}