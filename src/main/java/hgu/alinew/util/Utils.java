package hgu.alinew.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hgu.alinew.model.ChapelerInfo;

public class Utils {

	public static void storeResults(List<ChapelerInfo> chapelerList, String profName) throws IOException {
		String fName = System.getProperty("user.dir") + File.separator + "results" + File.separator + profName + " 팀 채플 참석자 명단" + ".xlsx";

		File savedFile = new File(fName);
		savedFile.getParentFile().mkdirs();

		String excelFilePath = savedFile.getAbsolutePath();

		writeExcel(chapelerList, excelFilePath);
	}

	private static void writeExcel(List<ChapelerInfo> chapelerList, String excelFilePath) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Chaple Attendees Info");

		int rowCount = 0;

		for (ChapelerInfo aBook : chapelerList) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aBook, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}

		workbook.close();
	}

	private static void writeBook(ChapelerInfo aChapler, Row row) {
		Cell cell = row.createCell(1);
		cell.setCellValue(aChapler.getTeamInfo());

		cell = row.createCell(2);
		cell.setCellValue(aChapler.getStudentId());

		cell = row.createCell(3);
		cell.setCellValue(aChapler.getAttendance());
	}

	public static void printRangeOfTheNumberOfComments(int numScrollDown) {
		int minNumOfComments = 0;
		int maxNumOfComments = (numScrollDown - 1) * 20;

		// The least number of scroll down is 7 because of recommended video on the right side
		if (numScrollDown > 7) {
			minNumOfComments = (numScrollDown - 2) * 20;

			System.out.println("Range of the number of comments : " + "(" + minNumOfComments + " , " + maxNumOfComments + ")");
		} else {
			System.out.println("Range of the number of comments : " + "[" + minNumOfComments + " , " + maxNumOfComments + ")");
		}
	}

}
