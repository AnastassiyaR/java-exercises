package ee.taltech.iti0202.detective;

import ee.taltech.iti0202.detective.crimecase.CrimeCase;
import ee.taltech.iti0202.detective.evidence.Evidence;
import ee.taltech.iti0202.detective.jsonloader.JsonLoader;
import ee.taltech.iti0202.detective.suspect.Suspect;

import java.util.List;
import java.util.stream.Collectors;

public class Investigation {
    private List<CrimeCase> crimeCases;

    /**
     *
     * @param jsonFilePath
     */
    public Investigation(String jsonFilePath) {
        this.crimeCases = JsonLoader.loadCases(jsonFilePath);
    }

    /**
     * Retrieves details of all crime cases.
     *
     * @return A formatted string listing all crime cases with their suspects and evidence.
     */
    public String allCases() {
        if (crimeCases.isEmpty()) return "No cases found";

        return crimeCases.stream()
                // Для каждого crimeCase, создай из него новую строку (или другой объект)
                // То есть выводит РАЗНЫЕ стринги
                .map(crimeCase -> {
                    // String because you make already to string, like add to ""
                    String suspects = crimeCase.suspects().isEmpty()
                            ? ""
                            // BECAUSE OF THIS
                            : String.join(", ",
                            crimeCase.suspects().stream()
                                    .map(Suspect::name)
                                    .toList());

                    String evidence = crimeCase.evidence().isEmpty()
                            ? ""
                            : String.join(", ", crimeCase.evidence().stream()
                                    .map(Evidence::type)
                                    .toList());

                    return String.format(
                            "Case ID: %d\nTitle: %s\nDescription: %s\nSuspects: %s\nEvidence: %s",
                            crimeCase.caseId(),
                            crimeCase.title(),
                            crimeCase.description(),
                            suspects,
                            evidence
                    );
                })

                // For string
                .collect(Collectors.joining("\n"));
    }

    /**
     * Searches for a suspect by name.
     *
     * @param suspectName The name of the suspect to search for.
     * @return A formatted string with suspect details if found, otherwise a "No suspect found" message.
     */
    public String searchSuspect(String suspectName) {
        String searchName = suspectName.trim().toLowerCase();

        return crimeCases.stream()
                // Flatten the list of suspects from all crime cases
                .flatMap(crimeCase -> crimeCase.suspects().stream())

                .filter(suspect -> suspect.name().toLowerCase().trim().equals(searchName))
                .map(suspect -> String.format("Name: %s\nAge: %d\nAlibi: %s\nCriminal Record: %s\nMotive: %s",
                        suspect.name(),
                        suspect.age(),
                        suspect.alibi(),
                        suspect.criminalRecord() ? "Jah" : "Ei",
                        suspect.motive()))
                .findFirst()
                .orElse("No suspect found");
    }

    /**
     * Finds the prime suspects for a given case based on their criminal record and alibi.
     *
     * @param caseId The ID of the crime case to check.
     * @return A formatted string with the prime suspect(s) for the case, or a message if none are found.
     */
    public String findPrimeSuspects(int caseId) {

        return crimeCases.stream()
                .filter(crimeCase -> crimeCase.caseId().equals(caseId))
                .findFirst()
                .map(crimeCase -> {
                    List<Suspect> primeSuspects = crimeCase.suspects().stream()
                            .filter(suspect -> suspect.criminalRecord()
                                    && suspect.alibi().equalsIgnoreCase("Puudub"))
                            .toList();

                    if (primeSuspects.isEmpty()) {
                        return "No prime suspects found for case with ID: " + caseId;
                    }


                    StringBuilder result = new StringBuilder("Prime suspect");
                    if (primeSuspects.size() > 1) {
                        result.append("s");
                    }
                    result.append(" in case: ").append(caseId).append("\n");

                    for (Suspect suspect : primeSuspects) {
                        result.append("Name: ").append(suspect.name()).append("\n")
                                .append("Age: ").append(suspect.age()).append("\n")
                                .append("Alibi: ").append(suspect.alibi()).append("\n")
                                .append("Criminal Record: Jah\n")
                                .append("Motive: ").append(suspect.motive()).append("\n");
                    }
                    return result.toString().trim();
                })
                .orElse("No prime suspects found for case with ID: " + caseId);
    }

    // Second funny version :D
//    public String findPrimeSuspects(int caseId) {
//
//        return crimeCases.stream()
//                .filter(crimeCase -> crimeCase.caseId().equals(caseId))
//                .findFirst()
//                .map(crimeCase -> {
//                    String primeSuspects = crimeCase.suspects().stream()
//                            .filter(suspect -> suspect.criminalRecord()
//                                    && suspect.alibi().equalsIgnoreCase("Puudub"))
//                            .map(suspect -> String.format(
//                            "Name: %s\nAge: %d\nAlibi: %s\nCriminal Record: Jah\nMotive: %s\n",
//                                    suspect.name(),
//                                    suspect.age(),
//                                    suspect.alibi(),
//                                    suspect.motive()))
//                            .collect(Collectors.joining());
//
//                    int nameCount = primeSuspects.split("Name").length - 1;
//
//                    if (primeSuspects.isEmpty()) {
//                        return "No prime suspects found for case with ID: " + caseId;
//                    }
//
//                    return "Prime suspect"
//                            + ((nameCount > 1) ? "s" : "")
//                            + " in case: " + caseId
//                            + "\n" + primeSuspects.trim();
//
//                })
//                .orElse("No prime suspects found for case with ID: " + caseId);
//    }

    /**
     * Attempts to solve each case by finding the most likely suspect based on available evidence.
     *
     * @return A formatted string with the most likely suspect for each case, if one is identified.
     */
    public String solveCase()  {
        StringBuilder result = new StringBuilder();

        for (CrimeCase crimeCase : crimeCases) {
            result.append("Case: ").append(crimeCase.title()).append("\n");

            List<Suspect> suspects = crimeCase.suspects();
            List<Evidence> evidences = crimeCase.evidence();

            if (suspects.isEmpty()) {
                result.append("No suspects available");
                continue;
            }

            Suspect bestSuspect = null;
            int highestScore = -1;

            for (Suspect suspect : suspects) {
                int score = 0;

                if (suspect.criminalRecord()) {
                    score += 5;
                }

                if (suspect.alibi().equalsIgnoreCase("puudub")) {
                    score += 4;
                }

                if (!suspect.motive().isBlank()) {
                    score += 2;
                }

                boolean mentionedInEvidence = evidences.stream()
                        .anyMatch(evidence ->
                                evidence.foundAt().toLowerCase().contains(suspect.name().toLowerCase()));
                if (mentionedInEvidence) {
                    score += 3;
                }

                if (score > highestScore) {
                    highestScore = score;
                    bestSuspect = suspect;
                }
            }

            if (bestSuspect != null) {
                result.append("Most likely suspect: ").append(bestSuspect.name()).append("\n");
                result.append("Crime likelihood score: ").append(highestScore).append("\n");
                result.append("Motive: ").append(bestSuspect.motive()).append("\n");
            } else {
                result.append("No likely suspect found").append("\n");
            }
        }

        return result.toString().trim();
    }


    public static void main(String[] args) {
        String jsonFilePath = "EX/EX09Detective/src/ee/taltech/iti0202/detective/cases.json";
        Investigation investigation = new Investigation(jsonFilePath);

        System.out.println("All Cases:");
        System.out.println(investigation.allCases());

        System.out.println("\nSearching for suspect:");
        System.out.println(investigation.searchSuspect("Mailis Reps"));

        System.out.println("\nFinding prime suspects:");
        System.out.println(investigation.findPrimeSuspects(1));

        System.out.println("\nSolving cases:");
        System.out.println(investigation.solveCase());
    }
}
