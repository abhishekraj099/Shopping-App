package com.example.shoppingapp.data.repo

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.UserData
import com.example.shoppingapp.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(var firebaseAuth: FirebaseAuth) : Repo {
    override fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)
            firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("User Registered Successfully"))
                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception!!.localizedMessage.toString()))
                        }
                    }


                }

            awaitClose {
                close()
            }
        }
}