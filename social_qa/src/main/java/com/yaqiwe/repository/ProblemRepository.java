package com.yaqiwe.repository;

import com.yaqiwe.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 13:35
 * @Version 1.0
 */
public interface ProblemRepository extends JpaRepository<Problem,String> , JpaSpecificationExecutor<Problem> {

    /**
     * 最新回复
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT problem.* FROM problem ,pl WHERE id=problem_id and label_id=? ORDER BY reply_time DESC")
    public Page<Problem> newList(String labelId, Pageable pageable);

    /**
     * 最热回复
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT problem.* FROM problem ,pl WHERE id=problem_id and label_id=? ORDER BY reply DESC")
    public Page<Problem> hotList(String labelId, Pageable pageable);

    /**
     * 等待回复
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT problem.* FROM problem ,pl WHERE id=problem_id and label_id=? AND reply=0 ORDER BY create_time DESC")
    public Page<Problem> waitList(String labelId , Pageable pageable);
}
