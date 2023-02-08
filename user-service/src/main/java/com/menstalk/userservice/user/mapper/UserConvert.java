package com.menstalk.userservice.user.mapper;

import com.menstalk.userservice.user.domain.User;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User authConvertToUser(UserAuthResponse userAuthResponse);

    UserResponse userConvertToUserResponse(User user);

}
