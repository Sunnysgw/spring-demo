package com.sgw.provider.common.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.data.domain.Sort;
import org.springframework.util.ClassUtils;

import java.util.Collections;
import java.util.Set;

/**
 * @author sunnys
 */
public class StringToSortConverter implements ConditionalGenericConverter {

    private final static String ASC = "asc";

    private final static String DESC = "desc";

    private final ConversionService conversionService;


    public StringToSortConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (ClassUtils.isAssignable(sourceType.getType(), String.class) && ClassUtils.isAssignable(targetType.getType(), Sort.class)) {
            return true;
        }
        if (ClassUtils.isAssignable(sourceType.getType(), targetType.getType())) {
            // maybe
            return true;
        }
        // no
        return false;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Sort.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        String sourceValue = (String) source;
        String[] split = sourceValue.split(",");
        if (split.length == 2) {
            String order = split[0];
            String property = split[1];
            return Sort.by(ASC.equalsIgnoreCase(order) ? Sort.Order.asc(property) : Sort.Order.desc(property));
        }
        return null;
    }
}
