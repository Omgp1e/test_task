package org.example.testcase.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.*;


@UtilityClass
public class RowMapperUtil {
    public static String str(Object[] row, int i) {
        return row[i] != null ? row[i].toString() : null;
    }

    public static LocalDate date(Object[] row, int i) {
        Object value = row[i];
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }
        if (value instanceof java.sql.Date) {
            return ((java.sql.Date) value).toLocalDate();
        }
        if (value instanceof java.util.Date) {
            return ((java.util.Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        if (value instanceof Instant) {
            return ((Instant) value).atZone(ZoneId.systemDefault()).toLocalDate();
        }
        if (value instanceof OffsetDateTime) {
            return ((OffsetDateTime) value).toLocalDate();
        }
        throw new IllegalArgumentException("Unsupported date type: " + value);
    }

    public static YearMonth yearMonth(Object[] row, int i) {
        return YearMonth.from(date(row, i));
    }

    public static Long longVal(Object[] row, int i) {
        Object val = row[i];
        if (val == null) return 0L;
        if (val instanceof Number n) return n.longValue();
        return Long.parseLong(val.toString());
    }

    public static BigDecimal decimal(Object[] row, int i) {
        Object val = row[i];
        if (val == null) return BigDecimal.ZERO;
        if (val instanceof BigDecimal bd) return bd;
        if (val instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        return new BigDecimal(val.toString());
    }
}
