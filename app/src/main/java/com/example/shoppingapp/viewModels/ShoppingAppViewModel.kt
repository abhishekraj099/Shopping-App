package com.example.shoppingapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.useCase.CreateUserUseCas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingAppViewModel @Inject constructor(val createUserUseCas: CreateUserUseCas) :
    ViewModel() {
 private val _signUpScreenState= MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    fun createUser(userData: UserData) {
        viewModelScope.launch {
            createUserUseCas.createUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                         _signUpScreenState.value= SignUpScreenState(error = it.message)
                    }
                    ResultState.Loading -> {
                        _signUpScreenState.value= SignUpScreenState(isLoading = true)
                    }
                    is ResultState.Success -> {
                           _signUpScreenState.value=SignUpScreenState(userData =it.data )
                    }
                }


            }
        }
    }
}

data class  SignUpScreenState(
    
    val isLoading: Boolean= false,
    val error:String? = null,
    val userData: String?= null
)