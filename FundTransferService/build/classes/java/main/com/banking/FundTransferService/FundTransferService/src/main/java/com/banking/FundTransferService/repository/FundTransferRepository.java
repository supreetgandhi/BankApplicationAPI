package com.banking.FundTransferService.repository;

import com.banking.FundTransferService.model.entity.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {

    Optional<FundTransfer> findFundTransferByTransactionReference(String referenceId);

    List<FundTransfer> findFundTransferByFromAccount(String accountId);
}
