package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionDao;
import com.itheima.domain.store.Question;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public String save(Question question, boolean flag) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            question.setId(id);

            //设置新创建的题目默认的审核状态为未审核（0）
            //此功能需添加 现在直接为1 即审核通过
            question.setReviewStatus("1");
            question.setCreateTime(new Date());
            //检测到前端上传文件了，记录文件名，否则不记录
            if(flag) {
                //设置当前存储的图片名称为id值
                question.setPicture(id);
            }
            //3.调用Dao层操作
            questionDao.save(question);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
            return id;
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //3.调用Dao层操作
            questionDao.delete(question);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Question question, boolean flag) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            //检测到前端上传文件了，记录文件名，否则不记录
            if(flag) {
                //设置当前存储的图片名称为id值
                question.setPicture(question.getId());
            }

            //3.调用Dao层操作
            questionDao.update(question);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update_review() {

    }

    @Override
    public Question findById(String id) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //3.调用Dao层操作
            return questionDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Question> findAll() {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //3.调用Dao层操作
            return questionDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page,size);
            List<Question> all = questionDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ByteArrayOutputStream getReport() throws IOException{
        //获取对应要展示的数据
        SqlSession sqlSession = null;
        List<Question> questionList = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            //3.调用Dao层操作
            questionList = questionDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        //1.获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();
        //2.创建工作表
        Sheet s = wb.createSheet("题目数据文件");
        //设置通用配置
//        s.setColumnWidth(4,100);
        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);
        //制作标题
        s.addMergedRegion(new CellRangeAddress(1,1,1,12));
        Row row_1 = s.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");
        //创建一个样式
        CellStyle cs_title = wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cs_title);


        //制作表头
        String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        Row row_2 = s.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i); //++
            cell_2_temp.setCellValue(fields[i]);    //++
            cell_2_temp.setCellStyle(cs_field);
        }
        //制作数据区
        int row_index = 0;
        for (Question q : questionList) {
            int cell_index = 0;
            Row row_temp = s.createRow(3 + row_index++);

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());    //++
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());    //++
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());    //++
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());    //++
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());    //++
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());    //++
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());    //++
            cell_data_7.setCellStyle(cs_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());    //++
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());    //++
            cell_data_9.setCellStyle(cs_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());    //++
            cell_data_10.setCellStyle(cs_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());    //++
            cell_data_11.setCellStyle(cs_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());    //++
            cell_data_12.setCellStyle(cs_field);
        }


        //将内存中的workbook数据写入到流中
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        wb.close();
        os.close();
        return os;
    }
}









