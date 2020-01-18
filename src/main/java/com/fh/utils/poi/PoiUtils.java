package com.fh.utils.poi;

import com.fh.annotation.ExportExcel;
import com.fh.annotation.ExportTitle;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Lenovo
 * @title: PoiUtils
 * @projectName service_student_web
 * @description: TODO
 * @date 2020/1/1412:08
 */
public class PoiUtils {
    private static Logger log = Logger.getLogger(PoiUtils.class);

    public static void excelUtil(List list, HttpServletResponse response){
        //处理标题==========================================================================================================================================================================================
        Object o = list.get(0);//得到要下载的对象
        if(o!=null){
            Class aClass = o.getClass();//得到类对象
            //获取标题名，标题名是加在了注解上
            ExportTitle annotation = (ExportTitle) aClass.getAnnotation(ExportTitle.class);
            //标题名
            String title = annotation.title();
            //创建workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建sheet页
            XSSFSheet sheet = workbook.createSheet(title);//赋予sheet页的名字就是类上的标题名
        // 处理列头==========================================================================================================================================================================================
            //创建列头行
            XSSFRow row = sheet.createRow(0);//这一行就是表头
            //获取类的所有属性
            Field[] declaredFields = aClass.getDeclaredFields();
            int cellRowNum=0;
            for (int i = 0; i < declaredFields.length; i++) {
                //具体每个属性
                Field declaredField = declaredFields[i];
                //判断属性上是否有注解，是否是我们要导出的
                ExportExcel exportExcel = declaredField.getAnnotation(ExportExcel.class);
                if(exportExcel!=null){//不为空就是要导出的
                    //获取要导出的字段名
                    String name = exportExcel.name();
                    //将要导出的字段放入列中
                    XSSFCell cell = row.createCell(cellRowNum);
                    cell.setCellValue(name);
                    cellRowNum++;
                }
            }
        //处理列数据==========================================================================================================================================================================================
            for (int i = 0; i < list.size(); i++) {
                //获取具体数据
                Object o1 = list.get(i);
                //创建数据行
                XSSFRow row1 = sheet.createRow(i + 1);//因为下标为 0是列头了
                int cellNum=0;
                for (int j = 0; j < declaredFields.length; j++) {
                    //获取每一个属性
                    Field declaredField = declaredFields[j];
                    //判断有没有注解
                    boolean annotationPresent = declaredField.isAnnotationPresent(ExportExcel.class);
                    if(annotationPresent==true){ //有
                        //拆解创建列
                        XSSFCell cell = row1.createCell(cellNum);
                        try {
                            //获取属性的值，在反射中对于对象来说
                            //因为是私有的所有设置一下暴力破解
                            declaredField.setAccessible(true);
                            Object o2 = declaredField.get(o1);

                            if(o2!=null){
                                //判断一下属性的类型
                                Class type = declaredField.getType();
                                //data类型
                                if(type==Date.class){
                                    //date类型处理
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date= (Date) o2;//强转date
                                    String format = sdf.format(date);
                                    cell.setCellValue(format);
                                }
                                //string类型
                                if (type==String.class){
                                    cell.setCellValue(o2.toString());
                                }
                                //Integer类型
                                if(type==Integer.class){
                                    cell.setCellValue(Integer.valueOf(o2.toString()));
                                }
                                //BigDecimal类型
                                if (type==BigDecimal.class){
                                    CellStyle style = workbook.createCellStyle();
                                    BigDecimal bigDecimal = new BigDecimal(o2.toString());
                                    /*
                                     * 最初的val是没有类型的需要强转，既然强转为什么不直接转成Double类型,而是这样转成BigDecimal类型，
                                     * 再用doubleValue方法转呢，完全可以这样做double doubleVal = (Double)val;在编译的时候不会报错，
                                     * 运行时候会抱异常 java.lang.ClassCastException: java.math.BigDecimal cannot be cast to java.lang.Double
                                     * 经实践还是用下面的代码才可以导出的数据上面没有小三角。
                                     */
                                    double doubleVal = ((BigDecimal) o2).doubleValue();
                                    DataFormat format = workbook.createDataFormat();
                                    //此格式是货币格式
                                    style.setDataFormat(format.getFormat("￥#,##0.00"));
                                    cell.setCellValue(doubleVal);
                                    cell.setCellStyle(style);
                                }
                            }else {
                                cell.setCellValue("");
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        cellNum++;//本列加一
                    }
                }

            }
        //下载==========================================================================================================================================================================================
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=\""+ UUID.randomUUID().toString()+".xlsx\"");

            ServletOutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            log.error("要导出的数据为空");
        }

    }
}
