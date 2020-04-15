package com.jeecms.wdedu.action;


import com.alibaba.fastjson.JSON;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TCeeApplications;
import com.jeecms.wdedu.service.CommonSvc;


import com.utils.StringUtil;


import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Copyright (C),
 * FileName:
 * Author:
 * Date:
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 **/
@Controller
@RequestMapping(value = "pdf")
public class ReportController {
    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Logger logger=Logger.getLogger("testlog");

    @RequestMapping(value = "/createPdf.jspx")
    public String report(HttpServletRequest request, ModelMap model, String applicationId, String provinceId) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

//        String reqSql = "SELECT * FROM t_cee_applications_require\n" +
//                "WHERE application_id = '" + applicationId + "'";
//        List<Map<String, Object>> reqList = commonSvc.findForJdbc(reqSql);

//        for (int i = 0; i < reqList.size(); i++) {
//            final String query = "CALL getAutoChooseZhiyuanSolution (" + applicationId + ", " + reqList.get(i).get("batch_id") + ")";
//            try {
//                executeVoidProcedureSql(query, null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        final String getEstimateReportQuery = "CALL getEstimateReport("+applicationId+")";
        try {
            executeVoidProcedureSql(getEstimateReportQuery,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用户id
        int userId = user.getId();
/*        *//*查询表中pdfurl是否存在,1.存在则网页调用pdf*//*
        String pdfUrl="SELECT pdfUrl FROM t_cee_applications where id="+applicationId;
        List<Map<String, Object>> pdfUrlList = commonSvc.findForJdbc(pdfUrl);
        if(pdfUrlList.size()>0&&!StringUtil.isEmpty((String)pdfUrlList.get(0).get("pdfUrl"))){
            model.addAttribute("pdfurl", (String) pdfUrlList.get(0).get("pdfUrl"));
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "viewer");
        }*/
        /*2。不存在则生成网页*/
        //姓名
        String nameSql = "SELECT * FROM `jc_user_ext` WHERE user_id = " + userId + "";
        List<Map<String, Object>> nameList = commonSvc.findForJdbc(nameSql);
        String name = String.valueOf(nameList.get(0).get("realname"));
        model.addAttribute("name", name);
        //省份
        String proSql = "SELECT * FROM `t_base_province` WHERE province_id = (SELECT attr_value FROM `jc_user_attr` WHERE user_id = '" + userId + "' AND attr_name = 'province_id')";
        List<Map<String, Object>> proList = commonSvc.findForJdbc(proSql);
        String proName = String.valueOf(proList.get(0).get("province_name"));
        model.addAttribute("proName", proName);
        //文理科
        String majorSql = "SELECT * FROM jc_user_attr WHERE user_id = '" + userId + "' AND attr_name ='major_type_id' ";
        List<Map<String, Object>> majorList = commonSvc.findForJdbc(majorSql);
//        String major = String.valueOf(majorList.get(0).get("attr_value"));
//        model.addAttribute("major", major);
        //成绩.排名
        String score_rankSql = "SELECT * FROM `t_cee_applications` WHERE id = '" + applicationId + "'";
        List<Map<String, Object>> score_rankList = commonSvc.findForJdbc(score_rankSql);
        String score = String.valueOf(score_rankList.get(0).get("score"));
        String rank = String.valueOf(score_rankList.get(0).get("rank"));
        model.addAttribute("socre", score);
        model.addAttribute("rank", rank);
        model.addAttribute("rankJson", JSON.toJSONString(rank));
        String major = String.valueOf(score_rankList.get(0).get("major_type_id"));
        model.addAttribute("major", major);
        //批次个数(方案完整性)
        //查询t_base_province表当前省份有数据的年份
        //TBaseProvince tBaseProvince = commonSvc.findUniqueByProperty(TBaseProvince.class, "province_id", provinceId);
        String yearSql = "SELECT data_batch_year FROM t_base_province WHERE province_id=" + provinceId + ";";
        //int year = tBaseProvince.getDataBatchYear();
        List<Map<String, Object>> yearList = commonSvc.findForJdbc(yearSql);
        String year = String.valueOf(yearList.get(0).get("data_batch_year"));
        String batchNumSql = "SELECT a.*,b.yxNum FROM \n" +
                "(SELECT * FROM t_cee_batch a WHERE YEAR = "+year +" AND province_id = '"+provinceId+"' AND major_type_id = "+major+" AND is_show = 1 ) a\n" +
                "LEFT JOIN(SELECT *,COUNT(*) AS yxNum FROM t_cee_applications_estimate\n" +
                "WHERE application_id = '"+applicationId+"'\n" +
                "GROUP BY application_id,YEAR,province_id,major_type_id,batch_id ) b\n" +
                "ON a.year = b.year\n" +
                "AND a.province_id = b.province_id\n" +
                "AND a.major_type_id = b.major_type_id\n" +
                "AND a.batch_id= b.batch_id ORDER BY `order` ASC";
        //本科提高A段、本科第一批次、本科第二批次、专科提前、专科批次、专科提前录取B段、专科提前录取C段、特殊院校批次
        List<Map<String, Object>> batchNumList = commonSvc.findForJdbc(batchNumSql);
        String programme = "";
        for (int i = 0; i < batchNumList.size(); i++) {
            programme += batchNumList.get(i).get("batch_name") + "志愿设置" + batchNumList.get(i).get("univ_num") + "个平行院校，每个院校设置" + batchNumList.get(i).get("major_num") + "个专业志愿。";
        }
        programme+="完整填报很有必要，不要放弃任何一个机会。";
        model.addAttribute("programme", programme);
        model.addAttribute("batchNumList", batchNumList);
        model.addAttribute("batchNumListJson", JSON.toJSONString(batchNumList));
        //筛选条件 stu_user_id = " + userId + " and
        String conditionSql = "SELECT a.*,c.batch_name FROM t_cee_applications_require a , t_cee_applications b , t_cee_batch c WHERE a.application_id = '" + applicationId + "' AND a.application_id = b.id and b.year = c.year and b.province_id = c.province_id AND b.major_type_id = c.major_type_id AND a.batch_id = c.batch_id ";
        List<Map<String, Object>> conditionList = commonSvc.findForJdbc(conditionSql);
        model.addAttribute("conditionList", conditionList);
        //完整性检查+风险提示
        String volAllSql = "SELECT a.*,b.major_num,COUNT(*) AS yixuanNum FROM t_cee_applications_estimate a,t_cee_batch b" +
                " WHERE a.application_id = " + applicationId + "" +
                " AND a.year = b.year " +
                " AND a.province_id = b.province_id" +
                " AND a.major_type_id = b.major_type_id" +
                " AND a.batch_id= b.batch_id AND a.data_type=0 " +
                " GROUP BY a.batch_id,a.union_id "+
                " ORDER BY a.batch_id ASC, a.is_formal ASC ,a.univ_order ASC ,a.major_order ASC ";
        List<Map<String, Object>> volAllList = commonSvc.findForJdbc(volAllSql);
        model.addAttribute("volAllList", volAllList);
        //风险提示
//        String volRiskSql = "SELECT * FROM t_cee_applications_estimate" +
//                " WHERE application_id = " + applicationId + " " +
//                "AND data_type = 0;";
//        List<Map<String, Object>> volRiskList = commonSvc.findForJdbc(volRiskSql);
//        model.addAttribute("volRiskList", volRiskList);
        //平行志愿评估
        String volAssSql = "SELECT  d.*,c.majornum  FROM (\n" +
                "SELECT a.*,b.descr,b.kyjg,b.zszc_yd FROM t_cee_applications_estimate a ,  t_data_university_detail b \n" +
                "WHERE a.application_id = '"+applicationId+"' \n" +
                "AND LEFT(a.union_id,5) = b.Id  \n" +
                "AND a.data_type = 0 \n" +
                "ORDER BY a.batch_id ASC, a.is_formal ASC ,a.univ_order ASC ,a.major_order ASC \n" +
                ") d\n" +
                "LEFT JOIN (\n" +
                "SELECT *,COUNT(*) AS majornum FROM t_cee_applications_estimate\n" +
                "WHERE application_id = '"+applicationId+"' \n" +
                "AND data_type = 1 \n" +
                "GROUP BY YEAR,province_id,major_type_id,batch_id,union_id ) c\n" +
                "ON d.application_id = c.application_id\n" +
                "AND d.year = c.year\n" +
                "AND d.province_id=c.province_id\n" +
                "AND d.major_type_id = c.major_type_id\n" +
                "AND d.batch_id = c.batch_id\n" +
                "AND d.union_id = c.union_id "+"ORDER BY univ_order ASC";
        List<Map<String, Object>> volAssList = commonSvc.findForJdbc(volAssSql);
        model.addAttribute("volAssList", volAssList);
        model.addAttribute("volAssListJson", JSON.toJSONString(volAssList));
        if (volAllList.size()>0){
            if (volAllList.get(0).get("enroll_year1")!=null){
                int enrollYear1 = (int) volAllList.get(0).get("enroll_year1");
                model.addAttribute("enrollYear1",enrollYear1);
            }
            if (volAllList.get(0).get("enroll_year2")!=null){
                int enrollYear2 = (int) volAllList.get(0).get("enroll_year2");
                model.addAttribute("enrollYear2",enrollYear2);
            }
            if (volAllList.get(0).get("enroll_year3")!=null){
                int enrollYear3 = (int) volAllList.get(0).get("enroll_year3");
                model.addAttribute("enrollYear3",enrollYear3);
            }
        }
        String volAssSql2 = "SELECT * FROM t_cee_applications_estimate " +
                "WHERE application_id = " + applicationId + " " +
                "AND data_type = 1 " +
                "ORDER BY batch_id ASC, is_formal ASC ,univ_order ASC ,major_order ASC ;";
        List<Map<String, Object>> volAssList2 = commonSvc.findForJdbc(volAssSql2);
        model.addAttribute("volAssList2", volAssList2);
        model.addAttribute("volAssList2Json", JSON.toJSONString(volAssList2));
        //志愿无忧专家总结
        String summarySql = "SELECT remark FROM `t_cee_applications`  " +
                "WHERE id = " + applicationId + " ;";
        List<Map<String, Object>> summaryList = commonSvc.findForJdbc(summarySql);
        model.addAttribute("summaryList", summaryList);
        //大学评估数据
        //用户的省份
        String province = user.getAttr().get("province_id");
        //用户的文理科
        String majorType = user.getAttr().get("major_type_id");
        //查询t_base_province表当前省份有数据的年份
        TBaseProvince tBaseProvince1 = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(province));
        int year1 = tBaseProvince1.getDataBatchYear();

        String batchSql = "SELECT * FROM `t_cee_applications_require` WHERE stu_user_id = '" + user.getId() + "' AND application_id='" + applicationId + "' ";
       /* List<Map<String, Object>> batchList = commonSvc.findForJdbc(batchSql);
        for (int i = 0; i < batchList.size(); i++) {
            final String query = "CALL getAutoZhiYuanSolution(" + applicationId + ", '" + batchList.get(i).get("batch_id") + "','','1','-1')";
            List<Map<String, Object>> list = find_sql(query, null);
            model.addAttribute("batchList" + i, list);
        }*/
        model.addAttribute("applicationId", applicationId);
       // return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "viewer");
        TCeeApplications applicationsInfo = commonSvc.findUniqueByProperty(TCeeApplications.class, "id", Integer.valueOf(applicationId));
        model.addAttribute("applicationsInfo", applicationsInfo);
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        Map<String, String> provinceMap = new HashMap<>();
        for (TBaseProvince t : provinceList) {
            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
        }
        model.addAttribute("provinceMap", provinceMap);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "assessmentReport");
    }

    public List<Map<String, Object>> find_sql(String queryString, Object[] params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(queryString);

//        if (null != params) {
//            for (int i = 0; i < params.length; i++) {
//                query.setParameter(i, params[i]);
//            }
//        }
        List<Map<String, Object>> list = query.list();
        return list ;
    }

    @RequestMapping(value = "/generatePdf.jspx")
    @ResponseBody
    public void generatePdf(@RequestParam(value = "file", required = false)String file, String applicationId,HttpServletRequest request,String  t, HttpServletResponse response) throws IOException {
        FileOutputStream fileOutputStream = null;
        byte[] bytes = null;
        Map<String,String> map=new HashMap<>();
        //用户id
        if (!StringUtil.isEmpty(file)) {
            file = URLEncoder.encode(file, "UTF-8").replaceAll("%EF%BC%852B", "\\+").replaceAll("%2F", "\\/");
            bytes = Base64.decodeBase64(file);
        } else {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader();) {
                char[] buff = new char[1024];
                int len;
                while ((len = reader.read(buff)) != -1) {
                    sb.append(buff, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sb1 = sb.toString().substring(5);
            sb1 = URLEncoder.encode(sb1, "UTF-8").replaceAll("%25252B", "\\+").replaceAll("%252F", "\\/");
            bytes = Base64.decodeBase64(sb1);
        }

       /*查询该用户下是否有pdfUrl，有则存入原来的路径，没有则生成新的文件路径*/
     /*   String pdfUrl="SELECT pdfUrl FROM t_cee_applications where id="+applicationId;
        List<Map<String, Object>> pdfUrlList = commonSvc.findForJdbc(pdfUrl);*/
        try {
     /*  if (pdfUrlList.size()>0&&!StringUtil.isEmpty((String)pdfUrlList.get(0).get("pdfUrl"))) {
            fileOutputStream = new FileOutputStream((String) pdfUrlList.get(0).get("pdfUrl"));
            }else {*/

           //3.将路径存入数据库表
           //本地存储
       /*  fileOutputStream = new FileOutputStream("/Users/wumin/IdeaProjects/wdedu-svn/trunk/WebContent/u/cms/pdf/" + t + ".pdf");
          String fileSql = "update t_cee_applications set pdfUrl='/Users/wumin/IdeaProjects/wdedu-svn/trunk/WebContent/u/cms/pdf/" + t + ".pdf' " +
                   "where id=" + applicationId;*/
           // String path3 =  request.getScheme()+"://"+ request.getServerName() +":" +request.getServerPort();

           //服务器上存储路径
          /* String path1 = "/mnt/service/tomcat718080-wdedu/";
           String path2 = request.getContextPath() + "/u/cms/www/report/" + t + ".pdf";
           logger.info("pdfurl+++++++++++" + path1 + path2);*/
           fileOutputStream = new FileOutputStream("D:\\"+ t + ".pdf");
/*           String fileSql = "UPDATE t_cee_applications SET pdfUrl = '" + path2 + "' WHERE id = " + applicationId;//服务器上更新地址
           commonSvc.executeSql(fileSql);
           logger.info("存入数据库成功+++++++++++" + fileSql);*/

     //  }
            fileOutputStream.write(bytes);
            logger.info("生成pdf成功+++++++++++");
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        map.put("msg","生成pdf成功！");
        String json = JSON.toJSONString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(json);

    }


    public void executeVoidProcedureSql(final String queryString, final Object[] params) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                ResultSet rs = null;
                CallableStatement call = conn.prepareCall("{" + queryString + "}");
                if (null != params) {
                    for (int i = 0; i < params.length; i++) {
                        call.setObject(i + 1, params[i]);
                    }
                }
                rs = call.executeQuery();
                call.close();
                rs.close();
            }
        });
    }

}
