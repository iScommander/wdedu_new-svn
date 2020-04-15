package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_cee_enroll_history", schema = "wodecareer", catalog = "")
public class TCeeEnrollHistory {
    private int id;
    private Integer year;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private Integer batchIdOld;
    private Integer historyType;
    private String relationId;
    private String univId;
    private String univCode;
    private String univName;
    private String univAttr;
    private String majorId;
    private String majorId2;
    private String majorCode;
    private String majorName;
    private Integer historyPlaned;
    private Integer historyEnrolled;
    private Integer lowScore;
    private Integer averageScore;
    private Integer highScore;
    private Integer submitPercent;
    private Integer oneVolunteersOnline;
    private Integer oneVolunteersSubmitNum;
    private Integer oneVolunteersSubmitScore;
    private Integer oneVolunteersSubmitRank;
    private Integer rankHighScore;
    private Integer rankLowScore;
    private Integer rankAverageScore;
    private Integer segScoreHigh;
    private Integer segScoreLow;
    private Integer segScoreRange;
    private Integer signNum;
    private Integer reachNum;
    private Integer historyEnrolledAf;
    private Integer addNum;
    private Integer firstMajorNum;
    private Integer firstCeeNum;
    private BigDecimal enrollRate;
    private Integer lvl1;
    private Integer lvl2;
    private Integer lvl3;
    private Integer lvl4;
    private Integer lvl5;
    private Integer lvl6;
    private Integer lvl7;
    private Integer lvl8;
    private Integer lvl9;
    private Integer lvl10;
    private Integer lvl11;
    private Integer lvl12;
    private Integer lvl13;
    private Boolean iszhongdian;
    private Boolean isdalei;
    private Boolean isteshe;
    private Integer plan;
    private Integer scoreHigh;
    private Integer scoreLow;
    private Integer scoreRange;
    private Integer electiveSubjectsNum;
    private Integer electiveSubjects;
    private String electiveSubjectsLevel;
    private String pxStrscore;
    private Integer pxZonghe;
    private Integer pxYuwen;
    private Integer pxShuxue;
    private Integer pxWaiyu;
    private Integer ifMerge;
    private String remark;
    private String oldBatchName;
    private Integer gap;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "batch_id_old")
    public Integer getBatchIdOld() {
        return batchIdOld;
    }

    public void setBatchIdOld(Integer batchIdOld) {
        this.batchIdOld = batchIdOld;
    }

    @Basic
    @Column(name = "history_type")
    public Integer getHistoryType() {
        return historyType;
    }

    public void setHistoryType(Integer historyType) {
        this.historyType = historyType;
    }

    @Basic
    @Column(name = "relation_id")
    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
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
    @Column(name = "univ_attr")
    public String getUnivAttr() {
        return univAttr;
    }

    public void setUnivAttr(String univAttr) {
        this.univAttr = univAttr;
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
    @Column(name = "major_id2")
    public String getMajorId2() {
        return majorId2;
    }

    public void setMajorId2(String majorId2) {
        this.majorId2 = majorId2;
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
    @Column(name = "history_planed")
    public Integer getHistoryPlaned() {
        return historyPlaned;
    }

    public void setHistoryPlaned(Integer historyPlaned) {
        this.historyPlaned = historyPlaned;
    }

    @Basic
    @Column(name = "history_enrolled")
    public Integer getHistoryEnrolled() {
        return historyEnrolled;
    }

    public void setHistoryEnrolled(Integer historyEnrolled) {
        this.historyEnrolled = historyEnrolled;
    }

    @Basic
    @Column(name = "low_score")
    public Integer getLowScore() {
        return lowScore;
    }

    public void setLowScore(Integer lowScore) {
        this.lowScore = lowScore;
    }

    @Basic
    @Column(name = "average_score")
    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    @Basic
    @Column(name = "high_score")
    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    @Basic
    @Column(name = "submit_percent")
    public Integer getSubmitPercent() {
        return submitPercent;
    }

    public void setSubmitPercent(Integer submitPercent) {
        this.submitPercent = submitPercent;
    }

    @Basic
    @Column(name = "oneVolunteers_online")
    public Integer getOneVolunteersOnline() {
        return oneVolunteersOnline;
    }

    public void setOneVolunteersOnline(Integer oneVolunteersOnline) {
        this.oneVolunteersOnline = oneVolunteersOnline;
    }

    @Basic
    @Column(name = "oneVolunteers_submit_num")
    public Integer getOneVolunteersSubmitNum() {
        return oneVolunteersSubmitNum;
    }

    public void setOneVolunteersSubmitNum(Integer oneVolunteersSubmitNum) {
        this.oneVolunteersSubmitNum = oneVolunteersSubmitNum;
    }

    @Basic
    @Column(name = "oneVolunteers_submit_score")
    public Integer getOneVolunteersSubmitScore() {
        return oneVolunteersSubmitScore;
    }

    public void setOneVolunteersSubmitScore(Integer oneVolunteersSubmitScore) {
        this.oneVolunteersSubmitScore = oneVolunteersSubmitScore;
    }

    @Basic
    @Column(name = "oneVolunteers_submit_rank")
    public Integer getOneVolunteersSubmitRank() {
        return oneVolunteersSubmitRank;
    }

    public void setOneVolunteersSubmitRank(Integer oneVolunteersSubmitRank) {
        this.oneVolunteersSubmitRank = oneVolunteersSubmitRank;
    }

    @Basic
    @Column(name = "rank_high_score")
    public Integer getRankHighScore() {
        return rankHighScore;
    }

    public void setRankHighScore(Integer rankHighScore) {
        this.rankHighScore = rankHighScore;
    }

    @Basic
    @Column(name = "rank_low_score")
    public Integer getRankLowScore() {
        return rankLowScore;
    }

    public void setRankLowScore(Integer rankLowScore) {
        this.rankLowScore = rankLowScore;
    }

    @Basic
    @Column(name = "rank_average_score")
    public Integer getRankAverageScore() {
        return rankAverageScore;
    }

    public void setRankAverageScore(Integer rankAverageScore) {
        this.rankAverageScore = rankAverageScore;
    }

    @Basic
    @Column(name = "seg_score_high")
    public Integer getSegScoreHigh() {
        return segScoreHigh;
    }

    public void setSegScoreHigh(Integer segScoreHigh) {
        this.segScoreHigh = segScoreHigh;
    }

    @Basic
    @Column(name = "seg_score_low")
    public Integer getSegScoreLow() {
        return segScoreLow;
    }

    public void setSegScoreLow(Integer segScoreLow) {
        this.segScoreLow = segScoreLow;
    }

    @Basic
    @Column(name = "seg_score_range")
    public Integer getSegScoreRange() {
        return segScoreRange;
    }

    public void setSegScoreRange(Integer segScoreRange) {
        this.segScoreRange = segScoreRange;
    }

    @Basic
    @Column(name = "sign_num")
    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    @Basic
    @Column(name = "reach_num")
    public Integer getReachNum() {
        return reachNum;
    }

    public void setReachNum(Integer reachNum) {
        this.reachNum = reachNum;
    }

    @Basic
    @Column(name = "history_enrolled_AF")
    public Integer getHistoryEnrolledAf() {
        return historyEnrolledAf;
    }

    public void setHistoryEnrolledAf(Integer historyEnrolledAf) {
        this.historyEnrolledAf = historyEnrolledAf;
    }

    @Basic
    @Column(name = "add_num")
    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
    }

    @Basic
    @Column(name = "first_major_num")
    public Integer getFirstMajorNum() {
        return firstMajorNum;
    }

    public void setFirstMajorNum(Integer firstMajorNum) {
        this.firstMajorNum = firstMajorNum;
    }

    @Basic
    @Column(name = "first_cee_num")
    public Integer getFirstCeeNum() {
        return firstCeeNum;
    }

    public void setFirstCeeNum(Integer firstCeeNum) {
        this.firstCeeNum = firstCeeNum;
    }

    @Basic
    @Column(name = "enroll_rate")
    public BigDecimal getEnrollRate() {
        return enrollRate;
    }

    public void setEnrollRate(BigDecimal enrollRate) {
        this.enrollRate = enrollRate;
    }

    @Basic
    @Column(name = "lvl1")
    public Integer getLvl1() {
        return lvl1;
    }

    public void setLvl1(Integer lvl1) {
        this.lvl1 = lvl1;
    }

    @Basic
    @Column(name = "lvl2")
    public Integer getLvl2() {
        return lvl2;
    }

    public void setLvl2(Integer lvl2) {
        this.lvl2 = lvl2;
    }

    @Basic
    @Column(name = "lvl3")
    public Integer getLvl3() {
        return lvl3;
    }

    public void setLvl3(Integer lvl3) {
        this.lvl3 = lvl3;
    }

    @Basic
    @Column(name = "lvl4")
    public Integer getLvl4() {
        return lvl4;
    }

    public void setLvl4(Integer lvl4) {
        this.lvl4 = lvl4;
    }

    @Basic
    @Column(name = "lvl5")
    public Integer getLvl5() {
        return lvl5;
    }

    public void setLvl5(Integer lvl5) {
        this.lvl5 = lvl5;
    }

    @Basic
    @Column(name = "lvl6")
    public Integer getLvl6() {
        return lvl6;
    }

    public void setLvl6(Integer lvl6) {
        this.lvl6 = lvl6;
    }

    @Basic
    @Column(name = "lvl7")
    public Integer getLvl7() {
        return lvl7;
    }

    public void setLvl7(Integer lvl7) {
        this.lvl7 = lvl7;
    }

    @Basic
    @Column(name = "lvl8")
    public Integer getLvl8() {
        return lvl8;
    }

    public void setLvl8(Integer lvl8) {
        this.lvl8 = lvl8;
    }

    @Basic
    @Column(name = "lvl9")
    public Integer getLvl9() {
        return lvl9;
    }

    public void setLvl9(Integer lvl9) {
        this.lvl9 = lvl9;
    }

    @Basic
    @Column(name = "lvl10")
    public Integer getLvl10() {
        return lvl10;
    }

    public void setLvl10(Integer lvl10) {
        this.lvl10 = lvl10;
    }

    @Basic
    @Column(name = "lvl11")
    public Integer getLvl11() {
        return lvl11;
    }

    public void setLvl11(Integer lvl11) {
        this.lvl11 = lvl11;
    }

    @Basic
    @Column(name = "lvl12")
    public Integer getLvl12() {
        return lvl12;
    }

    public void setLvl12(Integer lvl12) {
        this.lvl12 = lvl12;
    }

    @Basic
    @Column(name = "lvl13")
    public Integer getLvl13() {
        return lvl13;
    }

    public void setLvl13(Integer lvl13) {
        this.lvl13 = lvl13;
    }

    @Basic
    @Column(name = "iszhongdian")
    public Boolean getIszhongdian() {
        return iszhongdian;
    }

    public void setIszhongdian(Boolean iszhongdian) {
        this.iszhongdian = iszhongdian;
    }

    @Basic
    @Column(name = "isdalei")
    public Boolean getIsdalei() {
        return isdalei;
    }

    public void setIsdalei(Boolean isdalei) {
        this.isdalei = isdalei;
    }

    @Basic
    @Column(name = "isteshe")
    public Boolean getIsteshe() {
        return isteshe;
    }

    public void setIsteshe(Boolean isteshe) {
        this.isteshe = isteshe;
    }

    @Basic
    @Column(name = "plan")
    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    @Basic
    @Column(name = "score_high")
    public Integer getScoreHigh() {
        return scoreHigh;
    }

    public void setScoreHigh(Integer scoreHigh) {
        this.scoreHigh = scoreHigh;
    }

    @Basic
    @Column(name = "score_low")
    public Integer getScoreLow() {
        return scoreLow;
    }

    public void setScoreLow(Integer scoreLow) {
        this.scoreLow = scoreLow;
    }

    @Basic
    @Column(name = "score_range")
    public Integer getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(Integer scoreRange) {
        this.scoreRange = scoreRange;
    }

    @Basic
    @Column(name = "elective_subjects_num")
    public Integer getElectiveSubjectsNum() {
        return electiveSubjectsNum;
    }

    public void setElectiveSubjectsNum(Integer electiveSubjectsNum) {
        this.electiveSubjectsNum = electiveSubjectsNum;
    }

    @Basic
    @Column(name = "elective_subjects")
    public Integer getElectiveSubjects() {
        return electiveSubjects;
    }

    public void setElectiveSubjects(Integer electiveSubjects) {
        this.electiveSubjects = electiveSubjects;
    }

    @Basic
    @Column(name = "elective_subjects_level")
    public String getElectiveSubjectsLevel() {
        return electiveSubjectsLevel;
    }

    public void setElectiveSubjectsLevel(String electiveSubjectsLevel) {
        this.electiveSubjectsLevel = electiveSubjectsLevel;
    }

    @Basic
    @Column(name = "px_strscore")
    public String getPxStrscore() {
        return pxStrscore;
    }

    public void setPxStrscore(String pxStrscore) {
        this.pxStrscore = pxStrscore;
    }

    @Basic
    @Column(name = "px_zonghe")
    public Integer getPxZonghe() {
        return pxZonghe;
    }

    public void setPxZonghe(Integer pxZonghe) {
        this.pxZonghe = pxZonghe;
    }

    @Basic
    @Column(name = "px_yuwen")
    public Integer getPxYuwen() {
        return pxYuwen;
    }

    public void setPxYuwen(Integer pxYuwen) {
        this.pxYuwen = pxYuwen;
    }

    @Basic
    @Column(name = "px_shuxue")
    public Integer getPxShuxue() {
        return pxShuxue;
    }

    public void setPxShuxue(Integer pxShuxue) {
        this.pxShuxue = pxShuxue;
    }

    @Basic
    @Column(name = "px_waiyu")
    public Integer getPxWaiyu() {
        return pxWaiyu;
    }

    public void setPxWaiyu(Integer pxWaiyu) {
        this.pxWaiyu = pxWaiyu;
    }

    @Basic
    @Column(name = "if_merge")
    public Integer getIfMerge() {
        return ifMerge;
    }

    public void setIfMerge(Integer ifMerge) {
        this.ifMerge = ifMerge;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "old_batch_name")
    public String getOldBatchName() {
        return oldBatchName;
    }

    public void setOldBatchName(String oldBatchName) {
        this.oldBatchName = oldBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCeeEnrollHistory that = (TCeeEnrollHistory) o;

        if (id != that.id) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (majorTypeId != null ? !majorTypeId.equals(that.majorTypeId) : that.majorTypeId != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (batchIdOld != null ? !batchIdOld.equals(that.batchIdOld) : that.batchIdOld != null) return false;
        if (historyType != null ? !historyType.equals(that.historyType) : that.historyType != null) return false;
        if (relationId != null ? !relationId.equals(that.relationId) : that.relationId != null) return false;
        if (univId != null ? !univId.equals(that.univId) : that.univId != null) return false;
        if (univCode != null ? !univCode.equals(that.univCode) : that.univCode != null) return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (univAttr != null ? !univAttr.equals(that.univAttr) : that.univAttr != null) return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;
        if (majorId2 != null ? !majorId2.equals(that.majorId2) : that.majorId2 != null) return false;
        if (majorCode != null ? !majorCode.equals(that.majorCode) : that.majorCode != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (historyPlaned != null ? !historyPlaned.equals(that.historyPlaned) : that.historyPlaned != null)
            return false;
        if (historyEnrolled != null ? !historyEnrolled.equals(that.historyEnrolled) : that.historyEnrolled != null)
            return false;
        if (lowScore != null ? !lowScore.equals(that.lowScore) : that.lowScore != null) return false;
        if (averageScore != null ? !averageScore.equals(that.averageScore) : that.averageScore != null) return false;
        if (highScore != null ? !highScore.equals(that.highScore) : that.highScore != null) return false;
        if (submitPercent != null ? !submitPercent.equals(that.submitPercent) : that.submitPercent != null)
            return false;
        if (oneVolunteersOnline != null ? !oneVolunteersOnline.equals(that.oneVolunteersOnline) : that.oneVolunteersOnline != null)
            return false;
        if (oneVolunteersSubmitNum != null ? !oneVolunteersSubmitNum.equals(that.oneVolunteersSubmitNum) : that.oneVolunteersSubmitNum != null)
            return false;
        if (oneVolunteersSubmitScore != null ? !oneVolunteersSubmitScore.equals(that.oneVolunteersSubmitScore) : that.oneVolunteersSubmitScore != null)
            return false;
        if (oneVolunteersSubmitRank != null ? !oneVolunteersSubmitRank.equals(that.oneVolunteersSubmitRank) : that.oneVolunteersSubmitRank != null)
            return false;
        if (rankHighScore != null ? !rankHighScore.equals(that.rankHighScore) : that.rankHighScore != null)
            return false;
        if (rankLowScore != null ? !rankLowScore.equals(that.rankLowScore) : that.rankLowScore != null) return false;
        if (rankAverageScore != null ? !rankAverageScore.equals(that.rankAverageScore) : that.rankAverageScore != null)
            return false;
        if (segScoreHigh != null ? !segScoreHigh.equals(that.segScoreHigh) : that.segScoreHigh != null) return false;
        if (segScoreLow != null ? !segScoreLow.equals(that.segScoreLow) : that.segScoreLow != null) return false;
        if (segScoreRange != null ? !segScoreRange.equals(that.segScoreRange) : that.segScoreRange != null)
            return false;
        if (signNum != null ? !signNum.equals(that.signNum) : that.signNum != null) return false;
        if (reachNum != null ? !reachNum.equals(that.reachNum) : that.reachNum != null) return false;
        if (historyEnrolledAf != null ? !historyEnrolledAf.equals(that.historyEnrolledAf) : that.historyEnrolledAf != null)
            return false;
        if (addNum != null ? !addNum.equals(that.addNum) : that.addNum != null) return false;
        if (firstMajorNum != null ? !firstMajorNum.equals(that.firstMajorNum) : that.firstMajorNum != null)
            return false;
        if (firstCeeNum != null ? !firstCeeNum.equals(that.firstCeeNum) : that.firstCeeNum != null) return false;
        if (enrollRate != null ? !enrollRate.equals(that.enrollRate) : that.enrollRate != null) return false;
        if (lvl1 != null ? !lvl1.equals(that.lvl1) : that.lvl1 != null) return false;
        if (lvl2 != null ? !lvl2.equals(that.lvl2) : that.lvl2 != null) return false;
        if (lvl3 != null ? !lvl3.equals(that.lvl3) : that.lvl3 != null) return false;
        if (lvl4 != null ? !lvl4.equals(that.lvl4) : that.lvl4 != null) return false;
        if (lvl5 != null ? !lvl5.equals(that.lvl5) : that.lvl5 != null) return false;
        if (lvl6 != null ? !lvl6.equals(that.lvl6) : that.lvl6 != null) return false;
        if (lvl7 != null ? !lvl7.equals(that.lvl7) : that.lvl7 != null) return false;
        if (lvl8 != null ? !lvl8.equals(that.lvl8) : that.lvl8 != null) return false;
        if (lvl9 != null ? !lvl9.equals(that.lvl9) : that.lvl9 != null) return false;
        if (lvl10 != null ? !lvl10.equals(that.lvl10) : that.lvl10 != null) return false;
        if (lvl11 != null ? !lvl11.equals(that.lvl11) : that.lvl11 != null) return false;
        if (lvl12 != null ? !lvl12.equals(that.lvl12) : that.lvl12 != null) return false;
        if (lvl13 != null ? !lvl13.equals(that.lvl13) : that.lvl13 != null) return false;
        if (iszhongdian != null ? !iszhongdian.equals(that.iszhongdian) : that.iszhongdian != null) return false;
        if (isdalei != null ? !isdalei.equals(that.isdalei) : that.isdalei != null) return false;
        if (isteshe != null ? !isteshe.equals(that.isteshe) : that.isteshe != null) return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null) return false;
        if (scoreHigh != null ? !scoreHigh.equals(that.scoreHigh) : that.scoreHigh != null) return false;
        if (scoreLow != null ? !scoreLow.equals(that.scoreLow) : that.scoreLow != null) return false;
        if (scoreRange != null ? !scoreRange.equals(that.scoreRange) : that.scoreRange != null) return false;
        if (electiveSubjectsNum != null ? !electiveSubjectsNum.equals(that.electiveSubjectsNum) : that.electiveSubjectsNum != null)
            return false;
        if (electiveSubjects != null ? !electiveSubjects.equals(that.electiveSubjects) : that.electiveSubjects != null)
            return false;
        if (electiveSubjectsLevel != null ? !electiveSubjectsLevel.equals(that.electiveSubjectsLevel) : that.electiveSubjectsLevel != null)
            return false;
        if (pxStrscore != null ? !pxStrscore.equals(that.pxStrscore) : that.pxStrscore != null) return false;
        if (pxZonghe != null ? !pxZonghe.equals(that.pxZonghe) : that.pxZonghe != null) return false;
        if (pxYuwen != null ? !pxYuwen.equals(that.pxYuwen) : that.pxYuwen != null) return false;
        if (pxShuxue != null ? !pxShuxue.equals(that.pxShuxue) : that.pxShuxue != null) return false;
        if (pxWaiyu != null ? !pxWaiyu.equals(that.pxWaiyu) : that.pxWaiyu != null) return false;
        if (ifMerge != null ? !ifMerge.equals(that.ifMerge) : that.ifMerge != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (oldBatchName != null ? !oldBatchName.equals(that.oldBatchName) : that.oldBatchName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (majorTypeId != null ? majorTypeId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (batchIdOld != null ? batchIdOld.hashCode() : 0);
        result = 31 * result + (historyType != null ? historyType.hashCode() : 0);
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + (univId != null ? univId.hashCode() : 0);
        result = 31 * result + (univCode != null ? univCode.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (univAttr != null ? univAttr.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorId2 != null ? majorId2.hashCode() : 0);
        result = 31 * result + (majorCode != null ? majorCode.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (historyPlaned != null ? historyPlaned.hashCode() : 0);
        result = 31 * result + (historyEnrolled != null ? historyEnrolled.hashCode() : 0);
        result = 31 * result + (lowScore != null ? lowScore.hashCode() : 0);
        result = 31 * result + (averageScore != null ? averageScore.hashCode() : 0);
        result = 31 * result + (highScore != null ? highScore.hashCode() : 0);
        result = 31 * result + (submitPercent != null ? submitPercent.hashCode() : 0);
        result = 31 * result + (oneVolunteersOnline != null ? oneVolunteersOnline.hashCode() : 0);
        result = 31 * result + (oneVolunteersSubmitNum != null ? oneVolunteersSubmitNum.hashCode() : 0);
        result = 31 * result + (oneVolunteersSubmitScore != null ? oneVolunteersSubmitScore.hashCode() : 0);
        result = 31 * result + (oneVolunteersSubmitRank != null ? oneVolunteersSubmitRank.hashCode() : 0);
        result = 31 * result + (rankHighScore != null ? rankHighScore.hashCode() : 0);
        result = 31 * result + (rankLowScore != null ? rankLowScore.hashCode() : 0);
        result = 31 * result + (rankAverageScore != null ? rankAverageScore.hashCode() : 0);
        result = 31 * result + (segScoreHigh != null ? segScoreHigh.hashCode() : 0);
        result = 31 * result + (segScoreLow != null ? segScoreLow.hashCode() : 0);
        result = 31 * result + (segScoreRange != null ? segScoreRange.hashCode() : 0);
        result = 31 * result + (signNum != null ? signNum.hashCode() : 0);
        result = 31 * result + (reachNum != null ? reachNum.hashCode() : 0);
        result = 31 * result + (historyEnrolledAf != null ? historyEnrolledAf.hashCode() : 0);
        result = 31 * result + (addNum != null ? addNum.hashCode() : 0);
        result = 31 * result + (firstMajorNum != null ? firstMajorNum.hashCode() : 0);
        result = 31 * result + (firstCeeNum != null ? firstCeeNum.hashCode() : 0);
        result = 31 * result + (enrollRate != null ? enrollRate.hashCode() : 0);
        result = 31 * result + (lvl1 != null ? lvl1.hashCode() : 0);
        result = 31 * result + (lvl2 != null ? lvl2.hashCode() : 0);
        result = 31 * result + (lvl3 != null ? lvl3.hashCode() : 0);
        result = 31 * result + (lvl4 != null ? lvl4.hashCode() : 0);
        result = 31 * result + (lvl5 != null ? lvl5.hashCode() : 0);
        result = 31 * result + (lvl6 != null ? lvl6.hashCode() : 0);
        result = 31 * result + (lvl7 != null ? lvl7.hashCode() : 0);
        result = 31 * result + (lvl8 != null ? lvl8.hashCode() : 0);
        result = 31 * result + (lvl9 != null ? lvl9.hashCode() : 0);
        result = 31 * result + (lvl10 != null ? lvl10.hashCode() : 0);
        result = 31 * result + (lvl11 != null ? lvl11.hashCode() : 0);
        result = 31 * result + (lvl12 != null ? lvl12.hashCode() : 0);
        result = 31 * result + (lvl13 != null ? lvl13.hashCode() : 0);
        result = 31 * result + (iszhongdian != null ? iszhongdian.hashCode() : 0);
        result = 31 * result + (isdalei != null ? isdalei.hashCode() : 0);
        result = 31 * result + (isteshe != null ? isteshe.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (scoreHigh != null ? scoreHigh.hashCode() : 0);
        result = 31 * result + (scoreLow != null ? scoreLow.hashCode() : 0);
        result = 31 * result + (scoreRange != null ? scoreRange.hashCode() : 0);
        result = 31 * result + (electiveSubjectsNum != null ? electiveSubjectsNum.hashCode() : 0);
        result = 31 * result + (electiveSubjects != null ? electiveSubjects.hashCode() : 0);
        result = 31 * result + (electiveSubjectsLevel != null ? electiveSubjectsLevel.hashCode() : 0);
        result = 31 * result + (pxStrscore != null ? pxStrscore.hashCode() : 0);
        result = 31 * result + (pxZonghe != null ? pxZonghe.hashCode() : 0);
        result = 31 * result + (pxYuwen != null ? pxYuwen.hashCode() : 0);
        result = 31 * result + (pxShuxue != null ? pxShuxue.hashCode() : 0);
        result = 31 * result + (pxWaiyu != null ? pxWaiyu.hashCode() : 0);
        result = 31 * result + (ifMerge != null ? ifMerge.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (oldBatchName != null ? oldBatchName.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "gap")
    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }
}
