package com.sgw.common.configuration;

import feign.FeignException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import static java.lang.String.format;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class FeignConfiguration implements RequestInterceptor, Encoder, Decoder {
    @Override
    public void apply(RequestTemplate template) {
        template.header("data", new Date().toString());
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        return null;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType == String.class) {
            template.body(object.toString());
        } else if (bodyType == byte[].class) {
            template.body((byte[]) object, null);
        } else if (object != null) {
            throw new EncodeException(
                    format("%s is not a type supported by this encoder.", object.getClass()));
        }
    }
}
