package com.home.mybuddywriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Readtestdata {
	
	String path;
	Object[][] dataarray;
	public File src = null;
	public  FileInputStream fis = null;
    public  FileOutputStream fileOut =null;
    private XSSFWorkbook wb = null;
    private XSSFSheet sh1 = null;
    private XSSFRow row   =null;
    private XSSFCell cell = null;
    DataFormatter formatter = null;
	
	public Readtestdata(String path){
		this.path=path;	
		try{
			  // Specify the path of file
			 //   src=new File("/testdata/testdata.xlsx");
			 
			   // load file
			    fis=new FileInputStream(path);
			 
			   // Load workbook
			    wb=new XSSFWorkbook(fis);
			   
			   // Load sheet- Here we are loading first sheet only
			    sh1= wb.getSheetAt(0);
			    
			   //Dataformatter
			    formatter = new DataFormatter();
			    
			  //close the input stream
			    fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
    // returns the row count in a sheet
    public int getRowCount(String sheetname){
          int index = wb.getSheetIndex(sheetname);
          if(index==-1)
                return 0;
          else{
          sh1 = wb.getSheetAt(index);
          int number=sh1.getLastRowNum()+1;
          return number;
          }
         
    }
   
    // returns the data from a cell
    public String getCellData(String sheetname, int colNum,int rowNum){
    	 try{
             if(rowNum <=0)
                   return "";
      
       int index = wb.getSheetIndex(sheetname);

       if(index==-1)
             return "";
      
       sh1 = wb.getSheetAt(index);
       row = sh1.getRow(rowNum-1);
       if(row==null)
             return "";
       cell = row.getCell(colNum);
       if(cell==null)
             return "";
       
      
   if(cell.getCellType()==Cell.CELL_TYPE_STRING )
         return cell.getStringCellValue();
   else if( cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
        
         String cellText  = String.valueOf(cell.getNumericCellValue());
         if (HSSFDateUtil.isCellDateFormatted(cell)) {
            // format in form of M/D/YY
               double d = cell.getNumericCellValue();

               Calendar cal =Calendar.getInstance();
               cal.setTime(HSSFDateUtil.getJavaDate(d));
             cellText =
              (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
            cellText = cal.get(Calendar.MONTH)+1 + "/" +
                       cal.get(Calendar.DAY_OF_MONTH) + "/" +
                       cellText;
            
           // System.out.println(cellText);

          }

        return cellText;
   }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
       return "";
   else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
	    return formatter.formatCellValue(cell);
        //return String.valueOf(BigDecimal.valueOf(cell.getNumericCellValue()));
   else
         return String.valueOf(cell.getBooleanCellValue());
       }
       catch(Exception e){
            
             e.printStackTrace();
             return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
       }
    }
	
 // find whether sheets exists    
    public boolean isSheetExist(String sheetname){
          int index = wb.getSheetIndex(sheetname);
          if(index==-1){
                index=wb.getSheetIndex(sheetname.toUpperCase());
                      if(index==-1)
                            return false;
                      else
                            return true;
          }
          else
                return true;
    }
   
 // returns number of columns in a sheet  
    public int getColumnCount(String sheetname){
          // check if sheet exists
          if(!isSheetExist(sheetname))
           return -1;
         
          sh1 = wb.getSheet(sheetname);
          row = sh1.getRow(0);
         
          if(row==null)
                return -1;
         
          return row.getLastCellNum();
         
         
         
    }
    
 
    
 // to run this on stand alone
 /*	public static void main(String arg[]) throws IOException{
 		
 		//System.out.println(filename);
 		 Readtestdata datatable = null;
 		 datatable = new  Readtestdata("/testdata/testdata.xlsx");
 		   for(int row=0; row<datatable.getRowCount("logintest"); row++){
 				for(int col=0 ;col< datatable.getColumnCount("logintest"); col++){
 				
 					System.out.println("RowNum:" +row + "ColNum:"+ col+ "value:" + datatable.getCellData("logintest",col, (row+1)));
 					
 				}
 		   }
 	}*/
 
 
}
 
