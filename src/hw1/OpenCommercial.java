package hw1;/* hw1.OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

    /** Prompts the user for the name X of a company (a single string), opens
     *  the Web site corresponding to www.X.com, and prints the first five lines
     *  of the Web page in reverse order.
     *  @param arg is not used.
     *  @exception Exception thrown if there are any problems parsing the
     *             user's input or opening the connection.
     */
    public static void main(String[] arg) throws Exception {

        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the name of a company (without spaces): ");
        System.out.flush();        /* Make sure the line is printed immediately. */
        inputLine = keyboard.readLine();

        /* Replace this comment with your solution.  */
        OpenCommercial openCommercial = new OpenCommercial();
        URL url = new URL(openCommercial.getURL(inputLine));

        BufferedReader webpage = new BufferedReader(new InputStreamReader(url.openStream()));
        String webpageLine;
        String[] webpageLines = new String[5];
        int index = 4;
        while((webpageLine = webpage.readLine()) != null && index >= 0) {
            webpageLines[index] = webpageLine;
            index--;
        }
        for (String s:webpageLines) {
            System.out.println(s);
        }
    }

    private String getURL(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.");
        sb.append(name);
        sb.append(".com/");
        return sb.toString();
    }
}