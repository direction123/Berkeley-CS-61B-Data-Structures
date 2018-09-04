package hw1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Nuke2 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a string whose length is larger than 2");
        String input = bufferedReader.readLine();
        System.out.println(input.substring(0, 1) + input.substring(2));
    }
}
