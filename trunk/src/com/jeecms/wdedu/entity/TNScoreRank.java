package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_score_rank", schema = "wodecareer", catalog = "")
public class TNScoreRank {
    private int id;
    private String userAccount;
    private String rankType;
    private Integer chinese;
    private Integer chineseCount;
    private Integer math;
    private Integer mathCount;
    private Integer english;
    private Integer englishCount;
    private Integer politics;
    private Integer politicsCount;
    private Integer history;
    private Integer historyCount;
    private Integer geography;
    private Integer geographyCount;
    private Integer physical;
    private Integer physicalCount;
    private Integer chemistry;
    private Integer chemistryCount;
    private Integer biology;
    private Integer biologyCount;
    private Integer painting;
    private Integer paintingCount;
    private Integer sports;
    private Integer sportsCount;
    private Integer music;
    private Integer musicCount;
    private Integer technology;
    private Integer technologyCount;
    private Integer liberalarts;
    private Integer liberalartsCount;
    private Integer science;
    private Integer scienceCount;
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
    @Column(name = "rankType")
    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    @Basic
    @Column(name = "chinese")
    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    @Basic
    @Column(name = "chineseCount")
    public Integer getChineseCount() {
        return chineseCount;
    }

    public void setChineseCount(Integer chineseCount) {
        this.chineseCount = chineseCount;
    }

    @Basic
    @Column(name = "math")
    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    @Basic
    @Column(name = "mathCount")
    public Integer getMathCount() {
        return mathCount;
    }

    public void setMathCount(Integer mathCount) {
        this.mathCount = mathCount;
    }

    @Basic
    @Column(name = "English")
    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    @Basic
    @Column(name = "EnglishCount")
    public Integer getEnglishCount() {
        return englishCount;
    }

    public void setEnglishCount(Integer englishCount) {
        this.englishCount = englishCount;
    }

    @Basic
    @Column(name = "politics")
    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }

    @Basic
    @Column(name = "politicsCount")
    public Integer getPoliticsCount() {
        return politicsCount;
    }

    public void setPoliticsCount(Integer politicsCount) {
        this.politicsCount = politicsCount;
    }

    @Basic
    @Column(name = "history")
    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    @Basic
    @Column(name = "historyCount")
    public Integer getHistoryCount() {
        return historyCount;
    }

    public void setHistoryCount(Integer historyCount) {
        this.historyCount = historyCount;
    }

    @Basic
    @Column(name = "geography")
    public Integer getGeography() {
        return geography;
    }

    public void setGeography(Integer geography) {
        this.geography = geography;
    }

    @Basic
    @Column(name = "geographyCount")
    public Integer getGeographyCount() {
        return geographyCount;
    }

    public void setGeographyCount(Integer geographyCount) {
        this.geographyCount = geographyCount;
    }

    @Basic
    @Column(name = "physical")
    public Integer getPhysical() {
        return physical;
    }

    public void setPhysical(Integer physical) {
        this.physical = physical;
    }

    @Basic
    @Column(name = "physicalCount")
    public Integer getPhysicalCount() {
        return physicalCount;
    }

    public void setPhysicalCount(Integer physicalCount) {
        this.physicalCount = physicalCount;
    }

    @Basic
    @Column(name = "chemistry")
    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    @Basic
    @Column(name = "chemistryCount")
    public Integer getChemistryCount() {
        return chemistryCount;
    }

    public void setChemistryCount(Integer chemistryCount) {
        this.chemistryCount = chemistryCount;
    }

    @Basic
    @Column(name = "biology")
    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }

    @Basic
    @Column(name = "biologyCount")
    public Integer getBiologyCount() {
        return biologyCount;
    }

    public void setBiologyCount(Integer biologyCount) {
        this.biologyCount = biologyCount;
    }

    @Basic
    @Column(name = "painting")
    public Integer getPainting() {
        return painting;
    }

    public void setPainting(Integer painting) {
        this.painting = painting;
    }

    @Basic
    @Column(name = "paintingCount")
    public Integer getPaintingCount() {
        return paintingCount;
    }

    public void setPaintingCount(Integer paintingCount) {
        this.paintingCount = paintingCount;
    }

    @Basic
    @Column(name = "sports")
    public Integer getSports() {
        return sports;
    }

    public void setSports(Integer sports) {
        this.sports = sports;
    }

    @Basic
    @Column(name = "sportsCount")
    public Integer getSportsCount() {
        return sportsCount;
    }

    public void setSportsCount(Integer sportsCount) {
        this.sportsCount = sportsCount;
    }

    @Basic
    @Column(name = "music")
    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        this.music = music;
    }

    @Basic
    @Column(name = "musicCount")
    public Integer getMusicCount() {
        return musicCount;
    }

    public void setMusicCount(Integer musicCount) {
        this.musicCount = musicCount;
    }

    @Basic
    @Column(name = "technology")
    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }

    @Basic
    @Column(name = "technologyCount")
    public Integer getTechnologyCount() {
        return technologyCount;
    }

    public void setTechnologyCount(Integer technologyCount) {
        this.technologyCount = technologyCount;
    }

    @Basic
    @Column(name = "liberalarts")
    public Integer getLiberalarts() {
        return liberalarts;
    }

    public void setLiberalarts(Integer liberalarts) {
        this.liberalarts = liberalarts;
    }

    @Basic
    @Column(name = "liberalartsCount")
    public Integer getLiberalartsCount() {
        return liberalartsCount;
    }

    public void setLiberalartsCount(Integer liberalartsCount) {
        this.liberalartsCount = liberalartsCount;
    }

    @Basic
    @Column(name = "science")
    public Integer getScience() {
        return science;
    }

    public void setScience(Integer science) {
        this.science = science;
    }

    @Basic
    @Column(name = "scienceCount")
    public Integer getScienceCount() {
        return scienceCount;
    }

    public void setScienceCount(Integer scienceCount) {
        this.scienceCount = scienceCount;
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
        TNScoreRank that = (TNScoreRank) o;
        return id == that.id &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(rankType, that.rankType) &&
                Objects.equals(chinese, that.chinese) &&
                Objects.equals(chineseCount, that.chineseCount) &&
                Objects.equals(math, that.math) &&
                Objects.equals(mathCount, that.mathCount) &&
                Objects.equals(english, that.english) &&
                Objects.equals(englishCount, that.englishCount) &&
                Objects.equals(politics, that.politics) &&
                Objects.equals(politicsCount, that.politicsCount) &&
                Objects.equals(history, that.history) &&
                Objects.equals(historyCount, that.historyCount) &&
                Objects.equals(geography, that.geography) &&
                Objects.equals(geographyCount, that.geographyCount) &&
                Objects.equals(physical, that.physical) &&
                Objects.equals(physicalCount, that.physicalCount) &&
                Objects.equals(chemistry, that.chemistry) &&
                Objects.equals(chemistryCount, that.chemistryCount) &&
                Objects.equals(biology, that.biology) &&
                Objects.equals(biologyCount, that.biologyCount) &&
                Objects.equals(painting, that.painting) &&
                Objects.equals(paintingCount, that.paintingCount) &&
                Objects.equals(sports, that.sports) &&
                Objects.equals(sportsCount, that.sportsCount) &&
                Objects.equals(music, that.music) &&
                Objects.equals(musicCount, that.musicCount) &&
                Objects.equals(technology, that.technology) &&
                Objects.equals(technologyCount, that.technologyCount) &&
                Objects.equals(liberalarts, that.liberalarts) &&
                Objects.equals(liberalartsCount, that.liberalartsCount) &&
                Objects.equals(science, that.science) &&
                Objects.equals(scienceCount, that.scienceCount) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, rankType, chinese, chineseCount, math, mathCount, english, englishCount, politics, politicsCount, history, historyCount, geography, geographyCount, physical, physicalCount, chemistry, chemistryCount, biology, biologyCount, painting, paintingCount, sports, sportsCount, music, musicCount, technology, technologyCount, liberalarts, liberalartsCount, science, scienceCount, userId);
    }
}
