package com.banking.FundTransferService.controller;

import com.banking.FundTransferService.model.dto.FundTransferDto;
import com.banking.FundTransferService.model.dto.request.FundTransferRequest;
import com.banking.FundTransferService.model.dto.response.FundTransferResponse;
import com.banking.FundTransferService.service.FundTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fund-transfers")
public class FundTransferController {

    private final FundTransferService fundTransferService;

    @PostMapping
    public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
        return new ResponseEntity<>(fundTransferService.fundTransfer(fundTransferRequest), HttpStatus.CREATED);
    }


    @GetMapping("/{referenceId}")
    public ResponseEntity<FundTransferDto> getTransferDetailsFromReferenceId(@PathVariable String referenceId) {
        return new ResponseEntity<>(fundTransferService.getTransferDetailsFromReferenceId(referenceId), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<FundTransferDto>> getAllTransfersByAccountId(@RequestParam String accountId) {
        return new ResponseEntity<>(fundTransferService.getAllTransfersByAccountId(accountId), HttpStatus.OK);
    }
}
