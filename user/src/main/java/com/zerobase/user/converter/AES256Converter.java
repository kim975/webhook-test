package com.zerobase.user.converter;

import com.zerobase.user.util.EncryptUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AES256Converter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String text) {
        return EncryptUtil.aes256Encrypt(text);
    }

    @Override
    public String convertToEntityAttribute(String text) {
        return EncryptUtil.aes256Decrypt(text);
    }
}
