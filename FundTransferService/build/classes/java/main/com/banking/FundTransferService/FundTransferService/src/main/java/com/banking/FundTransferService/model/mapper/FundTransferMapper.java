package com.banking.FundTransferService.model.mapper;

import com.banking.FundTransferService.model.dto.FundTransferDto;
import com.banking.FundTransferService.model.entity.FundTransfer;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class FundTransferMapper {

    public FundTransfer convertToEntity(FundTransferDto dto, Object... args) {

        FundTransfer fundTransfer = new FundTransfer();
        if(!Objects.isNull(dto)){
            BeanUtils.copyProperties(dto, fundTransfer);
        }
        return fundTransfer;
    }

    public FundTransferDto convertToDto(FundTransfer entity, Object... args) {

        FundTransferDto fundTransferDto = new FundTransferDto();
        if(!Objects.isNull(entity)){
            BeanUtils.copyProperties(entity, fundTransferDto);
        }
        return fundTransferDto;
    }
}
