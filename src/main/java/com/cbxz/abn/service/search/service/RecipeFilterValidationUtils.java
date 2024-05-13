package com.cbxz.abn.service.search.service;

import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.EnumSet;

class RecipeFilterValidationUtils {

    private static final EnumSet<Operation> EQUALS_OPERATIONS = EnumSet.of(
            Operation.EQUALS,
            Operation.NOT_EQUALS
    );

    static void validateCriteria(SearchCriteria criteria) {
        if (keyIsOfType(criteria, Boolean.class)) {
            if (!isBoolean(criteria.value())) {
                throwWrongTypeParameter(criteria);
            }
        }
        if (keyIsOfType(criteria, Integer.class)) {
            if (!NumberUtils.isCreatable(criteria.value())) {
                throwWrongTypeParameter(criteria);
            }
        }
        if (isBooleanOrIntegerType(criteria) && notEqualsOperation(criteria)) {
            throwWrongOperationType(criteria);
        }

    }

    private static boolean keyIsOfType(SearchCriteria criteria, Class<?> type) {
        return criteria.key().getType().equals(type);
    }

    private static boolean notEqualsOperation(SearchCriteria criteria) {
        return !EQUALS_OPERATIONS.contains(criteria.operator());
    }

    private static boolean isBooleanOrIntegerType(SearchCriteria criteria) {
        return keyIsOfType(criteria, Integer.class) || keyIsOfType(criteria, Boolean.class);
    }

    private static void throwWrongOperationType(SearchCriteria criteria) {
        throw new IllegalArgumentException(
                "Wrong operation type " +
                        criteria.operator() +
                        " for parameter " +
                        criteria.key().name() +
                        " should be one of " +
                        EQUALS_OPERATIONS);
    }

    private static void throwWrongTypeParameter(SearchCriteria criteria) {
        throw new IllegalArgumentException(
                "Wrong type for parameter " +
                        criteria.key().name() +
                        " should be " +
                        criteria.key().getType().getSimpleName());
    }

    private static boolean isBoolean(String string) {
        return string.equalsIgnoreCase("false") || string.equalsIgnoreCase("true");
    }
}
