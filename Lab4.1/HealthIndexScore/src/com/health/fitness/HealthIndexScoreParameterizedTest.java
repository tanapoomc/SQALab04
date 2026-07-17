package com.health.fitness;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthIndexScoreParameterizedTest {

    // ใช้ ParameterizedTest ในการรันชุดข้อมูลขอบเขตปกติ (4n + 1 = 13 เคส)
    @ParameterizedTest(name = "TC{index}: VO2Max={0}, RHR={1}, HRR={2}")
    @CsvSource({
        // โครงสร้างข้อมูล: "vo2Max, rhr, hrr, expectedScore, expectedLevel"
        
        // --- เคสที่ 1: All Nominal (ค่าปกติระดับ Standard) ---
        "35.0, 72, 15, 8, STANDARD",   // TC1: VO2Max=2คะแนน, RHR=3คะแนน, HRR=3คะแนน
        
        // --- เคสที่ 2-5: ทดสอบขอบเขตของ VO2 Max (ตรึง RHR, HRR เป็น Nominal) ---
        "0.0,  72, 15, 6, STANDARD",   // TC2: VO2 Max (Min) -> 0คะแนน
        "1.0,  72, 15, 6, STANDARD",   // TC3: VO2 Max (Min+1) -> 0คะแนน
        "79.0, 72, 15, 11, STANDARD",  // TC4: VO2 Max (Max-1) -> 5คะแนน
        "80.0, 72, 15, 11, STANDARD",  // TC5: VO2 Max (Max) -> 5คะแนน
        
        // --- เคสที่ 6-9: ทดสอบขอบเขตของ RHR (ตรึง VO2Max, HRR เป็น Nominal) ---
        "35.0, 40,  15, 10, STANDARD",  // TC6: RHR (Min) -> 5คะแนน
        "35.0, 41,  15, 10, STANDARD",  // TC7: RHR (Min+1) -> 5คะแนน
        "35.0, 219, 15, 6,  STANDARD",  // TC8: RHR (Max-1) -> 1คะแนน
        "35.0, 220, 15, 6,  STANDARD",  // TC9: RHR (Max) -> 1คะแนน
        
        // --- เคสที่ 10-13: ทดสอบขอบเขตของ HRR (ตรึง VO2Max, RHR เป็น Nominal) ---
        "35.0, 72, 0,  6,  STANDARD",  // TC10: HRR (Min) -> 1คะแนน
        "35.0, 72, 1,  6,  STANDARD",  // TC11: HRR (Min+1) -> 1คะแนน
        "35.0, 72, 29, 10, STANDARD",  // TC12: HRR (Max-1) -> 5คะแนน
        "35.0, 72, 30, 10, STANDARD"   // TC13: HRR (Max) -> 5คะแนน
    })
    public void testNormalBoundaryValue(double vo2Max, int rhr, int hrr, int expectedScore, HealthIndexScore.FitnessLevel expectedLevel) {
        
        // 1. สร้าง Object โดยส่งค่าผ่าน Constructor ของ HealthIndexScore
        HealthIndexScore health = new HealthIndexScore(vo2Max, rhr, hrr);

        // 2. เรียกใช้เมธอดคำนวณคะแนนรวมและระดับฟิตเนสจริงจากระบบ
        int actualScore = health.getTotalScore();
        HealthIndexScore.FitnessLevel actualLevel = health.getFitnessLevel();

        // 3. พิมพ์ผลลัพธ์ด้วยรูปแบบ Tab (\t) เพื่อให้หนูลากคลุม Copy ไป Paste ลง Excel ได้ทันที! 
        System.out.printf("%.1f\t%d\t%d\t%d\t%d\t%s\t%s\n", 
                vo2Max, rhr, hrr, expectedScore, actualScore, expectedLevel, actualLevel);

        // 4. ตรวจสอบความถูกต้องของผลลัพธ์
        assertEquals(expectedScore, actualScore, "คะแนนรวม (Total Score) ไม่ถูกต้อง!");
        assertEquals(expectedLevel, actualLevel, "ระดับผลลัพธ์ (Fitness Level) ไม่ถูกต้อง!");
    }
}
