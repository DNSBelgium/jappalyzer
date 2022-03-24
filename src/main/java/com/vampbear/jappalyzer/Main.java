package com.vampbear.jappalyzer;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static boolean verbose = false;
    private static boolean latest = false;

    public static void main(String[] args) {
        long startTimestamp = System.currentTimeMillis();
        final Options options = new Options();
        options.addOption("v", "verbose", false, "add verbose information to output");
        options.addOption("l", "latest", false, "load latest technologies data");
        CommandLineParser argsParser = new DefaultParser();
        try {
            CommandLine cmd = argsParser.parse(options, args);
            if (cmd.hasOption("verbose")) {
                verbose = true;
            }
            if (cmd.hasOption("latest")) {
                latest = true;
            }
            List<String> argList = cmd.getArgList();
            for (String arg : argList) {
                processLink(arg, cmd);
            }
            long duration = System.currentTimeMillis() - startTimestamp;
            if (verbose) System.out.println("Processing time: " + duration + "ms");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void processLink(String url, CommandLine cmd) {
        if (verbose) System.out.println("Processing URL: " + url);
        try {
            if (verbose) System.out.println("Loading technology data");

            Jappalyzer jappalyzer;
            if (latest) {
                if (verbose) System.out.println("Loading latest technologies data");
                jappalyzer = Jappalyzer.latest();
            } else {
                jappalyzer = Jappalyzer.create();
            }

            List<Technology> instanceTechnologies = jappalyzer.getTechnologies();
            if (verbose) System.out.println("Known technologies size: " + instanceTechnologies.size());
            HttpClient httpClient = new HttpClient();
            long loadingPageStartTimestamp = System.currentTimeMillis();
            PageResponse pageResponse = httpClient.getPageByUrl(url);
            long duration = System.currentTimeMillis() - loadingPageStartTimestamp;
            if (verbose) System.out.println("Page loaded with " + duration + "ms");
            Set<TechnologyMatch> foundTechs = jappalyzer.fromPageResponse(pageResponse);
            System.out.println("Technologies:");
            for (TechnologyMatch match : foundTechs) {
                System.out.println(getTechnologyMatchString(match));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTechnologyMatchString(TechnologyMatch technologyMatch) {
        StringBuilder sb = new StringBuilder();
        sb.append("* ");
        sb.append(technologyMatch.getTechnology().getName());
        if (!technologyMatch.getVersion().isEmpty()) {
            sb.append(" (").append(technologyMatch.getVersion()).append(")");
        }
        List<String> categoriesNames = technologyMatch.getTechnology().getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        sb.append(" [").append(String.join(", ", categoriesNames)).append("]");
        return sb.toString();
    }
}
