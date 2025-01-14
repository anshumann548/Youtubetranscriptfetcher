package com.transcript.fetch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.brotli.dec.BrotliInputStream;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class paramcatcher {

    // Constants
    public static final String VIDEO_URL = "https://www.youtube.com/watch?v=S5v15H-kr5M";

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
     * @return the extracted "params" value
     */
    public static String getParams() {
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
     * Main method to run the paramcatcher class.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String params = getParams();
        if (params != null) {
            System.out.println("Extracted params: " + params);
        } else {
            System.err.println("Failed to extract 'params'.");
        }
    }
}
