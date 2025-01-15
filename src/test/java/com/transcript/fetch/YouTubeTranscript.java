package com.transcript.fetch;

import org.testng.annotations.Test;

public class YouTubeTranscript {

    @Test
    public void YouTubeTranscript() {
        YouTubeTranscriptFetcher executor = new YouTubeTranscriptFetcher();
        executor.runner();

    }
}
