package com.example.PasswordGenerator.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Password {
    private int count;
    private int length;
    private boolean includeUpperCase;
    private boolean includeNumbers;
    private boolean includeSpecialChars;
}
