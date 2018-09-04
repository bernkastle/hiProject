package com.annotation;

public class NoBug {
    @Check
    public void suanShu(){
        System.out.println("1234567890");
    }

    @Check
    public void jiafa(){
        System.out.println("1+1="+1+1);
    }
    @Check
    public void jiefa(){
        System.out.println("1-1="+(1-1));
    }
    @Check
    public void chengfa(){
        System.out.println("3 x 5="+ 3*5);
    }
    @Check
    public void chufa(){
        System.out.println("6 / 0="+ 6 / 0);
    }

    public void ziwojieshao(){
        System.out.println("我写的程序没有 bug!");
    }
}
