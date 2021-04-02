package app.netlify.qaautomationpractice.utility.data_io;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    public static void setExcelSheet(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IOException("Sheet \"" + sheetName + "\" could not be found in workbook.");
        }
    }

    /**
     * Returns the value of a cell in an .xlsx file. Requires invoking setExcelSheet().
     *
     * @param row    The row of the cell (0-indexed)
     * @param column The column of the cell (0-indexed)
     * @return The String value of a cell.
     */
    public static String getCellValue(int row, int column) {
        XSSFCell cell = sheet.getRow(row)
                .getCell(column);
        return getCellAsString(cell);
    }

    private static String getCellAsString(XSSFCell cell) {
        String cellAsString = null;
        switch (cell.getCellType()) {
            case STRING:
                cellAsString = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellAsString = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                cellAsString = "";
                break;
            case ERROR:
                cellAsString = cell.getErrorCellString();
                break;
            case NUMERIC:
                cellAsString = String.valueOf(cell.getNumericCellValue());
                break;
            case FORMULA:
                FormulaEvaluator eval = new XSSFFormulaEvaluator(workbook);
                CellValue cellValue = eval.evaluate(cell);
                cellAsString = getEvaluatedFormulaAsString(cellValue);
                break;
            case _NONE:
                break;
        }
        return cellAsString;
    }

    /**
     * Converts evaluated formula into String.
     *
     * @param value The CellValue object from evaluating a formula.
     * @return A string representation of an evaluated formula.
     */
    private static String getEvaluatedFormulaAsString(CellValue value) {
        String valueAsString = null;
        switch (value.getCellType()) {
            case STRING:
                valueAsString = value.getStringValue();
                break;
            case BOOLEAN:
                valueAsString = String.valueOf(value.getBooleanValue());
                break;
            case BLANK:
                valueAsString = "";
                break;
            case ERROR:
                valueAsString = String.valueOf(value.getErrorValue());
                break;
            case NUMERIC:
                valueAsString = String.valueOf(value.getNumberValue());
                break;
            case FORMULA:
                //Literally impossible to get a cell value of type formula. Unless they add that into POI one day.
                break;
            case _NONE:
                break;
        }
        return valueAsString;
    }

    public static String[][] getTableOfCells(int row1, int column1, int row2, int column2) {
        String[][] table = new String[(row2 - row1) + 1][(column2 - column1) + 1];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                XSSFCell cell = sheet.getRow(row1 + i).getCell(column1 + j);
                table[i][j] = getCellAsString(cell);
            }
        }
        return table;
    }

    public static String[][] getTableWithinBounds(String boundCellValue) {
        String[][] table;
        // Handle numbers and strings
        DataFormatter formatter = new DataFormatter();
        // BoundaryCells are the first and the last column
        // We need to find first and last column, so that we know which rows to read for the data
        XSSFCell[] boundaryCells = findCellsWithinBounds(boundCellValue);
        // First cell to start with
        XSSFCell startCell = boundaryCells[0];
        // Last cell where data reading should stop
        XSSFCell endCell = boundaryCells[1];

        // Find the start row based on the start cell
        int startRow = startCell.getRowIndex() + 1;
        // Find the end row based on end cell
        int endRow = endCell.getRowIndex() - 1;
        // Find the start column based on the start cell
        int startCol = startCell.getColumnIndex() + 1;
        // Find the end column based on end cell
        int endCol = endCell.getColumnIndex() - 1;

        // Declare multi-dimensional array to capture the data from the table
        table = new String[endRow - startRow + 1][endCol - startCol + 1];

        for (int i = startRow; i < endRow + 1; i++) {
            for (int j = startCol; j < endCol + 1; j++) {
                // testData[i-startRow][j-startCol] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
                // For every column in every row, fetch the value of the cell
                Cell cell = sheet.getRow(i).getCell(j);
                // Capture the value of the cell in the multi-dimensional array
                table[i - startRow][j - startCol] = formatter.formatCellValue(cell);
            }
        }
        // Return the multi-dimensional array
        return table;
    }

    private static XSSFCell[] findCellsWithinBounds(String boundCellValue) {
        DataFormatter formatter = new DataFormatter();
        // Declare begin position
        boolean begin = true;
        XSSFCell[] cells = new XSSFCell[2];

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (boundCellValue.equals(formatter.formatCellValue(cell))) {
                    if (begin) {
                        // Find the begin cell, this is used for boundary cells
                        cells[0] = (XSSFCell) cell;
                        begin = false;
                    } else {
                        // Find the end cell, this is used for boundary cells
                        cells[1] = (XSSFCell) cell;
                    }
                }
            }
        }
        // Return the cells array
        return cells;
    }

    public static void close() throws IOException {
        if (workbook != null)
            workbook.close();
    }
}
