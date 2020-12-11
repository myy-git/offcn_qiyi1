package com.offcn.test;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class meTest {

    @Test
    public void randoms(){
        //生成随机数1-10去重
        Random random = new Random(1);
        for (int i = 0 ;i<=3;i++){

            Set<Integer> set = new HashSet();
        while (true) {
            set.add(random.nextInt(10));
            if (set.size() > 8) {
                break;
            }
        }
        for (Integer sets:set){
            System.out.print(sets.intValue()+",");
        }
            System.out.println();
        }

    }

    @Test
    public void randos() {
        String  s = "2-233-22-11";
        String[] split = s.split("-");
        Integer s12 = 0;
        for (String s1 : split) {
            int i = Integer.parseInt(s1);
            s12 =  i+s12;
        }
        System.out.println(s12);
    }

    @Test
    public void random() throws IOException {
        FileReader fileReader = new FileReader("E:\\11.txt");
        int i = 0;
        while ((i=fileReader.read()) != -1){
            System.out.println((char) i);
        }
    }


   /* public static void main(String[] args) {
        System.out.println(test1(15));
    }*/

    public static Integer test1(Integer n){
        if (n.byteValue()==1){
            return n;
        }else{
            return n * test1(n-1);
        }
    }

    public static long factorial(long n){
        if(n == 1){
            return 1;
        }
        else{
            return n*factorial(n-1);
        }
    }

    //主方法
 /*   public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);//声明Scanner对象
        System.out.print("请输入数字:"); //提示用户输入数字
        int num = scanner.nextInt();//定义num接受输入的数字
        long ret = factorial(num);//调用阶乘方法
        System.out.println(num+"的阶乘为"+ret);//输出返回值
    }
*/
    @Test
    public  void test2(){
        for (int i = 0; i < 10; i++) {
            if (i%4==0){
                System.out.println(i);
            }
        }
    }

}
