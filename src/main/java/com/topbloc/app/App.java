package com.topbloc.app;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public HashMap<String, Student> map = new HashMap<String, Student>();

    public static void main( String[] args ) throws IOException, InvalidFormatException
    {
        //Read each excel file
        ExcelReader.readStudentInfo(ExcelReader.getWorkbook("src/main/java/resources/Student Info.xlsx"));
        ExcelReader.readTestScores(ExcelReader.getWorkbook("src/main/java/resources/Test Scores.xlsx"));
        ExcelReader.readTestRetakeScores(ExcelReader.getWorkbook("src/main/java/resources/Test Retake Scores.xlsx"));

        //calculate average of final test scores
        for (Map.Entry<String, Student> entry : myClass.map.entrySet()) {
            System.out.println(entry.getValue());
            myClass.classAverage += entry.getValue().getMaxScore();
        }
        myClass.classAverage = (int) myClass.classAverage/myClass.count;
        Collections.sort(myClass.csFemales);

        //post to api
        postChallengeData("http://3.86.140.38:5000/challenge");
    }

    public static void postChallengeData(String url) {
        Gson gson = new Gson();
        String[] studentIds = new String[myClass.csFemales.size()];
        studentIds = myClass.csFemales.toArray(studentIds);

        PostData payload = new PostData("fahmed93@gmail.com", "Fahad Ahmed", studentIds, myClass.classAverage);
        String jsonPayload = gson.toJson(payload);
        System.out.println(jsonPayload);
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    url);

            StringEntity input = new StringEntity(jsonPayload);
          //  System.out.println(input.toString());
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
