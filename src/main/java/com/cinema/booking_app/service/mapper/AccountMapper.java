package com.cinema.booking_app.service.mapper;

import com.cinema.booking_app.entity.AccountEntity;
import com.cinema.booking_app.entity.enums.TokenType;
import com.cinema.booking_app.service.dto.AccountDto;
import com.cinema.booking_app.service.dto.request.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = DefaultConfigMapper.class,
        imports = {TokenType.class}
)
public interface AccountMapper extends EntityMapper<AccountDto, AccountEntity> {
    @Mapping(target = "avatar", ignore = true)
    AccountEntity toEntity(SignUpRequest request);

}
