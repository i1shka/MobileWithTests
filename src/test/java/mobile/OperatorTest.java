package mobile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {
    @Test
    void testSqrDistanceCalc1() {
        Assertions.assertEquals(25, Operator.sqrDistanceCalc(0,3,0,4));
    }

    @Test
    void testSqrDistanceCalc2() {
        Assertions.assertEquals(100, Operator.sqrDistanceCalc(-6,0,-8,0));
    }

    @Test
    void testSqrDistanceCalc3() {
        Assertions.assertEquals(0, Operator.sqrDistanceCalc(10,10,-5,-5));
    }

    @Test
    void testResultMap1() {
//        проверяем, когда абонент в зоне покрытия, и оператора нет в map, добавляем опреатора и 1
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 1);
        Map<String, Integer> actual=new LinkedHashMap<>();
        Operator.resultMap(10,15,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap2() {
        //        проверяем, когда абонент вне зоны покрытия, и оператора нет в map, добавляем опреатора и 0
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 0);
        Map<String, Integer> actual=new LinkedHashMap<>();
        Operator.resultMap(10,5,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap3() {
        //        проверяем, когда абонент на границе зоны покрытия, и оператора нет в map, добавляем опреатора и 1
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 1);
        Map<String, Integer> actual=new LinkedHashMap<>();
        Operator.resultMap(10,10,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap4() {
        //        проверяем, когда абонент в зоне покрытия, и оператор есть в map, добавляем опреатора и 1
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 2);
        Map<String, Integer> actual=new LinkedHashMap<>();
        actual.put("Megahorn", 1);
        Operator.resultMap(5,10,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap5() {
        //        проверяем, когда абонент вне зоны покрытия, и оператор есть в map, добавляем опреатора, количество БС не меняется
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 1);
        Map<String, Integer> actual=new LinkedHashMap<>();
        actual.put("Megahorn", 1);
        Operator.resultMap(15,10,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap6() {
        //        проверяем, когда абонент вне зоны покрытия, и оператор есть в map со значением 0, добавляем опреатора, 0 не меняется
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 0);
        Map<String, Integer> actual=new LinkedHashMap<>();
        actual.put("Megahorn", 0);
        Operator.resultMap(15,10,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testResultMap7() {
        //        проверяем, когда абонент в зоне покрытия, и оператор есть в map со значением 0, добавляем опреатора, БС = 1
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 1);
        Map<String, Integer> actual=new LinkedHashMap<>();
        actual.put("Megahorn", 0);
        Operator.resultMap(5,10,"Megahorn",actual);
        Assertions.assertIterableEquals(expected.entrySet(), actual.entrySet());
    }

    @Test
    void testCheckCoverage1() {
//        Проверяем правильность обработки оператора и параметров БС, когда абонент в зоне покрытия
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 1);
        Operator op = new Operator("Megahorn", new ArrayList<>(asList(0,0,10)));
        Assertions.assertIterableEquals(expected.entrySet(), op.checkCoverage(1,1).entrySet());
    }

    @Test
    void testCheckCoverage2() {
//        Проверяем правильность обработки оператора и параметров БС, когда абонент вне зоны покрытия
        Map<String, Integer> expected=new LinkedHashMap<>();
        expected.put("Megahorn", 0);
        Operator op = new Operator("Megahorn", new ArrayList<>(asList(0,0,10)));
        Assertions.assertIterableEquals(expected.entrySet(), op.checkCoverage(-20,-20).entrySet());
    }
}