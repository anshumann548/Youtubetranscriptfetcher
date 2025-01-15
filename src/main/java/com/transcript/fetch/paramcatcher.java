package com.transcript.fetch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.brotli.dec.BrotliInputStream;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class paramcatcher {

    String VIDEO_URL;

    /**
     * Handles Brotli-compressed or uncompressed responses and converts them to
     * a String.
     *
     * @param response the HTTP response
     * @return the decompressed or plain response body as a String
     */
    private static String BrResponseToJSONStr(Response response) {
        String contentEncoding = response.getHeader("Content-Encoding");
        try {
            if ("br".equals(contentEncoding)) {
                // Handle Brotli compression
                byte[] compressedData = response.getBody().asByteArray();
                try (BrotliInputStream brotliInputStream = new BrotliInputStream(new ByteArrayInputStream(compressedData))) {
                    return new String(brotliInputStream.readAllBytes(), StandardCharsets.UTF_8);
                }
            } else {
                // Handle uncompressed responses
                return new String(response.getBody().asByteArray(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.err.println("Error decompressing Brotli response: " + e.getMessage());
        }
        return "Error";
    }

    /**
     * Extracts the "params" value from the YouTube video page.
     *
     * @param VIDEO_URL the YouTube video URL
     * @return the extracted "params" value
     */
    public String getParams(String VIDEO_URL) {
        // Step 1: Get the HTML from the video URL
        Response getResponse = RestAssured.given().when().get(VIDEO_URL);
        String html = BrResponseToJSONStr(getResponse);

        // Step 2: Use regex to extract the "params" value
        String regex = "\"getTranscriptEndpoint\"\\s*:\\s*\\{.*?\"params\"\\s*:\\s*\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            // Extract and return the value of "params"
            return matcher.group(1);
        } else {
            System.err.println("No 'params' key found in the response.");
            return null;
        }
    }

    /**
     * Main method to run the ParamCatcher class.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        paramcatcher param = new paramcatcher();
        param.fetchurl();
        // param.getParams();
    }

    public String fetchurl() {
        // Use Scanner to get the video path from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the YouTube video path (e.g., S5v15H-kr5M): ");
        String videoPath = scanner.nextLine();
        scanner.close();

        // Prefix the base URL
        VIDEO_URL = "https://www.youtube.com/watch?v=" + videoPath;

        // Validate user input
        if (videoPath.isBlank()) {
            System.err.println("Invalid input. Please provide a valid YouTube video path.");

        }

        System.out.println("Processing URL: " + VIDEO_URL);

        // Extract the "params" value
        return VIDEO_URL;
    }

}
