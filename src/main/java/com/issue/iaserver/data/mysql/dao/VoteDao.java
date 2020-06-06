package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.mysql.entity.VoteDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * 投票对应关系
 * IssueID 关键词id 投票用户id（这个用来标识那些用户给哪个issue的哪个关键词投过票）
 * IssueID 关注点id 投票用户 id（这个用来标识那些用户给哪个issue的哪个关注点投过票）
 * IssueID 关键词id 票数（issue的某个关键词的票数）
 * IssueID 关注点id 票数（issue的某个关注点的票数）
 *
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 19:42
 */
@Repository
public interface VoteDao extends JpaRepository<VoteDO, Long> {

    @Query("select v.focus_id from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id and v.user_id=:user_id " +
            "and keyword_id IS NULL")
    ArrayList<Long> getFocusByUserId(@Param("csv_id") long csv_id, @Param("item_id") long item_id,
                                     @Param("user_id") long user_id);

    @Query("select v.keyword_id from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id and v.user_id=:user_id " +
            "and focus_id IS NULL")
    ArrayList<Long> getKeywordByUserId(@Param("csv_id") long csv_id, @Param("item_id") long item_id,
                                       @Param("user_id") long user_id);

    @Query("select v.vote from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id and v.focus_id=:focus_id" +
            " and v.keyword_id IS NULL")
    ArrayList<Integer> getFocusVote(@Param("csv_id") long csv_id, @Param("item_id") long item_id,
                                   @Param("focus_id") long focus_id);

    @Query("select v.vote from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id and v.focus_id=:focus_id" +
            " and v.focus_id IS NULL")
    ArrayList<Integer> getKeywordVote(@Param("csv_id") long csv_id, @Param("item_id") long item_id,
                                    @Param("focus_id") long focus_id);

    @Query("select v.keyword_id from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id")
    ArrayList<Long> getKeyWordIdByIssueID(@Param("csv_id") long csv_id, @Param("item_id") long item_id);

    @Query("select v.focus_id from VoteDO v where v.csv_id=:csv_id and v.item_id=:item_id")
    ArrayList<Long> getFocusIdByIssueID(@Param("csv_id") long csv_id, @Param("item_id") long item_id);

}
