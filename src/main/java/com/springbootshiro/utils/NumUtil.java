package com.springbootshiro.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/21.
 */
public class NumUtil {
    public int[] productExceptSelf(int[] nums) {
        if(nums.length <= 1){
            return nums;
        }
        int[] res = new int[nums.length];
        res[0] = 1;
        int left = 1, right = 1;
        // 计算每个点左边的乘积
        for(int i = 1; i < nums.length; i++){
            left = left * nums[i - 1];
            res[i] = left;
        }
        // 计算每个点右边的乘积
        for(int i = nums.length - 2; i >= 0; i--){
            right = right * nums[i + 1];
            res[i] = right * res[i];
        }
        return res;


    }

    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i:nums){
            if(!set.add(i))
                set.remove(i);
        }
        Iterator<Integer> it = set.iterator();
        return it.next();
    }
}
