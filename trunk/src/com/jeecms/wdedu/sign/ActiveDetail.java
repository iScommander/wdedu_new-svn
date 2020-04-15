package com.jeecms.wdedu.sign;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="active_detail")
public class ActiveDetail
{

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(name="active_Id")
  private Integer active_Id;

  @Column(name="province")
  private String province;
  
  @Column(name="pro_id")
  private String pro_id;

  @Column(name="city")
  private String city;

  @Column(name="quxian")
  private String quxian;

  @Column(name="lecturer1")
  private String lecturer1;
  
  @Column(name="teacher1_id")
  private Integer teacher1_id;

  @Column(name="lecturer_picture1")
  private String lecturer_picture1;

  @Column(name="lecturer_expert1")
  private String lecturer_expert1;

  @Column(name="lecturer2")
  private String lecturer2;
  
  @Column(name="teacher2_id")
  private Integer teacher2_id;
  
  @Column(name="lecturer_picture2")
  private String lecturer_picture2;

  @Column(name="lecturer_expert2")
  private String lecturer_expert2;

  @Column(name="address")
  private String address;

  @Column(name="active_time")
  private Date active_time;

  @Column(name="theme")
  private String theme;

  @Column(name="product_Id")
  private Integer product_Id;

  @Column(name="free")
  private Integer free;

  @Column(name="active_start_time")
  private Date active_start_time;

  @Column(name="active_end_time")
  private Date active_end_time;

  @Column(name="qd_start_time")
  private Date qd_start_time;

  @Column(name="qd_end_time")
  private Date qd_end_time;

  @Column(name="host_department")
  private String host_department;

  @Column(name="image")
  private String image;

  @Column(name="lqzl_content")
  private String lqzl_content;

  @Column(name="details")
  private String details;

  @Column(name="active_status")
  private String active_status;

  @Column(name="lecturers")
  private String lecturers;

  @Column(name="remark")
  private String remark;

  @Column(name="people")
  private Integer people;

  @Column(name="sfxybm")
  private Integer sfxybm;

  @Column(name="sfxyqd")
  private Integer sfxyqd;
  
  
  
  
  
  /**
 * @return the teacher1_id
 */
public Integer getTeacher1_id() {
    return teacher1_id;
}

/**
 * @param teacher1_id the teacher1_id to set
 */
public void setTeacher1_id(Integer teacher1_id) {
    this.teacher1_id = teacher1_id;
}

/**
 * @return the teacher2_id
 */
public Integer getTeacher2_id() {
    return teacher2_id;
}

/**
 * @param teacher2_id the teacher2_id to set
 */
public void setTeacher2_id(Integer teacher2_id) {
    this.teacher2_id = teacher2_id;
}

/**
 * @return the info_pay_success
 */
public String getInfo_pay_success() {
    return info_pay_success;
}

/**
 * @param info_pay_success the info_pay_success to set
 */
public void setInfo_pay_success(String info_pay_success) {
    this.info_pay_success = info_pay_success;
}

@Column(name="info_pay_success")
  private String info_pay_success;


  public Integer getPeople()
  {
    return this.people;
  }

  public void setPeople(Integer people)
  {
    this.people = people;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Integer getActive_Id()
  {
    return this.active_Id;
  }

  public void setActive_Id(Integer active_Id)
  {
    this.active_Id = active_Id;
  }

  public String getProvince()
  {
    return this.province;
  }

  public void setProvince(String province)
  {
    this.province = province;
  }

  /**
 * @return the pro_id
 */
public String getPro_id() {
    return pro_id;
}

/**
 * @param pro_id the pro_id to set
 */
public void setPro_id(String pro_id) {
    this.pro_id = pro_id;
}

/**
 * @return the sfxybm
 */
public Integer getSfxybm() {
    return sfxybm;
}

/**
 * @param sfxybm the sfxybm to set
 */
public void setSfxybm(Integer sfxybm) {
    this.sfxybm = sfxybm;
}

/**
 * @return the sfxyqd
 */
public Integer getSfxyqd() {
    return sfxyqd;
}

/**
 * @param sfxyqd the sfxyqd to set
 */
public void setSfxyqd(Integer sfxyqd) {
    this.sfxyqd = sfxyqd;
}

public String getCity()
  {
    return this.city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getQuxian()
  {
    return this.quxian;
  }

  public void setQuxian(String quxian)
  {
    this.quxian = quxian;
  }

  public String getLecturer1()
  {
    return this.lecturer1;
  }

  public void setLecturer1(String lecturer1)
  {
    this.lecturer1 = lecturer1;
  }

  public String getLecturer_picture1()
  {
    return this.lecturer_picture1;
  }

  public void setLecturer_picture1(String lecturer_picture1)
  {
    this.lecturer_picture1 = lecturer_picture1;
  }

  public String getLecturer_expert1()
  {
    return this.lecturer_expert1;
  }

  public void setLecturer_expert1(String lecturer_expert1)
  {
    this.lecturer_expert1 = lecturer_expert1;
  }

  public String getLecturer2()
  {
    return this.lecturer2;
  }

  public void setLecturer2(String lecturer2)
  {
    this.lecturer2 = lecturer2;
  }

  public String getLecturer_picture2()
  {
    return this.lecturer_picture2;
  }

  public void setLecturer_picture2(String lecturer_picture2)
  {
    this.lecturer_picture2 = lecturer_picture2;
  }

  public String getLecturer_expert2()
  {
    return this.lecturer_expert2;
  }

  public void setLecturer_expert2(String lecturer_expert2)
  {
    this.lecturer_expert2 = lecturer_expert2;
  }

  public String getAddress()
  {
    return this.address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public Date getActive_time()
  {
    return this.active_time;
  }

  public void setActive_time(Date active_time)
  {
    this.active_time = active_time;
  }

  public String getTheme()
  {
    return this.theme;
  }

  public void setTheme(String theme)
  {
    this.theme = theme;
  }

  public Integer getProduct_Id()
  {
    return this.product_Id;
  }

  public void setProduct_Id(Integer product_Id)
  {
    this.product_Id = product_Id;
  }

  public Integer getFree()
  {
    return this.free;
  }

  public void setFree(Integer free)
  {
    this.free = free;
  }

  public Date getActive_start_time()
  {
    return this.active_start_time;
  }

  public void setActive_start_time(Date active_start_time)
  {
    this.active_start_time = active_start_time;
  }

  public Date getActive_end_time()
  {
    return this.active_end_time;
  }

  public void setActive_end_time(Date active_end_time)
  {
    this.active_end_time = active_end_time;
  }

  public Date getQd_start_time()
  {
    return this.qd_start_time;
  }

  public void setQd_start_time(Date qd_start_time)
  {
    this.qd_start_time = qd_start_time;
  }

  public Date getQd_end_time()
  {
    return this.qd_end_time;
  }

  public void setQd_end_time(Date qd_end_time)
  {
    this.qd_end_time = qd_end_time;
  }

  public String getHost_department()
  {
    return this.host_department;
  }

  public void setHost_department(String host_department)
  {
    this.host_department = host_department;
  }

  public String getImage()
  {
    return this.image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

  public String getLqzl_content()
  {
    return this.lqzl_content;
  }

  public void setLqzl_content(String lqzl_content)
  {
    this.lqzl_content = lqzl_content;
  }

  public String getDetails()
  {
    return this.details;
  }

  public void setDetails(String details)
  {
    this.details = details;
  }

  public String getActive_status()
  {
    return this.active_status;
  }

  public void setActive_status(String active_status)
  {
    this.active_status = active_status;
  }

  public String getLecturers()
  {
    return this.lecturers;
  }

  public void setLecturers(String lecturers)
  {
    this.lecturers = lecturers;
  }

  public String getRemark()
  {
    return this.remark;
  }

  public void setRemark(String remark)
  {
    this.remark = remark;
  }
}