import java.util.HashMap;
import java.util.Map;

public class ThirdTest {

    public static void main(String[] args) {
        ThirdTest thirdTest = new ThirdTest();
        int maxlength = thirdTest.lengthOfLongestSubstring("abababbacf");
        System.out.println("最大长度："+maxlength);
    }
    public int lengthOfLongestSubstring(String s) {
        Map<String, Map<Character,Integer>> maxLengthMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(maxLengthMap.get("max")==null){
                Map<Character,Integer> initMap = new HashMap<>();
                initMap.put(c,i);
                maxLengthMap.put("max",initMap);
                max = Math.max(max,initMap.size());
                continue;
            }
            Map<Character,Integer> exstMap = maxLengthMap.get("max");
            if(exstMap.get(c)==null){
                exstMap.put(c,i);
                max = Math.max(max,exstMap.size());
            }else {
                int begin = exstMap.get(c);
                Map<Character,Integer> initMap = new HashMap<>();
                for (int j = begin+1; j < i; j++) {
                    initMap.put(s.charAt(j),j);
                }
                initMap.put(c,i);
                max = Math.max(max,initMap.size());
                maxLengthMap.put("max",initMap);
            }
        }
        return max;
    }
}
