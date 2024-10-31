package mobile;

import java.util.*;

public class Operator {
    String name;
    ArrayList<Integer> baseStation;
    static Map<String, ArrayList<Integer>>operators=new LinkedHashMap<>();

    public Operator(){}

    public Operator(String name, ArrayList<Integer> baseStation) {
        this.name = name;
        this.baseStation = baseStation;
        if (operators.containsKey(this.name))
        {
            operators.get(this.name).addAll(this.baseStation);
        } else {
        operators.put(this.name, this.baseStation);}
    }

    public Map<String, Integer> checkCoverage(int x, int y){
        Map<String, Integer> res=new LinkedHashMap<>();
        for(Map.Entry<String, ArrayList<Integer>> entr : operators.entrySet()){
            for (int i = 0; i <entr.getValue().size() ; i+=3) {
                int s=sqrDistanceCalc(entr.getValue().get(i), x, entr.getValue().get(i+1), y);
                int r=(int)Math.pow(entr.getValue().get(i+2), 2);
                resultMap(s, r, entr.getKey(), res);
            }
        }
        return res;
    }

    public static void resultMap(int s, int r, String opName, Map<String, Integer> res){
        int service = 0;
        if (s<= r) service=1;
        if(res.containsKey(opName)){
            res.put(opName,res.get(opName)+service);}
        else res.put(opName,service);
    }

    public static int sqrDistanceCalc(int x1, int x2, int y1, int y2){
        int s= (int) (Math.pow((x1-x2),2) +  Math.pow((y1-y2),2));
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(name, operator.name) && Objects.equals(baseStation, operator.baseStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseStation);
    }
}
