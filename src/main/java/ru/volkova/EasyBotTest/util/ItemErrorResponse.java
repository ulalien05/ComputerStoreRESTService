package ru.volkova.EasyBotTest.util;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemErrorResponse {

    private final String message;

    private final LocalDateTime time;


}
