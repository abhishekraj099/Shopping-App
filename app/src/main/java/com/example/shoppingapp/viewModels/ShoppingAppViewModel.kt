package com.example.shoppingapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.ProductDataModels
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.models.UserDataParent
import com.example.shoppingapp.domain.useCase.CreateUserUseCase
import com.example.shoppingapp.domain.useCase.GetAllProductsUseCase
import com.example.shoppingapp.domain.useCase.GetUserByIdUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserByIdUserCase: GetUserByIdUserCase,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpScreenUiState())
    val uiState: StateFlow<SignUpScreenUiState> = _uiState.asStateFlow()

    private val _profileUiState = MutableStateFlow(ProfileScreenUiState())
    val profileUiState: StateFlow<ProfileScreenUiState> = _profileUiState.asStateFlow()

    private val _productUiState = MutableStateFlow(ProductScreenUiState())
    val productUiState: StateFlow<ProductScreenUiState> = _productUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllProducts()
        }
    }

    fun createUser(userData: UserData) {
        viewModelScope.launch {
            createUserUseCase.createUser(userData).collect {
                when (it) {
                    is ResultState.Success -> {
                        _uiState.value = _uiState.value.copy(success = it.data, isLoading = false)
                    }
                    is ResultState.Error -> {
                        _uiState.value = _uiState.value.copy(error = it.message, isLoading = false)
                    }
                    ResultState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun loginUser(userData: UserData) {
        viewModelScope.launch {
            createUserUseCase.loginUser(userData).collect {
                when (it) {
                    is ResultState.Success -> {
                        _uiState.value = _uiState.value.copy(success = it.data, isLoading = false)
                    }
                    is ResultState.Error -> {
                        _uiState.value = _uiState.value.copy(error = it.message, isLoading = false)
                    }
                    ResultState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.getAllProducts().collect {
                when (it) {
                    is ResultState.Success -> {
                        Log.d("ShoppingAppViewModel", "Products fetched successfully: ${it.data}")
                        _productUiState.value = _productUiState.value.copy(productList = it.data, isLoading = false)
                    }
                    is ResultState.Error -> {
                        Log.e("ShoppingAppViewModel", "Error fetching products: ${it.message}")
                        _productUiState.value = _productUiState.value.copy(error = it.message, isLoading = false)
                    }
                    ResultState.Loading -> {
                        Log.d("ShoppingAppViewModel", "Loading products...")
                        _productUiState.value = _productUiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun getUserById(uid: String) {
        viewModelScope.launch {
            getUserByIdUserCase.getUserById(uid).collect {
                when (it) {
                    is ResultState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            userDataParent = it.data,
                            isLoading = false
                        )
                    }
                    is ResultState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(error = it.message, isLoading = false)
                    }
                    ResultState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun resetState() {
        _uiState.value = SignUpScreenUiState()
        _profileUiState.value = ProfileScreenUiState()
        _productUiState.value = ProductScreenUiState()
    }
}

data class SignUpScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: UserData? = null,
    val success: String? = null,
)

data class ProfileScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userDataParent: UserDataParent? = null,
)

data class ProductScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val productList: List<ProductDataModels>? = null,
)