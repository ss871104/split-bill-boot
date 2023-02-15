package com.menstalk.userservice.user.mapper;

import com.menstalk.userservice.user.domain.User;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserAuthResponse userConvertToAuthResponse(User user);
    @Mapping(target = "registerTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResponse userConvertToUserResponse(User user);

}
