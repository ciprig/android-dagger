/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.dagger.LiveDataTestUtil
import com.example.android.dagger.user.UserManager
import com.google.common.truth.Truth.assertThat
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val userManager = mockk<UserManager>()
    private val viewModel = LoginViewModel(userManager)

    @Test
    fun `Get username`() {
        every { userManager.username } returns "Username"

        val username = viewModel.getUsername()

        assertThat(username).isEqualTo("Username")
    }

    @Test
    fun `Login emits success`() {
        every { userManager.loginUser(any(), any()) } returns true

        viewModel.login("username", "login")

        assertThat(LiveDataTestUtil.getValue(viewModel.loginState)).isEqualTo(LoginSuccess)
    }

    @Test
    fun `Login emits error`() {
        every { userManager.loginUser(any(), any()) } returns false

        viewModel.login("username", "login")

        assertThat(LiveDataTestUtil.getValue(viewModel.loginState)).isEqualTo(LoginError)
    }

    @Test
    fun `Login unregisters`() {
        every { userManager.unregister() } returns Unit

        viewModel.unregister()


        //verify { userManager.unregister() }

        confirmVerified(userManager)
    }
}
