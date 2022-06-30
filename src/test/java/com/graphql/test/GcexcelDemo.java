/***
 * https://www.grapecity.com/blogs/getting-started-with-grapecity-documents-for-excel-java-on-windows-mac-linux
 */
package com.graphql.test;
import com.grapecity.documents.excel.*;


public class GcexcelDemo {
	 public static void main(String[] args)
	 {
		 Workbook workbook = new Workbook();
		 Object[][] sourceData = new Object[][]{
             {"ITEM", "AMOUNT"},
             {"Income 1", 2500},
             {"Income 2", 1000},
             {"Income 3", 250},
             {"Other", 250},
     };

Object[][] sourceData1 = new Object[][]{
             {"ITEM", "AMOUNT"},
             {"Rent/mortgage", 800},
             {"Electricity", 120},
             {"Gas", 50},
             {"Cell phone", 45},
             {"Groceries", 500},
             {"Car payment", 273},
             {"Auto expenses", 120},
             {"Student loans", 50},
             {"Credit cards", 100},
             {"Auto Insurance", 78},
             {"Personal care", 50},
             {"Entertainment", 100},
             {"Miscellaneous", 50},
     };
     IWorksheet worksheet = workbook.getWorksheets().get(0);
     worksheet.setName("Table");
     worksheet.getRange("B3:C7").setValue(sourceData);
     worksheet.getRange("B10:C23").setValue(sourceData1);

     worksheet.getRange("B2:C2").merge();
     worksheet.getRange("B2").setValue("MONTHLY INCOME");
     worksheet.getRange("B9:C9").merge();
     worksheet.getRange("B9").setValue("MONTHLY EXPENSES");
     worksheet.getRange("E2:G2").merge();
//     worksheet.getRange("E2").setValue("PERCENTAGE OF INCOME SPENT");
//     worksheet.getRange("E5:G5").merge();
//     worksheet.getRange("E5").setValue("SUMMARY");
//     worksheet.getRange("E3:F3").merge();
//     worksheet.getRange("E9").setValue("BALANCE");
//     worksheet.getRange("E6").setValue("Total Monthly Income");
//     worksheet.getRange("E7").setValue("Total Monthly Expenses");
//     ITable incomeTable = worksheet.getTables().add(worksheet.getRange("B3:C7"), true);
//     incomeTable.setName("tblIncome");
//     incomeTable.setTableStyle(workbook.getTableStyles().get("TableStyleMedium4"));

     //Create the second table to show Expenses.
   //  ITable expensesTable = worksheet.getTables().add(worksheet.getRange("B10:C23"), true);
//     expensesTable.setName("tblExpenses");
//     expensesTable.setTableStyle(workbook.getTableStyles().get("TableStyleMedium4"));
//     worksheet.getNames().add("TotalMonthlyIncome", "=SUM(tblIncome[AMOUNT])");
//     worksheet.getNames().add("TotalMonthlyExpenses", "=SUM(tblExpenses[AMOUNT])");
//     worksheet.getRange("E3").setFormula("=TotalMonthlyExpenses");
//     worksheet.getRange("G3").setFormula("=TotalMonthlyExpenses/TotalMonthlyIncome");
//     worksheet.getRange("G6").setFormula("=TotalMonthlyIncome");
//     worksheet.getRange("G7").setFormula("=TotalMonthlyExpenses");
//     worksheet.getRange("G9").setFormula("=TotalMonthlyIncome-TotalMonthlyExpenses");
//     worksheet.setStandardHeight(26.25);
//     worksheet.setStandardWidth(8.43);
//
//     worksheet.getRange("2:24").setRowHeight(27);
//     worksheet.getRange("A:A").setColumnWidth(2.855);
//     worksheet.getRange("B:B").setColumnWidth(33.285);
//     worksheet.getRange("C:C").setColumnWidth(25.57);
//     worksheet.getRange("D:D").setColumnWidth(1);
//     worksheet.getRange("E:F").setColumnWidth(25.57);
//     worksheet.getRange("G:G").setColumnWidth(14.285);
     workbook.save("./GcExcelFeatures.xlsx");
	 }

}
