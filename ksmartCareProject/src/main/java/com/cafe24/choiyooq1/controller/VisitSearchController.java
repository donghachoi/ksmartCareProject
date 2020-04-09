package com.cafe24.choiyooq1.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.VisitSearchService;
import com.cafe24.choiyooq1.service.VisitService;


@Controller
public class VisitSearchController {

	@Autowired
	private VisitSearchService visitSerchService;
	
	@Autowired
	private VisitService visitservice;
	
	//임의에 세션값 
	private String centerCode= "3-41590-00001";
	private String canterName ="전주스마트재가센터";
	
    //방문일정검색 직원 리스트 
    @GetMapping("/employee/emplyeeCalenderSearch")
    public String vemplyeeCalenderSearch(Model model) {
    	
    	List<Employee> list = visitservice.emplyeeList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/emplyeeCalenderSearch";
    }
    
    //방문일정검색 수급자 리스트 
    @GetMapping("/employee/elderCalenderSearch")
    public String velderCalenderSearch(Model model) {
    	
    	List<Elder> list = visitservice.elderAllList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/elderCalenderSearch";
    }    
	
	//방문일정 검색 수급자 일정 리스트 
	@PostMapping("/elder/elderCalenderSearch")
	public @ResponseBody List<Visit> elderCalenderSearch(@RequestParam(value="id") String id){
		
		List<Visit> list = visitSerchService.elderCalenderSearch(id);
		return list;
	}
	
	//방문일정 검색 직원 일정 리스트 
	@PostMapping("/employee/employeeCalenderSearch")
	public @ResponseBody List<Visit> employeeCalenderSearch(@RequestParam(value="id") String id){
		
		List<Visit> list = visitSerchService.employeeCalenderSearch(id);
		return list;
	}
	
	// 수급자 일정 엑셀 출력 
	@GetMapping("/elder/excelDown")
	public void elderExcelDown(HttpServletResponse response, @RequestParam(value="id") String id, @RequestParam(value ="name") String name) throws Exception {
		
		Calendar today = Calendar.getInstance();
		String syear = Integer.toString(today.get(Calendar.YEAR));
		String smonth = "0"+Integer.toString(today.get(Calendar.MONTH)+1);

		List<Visit> list = visitSerchService.excelDown(id, syear, smonth);
		
		// 워크북 생성

	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet(name+"일정 출력");
	    sheet.setColumnWidth(0, 6000);
	    sheet.setColumnWidth(1, 6000);
	    sheet.setColumnWidth(2, 6000);
	    sheet.setColumnWidth(3, 6000);
	    
	    Row row = null;
	    Cell cell = null;
	    int rowNo = 0;

	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();

	    // 가는 경계선을 가집니다.
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);

	    // 배경색은 노란색입니다.
	    headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("서비스 종류 ");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("날짜");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("시간");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);	 
	    cell.setCellValue("담당요양사");
	    
	    // 데이터 부분 생성

	    for(Visit vo : list) {

	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitServiceCategory());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitPlanDate());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitServiceTime());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getEmployeeName());
	    }

	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=Calendar.xls");

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	}
	
	//요양사 엑셀 일정
	@GetMapping("/employee/excelDown")
	public void employeeExcelDown(HttpServletResponse response, @RequestParam(value="id") String id, @RequestParam(value ="name") String name) throws Exception {

		Calendar today = Calendar.getInstance();
		String syear = Integer.toString(today.get(Calendar.YEAR));
		String smonth = "0"+Integer.toString(today.get(Calendar.MONTH)+1);

		List<Visit> list = visitSerchService.employeeExcelDown(id, syear, smonth);
		
		// 워크북 생성
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet(name+"일정 출력");
	    sheet.setColumnWidth(0, 6000);
	    sheet.setColumnWidth(1, 6000);
	    sheet.setColumnWidth(2, 6000);
	    sheet.setColumnWidth(3, 6000);
	    
	    Row row = null;
	    Cell cell = null;
	    int rowNo = 0;

	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();

	    // 가는 경계선을 가집니다.
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);

	    // 배경색은 노란색입니다.
	    headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("서비스 종류 ");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("날짜");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("시간");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);	 
	    cell.setCellValue("수급자이름");
	    
	    // 데이터 부분 생성

	    for(Visit vo : list) {

	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitServiceCategory());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitPlanDate());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getVisitPlanTime());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getElderName());
	    }

	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=Calendar.xls");

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	}
}
