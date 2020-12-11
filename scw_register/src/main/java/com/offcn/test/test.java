package com.offcn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {



        public static void main(String[] args) {
            int[] i = {1,2,6,65,1,1,2,3};
            int[] ints = test.searchRange(i, 1);
            for (int anInt : ints) {
                System.out.println(anInt);
            }


        }

        public static int[] searchRange(int[] nums, int target) {
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0;i<nums.length;i++){
                if(nums[i]==target){
                    list.add(i);
                }
            }

            Map map = new HashMap<>();
            if(list.size()==0){
                map.put("first",-1);
                map.put("last",-1);
            }else{
                map.put("first",list.get(0));
                map.put("last",list.get(list.size()-1));
            }
            int[] i = {0,0};
            i[0] = (Integer) map.get("first");
            i[1] = (Integer) map.get("last");
            return i;
        }

}
