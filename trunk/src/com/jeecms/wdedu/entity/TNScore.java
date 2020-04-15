package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_score", schema = "wodecareer", catalog = "")
public class TNScore {
    private int id;
    private String userAccount;
    private String scoreType;
    private String chinese;
    private String chineseTotal;
    private String math;
    private String mathTotal;
    private String english;
    private String englishTotal;
    private String politics;
    private String politicsTotal;
    private String history;
    private String historyTotal;
    private String geography;
    private String geographyTotal;
    private String physical;
    private String physicalTotal;
    private String chemistry;
    private String chemistryTotal;
    private String biology;
    private String biologyTotal;
    private String painting;
    private String paintingTotal;
    private String sports;
    private String sportsTotal;
    private String music;
    private String musicTotal;
    private String technology;
    private String technologyTotal;
    private String liberalarts;
    private String liberalartsTotal;
    private String science;
    private String scienceTotal;
    private String courseScore;
    private String total;
    private Integer rank;
    private Integer studentCount;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "scoreType")
    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    @Basic
    @Column(name = "chinese")
    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    @Basic
    @Column(name = "chineseTotal")
    public String getChineseTotal() {
        return chineseTotal;
    }

    public void setChineseTotal(String chineseTotal) {
        this.chineseTotal = chineseTotal;
    }

    @Basic
    @Column(name = "math")
    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    @Basic
    @Column(name = "mathTotal")
    public String getMathTotal() {
        return mathTotal;
    }

    public void setMathTotal(String mathTotal) {
        this.mathTotal = mathTotal;
    }

    @Basic
    @Column(name = "English")
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Basic
    @Column(name = "EnglishTotal")
    public String getEnglishTotal() {
        return englishTotal;
    }

    public void setEnglishTotal(String englishTotal) {
        this.englishTotal = englishTotal;
    }

    @Basic
    @Column(name = "politics")
    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    @Basic
    @Column(name = "politicsTotal")
    public String getPoliticsTotal() {
        return politicsTotal;
    }

    public void setPoliticsTotal(String politicsTotal) {
        this.politicsTotal = politicsTotal;
    }

    @Basic
    @Column(name = "history")
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Basic
    @Column(name = "historyTotal")
    public String getHistoryTotal() {
        return historyTotal;
    }

    public void setHistoryTotal(String historyTotal) {
        this.historyTotal = historyTotal;
    }

    @Basic
    @Column(name = "geography")
    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    @Basic
    @Column(name = "geographyTotal")
    public String getGeographyTotal() {
        return geographyTotal;
    }

    public void setGeographyTotal(String geographyTotal) {
        this.geographyTotal = geographyTotal;
    }

    @Basic
    @Column(name = "physical")
    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    @Basic
    @Column(name = "physicalTotal")
    public String getPhysicalTotal() {
        return physicalTotal;
    }

    public void setPhysicalTotal(String physicalTotal) {
        this.physicalTotal = physicalTotal;
    }

    @Basic
    @Column(name = "chemistry")
    public String getChemistry() {
        return chemistry;
    }

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    @Basic
    @Column(name = "chemistryTotal")
    public String getChemistryTotal() {
        return chemistryTotal;
    }

    public void setChemistryTotal(String chemistryTotal) {
        this.chemistryTotal = chemistryTotal;
    }

    @Basic
    @Column(name = "biology")
    public String getBiology() {
        return biology;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    @Basic
    @Column(name = "biologyTotal")
    public String getBiologyTotal() {
        return biologyTotal;
    }

    public void setBiologyTotal(String biologyTotal) {
        this.biologyTotal = biologyTotal;
    }

    @Basic
    @Column(name = "painting")
    public String getPainting() {
        return painting;
    }

    public void setPainting(String painting) {
        this.painting = painting;
    }

    @Basic
    @Column(name = "paintingTotal")
    public String getPaintingTotal() {
        return paintingTotal;
    }

    public void setPaintingTotal(String paintingTotal) {
        this.paintingTotal = paintingTotal;
    }

    @Basic
    @Column(name = "sports")
    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    @Basic
    @Column(name = "sportsTotal")
    public String getSportsTotal() {
        return sportsTotal;
    }

    public void setSportsTotal(String sportsTotal) {
        this.sportsTotal = sportsTotal;
    }

    @Basic
    @Column(name = "music")
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Basic
    @Column(name = "musicTotal")
    public String getMusicTotal() {
        return musicTotal;
    }

    public void setMusicTotal(String musicTotal) {
        this.musicTotal = musicTotal;
    }

    @Basic
    @Column(name = "technology")
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Basic
    @Column(name = "technologyTotal")
    public String getTechnologyTotal() {
        return technologyTotal;
    }

    public void setTechnologyTotal(String technologyTotal) {
        this.technologyTotal = technologyTotal;
    }

    @Basic
    @Column(name = "liberalarts")
    public String getLiberalarts() {
        return liberalarts;
    }

    public void setLiberalarts(String liberalarts) {
        this.liberalarts = liberalarts;
    }

    @Basic
    @Column(name = "liberalartsTotal")
    public String getLiberalartsTotal() {
        return liberalartsTotal;
    }

    public void setLiberalartsTotal(String liberalartsTotal) {
        this.liberalartsTotal = liberalartsTotal;
    }

    @Basic
    @Column(name = "science")
    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    @Basic
    @Column(name = "scienceTotal")
    public String getScienceTotal() {
        return scienceTotal;
    }

    public void setScienceTotal(String scienceTotal) {
        this.scienceTotal = scienceTotal;
    }

    @Basic
    @Column(name = "courseScore")
    public String getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(String courseScore) {
        this.courseScore = courseScore;
    }

    @Basic
    @Column(name = "total")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "studentCount")
    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNScore tnScore = (TNScore) o;
        return id == tnScore.id &&
                Objects.equals(userAccount, tnScore.userAccount) &&
                Objects.equals(scoreType, tnScore.scoreType) &&
                Objects.equals(chinese, tnScore.chinese) &&
                Objects.equals(chineseTotal, tnScore.chineseTotal) &&
                Objects.equals(math, tnScore.math) &&
                Objects.equals(mathTotal, tnScore.mathTotal) &&
                Objects.equals(english, tnScore.english) &&
                Objects.equals(englishTotal, tnScore.englishTotal) &&
                Objects.equals(politics, tnScore.politics) &&
                Objects.equals(politicsTotal, tnScore.politicsTotal) &&
                Objects.equals(history, tnScore.history) &&
                Objects.equals(historyTotal, tnScore.historyTotal) &&
                Objects.equals(geography, tnScore.geography) &&
                Objects.equals(geographyTotal, tnScore.geographyTotal) &&
                Objects.equals(physical, tnScore.physical) &&
                Objects.equals(physicalTotal, tnScore.physicalTotal) &&
                Objects.equals(chemistry, tnScore.chemistry) &&
                Objects.equals(chemistryTotal, tnScore.chemistryTotal) &&
                Objects.equals(biology, tnScore.biology) &&
                Objects.equals(biologyTotal, tnScore.biologyTotal) &&
                Objects.equals(painting, tnScore.painting) &&
                Objects.equals(paintingTotal, tnScore.paintingTotal) &&
                Objects.equals(sports, tnScore.sports) &&
                Objects.equals(sportsTotal, tnScore.sportsTotal) &&
                Objects.equals(music, tnScore.music) &&
                Objects.equals(musicTotal, tnScore.musicTotal) &&
                Objects.equals(technology, tnScore.technology) &&
                Objects.equals(technologyTotal, tnScore.technologyTotal) &&
                Objects.equals(liberalarts, tnScore.liberalarts) &&
                Objects.equals(liberalartsTotal, tnScore.liberalartsTotal) &&
                Objects.equals(science, tnScore.science) &&
                Objects.equals(scienceTotal, tnScore.scienceTotal) &&
                Objects.equals(courseScore, tnScore.courseScore) &&
                Objects.equals(total, tnScore.total) &&
                Objects.equals(rank, tnScore.rank) &&
                Objects.equals(studentCount, tnScore.studentCount) &&
                Objects.equals(userId, tnScore.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, scoreType, chinese, chineseTotal, math, mathTotal, english, englishTotal, politics, politicsTotal, history, historyTotal, geography, geographyTotal, physical, physicalTotal, chemistry, chemistryTotal, biology, biologyTotal, painting, paintingTotal, sports, sportsTotal, music, musicTotal, technology, technologyTotal, liberalarts, liberalartsTotal, science, scienceTotal, courseScore, total, rank, studentCount, userId);
    }
}
