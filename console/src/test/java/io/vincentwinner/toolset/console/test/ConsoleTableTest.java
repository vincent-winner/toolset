package io.vincentwinner.toolset.console.test;

import io.vincentwinner.toolset.console.table.ConsoleTable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsoleTableTest {

    @Test
    public void _01_testConsoleTable(){
        ConsoleTable consoleTable = new ConsoleTable();
        consoleTable.addHeader("id","姓名","年龄");
        consoleTable.addBody("1","zs","14");
        consoleTable.addBody("2","ls","15");
        consoleTable.addBody("3","ww","16");
        consoleTable.print();
        Assert.assertEquals("+－－－－+－－－－+－－－－+\n" +
                "|　ｉｄ　|　姓名　|　年龄　|\n" +
                "+－－－－+－－－－+－－－－+\n" +
                "|　１　　|　ｚｓ　|　１４　|\n" +
                "|　２　　|　ｌｓ　|　１５　|\n" +
                "|　３　　|　ｗｗ　|　１６　|\n" +
                "+－－－－+－－－－+－－－－+\n",consoleTable.toString());
    }

}
