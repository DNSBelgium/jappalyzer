package com.vampbear.jappalyzer;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageResponse {

    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final Document document;
    private final String origContent;

    public PageResponse(String content) {
        this(200, Collections.emptyMap(), content);
    }

    public PageResponse(int statusCode, Map<String, List<String>> headers, String content) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.origContent = content;
        this.document = Jsoup.parse(content);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getOrigContent() {
        return origContent;
    }

    public Document getDocument() {
        return document;
    }
}
