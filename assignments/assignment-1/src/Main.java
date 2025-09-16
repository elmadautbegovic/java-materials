import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws ParseException {
        File f = new File("tmdb_data.csv");
        Scanner scanner;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        scanner.nextLine(); // Skip header row

        List<TVShow> cleanedShows = new ArrayList<>();
        List<String> brokenShows = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = parseCsvLine(line);
            if (parts.length == 10 && !parts[3].equals("0")) {
                try {
                    cleanedShows.add(new TVShow(
                            Integer.parseInt(parts[0]),
                            parts[1], parts[2],
                            Integer.parseInt(parts[3].replaceAll("\"", "").replaceAll(",", "")),
                            Double.parseDouble(parts[4].replaceAll("\"", "").replace(",", ".")),
                            parts[5], parts[6],
                            Boolean.parseBoolean(parts[7]),
                            Double.parseDouble(parts[8].replaceAll("\"", "").replace(",", ".")),
                            parts[9]
                    ));
                } catch (Exception e) {
                    brokenShows.add(line);
                }
            } else {
                brokenShows.add(line);
            }
        }

        writeToFile("cleanShows.csv", cleanedShows);
        writeToFile("brokenShows.csv", brokenShows);

        // Task 1: Top 50 shows by rating
        List<TVShow> topByRating = cleanedShows.stream()
                .filter(show -> show.getVoteCount() > 99)
                .sorted(Comparator.comparing(TVShow::getVoteAverage).reversed())
                .limit(50)
                .collect(Collectors.toList());
        writeToFile("task1.csv", topByRating);

        // Task 2: Top 50 shows by rating and language
        Map<String, List<TVShow>> byLanguage = cleanedShows.stream()
                .collect(Collectors.groupingBy(TVShow::getOglang, Collectors.toList()));
        byLanguage.forEach((lang, shows) -> {
            shows.sort(Comparator.comparing(TVShow::getVoteAverage).reversed());
            writeToFile("task2." + lang + ".csv", shows.subList(0, Math.min(50, shows.size())));
        });

        // Task 3: Total shows with vote count over 15,000
        long showsWithVoteOver15000 = cleanedShows.stream()
                .filter(show -> show.getVoteCount() > 15000)
                .count();
        writeToFile("task3.csv", List.of("Number of shows with vote count over 15000: " + showsWithVoteOver15000));

        // Task 4: Top 50 shows between 2010-2019
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2010-01-01");
        Date endDate = sdf.parse("2019-12-31");
        List<TVShow> topBetween2010And2019 = cleanedShows.stream()
                .filter(show -> {
                    try {
                        Date firstAir = sdf.parse(show.getFirstAir());
                        Date lastAir = sdf.parse(show.getLastAir());
                        return !firstAir.before(startDate) && !lastAir.after(endDate);
                    } catch (ParseException e) {
                        return false;
                    }
                })
                .sorted(Comparator.comparing(TVShow::getVoteAverage).reversed())
                .limit(50)
                .collect(Collectors.toList());
        writeToFile("task4.csv", topBetween2010And2019);

        // Task 5: Top 50 currently in production shows
        List<TVShow> inProductionShows = cleanedShows.stream()
                .filter(TVShow::getInProduction)
                .sorted(Comparator.comparing(TVShow::getVoteAverage).reversed())
                .limit(50)
                .collect(Collectors.toList());
        writeToFile("task5.csv", inProductionShows);

        // Task 6: Top 50 single-word titled shows by popularity
        List<TVShow> singleWordShows = cleanedShows.stream()
                .filter(show -> !show.getName().contains(" "))
                .sorted(Comparator.comparing(TVShow::getPopularity).reversed())
                .limit(50)
                .collect(Collectors.toList());
        writeToFile("task6.csv", singleWordShows);

        // Task 7: Top 50 shows per genre
        Map<String, List<TVShow>> byGenre = cleanedShows.stream()
                .flatMap(show -> show.getGenres().stream().map(genre -> new AbstractMap.SimpleEntry<>(genre, show)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
        byGenre.forEach((genre, shows) -> {
            shows.sort(Comparator.comparing(TVShow::getVoteAverage).reversed());
            writeToFile("task7_" + sanitizeGenre(genre) + ".csv", shows.subList(0, Math.min(50, shows.size())));
        });
    }

    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?=,|$)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            if (matcher.group(1) != null) fields.add(matcher.group(1));
            else fields.add(matcher.group(2));
        }
        return fields.toArray(new String[0]);
    }

    private static void writeToFile(String filename, List<?> data) {
        try (FileWriter fw = new FileWriter(filename)) {
            for (Object item : data) {
                fw.write(item.toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error writing " + filename + ": " + e.getMessage());
        }
    }

    private static String sanitizeGenre(String genre) {
        return genre.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}