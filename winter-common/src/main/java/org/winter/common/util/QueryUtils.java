package org.winter.common.util;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.winter.common.annotation.QueryCriteria;
import org.winter.common.enums.QueryType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryUtils {

    public static <R, Q> Predicate getPredicate(Root<R> root, Q queryObject, CriteriaBuilder criteriaBuilder) {
        Field[] fields = ReflectUtil.getFieldsDirectly(queryObject.getClass(), true);
        Map<String, String> stringFuzzyMap = new HashMap<>();
        Map<String, String> equalsMap = new HashMap<>();
        Map<String, List<?>> betweenMap = new HashMap<>();
        for (Field field : fields) {
            QueryCriteria queryCriteria = field.getAnnotation(QueryCriteria.class);
            if (ObjectUtil.isNotNull(queryCriteria)) {
                QueryType queryType = queryCriteria.type();
                String fieldName = field.getName();
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Object invokeObj = ReflectUtil.invoke(queryObject, methodName);
                    if (ObjectUtil.equal(queryType, QueryType.LIKE)) {
                        //字符串模糊查询
                        if (ObjectUtil.isNotNull(invokeObj)) {
                            String value = invokeObj.toString();
                            stringFuzzyMap.put(fieldName, value);
                        }
                    } else if (ObjectUtil.equal(queryType, QueryType.EQUALS)) {
                        //1.对象属性做EQUALS
                        if (ObjectUtil.isNotNull(invokeObj)) {
                            String fieldValue = invokeObj.toString();
                            if (!"".equals(fieldValue)) {
                                equalsMap.put(fieldName, fieldValue);
                            }
                        }
                        //2.注解value做EQUALS
                        String annotationValue = queryCriteria.value();
                        if (ObjectUtil.isNotNull(annotationValue) && !"".equals(annotationValue)) {
                            equalsMap.put(fieldName, annotationValue);
                        }
                    } else if (ObjectUtil.equal(queryType, QueryType.BETWEEN)) {
                        if (ObjectUtil.isNotNull(invokeObj)) {
                            List<?> objects = (List<?>) invokeObj;
                            if (objects.size() > 0) {
                                betweenMap.put(fieldName, (List<?>) invokeObj);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        List<Predicate> predicateList = new ArrayList<>();
        for (String field : stringFuzzyMap.keySet()) {
            String value = stringFuzzyMap.get(field);
            if (value != null && !"".equals(value)) {
                predicateList.add(criteriaBuilder.like(root.get(field), "%" + value + "%"));
            }
        }
        for (String field : equalsMap.keySet()) {
            String value = equalsMap.get(field);
            if (BooleanUtils.isStringToBoolean(value)) {
                predicateList.add(criteriaBuilder.equal(root.get(field), Boolean.valueOf(value)));
            } else if (NumberUtil.isNumber(value)) {
                predicateList.add(criteriaBuilder.equal(root.get(field), Long.valueOf(value)));
            } else {
                predicateList.add(criteriaBuilder.equal(root.get(field), value));
            }
        }
        for (String field : betweenMap.keySet()) {
            List<?> list = betweenMap.get(field);
            if (ArrayUtil.isNotEmpty(list)) {
                if (list.get(0) instanceof Timestamp && list.get(1) instanceof Timestamp) {
                    predicateList.add(criteriaBuilder.between(root.get(field), (Timestamp) list.get(0), (Timestamp) list.get(1)));
                }
                if (list.get(0) instanceof Long && list.get(1) instanceof Long) {
                    predicateList.add(criteriaBuilder.between(root.get(field), (Long) list.get(0), (Long) list.get(1)));
                }
                if (list.get(0) instanceof Integer && list.get(1) instanceof Integer) {
                    predicateList.add(criteriaBuilder.between(root.get(field), (Integer) list.get(0), (Integer) list.get(1)));
                }
            }
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
