/*
 * Copyright (C), 2017-2018 志愿无忧
 * FileName: EvaluationAct.java
 * Author:   panglv
 * Date:     2018年2月5日 下午4:47:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jeecms.wdedu.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈测评类〉
 *
 * @author panglv
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@Transactional
public class EvaluationAct {

    private static final Map<String, String> IBM_MAP = new HashMap<String, String>();

    static {
        IBM_MAP.put("E", "外倾");
        IBM_MAP.put("I", "内倾");
        IBM_MAP.put("S", "感觉");
        IBM_MAP.put("N", "直觉");
        IBM_MAP.put("T", "思维");
        IBM_MAP.put("F", "情感");
        IBM_MAP.put("J", "判断");
        IBM_MAP.put("P", "知觉");
    }

    private static final Map<String, String> HOLD_MAP = new HashMap<String, String>();

    static {
        HOLD_MAP.put("R", "实践型");
        HOLD_MAP.put("I", "研究型");
        HOLD_MAP.put("A", "艺术型");
        HOLD_MAP.put("S", "社会型");
        HOLD_MAP.put("E", "企业型");
        HOLD_MAP.put("C", "常规型");
    }

    /**
     * 测评种类距离值
     */
    private static final Map<String, Integer> FRACTION_MAP = new HashMap<String, Integer>();

    static {
        FRACTION_MAP.put("RR", 3);
        FRACTION_MAP.put("RI", 2);
        FRACTION_MAP.put("RA", 1);
        FRACTION_MAP.put("RC", 2);
        FRACTION_MAP.put("RE", 1);
        FRACTION_MAP.put("RS", 0);
        FRACTION_MAP.put("IR", 2);
        FRACTION_MAP.put("II", 3);
        FRACTION_MAP.put("IA", 2);
        FRACTION_MAP.put("IC", 1);
        FRACTION_MAP.put("IE", 0);
        FRACTION_MAP.put("IS", 1);
        FRACTION_MAP.put("AR", 1);
        FRACTION_MAP.put("AI", 2);
        FRACTION_MAP.put("AA", 3);
        FRACTION_MAP.put("AC", 0);
        FRACTION_MAP.put("AE", 1);
        FRACTION_MAP.put("AS", 2);
        FRACTION_MAP.put("CR", 2);
        FRACTION_MAP.put("CI", 1);
        FRACTION_MAP.put("CA", 0);
        FRACTION_MAP.put("CC", 3);
        FRACTION_MAP.put("CE", 2);
        FRACTION_MAP.put("CS", 1);
        FRACTION_MAP.put("ER", 1);
        FRACTION_MAP.put("EI", 0);
        FRACTION_MAP.put("EA", 1);
        FRACTION_MAP.put("EC", 2);
        FRACTION_MAP.put("EE", 3);
        FRACTION_MAP.put("ES", 2);
        FRACTION_MAP.put("SR", 0);
        FRACTION_MAP.put("SI", 1);
        FRACTION_MAP.put("SA", 2);
        FRACTION_MAP.put("SC", 1);
        FRACTION_MAP.put("SE", 2);
        FRACTION_MAP.put("SS", 3);
    }

    DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    @Autowired
    private CommonSvc commonSvc;

    /**
     * 高考测评首页
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/index.jspx")
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model, String ksType,Integer tabType) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String haveTianZhiyuan = "false";
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();
            while (groupssIt2.hasNext()) {
                if ("TianZhiYuan:*".equals(groupssIt2.next())) {
                    haveTianZhiyuan = "true";
                    model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
                    break;
                }
            }
        }
        String kstype = ksType;
        model.addAttribute("kstype", kstype);
        // 获取测评者信息
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isallbg", "yes");
        if (qstResult != null) {
            if (qstResult.getRstANum() == null || (qstResult.getRstANum() != null && qstResult.getRstANum() == 0)) {
                map.put("isonebg", "no");
                map.put("isallbg", "no");
            } else if (qstResult.getRstANum() != null && qstResult.getRstANum() == 3) {
                map.put("isonecs", "no");
            }

            if (qstResult.getRstBNum() == null || (qstResult.getRstBNum() != null && qstResult.getRstBNum() == 0)) {
                map.put("istwobg", "no");
                map.put("isallbg", "no");
            } else if (qstResult.getRstBNum() != null && qstResult.getRstBNum() == 3) {
                map.put("istwocs", "no");
            }

            if (qstResult.getRstCNum() == null || (qstResult.getRstCNum() != null && qstResult.getRstCNum() == 0)) {
                map.put("isthreebg", "no");
                map.put("isallbg", "no");
            } else if (qstResult.getRstCNum() != null && qstResult.getRstCNum() == 3) {
                map.put("isthreecs", "no");
            }
        } else {
            map.put("isthreebg", "no");
            map.put("istwobg", "no");
            map.put("isallbg", "no");
            map.put("isonebg", "no");
        }

        model.addAttribute("is", map);

        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs");

    }

    /**
     * holland职业测评
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/careerQuiz.jspx")
    public String careerQuiz(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        // 获取测评者信息
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult != null && qstResult.getRstANum() != null && qstResult.getRstANum() == 3) {
            return "redirect:findcareerQuiz.jspx";
        }
        model.addAttribute(
                "onelist",
                commonSvc
                        .findForJdbc("SELECT content,option1,value1,option2 FROM t_qst WHERE type='A1'"));
        model.addAttribute(
                "twolist",
                commonSvc
                        .findForJdbc("SELECT content,option1,value1,option2 FROM t_qst WHERE type='A2'"));
        model.addAttribute(
                "threelist",
                commonSvc
                        .findForJdbc("SELECT content,option1,value1,option2 FROM t_qst WHERE type='A3'"));
        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "zycp");

    }

    /**
     * holland职业测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param report_A
     * @param report_C
     * @param report_E
     * @param report_I
     * @param report_S
     * @param report_R
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/careerQuizReport.jspx", method = RequestMethod.POST)
    public String careerQuizReport(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model, Integer report_A,
                                   Integer report_C, Integer report_E, Integer report_I,
                                   Integer report_S, Integer report_R) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        // 获取测评者信息
        Integer userID = user.getId();
        String username = user.getUsername();

        // 整理测评者成绩
        Map<String, Integer> reportMap = new HashMap<String, Integer>();
        reportMap.put("A", report_A);
        reportMap.put("C", report_C);
        reportMap.put("E", report_E);
        reportMap.put("I", report_I);
        reportMap.put("S", report_S);
        reportMap.put("R", report_R);

        List<Entry<String, Integer>> resultlist = new ArrayList<Entry<String, Integer>>(
                reportMap.entrySet());
        Collections.sort(resultlist, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2) {
                int flag = o1.getValue().compareTo(o2.getValue());
                if (flag == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return flag;
            }
        });
        // 获取测评成绩
        String resultvalue = resultlist.get(5).getKey()
                + resultlist.get(4).getKey() + resultlist.get(3).getKey();
        // 保存此测评成绩
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null) {
            qstResult = new TQstResult();
            qstResult.setUserId(userID);
            qstResult.setUserName(username);
        }
        Integer rsta = 1;
        if (!StringUtils.isEmpty(qstResult.getRstANum())) {
            rsta = qstResult.getRstANum() + 1;
        }
        qstResult.setRstANum(rsta);
        qstResult.setResultADate(new Date());
        qstResult.setRstA(resultvalue);
        qstResult.setRetAA(report_A);
        qstResult.setRetAC(report_C);
        qstResult.setRetAE(report_E);
        qstResult.setRetAI(report_I);
        qstResult.setRetAR(report_R);
        qstResult.setRetAS(report_S);
        commonSvc.saveOrUpdate(qstResult);
        model.addAttribute("date", format.format(qstResult.getResultADate()));
        model.addAttribute("qstResult", qstResult);
        //条形表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("R", new BigDecimal((float) report_R / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("I", new BigDecimal((float) report_I / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("A", new BigDecimal((float) report_A / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("S", new BigDecimal((float) report_S / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("C", new BigDecimal((float) report_C / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("E", new BigDecimal((float) report_E / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        model.addAttribute("txmap", map);
        // 结果分析

        model.addAttribute("one", commonSvc.findUniqueByProperty(TQstA.class, "type", resultlist.get(5).getKey()));
        model.addAttribute("twp", commonSvc.findUniqueByProperty(TQstA.class, "type", resultlist.get(4).getKey()));
        model.addAttribute("three", commonSvc.findUniqueByProperty(TQstA.class, "type", resultlist.get(3).getKey()));
        String resultvalues = HOLD_MAP.get(resultlist.get(5).getKey()) + resultlist.get(5).getKey() + "," + HOLD_MAP.get(resultlist.get(4).getKey()) + resultlist.get(4).getKey() + "," + HOLD_MAP.get(resultlist.get(3).getKey()) + resultlist.get(3).getKey();
        model.addAttribute("resultvalue", resultvalues);
        // 专业匹配表
        List<Map<String, Object>> majorMatchingList = majorMatching(resultlist.get(5).getKey(), resultlist.get(4).getKey(), resultlist.get(3).getKey());

        model.addAttribute("majorMatchingList", majorMatchingList);
        // 前10专业匹配表
        model.addAttribute("tenList", tenList(resultlist.get(5).getKey(), resultlist.get(4).getKey(), resultlist.get(3).getKey()));
        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-hresult");

    }

    /**
     * 计算专业匹配
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param ontstr
     * @param twoStr
     * @param threestr
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<Map<String, Object>> majorMatching(String ontstr,
                                                   String twoStr, String threestr) {
        // 最终返回的list
        List<Map<String, Object>> lastlist = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> idlist = commonSvc
                .findForJdbc("SELECT DISTINCT major_parent_id FROM t_qst_A_major");
        for (Map<String, Object> map : idlist) {
            List<Map<String, Object>> thislist = new ArrayList<Map<String, Object>>();

            Map<String, Object> processMap = null;
            // 获取每个一个专业下的所有专业
            List<Map<String, Object>> majorlist = commonSvc
                    .findForJdbc("SELECT major_parent_id AS aid ,major_parent AS aname ,major_Id AS bid ,major AS bname ,type FROM t_qst_A_major WHERE major_parent_id='"
                            + map.get("major_parent_id").toString() + "'");


            Integer listsize = majorlist.size();
            for (int i = 0; i < listsize; i++) {
                String idsize = commonSvc
                        .findOneForJdbc(
                                "SELECT COUNT(*) as arow FROM t_qst_A_major WHERE major_parent_id='"
                                        + map.get("major_parent_id")
                                        .toString() + "'")
                        .get("arow").toString();

                if (i == 0) {
                    processMap = majorlist.get(i);
                    // 计算需要合并几格

                    if ("1".equals(idsize)) {
                        idsize = "0";
                    }
                    processMap.put("arow", idsize);
                    processMap.put("last", Integer.valueOf(idsize) - 1);
                    // 预留计算匹配分数和匹配程度
                    processMap = calculation(processMap, ontstr, twoStr, threestr);
                    // 将此条数据加入新list
                    thislist.add(processMap);

                } else {
                    // 第二条以后数据，不需要aid和aname,和arow
                    processMap = new HashMap<String, Object>();
                    processMap.put("bid", majorlist.get(i).get("bid"));
                    processMap.put("bname", majorlist.get(i).get("bname"));
                    processMap.put("type", majorlist.get(i).get("type"));
                    processMap.put("last", Integer.valueOf(idsize) - 1);
                    // 预留计算匹配分数和匹配程度
                    processMap = calculation(processMap, ontstr, twoStr, threestr);
                    // 将此条数据加入新list
                    thislist.add(processMap);
                }

            }
            Map<String, Object> objmap = new HashMap<String, Object>();
            objmap.put("list", thislist);
            lastlist.add(objmap);
        }

        return lastlist;

    }

    /**
     * 计算匹配分数和匹配值
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param map
     * @param pone
     * @param ptwo
     * @param pthree
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Map<String, Object> calculation(Map<String, Object> map, String pone, String ptwo, String pthree) {
        String one = pone + map.get("type").toString().substring(0, 1);
        String two = ptwo + map.get("type").toString().substring(1, 2);
        String three = pthree + map.get("type").toString().substring(2, 3);
        Integer fraction = 3 * (FRACTION_MAP.get(one)) + 2 * (FRACTION_MAP.get(two)) + 1 * (FRACTION_MAP.get(three));
        if (fraction >= 0 && fraction <= 6) {
            map.put("degree", "低度匹配");
        } else if (fraction >= 7 && fraction <= 12) {
            map.put("degree", "中度匹配");
        } else {
            map.put("degree", "高度匹配");
        }
        map.put("fraction", fraction);
        return map;

    }

    public List<Map<String, Object>> tenList(String pone, String ptwo, String pthree) {
        List<Map<String, Object>> fistlist = commonSvc.findForJdbc("SELECT major_Id AS bid ,major AS bname ,TYPE FROM t_qst_A_major");
        for (Map<String, Object> map : fistlist) {
            map = calculation(map, pone, ptwo, pthree);
        }

        fistlist = Descendinglist(fistlist, "fraction");


        return fistlist.subList(0, 10);

    }

    public List<Map<String, Object>> Descendinglist(
            List<Map<String, Object>> list, final String str) {
        // 降序
        Collections.sort(list, new Comparator<Map<String, Object>>() {

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = (Integer) o1.get(str);// name1是从你list里面拿出来的一个
                Integer name2 = (Integer) o2.get(str); // name2是从你list里面拿出来的第二个name
                return name2.compareTo(name1);
            }
        });

        return list;

    }

    /**
     * 查看holland职业测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/findcareerQuiz.jspx")
    public String findcareerQuiz(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model,String haveTianZhiyuan) {

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);

        // 获取测评者信息
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null || qstResult.getRstANum() == null || 0 == qstResult.getRstANum()) {
            return "redirect:careerQuiz.jspx";
        }


        model.addAttribute("date", format.format(qstResult.getResultADate()));
        model.addAttribute("qstResult", qstResult);
        //条形表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("R", new BigDecimal((float) qstResult.getRetAR() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("I", new BigDecimal((float) qstResult.getRetAI() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("A", new BigDecimal((float) qstResult.getRetAA() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("S", new BigDecimal((float) qstResult.getRetAS() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("C", new BigDecimal((float) qstResult.getRetAC() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("E", new BigDecimal((float) qstResult.getRetAE() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        model.addAttribute("txmap", map);
        // 结果分析

        String firstStr = qstResult.getRstA().substring(0, 1);
        String secondStr = qstResult.getRstA().substring(1, 2);
        String thirdStr = qstResult.getRstA().substring(2, 3);

        model.addAttribute("one", commonSvc.findUniqueByProperty(TQstA.class, "type", firstStr));
        model.addAttribute("twp", commonSvc.findUniqueByProperty(TQstA.class, "type", secondStr));
        model.addAttribute("three", commonSvc.findUniqueByProperty(TQstA.class, "type", thirdStr));
        String resultvalues = HOLD_MAP.get(firstStr) + firstStr + "," + HOLD_MAP.get(secondStr) + secondStr + "," + HOLD_MAP.get(thirdStr) + thirdStr;
        model.addAttribute("resultvalue", resultvalues);
        // 专业匹配表
        List<Map<String, Object>> majorMatchingList = majorMatching(firstStr, secondStr, thirdStr);

        model.addAttribute("majorMatchingList", majorMatchingList);
        // 前10专业匹配表
        model.addAttribute("tenList", tenList(firstStr, secondStr, thirdStr));
        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-hresult");

    }

    /**
     * ibm测评
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/MBTI.jspx")
    public String MBTI(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult != null && qstResult.getRstBNum() != null && qstResult.getRstBNum() == 3) {

            return "redirect:findMBTIReport.jspx";
        }
        model.addAttribute(
                "onelist",
                commonSvc
                        .findForJdbc("SELECT content,option1,value1,option2,value2 FROM t_qst WHERE type='B1'ORDER BY value1"));
        model.addAttribute(
                "twolist",
                commonSvc
                        .findForJdbc("SELECT option1,value1,option2,value2 FROM t_qst WHERE type='B2' ORDER BY value1"));

        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "MBTIcp");

    }

    /**
     * ibm测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param E
     * @param I
     * @param S
     * @param N
     * @param T
     * @param F
     * @param J
     * @param P
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/MBTIReport.jspx")
    public String MBTIReport(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model, Integer E, Integer I, Integer S, Integer N, Integer T, Integer F, Integer J, Integer P
    ) {
        //基本信息，准备工作
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        Integer userID = user.getId();
        String username = user.getUsername();

        //计算此同学是什么人格
        String personality = (String) ((E > I ? "E" : "I") + (S > N ? "S" : "N") + (T > F ? "T" : "F") + (J > P ? "J" : "P"));
        //保存此人测评信息
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null) {
            qstResult = new TQstResult();
            qstResult.setUserId(userID);
            qstResult.setUserName(username);
        }
        Integer rstb = 1;
        if (!StringUtils.isEmpty(qstResult.getRstBNum())) {
            rstb = qstResult.getRstBNum() + 1;
        }
        qstResult.setRstBNum(rstb);
        qstResult.setResultBDate(new Date());
        qstResult.setRstB(personality);
        qstResult.setRstBE(E);
        qstResult.setRstBF(F);
        qstResult.setRstBI(I);
        qstResult.setRstBJ(J);
        qstResult.setRstBN(N);
        qstResult.setRstBP(P);
        qstResult.setRstBS(S);
        qstResult.setRstBT(T);
        commonSvc.saveOrUpdate(qstResult);
        model.addAttribute("date", format.format(qstResult.getResultBDate()));
        model.addAttribute("qstResult", qstResult);
        //我的mibi人格部分
        Map<String, Object> myPersonalityMap = new HashMap<String, Object>();
        myPersonalityMap.put("title", personality + " " + IBM_MAP.get(E > I ? "E" : "I") + "|" + IBM_MAP.get(S > N ? "S" : "N") + "|" + IBM_MAP.get(T > F ? "T" : "F") + "|" + IBM_MAP.get(J > P ? "J" : "P"));
        myPersonalityMap.put("details", IBM_MAP.get(E > I ? "E" : "I") + "(" + (E > I ? "E" : "I") + ")" + "、" + IBM_MAP.get(S > N ? "S" : "N") + "(" + (S > N ? "S" : "N") + ")" + "、" + IBM_MAP.get(T > F ? "T" : "F") + "(" + (T > F ? "T" : "F") + ")" + "、" + IBM_MAP.get(J > P ? "J" : "P") + "(" + (J > P ? "J" : "P") + ")");
        Integer divisornum = 30;
        myPersonalityMap.put("onevalue", new BigDecimal((float) (E > I ? E : I) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("twovalue", new BigDecimal((float) (S > N ? S : N) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("threevalue", new BigDecimal((float) (T > F ? T : F) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("fourvalue", new BigDecimal((float) (J > P ? J : P) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        myPersonalityMap.put("one", E > I ? "E" : "I");
        myPersonalityMap.put("two", S > N ? "S" : "N");
        myPersonalityMap.put("three", T > F ? "T" : "F");
        myPersonalityMap.put("four", J > P ? "J" : "P");
        model.addAttribute("myPersonalityMap", myPersonalityMap);
        TQstB qstB = commonSvc.findUniqueByProperty(TQstB.class, "type", personality);
        model.addAttribute("qstB", qstB);

        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-mresult");

    }

    /**
     * 查看ibm测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/findMBTIReport.jspx")
    public String findMBTIReport(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model,String haveTianZhiyuan ) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
        //保存此人测评信息
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null || qstResult.getRstBNum() == null || 0 == qstResult.getRstBNum()) {
            return "redirect:MBTI.jspx";
        }

        model.addAttribute("date", format.format(qstResult.getResultBDate()));
        model.addAttribute("qstResult", qstResult);
        //我的mibi人格部分
        Map<String, Object> myPersonalityMap = new HashMap<String, Object>();
        String oneString = qstResult.getRstB().substring(0, 1);
        Integer onevalue = "E".equals(oneString) ? qstResult.getRstBE() : qstResult.getRstBI();

        String twoString = qstResult.getRstB().substring(1, 2);
        Integer twovalue = "S".equals(oneString) ? qstResult.getRstBS() : qstResult.getRstBN();

        String threeString = qstResult.getRstB().substring(2, 3);
        Integer threevalue = "T".equals(oneString) ? qstResult.getRstBT() : qstResult.getRstBF();

        String fourString = qstResult.getRstB().substring(3, 4);
        Integer fourvalue = "J".equals(oneString) ? qstResult.getRstBJ() : qstResult.getRstBF();


        myPersonalityMap.put("title", qstResult.getRstB() + " " + IBM_MAP.get(oneString) + "|" + IBM_MAP.get(twoString) + "|" + IBM_MAP.get(threeString) + "|" + IBM_MAP.get(fourString));
        myPersonalityMap.put("details", IBM_MAP.get(oneString) + "(" + (oneString) + ")" + "、" + IBM_MAP.get(twoString) + "(" + (twoString) + ")" + "、" + IBM_MAP.get(threeString) + "(" + (threeString) + ")" + "、" + IBM_MAP.get(fourString) + "(" + (fourString) + ")");
        Integer divisornum = 30;
        myPersonalityMap.put("onevalue", new BigDecimal((float) (onevalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("twovalue", new BigDecimal((float) (twovalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("threevalue", new BigDecimal((float) (threevalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("fourvalue", new BigDecimal((float) (fourvalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        myPersonalityMap.put("one", oneString);
        myPersonalityMap.put("two", twoString);
        myPersonalityMap.put("three", threeString);
        myPersonalityMap.put("four", fourString);
        model.addAttribute("myPersonalityMap", myPersonalityMap);
        TQstB qstB = commonSvc.findUniqueByProperty(TQstB.class, "type", qstResult.getRstB());
        model.addAttribute("qstB", qstB);

        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-mresult");

    }

    /**
     * 社会评价测评
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/sociaSupport.jspx")
    public String sociaSupport(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult != null && qstResult.getRstCNum() != null && qstResult.getRstCNum() == 3) {

            return "redirect:findsociaSupportReport.jspx";
        }

        model.addAttribute("onelist", commonSvc
                .findForJdbc("SELECT *  FROM t_qst WHERE type='C1'"));
        model.addAttribute("twolist", commonSvc
                .findForJdbc("SELECT *  FROM t_qst WHERE type='C2'"));
        model.addAttribute("threelist", commonSvc
                .findForJdbc("SELECT *  FROM t_qst WHERE type='C3'"));

        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "shzccp");

    }

    /**
     * 社会评价测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param A
     * @param B
     * @param C
     * @param majorname
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/sociaSupportReport.jspx")
    public String sociaSupportReport(HttpServletRequest request,
                                     HttpServletResponse response, ModelMap model, Integer A, Integer B, Integer C, String majorname) {
        //基本信息，准备工作
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Integer userID = user.getId();
        String username = user.getUsername();
        //保存此人社会支持信息
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null) {
            qstResult = new TQstResult();
            qstResult.setUserId(userID);
            qstResult.setUserName(username);
        }

        Integer rstc = 1;
        if (!StringUtils.isEmpty(qstResult.getRstCNum())) {
            rstc = qstResult.getRstCNum() + 1;
        }
        qstResult.setRstCNum(rstc);
        qstResult.setRstC1(A);
        qstResult.setRstC2(B);
        qstResult.setRstC3(C);
        qstResult.setResultCDate(new Date());
        qstResult.setRstCMajorName(majorname);
        commonSvc.saveOrUpdate(qstResult);
        model.addAttribute("date", format.format(qstResult.getResultCDate()));
        model.addAttribute("qstResult", qstResult);
        model.addAttribute("majorname", majorname);
        //获取测评结果信息
        Map<String, Object> reportMap = loadreport(A, B, C);
        model.addAttribute("reportMap", reportMap);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isallbg", "yes");
        if (qstResult != null) {
            if (qstResult.getRstANum() == null || (qstResult.getRstANum() != null && qstResult.getRstANum() == 0)) {
                map.put("isallbg", "no");
            }
            if (qstResult.getRstBNum() == null || (qstResult.getRstBNum() != null && qstResult.getRstBNum() == 0)) {
                map.put("isallbg", "no");
            }
            if (qstResult.getRstCNum() == null || (qstResult.getRstCNum() != null && qstResult.getRstCNum() == 0)) {
                map.put("isallbg", "no");
            }

        }

        model.addAttribute("is", map);
        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-sresult");
    }

    /**
     * 查看社会评价测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/findsociaSupportReport.jspx")
    public String findsociaSupportReport(HttpServletRequest request,
                                         HttpServletResponse response, ModelMap model,String haveTianZhiyuan ) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);
        if (qstResult == null || qstResult.getRstCNum() == null || 0 == qstResult.getRstCNum()) {
            return "redirect:sociaSupport.jspx";
        }
        model.addAttribute("date", format.format(qstResult.getResultCDate()));
        model.addAttribute("qstResult", qstResult);
        model.addAttribute("majorname", qstResult.getRstCMajorName());
        //获取测评结果信息
        Map<String, Object> reportMap = loadreport(qstResult.getRstC1(), qstResult.getRstC2(), qstResult.getRstC3());
        model.addAttribute("reportMap", reportMap);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isallbg", "yes");
        if (qstResult != null) {
            if (qstResult.getRstANum() == null || (qstResult.getRstANum() != null && qstResult.getRstANum() == 0)) {
                map.put("isallbg", "no");
            }
            if (qstResult.getRstBNum() == null || (qstResult.getRstBNum() != null && qstResult.getRstBNum() == 0)) {
                map.put("isallbg", "no");
            }
            if (qstResult.getRstCNum() == null || (qstResult.getRstCNum() != null && qstResult.getRstCNum() == 0)) {
                map.put("isallbg", "no");
            }

        }

        model.addAttribute("is", map);

        return FrontUtils.getTplPath(site.getSolutionPath(), "dlsy", "gkxkcs-sresult");
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param A
     * @param B
     * @param C
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private Map<String, Object> loadreport(Integer A, Integer B, Integer C) {
        String shqgzzc = "shqgzzc_" + findjb(A);
        String shrmzy = "shrmzy_" + findjb(B);
        String jjkxcd = "jjkxcd_" + findjb(C);
        Map<String, Object> loadreportmap = new HashMap<String, Object>();
        loadreportmap.put("A", findjb(A));
        loadreportmap.put("Aavlue", commonSvc.findUniqueByProperty(TQstC.class, "type", shqgzzc).getResult());

        loadreportmap.put("B", findjb(B));
        loadreportmap.put("Bavlue", commonSvc.findUniqueByProperty(TQstC.class, "type", shrmzy).getResult());

        loadreportmap.put("C", findjb(C));
        loadreportmap.put("Cavlue", commonSvc.findUniqueByProperty(TQstC.class, "type", jjkxcd).getResult());

        return loadreportmap;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param num
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String findjb(Integer num) {
        if (num >= 0 && num <= 6) {
            return "低度";
        } else if (num >= 7 && num <= 12) {
            return "中度";
        } else {
            return "高度";
        }
    }


    /**
     * 新高考测评
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/newGKXK.jspx")
    public String newGKXK(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        TQstDResult qstDresult = commonSvc.findUniqueByProperty(TQstDResult.class, "userId", String.valueOf(userId));
        if (qstDresult != null && qstDresult.getNumber() != null && 3 == qstDresult.getNumber()) {
            return "redirect:findNewGKXKReport.jspx";
        }
        model.addAttribute("onelist", commonSvc.findForJdbc("SELECT * FROM t_qst WHERE type='D1'"));
        model.addAttribute("twolist", commonSvc.findForJdbc("SELECT * FROM t_qst WHERE type='D2'"));
        model.addAttribute("threelist", commonSvc.findForJdbc("SELECT * FROM t_qst WHERE type='D3'"));
        model.addAttribute("fourlist", commonSvc.findForJdbc("SELECT * FROM t_qst WHERE type='D4'"));

        if (provinceId == 18) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newGkxk_zj");
        }

        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newGkxk");

    }

    /**
     * 新高考测评报报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/newGKXKReport.jspx")
    public String newGKXKReport(HttpServletRequest request, HttpServletResponse response, ModelMap model, String protype,
                                String report_A, String report_C, String report_E, String report_I, String report_S, String report_R,
                                String thirdWL, String thirdLS, String thirdDL, String thirdSW, String thirdZZ, String thirdHX, String thirdJS,
                                String fourthWL, String fourthLS, String fourthDL, String fourthSW, String fourthZZ, String fourthHX, String fourthJS
    ) {

        //准备工作，获取用户信息
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        //将数据全部转换为integer
        Integer reportA = Integer.parseInt(report_A);
        Integer reportC = Integer.parseInt(report_C);
        Integer reportE = Integer.parseInt(report_E);
        Integer reportI = Integer.parseInt(report_I);
        Integer reportS = Integer.parseInt(report_S);
        Integer reportR = Integer.parseInt(report_R);

        Integer fLS = null;

        Integer tWL = Integer.parseInt(thirdWL);
        Integer tLS = Integer.parseInt(thirdLS);
        Integer tDL = Integer.parseInt(thirdDL);
        Integer tSW = Integer.parseInt(thirdSW);
        Integer tZZ = Integer.parseInt(thirdZZ);
        Integer tHX = Integer.parseInt(thirdHX);

        Integer fWL = Integer.parseInt(fourthWL);
        try {
            fLS = Integer.parseInt(fourthLS);
        } catch (Exception e) {
            fLS = 32;
        }
        Integer fDL = Integer.parseInt(fourthDL);
        Integer fSW = Integer.parseInt(fourthSW);
        Integer fZZ = Integer.parseInt(fourthZZ);
        Integer fHX = Integer.parseInt(fourthHX);
        Integer tJS = null;
        Integer fJS = null;
        if ("zjpro".equals(protype)) {
            tJS = Integer.parseInt(thirdJS);
            fJS = Integer.parseInt(fourthJS);
        }

        //查看是否已超过测试上线
        TQstDResult qstDresult = commonSvc.findUniqueByProperty(TQstDResult.class, "userId", String.valueOf(userId));
        if (qstDresult != null && qstDresult.getNumber() != null && 3 == qstDresult.getNumber()) {
            return "redirect:findNewGKXKReport.jspx";
        }

        //类型数据排排序
        reportA = (int) Math.round(reportA / 4);
        reportC = (int) Math.round(reportC / 6);
        reportE = (int) Math.round(reportE / 5);
        reportI = (int) Math.round(reportI / 6);
        reportS = (int) Math.round(reportS / 6);
        reportR = (int) Math.round(reportR / 5);

        //计算个人性却代码
        String resultvalue = computerInterest(reportA, reportC, reportE, reportI, reportS, reportR);
        //计算专业兴趣分数
        List<Map<String, Object>> computerMajorInterestlist = computerMajorInterest(resultvalue.subSequence(0, 1).toString(), resultvalue.subSequence(1, 2).toString(), resultvalue.subSequence(2, 3).toString(), protype);

        //学科分布兴趣
        Map<String, Object> subjectInterestlistMap = findSubjectInterestlistMap(computerMajorInterestlist);
        model.addAttribute("subjectInterestlistMap", subjectInterestlistMap);

        //新增-刘永清的要求-将兴趣前三科拼起来保存
        String subjectStr = findsybjectStr(subjectInterestlistMap, protype);
        TestResult testresult = commonSvc.findUniqueByProperty(TestResult.class, "userId", userId);
        if (testresult == null) {
            testresult = new TestResult();
        }
        Integer school = 11;
        testresult.setUserId(userId);
        testresult.setSchool(school);
        testresult.setSubject(subjectStr);
        commonSvc.saveOrUpdate(testresult);

        //学业信心兴趣
        Map<String, Object> academicConfidenceMAp = findAcademicConfidenceMAp(tWL, tLS, tDL, tSW, tZZ, tHX, tJS, 10, protype);
        model.addAttribute("academicConfidenceMAp", academicConfidenceMAp);
        //发展期望
        Map<String, Object> expectMAp = findAcademicConfidenceMAp(fWL, fLS, fDL, fSW, fZZ, fHX, fJS, 6, protype);
        model.addAttribute("expectMAp", expectMAp);
        //社会支持
        Map<String, Object> socialSupportMap = findAcademicConfidenceMAp(fWL, fLS, fDL, fSW, fZZ, fHX, fJS, 6, protype);
        model.addAttribute("socialSupportMap", socialSupportMap);

        //计算匹配总分
        List<Map<String, Object>> list = sumlist(computerMajorInterestlist, subjectInterestlistMap, academicConfidenceMAp, expectMAp, socialSupportMap, protype);
        model.addAttribute("computerMajorInterestlist", list);

        //报告生成完成，保存信息
        if (qstDresult == null) {
            qstDresult = new TQstDResult();
            qstDresult.setUserId(String.valueOf(userId));
        }
        qstDresult.setR(reportR);
        qstDresult.setI(reportI);
        qstDresult.setA(reportA);
        qstDresult.setS(reportS);
        qstDresult.setE(reportE);
        qstDresult.setC(reportC);
        qstDresult.setBest(resultvalue);

        qstDresult.setWuliD3(tWL);
        qstDresult.setLishiD3(tLS);
        qstDresult.setDiliD3(tDL);
        qstDresult.setShengwuD3(tSW);
        qstDresult.setZhengzhiD3(tZZ);
        qstDresult.setHuaxueD3(tHX);

        qstDresult.setWuliD4(fWL);
        qstDresult.setLishiD4(fLS);
        qstDresult.setDiliD4(fDL);
        qstDresult.setShengwuD4(fSW);
        qstDresult.setZhengzhiD4(fZZ);
        qstDresult.setHuaxueD4(fHX);
        //新增技术
        qstDresult.setJishuD3(tJS);
        qstDresult.setJishuD4(fJS);
        qstDresult.setTime(new Date());
        Integer rstb = 1;
        if (!StringUtils.isEmpty(qstDresult.getNumber())) {
            rstb = qstDresult.getNumber() + 1;
        }
        qstDresult.setNumber(rstb);
        commonSvc.saveOrUpdate(qstDresult);

        String username = user.getRealname();
        model.addAttribute("date", format.format(qstDresult.getTime()));
        model.addAttribute("name", username);

        if ("zjpro".equals(protype)) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newgkxkcs-result_zj");
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newgkxkcs-result");

    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param computerMajorInterestlist
     * @param subjectInterestlistMap
     * @param academicConfidenceMAp
     * @param expectMAp
     * @param socialSupportMap
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private List<Map<String, Object>> sumlist(
            List<Map<String, Object>> computerMajorInterestlist,
            Map<String, Object> subjectInterestlistMap,
            Map<String, Object> academicConfidenceMAp,
            Map<String, Object> expectMAp,
            Map<String, Object> socialSupportMap,
            String protype) {
        //生物
        Integer sWNum = Integer.parseInt(subjectInterestlistMap.get("生物").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("sw").toString()) +
                Integer.parseInt(expectMAp.get("sw").toString()) +
                Integer.parseInt(socialSupportMap.get("sw").toString());
        //物理
        Integer wLNum = Integer.parseInt(subjectInterestlistMap.get("物理").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("wl").toString()) +
                Integer.parseInt(expectMAp.get("wl").toString()) +
                Integer.parseInt(socialSupportMap.get("wl").toString());
        //化学
        Integer hXNum = Integer.parseInt(subjectInterestlistMap.get("化学").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("hx").toString()) +
                Integer.parseInt(expectMAp.get("hx").toString()) +
                Integer.parseInt(socialSupportMap.get("hx").toString());
        //政治
        Integer zZNum = Integer.parseInt(subjectInterestlistMap.get("政治").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("zz").toString()) +
                Integer.parseInt(expectMAp.get("zz").toString()) +
                Integer.parseInt(socialSupportMap.get("zz").toString());
        //地理
        Integer dLNum = Integer.parseInt(subjectInterestlistMap.get("地理").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("dl").toString()) +
                Integer.parseInt(expectMAp.get("dl").toString()) +
                Integer.parseInt(socialSupportMap.get("dl").toString());
        //历史
        Integer lSNum = Integer.parseInt(subjectInterestlistMap.get("历史").toString()) +
                Integer.parseInt(academicConfidenceMAp.get("ls").toString()) +
                Integer.parseInt(expectMAp.get("ls").toString()) +
                Integer.parseInt(socialSupportMap.get("ls").toString());
        //技术
        Integer jSNum = 0;
        if ("zjpro".equals(protype)) {
            jSNum = Integer.parseInt(subjectInterestlistMap.get("技术").toString()) +
                    Integer.parseInt(academicConfidenceMAp.get("js").toString()) +
                    Integer.parseInt(expectMAp.get("js").toString()) +
                    Integer.parseInt(socialSupportMap.get("js").toString());
        }
        for (Map<String, Object> map : computerMajorInterestlist) {
            //生物
            if ("生物".equals(map.get("subject"))) {
                map.put("num", sWNum);
            }
            //物理
            if ("物理".equals(map.get("subject"))) {
                map.put("num", wLNum);
            }
            //化学
            if ("化学".equals(map.get("subject"))) {
                map.put("num", hXNum);
            }
            //政治
            if ("政治".equals(map.get("subject"))) {
                map.put("num", zZNum);
            }
            //历史
            if ("历史".equals(map.get("subject"))) {
                map.put("num", lSNum);
            }
            //地理
            if ("地理".equals(map.get("subject"))) {
                map.put("num", dLNum);
            }
            //技术
            if ("zjpro".equals(protype)) {
                if ("技术".equals(map.get("subject"))) {
                    map.put("num", jSNum);
                }
            }


        }


        computerMajorInterestlist = Descendinglist(computerMajorInterestlist, "num");

        return computerMajorInterestlist;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param subjectInterestlistMap
     * @param protype
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String findsybjectStr(Map<String, Object> subjectInterestlistMap, String protype) {

        // 保存个人兴趣前三专业
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        resultMap.put("政治", Integer.parseInt(subjectInterestlistMap.get("政治").toString()));
        resultMap.put("历史", Integer.parseInt(subjectInterestlistMap.get("历史").toString()));
        resultMap.put("地理", Integer.parseInt(subjectInterestlistMap.get("地理").toString()));
        resultMap.put("物理", Integer.parseInt(subjectInterestlistMap.get("物理").toString()));
        resultMap.put("化学", Integer.parseInt(subjectInterestlistMap.get("化学").toString()));
        resultMap.put("生物", Integer.parseInt(subjectInterestlistMap.get("生物").toString()));
        //如果是浙江、新增技术
        if ("zjpro".equals(protype)) {
            resultMap.put("技术", Integer.parseInt(subjectInterestlistMap.get("技术").toString()));
        }


        List<Entry<String, Integer>> resultlist = new ArrayList<Entry<String, Integer>>(resultMap.entrySet());
        Collections.sort(resultlist, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2) {
                int flag = o1.getValue().compareTo(o2.getValue());
                if (flag == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return flag;
            }
        });
        String resultvalue = "";
        if ("zjpro".equals(protype)) {
            resultvalue = resultlist.get(6).getKey() + "-" + resultlist.get(5).getKey() + "-" + resultlist.get(4).getKey();
        } else {
            resultvalue = resultlist.get(5).getKey() + "-" + resultlist.get(4).getKey() + "-" + resultlist.get(3).getKey();
        }


        return resultvalue;
    }

    /**
     * 查看新高考测评报告
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/evaluation/findNewGKXKReport.jspx")
    public String findNewGKXKReport(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        //查看是否已超过测试上线
        TQstDResult qstDresult = commonSvc.findUniqueByProperty(TQstDResult.class, "userId", String.valueOf(userId));
        if (qstDresult == null || qstDresult.getNumber() == null || 0 == qstDresult.getNumber()) {
            return "redirect:newGKXK.jspx";
        }
        model.addAttribute("date", format.format(qstDresult.getTime()));
        String username = user.getRealname();
        model.addAttribute("name", username);
        Integer thirdWL = qstDresult.getWuliD3();
        Integer thirdLS = qstDresult.getLishiD3();
        Integer thirdDL = qstDresult.getDiliD3();
        Integer thirdSW = qstDresult.getShengwuD3();
        Integer thirdZZ = qstDresult.getZhengzhiD3();
        Integer thirdHX = qstDresult.getHuaxueD3();
        Integer fourthWL = qstDresult.getWuliD4();
        Integer fourthLS = qstDresult.getLishiD4();
        Integer fourthDL = qstDresult.getDiliD4();
        Integer fourthSW = qstDresult.getShengwuD4();
        Integer fourthZZ = qstDresult.getZhengzhiD4();
        Integer fourthHX = qstDresult.getHuaxueD4();

        //新增技术
        Integer thirdJS = qstDresult.getJishuD3();
        Integer fourthJS = qstDresult.getJishuD4();
        String resultvalue = qstDresult.getBest();

        String protype = null;
        if (provinceId == 18) {
            protype = "zjpro";
        }
        //计算专业兴趣分数
        List<Map<String, Object>> computerMajorInterestlist = computerMajorInterest(resultvalue.subSequence(0, 1).toString(), resultvalue.subSequence(1, 2).toString(), resultvalue.subSequence(2, 3).toString(), protype);

        //学科分布兴趣
        Map<String, Object> subjectInterestlistMap = findSubjectInterestlistMap(computerMajorInterestlist);
        model.addAttribute("subjectInterestlistMap", subjectInterestlistMap);
        //学业信心兴趣
        Map<String, Object> academicConfidenceMAp = findAcademicConfidenceMAp(thirdWL, thirdLS, thirdDL, thirdSW, thirdZZ, thirdHX, thirdJS, 10, protype);
        model.addAttribute("academicConfidenceMAp", academicConfidenceMAp);
        //发展期望
        Map<String, Object> expectMAp = findAcademicConfidenceMAp(fourthWL, fourthLS, fourthDL, fourthSW, fourthZZ, fourthHX, fourthJS, 6, protype);
        model.addAttribute("expectMAp", expectMAp);
        //社会支持
        Map<String, Object> socialSupportMap = findAcademicConfidenceMAp(fourthWL, fourthLS, fourthDL, fourthSW, fourthZZ, fourthHX, fourthJS, 6, protype);
        model.addAttribute("socialSupportMap", socialSupportMap);
        //计算匹配总分
        List<Map<String, Object>> list = sumlist(computerMajorInterestlist, subjectInterestlistMap, academicConfidenceMAp, expectMAp, socialSupportMap, protype);
        model.addAttribute("computerMajorInterestlist", list);
        if ("zjpro".equals(protype)) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newgkxkcs-result_zj");
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "newgkxkcs-result");

    }

    /**
     * 功能描述: <br>
     * <p>
     * //学业信心兴趣
     * 〈功能详细描述〉
     *
     * @param thirdWL
     * @param thirdLS
     * @param thirdDL
     * @param thirdSW
     * @param thirdZZ
     * @param thirdHH
     * @param divisorNum
     * @param protype
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private Map<String, Object> findAcademicConfidenceMAp(Integer thirdWL,
                                                          Integer thirdLS, Integer thirdDL, Integer thirdSW, Integer thirdZZ,
                                                          Integer thirdHH, Integer thirdJS, Integer divisorNum, String protype) {
        Map<String, Object> reportMap = new HashMap<String, Object>();
        String gdStr = "";
        String zdStr = "";
        String ddStr = "";
        //物理
        double f1 = new BigDecimal((float) thirdWL / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer wlNum = (int) Math.round((f1 - 1) * 3);
        if (wlNum >= 0 && wlNum <= 6) {
            ddStr = ddStr + "物理、";
        } else if (wlNum >= 7 && wlNum <= 12) {
            zdStr = zdStr + "物理、";
        } else {
            gdStr = gdStr + "物理、";
        }
        reportMap.put("wl", wlNum);

        //生物
        double f2 = new BigDecimal((float) thirdSW / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer swNum = (int) Math.round((f2 - 1) * 3);
        if (swNum >= 0 && swNum <= 6) {
            ddStr = ddStr + "生物、";
        } else if (swNum >= 7 && swNum <= 12) {
            zdStr = zdStr + "生物、";
        } else {
            gdStr = gdStr + "生物、";
        }
        reportMap.put("sw", swNum);

        //化学
        double f3 = new BigDecimal((float) thirdHH / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer hxNum = (int) Math.round((f3 - 1) * 3);
        if (hxNum >= 0 && hxNum <= 6) {
            ddStr = ddStr + "化学、";
        } else if (hxNum >= 7 && hxNum <= 12) {
            zdStr = zdStr + "化学、";
        } else {
            gdStr = gdStr + "化学、";
        }
        reportMap.put("hx", hxNum);

        //政治
        double f4 = new BigDecimal((float) thirdZZ / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer zzNum = (int) Math.round((f4 - 1) * 3);
        if (zzNum >= 0 && zzNum <= 6) {
            ddStr = ddStr + "政治、";
        } else if (zzNum >= 7 && zzNum <= 12) {
            zdStr = zdStr + "政治、";
        } else {
            gdStr = gdStr + "政治、";
        }
        reportMap.put("zz", zzNum);

        //地理
        double f5 = new BigDecimal((float) thirdDL / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer dlNum = (int) Math.round((f5 - 1) * 3);
        if (dlNum >= 0 && dlNum <= 6) {
            ddStr = ddStr + "地理、";
        } else if (dlNum >= 7 && dlNum <= 12) {
            zdStr = zdStr + "地理、";
        } else {
            gdStr = gdStr + "地理、";
        }
        reportMap.put("dl", dlNum);

        //历史
        double f6 = new BigDecimal((float) thirdLS / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Integer lsNum = (int) Math.round((f6 - 1) * 3);
        if (lsNum >= 0 && lsNum <= 6) {
            ddStr = ddStr + "历史、";
        } else if (lsNum >= 7 && lsNum <= 12) {
            zdStr = zdStr + "历史、";
        } else {
            gdStr = gdStr + "历史、";
        }
        reportMap.put("ls", lsNum);

        //技术

        if ("zjpro".equals(protype)) {
            double f7 = new BigDecimal((float) thirdJS / divisorNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Integer jsNum = (int) Math.round((f7 - 1) * 3);
            if (jsNum >= 0 && jsNum <= 6) {
                ddStr = ddStr + "技术、";
            } else if (jsNum >= 7 && jsNum <= 12) {
                zdStr = zdStr + "技术、";
            } else {
                gdStr = gdStr + "技术、";
            }
            reportMap.put("js", jsNum);
        }


        if (!StringUtils.isEmpty(ddStr)) {
            reportMap.put("ddStr", ddStr.substring(0, ddStr.length() - 1));
        }
        if (!StringUtils.isEmpty(zdStr)) {
            reportMap.put("zdStr", zdStr.substring(0, zdStr.length() - 1));
        }
        if (!StringUtils.isEmpty(gdStr)) {
            reportMap.put("gdStr", gdStr.substring(0, gdStr.length() - 1));
        }
        return reportMap;
    }

    /**
     * 计算个人兴趣代码
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param report_A
     * @param report_C
     * @param report_E
     * @param report_I
     * @param report_S
     * @param report_R
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String computerInterest(Integer report_A, Integer report_C, Integer report_E, Integer report_I, Integer report_S, Integer report_R) {
        Map<String, Integer> reportMap = new HashMap<String, Integer>();
        reportMap.put("A", report_A);
        reportMap.put("C", report_C);
        reportMap.put("E", report_E);
        reportMap.put("I", report_I);
        reportMap.put("S", report_S);
        reportMap.put("R", report_R);

        List<Entry<String, Integer>> resultlist = new ArrayList<Entry<String, Integer>>(
                reportMap.entrySet());
        Collections.sort(resultlist, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2) {
                int flag = o1.getValue().compareTo(o2.getValue());
                if (flag == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return flag;
            }
        });
        // 获取测评成绩
        String resultvalue = resultlist.get(5).getKey()
                + resultlist.get(4).getKey() + resultlist.get(3).getKey();

        return resultvalue;
    }

    /**
     * 计算个人兴趣专业
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param pone
     * @param ptwo
     * @param pthree
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<Map<String, Object>> computerMajorInterest(String pone, String ptwo, String pthree, String protype) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> repeatMap = null;

        List<Map<String, Object>> repeatList = commonSvc.findForJdbc("SELECT major_type FROM t_qst_D_major GROUP BY major_type");

        for (Map<String, Object> subjectmap : repeatList) {
            String subject = (String) subjectmap.get("major_type");

            //新加技术/如果是浙江才有技术
            if (!"zjpro".equals(protype)) {
                if ("技术".equals(subject)) {
                    continue;
                }
            }


            Integer zznum = 0;
            String zzStr = "";
            repeatMap = new HashMap<String, Object>();
            List<Map<String, Object>> zzList = commonSvc.findForJdbc("SELECT * FROM t_qst_D_major WHERE major_type='" + subject + "'");
            for (Map<String, Object> map : zzList) {
                String one = pone + map.get("type").toString().substring(0, 1);
                String two = ptwo + map.get("type").toString().substring(1, 2);
                String three = pthree + map.get("type").toString().substring(2, 3);
                Integer fraction = 3 * (FRACTION_MAP.get(one)) + 2 * (FRACTION_MAP.get(two)) + 1 * (FRACTION_MAP.get(three));
                zznum = zznum + fraction;
                zzStr = zzStr + map.get("major").toString() + "、";
            }
            repeatMap.put("subject", subject);
            repeatMap.put("num", zznum);
            repeatMap.put("size", zzList.size());
            repeatMap.put("major", zzStr.subSequence(0, zzStr.length() - 1));

            list.add(repeatMap);
        }

        list = Descendinglist(list, "num");

        return list;

    }

    /**
     * 计算各学科分布兴趣
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param computerMajorInterestlist
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private Map<String, Object> findSubjectInterestlistMap(List<Map<String, Object>> computerMajorInterestlist) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String gdStr = "";
        String zdStr = "";
        String ddStr = "";
        for (Map<String, Object> map : computerMajorInterestlist) {
            double f1 = new BigDecimal((float) Integer.parseInt(map.get("num").toString()) / Integer.parseInt(map.get("size").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Integer subjectNum = (int) Math.round((f1));
            returnMap.put((String) map.get("subject"), subjectNum);
            if (subjectNum >= 0 && subjectNum <= 6) {
                ddStr = ddStr + map.get("subject").toString() + "、";
            } else if (subjectNum >= 7 && subjectNum <= 12) {
                zdStr = zdStr + map.get("subject").toString() + "、";
            } else {
                gdStr = gdStr + map.get("subject").toString() + "、";
            }
        }
        if (!StringUtils.isEmpty(ddStr)) {
            returnMap.put("ddStr", ddStr.substring(0, ddStr.length() - 1));
        }
        if (!StringUtils.isEmpty(zdStr)) {
            returnMap.put("zdStr", zdStr.substring(0, zdStr.length() - 1));
        }
        if (!StringUtils.isEmpty(gdStr)) {
            returnMap.put("gdStr", gdStr.substring(0, gdStr.length() - 1));
        }


        return returnMap;
    }


    @RequestMapping(value = "/evaluation/allcs.jspx")
    public String allcs(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model,String haveTianZhiyuan) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
        // 获取测评者信息
        Integer userID = user.getId();
        TQstResult qstResult = commonSvc.findUniqueByProperty(TQstResult.class, "userId", userID);

        model.addAttribute("date", format.format(qstResult.getResultADate()));
        model.addAttribute("qstResult", qstResult);
        //条形表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("R", new BigDecimal((float) qstResult.getRetAR() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("I", new BigDecimal((float) qstResult.getRetAI() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("A", new BigDecimal((float) qstResult.getRetAA() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("S", new BigDecimal((float) qstResult.getRetAS() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("C", new BigDecimal((float) qstResult.getRetAC() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        map.put("E", new BigDecimal((float) qstResult.getRetAE() / 17).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        model.addAttribute("txmap", map);
        // 结果分析

        String firstStr = qstResult.getRstA().substring(0, 1);
        String secondStr = qstResult.getRstA().substring(1, 2);
        String thirdStr = qstResult.getRstA().substring(2, 3);

        model.addAttribute("one", commonSvc.findUniqueByProperty(TQstA.class, "type", firstStr));
        model.addAttribute("twp", commonSvc.findUniqueByProperty(TQstA.class, "type", secondStr));
        model.addAttribute("three", commonSvc.findUniqueByProperty(TQstA.class, "type", thirdStr));
        String resultvalues = HOLD_MAP.get(firstStr) + firstStr + "," + HOLD_MAP.get(secondStr) + secondStr + "," + HOLD_MAP.get(thirdStr) + thirdStr;
        model.addAttribute("resultvalue", resultvalues);
        // 专业匹配表
        List<Map<String, Object>> majorMatchingList = majorMatching(firstStr, secondStr, thirdStr);

        model.addAttribute("majorMatchingList", majorMatchingList);
        // 前10专业匹配表
        model.addAttribute("tenList", tenList(firstStr, secondStr, thirdStr));


        //ibm报告

        model.addAttribute("date", format.format(qstResult.getResultBDate()));
        model.addAttribute("qstResult", qstResult);
        //我的mibi人格部分
        Map<String, Object> myPersonalityMap = new HashMap<String, Object>();
        String oneString = qstResult.getRstB().substring(0, 1);
        Integer onevalue = "E".equals(oneString) ? qstResult.getRstBE() : qstResult.getRstBI();

        String twoString = qstResult.getRstB().substring(1, 2);
        Integer twovalue = "S".equals(oneString) ? qstResult.getRstBS() : qstResult.getRstBN();

        String threeString = qstResult.getRstB().substring(2, 3);
        Integer threevalue = "T".equals(oneString) ? qstResult.getRstBT() : qstResult.getRstBF();

        String fourString = qstResult.getRstB().substring(3, 4);
        Integer fourvalue = "J".equals(oneString) ? qstResult.getRstBJ() : qstResult.getRstBF();


        myPersonalityMap.put("title", qstResult.getRstB() + " " + IBM_MAP.get(oneString) + "|" + IBM_MAP.get(twoString) + "|" + IBM_MAP.get(threeString) + "|" + IBM_MAP.get(fourString));
        myPersonalityMap.put("details", IBM_MAP.get(oneString) + "(" + (oneString) + ")" + "、" + IBM_MAP.get(twoString) + "(" + (twoString) + ")" + "、" + IBM_MAP.get(threeString) + "(" + (threeString) + ")" + "、" + IBM_MAP.get(fourString) + "(" + (fourString) + ")");
        Integer divisornum = 30;
        myPersonalityMap.put("onevalue", new BigDecimal((float) (onevalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("twovalue", new BigDecimal((float) (twovalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("threevalue", new BigDecimal((float) (threevalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        myPersonalityMap.put("fourvalue", new BigDecimal((float) (fourvalue) / divisornum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);

        myPersonalityMap.put("one", oneString);
        myPersonalityMap.put("two", twoString);
        myPersonalityMap.put("three", threeString);
        myPersonalityMap.put("four", fourString);
        model.addAttribute("myPersonalityMap", myPersonalityMap);
        TQstB qstB = commonSvc.findUniqueByProperty(TQstB.class, "type", qstResult.getRstB());
        model.addAttribute("qstB", qstB);

        //社会支持报告
        model.addAttribute("date", format.format(qstResult.getResultBDate()));
        model.addAttribute("qstResult", qstResult);
        model.addAttribute("majorname", qstResult.getRstCMajorName());
        //获取测评结果信息
        Map<String, Object> reportMap = loadreport(qstResult.getRstC1(), qstResult.getRstC2(), qstResult.getRstC3());
        model.addAttribute("reportMap", reportMap);

        return FrontUtils.getTplPath(site.getSolutionPath(), "gkxk", "allcs");

    }

}
