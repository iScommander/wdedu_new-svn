package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_cee_enroll_univ_list_choose", schema = "wodecareer", catalog = "")
public class TCeeEnrollUnivListChoose {
    private Integer id;
    private Integer applicationId;
    private String pskSet;
    private Integer univListId;
    private Integer year;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private String univId;
    private String univCode;
    private String univName;
    private Integer univLowScore1;
    private Integer univRankLowScore1;
    private Integer univLowScore2;
    private Integer univRankLowScore2;
    private Integer univLowScore3;
    private Integer univRankLowScore3;
    private String majorId;
    private String majorCode;
    private String majorName;
    private Integer majorListId;
    private Integer lowScore1;
    private Integer rankLowScore1;
    private Integer lowScore2;
    private Integer rankLowScore2;
    private Integer lowScore3;
    private Integer rankLowScore3;
    private Integer chooseFlag;
    private Integer univXh;
    private Integer majorXh;
    private Integer pskSetValue;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "psk_set")
    public String getPskSet() {
        return pskSet;
    }

    public void setPskSet(String pskSet) {
        this.pskSet = pskSet;
    }

    @Basic
    @Column(name = "univ_list_id")
    public Integer getUnivListId() {
        return univListId;
    }

    public void setUnivListId(Integer univListId) {
        this.univListId = univListId;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "major_type_id")
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "batch_id")
    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "univ_id")
    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univ_code")
    public String getUnivCode() {
        return univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    @Basic
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univ_low_score_1")
    public Integer getUnivLowScore1() {
        return univLowScore1;
    }

    public void setUnivLowScore1(Integer univLowScore1) {
        this.univLowScore1 = univLowScore1;
    }

    @Basic
    @Column(name = "univ_rank_low_score_1")
    public Integer getUnivRankLowScore1() {
        return univRankLowScore1;
    }

    public void setUnivRankLowScore1(Integer univRankLowScore1) {
        this.univRankLowScore1 = univRankLowScore1;
    }

    @Basic
    @Column(name = "univ_low_score_2")
    public Integer getUnivLowScore2() {
        return univLowScore2;
    }

    public void setUnivLowScore2(Integer univLowScore2) {
        this.univLowScore2 = univLowScore2;
    }

    @Basic
    @Column(name = "univ_rank_low_score_2")
    public Integer getUnivRankLowScore2() {
        return univRankLowScore2;
    }

    public void setUnivRankLowScore2(Integer univRankLowScore2) {
        this.univRankLowScore2 = univRankLowScore2;
    }

    @Basic
    @Column(name = "univ_low_score_3")
    public Integer getUnivLowScore3() {
        return univLowScore3;
    }

    public void setUnivLowScore3(Integer univLowScore3) {
        this.univLowScore3 = univLowScore3;
    }

    @Basic
    @Column(name = "univ_rank_low_score_3")
    public Integer getUnivRankLowScore3() {
        return univRankLowScore3;
    }

    public void setUnivRankLowScore3(Integer univRankLowScore3) {
        this.univRankLowScore3 = univRankLowScore3;
    }

    @Basic
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "major_code")
    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    @Basic
    @Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "major_list_id")
    public Integer getMajorListId() {
        return majorListId;
    }

    public void setMajorListId(Integer majorListId) {
        this.majorListId = majorListId;
    }

    @Basic
    @Column(name = "low_score_1")
    public Integer getLowScore1() {
        return lowScore1;
    }

    public void setLowScore1(Integer lowScore1) {
        this.lowScore1 = lowScore1;
    }

    @Basic
    @Column(name = "rank_low_score_1")
    public Integer getRankLowScore1() {
        return rankLowScore1;
    }

    public void setRankLowScore1(Integer rankLowScore1) {
        this.rankLowScore1 = rankLowScore1;
    }

    @Basic
    @Column(name = "low_score_2")
    public Integer getLowScore2() {
        return lowScore2;
    }

    public void setLowScore2(Integer lowScore2) {
        this.lowScore2 = lowScore2;
    }

    @Basic
    @Column(name = "rank_low_score_2")
    public Integer getRankLowScore2() {
        return rankLowScore2;
    }

    public void setRankLowScore2(Integer rankLowScore2) {
        this.rankLowScore2 = rankLowScore2;
    }

    @Basic
    @Column(name = "low_score_3")
    public Integer getLowScore3() {
        return lowScore3;
    }

    public void setLowScore3(Integer lowScore3) {
        this.lowScore3 = lowScore3;
    }

    @Basic
    @Column(name = "rank_low_score_3")
    public Integer getRankLowScore3() {
        return rankLowScore3;
    }

    public void setRankLowScore3(Integer rankLowScore3) {
        this.rankLowScore3 = rankLowScore3;
    }

    @Basic
    @Column(name = "choose_flag")
    public Integer getChooseFlag() {
        return chooseFlag;
    }

    public void setChooseFlag(Integer chooseFlag) {
        this.chooseFlag = chooseFlag;
    }

    @Basic
    @Column(name = "univ_xh")
    public Integer getUnivXh() {
        return univXh;
    }

    public void setUnivXh(Integer univXh) {
        this.univXh = univXh;
    }

    @Basic
    @Column(name = "major_xh")
    public Integer getMajorXh() {
        return majorXh;
    }

    public void setMajorXh(Integer majorXh) {
        this.majorXh = majorXh;
    }

    @Basic
    @Column(name = "psk_set_value")
    public Integer getPskSetValue() {
        return pskSetValue;
    }

    public void setPskSetValue(Integer pskSetValue) {
        this.pskSetValue = pskSetValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCeeEnrollUnivListChoose that = (TCeeEnrollUnivListChoose) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
            return false;
        if (pskSet != null ? !pskSet.equals(that.pskSet) : that.pskSet != null) return false;
        if (univListId != null ? !univListId.equals(that.univListId) : that.univListId != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (majorTypeId != null ? !majorTypeId.equals(that.majorTypeId) : that.majorTypeId != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (univId != null ? !univId.equals(that.univId) : that.univId != null) return false;
        if (univCode != null ? !univCode.equals(that.univCode) : that.univCode != null) return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (univLowScore1 != null ? !univLowScore1.equals(that.univLowScore1) : that.univLowScore1 != null)
            return false;
        if (univRankLowScore1 != null ? !univRankLowScore1.equals(that.univRankLowScore1) : that.univRankLowScore1 != null)
            return false;
        if (univLowScore2 != null ? !univLowScore2.equals(that.univLowScore2) : that.univLowScore2 != null)
            return false;
        if (univRankLowScore2 != null ? !univRankLowScore2.equals(that.univRankLowScore2) : that.univRankLowScore2 != null)
            return false;
        if (univLowScore3 != null ? !univLowScore3.equals(that.univLowScore3) : that.univLowScore3 != null)
            return false;
        if (univRankLowScore3 != null ? !univRankLowScore3.equals(that.univRankLowScore3) : that.univRankLowScore3 != null)
            return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;
        if (majorCode != null ? !majorCode.equals(that.majorCode) : that.majorCode != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (majorListId != null ? !majorListId.equals(that.majorListId) : that.majorListId != null) return false;
        if (lowScore1 != null ? !lowScore1.equals(that.lowScore1) : that.lowScore1 != null) return false;
        if (rankLowScore1 != null ? !rankLowScore1.equals(that.rankLowScore1) : that.rankLowScore1 != null)
            return false;
        if (lowScore2 != null ? !lowScore2.equals(that.lowScore2) : that.lowScore2 != null) return false;
        if (rankLowScore2 != null ? !rankLowScore2.equals(that.rankLowScore2) : that.rankLowScore2 != null)
            return false;
        if (lowScore3 != null ? !lowScore3.equals(that.lowScore3) : that.lowScore3 != null) return false;
        if (rankLowScore3 != null ? !rankLowScore3.equals(that.rankLowScore3) : that.rankLowScore3 != null)
            return false;
        if (chooseFlag != null ? !chooseFlag.equals(that.chooseFlag) : that.chooseFlag != null) return false;
        if (univXh != null ? !univXh.equals(that.univXh) : that.univXh != null) return false;
        if (majorXh != null ? !majorXh.equals(that.majorXh) : that.majorXh != null) return false;
        if (pskSetValue != null ? !pskSetValue.equals(that.pskSetValue) : that.pskSetValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (applicationId != null ? applicationId.hashCode() : 0);
        result = 31 * result + (pskSet != null ? pskSet.hashCode() : 0);
        result = 31 * result + (univListId != null ? univListId.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (majorTypeId != null ? majorTypeId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (univId != null ? univId.hashCode() : 0);
        result = 31 * result + (univCode != null ? univCode.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (univLowScore1 != null ? univLowScore1.hashCode() : 0);
        result = 31 * result + (univRankLowScore1 != null ? univRankLowScore1.hashCode() : 0);
        result = 31 * result + (univLowScore2 != null ? univLowScore2.hashCode() : 0);
        result = 31 * result + (univRankLowScore2 != null ? univRankLowScore2.hashCode() : 0);
        result = 31 * result + (univLowScore3 != null ? univLowScore3.hashCode() : 0);
        result = 31 * result + (univRankLowScore3 != null ? univRankLowScore3.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorCode != null ? majorCode.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorListId != null ? majorListId.hashCode() : 0);
        result = 31 * result + (lowScore1 != null ? lowScore1.hashCode() : 0);
        result = 31 * result + (rankLowScore1 != null ? rankLowScore1.hashCode() : 0);
        result = 31 * result + (lowScore2 != null ? lowScore2.hashCode() : 0);
        result = 31 * result + (rankLowScore2 != null ? rankLowScore2.hashCode() : 0);
        result = 31 * result + (lowScore3 != null ? lowScore3.hashCode() : 0);
        result = 31 * result + (rankLowScore3 != null ? rankLowScore3.hashCode() : 0);
        result = 31 * result + (chooseFlag != null ? chooseFlag.hashCode() : 0);
        result = 31 * result + (univXh != null ? univXh.hashCode() : 0);
        result = 31 * result + (majorXh != null ? majorXh.hashCode() : 0);
        result = 31 * result + (pskSetValue != null ? pskSetValue.hashCode() : 0);
        return result;
    }

    public void setLocal(TCeeEnrollUnivListUser tCeeEnrollUnivListUser) {
        this.applicationId = tCeeEnrollUnivListUser.getApplicationId();
        this.pskSet = tCeeEnrollUnivListUser.getPskSet();
        this.univListId = tCeeEnrollUnivListUser.getUnivListId();
        this.year = tCeeEnrollUnivListUser.getYear();
        this.provinceId = tCeeEnrollUnivListUser.getProvinceId();
        this.majorTypeId = tCeeEnrollUnivListUser.getMajorTypeId();
        this.batchId = tCeeEnrollUnivListUser.getBatchId();
        this.univId = tCeeEnrollUnivListUser.getUnivId();
        this.univCode = tCeeEnrollUnivListUser.getUnivCode();
        this.univName = tCeeEnrollUnivListUser.getUnivName();
        this.univLowScore1 = tCeeEnrollUnivListUser.getUnivLowScore1();
        this.univRankLowScore1 = tCeeEnrollUnivListUser.getUnivRankLowScore1();
        this.univLowScore2 = tCeeEnrollUnivListUser.getUnivLowScore2();
        this.univRankLowScore2 = tCeeEnrollUnivListUser.getUnivRankLowScore2();
        this.univLowScore3 = tCeeEnrollUnivListUser.getUnivLowScore3();
        this.univRankLowScore3 = tCeeEnrollUnivListUser.getUnivRankLowScore3();
        this.majorId = tCeeEnrollUnivListUser.getMajorId();
        this.majorCode = tCeeEnrollUnivListUser.getMajorCode();
        this.majorName = tCeeEnrollUnivListUser.getMajorName();
        this.majorListId = tCeeEnrollUnivListUser.getMajorListId();
        this.lowScore1 = tCeeEnrollUnivListUser.getLowScore1();
        this.rankLowScore1 = tCeeEnrollUnivListUser.getRankLowScore1();
        this.lowScore2 = tCeeEnrollUnivListUser.getLowScore2();
        this.rankLowScore2 = tCeeEnrollUnivListUser.getRankLowScore2();
        this.lowScore3 = tCeeEnrollUnivListUser.getLowScore3();
        this.rankLowScore3 = tCeeEnrollUnivListUser.getRankLowScore3();
        this.chooseFlag = tCeeEnrollUnivListUser.getChooseFlag();
        this.univXh = tCeeEnrollUnivListUser.getUnivXh();
        this.majorXh = tCeeEnrollUnivListUser.getMajorXh();
        this.pskSetValue = tCeeEnrollUnivListUser.getPskSetValue();
    }
}
