package com.example.demo.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.demo.Model.Entity.SupportedBankEntity;
import com.example.demo.DTO.SupportedBankDTO.SupportedBankDTO;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SupportedBankMapper {
    
    SupportedBankDTO toDTO(SupportedBankEntity supportedBankEntity);

    @Mapping(target = "payments", ignore = true)
    SupportedBankEntity toEntity(SupportedBankDTO supportedBankDTO);

    List<SupportedBankDTO> toDTOList(List<SupportedBankEntity> supportedBankEntities);

    List<SupportedBankEntity> toEntityList(List<SupportedBankDTO> supportedBankDTOs);
}