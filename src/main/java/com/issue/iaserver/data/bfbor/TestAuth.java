package com.issue.iaserver.data.bfbor;

import com.issue.iaserver.data.bfbor.base.BfConfiguration;
import com.issue.iaserver.data.bfbor.utils.BFUtil;

import java.util.UUID;

public class TestAuth {

    public static void main(String [] args){
        BfConfiguration bc = new BfConfiguration("106.54.135.213",6379,8,0.00001,200000000);
        System.out.println(bc);
        BFUtil bfUtil = new BFUtil(bc);
        long start = System.currentTimeMillis();
        int number =100;
        for (int i = 0;i <= number;i++){
            bfUtil.add(UUID.randomUUID().toString());
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)/number);
    }

}
