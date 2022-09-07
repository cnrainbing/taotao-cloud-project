package com.taotao.cloud.office.easyexecl.core.sort;

import com.alibaba.excel.EasyExcel;
import com.taotao.cloud.common.execl.util.TestFileUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**

 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortDataTest {

    private static File file07;
    private static File file03;
    private static File fileCsv;
    private static File sortNoHead07;
    private static File sortNoHead03;
    private static File sortNoHeadCsv;

    @BeforeClass
    public static void init() {
        file07 = TestFileUtil.createNewFile("sort.xlsx");
        file03 = TestFileUtil.createNewFile("sort.xls");
        fileCsv = TestFileUtil.createNewFile("sort.csv");
        sortNoHead07 = TestFileUtil.createNewFile("sortNoHead.xlsx");
        sortNoHead03 = TestFileUtil.createNewFile("sortNoHead.xls");
        sortNoHeadCsv = TestFileUtil.createNewFile("sortNoHead.csv");
    }

    @Test
    public void t01ReadAndWrite07() {
        readAndWrite(file07);
    }

    @Test
    public void t02ReadAndWrite03() {
        readAndWrite(file03);
    }

    @Test
    public void t03ReadAndWriteCsv() {
        readAndWrite(fileCsv);
    }

    @Test
    public void t11ReadAndWriteNoHead07() {
        readAndWriteNoHead(sortNoHead07);
    }

    @Test
    public void t12ReadAndWriteNoHead03() {
        readAndWriteNoHead(sortNoHead03);
    }

    @Test
    public void t13ReadAndWriteNoHeadCsv() {
        readAndWriteNoHead(sortNoHeadCsv);
    }

    private void readAndWrite(File file) {
        EasyExcel.write(file, SortData.class).sheet().doWrite(data());
        List<Map<Integer, String>> dataMap = EasyExcel.read(file).sheet().doReadSync();
        Assert.assertEquals(1, dataMap.size());
        Map<Integer, String> record = dataMap.get(0);
        Assert.assertEquals("column1", record.get(0));
        Assert.assertEquals("column2", record.get(1));
        Assert.assertEquals("column3", record.get(2));
        Assert.assertEquals("column4", record.get(3));
        Assert.assertEquals("column5", record.get(4));
        Assert.assertEquals("column6", record.get(5));

        EasyExcel.read(file, SortData.class, new SortDataListener()).sheet().doRead();
    }

    private void readAndWriteNoHead(File file) {
        EasyExcel.write(file).head(head()).sheet().doWrite(data());
        List<Map<Integer, String>> dataMap = EasyExcel.read(file).sheet().doReadSync();
        Assert.assertEquals(1, dataMap.size());
        Map<Integer, String> record = dataMap.get(0);
        Assert.assertEquals("column1", record.get(0));
        Assert.assertEquals("column2", record.get(1));
        Assert.assertEquals("column3", record.get(2));
        Assert.assertEquals("column4", record.get(3));
        Assert.assertEquals("column5", record.get(4));
        Assert.assertEquals("column6", record.get(5));
        EasyExcel.read(file, SortData.class, new SortDataListener()).sheet().doRead();
    }

    private List<List<String>> head() {
        List<List<String>> head = new ArrayList<List<String>>();
        head.add(Collections.singletonList("column1"));
        head.add(Collections.singletonList("column2"));
        head.add(Collections.singletonList("column3"));
        head.add(Collections.singletonList("column4"));
        head.add(Collections.singletonList("column5"));
        head.add(Collections.singletonList("column6"));
        return head;
    }

    private List<SortData> data() {
        List<SortData> list = new ArrayList<SortData>();
        SortData sortData = new SortData();
        sortData.setColumn1("column1");
        sortData.setColumn2("column2");
        sortData.setColumn3("column3");
        sortData.setColumn4("column4");
        sortData.setColumn5("column5");
        sortData.setColumn6("column6");
        list.add(sortData);
        return list;
    }
}