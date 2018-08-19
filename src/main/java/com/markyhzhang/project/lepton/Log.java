package com.markyhzhang.project.lepton;

public class Log {

    public static void print(String s){
        System.out.println(s);
    }

    public static void d(String s){
        print("[DEBUG] " + s);
    }

    public static void d(int s){
        d(s+"");
    }

    public static void d(double s){
        d(s+"");
    }

    public static void d(long s){
        d(s+"");
    }

    public static void d(Object s){
        d(s.toString());
    }

    public static void e(Exception e){
        print("[==========(ERROR)==========]");
        e.printStackTrace();
        print("[=======(END OF ERROR)=======]");
    }

}
