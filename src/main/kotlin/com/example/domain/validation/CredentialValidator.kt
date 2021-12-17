package com.example.domain.validation

import com.example.domain.util.AppException
import com.example.domain.util.Constants

class CredentialValidator {

    fun validateEmailOrUsername(emailOrUsername: String) {
        if (emailOrUsername.isBlank() ||
                Constants.Length.Min.USERNAME > emailOrUsername.length) {
            throw AppException.InvalidException(Constants.Error.Validation.INVALID_FIELD)
        }
    }

    fun validatePassword(password: String) {
        if (password.isBlank() ||
                Constants.Length.Min.PASSWORD > password.length) {
            throw AppException.InvalidException(Constants.Error.Validation.INVALID_FIELD)
        }
    }
}