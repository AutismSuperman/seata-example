package com.example.service;

import com.example.entity.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;


@LocalTCC
public interface OrderService {


    @TwoPhaseBusinessAction(name = "OrderTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean create(BusinessActionContext actionContext,
                   @BusinessActionContextParameter(paramName = "orderId") String orderId,
                   Order order
    );

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean rollback(BusinessActionContext actionContext);

}