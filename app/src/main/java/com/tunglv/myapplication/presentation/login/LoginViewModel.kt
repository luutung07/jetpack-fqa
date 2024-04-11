package com.tunglv.myapplication.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    companion object {
        private const val MIN_LENGTH_PASSWORD = 8
        private const val MAX_LENGTH_PASSWORD = 50
    }

    private var _errorAccountState = mutableStateOf(ERROR_TYPE.NO_ERROR)
    val errorAccountState = _errorAccountState

    private var _errorPassWordState = mutableStateOf(ERROR_TYPE.NO_ERROR)
    val errorPassWordState = _errorPassWordState

    private var _errorConfirmPassWordState = mutableStateOf(ERROR_TYPE.NO_ERROR)
    val errorConfirmPassWordState = _errorConfirmPassWordState

    init {

    }

    fun validate(inputAccount: String, inputPassword: String, inputConfirmPassword: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            _errorAccountState.value = ERROR_TYPE.NO_ERROR
            _errorPassWordState.value = ERROR_TYPE.NO_ERROR
            _errorConfirmPassWordState.value = ERROR_TYPE.NO_ERROR

            if (inputConfirmPassword != null && inputPassword != inputConfirmPassword) {
                _errorConfirmPassWordState.value = ERROR_TYPE.CONFIRM_PASS_ERROR
            }

            if (inputPassword.length < MIN_LENGTH_PASSWORD || inputPassword.length > MAX_LENGTH_PASSWORD) {
                _errorPassWordState.value = ERROR_TYPE.MAX_LENGTH_ERROR
            }
            if (inputConfirmPassword != null){
                if (inputConfirmPassword.length < MIN_LENGTH_PASSWORD || inputConfirmPassword.length > MAX_LENGTH_PASSWORD) {
                    _errorConfirmPassWordState.value = ERROR_TYPE.MAX_LENGTH_ERROR
                }
            }

            if (inputAccount.isEmpty()) {
                _errorAccountState.value = ERROR_TYPE.EMPTY_ERROR
            }
            if (inputPassword.isEmpty()) {
                _errorPassWordState.value = ERROR_TYPE.EMPTY_ERROR
            }
            if (inputConfirmPassword?.isEmpty() == true) {
                _errorConfirmPassWordState.value = ERROR_TYPE.EMPTY_ERROR
            }
        }
    }

}
