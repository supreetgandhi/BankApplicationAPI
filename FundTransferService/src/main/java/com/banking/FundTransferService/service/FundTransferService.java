package com.banking.FundTransferService.service;

import com.banking.FundTransferService.model.dto.FundTransferDto;
import com.banking.FundTransferService.model.dto.request.FundTransferRequest;
import com.banking.FundTransferService.model.dto.response.FundTransferResponse;

import java.util.List;

public interface FundTransferService {


    FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);

    FundTransferDto getTransferDetailsFromReferenceId(String referenceId);

    List<FundTransferDto> getAllTransfersByAccountId(String accountId);
}
