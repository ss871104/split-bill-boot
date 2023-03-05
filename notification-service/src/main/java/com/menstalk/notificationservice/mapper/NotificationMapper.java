package com.menstalk.notificationservice.mapper;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.dto.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationResponse notificationToDto(Notification notification);
}
