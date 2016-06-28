/*
 */
package shafin.sustord.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shafin.sustord.dto.StudentPOJO;

/**
 *
 * @author SHAFIN
 */
public class StatisticsHelper {

	public static Map<String, Integer> getCgpaDistibutionMap(List<StudentPOJO> students) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int Ap, A, Am, Bp, B, Bm, Cp, C, Cm, F;
		Ap = A = Am = Bp = B = Bm = Cp = C = Cm = F = 0;

		for (StudentPOJO s : students) {
			double cgpa = s.getCgpa();
			String gradeLetter = CgpaCalculation.getGradeLetterFromGradePoint(cgpa);

			if (gradeLetter.equals("A+")) {
				Ap++;
			} else if (gradeLetter.equals("A")) {
				A++;
			} else if (gradeLetter.equals("A-")) {
				Am++;
			} else if (gradeLetter.equals("B+")) {
				Bp++;
			} else if (gradeLetter.equals("B")) {
				B++;
			} else if (gradeLetter.equals("B-")) {
				Bm++;
			} else if (gradeLetter.equals("C+")) {
				Cp++;
			} else if (gradeLetter.equals("C")) {
				C++;
			} else if (gradeLetter.equals("C-")) {
				Cm++;
			} else if (gradeLetter.equals("F")) {
				// F++;
			} else {
				System.out.println("nothing to do with this gradeletter");
			}
		}

		map.put("Ap", Ap);
		map.put("A", A);
		map.put("Am", Am);
		map.put("Bp", Bp);
		map.put("B", B);
		map.put("Bm", Bm);
		map.put("Cp", Cp);
		map.put("C", C);
		map.put("Cm", Cm);
		map.put("F", F);

		return map;
	}
}
