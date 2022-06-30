package com.graphql.test;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.xlsx4j.jaxb.Context;
import org.xlsx4j.sml.CTRst;
import org.xlsx4j.sml.CTXstringWhitespace;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.STCellType;
import org.xlsx4j.sml.SheetData;

public class Doc4jExample {
	
	
	
	 public static void main(String[] args) throws IOException, Docx4JException, JAXBException {
		 
		 SpreadsheetMLPackage pkg = SpreadsheetMLPackage.createPackage();
			// Create worksheet
			WorksheetPart sheet = pkg.createWorksheetPart(new PartName("/xl/worksheets/sheet1.xml"), "Sheet1", 1);
			SheetData sheetData = sheet.getContents().getSheetData();
			// Add Data
			Row row = Context.getsmlObjectFactory().createRow();
			Cell cell = Context.getsmlObjectFactory().createCell();
			cell.setV("1234");
			row.getC().add(cell);
			CTXstringWhitespace ctx = Context.getsmlObjectFactory().createCTXstringWhitespace();
			ctx.setValue("Open Source Java Library for Spreadsheet Documents");
			CTRst ctrst = new CTRst();
			ctrst.setT(ctx);
			cell.setT(STCellType.INLINE_STR);
			cell.setIs(ctrst);
			row.getC().add(cell);
			sheetData.getRow().add(row);
			// Save
			pkg.save(new File("FileFormat.xlsx"));  
	 }
	
	

}
