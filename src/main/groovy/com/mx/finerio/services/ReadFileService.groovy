package com.mx.finerio.services

import com.mx.finerio.dto.CsvRow
import com.opencsv.CSVReader

class ReadFileService {

    List<CsvRow> processInputFileOneByOne(String inputFilePath) throws Exception{
        List<CsvRow> rows = []
        CSVReader reader = new CSVReader(new FileReader(inputFilePath))

        try {
            String[] line = reader.readNext()
            while (line != null) {
                rows.add(createCsvRow(line))
                line = reader.readNext()
            }
        }
        catch (Exception e){
            println e
        }
        finally {
            reader.close()
        }
        return rows

    }

    List<CsvRow> processInputFile(String inputFilePath) throws Exception{

        List<CsvRow> rows = []
        try  {
            CSVReader reader = new CSVReader(new FileReader(inputFilePath))
            List<String[]> r = reader.readAll()
            r.forEach({ x -> rows.add(createCsvRow(x)) })
        }
        catch (Exception e){
             println e
        }
        rows

    }


    CsvRow  createCsvRow(String[] plainRowList) {
        CsvRow row = new CsvRow()
        row.with {
            userName = plainRowList[0]
            financialEntityId = plainRowList[1] as Long
            accountName = plainRowList[2]
            accountNumber = plainRowList[3] as Long
            accountNature = plainRowList[4]
            balance = plainRowList[5] as BigDecimal
        }
        row
    }
}