package message.workflow.repository;

import message.mybatis.repository.MyBatisRepository;
import message.workflow.domain.FlowNode;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * .
 *
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 2015-11-18 14:19
 */
@Repository
public interface FlowNodeRepository extends MyBatisRepository<FlowNode> {
    /**
     * 通过流程id获取流程节点
     *
     * @param flowId 流程id
     * @return
     */
    List<FlowNode> findByFlowId(@Param("flowId") Long flowId);

    /**
     * 通过流程id获取流程节点
     *
     * @param flowIds 流程id
     * @return
     */
    List<FlowNode> loadByFlowIds(@Param("flowIds") List<Long> flowIds);
}