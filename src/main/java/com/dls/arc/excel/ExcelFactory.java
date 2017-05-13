package com.dls.arc.excel;


import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class ExcelFactory<T> {
    private static final String EXCEL_FONT_FAMILY_SETTING = "Monaco";
    private static final int EXCEL_FONT_SIZE_SETTING = 12;

    private WritableWorkbook writableWorkbook = null;
    private WritableSheet writableSheet = null;

    public WritableWorkbook createExcel(OutputStream os, Excel excel,
                                        List<String> header, List<T> datas, ExcelMapper<T> mapper)
            throws RowsExceededException, WriteException {
        try {
            writableWorkbook = Workbook.createWorkbook(os);
            writableSheet = writableWorkbook.createSheet(excel.getSheetName(),
                    excel.getSheetNum());
            SheetSettings settings = writableSheet.getSettings();
            settings.setVerticalFreeze(1);

            // Write the title
            if (header != null && header.size() > 0) {
                for (int i = 0; i < header.size(); i++) {
                    writableSheet.addCell(new Label(i, 0, header.get(i),
                            setHeaderCellStyle(new CellStyle(EXCEL_FONT_FAMILY_SETTING, EXCEL_FONT_SIZE_SETTING))));
                }
            }

            // Write data to file
            if (datas != null && datas.size() > 0) {
                for (int i = 1; i <= datas.size(); i++) {
                    mapper.mapToExcel(writableSheet, datas.get(i - 1),
                            setBodyCellStyle(new CellStyle(EXCEL_FONT_FAMILY_SETTING, EXCEL_FONT_SIZE_SETTING)), i);
                }
            }
        } catch (IOException e) {
            return null;
        }

        return writableWorkbook;
    }

    private WritableCellFormat setHeaderCellStyle(CellStyle style) throws WriteException {
        WritableFont font = new WritableFont(
                WritableFont.createFont(style.getFont()), style.getFontSize(), WritableFont.BOLD);
        WritableCellFormat cellFormat = setCentre(style, font);

        return cellFormat;
    }

    private WritableCellFormat setBodyCellStyle(CellStyle style) throws WriteException {
        WritableFont font = new WritableFont(
                WritableFont.createFont(style.getFont()), style.getFontSize(), WritableFont.NO_BOLD);
        WritableCellFormat cellFormat = setCentre(style, font);

        return cellFormat;
    }

    private WritableCellFormat setCentre(CellStyle style, WritableFont font)
            throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        cellFormat.setBackground(style.getBgColor());
        cellFormat.setAlignment(Alignment.CENTRE);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        return cellFormat;
    }
}

