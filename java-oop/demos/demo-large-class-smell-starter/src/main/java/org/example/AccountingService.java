package org.example;

import java.math.BigDecimal;

public class AccountingService {
    public BigDecimal calculateTotalOutstandingPayments(Visit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (Visit pv : visits) {
            if (!pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }

    public BigDecimal calculateTotalReceived(Visit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (Visit pv : visits) {
            if (pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }
}
