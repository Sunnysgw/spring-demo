package com.sgw.common;

import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class MyEncoder implements Encoder, Decoder {
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        return null;
    }
}
