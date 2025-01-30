package com.tistory.eclipse4j.persistence.config

import com.tistory.eclipse4j.persistence.support.DataSourceType
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class RWRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly())
            return DataSourceType.READ
        return DataSourceType.WRITE
    }
}
