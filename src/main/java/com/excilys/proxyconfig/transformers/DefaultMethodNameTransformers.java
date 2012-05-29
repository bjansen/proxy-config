package com.excilys.proxyconfig.transformers;

import com.excilys.proxyconfig.internal.util.Strings;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public enum DefaultMethodNameTransformers implements MethodNameTransformer {

    NO_TRANSFORM {
        @Override
        public String transform(String methodName) {
            return methodName;
        }
    },
    LOWER_CASE {
        @Override
        public String transform(String methodName) {
            return methodName.toLowerCase();
        }
    },
    CAMEL_CASE_TO_DOTS {
        @Override
        public String transform(String methodName) {
            return Strings.camelCaseToDots(methodName);
        }
    },
    CAMEL_CASE_TO_UNDERSCORES {
        @Override
        public String transform(String methodName) {
            return Strings.camelCaseToUnderscores(methodName);
        }
    },
    UNCAP_FIRST {
        @Override
        public String transform(String methodName) {
            return Strings.uncapFirst(methodName);
        }
    };

    public static List<MethodNameTransformer> valuesAsList() {
        List<MethodNameTransformer> list = new ArrayList<MethodNameTransformer>();

        for (DefaultMethodNameTransformers transformer : values()) {
            list.add(transformer);
        }

        return list;
    }
}
