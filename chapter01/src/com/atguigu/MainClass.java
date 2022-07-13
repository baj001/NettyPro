package com.atguigu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sfkstart
 * @create 2021-12-09-10:16
 */
/*
分析
abcd互不相同 且都大于0 其中abcd代表的是索引
根据之前的三数之和
先固定一位，然后使用双指针
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //首先定义一个list用来存储结果
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 4) return ans;
        //对数组进行排序
        Arrays.sort(nums);
        //使用双层 暴力一波
        for(int i = 0; i < (len - 3); i++){
            //首先进行去重
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j = i + 1; j < (len - 2); j++){
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                int L = j + 1;
                int R = len - 1;
                while(L < R){
                    if(L > j + 1 && nums[L] == nums[L - 1]){
                        L++;
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if(target > sum) L++;
                    else if(target < sum) R--;
                    else {
                        ans.add(Arrays.asList(nums[i],nums[j],nums[L],nums[R]));
                        L++;
                        R--;

                    }
                }
            }
        }
        return ans;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            List<List<Integer>> ret = new Solution().fourSum(nums, target);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}