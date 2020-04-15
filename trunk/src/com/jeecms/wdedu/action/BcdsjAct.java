package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.BcdsjSvc;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtil;
import com.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;

@Controller
@RequestMapping(value = "/bcdsj")
public class BcdsjAct {
    public final static String TPLDIR_BCDSJ = "bcdsj";

    //    public final static String UNIVERSITY_RANK_RESULT = "universityRank";
    public final static String UNIVERSITY_RANK_RESULT = "paihangbang";
    public final static String UNIVERSITY_RANK_RESULT_ENROLL = "paihangbangEnroll";
    public final static String UNIVERSITY_RANK_RESULT_SALARY = "paihangbangSalary";

    //    public final static String PROFESSION_RANK_RESULT = "professionRank";
    public final static String PROFESSION_RANK_RESULT = "paihangbangzhuanye";
    public final static String PROFESSION_RANK_RESULT_ENROLL = "paihangbangzhuanyeEnroll";
    public final static String PROFESSION_RANK_RESULT_SALARY = "paihangbangzhuanyeSalary";

    public final static String PROFESSION_LAB_RESULT = "professionAndLab";
    public final static String UNIVERSITY_TEAM_RESULT = "universityTeam";
    public final static String ALL_PROFESSIONS_RESULT = "professionalBooks";

    @Autowired
    private BcdsjSvc bcdsjSvc;

    @Autowired
    private CommonSvc commonSvc;


    /**
     * 院校排行榜
     */
    @RequiresPermissions("YuanXiaoPaiHang:*")
    @RequestMapping("/universityRank.jspx")
    public String queryUniversityRank(String univName, String univTypes, String univProvinces,
                                      Integer zgxyhPaginationPageNo, Integer zgxyhPaginationSetPageNum,
                                      Integer wslphbPaginationPageNo, Integer wslphbPaginationSetPageNum,
                                      Integer QSphbPaginationPageNo, Integer QSphbPaginationSetPageNum,
                                      Integer USphbPaginationPageNo, Integer USphbPaginationSetPageNum,
                                      Integer sjxsPaginationPageNo, Integer sjxsPaginationSetPageNum,String present,
                                      HttpServletRequest request, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);

        if (zgxyhPaginationSetPageNum == null || zgxyhPaginationSetPageNum == 0) {
            zgxyhPaginationSetPageNum = 20;
        }
        if (wslphbPaginationSetPageNum == null || wslphbPaginationSetPageNum == 0) {
            wslphbPaginationSetPageNum = 20;
        }
        if (QSphbPaginationSetPageNum == null || QSphbPaginationSetPageNum == 0) {
            QSphbPaginationSetPageNum = 20;
        }
        if (USphbPaginationSetPageNum == null || USphbPaginationSetPageNum == 0) {
            USphbPaginationSetPageNum = 20;
        }
        if (sjxsPaginationSetPageNum == null || sjxsPaginationSetPageNum == 0) {
            sjxsPaginationSetPageNum = 20;
        }

        List<TDataUniversityRank> tBasecateNameList = commonSvc.findByQueryString("SELECT DISTINCT cateName FROM TDataUniversityRank\n");
        List<TDataUniversityRank> tBaseProvinceNameList = commonSvc.findByQueryString("SELECT DISTINCT provinceName FROM TDataUniversityRank\n");

        List<TBaseProvince> tBaseProvinces = commonSvc.getList(TBaseProvince.class);
        model.addAttribute("tBaseProvinces", tBaseProvinces);

        model.addAttribute("tBaseProvinceNameList", tBaseProvinceNameList);
        model.addAttribute("tBasecateNameList", tBasecateNameList);
        //分页信息
        if (StringUtils.isNotBlank(univProvinces)) {
            univProvinces = univProvinces.substring(0, univProvinces.length() - 1);
        }
        if (StringUtils.isNotBlank(univTypes)) {
            univTypes = univTypes.substring(0, univTypes.length() - 1);
        }
        Pagination zgxyhPagination = bcdsjSvc.queryUniversityRank(1, univName, univTypes, univProvinces, cpn(zgxyhPaginationPageNo), zgxyhPaginationSetPageNum);
        Pagination wslphbPagination = bcdsjSvc.queryUniversityRank(2, univName, univTypes, univProvinces, cpn(wslphbPaginationPageNo), wslphbPaginationSetPageNum);
        Pagination QSphbPagination = bcdsjSvc.queryUniversityRank(3, univName, univTypes, univProvinces, cpn(QSphbPaginationPageNo), QSphbPaginationSetPageNum);
        Pagination USphbPagination = bcdsjSvc.queryUniversityRank(4, univName, univTypes, univProvinces, cpn(USphbPaginationPageNo), USphbPaginationSetPageNum);
        Pagination sjxsPagination = bcdsjSvc.queryUniversityRank(5, univName, univTypes, univProvinces, cpn(sjxsPaginationPageNo), sjxsPaginationSetPageNum);

        model.addAttribute("zgxyhPagination", zgxyhPagination);
        model.addAttribute("wslphbPagination", wslphbPagination);
        model.addAttribute("QSphbPagination", QSphbPagination);
        model.addAttribute("USphbPagination", USphbPagination);
        model.addAttribute("sjxsPagination", sjxsPagination);

        model.addAttribute("univName", univName);

        model.addAttribute("typess", univTypes);
        model.addAttribute("cityss", univProvinces);
        model.addAttribute("present",present);

        model.addAttribute("zgxyhSetPageNum", zgxyhPaginationSetPageNum);
        model.addAttribute("wslphbSetPageNum", wslphbPaginationSetPageNum);
        model.addAttribute("QSphbSetPageNum", QSphbPaginationSetPageNum);
        model.addAttribute("USphbSetPageNum", USphbPaginationSetPageNum);
        model.addAttribute("sjxsSetPageNum", sjxsPaginationSetPageNum);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, UNIVERSITY_RANK_RESULT);
    }

    @RequestMapping("/universityEnrollRank.jspx")
    public String queryUniversityEnrollRank(String univName, Integer majorTypeId, Integer provinceId, String univProvinces,
                                            Integer lqfsPaginationPageNo, Integer lqfsPaginationSetPageNum,
                                            Integer lqrsPaginationPageNo, Integer lqrsPaginationSetPageNum,
                                            Integer lqzysPaginationPageNo, Integer lqzysPaginationSetPageNum,
                                            Integer lqmcbhPaginationPageNo, Integer lqmcbhPaginationSetPageNum,
                                            String present,String univTypes, Integer pageNo, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);

        if (lqfsPaginationSetPageNum == null || lqfsPaginationSetPageNum == 0) {
            lqfsPaginationSetPageNum = 20;
        }
        if (lqrsPaginationSetPageNum == null || lqrsPaginationSetPageNum == 0) {
            lqrsPaginationSetPageNum = 20;
        }
        if (lqzysPaginationSetPageNum == null || lqzysPaginationSetPageNum == 0) {
            lqzysPaginationSetPageNum = 20;
        }
        if (lqmcbhPaginationSetPageNum == null || lqmcbhPaginationSetPageNum == 0) {
            lqmcbhPaginationSetPageNum = 20;
        }


        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Integer proId = Integer.valueOf(user.getAttr().get("province_id"));
        if (provinceId == null) {
            provinceId = proId;
        }

        Integer grade = Integer.valueOf(user.getAttr().get("major_type_id"));
        if (majorTypeId == null) {
            majorTypeId = grade;
        }

        // Alltodo: 2019/11/12 查找年份
        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");
        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");

        List<TDataUniversityRank> tBasecateNameList = commonSvc.findByQueryString(" SELECT DISTINCT univType \n" +
                "FROM TCeeEnrollUnivList\n" +
                "WHERE YEAR = " + planYear + " \n" +
                "AND provinceId = " + provinceId + "\n" +
                "AND majorTypeId = " + majorTypeId + " \n" +
                "AND univType IS NOT NULL ");

        //分页信息
//        Pagination pagination = bcdsjSvc.queryUniversityEnrollRank(univName, planYear, majorTypeId, provinceId, tabType, univProvince, univType, cpn(pageNo), CookieUtils.getPageSize(request));
        Pagination lqfsPagination = bcdsjSvc.queryUniversityEnrollRank(univName, planYear, majorTypeId, provinceId, 1, univProvinces, univTypes, cpn(lqfsPaginationPageNo), lqfsPaginationSetPageNum);
        Pagination lqrsPagination = bcdsjSvc.queryUniversityEnrollRank(univName, planYear, majorTypeId, provinceId, 2, univProvinces, univTypes, cpn(lqrsPaginationPageNo), lqrsPaginationSetPageNum);
        Pagination lqzysPagination = bcdsjSvc.queryUniversityEnrollRank(univName, planYear, majorTypeId, provinceId, 4, univProvinces, univTypes, cpn(lqzysPaginationPageNo), lqzysPaginationSetPageNum);
        Pagination lqmcbhPagination = bcdsjSvc.queryUniversityEnrollRank(univName, planYear, majorTypeId, provinceId, 3, univProvinces, univTypes, cpn(lqmcbhPaginationPageNo), lqmcbhPaginationSetPageNum);

        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);

        model.addAttribute("lqfsPagination", lqfsPagination);
        model.addAttribute("lqrsPagination", lqrsPagination);
        model.addAttribute("lqzysPagination", lqzysPagination);
        model.addAttribute("lqmcbhPagination", lqmcbhPagination);

        model.addAttribute("lqfsSetPageNum", lqfsPaginationSetPageNum);
        model.addAttribute("lqrsSetPageNum", lqrsPaginationSetPageNum);
        model.addAttribute("lqzysSetPageNum", lqzysPaginationSetPageNum);
        model.addAttribute("lqmcbhSetPageNum", lqmcbhPaginationSetPageNum);

        model.addAttribute("tBasecateNameList", tBasecateNameList);
        model.addAttribute("provinceList", provinceList);

        model.addAttribute("univName", univName);
        model.addAttribute("majorTypeId", majorTypeId);
        model.addAttribute("provinceId", provinceId);

        String univProvince = "";
        if (StringUtils.isNotBlank(univProvinces)) {
            String[] univProvinceIds = univProvinces.split(",");
            //回填数据
            for (String univProvinceId : univProvinceIds) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (tBaseProvince.getProvinceId().toString().equals(univProvinceId)) {
                        univProvince += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }

        model.addAttribute("univProvince", univProvince);
        model.addAttribute("univType", univTypes);
        model.addAttribute("planYear", planYear);
        model.addAttribute("enrollYear", enrollYear);
        model.addAttribute("present", present);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, UNIVERSITY_RANK_RESULT_ENROLL);
    }


    @RequestMapping("/universitySalaryRank.jspx")
    public String queryUniversitySalaryRank(String univName, Integer majorTypeId, Integer provinceId, String univLevel, Integer tabType, String univProvinces, String univTypes, Integer PaginationPageNo, Integer PaginationSetPageNum, Integer pageNo, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);

        if (PaginationSetPageNum == null || PaginationSetPageNum == 0) {
            PaginationSetPageNum = 20;
        }

        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        Integer proId = Integer.valueOf(user.getAttr().get("province_id"));
        if (provinceId == null) {
            provinceId = proId;
        }

        List<TDataUniversityRank> tBasecateNameList = commonSvc.findByQueryString("SELECT DISTINCT univType FROM TDataUniversity\n" +
                "WHERE univType IS NOT NULL ");

        model.addAttribute("tBasecateNameList", tBasecateNameList);
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);

        Integer year = 2018;
        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");
        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");
        //分页信息
        Pagination pagination = bcdsjSvc.queryUniversitySalaryRank(univName, year, provinceId, univLevel, tabType, univProvinces, univTypes, cpn(PaginationPageNo), PaginationSetPageNum);
        model.addAttribute("pagination", pagination);

        model.addAttribute("univName", univName);
        model.addAttribute("majorTypeId", majorTypeId);
        model.addAttribute("provinceId", provinceId);


        String univProvince = "";
        if (StringUtils.isNotBlank(univProvinces)) {
            String[] univProvinceIds = univProvinces.split(",");
            //回填数据
            for (String univProvinceId : univProvinceIds) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (tBaseProvince.getProvinceId().toString().equals(univProvinceId)) {
                        univProvince += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }

        model.addAttribute("SetPageNum", PaginationSetPageNum);
        model.addAttribute("univType", univTypes);
        model.addAttribute("univProvince", univProvince);
        model.addAttribute("planYear", planYear);
        model.addAttribute("enrollYear", enrollYear);

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, UNIVERSITY_RANK_RESULT_SALARY);
    }

    /**
     * 专业排行榜
     */
    @RequiresPermissions("ZhuanYePaoHang:*")
    @RequestMapping("/professionRank.jspx")
    public String queryProfessionRank(String univName1, String majorName1, String undergraduate, Integer PaginationPageNo, Integer PaginationSetPageNum, HttpServletRequest request, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);

        if (PaginationSetPageNum == null || PaginationSetPageNum == 0) {
            PaginationSetPageNum = 20;
        }
        String kstype = ksType;
        model.addAttribute("kstype", kstype);

        String sql = "SELECT * FROM t_data_major\n" +
                "WHERE parent_major_id = 1 \n" +
                "AND CONVERT(major_id,SIGNED) < 50 ";
        List<Map<String, Object>> tDataMajors = commonSvc.findForJdbc(sql);
        model.addAttribute("tDataMajors", tDataMajors);

        //分页信息
        Pagination pagination = bcdsjSvc.queryProfessionRank(univName1, majorName1, undergraduate, cpn(PaginationPageNo), PaginationSetPageNum);
        model.addAttribute("pagination", pagination);
        model.addAttribute("univName", univName1);
        model.addAttribute("majorName", majorName1);


        String undergraduates = "";
        if (StringUtils.isNotBlank(undergraduate)) {
            String[] undergraduatess = undergraduate.split(",");
            //回填数据
            for (String undergraduatesss : undergraduatess) {
                for (Map<String, Object> tDataMajor : tDataMajors) {
                    if (tDataMajor.get("major_id").toString().equals(undergraduatesss)) {
                        undergraduates += tDataMajor.get("major_name").toString() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("PaginationSetPageNum", PaginationSetPageNum);
        model.addAttribute("PaginationPageNo", PaginationPageNo);
        model.addAttribute("undergraduates", undergraduates);
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, PROFESSION_RANK_RESULT);
    }

    @RequestMapping("/professionSalaryRank.jspx")
    public String queryProfessionSalaryRank(String univName1, String majorName1, Integer majorTypeId, Integer provinceId, String undergraduate, Integer tabType,Integer PaginationSetPageNum, String majorId, String majorName, Integer pageNo, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        if (PaginationSetPageNum == null || PaginationSetPageNum == 0) {
            PaginationSetPageNum = 20;
        }
        Integer proId = Integer.valueOf(user.getAttr().get("province_id"));
        if (provinceId == null) {
            provinceId = proId;
        }
        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");
        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");

        String sql = "SELECT * FROM t_data_major\n" +
                "WHERE parent_major_id = 1 \n" +
                "AND CONVERT(major_id,SIGNED) < 50 ";
        List<Map<String, Object>> tDataMajors = commonSvc.findForJdbc(sql);
        model.addAttribute("tDataMajors", tDataMajors);

        tabType = 1;
        //分页信息
        Pagination pagination = bcdsjSvc.queryProfessionSalaryRank(enrollYear, provinceId, majorTypeId, tabType, undergraduate, majorId, majorName1, cpn(pageNo), PaginationSetPageNum);
        model.addAttribute("pagination", pagination);
        model.addAttribute("majorTypeId", majorTypeId);
        model.addAttribute("provinceId", provinceId);
        model.addAttribute("tabType", tabType);
        model.addAttribute("majorId", majorId);
        model.addAttribute("planYear", planYear);
        model.addAttribute("enrollYear", enrollYear);

        String undergraduates = "";
        if (StringUtils.isNotBlank(undergraduate)) {
            String[] undergraduatess = undergraduate.split(",");
            //回填数据
            for (String undergraduatesss : undergraduatess) {
                for (Map<String, Object> tDataMajor : tDataMajors) {
                    if (tDataMajor.get("major_id").toString().equals(undergraduatesss)) {
                        undergraduates += tDataMajor.get("major_name").toString() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("majorName1", majorName1);
        model.addAttribute("undergraduates", undergraduates);
        model.addAttribute("PaginationSetPageNum", PaginationSetPageNum);

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, PROFESSION_RANK_RESULT_SALARY);
    }


    @RequestMapping("/professionEnrollRank.jspx")
    public String queryProfessionEnrollRank(String univName1, String majorName1, String univName2, String majorName2, String univName3, String majorName3, Integer majorTypeId, Integer provinceId, String undergraduate, Integer tabType, String majorId, Integer pageNo, HttpServletRequest request, ModelMap model,
                                            Integer lqfspmPaginationPageNo, Integer lqfspmPaginationSetPageNum,
                                            Integer lqrspmPaginationPageNo, Integer lqrspmPaginationSetPageNum,
                                            Integer lqmcbhPaginationPageNo, Integer lqmcbhPaginationSetPageNum, Integer typeId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (typeId == null) {
            typeId = 1;
        }
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        if (lqfspmPaginationSetPageNum == null || lqfspmPaginationSetPageNum == 0) {
            lqfspmPaginationSetPageNum = 20;
        }
        if (lqrspmPaginationSetPageNum == null || lqrspmPaginationSetPageNum == 0) {
            lqrspmPaginationSetPageNum = 20;
        }
        if (lqmcbhPaginationSetPageNum == null || lqmcbhPaginationSetPageNum == 0) {
            lqmcbhPaginationSetPageNum = 20;
        }

        String sql = "SELECT * FROM t_data_major\n" +
                "WHERE parent_major_id = 1 \n" +
                "AND CONVERT(major_id,SIGNED) < 50 ";
        List<Map<String, Object>> tDataMajors = commonSvc.findForJdbc(sql);
        model.addAttribute("tDataMajors", tDataMajors);

        Integer proId = Integer.valueOf(user.getAttr().get("province_id"));
        if (provinceId == null) {
            provinceId = proId;
        }
        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");
        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id = " + provinceId + " ");

        String undergraduates = "";
        if (StringUtils.isNotBlank(undergraduate)) {
            String[] undergraduatess = undergraduate.split(",");
            //回填数据
            for (String undergraduatesss : undergraduatess) {
                for (Map<String, Object> tDataMajor : tDataMajors) {
                    if (tDataMajor.get("major_id").toString().equals(undergraduatesss)) {
                        undergraduates += tDataMajor.get("major_name").toString() + ",";
                        break;
                    }
                }
            }
        }
        //分页信息
//        Pagination pagination = bcdsjSvc.queryProfessionEnrollRank(enrollYear, provinceId, majorTypeId, tabType, majorId, cpn(pageNo), CookieUtils.getPageSize(request));
        Pagination lqfspmPagination = bcdsjSvc.queryProfessionEnrollRank(enrollYear, provinceId, majorTypeId, 1, majorId, univName1, majorName1, cpn(lqfspmPaginationPageNo), lqfspmPaginationSetPageNum);
        Pagination lqrspmPagination = bcdsjSvc.queryProfessionEnrollRank(enrollYear, provinceId, majorTypeId, 2, majorId, univName2, majorName2, cpn(lqrspmPaginationPageNo), lqrspmPaginationSetPageNum);
        Pagination lqmcbhPagination = bcdsjSvc.queryProfessionEnrollRank(enrollYear, provinceId, majorTypeId, 3, majorId, univName3, majorName3, cpn(lqmcbhPaginationPageNo), lqmcbhPaginationSetPageNum);
        model.addAttribute("lqfspmPagination", lqfspmPagination);
        model.addAttribute("lqrspmPagination", lqrspmPagination);
        model.addAttribute("lqmcbhPagination", lqmcbhPagination);
        model.addAttribute("majorTypeId", majorTypeId);
        model.addAttribute("provinceId", provinceId);
        model.addAttribute("tabType", tabType);
        model.addAttribute("majorId", majorId);
        model.addAttribute("planYear", planYear);
        model.addAttribute("enrollYear", enrollYear);

        model.addAttribute("undergraduates", undergraduates);

        model.addAttribute("lqfspmPaginationSetPageNum", lqfspmPaginationSetPageNum);
        model.addAttribute("lqrspmPaginationSetPageNum", lqrspmPaginationSetPageNum);
        model.addAttribute("lqmcbhPaginationSetPageNum", lqmcbhPaginationSetPageNum);

        model.addAttribute("univName1", univName1);
        model.addAttribute("univName2", univName2);
        model.addAttribute("univName3", univName3);

        model.addAttribute("majorName1", majorName1);
        model.addAttribute("majorName2", majorName2);
        model.addAttribute("majorName3", majorName3);

        model.addAttribute("typeId", typeId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, PROFESSION_RANK_RESULT_ENROLL);
    }

    /**
     * 重点学科与实验室
     */
    @RequiresPermissions("ZhongDianXueKe:*")
    @RequestMapping("/professionAndLab.jspx")
    public String queryProfessionAndLab(String univName, String majorName, Integer pageNo, HttpServletRequest request, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        String kstype = ksType;
        model.addAttribute("kstype", kstype);
        Pagination pagination = bcdsjSvc.queryProfessionAndLab(univName, majorName, cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pagination", pagination);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, PROFESSION_LAB_RESULT);
    }

    /**
     * 中外合作院校
     */
    @RequiresPermissions("ZhongWaiHeZuo:*")
    @RequestMapping("/universityTeam.jspx")
    public String queryUniversityTeam(Integer univId, Integer pageNo, HttpServletRequest request, ModelMap model, HttpServletResponse response, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        String kstype = ksType;
        model.addAttribute("kstype", kstype);
        //查询大学列表
        List<TDataUniversityDetail> universityList = bcdsjSvc.queryUniversityTeam();
        //查询大学信息
        TDataUniversityDetail univInfo = null;
        if (univId != null) {
            univInfo = commonSvc.findUniqueByProperty(TDataUniversityDetail.class, "id", univId);
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(univInfo));
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("universityList", universityList);
        model.addAttribute("univInfo", univInfo);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, UNIVERSITY_TEAM_RESULT);
    }

    //中外合作办学搜索
    @RequestMapping("/universityTeamByName.jspx")
    public void universityTeamByName(String univName, HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);

        //查询大学信息
      /*  TDataUniversityDetail univInfo = null;
        if (univName != null) {
            univInfo = commonSvc.findUniqueByProperty(TDataUniversityDetail.class, "univName", univName);
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(univInfo));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        String sql = "SELECT id,univ_name FROM t_data_university_detail where univ_name like '%" + univName + "%'";
        List<Map<String, Object>> univList = commonSvc.findForJdbc(sql);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(univList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 职业大全
     */
    @RequiresPermissions("ZhiYeDaQuan:*")
    @RequestMapping("/professionalBooks.jspx")
    public String queryAllProfessions(String class1, Integer id, HttpServletRequest request, ModelMap model, HttpServletResponse response, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        String kstype = ksType;
        model.addAttribute("kstype", kstype);
        Map<String, Map<String, List>> resultMap = new HashMap<>();
        List<TDataCareer> booksList = commonSvc.loadAll(TDataCareer.class);
        for (TDataCareer career : booksList) {
            Map careerMap = new HashMap<>();
            careerMap.put("id", career.getId());
            careerMap.put("career", career.getCareer());
            String c1 = career.getClass1();
            String c2 = career.getClass2();
            if (!resultMap.containsKey(c1)) {
                resultMap.put(c1, new HashMap<String, List>());
            }
            if (!resultMap.get(c1).containsKey(c2)) {
                resultMap.get(c1).put(c2, new ArrayList());
            }
            resultMap.get(c1).get(c2).add(careerMap);
        }
        try {
            response.setCharacterEncoding("utf-8");
            if (id != null) {
                TDataCareer car = commonSvc.findUniqueByProperty(TDataCareer.class, "id", id);
                response.getWriter().write(JSON.toJSONString(car));
                return "";
            }
            if (class1 != null) {
                response.getWriter().write(JSON.toJSONString(resultMap.get(class1)));
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("resultMap", resultMap);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BCDSJ, ALL_PROFESSIONS_RESULT);
    }
}
