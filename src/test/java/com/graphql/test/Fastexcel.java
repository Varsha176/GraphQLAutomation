package com.graphql.test;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Fastexcel {
	static File f =null;
	 static Workbook wb ;

    public static void main(String[] args) throws IOException {

        List<String> words = List.of("sky", "blue", "work", "falcon","tEST");

        int row = 0;
        int col = 0;

        f = new File("/Users/varsha/eclipse-workspace/GraphQLDemo/src/test/resources/words.xlsx");

        try (FileOutputStream fos = new FileOutputStream(f)) {

             wb = new Workbook(fos, "Application", "1.0");
            Worksheet ws = wb.newWorksheet("Sheet 1");
            
            ws.value(1, 1, "old falcon");
            ws.style(1, 1).horizontalAlignment("center").bold().italic().set();

            for (String word : words) {

                ws.value(row, col, word);
                row++;
            }

            createNewSheet();
            wb.finish();
        }
        
        FastExcelRead();
    }
    public static void createNewSheet() throws FileNotFoundException, IOException
    {
    	try (FileOutputStream os = new FileOutputStream(f)) {
    	  //  Workbook wb = new Workbook(os, "MyApplication", "1.0");
    	    Worksheet ws = wb.newWorksheet("Sheet 2");
    	    ws.value(0, 0, "This is a string in A1");
    	    ws.value(0, 1, new Date());
    	    ws.value(0, 2, 1234);
    	    ws.value(0, 3, 123456L);
    	    ws.value(0, 4, 1.234);
    	    wb.finish();
    	}

    }
    
    public static void FastExcelRead() throws IOException {

       

            File f = new File("/Users/varsha/eclipse-workspace/GraphQLDemo/src/test/resources/words.xlsx");

            try (ReadableWorkbook wb = new ReadableWorkbook(f)) {

                Sheet sheet = wb.getFirstSheet();

                try (Stream<Row> rows = sheet.openStream()) {

                    Iterator<Row> it = rows.iterator();

                  //  int i = 0;
                    while (it.hasNext()) {

                        Row row = it.next();
                        row.stream().forEach(cell -> System.out.println(cell.getText()));

                    //    i++;
                    }
                }
            }
        
    }
}