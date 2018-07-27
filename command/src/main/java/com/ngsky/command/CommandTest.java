package com.ngsky.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandTest {
    public static void main(String[] args) {
        InputStreamReader isr = null;
        BufferedReader br = null;

        Process process = null;

        List<String> commandList = new ArrayList<String>();
        /*commandList.add("cd /");
        commandList.add("");
        commandList.add("ps auxf");
        commandList.add("netstat -naltp");*/
        commandList.add("java -version");
        commandList.add("ipconfig");
        commandList.add("netstat");
        try {
            ProcessBuilder pb = new ProcessBuilder(commandList);
            pb.command("cmd", "/c", "");
            pb.command("java -version");
            process = pb.start();
            isr = new InputStreamReader(process.getInputStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            isr.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
