package com.banking.FundTransferService.service.implementation;

import com.banking.FundTransferService.exception.ResourceNotFoundException;
import com.banking.FundTransferService.model.TransactionStatus;
import com.banking.FundTransferService.model.TransferType;
import com.banking.FundTransferService.model.dto.FundTransferDto;
import com.banking.FundTransferService.model.dto.request.FundTransferRequest;
import com.banking.FundTransferService.model.dto.response.FundTransferResponse;
import com.banking.FundTransferService.model.entity.FundTransfer;
import com.banking.FundTransferService.model.mapper.FundTransferMapper;
import com.banking.FundTransferService.repository.FundTransferRepository;
import com.banking.FundTransferService.service.FundTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundTransferServiceImpl implements FundTransferService {

    // private final AccountService accountService;
    private final FundTransferRepository fundTransferRepository;
    // private final TransactionService transactionService;

    @Value("${spring.application.ok}")
    private String ok;

    private final FundTransferMapper fundTransferMapper = new FundTransferMapper();


    @Override
    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {

        String transactionId = "123457sse2";
        FundTransfer fundTransfer = FundTransfer.builder()
                .transferType(TransferType.INTERNAL)
                .amount(fundTransferRequest.getAmount())
                .fromAccount("7845124578")
                .transactionReference(transactionId)
                .status(TransactionStatus.SUCCESS)
                .toAccount("7845124579").build();

        fundTransferRepository.save(fundTransfer);
        return FundTransferResponse.builder()
                .transactionId(transactionId)
                .message("Fund transfer was successful").build();
    }


//    private String internalTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
//
//        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(amount));
//        accountService.updateAccount(fromAccount.getAccountNumber(), fromAccount);
//
//        toAccount.setAvailableBalance(toAccount.getAvailableBalance().add(amount));
//        accountService.updateAccount(toAccount.getAccountNumber(), toAccount);
//
//        List<Transaction> transactions = List.of(
//                Transaction.builder()
//                        .accountId(fromAccount.getAccountNumber())
//                        .transactionType("INTERNAL_TRANSFER")
//                        .amount(amount.negate())
//                        .description("Internal fund transfer from "+fromAccount.getAccountNumber()+" to "+toAccount.getAccountNumber())
//                        .build(),
//                Transaction.builder()
//                        .accountId(toAccount.getAccountNumber())
//                        .transactionType("INTERNAL_TRANSFER")
//                        .amount(amount)
//                        .description("Internal fund transfer received from: "+fromAccount.getAccountNumber()).build());
//
//        String transactionReference = UUID.randomUUID().toString();
//        transactionService.makeInternalTransactions(transactions, transactionReference);
//        return transactionReference;
//    }




    @Override
    public FundTransferDto getTransferDetailsFromReferenceId(String referenceId) {
        return fundTransferRepository.findFundTransferByTransactionReference(referenceId)
                .map(fundTransferMapper::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Fund transfer not found"));
    }

    @Override
    public List<FundTransferDto> getAllTransfersByAccountId(String accountId) {

        List<FundTransfer> fundTransfers = fundTransferRepository.findFundTransferByFromAccount(accountId);
        return fundTransfers.stream().map(fundTransfer -> {
            FundTransferDto fundTransferDto = new FundTransferDto();
            BeanUtils.copyProperties(fundTransfer, fundTransferDto);
            fundTransferDto.setTransferredOn(fundTransfer.getTransferredOn());
            fundTransferDto.setTransferType(fundTransfer.getTransferType());
            fundTransferDto.setToAccount(fundTransfer.getToAccount());
            fundTransferDto.setStatus(fundTransfer.getStatus());
            fundTransferDto.setFromAccount(fundTransfer.getFromAccount());
            fundTransferDto.setTransactionReference(fundTransfer.getTransactionReference());
            fundTransferDto.setAmount(fundTransfer.getAmount());
            return fundTransferDto;
        }).collect(Collectors.toList());
    }
}
