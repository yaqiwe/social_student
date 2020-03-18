package com.yaqiwe.repository;

import com.yaqiwe.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 13:36
 * @Version 1.0
 */
public interface ReplyRepository extends JpaRepository<Reply,String >, JpaSpecificationExecutor<Reply> {
}
