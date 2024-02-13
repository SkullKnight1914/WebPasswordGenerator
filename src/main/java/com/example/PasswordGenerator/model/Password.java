package com.example.PasswordGenerator.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Password {
//    @Min(value = 1, message = "Количество должно быть больше нуля")
//    @Max(value = 16, message = "Количество сгенерированных паролей не должно быть больше 16")
    private int count;
//    @Min(value = 1, message = "Длина пароля должна быть больше нуля")
    private int length;
    private boolean includeUpperCase;
    private boolean includeNumbers;
    private boolean includeSpecialChars;
}
