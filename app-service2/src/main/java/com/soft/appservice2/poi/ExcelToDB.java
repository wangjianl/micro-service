package com.soft.appservice2.poi;/**
 * Created by wangjian on 18/4/7.
 */

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class ExcelToDB {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    //判断Excel的版本,获取Workbook
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    //判断文件是否是excel
    public static void checkExcelVaild(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }

    //由指定的Sheet导出至List
    public static Set<String> exportListFromExcel() throws IOException {
        Set<String> resultSet = new HashSet();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("/Users/wangjian/work2/serven.xlsx"); // 创建文件对象
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(is,excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet

            // 为跳过第一行目录设置count
            int count = 0;

            for (Row row : sheet) {
                // 跳过第一行的目录
                if(count == 0){
                    count++;
                    continue;
                }
                // 如果当前行没有数据，跳出循环
                if(row.getCell(0).toString().equals("")){
                    //return ;
                }
                String rowValue = "";
                int cellCount = 0;
                for (Cell cell : row) {
                    cellCount++;
                    if(cell.toString() == null){
                        continue;
                    }
                    int cellType = cell.getCellType();
                    String cellValue = "";
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING:     // 文本
                            cellValue = cell.getRichStringCellValue().getString();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:    // 数字、日期
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = fmt.format(cell.getDateCellValue());
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:    // 布尔型
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK: // 空白
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_ERROR: // 错误
                            cellValue = "错误#";
                            break;
                        case Cell.CELL_TYPE_FORMULA:    // 公式
                            // 得到对应单元格的公式
                            //cellValue = cell.getCellFormula() + "#";
                            // 得到对应单元格的字符串
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cellValue = String.valueOf(cell.getRichStringCellValue().getString()) + "";
                            break;
                        default:
                            cellValue = "";
                    }
                    //System.out.print(cellValue);
                    rowValue += cellValue;
                    if(cellCount==2){
                        System.out.println(cellValue);
                        resultSet.add(cellValue.toString());
                    }
                }
                //System.out.println(rowValue);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        }
        return resultSet;
    }


    public static void main(String []args){
        try {
            Set resultSet = exportListFromExcel();
            System.out.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
