package com.transcript.fetch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.brotli.dec.BrotliInputStream;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class YouTubeTranscriptFetcher {

    // Class-level variables
    private static final String API_URL = "https://www.youtube.com/youtubei/v1/get_transcript?prettyPrint=false";

    // Request headers using HashMap to support more than 10 key-value pairs
    private static final Map<String, String> HEADERS = new HashMap<String, String>() {
        {
            put("Sec-Ch-Ua", "\"Chromium\";v=\"123\", \"Not:A-Brand\";v=\"8\"");
            put("Sec-Ch-Ua-Platform", "Linux");
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.6312.122 Safari/537.36");
            put("Content-Type", "application/json");
            put("X-Youtube-Client-Name", "1");
            put("X-Youtube-Client-Version", "2.20250108.06.00");
            put("Accept", "*/*");
            put("Origin", "https://www.youtube.com");
            put("Accept-Encoding", "gzip, deflate, br");
            put("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8");
            put("Priority", "u=1, i");
            put("referer", "REFERER_URL");
        }
    };

    public void runner() {
        paramcatcher param = new paramcatcher();
        String vurl = param.fetchurl();
        String dynamicparams = param.getParams(vurl);

        // String REFERER_URL = vurl;
        String body = "{\n  \"context\": {\n    \"client\": {\n      \"hl\": \"en\",\n      \"gl\": \"IN\",\n      \"remoteHost\": \"112.196.62.7\",\n      \"deviceMake\": \"\",\n      \"deviceModel\": \"\",\n      \"visitorData\": \"CgtaYlNFU2ZfaWh5NCjH7JO8BjIKCgJJThIEGgAgTQ%3D%3D\",\n      \"userAgent\": \"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36,gzip(gfe)\",\n      \"clientName\": \"WEB\",\n      \"clientVersion\": \"2.20250108.06.00\",\n      \"osName\": \"X11\",\n      \"osVersion\": \"\",\n      \"originalUrl\": \"\",\n      \"platform\": \"DESKTOP\",\n      \"clientFormFactor\": \"UNKNOWN_FORM_FACTOR\",\n      \"configInfo\": {\n        \"appInstallData\": \"CMfsk7wGEMjYsQUQ37TOHBCrns4cEKKjzhwQrsHOHBDRlM4cEMn3rwUQ3q2xBRC3768FEIvUsQUQwc2xBRCNzLAFEIPDsQUQxNixBRCSuM4cEI7XsQUQt6TOHBDTuc4cEObPsQUQgcOxBRDB2v8SENfBsQUQotSxBRD4q7EFEMrCzhwQ0-GvBRCKobEFEMbYsQUQytSxBRCazrEFEOW5sQUQj8OxBRC36v4SEMvRsQUQ4M2xBRDCt84cEN68zhwQgdaxBRCM0LEFEKiasAUQgL3OHBCZjbEFEPyyzhwQ6-j-EhDJ5rAFEK_CzhwQlP6wBRDM364FEKaasAUQ8ZywBRDnqM4cEOLUrgUQvZmwBRDalM4cEMa_sQUQnaawBRCHw7EFEO25sQUQ6sOvBRCO0LEFEKbAzhwQvsLOHBDQjbAFEInorgUQjdSxBRDZqs4cEIiHsAUQwLfOHBDK2LEFEIS9zhwQwavOHBD6uM4cENuvrwUQveawBRDnms4cEJnS_xIQiOOvBRC9tq4FEJLLsQUQhaexBRDN0bEFEKaTsQUQntCwBRC9irAFEParsAUQwcLOHBCUu84cEOHssAUQmZixBSosQ0FNU0d4VVFvTDJ3RE5Ia0J2UHQ4UXVQOUE2b0RPRnkxbnloX3dRZEJ3PT0%3D\",\n        \"coldConfigData\": \"CMfsk7wGGjJBT2pGb3gyMVozdlA1c2FsWXpwelVYaG4xY216VEtpWFg3c1I3TFZobnBtd2l2Vlk2USIyQU9qRm94MjFaM3ZQNXNhbFl6cHpVWGhuMWNtelRLaVhYN3NSN0xWaG5wbXdpdlZZNlE%3D\",\n        \"coldHashData\": \"CMfsk7wGEhQxMDU2MzU5Mjc4Mjg3MzI3MTYxMRjH7JO8BjIyQU9qRm94MjFaM3ZQNXNhbFl6cHpVWGhuMWNtelRLaVhYN3NSN0xWaG5wbXdpdlZZNlE6MkFPakZveDIxWjN2UDVzYWxZenB6VVhobjFjbXpUS2lYWDdzUjdMVmhucG13aXZWWTZR\",\n        \"hotHashData\": \"CMfsk7wGEhQxMTk5MTE1OTk3MTg4MzgxMTg5ORjH7JO8BjIyQU9qRm94MjFaM3ZQNXNhbFl6cHpVWGhuMWNtelRLaVhYN3NSN0xWaG5wbXdpdlZZNlE6MkFPakZveDIxWjN2UDVzYWxZenB6VVhobjFjbXpUS2lYWDdzUjdMVmhucG13aXZWWTZR\"\n      },\n      \"userInterfaceTheme\": \"USER_INTERFACE_THEME_LIGHT\",\n      \"timeZone\": \"Asia/Calcutta\",\n      \"browserName\": \"Chrome\",\n      \"browserVersion\": \"131.0.0.0\",\n      \"acceptHeader\": \"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\",\n      \"deviceExperimentId\": \"ChxOelExT1RNMU56WTJPVEF6T0RJNU1EZ3pOZz09EMfsk7wGGMfsk7wG\",\n      \"rolloutToken\": \"\",\n      \"screenWidthPoints\": 960,\n      \"screenHeightPoints\": 351,\n      \"screenPixelDensity\": 1,\n      \"screenDensityFloat\": 1,\n      \"utcOffsetMinutes\": 330,\n      \"connectionType\": \"CONN_CELLULAR_4G\",\n      \"memoryTotalKbytes\": \"8000000\",\n      \"mainAppWebInfo\": {\n        \"graftUrl\": \"\",\n        \"pwaInstallabilityStatus\": \"PWA_INSTALLABILITY_STATUS_UNKNOWN\",\n        \"webDisplayMode\": \"WEB_DISPLAY_MODE_BROWSER\",\n        \"isWebNativeShareAvailable\": false\n      }\n    },\n    \"user\": {\n      \"lockedSafetyMode\": false\n    },\n    \"request\": {\n      \"useSsl\": true,\n      \n      \"internalExperimentFlags\": []\n    },\n    \"clickTracking\": {\n      \"clickTrackingParams\": \"CBEQ040EGAYiEwiKt8e3yfKKAxUN36ACHdn5MZk=\"\n    },\n    \"adSignalsInfo\": {\n    \n      \"bid\": \"\"\n    }\n  },\n  \"params\": \"" + dynamicparams + "\",\n  \"languageCode\": \"en\"\n}";

        // Set up RestAssured
        RestAssured.baseURI = API_URL;

        RequestSpecification request = RestAssured.given()
                .headers(HEADERS)
                .body(body);

        // Send POST request and get the response
        Response response = request.post();

        // Check Content-Encoding to determine if Brotli decompression is needed
        String contentEncoding = response.getHeader("Content-Encoding");
        if ("br".equals(contentEncoding)) {
            // Handle Brotli compression
            byte[] compressedData = response.getBody().asByteArray();
            try (BrotliInputStream brotliInputStream = new BrotliInputStream(new ByteArrayInputStream(compressedData))) {
                String decompressedBody = new String(brotliInputStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(decompressedBody);
            } catch (IOException e) {
            }
        } else {
            // If no compression, just print the body directly
            System.out.println(new String(response.getBody().asByteArray(), StandardCharsets.UTF_8));
        }
    }

    public static void main(String[] args) {
        YouTubeTranscriptFetcher fetcher = new YouTubeTranscriptFetcher();

        fetcher.runner();
    }
}
