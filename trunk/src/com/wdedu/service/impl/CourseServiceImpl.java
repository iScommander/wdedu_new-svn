package com.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.wdedu.dao.ICourseDao;
import com.wdedu.service.ICourseService;
import com.jeecms.wdedu.service.CommonSvc;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;


@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;

    @Autowired
    private CommonSvc commonService;

    @Override
    public List<Map<String, Object>> selectByCode(String sql) {

        return courseDao.findSqlList(sql);
    }

    /* (non-Javadoc)
     * @see com.sygh.service.ICourseService#findkmList(java.lang.String)
     */
    @Override
    public Pagination findkmList(Integer provinceId, Integer year, Integer stuid, String majorNames, Integer pageNo, Integer pageSize, String univ_name, String major_name) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT s.*,f.student FROM t_majorsubjectselector s LEFT JOIN  t_majorFollow f ON f.selector=s.id AND f.student=" + stuid);

        sql.append(" where 1=1 ");
        if (provinceId != null && provinceId != 0) {
            sql.append(" and s.province_id = " + provinceId);
        }
        if (year != null && year != 0) {
            sql.append(" AND s.year =" + year);
        }



        if (majorNames != null && majorNames != "") {
            String majorNamesTmp=majorNames.replace("m","");
            String[] majorname = majorNamesTmp.split(",");
            StringBuffer sb = new StringBuffer();
            for (String string : majorname) {
                sb.append("'" + string + "',");
            }
            sql.append(" and major_id IN (" + sb.toString().subSequence(0, sb.length() - 1) + ")");
        }
//        sql.append(" ORDER BY s.id");

//        院校名称
        if (StringUtils.isNotBlank(univ_name)) {
            sql.append("\nAND s.university_name LIKE '%"+univ_name+"%'");
        }
//        专业名称
        if (StringUtils.isNotBlank(major_name)) {
            sql.append("\nAND s.major_name LIKE '%"+major_name+"%'");
        }

        List<Map<String, Object>> list = commonService.findForJdbcParam(sql.toString(), cpn(pageNo), pageSize, null);

        String totalCountSql = "select count(1) from (" + sql + ") as tab";
        long totalCount = (long) commonService.getCountForJdbcParam(totalCountSql, null);
        Pagination pagination = new Pagination();
        pagination.setList(list);
        pagination.setPageNo(cpn(pageNo));
        pagination.setPageSize(pageSize);
        if (totalCount != 0) {
            pagination.setTotalCount(new Long(totalCount).intValue());
        }

        return pagination;
    }

    /**
     * 根据科目选专业
     */
    @Override
    public Pagination findXkList(Integer provinceId, Integer year, Integer stuid, String subjects, Integer limitType, String andOr0, String unlimited0, Integer pageNo, Integer pageSize, String univ_name, String major_name) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT s.*,f.student FROM t_majorsubjectselector s LEFT JOIN  t_majorFollow f ON f.selector=s.id AND  f.student=" + stuid);

        sb.append(" where 1=1 ");
        if (provinceId != null && provinceId != 0) {
            sb.append(" and s.province_id = " + provinceId);
        }
        if (year != null && year != 0) {
            sb.append(" AND s.year =" + year);
        }

        String sbUnlimit = sb.toString();

        if (limitType != null) {

            if (limitType == 1) {
                if (!StringUtils.isBlank(subjects)) {
                    String[] subjectList = subjects.split(",");
                    int size = subjectList.length;
//                    sb.append(" AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0\n" +
//                            "AND \n" +
//                            "(s.subjectlimit_additional = '不限'\n" +
//                            "OR LOCATE('" + subjectList[1] + "',s.subjectlimit_additional) > 0\n" +
//                            "OR LOCATE('" + subjectList[2] + "',s.subjectlimit_additional) > 0\n" +
//                            ")");
                    if (size == 1) {
                        sb.append("\n#只选首选\n" +
                                "AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 \n" +
                                "AND s.subjectlimit_additional <> '不限'");
                    } else if (size == 2) {
                        if (andOr0.equals("0")) {
                            sb.append("\n#选一门次选 可选\n" +
                                    "AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 \n" +
                                    "AND LOCATE('" + subjectList[1] + "',s.subjectlimit_additional) > 0 ");
                        } else if (andOr0.equals("1")) {
                            sb.append("#选一门次选 必选 \n" +
                                    "AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 \n" +
                                    "AND s.subjectlimit_additional = '" + subjectList[1] + "'\n" +
                                    "OR\n" +
                                    "(LOCATE('" + subjectList[1] + "',s.subjectlimit_additional) > 0 AND LOCATE('+',s.subjectlimit_additional) > 0 )");
                        }
                    } else if (size == 3) {
                        if (andOr0.equals("0")) {
                            sb.append("\n#选两门次选 可选\n" +
                                    "AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 \n" +
                                    "AND\n" +
                                    "(LOCATE('" + subjectList[1] + "',s.subjectlimit_additional) > 0 \n" +
                                    "OR \n" +
                                    "LOCATE('" + subjectList[2] + "',s.subjectlimit_additional) > 0 )\n");
                        } else if (andOr0.equals("1")) {
                            sb.append("\n#选两门次选 必选\n" +
                                    "AND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0\n" +
                                    "AND LOCATE('" + subjectList[1] + "',s.subjectlimit_additional) > 0 \n" +
                                    "AND LOCATE('" + subjectList[2] + "',s.subjectlimit_additional) > 0 \n" +
                                    "AND LOCATE('+',s.subjectlimit_additional) > 0 \n");
                        }
                    }
                    /*是否不限*/
                    if (StringUtils.isNotBlank(unlimited0)) {
                        if (size >= 1) {
                            sbUnlimit += "\nAND LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0\n" +
                                    "AND s.subjectlimit_additional = '不限'";
                            sb.append("\nUNION\n" + sbUnlimit);
                        }
                    }
                }
            } else if (limitType == 0) {
//                    sb.append(" AND (\n" +
//                            "s.subjectlimit = '不限'\n" +
//                            "OR (LOCATE('+',s.subjectlimit) = 0 AND (LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 OR LOCATE('" + subjectList[1] + "',s.subjectlimit) > 0 OR LOCATE('" + subjectList[2] + "',s.subjectlimit) > 0 ))\n" +
//                            "OR (LOCATE('+',s.subjectlimit) > 0 AND ( (LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 AND LOCATE('" + subjectList[1] + "',s.subjectlimit) > 0) OR (LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 AND LOCATE('" + subjectList[2] + "',s.subjectlimit) > 0) OR (LOCATE('" + subjectList[1] + "',s.subjectlimit) > 0 AND LOCATE('" + subjectList[2] + "',s.subjectlimit) > 0) OR (LOCATE('" + subjectList[0] + "',s.subjectlimit) > 0 AND LOCATE('" + subjectList[1] + "',s.subjectlimit) > 0) AND LOCATE('" + subjectList[2] + "',s.subjectlimit) > 0) )\n" +
//                            ")");
                if (!StringUtils.isBlank(subjects)) {
                    String[] subjectList0 = subjects.split(",");
                    int size0 = subjectList0.length;
                    if (size0 == 3) {
                        if (andOr0.equals("0")) {
                            sb.append("\nAND (LOCATE('" + subjectList0[0] + "',s.subjectlimit) > 0 \n" +
                                    "OR LOCATE('" + subjectList0[1] + "',s.subjectlimit) > 0 \n" +
                                    "OR LOCATE('" + subjectList0[2] + "',s.subjectlimit) > 0 )");
                        } else if (andOr0.equals("1")) {
                            sb.append("\nAND LOCATE('" + subjectList0[0] + "',s.subjectlimit) > 0\n" +
                                    "AND LOCATE('" + subjectList0[1] + "',s.subjectlimit) > 0\n" +
                                    "AND LOCATE('" + subjectList0[2] + "',s.subjectlimit) > 0 \n" +
                                    "AND LOCATE('+',subjectlimit) > 0 ");
                        }
                    } else if (size0 == 2) {
                        if (andOr0.equals("0")) {
                            sb.append("\nAND (LOCATE('" + subjectList0[0] + "',s.subjectlimit) > 0 \n" +
                                    "OR LOCATE('" + subjectList0[1] + "',s.subjectlimit) > 0 )");
                        } else if (andOr0.equals("1")) {
                            sb.append("\nAND LOCATE('" + subjectList0[0] + "',s.subjectlimit) > 0\n" +
                                    "AND LOCATE('" + subjectList0[1] + "',s.subjectlimit) > 0\n" +
                                    "AND LOCATE('+',subjectlimit) > 0 ");
                        }
                    } else if (size0 == 1) {
                        sb.append("\nAND LOCATE('" + subjectList0[0] + "',s.subjectlimit) > 0 ");
                    }
                    /*是否不限*/
                    if (StringUtils.isNotBlank(unlimited0)) {
                        if (size0 >= 1) {
                            sbUnlimit += "\nAND s.subjectlimit = '不限'";
                            sb.append("\nUNION\n" + sbUnlimit);
                        }
                    }
                }
            }
        }
        //        sb.append(" ORDER BY s.id");
        String sbStr = sb.toString();

        if (StringUtils.isNotBlank(univ_name) || StringUtils.isNotBlank(major_name)) {
            StringBuffer sbOther = new StringBuffer();
            sbOther.append("SELECT two.* FROM ( \n");
            sbOther.append(sbStr);
            sbOther.append(") two \n" +
                    "WHERE 1 = 1");
            if (StringUtils.isNotBlank(univ_name)) {
                sbOther.append("\nAND two.university_name LIKE '%" + univ_name + "%'");
            }
            if (StringUtils.isNotBlank(major_name)) {
                sbOther.append("\nAND two.major_name LIKE '%" + major_name + "%'");
            }
            sbStr = sbOther.toString();
        }
        List<Map<String, Object>> list = commonService.findForJdbcParam(sbStr, cpn(pageNo), pageSize, null);
        String totalCountSql = "select count(1) from (" + sbStr + ") as tab";
        long totalCount = (long) commonService.getCountForJdbcParam(totalCountSql, null);
        Pagination pagination = new Pagination();
        pagination.setList(list);
        pagination.setPageNo(cpn(pageNo));
        pagination.setPageSize(pageSize);
        if (totalCount != 0) {
            pagination.setTotalCount(new Long(totalCount).intValue());
        }
        return pagination;
    }

    /**
     * 根据职业选科目
     *
     * @param
     */
    @Override
    public Pagination findZyList(Integer provinceId, Integer year, Integer stuid, String careerIds, Integer pageNo, Integer pageSize, String univ_name, String major_name) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT DISTINCT  *  FROM t_career_info   WHERE 1 = 1    ");
        if (careerIds != null && careerIds != "") {
            String[] careercodes = careerIds.split(",");
            StringBuffer sb = new StringBuffer();
            for (String string : careercodes) {
                sb.append("'" + string + "',");
            }
            sql.append("  AND careerId  IN (" + sb.toString().subSequence(0, sb.length() - 1) + ")");
        }

        List<Map<String, Object>> list1 = commonService.findForJdbcParam(sql.toString(), cpn(pageNo), pageSize, null);

        StringBuffer ss = new StringBuffer();
        ss.append("SELECT  s.*, f.student FROM t_majorsubjectselector s LEFT JOIN  t_majorFollow f  ON f.selector=s.id AND  f.student=" + stuid);
        StringBuffer sb = new StringBuffer();
        for (Map<String, Object> map : list1) {
            sb.append("'" + map.get("majorId") + "',");
        }

        ss.append("  WHERE 1 =1 ");
        if (sb.length() > 0) {
            ss.append(" and major_id IN (" + sb.toString().subSequence(0, sb.length() - 1) + ")");
        }
        if (provinceId != null && provinceId != 0) {
            ss.append(" and s.province_id = " + provinceId);
        }
        if (year != null && year != 0) {
            ss.append(" AND s.year =" + year);
        }
//        ss.append("  ORDER BY s.id");

//        院校名称
        if (StringUtils.isNotBlank(univ_name)) {
            ss.append("\nAND s.university_name LIKE '%"+univ_name+"%'");
        }
//        专业名称
        if (StringUtils.isNotBlank(major_name)) {
            ss.append("\nAND s.major_name LIKE '%"+major_name+"%'");
        }
        List<Map<String, Object>> list = commonService.findForJdbcParam(ss.toString(), cpn(pageNo), pageSize, null);

        for (Map<String, Object> map : list) {
            String careername = "";

            for (Map<String, Object> map1 : list1) {
                if (map1.get("majorId") == null || map1.get("majorId") == "") {
                    careername += "";
                } else {
                    if (map.get("major_id").toString().equals(map1.get("majorId").toString())) {
                        if (map.get("career") == null) {
                            careername += "";
                        } else {
                            careername += map.get("career") + ",";
                        }
                    }
                }
                map.put("careername", careername);
            }
        }

        String totalCountSql = "select count(1) from (" + ss + ") as tab";
        long totalCount = (long) commonService.getCountForJdbcParam(totalCountSql, null);
        Pagination pagination = new Pagination();
        pagination.setList(list);
        pagination.setPageNo(cpn(pageNo));
        pagination.setPageSize(pageSize);
        if (totalCount != 0) {
            pagination.setTotalCount(new Long(totalCount).intValue());
        }
        return pagination;
    }

    /**
     * 查询职业一级List
     */
    public List<Map<String, Object>> selectFirstList() {

        String FRSql = "SELECT distinct  catalogCode1,catalog1 FROM t_career_info ";

        List<Map<String, Object>> firstList = commonService.findForJdbc(FRSql, null);

        return firstList;
    }

    /**
     * 查询职业二级List
     */
    public List<Map<String, Object>> selectSecondList() {

        String SESql = "SELECT distinct  catalogCode1,catalogCode2,catalog2 FROM t_career_info WHERE catalogCode1 IN(SELECT catalogCode1 FROM t_career_info )";

        List<Map<String, Object>> secondList = commonService.findForJdbc(SESql, null);

        return secondList;
    }

    /**
     * 查询职业三级List
     */
    public List<Map<String, Object>> selectThirdList() {

        String THSql = "SELECT distinct  catalogCode1,catalogCode2,careerId,career FROM t_career_info WHERE  catalogCode2 IN(SELECT catalogCode2 FROM t_career_info)";
        List<Map<String, Object>> thirdList = commonService.findForJdbc(THSql, null);
        return thirdList;
    }
}




