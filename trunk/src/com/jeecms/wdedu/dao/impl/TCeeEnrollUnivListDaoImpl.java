package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.TCeeEnrollUnivListDao;
import com.jeecms.wdedu.entity.TCeeEnrollUnivList;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtil;
import com.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TCeeEnrollUnivListDaoImpl extends HibernateBaseDao<TCeeEnrollUnivList, Integer> implements TCeeEnrollUnivListDao {
    @Override
    protected Class<TCeeEnrollUnivList> getEntityClass() {
        return TCeeEnrollUnivList.class;
    }

    @Autowired
    private CommonSvc commonSvc;

    @Override
    public Pagination getUnivInfo(Map requestMap, Integer year, Integer province, Integer majorType, Integer batch, int pageNo, int pageSize) {

        String hql = "FROM TCeeEnrollUnivList bean WHERE 1=1 ";
        Finder f = Finder.create(hql);

        if (requestMap.get("univ") != null && requestMap.get("univ") != "") {
            f.append(" and (bean.univName like '%" + requestMap.get("univ") + "%' )");
        }

        if (requestMap.get("major") != null && requestMap.get("major") != "") {
            f.append(" and ( bean.univId in (SELECT DISTINCT major.univId FROM TCeeEnrollMajorList major WHERE major.planOrHistory= 1 and major.year = '" + year + "' AND major.provinceId ='" + province + "' and major.majorTypeId = '" + majorType + "' and major.batchId = '" + batch + "' AND major.majorName like '%" + requestMap.get("major") + "%')) ");
        }

        if (requestMap.get("univIdFromMajor") != null) {
            List univIdFromMajor = (List) requestMap.get("univIdFromMajor");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromMajor);
        }
        if (requestMap.get("univIdFromScore") != null) {
            List univIdFromScore = (List) requestMap.get("univIdFromScore");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromScore);
        }
        appendToMapHql(requestMap, f);
        appendHql(year, province, majorType, batch, f);
        f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.scoreLow1  DESC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.scoreLow2  DESC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.scoreLow3  DESC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST , bean.scoreLow4  DESC NULLS LAST ");

        return find(f, pageNo, pageSize);
    }

    @Override
    public Pagination getUnivInfo(Map requestMap, Integer year, String univProvince, Integer majorType, Integer batch, Integer yxChoose, int pageNo, int pageSize) {
        String hql = "FROM TCeeEnrollUnivList bean WHERE bean.provinceId = " + requestMap.get("provinceId") + "  ";
        Finder f = Finder.create(hql);
        if (StringUtils.isNotBlank(univProvince)) {
//            Integer[] provinceArr = (Integer[]) requestMap.get("univProvince");
            f.append(" and bean.univProvince in ( " + univProvince + " )");
        }

        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("batchId")))) {
            f.append(" and bean.batchId = :batchId").setParam("batchId", requestMap.get("batchId"));
        }
        if (majorType != null) {
            f.append(" and bean.majorTypeId = " + majorType + "");
        }

        if (StringUtils.isNotBlank(String.valueOf(year))) {
            f.append(" and bean.year = :year").setParam("year", year);
        }
        if (requestMap.get("univSchoolType") != null && !"".equals(requestMap.get("univSchoolType"))) {

            String univSchoolType1 = requestMap.get("univSchoolType").toString();
//            String univSchoolType2 = univSchoolType1.substring(0, univSchoolType1.length() - 1);
            String univSchoolType3 = univSchoolType1.replace(",", "','");
            String univSchoolTypeLast = "'" + univSchoolType3 + "'";
//            String[] univSchoolTypes =  (String[])requestMap.get("univSchoolType");
//
//            String univSchoolTypeStr = "";
//            for (int i = 0; i < univSchoolTypes.length; i++) {
//                if (univSchoolTypes[i] != null && StringUtil.isNotEmpty(univSchoolTypes[i]) && univSchoolTypes[i] != "") {
//                    univSchoolTypeStr += "'" + univSchoolTypes[i] + "'";
//
//                    if (i != univSchoolTypes.length - 1) {
//                        univSchoolTypeStr += ",";
//                    }
//                }
//
//
//            }
            f.append(" and bean.univType in (" + univSchoolTypeLast + ")");

        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_is985")))) {
            f.append(" and bean.univIs985=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_is211")))) {
            f.append(" and bean.univIs211=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_isDefence")))) {
            f.append(" and bean.univIsDefence=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_isExcellent")))) {
            f.append(" and bean.univIsExcellent=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_isIndependence")))) {
            f.append(" and bean.univIsIndependence=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_isFirstRateUniv")))) {
            f.append(" and bean.univIsFirstRateUniv=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("univ_isFirstRateMajor")))) {
            f.append(" and bean.univIsFirstRateMajor=1");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("schoolOrMajorName")))) {

            String majorNameSql = "SELECT DISTINCT univ_list_id  FROM `t_cee_enroll_fill_list`\n" +
                    "WHERE YEAR = " + year + "\n" +
                    "AND province_id = " + requestMap.get("provinceId") + "\n" +
                    "AND major_type_id = " + majorType + "\n" +
                    "AND batch_id = " + requestMap.get("batchId") + "\n" +
                    "AND major_name LIKE '%" + requestMap.get("schoolOrMajorName") + "%'";
            List<Map<String, Object>> majorNameListIdMaps = commonSvc.findForJdbc(majorNameSql);
            f.append(" AND ( bean.univName LIKE '%" + requestMap.get("schoolOrMajorName") + "%' ");
            if (majorNameListIdMaps != null && majorNameListIdMaps.size() != 0) {
                String majorNameListIds = "";
                for (int i = 0; i < majorNameListIdMaps.size(); i++) {
                    String majorNameListId = majorNameListIdMaps.get(i).get("univ_list_id").toString();
                    majorNameListIds += majorNameListId;
                    if (i != majorNameListIdMaps.size() - 1) {
                        majorNameListIds += ",";
                    }
                }
                f.append(" OR bean.id IN ( " + majorNameListIds + " )");
            }
            f.append(" )");
        }
        if (StringUtils.isNotBlank(String.valueOf(requestMap.get("majorIds"))) && !"".equals(requestMap.get("majorIds"))) {
//            String[] majorIdArr = (String[]) requestMap.get("majorIds");
            String majorIds1 = requestMap.get("majorIds").toString();
            String majorIdsLast = "'" + majorIds1.replace(",","','") + "'";
            f.append(" AND  bean.id IN (SELECT univListId FROM TCeeEnrollFillList WHERE SUBSTRING(majorId,1,4) IN ( " + majorIdsLast + " ) )");
        }

//        江苏省科目限制有无资格筛选
        if (Integer.valueOf(requestMap.get("provinceId").toString()) == 17 && requestMap.get("subjectsLevel") != null) {
            String subjectsLevel = requestMap.get("subjectsLevel").toString();
            String[] subjectsLevels = subjectsLevel.split(",");
            Integer subjectsLevel1 = Integer.valueOf(subjectsLevels[0]);
            Integer subjectsLevel2 = Integer.valueOf(subjectsLevels[1]);

            if (yxChoose != null) {
                if (yxChoose != 0) {
                    switch (yxChoose) {
                        case 1:
//                    已选择
                            f.append(" AND (YEAR,provinceId,majorTypeId,batchId,univId) IN \n" +
                                    "(\n" +
                                    "SELECT DISTINCT YEAR,provinceId,majorTypeId,batchId,univId FROM TCeeApplicationsDetail\n" +
                                    "WHERE applicationId = " + requestMap.get("applicationId") + "\n" +
                                    "AND batchId = " + requestMap.get("batchId") + " \n" +
                                    "AND univId IS NOT NULL \n" +
                                    ")");
                            break;
                        case 2:
//                    未选择
                            f.append(" AND (YEAR,provinceId,majorTypeId,batchId,univId) NOT IN \n" +
                                    "(\n" +
                                    "SELECT DISTINCT YEAR,provinceId,majorTypeId,batchId,univId FROM TCeeApplicationsDetail\n" +
                                    "WHERE applicationId = " + requestMap.get("applicationId") + "\n" +
                                    "AND batchId = " + requestMap.get("batchId") + " \n" +
                                    "AND univId IS NOT NULL \n" +
                                    ")");

                            break;
                        case 3:
//                    有资格
                            f.append(" AND \n" +
                                    "((\n" +
                                    "SUBSTRING(univTestLevel,1,1) >= " + subjectsLevel1 + " AND SUBSTRING(univTestLevel,-1,1) >= " + subjectsLevel2 + " \n" +
                                    ")\n" +
                                    "OR \n" +
                                    "(\n" +
                                    "SUBSTRING(univTestLevel,1,1) >= " + subjectsLevel2 + " AND SUBSTRING(univTestLevel,-1,1) >= " + subjectsLevel1 + " \n" +
                                    "))");

                            break;
                        case 4:
//                    无资格
                            f.append(" AND \n" +
                                    "((\n" +
                                    "SUBSTRING(univTestLevel,1,1) < " + subjectsLevel1 + " OR SUBSTRING(univTestLevel,-1,1) < " + subjectsLevel2 + " \n" +
                                    ")\n" +
                                    "AND \n" +
                                    "(\n" +
                                    "SUBSTRING(univTestLevel,1,1) < " + subjectsLevel2 + " OR SUBSTRING(univTestLevel,-1,1) < " + subjectsLevel1 + " \n" +
                                    "))");

                            break;
                        default:
                    }
                }
            }
        }

//        总的选课限制筛选
//        是否选择不限 0:选择不限 1:未选择不限
//        if (yxChoose != null) {
        if(Integer.valueOf(requestMap.get("provinceId").toString()) == 16 || Integer.valueOf(requestMap.get("provinceId").toString()) == 1)
            if (requestMap.get("univSchoolKm") != null ) {
//            if (yxChoose == 3) {

                String chooseSubjectsStr = requestMap.get("univSchoolKm").toString();
//            选择科目限制
                String[] chooseSubjects = chooseSubjectsStr.split(",");
//            选择科目限制数量
                Integer chooseSubjectsNumber = chooseSubjects.length;

//                Integer chooseSubjectOrNot = Integer.valueOf(requestMap.get("dsdsd").toString());
                if (!"0".equals(chooseSubjectsStr) && chooseSubjectsStr!="") {
                    // Alltodo: 2020/4/1 前台传值，选择筛选科目限制，用逗号隔开

//            与或选项  0：或 1：与
//                    默认选或
                    Integer chooseSubjectANDOR = 0 ;
                            if(requestMap.get("univSchoolQh") != null) {
                                chooseSubjectANDOR =  Integer.valueOf(requestMap.get("univSchoolQh").toString());
                            }

                    switch (chooseSubjectsNumber) {

                        case 1:
                            f.append(" AND univTestLevel = '3' \n" +
                                    "AND univTestName LIKE '%" + chooseSubjectsStr + "%' ");
                            break;
                        case 2:
                            if (chooseSubjectANDOR == 0) {

                                f.append(" AND univTestLevel in ('5','2','3') \n" +
                                        "AND \n" +
                                        "(univTestName LIKE '%" + chooseSubjects[0] + "%' \n" +
                                        "OR univTestName LIKE '%" + chooseSubjects[1] + "%' ) ");

                            } else if (chooseSubjectANDOR == 1) {
                                f.append(" AND univTestLevel = '1' \n" +
                                        "AND \n" +
                                        "(univTestName LIKE '%" + chooseSubjects[0] + "%' \n" +
                                        "AND univTestName LIKE '%" + chooseSubjects[1] + "%' )");
                            }

                            break;
                        case 3:
                            if (chooseSubjectANDOR == 0) {

                                f.append(" AND univTestLevel in ('5','2','3') \n" +
                                        "AND \n" +
                                        "(univTestName LIKE '%" + chooseSubjects[0] + "%' \n" +
                                        "OR univTestName LIKE '%" + chooseSubjects[1] + "%' \n" +
                                        "OR univTestName LIKE '%" + chooseSubjects[2] + "%' ) ");

                            } else if (chooseSubjectANDOR == 1) {
                                f.append(" AND univTestLevel = '4' \n" +
                                        "AND \n" +
                                        "(univTestName LIKE '%" + chooseSubjects[0] + "%' \n" +
                                        "AND univTestName LIKE '%" + chooseSubjects[1] + "%' \n" +
                                        "AND univTestName LIKE '%" + chooseSubjects[2] + "%' ) ");
                            }

                            break;
                        default:
//                    不限
                            f.append(" AND univTestLevel = 0  ");
                    }
                } else if("0".equals(chooseSubjectsStr)){

                    f.append(" AND ( \n" +
                            "univTestLevel IN ('0') \n" +
                            ") ");

                }
                else {
                    //用户选择科目
                    String userSubjectsName = requestMap.get("subjectsLevel").toString();
                    String[] userSubjectsNames = userSubjectsName.split(",");

                    f.append(" AND ( \n" +
                            "univTestLevel IN ('0') \n" +
                            "OR (univTestLevel IN ('2','3','5') AND univTestName LIKE '%" + userSubjectsNames[0] + "%' OR  univTestName LIKE '%" + userSubjectsNames[1] + "%' OR  univTestName LIKE '%" + userSubjectsNames[2] + "%')  \n" +
                            "OR (univTestLevel IN ('1') AND ( (univTestName LIKE '%" + userSubjectsNames[0] + "%' AND  univTestName LIKE '%" + userSubjectsNames[1] + "%') OR (univTestName LIKE '%" + userSubjectsNames[0] + "%' AND  univTestName LIKE '%" + userSubjectsNames[2] + "%') OR (univTestName LIKE '%" + userSubjectsNames[1] + "%' AND  univTestName LIKE '%" + userSubjectsNames[2] + "%') ) )\n" +
                            "OR (univTestLevel IN ('4') AND univTestName LIKE '%" + userSubjectsNames[0] + "%' AND  univTestName LIKE '%" + userSubjectsNames[1] + "%' AND  univTestName LIKE '%" + userSubjectsNames[2] + "%' )\n" +
                            ") ");

                }
            }
//        }

        f.append(" ORDER BY IFNULL(bean.rankScoreLow1,9999999) ASC , \n" +
                "IFNULL(bean.scoreLow1,0) DESC, \n" +
                "IFNULL(bean.rankScoreLow2,9999999) ASC, \n" +
                "IFNULL(bean.scoreLow2,0) DESC, \n" +
                "IFNULL(bean.rankScoreLow3,9999999) ASC, \n" +
                "IFNULL(bean.scoreLow3,0) DESC , \n" +
                "IFNULL(bean.rankScoreLow4,9999999) ASC, \n" +
                "IFNULL(bean.scoreLow4,0) DESC ");
//        f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.scoreLow1  DESC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.scoreLow2  DESC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.scoreLow3  DESC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST , bean.scoreLow4  DESC NULLS LAST ");
        return find(f, pageNo, pageSize);
    }

    @Override
    public Pagination getUnivList(Map requestMap, List univList, int pageNo, int pageSize, String isSelectd, Integer year, Integer provinceId, Integer majorType, Integer batch) {
        String hql = "FROM TCeeEnrollUnivList bean WHERE 1=1 ";
        Finder f = Finder.create(hql);

        if (univList.size() > 0 && univList.get(0) != null) {
            Integer[] list = new Integer[univList.size()];
            for (int i = 0; i < univList.size(); i++) {
                list[i] = Integer.parseInt(String.valueOf(univList.get(i)));
            }

            if ("Y".equals(isSelectd)) {
                f.append(" and bean.univId in (:univId) ").setParamList("univId", list);
            } else if ("N".equals(isSelectd)) {
                f.append(" and bean.univId not in (:univId) ").setParamList("univId", list);
            }
        }

        if (requestMap.get("univIdFromMajor") != null) {
            List univIdFromMajor = (List) requestMap.get("univIdFromMajor");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromMajor);
        }
        if (requestMap.get("univIdFromScore") != null) {
            List univIdFromScore = (List) requestMap.get("univIdFromScore");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromScore);
        }

        appendToMapHql(requestMap, f);
        appendHql(year, provinceId, majorType, batch, f);
        f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.scoreLow1  DESC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.scoreLow2  DESC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.scoreLow3  DESC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST , bean.scoreLow4  DESC NULLS LAST ");
        return find(f, pageNo, pageSize);
    }

    @Override
    public Pagination getLevelUnivList(Map requestMap, String isSelectd, Integer level, Integer year, Integer provinceId, Integer majorType, Integer batchId, int pageNo, int pageSize) {
        String hql = "FROM TCeeEnrollUnivList bean WHERE 1=1 ";
        Finder f = Finder.create(hql);

        if ("Y".equals(isSelectd)) {
            f.append(" and bean.univTestLevel >=:level").setParam("level", level);
        } else if ("N".equals(isSelectd)) {
            f.append(" and bean.univTestLevel <:level").setParam("level", level);
        }

        if (requestMap.get("univIdFromMajor") != null) {
            List univIdFromMajor = (List) requestMap.get("univIdFromMajor");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromMajor);
        }
        if (requestMap.get("univIdFromScore") != null) {
            List univIdFromScore = (List) requestMap.get("univIdFromScore");
            f.append(" and bean.univId in (:univId) ").setParamList("univId", univIdFromScore);
        }

        appendToMapHql(requestMap, f);
        appendHql(year, provinceId, majorType, batchId, f);
        f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.scoreLow1  DESC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.scoreLow2  DESC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.scoreLow3  DESC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST , bean.scoreLow4  DESC NULLS LAST ");
        return find(f, pageNo, pageSize);
    }

    private void appendToMapHql(Map requestMap, Finder f) {
//        if (requestMap.get("city") != null) {
////            f.append(" and bean.univCity like:city").setParam("city", "%" + String.valueOf(requestMap.get("city")) + "%");
//            f.append(" and bean.univCity in (:univCity) ").setParamList("univCity", String.valueOf(requestMap.get("city")).split(","));
//        }
        if (requestMap.get("provinceId") != null) {
            String[] univProvince = (String[]) requestMap.get("provinceId");
            f.append(" and bean.univProvince in (:univProvince) ").setParamList("univProvince", univProvince);
        }
        if (requestMap.get("univType") != null) {
            f.append(" and bean.univType in (:univClassify)").setParamList("univClassify", String.valueOf(requestMap.get("univType")).split(","));
        }

        if (StringUtil.isNotEmpty(requestMap.get("univSchoolType"))) {
            String univSchoolType = String.valueOf(requestMap.get("univSchoolType"));
            String[] univType = univSchoolType.split(",");
            int size = univType.length;
            f.append(" and bean.univType in (");
            if (size == 1) {
                f.append("'" + univType[0] + "' ");
            }

            if (size == 2) {
                f.append("'" + univType[0] + "' , " + "'" + univType[1] + "'");
            }

            if (size > 2) {
                for (int i = 0; i < univType.length - 1; i++) {
                    f.append("'" + univType[i] + "' ,");
                }
                f.append("'" + univType[size - 1] + "'");
            }
            f.append(" ) ");

        }


        if (StringUtil.isNotEmpty(requestMap.get("univType"))) {
            String type = String.valueOf(requestMap.get("univType"));
            if (type.contains("1")) {
                //985学校
                f.append(" and bean.univIs985=1 ");
            }
            if (type.contains("2")) {
                //211学校
                f.append(" and bean.univIs211=1 ");
            }
            if (type.contains("3")) {
                //国防生
                f.append(" and bean.univIsDefence=1 ");
            }
            if (type.contains("4")) {
                //卓越计划
                f.append(" and bean.univIsExcellent=1 ");
            }
            if (type.contains("5")) {
                //自主招生
                f.append(" and bean.univIsIndependence=1 ");
            }
            if (type.contains("6")) {
                //一流大学
                f.append(" and bean.univIsFirstRateUniv=1 ");
            }
            if (type.contains("7")) {
                //一流学科
                f.append(" and bean.univIsFirstRateMajor=1 ");
            }
        }
    }

    private void appendHql(Integer year, Integer province, Integer majorType, Integer batch, Finder f) {
        if (null != year) {
            f.append(" and bean.year=:year").setParam("year", year);
        }
        if (null != province) {
            f.append(" and bean.provinceId=:province").setParam("province", province);
        }
        if (null != majorType) {
            f.append(" and bean.majorTypeId=:majorType").setParam("majorType", majorType);
        }
        if (StringUtils.isNotBlank(String.valueOf(batch))) {
            f.append(" and bean.batchId=:batch").setParam("batch", batch);
        }
    }

    private void appendHql2(Integer year, String[] province, Integer majorType, Integer batch, Finder f) {
        if (null != year) {
            f.append(" and bean.year=:year").setParam("year", year);
        }
        if (null != province) {
            f.append(" and bean.provinceId in (:province)").setParam("province", province);
        }
        if (null != majorType) {
            f.append(" and bean.majorTypeId=:majorType").setParam("majorType", majorType);
        }
        if (null != batch) {
            f.append(" and bean.batchId=:batch").setParam("batch", batch);
        }
    }
}
