package com.health.fitness;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthIndexScoreTest {

    // ==========================================
    // 1. การทดสอบ VO2 Max Score (Boundary Values)
    // ==========================================

    @Test
    @DisplayName("VO2 Max < 25 คะแนนควรได้ 0")
    public void testVo2Max_VeryPoor_Boundary() {
        // ขอบบนของช่วง < 25 (ใช้ค่าทศนิยมที่เข้าใกล้ 25 หรือค่าทดสอบทั่วไป)
        HealthIndexScore health = new HealthIndexScore(24.9, 60, 15);
        assertEquals(0, health.calculateVo2MaxScore());
    }

    @Test
    @DisplayName("VO2 Max ช่วง 25 ถึง 30 คะแนนควรได้ 1")
    public void testVo2Max_Poor_Boundary() {
        // ขอบล่าง (25) และ ขอบบน (30)
        HealthIndexScore healthMin = new HealthIndexScore(25.0, 60, 15);
        HealthIndexScore healthMax = new HealthIndexScore(30.0, 60, 15);

        assertEquals(1, healthMin.calculateVo2MaxScore());
        assertEquals(1, healthMax.calculateVo2MaxScore());
    }

    @Test
    @DisplayName("VO2 Max ช่วง 31 ถึง 40 คะแนนควรได้ 2")
    public void testVo2Max_Normal_Boundary() {
        // ขอบล่าง (31) และ ขอบบน (40)
        HealthIndexScore healthMin = new HealthIndexScore(31.0, 60, 15);
        HealthIndexScore healthMax = new HealthIndexScore(40.0, 60, 15);

        assertEquals(2, healthMin.calculateVo2MaxScore());
        assertEquals(2, healthMax.calculateVo2MaxScore());
    }

    @Test
    @DisplayName("VO2 Max ช่วง 41 ถึง 50 คะแนนควรได้ 3")
    public void testVo2Max_Good_Boundary() {
        // ขอบล่าง (41) และ ขอบบน (50)
        HealthIndexScore healthMin = new HealthIndexScore(41.0, 60, 15);
        HealthIndexScore healthMax = new HealthIndexScore(50.0, 60, 15);

        assertEquals(3, healthMin.calculateVo2MaxScore());
        assertEquals(3, healthMax.calculateVo2MaxScore());
    }

    @Test
    @DisplayName("VO2 Max ช่วง 51 ถึง 60 คะแนนควรได้ 4")
    public void testVo2Max_VeryGood_Boundary() {
        // ขอบล่าง (51) และ ขอบบน (60)
        HealthIndexScore healthMin = new HealthIndexScore(51.0, 60, 15);
        HealthIndexScore healthMax = new HealthIndexScore(60.0, 60, 15);

        assertEquals(4, healthMin.calculateVo2MaxScore());
        assertEquals(4, healthMax.calculateVo2MaxScore());
    }

    @Test
    @DisplayName("VO2 Max > 60 คะแนนควรได้ 5")
    public void testVo2Max_Athlete_Boundary() {
        // ขอบล่างของช่วงถัดไปคือค่าที่มากกว่า 60
        HealthIndexScore health = new HealthIndexScore(60.1, 60, 15);
        assertEquals(5, health.calculateVo2MaxScore());
    }

    // ==========================================
    // 2. การทดสอบ Resting Heart Rate (RHR) Score
    // ==========================================

    @Test
    @DisplayName("RHR ช่วง 40 ถึง 60 (Excellent) คะแนนควรได้ 5")
    public void testRhr_Excellent_Boundary() {
        // ขอบล่าง (40) และ ขอบบน (60)
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 40, 15);
        HealthIndexScore healthMax = new HealthIndexScore(35.0, 60, 15);

        assertEquals(5, healthMin.calculateRhrScore());
        assertEquals(5, healthMax.calculateRhrScore());
    }

    @Test
    @DisplayName("RHR ช่วง 61 ถึง 84 (Normal) คะแนนควรได้ 3")
    public void testRhr_Normal_Boundary() {
        // ขอบล่าง (61) และ ขอบบน (84)
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 61, 15);
        HealthIndexScore healthMax = new HealthIndexScore(35.0, 84, 15);

        assertEquals(3, healthMin.calculateRhrScore());
        assertEquals(3, healthMax.calculateRhrScore());
    }

    @Test
    @DisplayName("RHR ช่วง 85 ถึง 220 (Poor) คะแนนควรได้ 1")
    public void testRhr_Poor_Boundary() {
        // ขอบล่าง (85) และ ขอบบนสุดที่ระบบยอมรับปกติ (220)
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 85, 15);
        HealthIndexScore healthMax = new HealthIndexScore(35.0, 220, 15);

        assertEquals(1, healthMin.calculateRhrScore());
        assertEquals(1, healthMax.calculateRhrScore());
    }

    // ==========================================
    // 3. การทดสอบ Heart Rate Recovery (HRR) Score
    // ==========================================

    @Test
    @DisplayName("HRR < 12 คะแนนควรได้ 1")
    public void testHrr_Poor_Boundary() {
        // ขอบบนของช่วงคือ 11
        HealthIndexScore health = new HealthIndexScore(35.0, 60, 11);
        assertEquals(1, health.calculateHrrScore());
    }

    @Test
    @DisplayName("HRR ช่วง 12 ถึง 18 คะแนนควรได้ 3")
    public void testHrr_Good_Boundary() {
        // ขอบล่าง (12) และ ขอบบน (18)
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 60, 12);
        HealthIndexScore healthMax = new HealthIndexScore(35.0, 60, 18);

        assertEquals(3, healthMin.calculateHrrScore());
        assertEquals(3, healthMax.calculateHrrScore());
    }

    @Test
    @DisplayName("HRR ช่วง 19 ถึง 24 คะแนนควรได้ 4")
    public void testHrr_VeryGood_Boundary() {
        // ขอบล่าง (19) และ ขอบบน (24)
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 60, 19);
        HealthIndexScore healthMax = new HealthIndexScore(35.0, 60, 24);

        assertEquals(4, healthMin.calculateHrrScore());
        assertEquals(4, healthMax.calculateHrrScore());
    }

    @Test
    @DisplayName("HRR >= 25 คะแนนควรได้ 5")
    public void testHrr_Athlete_Boundary() {
        // ขอบล่างคือ 25
        HealthIndexScore health = new HealthIndexScore(35.0, 60, 25);
        assertEquals(5, health.calculateHrrScore());
    }

    // ==========================================
    // 4. การทดสอบ Fitness Level สรุปรวม
    // ==========================================

    @Test
    @DisplayName("Total Score รวมได้ขอบล่างสุดและบนสุดของ Standard (6-11) ต้องได้เกณฑ์ STANDARD")
    public void testFitnessLevel_Standard_Boundary() {
        // ต่ำสุดของ Standard: รวมได้ 6 คะแนน (เช่น VO2Max=2[คะแนน2], RHR=85[คะแนน1],
        // HRR=12[คะแนน3])
        HealthIndexScore healthMin = new HealthIndexScore(35.0, 85, 12);
        // สูงสุดของ Standard: รวมได้ 11 คะแนน (เช่น VO2Max=41[คะแนน3], RHR=61[คะแนน3],
        // HRR=25[คะแนน5])
        HealthIndexScore healthMax = new HealthIndexScore(41.0, 61, 25);

        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, healthMin.getFitnessLevel());
        assertEquals(HealthIndexScore.FitnessLevel.STANDARD, healthMax.getFitnessLevel());
    }

    @Test
    @DisplayName("Total Score รวมได้ขอบล่างสุดของ Excellent (12) ต้องได้เกณฑ์ EXCELLENT")
    public void testFitnessLevel_Excellent_Boundary() {
        // คะแนนรวม 12 (เช่น VO2Max=41[คะแนน3], RHR=60[คะแนน5], HRR=19[คะแนน4])
        HealthIndexScore health = new HealthIndexScore(41.0, 60, 19);
        assertEquals(HealthIndexScore.FitnessLevel.EXCELLENT, health.getFitnessLevel());
    }
}