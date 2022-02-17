package com.vampbear.jappalyzer;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jappalyzer {

    private List<Technology> technologies = new LinkedList<>();

    public static void main(String[] args) {
        List<Technology> technologies = loadTechnologies();
        System.out.println("Count: " + technologies.size());
        try {
            Jappalyzer jappalyzer = Jappalyzer.latest();
            List<Technology> instanceTechnologies = jappalyzer.getTechnologies();
            System.out.println("Instance techs: " + instanceTechnologies.size());
            List<Technology> foundTechs = jappalyzer.fromUrl("https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/");
            foundTechs.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Technology> getTechnologies() {
        return this.technologies;
    }

    private static Jappalyzer latest() {
        Jappalyzer jappalyzer = new Jappalyzer();
        jappalyzer.updateTechnologies();
        return jappalyzer;
    }

    private void updateTechnologies() {
        HttpClient httpClient = new HttpClient();
        String[] keys = new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "_"};
        try {
            for (String key : keys) {
                String techGithubUrl = String.format("https://raw.githubusercontent.com/AliasIO/wappalyzer/master/src/technologies/%s.json", key);
                String techContent = httpClient.getUrlContent(techGithubUrl);
                this.technologies.addAll(readTechnologiesFromString(techContent));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Technology> fromFile(String path) {
        List<Technology> foundTechs = new ArrayList<>();
        List<Technology> technologies = loadTechnologies();

        String fileContent = readFileContent(path);
        PageResponse pageResponse = new PageResponse(fileContent);

        for (Technology technology : technologies) {
            if (technology.appliebleTo(pageResponse)) {
                foundTechs.add(technology);
            }
        }

        return foundTechs;
    }

    public List<Technology> fromUrl(String url) throws IOException {
        List<Technology> foundTechs = new ArrayList<>();
        HttpClient httpClient = new HttpClient();
        String content = httpClient.getUrlContent(url);
        PageResponse pageResponse = new PageResponse(content);
        System.out.println("Content size: " + content.length());
        for (Technology technology : technologies) {
            if (technology.appliebleTo(pageResponse)) {
                foundTechs.add(technology);
            }
        }
        return foundTechs;
    }

    private static String readFileContent(String path) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static List<Technology> loadTechnologies() {
        try (Stream<Path> filesStream = Files.list(Paths.get("src/main/resources/technologies"))) {
            return filesStream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Jappalyzer::readTechnologiesFromFile)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private static List<Technology> readTechnologiesFromFile(Path file) {
        List<Technology> technologies = new LinkedList<>();
        try {
            byte[] content = Files.readAllBytes(file);
            JSONObject fileJSON = new JSONObject(new String(content));
            for (String key : fileJSON.keySet()) {
                JSONObject object = (JSONObject) fileJSON.get(key);
                technologies.add(convertToTechnology(key, object));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return technologies;
    }

    private static List<Technology> readTechnologiesFromString(String technologiesString) {
        List<Technology> technologies = new LinkedList<>();
        JSONObject fileJSON = new JSONObject(technologiesString);
        for (String key : fileJSON.keySet()) {
            JSONObject object = (JSONObject) fileJSON.get(key);
            technologies.add(convertToTechnology(key, object));
        }
        return technologies;
    }

    private static Technology convertToTechnology(String key, JSONObject object) {
        String description = "";
        if (object.has("description")) {
            description = object.getString("description");
        }
        Technology technology = new Technology(key, description);
        List<String> htmlTemplates = readValuesByKey(object, "html");
        for (String template : htmlTemplates) {
            technology.addHtmlTemplate(template);
        }
        List<String> domTemplates = readValuesByKey(object, "dom");
        for (String template : domTemplates) {
            technology.addDomTemplate(template);
        }
        List<String> scriptSrcTemplates = readValuesByKey(object, "scriptSrc");
        for (String template : scriptSrcTemplates) {
            technology.addScriptSrc(template);
        }
        return technology;
    }

    private static List<String> readValuesByKey(JSONObject object, String key) {
        List<String> values = new ArrayList<>();
        if (object.has(key)) {
            if (object.get(key) instanceof String) {
                values.add(object.getString(key));
            } else if (object.get(key) instanceof JSONArray) {
                JSONArray templates = object.getJSONArray(key);
                for (Object item : templates) {
                    if (item instanceof String) {
                        values.add((String) item);
                    }
                }
            }
        }
        return values;
    }
}
