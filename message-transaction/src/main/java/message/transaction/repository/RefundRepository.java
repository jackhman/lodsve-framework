package message.transaction.repository;

import message.mybatis.common.dao.BaseRepository;
import message.transaction.domain.Refund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 15/11/5 下午7:43
 */
@Repository
public interface RefundRepository extends BaseRepository<Refund> {
    Refund findByPaymentId(Long paymentId);

    Refund findByChargeId(String chargeId);

    Page<Refund> findRefunds(Pageable pageable);
}