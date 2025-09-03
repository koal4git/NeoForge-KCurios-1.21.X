package net.koala.kcurios.networking;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {


    public static void handleDashEnchantmentUsed(final MyData data, final IPayloadContext context) {
        //do somethign with the data, onm the main thread(I SHOULD BE ON THE SERVER HERE)
        EntityType.COW.spawn(((ServerLevel) context.player().level()), context.player().getOnPos(), MobSpawnType.TRIGGERED);
    }
}
