package com.health.fitness;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import com.health.fitness.HealthIndexScore.FitnessLevel;

public class HealthIndexScoreParameterizedTest {

    @BeforeAll
    public static void setUpHeader() {
        System.out.println("\n--- Copy this table to Excel ---");
        System.out.println(
                "TC ID\tVO2 Max\tRHR\tHRR\tExpected Score\tActual Score\tExpected Level\tActual Level\tStatus");
    }

    @ParameterizedTest(name = "{0} -> VO2Max:{1}, RHR:{2}, HRR:{3} | Expected: {4}({5})")
    @CsvSource({
            // Test Case, VO2Max, RHR, HRR, ExpectedResult, ExpectedScore (ถ้า Out of Range
            // ใส่ -1 ไว้)
            "TC001,       -1.0,   72,   15,  STANDARD,       6",
            "TC002,        0.0,   72,   15,  STANDARD,       6",
            "TC003,        1.0,   72,   15,  STANDARD,       6",
            "TC004,       35.0,   72,   15,  STANDARD,       8",
            "TC005,       79.0,   72,   15,  STANDARD,      11",
            "TC006,       80.0,   72,   15,  STANDARD,      11",
            "TC007,       81.0,   72,   15,  STANDARD,      11",
            "TC008,       35.0,   39,   15,  Out of Range,  -1",
            "TC009,       35.0,   40,   15,  STANDARD,      10",
            "TC010,       35.0,   41,   15,  STANDARD,      10",
            "TC011,       35.0,  219,   15,  STANDARD,       6",
            "TC012,       35.0,  220,   15,  STANDARD,       6",
            "TC013,       35.0,  221,   15,  Out of Range,  -1",
            "TC014,       35.0,   72,   -1,  STANDARD,       6",
            "TC015,       35.0,   72,    0,  STANDARD,       6",
            "TC016,       35.0,   72,    1,  STANDARD,       6",
            "TC017,       35.0,   72,   29,  STANDARD,      10",
            "TC018,       35.0,   72,   30,  STANDARD,      10",
            "TC019,       35.0,   72,   31,  STANDARD,      10"
    })
    void testHealthIndexScoreTable(String tcId, double vo2Max, int rhr, int hrr, String expectedResult,
            int expectedScore) {
        String actualResult = "";
        int actualScore = -1;
        String status = "FAIL";

        try {
            if ("Out of Range".equals(expectedResult)) {
                assertThrows(IllegalArgumentException.class, () -> {
                    new HealthIndexScore(vo2Max, rhr, hrr);
                });
                actualResult = "Out of Range";
                actualScore = -1;
            } else {
                HealthIndexScore score = new HealthIndexScore(vo2Max, rhr, hrr);
                actualResult = score.getFitnessLevel().name();
                actualScore = score.getTotalScore();

                assertEquals(FitnessLevel.valueOf(expectedResult), score.getFitnessLevel());
                assertEquals(expectedScore, score.getTotalScore());
            }
            status = "PASS";
        } catch (AssertionError | Exception e) {
            status = "FAIL";
            if (actualResult.isEmpty()) {
                actualResult = e.getClass().getSimpleName();
            }
            throw e;
        } finally {
            String expScoreStr = expectedScore == -1 ? "N/A" : String.valueOf(expectedScore);
            String actScoreStr = actualScore == -1 ? "N/A" : String.valueOf(actualScore);
            System.out.printf("%s\t%.1f\t%d\t%d\t%s\t%s\t%s\t%s\t%s\n",
                    tcId, vo2Max, rhr, hrr, expScoreStr, actScoreStr, expectedResult, actualResult, status);
        }
    }
}