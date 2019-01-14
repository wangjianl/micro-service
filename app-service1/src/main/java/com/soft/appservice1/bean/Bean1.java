package com.soft.appservice1.bean;
/**
 * Created by wangjian on 18/11/5.
 */


import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Bean1 {

    public static void main(String []args){
        String []star = new String [] {"1","2"};
        System.out.print(Arrays.asList(star).add("4"));

    }


    private Boolean success;
    private Boolean isFail;

    public Boolean getFail() {
        Objects.equals("","");
        return isFail;
    }

    public void setFail(Boolean fail) {
        isFail = fail;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
