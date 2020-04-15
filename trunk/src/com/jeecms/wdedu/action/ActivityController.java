/*


 * 
 * Copyright (C), 2017-2018 志愿无忧
 * FileName: ActivityController.java
 * Author:   panglv
 * Date:     2018年1月15日 上午9:48:04
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jeecms.wdedu.action;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.TScActiveDetail;
import com.jeecms.wdedu.entity.TScActiveTeacher;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.Colorewmxz;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.jeecms.common.page.SimplePage.cpn;

//import com.utils.ExcelUtil;

//import com.jeecms.common.hibernate3.Finder;
//import com.zywy.entity.t_d_province;
//import com.zywy.entity.sign.ActiveDetail;
//import com.zywy.entity.sign.AdmissionTicket;
//import com.zywy.entity.sign.ActiveTeacher;


/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author panglv
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class ActivityController {
    private static final TScActiveTeacher ActiveTeacher = null;
    SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd hh:mm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
  /**
     * 权限设计
     * 功能描述: <br>
     * 〈分公司的管理层只能看到分公司的数据，admin用户可以看到所有的用户数据〉
     */
    public Integer Quanxian(HttpServletRequest request,HttpServletResponse response,ModelMap model
         ) throws IOException{
           CmsSite site = CmsUtils.getSite(request);
           FrontUtils.frontData(request, model, site);
           CmsUser user = CmsUtils.getUser(request);
           
           Integer  qxpro_id=null;
        return null;
    }
    
    
    /**
     * 查询活动接口
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param province
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
      @RequestMapping(value="/active/activeList.jspx" )   
/*    @RequestMapping(value="/active/activeList.jspx" ,method=RequestMethod.GET)*/   
    public String ActiveList(HttpServletRequest request,HttpServletResponse response,ModelMap model
        ,String province,String city,String active_start_time,String active_end_time,
        String activeName,String teachname,String type, Integer pageNo,Integer pageSize) throws IOException{
       CmsSite site = CmsUtils.getSite(request);
       FrontUtils.frontData(request, model, site);
       /*
        * 查询条件
        */       
       Finder f = Finder.create("FROM  TScActiveDetail bean WHERE 1=1");
       //虚拟查询条件,便于后面增加条件
       if (!StringUtils.isEmpty(province) && !"none".equals(province)) {
           if(!StringUtils.isEmpty(city) && !"none".equals(city)){
               Map<String,Object> aa=Searcharea(province,city,null);
               String pro_name = aa.get("pro_name").toString();    
               String city_name = aa.get("city_name").toString();  
               f.append(" and bean.province='"+pro_name+"'");
               f.append(" and bean.city='"+city_name+"'");
           }
       }         
       if (!StringUtils.isEmpty(province) && !"none".equals(province)) {
           if(StringUtils.isEmpty(city) || "none".equals(city))
           {
               Map<String,Object> cc=Searcharea(province,null,null);
               String pro_name = cc.get("pro_name").toString();   
               f.append(" and bean.province='"+pro_name+"'");
           }
       }
                
       if (!StringUtils.isEmpty(active_start_time)) {
           f.append(" and bean.active_start_time>='"+active_start_time+"'");
       }
       
       if (!StringUtils.isEmpty(active_end_time)) {
           f.append("  and bean.active_end_time=<'"+active_end_time+"'");
       }

       f.append(" order by bean.id desc");
       Pagination pagination = commonService.findPager(f, cpn(pageNo), 20);
      
       model.addAttribute("pagination", pagination);
       model.addAttribute("pageNo",  cpn(pageNo));

       String allhtml="";
       if("ckjz".equals(type)){
           allhtml="jzhdManage-jzlb";
       }else if("xzewm".equals(type)){
           allhtml="jzhdManage-qrcode";
//       }else if("dc".equals(type)){
//           allhtml="jzhdManage-jzmd";
       }else if("cksy".equals(type)){
           allhtml="jzhdManage-allmd";
       }else if(type==null){
           allhtml="jzhdManage";
       }       
       //return "zywy/sign/jzhd";
       
       
       return FrontUtils.getTplPath(site.getSolutionPath(), "sign", allhtml);
       
   }
    
    /**
     * \
     * 
     * 功能描述: <br>
     * 〈活动发布页面〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/releasehtml.jspx")
    public String releasehtml(HttpServletRequest request,HttpServletResponse response,ModelMap model){
        
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        //需要修改
        String sqlteacher="SELECT name from t_sc_active_teacher group by teacher_id";
        List<Map<String,Object>> teacherlist=commonService.findForJdbc(sqlteacher);      
        model.addAttribute("teacherlist",teacherlist );
        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-addjz");
    }  
    
    /**
     * 功能描述: <br>
     * 〈活动发布提交接口〉
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ParseException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/releasehtml.jspx",method=RequestMethod.POST)
    public String releaseSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap model,
            String  province    ,// 活动省份    varchar(11)
            String  city    ,// 活动城市    varchar(11)
            String  quxian  ,// 活动区县    varchar(11)
            String  lecturer1   ,// 主讲老师1   varchar(20)
            String  lecturer2   ,// 主讲老师2   varchar(11)
            String  address ,// 活动地址    varchar(100)
            String  active_time ,// 活动举办时间  datetime
            String  theme   ,// 活动主题内容  varchar(32)
            Integer  product_Id  ,// 产品id    int(11)
            Integer  free    ,// 活动收费情况  int(11)
            String  active_start_time   ,// 活动开始报名时间    datetime
            String  active_end_time ,// 活动结束报名时间    datetime
            String  qd_start_time   ,// 开始签到时间，活动前四小时   datetime
            String  qd_end_time ,// 结束签到时间  datetime
            String  host_department ,// 活动的主办部门 varchar(32)
            String  image   ,// 活动主题图片  varchar(100)
            String  lqzl_content    ,// 领取资料的内容 varchar(255)
            String  details ,// 活动详情    varchar(400)
            String  active_status   ,// 活动状态    varchar(20)
            String  lecturers   ,//     varchar(255)
            String  remark  ,//     varchar(255)
            Integer people
            ) throws ParseException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        //活动ID
        
        Map<String,Object> aa=Searcharea(province,city,quxian);
        String pro_name = aa.get("pro_name").toString();        
        String city_name="";       
        if(!StringUtils.isEmpty(aa.get("city_name")))
        {
            city_name = aa.get("city_name").toString();  
        }else{
            city_name=null; 
        }
        String qxname = "";
        if(!StringUtils.isEmpty(aa.get("quxian_name"))){
               qxname = aa.get("quxian_name").toString(); 
        }else{
               qxname=null;
        }  
        //获取讲师的照片链接地址
        String lecturer_picture1="";
        String lecturer_picture2="";
        String sqlimage="SELECT image1 AS image  FROM t_sc_active_teacher WHERE teacher_Id ='"+lecturer1+"' LIMIT 1";
        String sqlimage1="SELECT image1 AS image  FROM t_sc_active_teacher WHERE teacher_Id ='"+lecturer2+"' LIMIT 1";
        List<Map<String,Object>> imagelist=commonService.findForJdbc(sqlimage); 
        List<Map<String,Object>> imagelist1=commonService.findForJdbc(sqlimage1);
        
        if(imagelist.size()!=0){
            Map<String,Object> map1=imagelist.get(0);
            lecturer_picture1=map1.get("image").toString();
        }else{
            lecturer_picture1="/zywy/u/cms/www/201612/17000.jpg";
        }
        
        if(!"0".equals(lecturer2) && !"".equals(lecturer2)){
            lecturer_picture2="/zywy/u/cms/www/201612/17000.jpg";
        }else{
            lecturer_picture2="";
        }
  
        if(imagelist1.size()!=0){
            Map<String,Object> map2=imagelist1.get(0);
            lecturer_picture2=map2.get("image").toString();
        }
        //确定省份id 
        Map<String, Object> promap=commonService.findOneForJdbc("SELECT pro_id FROM t_xueji_sign WHERE pro_name='"+pro_name+"' LIMIT 0,1");
        String pro_id=promap.get("pro_id").toString();
        
        //teacher1_id,teacher2_id的带入
        Integer teacher1_id=null;
        String lecturer_name1="";
        if(!"0".equals(lecturer1)){
           Map<String, Object> name1=commonService.findOneForJdbc("SELECT name FROM t_sc_active_teacher WHERE teacher_Id='"+lecturer1+"' LIMIT 0,1");
           lecturer_name1=name1.get("name").toString();  
           teacher1_id=Integer.parseInt(lecturer1.toString()); 
        }
               
        Integer teacher2_id=null;
        String lecturer_name2="";
        if(!"0".equals(lecturer2)){
            Map<String, Object> name2=commonService.findOneForJdbc("SELECT name FROM t_sc_active_teacher WHERE teacher_Id='"+lecturer2+"' LIMIT 0,1");
             lecturer_name2=name2.get("name").toString();           
            teacher2_id=Integer.parseInt(lecturer2.toString()); 
        }
               
        //新建活动id
        Integer activeId=createactiveId(pro_name);
        //新建活动
        TScActiveDetail activeDetail =createactiveDetail(pro_id,pro_name, city_name, qxname, lecturer_name1,  lecturer_name2, address, active_time, theme, product_Id, free, active_start_time, active_end_time, host_department,image, lqzl_content, details, active_status, lecturers, remark,people, activeId,lecturer_picture1,lecturer_picture2,teacher1_id,teacher2_id);
        commonService.saveOrUpdate(activeDetail);      
        return "redirect:activeList.jspx?type=ckjz";
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈删除活动〉
     *
     * @param request
     * @param response
     * @param model
     * @param activeid
     * @throws IOException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/deletehtml.jspx",method={RequestMethod.POST,RequestMethod.GET}) 
    public String delectactive(HttpServletRequest request,HttpServletResponse response,ModelMap model,String activeid) throws IOException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> map=new HashMap<String, Object>();
        String []activeids=activeid.split(",");
        for (String string : activeids) {
            TScActiveDetail admissionTicketlist=commonService.getEntity(TScActiveDetail.class, Integer.parseInt(string));
            if (admissionTicketlist!=null ) {
                String sql="DELETE FROM t_sc_active_detail WHERE id = '"+string+"'";
                commonService.executeSql(sql);
                map.put("success", true);
                map.put("msg", "删除成功!");
               
            }else{
                map.put("success", false);
                map.put("msg", "亲,活动已经删除!");
            }
        }        
        return "redirect:activeList.jspx?type=ckjz";
    }
    
    
    
    
    /**
     * 复制活动
     * 功能描述: <br>
     * 〈功能详细〉
     *
     * @param request
     * @param response
     * @param model
     * @param activeid
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/copyActive.jspx")
    public String copyActive(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activeid){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        TScActiveDetail activeDetail=commonService.getEntity(TScActiveDetail.class, activeid);
        activeDetail.setId(0);
        commonService.saveOrUpdate(activeDetail);   
        //return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-jzlb");
        return "redirect:activeList.jspx?type=ckjz";
    }
    
    /**
     * 编辑功能
     * 功能描述: <br>
     * 〈功能详细〉
     *
     * @param request
     * @param response
     * @param model
     * @param activeid
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
    @RequestMapping(value="/active/updateActiveIndex.jspx")
    public String updateActive(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activeid ){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        if(StringUtils.isEmpty(activeid)){
            model.addAttribute("activeid", activeid);
            return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-addjz");
        }
       
        TScActiveDetail activeDetail=commonService.getEntity(TScActiveDetail.class, activeid);
        model.addAttribute("activeDetail", activeDetail);
        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-editjz");
    }
    
    
    @RequestMapping(value="/active/updateActive.jspx",method=RequestMethod.POST)
    public String updateActiveing(HttpServletRequest request,HttpServletResponse response,ModelMap model,
            Integer activeid,
            Integer id, 
            String  province    ,// 活动省份    varchar(11)
            String  city    ,// 活动城市    varchar(11)
            String  quxian  ,// 活动区县    varchar(11)
            String  lecturer1   ,// 主讲老师1   varchar(20)
            String  lecturer2   ,// 主讲老师2   varchar(11)
            String  address ,// 活动地址    varchar(100)
            String  active_time ,// 活动举办时间  datetime
            String  theme   ,// 活动主题内容  varchar(32)
            Integer  product_Id  ,// 产品id    int(11)
            Integer  free    ,// 活动收费情况  int(11)
            String  active_start_time   ,// 活动开始报名时间    datetime
            String  active_end_time ,// 活动结束报名时间    datetime
            String  qd_start_time   ,// 开始签到时间，活动前四小时   datetime
            String  qd_end_time ,// 结束签到时间  datetime
            String  host_department ,// 活动的主办部门 varchar(32)
            String  image   ,// 活动主题图片  varchar(100)
            String  lqzl_content    ,// 领取资料的内容 varchar(255)
            String  details ,// 活动详情    varchar(400)
            String  active_status   ,// 活动状态    varchar(20)
            String  lecturers   ,//     varchar(255)
            String  remark  ,//活动备注
            String  sfxybm  ,//是否需要报名
            Integer people,
            String  sfxyqd  //是否需要签到
            
             )
            {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        
        StringBuffer sb=new StringBuffer();
        //保存省份信息
        sb.append("UPDATE t_sc_active_detail SET ");
        
 
        if(!StringUtils.isEmpty(lecturer1)){
            sb.append(" lecturer1 = '"+lecturer1+"'," );
        }

        if(!StringUtils.isEmpty(lecturer2)){
            sb.append(" lecturer2 = '"+lecturer2+"'," );
        }

        if(!StringUtils.isEmpty(address)){
            sb.append(" address = '"+address+"'," );
        }
        if(!StringUtils.isEmpty(active_start_time)){
            sb.append(" active_time = '"+active_start_time+"'," );
            sb.append(" active_start_time = '"+active_start_time+"'," );
        }
        if(!StringUtils.isEmpty(theme)){
            sb.append("theme = '"+theme+"'," );
        }
        if(product_Id!=null){
            sb.append(" product_Id = '"+product_Id+"'," );
        }
        if(free!=null){
            sb.append(" free = '"+free+"'," );
        }
 
        if(!StringUtils.isEmpty( active_end_time)){
            sb.append(" active_end_time = '"+active_end_time+"'," );
        }
        if(!StringUtils.isEmpty(qd_start_time)){
            sb.append(" qd_start_time = '"+qd_start_time+"'," );
        }
        if(!StringUtils.isEmpty(qd_end_time)){
            sb.append(" qd_end_time = '"+qd_end_time+"'," );
        }
        if(!StringUtils.isEmpty(host_department)){
            sb.append("host_department = '"+host_department+"'," );
        }
        if(!StringUtils.isEmpty(image)){
            sb.append("image = '"+image+"'," );
        }
        if(!StringUtils.isEmpty(lqzl_content)){
            sb.append("lqzl_content = '"+lqzl_content+"'," );
        }
        if(!StringUtils.isEmpty(details)){
            sb.append("details = '"+details+"'," );
        }
        if(!StringUtils.isEmpty(active_status)){
            sb.append(" active_status = '"+active_status+"'," );
        }
        if(!StringUtils.isEmpty(sfxybm)){
            sb.append(" sfxybm = '"+sfxybm+"'," );
        }
        if(!StringUtils.isEmpty(sfxyqd)){
            sb.append(" sfxyqd = '"+sfxyqd+"'," );
        }    
        if(people!=null){
            sb.append(" people = '"+people+"'," );
        }else{
            sb.append(" people = '"+100+"'," );
        }
        
        String sql=sb.toString().substring(0, sb.length()-1);
        sql+=" WHERE id = "+id;
        commonService.executeSql(sql);        
          
        return "redirect:activeList.jspx?type=ckjz";
    }
      
    
    private TScActiveDetail createactiveDetail(
                                            String  pro_id,
                                            String  province,// 活动省份    varchar(11)1
                                            String  city    ,// 活动城市    varchar(11)2
                                            String  quxian  ,// 活动区县    varchar(11)3
                                            String  lecturer1   ,// 主讲老师1   varchar(20)4
                                            String  lecturer2   ,// 主讲老师2   varchar(11)5                                       
                                            String  address ,// 活动地址    varchar(100)6
                                            String  active_time ,// 活动举办时间  datetime7
                                            String  theme   ,// 活动主题内容  varchar(32)8
                                            Integer  product_Id  ,// 产品id    int(11)9
                                            Integer  free    ,// 活动收费情况  int(11)10
                                            String  active_start_time   ,// 活动开始报名时间    datetime  11
                                            String  active_end_time ,// 活动结束报名时间    datetime  12                                     
                                            String  host_department ,// 活动的主办部门 varchar(32) 13
                                            String  image   ,// 活动主题图片  varchar(100) 14
                                            String  lqzl_content    ,// 领取资料的内容 varchar(255) 15 
                                            String  details ,// 活动详情    varchar(400) 16 
                                            String  active_status   ,// 活动状态    varchar(20) 17 
                                            String  lecturers   ,// 活动状态    varchar(20) 18
                                            String  remark , //19
                                            Integer people,//讲座人数
                                            Integer activeId,
                                            String lecturer_picture1,//讲师图片1
                                            String lecturer_picture2,//讲师图片1
                                            Integer teacher1_id,
                                            Integer teacher2_id
                                            ) throws ParseException  //20  
            {
        TScActiveDetail activeDetail=new TScActiveDetail();
        
        activeDetail.setActiveId(activeId); //1
        
        if(!StringUtils.isEmpty(pro_id)){
            activeDetail.setProId(pro_id);//2
        }
        
        if(!StringUtils.isEmpty(province)){
            activeDetail.setProvince(province);//2
        }
        if(!StringUtils.isEmpty(city)){
            activeDetail.setCity(city);//3
        }
        if(!StringUtils.isEmpty(quxian)){
            activeDetail.setQuxian(quxian);//4
        }
        if(!StringUtils.isEmpty(lecturer1)){
            activeDetail.setLecturer1(lecturer1);//5
        }

        if(!StringUtils.isEmpty(lecturer2)){
            activeDetail.setLecturer2(lecturer2);//6
        }

        if(!StringUtils.isEmpty(address)){
            activeDetail.setAddress(address);//7
        }
        if(!StringUtils.isEmpty(active_start_time)){
            activeDetail.setActiveStartTime((Timestamp) sm.parse(active_start_time));//8
        }
        if(!StringUtils.isEmpty(theme)){
            activeDetail.setTheme(theme);///9
        }
        if(product_Id!=null){
            activeDetail.setProductId(product_Id);//10
        }
        if(free!=null){
            activeDetail.setFree(free);}
            else{
            activeDetail.setFree(0);//11
        }
        if(!StringUtils.isEmpty(active_start_time)){
            activeDetail.setActiveStartTime((Timestamp) sm.parse(active_start_time));//12
        }
        if(!StringUtils.isEmpty(active_end_time)){
            activeDetail.setActiveEndTime((Timestamp) sm.parse(active_end_time));//13
        }

        if(!StringUtils.isEmpty(host_department)){
            activeDetail.setHostDepartment(host_department);//14
        }
        if(!StringUtils.isEmpty(image)){
            activeDetail.setImage(image);//15
        }
        if(!StringUtils.isEmpty(lqzl_content)){
            activeDetail.setLqzlContent(lqzl_content);//16
        }
        if(!StringUtils.isEmpty(details)){
            activeDetail.setDetails(details);//17
        }
        if(!StringUtils.isEmpty(active_status)){
            activeDetail.setActiveStatus(active_status); //18
        }
        if(!StringUtils.isEmpty(lecturers)){
            activeDetail.setSignKind(lecturers);//19
        }    
        if(!StringUtils.isEmpty(remark)){
            activeDetail.setRemark(remark);//20
        }      
        if(people!=null){
            activeDetail.setPeople(people);
        }else{
            activeDetail.setPeople(100);
        }
        if(!StringUtils.isEmpty(lecturer_picture1)){
            activeDetail.setLecturerPicture1(lecturer_picture1);
        }  
        if(!StringUtils.isEmpty(lecturer_picture2)){
            activeDetail.setLecturerPicture2(lecturer_picture2);
        }
        if(teacher1_id!=null){
            activeDetail.setTeacher1Id(teacher1_id);
        }
        if(teacher2_id!=null){
            activeDetail.setTeacher2Id(teacher2_id);
        }        
        return activeDetail;
    }
    
    /**
     * 下载二维码功能
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
//     * @param province
//     * @param pageNo
//     * @param pageSize
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value=" " )
    public  ResponseEntity<byte[]> Xzewm(HttpServletRequest request,HttpServletResponse response,ModelMap model
        ,String str,String type,String activeid,Integer id) 
                throws IOException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        //需要的参数  logo地址、不存二维码路径、二维码链接、二维码保存路径、二维码图片名称     
        // String logopath="F:/code/logo.jpg"; //存放图片
        // String savepath="F:/code/erwm/"; //存放文件   
       String logopath="/mnt/service/tomcat6/webapps/zywy/u/cms/www/signpic/logo.jpg";  //存放图片
       String savepath="/mnt/service/tomcat6/webapps/zywy/u/cms/www/signpic/logo";  //存放文件       
        String qrUrl="";    
        String Pathc="";
        String imagename="";   
       //先清空文件夹中的内容
        File file=new File(savepath);
        File[] listfile=file.listFiles();
        if(listfile.length>0) {
            //存在文件
            String[] content = file.list();//取得当前目录下所有文件和文件夹  
            for(String name : content){  
                File temp = new File(savepath, name);
                temp.delete(); 
                System.out.print("文件夹已经清空");
                }  
        }
       //彩色二维码下载到浏览器
        
       if("bm".equals(type)){
           //输出报名二维码       
             qrUrl="http://new.51bzy.com/zywy/sign/findActive.jspx";
             Pathc=savepath+"/"+str+"bm.jpg";
             imagename=str+"bm.jpg";
             File Pathcc=new File(Pathc);                
             System.out.print("这是文件路径"+Pathcc);
             Colorewmxz.encode(qrUrl, 512, 512, logopath,Pathc);            
       }
       if("qd".equals(type)){
           //输出签到二维码                 
           qrUrl="http://new.51bzy.com/zywy/sign/signindex.jspx?activeId="+activeid;
           Pathc=savepath+"/"+str+"qd.jpg";
           imagename=str+"qd.jpg";
           File Pathcc=new File(Pathc);                
           System.out.print("这是文件路径"+Pathcc);
           Colorewmxz.encode(qrUrl, 512, 512, logopath,Pathc);
        }

      HttpHeaders header=new HttpHeaders();
      header.setContentDispositionFormData("attachment", new String(imagename.getBytes("utf-8"), "ISO8859-1"));
      header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      System.out.println("二维码生成成功");      
      File filenameimage=new File(Pathc);
      return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filenameimage),header,HttpStatus.CREATED);
    }
    
    //导出excel       
    @RequestMapping(value="/active/UserListexcel.jspx" ) 
    public  ResponseEntity<byte[]> UserListexcel(HttpServletRequest request,HttpServletResponse response,ModelMap model
        ,String province,String city,String active_start_time,String active_end_time,
        String activeName,String teachname,String activeid, Integer pageNo,Integer pageSize) throws IOException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        
        StringBuilder sb1 = new StringBuilder();
        sb1.append("SELECT a.id,b.province,b.city,a.active_id,b.theme,b.lecturer1 ,b.active_start_time ,a.name,a.telephone,a.school_name,a.classes ,a.class_rank ,a.major_type FROM t_sc_active_sign a  LEFT JOIN t_sc_active_detail  b ON a.active_id=b.active_Id WHERE 1=1");
       //判断文件是否存在存在就删除
        String savepath="F:/code/erwm";
        //String savepath="/usr/local/tomcat6/webapps/zywy/u/cms/www/signpic/logo";  //存放文件         
        File deletefile=new File(savepath+"/Userlist_"+activeid+".xls");
        System.out.print(deletefile);
        if(deletefile.exists()){
        System.out.println("The file exist");
        deletefile.delete();
        System.out.print("删除成功!");}
   else{ 
       System.out.println("The file is not exist");
       System.out.print("over");
   }                          
        String fileName="";
        if (!StringUtils.isEmpty(activeid)) {
                sb1.append(" AND a.active_id='" + activeid.toString() + "' "); 
                fileName="Userlist_"+activeid.toString().trim();} 
            else{
                fileName="Alllist";}                
                           
        List<Map<String, Object>> userList = commonService.findForJdbc(sb1.toString());        
        List<String>  headers=new ArrayList<String>();
        Map<String, Object> map1=userList.get(0);       
        for(Map.Entry entry:map1.entrySet()){
            headers.add(entry.getKey().toString());
        }
       // String savePath="/usr/local/tomcat6/webapps/zywy/u/cms/www/201612"; 
       String sheetname="名单"+activeid.toString();       
       String titlename="参加活动学生名单"+activeid.toString();
       //String fileName=new String();
//       String filepathname= ExcelUtil.createExcel(savepath, fileName, sheetname, titlename, headers, userList);
//           File file=new File(filepathname);
           HttpHeaders header=new HttpHeaders();
           String filenameexcel=fileName+".xls";
           header.setContentDispositionFormData("attachment", filenameexcel);
           header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
           System.out.print("excel导出成功");           
           String filepath=savepath+"/"+filenameexcel;
           File fileexcel=new File(filepath);          
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(fileexcel),header,HttpStatus.CREATED);
    }
   
    /**
     * 查询用户列表AdmissionTicket
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param province
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    //@RequestMapping(value="/active/UserList.jspx" )    
    @RequestMapping(value="/active/UserList.jspx" ,method={RequestMethod.POST,RequestMethod.GET}) 
    public String UserList(HttpServletRequest request,HttpServletResponse response,ModelMap model
        ,String province,String city,String active_start_time,String active_end_time,
        String activeName,String teachname,String active_id, Integer pageNo,Integer pageSize) throws IOException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        
        CmsUser user = CmsUtils.getUser(request);

       /*
        * 查询条件
        */ 
       //省份转换          
       StringBuilder sb = new StringBuilder();
       sb.append("SELECT a.id,b.province,b.pro_id,b.city,a.active_id,b.theme,b.lecturer1 ,b.active_start_time ,a.name,a.telephone,a.school_name,a.classes ,a.class_rank ,a.major_type FROM t_sc_active_sign a  LEFT JOIN t_sc_active_detail  b ON a.active_id=b.active_Id WHERE 1=1");
       if (!StringUtils.isEmpty(province) && !"none".equals(province)) {
           Map<String,Object> aa=Searcharea(province,null,null);
           String pro_id = aa.get("pro_id").toString();    
           sb.append(" and b.pro_id='"+pro_id+"'");
       }   
      if (!StringUtils.isEmpty(active_id)) {
           sb.append(" AND a.active_id='" + active_id.toString() + "' ");}                  
       //查询总条数
       String sql1="SELECT COUNT(*) FROM t_sc_active_sign";
       List<Map<String, Object>> list = commonService.findForJdbcParam(sb.toString(), cpn(pageNo), 20);
       Pagination pagination =new Pagination();
       pagination.setList(list);      
       pagination.setPageNo(cpn(pageNo));
       pagination.setPageSize(20);
       Long totalCount=commonService.getCountForJdbc(sql1);
       if(totalCount!=0){
           pagination.setTotalCount(new Long(totalCount).intValue());
       }      
       model.addAttribute("pagination", pagination);
       model.addAttribute("pageNo", cpn(pageNo));
       model.addAttribute("active_id", active_id);
       return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-jzmd");       
   }
    
  
    /**
     * 讲座名单列表中根据省份选择老师的ajax
     * 功能描述: <br>
     * 〈功能详细描述〉
     * @param request
     
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/findteacher.jspx" ,method={RequestMethod.POST,RequestMethod.GET})   
    public  void confirmList(HttpServletRequest request ,HttpServletResponse response,ModelMap model,
           String pro_code)
                    throws ParseException, IOException{   
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
    
        String teacsql="SELECT * FROM `t_sc_active_teacher` WHERE pro_id =(SELECT DISTINCT(pro_id) FROM t_xueji_sign WHERE pro_code='"+pro_code+"')";
        List<Map<String, Object>> teacherList = commonService.findForJdbc(teacsql.toString());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teacherList", teacherList);
        //传入json数据
        JsonWriteUtil.write(response, map);
    }
        
    
    /**
     * 讲师列表
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param province
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
      @RequestMapping(value="/active/teacherList.jspx" )   
/*    @RequestMapping(value="/active/activeList.jspx" ,method=RequestMethod.GET)*/   
    public String TeacherList(HttpServletRequest request,HttpServletResponse response,ModelMap model
        ,String province,String city,
        Integer pageNo,Integer pageSize) throws IOException{
       CmsSite site = CmsUtils.getSite(request);
       FrontUtils.frontData(request, model, site);
       /*
        * 查询条件
        */       
       Finder f = Finder.create("FROM  TScActiveTeacher bean WHERE 1=1");
       
       if (!StringUtils.isEmpty(province) && !"none".equals(province)) {
               Map<String,Object> aa=Searcharea(province,null,null);
               String pro_id = aa.get("pro_id").toString();    
               f.append(" and bean.pro_id='"+pro_id+"'");
           }
                

       f.append(" order by bean.id asc");
       Pagination pagination = commonService.findPager(f, cpn(pageNo), 20);
       model.addAttribute("pageNo", cpn(pageNo));
       model.addAttribute("pagination", pagination);
       return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-teacherlist");
       
   }
    
     //编辑讲师 
      @RequestMapping(value="/active/updateTeacher.jspx")
      public String updateTeacher(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id ){
          CmsSite site = CmsUtils.getSite(request);
          FrontUtils.frontData(request, model, site);
          TScActiveTeacher activeteacher=commonService.getEntity(TScActiveTeacher.class,id);
          model.addAttribute("activeteacher", activeteacher);
          return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-teacheredit");
      }
      
      //编辑讲师
      @RequestMapping(value="/active/updateTeacher.jspx",method=RequestMethod.POST)
      public String updateTeachers(HttpServletRequest request,HttpServletResponse response,ModelMap model, 
              String  province,
              Integer teacherid,
              Integer id,String pro_id,
              String image1,
              String name,
              String introduce0, String introduce1, String introduce2,String strintroduce4,
              @RequestParam("file") MultipartFile file    // 活动地址    varchar(100)                          
              ) throws ParseException{          
             CmsSite site = CmsUtils.getSite(request);
             FrontUtils.frontData(request, model, site);  
             
             StringBuffer sb=new StringBuffer();
             //保存省份信息
             sb.append("UPDATE t_sc_active_teacher SET ");
             if(!"none".equals(province)){
                 Map<String,Object> cc=Searcharea(province,null,null);
                 String pro_name = cc.get("pro_name").toString(); 
                 sb.append(" province = '"+pro_name+"'," );
             }
             if(!StringUtils.isEmpty(introduce0)){
                 sb.append(" introduce0 = '"+introduce0+"'," );
             }
             if(!StringUtils.isEmpty(introduce1)){
                 sb.append(" introduce1 = '"+introduce1+"'," );
             }
             if(!StringUtils.isEmpty(name)){
                 sb.append(" name = '"+name+"'," );
             }
             if(!StringUtils.isEmpty(introduce2)){
                 sb.append(" introduce2 = '"+introduce2+"'," );
             }
             if(!StringUtils.isEmpty(strintroduce4)){
                 sb.append(" strintroduce4 = '"+strintroduce4+"'," );
             }             
             String sql=sb.toString().substring(0, sb.length()-1);            
             sql+=" WHERE id = "+id;
             commonService.executeSql(sql.toString());    
             //编辑讲师图片 首先判断是否有图片上传 再把原有的图片删除 然后把新的图片存储到原有的位置
             
             //判断
             String filepath="";
             if(!file.isEmpty()){
                 System.out.print("图片存在");
                String webPath = request.getSession().getServletContext().getRealPath("");
                String savePath="/u/cms/www/201612/";                
                savePath=webPath.replace('\\', '/')+savePath;
                 
                 //String savePath="F:/code/erwm/";
                 System.out.println(savePath);
               filepath="/zywy/u/cms/www/201612/"+teacherid.toString()+".jpg";
                 //filepath=savePath+teacherid.toString()+".jpg";
                 String message = "";
                 try{
                     message= "文件上传成功！";            
                     file.transferTo(new File(savePath+teacherid.toString()+".jpg"));
                     String sqlimage="update t_sc_active_teacher SET image1='" +filepath+"' where id ="+id;
                     commonService.executeSql(sqlimage.toString());    
                 }catch (Exception e) {
                     message= "文件上传失败！";
                     e.printStackTrace();           
                 }
             }else{
                 System.out.print("没有上传图片");
             } 
             String sqlteacher="update t_sc_active_teacher set `introduce4` =`strintroduce4`  where `teacher_id` ="+teacherid;
             commonService.executeSql(sqlteacher);
             return "redirect:/active/teacherList.jspx";         
      }     
      
    /**
     * \
     * 
     * 功能描述: <br>
     * 新增讲师界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/addteacher.jspx")
    public String Addteacher(HttpServletRequest request,HttpServletResponse response,ModelMap model){
        
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        //需要修改
        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-addteacher");
    }  
        
    /**
     * 功能描述: <br>
     * 新增讲师界面
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ParseException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value="/active/addteacher.jspx",method=RequestMethod.POST)
    public String addteacher(HttpServletRequest request,HttpServletResponse response,ModelMap model,
            String  province    ,// 讲师省份  
            String  name    ,//  讲师姓名
            String  introduce0  ,// 讲师头衔
            String  introduce1,// 讲师介绍1
            String  introduce2,// 讲师介绍2
            Blob  strintroduce4   ,// 基本信息
            @RequestParam("file") MultipartFile file // 活动地址    varchar(100)          
            ) throws ParseException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        //省份id,名称
        Map<String, Object> map=commonService.findOneForJdbc("SELECT  pro_name,pro_id  FROM `t_xueji_sign` where pro_code='"+province+"'"+"limit 1");
        //确定省份id
        String  province_id=map.get("pro_id").toString();
        Integer  pro_id=Integer.parseInt(province_id);
        //确定省份名称
        String  province1=map.get("pro_name").toString();        
        Integer teacher_id=teacherId(pro_id);
        //String转为BLob
           
        //判断
        if(!file.isEmpty()){
            System.out.print("图片存在");
            String webPath = request.getSession().getServletContext().getRealPath("");
            String savePath="/u/cms/www/201612/";
            savePath=webPath.replace('\\', '/')+savePath;
            System.out.println(savePath);
            String filepath="/zywy/u/cms/www/201612/"+teacher_id.toString()+".jpg";
            TScActiveTeacher activeTeacher =creat_teacher(teacher_id, name, province1,pro_id, introduce0, introduce1,introduce2, strintroduce4,filepath);
            String message = "";
            try{
                message= "文件上传成功！";            
                file.transferTo(new File(savePath+teacher_id.toString()+".jpg"));
                commonService.saveOrUpdate(activeTeacher);
            }catch (Exception e) {
                message= "文件上传失败！";
                e.printStackTrace();           
            }
        }else{
            System.out.print("没有上传图片");
            String path1="/zywy/u/cms/www/201612/17000.jpg";
            TScActiveTeacher activeTeacher =creat_teacher(teacher_id, name, province1,pro_id, introduce0, introduce1,introduce2, strintroduce4,path1);
            commonService.saveOrUpdate(activeTeacher);
        }   
        String sql="update t_sc_active_teacher set `introduce4` =`strintroduce4`  where `teacher_id` ="+teacher_id;
        commonService.executeSql(sql);
        return "redirect:/active/teacherList.jspx";
    }
    
    //创建教师teacher_id
    public Integer teacherId(Integer pro_id){
        Integer teacherid=null;
       //String yearoldTWo=sm.format(new Date()).substring(2, 4);
        Map<String, Object> map=commonService.findOneForJdbc("SELECT MAX(teacher_id) as teacher_id FROM t_sc_active_teacher WHERE pro_id ='"+pro_id+"'");
        if(map.get("teacher_id")!=null){
           {
               teacherid=Integer.parseInt(map.get("teacher_id").toString())+1;            
           }
        }else{
               teacherid=Integer.parseInt(pro_id.toString()+"001");
        }
        return teacherid;
    }
      
    public TScActiveTeacher creat_teacher(
            //传入方法的参数
            Integer teacher_id,//教师编号
            String name,
            String province,
            Integer pro_id,
            String introduce3, //讲师头衔
            String introduce1,
            String introduce2,
            Blob strintroduce4,//基本信息
            String file //讲师详情介绍
            )throws ParseException{
        TScActiveTeacher teacherdetail=new TScActiveTeacher();
        teacherdetail.setTeacherId(teacher_id);
        
        if(!StringUtils.isEmpty(name)){
            teacherdetail.setName(name);
        }
        if(!StringUtils.isEmpty(province)){
            teacherdetail.setProvince(province);
        }
        if(pro_id!=null){
            teacherdetail.setProId(pro_id);
        }
        if(!StringUtils.isEmpty(introduce1)){
            teacherdetail.setIntroduce1(introduce1);
        }
        if(!StringUtils.isEmpty(introduce2)){
            teacherdetail.setIntroduce2(introduce2);
        }
        if(!StringUtils.isEmpty(introduce3)){
            teacherdetail.setIntroduce3(introduce2);
        }       
        if(!StringUtils.isEmpty(strintroduce4)){
            teacherdetail.setIntroduce4(strintroduce4);
        }
        if(!StringUtils.isEmpty(file)){
            teacherdetail.setImage1(file);
        }                 
        return teacherdetail;
    }
    //删除老师
    @RequestMapping(value="/active/deleteteacher.jspx",method={RequestMethod.POST,RequestMethod.GET}) 
    public String delectteacher(HttpServletRequest request,HttpServletResponse response,ModelMap model,String activeid) throws IOException{
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> map=new HashMap<String, Object>();
        String []activeids=activeid.split(",");
        for (String string : activeids) {
            TScActiveTeacher admissionTicketlist=commonService.getEntity(TScActiveTeacher.class, Integer.parseInt(string));
            if (admissionTicketlist!=null ) {
                String sql="DELETE FROM t_sc_active_teacher WHERE id = '"+string+"'";
                commonService.executeSql(sql);
                map.put("success", true);
                map.put("msg", "讲师信息删除成功!");
               
            }else{
                map.put("success", false);
                map.put("msg", "亲,讲师信息已经删除!");
            }
        }        
        JsonWriteUtil.write(map);     
        return "redirect:/active/teacherList.jspx"; 
         
    }
    
    /**
     * 复制讲师信息
     * 功能描述: <br>
     * 〈功能详细描述〉
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
    
    @RequestMapping(value="/active/copyTeacher.jspx")
    public String copyTeacher(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        
        TScActiveTeacher activeteacher=commonService.getEntity(TScActiveTeacher.class, id);
     
        activeteacher.setId(0);
        commonService.saveOrUpdate(activeteacher);   
        //return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-jzlb");
        return "redirect:/active/teacherList.jspx";
    }
    
    
    /**
     * 确认领取资料
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    
    @RequestMapping(value="/active/confirmlist.jspx" ,method={RequestMethod.POST,RequestMethod.GET})   
    public String confirmList(HttpServletRequest request ,HttpServletResponse response,ModelMap model,
            Integer id, String name ,String telephone,String pro,String city , String school_name, 
            String active_id,
            Integer pageNo,Integer pageSize)
                    throws ParseException{   
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
    StringBuilder sql2 = new StringBuilder();
    sql2.append("SELECT id,active_id,name,telephone,pro,city,school_name,classes,major_type,ruchangquan_type,qiandao_type,lingquziliao,lzl_time ,info_pay_success FROM t_sc_active_sign  where 1=1");
    String sql3="SELECT COUNT(*) FROM t_sc_active_sign";
    //判断条件
    if (!StringUtils.isEmpty(telephone)) {
        sql2.append(" AND telephone='" + telephone.toString() + "' ");}
        
   if (!StringUtils.isEmpty(active_id)) {
        sql2.append(" AND active_id='" + active_id.toString() + "' ");}
        
    List<Map<String, Object>> list = commonService.findForJdbcParam(sql2.toString(), cpn(pageNo), 20);
    for (int i = 0; i < list.size(); i++) {
        String sql="SELECT * FROM t_sc_active_sign where  active_Id="+list.get(i).get("active_id")+" and ruchangquan_type=1 and ruchangquan_code  in (SELECT cft_seq FROM  t_s_online_order where  pay_state=1) and telephone ="+list.get(i).get("telephone");
        Map<String, Object> ffMap=commonService.findOneForJdbc(sql);
        if(ffMap!=null){
            list.get(i).put("isfufei", "付费用户");
            list.get(i).put("ispay", "1");
        }else{
            list.get(i).put("isfufei", "免费用户");
            list.get(i).put("ispay", "0");
        }
    }
   
    Pagination pagination =new Pagination();
    pagination.setList(list);      
    pagination.setPageNo(cpn(pageNo));
    pagination.setPageSize(20);
    Long totalCount=commonService.getCountForJdbc(sql3);
    if(totalCount!=0){
        pagination.setTotalCount(new Long(totalCount).intValue());
    }
    
    model.addAttribute("pagination", pagination);
    model.addAttribute("pageNo", cpn(pageNo));
    //model.addAttribute("total", totalCount);
    model.addAttribute("active_id", active_id);
    return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhdManage-qrlqzl");       
}
    
    
  
  //按照条件查找并点击确认领取 
    @RequestMapping(value="active/confirm.jspx",method=RequestMethod.GET)
    public String confirm(HttpServletRequest request ,HttpServletResponse response,ModelMap model,
            Integer id,String ispay,Integer pageNo  ,String active_id)
                    throws ParseException{   
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);            
        Date now = new Date(); 
        String t = dateFormat.format( now ); 
        
        String sql4=" UPDATE t_sc_active_sign SET lingquziliao='已经领取', lzl_time='"+ t + "' ";
        if("1".equals(ispay)){
            sql4+=",info_pay_success='已退款'";           
        }
        sql4+=" WHERE id ="+ id;
                
              
        commonService.executeSql(sql4);  
    return "redirect:confirmlist.jspx?pageNo="+pageNo+"&active_id="+active_id;
    
}
        
    //创建活动id
    public Integer createactiveId(String province){
        Integer activeid=null;
        String yearoldTWo=sm.format(new Date()).substring(2, 4);
        Map<String, Object> map=commonService.findOneForJdbc("SELECT MAX(active_id) as active_id FROM t_sc_active_detail WHERE Province ='"+province+"'");
        if(map.get("active_id")!=null){
            if(yearoldTWo.equals(map.get("active_id").toString().subSequence(0, 2))){
                activeid=Integer.parseInt(map.get("active_id").toString())+1;
            }else{
                activeid=Integer.parseInt(yearoldTWo+map.get("active_id").toString().substring(2, 4)+"001");
            }
        }else{
            Map<String, Object> promap=commonService.findOneForJdbc("SELECT pro_id FROM t_xueji_sign WHERE pro_name='"+province+"' LIMIT 0,1");
            activeid=Integer.parseInt(yearoldTWo+promap.get("pro_id").toString()+"001");
        }
        return activeid;
    }
    
     //查询省市区名称
    public Map<String,Object> Searcharea(String  pro_code ,String city_code,String quxian_code){   
        StringBuffer sql1=new StringBuffer();
        sql1.append("select ");
        StringBuffer sql2=new StringBuffer();
        sql2.append(" where 1=1  ");
        if(!StringUtils.isEmpty(pro_code)){
            sql1.append("pro_name,pro_id");
            sql2.append(" and pro_code="+pro_code);
        }   
        
        if(!StringUtils.isEmpty(city_code) && !"none".equals(city_code)){
            sql1.append(", city_name");
            sql2.append(" and city_code="+city_code);
        }
        
        if(!StringUtils.isEmpty(quxian_code) && quxian_code.length()>5){
            sql1.append(", quxian_name");
            sql2.append(" and quxian_code="+quxian_code);
        }
        
        sql1.append("  from t_xueji_sign  ");
        sql2.append(" limit 1");
        String sql3=sql1.toString().substring(0, sql1.length())+sql2.toString();
        Map<String, Object> area = commonService.findOneForJdbc(sql3);
        return area;
    }
      
    @Autowired
    private CommonSvc commonService;
    
    
    
}
