package pl.szotaa.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kubas on 12.06.2017.
 */
public class BmpGenerator
{
    public static String buildOutput(String numOfDigits, String messValue)
    {
        String path = BmpGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.concat("/CaptchaGenerator.exe");
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder(path, numOfDigits, messValue);
            processBuilder.redirectErrorStream();
            Process process = processBuilder.start();
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String temp;
            while((temp = bufferedReader.readLine()) != null)
                stringBuilder.append(temp);
            bufferedReader.close();

        }
        catch(Exception e)
        {
            System.out.println("Exception caught in BmpGenerator: buildOutput(String, String)");
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
