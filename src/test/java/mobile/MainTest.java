package mobile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void TestGetOperator1(){
        Operator expected=new Operator("MPS", new ArrayList<>(asList(1,1,10)));
        Operator actual=Main.getOperator(asList("MPS", "1", "1", "10"));
        Assertions.assertEquals(expected.name, actual.name);
        Assertions.assertEquals(expected.baseStation, actual.baseStation);
    }

    @Test
    void TestGetOperator2(){

    }

    @Test
    void TestGetOperator3(){

    }

}