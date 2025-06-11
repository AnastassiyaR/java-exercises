package ee.taltech.iti0202.detective.crimecase;

import ee.taltech.iti0202.detective.evidence.Evidence;
import ee.taltech.iti0202.detective.suspect.Suspect;

import java.util.List;

public record CrimeCase(Integer caseId, String title, String description,
                        List<Suspect> suspects, List<Evidence> evidence) {
}
