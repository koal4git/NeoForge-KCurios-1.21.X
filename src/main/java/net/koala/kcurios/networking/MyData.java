package net.koala.kcurios.networking;

import io.netty.buffer.ByteBuf;
import net.koala.kcurios.Kcurios;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MyData(String name, int age) implements CustomPacketPayload {
    public static final Type <MyData> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID, "my_data"));

    //Each pair of elemtents defiens the stream codec of the elemnt to encode/decode and the getter for the elemment to encode
    // variables inside will be encoded and decoded as their data type
    // the final paramet er takesi n the previous parameters in the order they are provided to construct the payload okect
    public static final StreamCodec<ByteBuf, MyData> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.STRING_UTF8,
        MyData::name,
        ByteBufCodecs.VAR_INT,
        MyData::age,
        MyData::new


    );


    @Override
    public Type<? extends CustomPacketPayload> type() {return TYPE;}
}
