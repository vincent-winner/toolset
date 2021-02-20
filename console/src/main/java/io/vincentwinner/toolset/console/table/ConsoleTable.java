package io.vincentwinner.toolset.console.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleTable {

    private static final char ROW_LINE = '-';
    private static final char COLUMN_LINE = '|';
    private static final char CORNER = '+';
    private static final char SPACE = '\u3000';
    private static final char LF = '\n';

    /**
     * 表格头信息
     */
    private final List<List<String>> HEADER_LIST = new ArrayList<>();
    /**
     * 表格体信息
     */
    private final List<List<String>> BODY_LIST = new ArrayList<>();
    /**
     * 每列最大字符个数
     */
    private List<Integer> columnCharNumber;

    /**
     * 添加头信息
     *
     * @param titles 列名
     * @return 自身对象
     */
    public ConsoleTable addHeader(String... titles) {
        if (columnCharNumber == null) {
            columnCharNumber = new ArrayList<>(Collections.nCopies(titles.length, 0));
        }
        List<String> l = new ArrayList<>();
        fillColumns(l, titles);
        HEADER_LIST.add(l);
        return this;
    }

    /**
     * 添加体信息
     *
     * @param values 列值
     * @return 自身对象
     */
    public ConsoleTable addBody(String... values) {
        List<String> l = new ArrayList<>();
        BODY_LIST.add(l);
        fillColumns(l, values);
        return this;
    }

    /**
     * 填充表格头或者体
     *
     * @param l       被填充列表
     * @param columns 填充内容
     */
    private void fillColumns(List<String> l, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];
            String col = convertCBC2SBC(column);
            l.add(col);
            int width = col.length();
            if (width > columnCharNumber.get(i)) {
                columnCharNumber.set(i, width);
            }
        }
    }

    /**
     * 获取表格字符串
     *
     * @return 表格字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        fillBorder(sb);
        fillRow(sb, HEADER_LIST);
        fillBorder(sb);
        fillRow(sb, BODY_LIST);
        fillBorder(sb);
        return sb.toString();
    }

    /**
     * 填充表头或者表体信息
     *
     * @param sb 内容
     * @param list 表头列表或者表体列表
     */
    private void fillRow(StringBuilder sb, List<List<String>> list) {
        for (List<String> r : list) {
            for (int i = 0; i < r.size(); i++) {
                if (i == 0) {
                    sb.append(COLUMN_LINE);
                }
                String header = r.get(i);
                sb.append(SPACE);
                sb.append(header);
                sb.append(SPACE);
                int l = header.length();
                int lw = columnCharNumber.get(i);
                if (lw > l) {
                    for (int j = 0; j < (lw - l); j++) {
                        sb.append(SPACE);
                    }
                }
                sb.append(COLUMN_LINE);
            }
            sb.append(LF);
        }
    }

    /**
     * 拼装边框
     *
     * @param sb StringBuilder
     */
    private void fillBorder(StringBuilder sb) {
        sb.append(CORNER);
        for (Integer width : columnCharNumber) {
            sb.append(convertCBC2SBC(fill(width + 2)));
            sb.append(CORNER);
        }
        sb.append(LF);
    }

    /**
     * 将半角字符转换为全角字符
     *
     * @param input 半角字符
     * @return 全角字符
     */
    private String convertCBC2SBC(String input){
        final char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 填充指定长度的 {@link #ROW_LINE}
     * @param len 长度
     * @return 字符串
     */
    private String fill(int len) {
        final int strLen = "".length();
        if (strLen > len) {
            return "";
        }
        String filledStr = repeat(ConsoleTable.ROW_LINE, len - strLen);
        return "".concat(filledStr);
    }

    /**
     * 重复字符 n 次
     * @param c 字符
     * @param count 重复次数
     * @return 结果字符串
     */
    private String repeat(char c,int count){
        if (count <= 0) {
            return "";
        }
        char[] result = new char[count];
        for (int i = 0; i < count; i++) {
            result[i] = c;
        }
        return new String(result);
    }

    /**
     * 打印到控制台
     */
    public void print() {
        System.out.print(toString());
    }

}
