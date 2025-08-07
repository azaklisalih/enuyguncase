package com.example.enuyguncase.presentation.checkout.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    phone: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onBack: () -> Unit,
    onPay: () -> Unit
) {


    val phoneDigitsOnly = phone.replace("\\D".toRegex(), "")
    val isPhoneValid = phoneDigitsOnly.length in 10..11

    val isNameError = name.isBlank()
    val isEmailError =
        email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPhoneError = phone.isBlank() || !isPhoneValid


    val isFormValid = name.isNotBlank() &&
            email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            phone.isNotBlank() &&
            isPhoneValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = StringResource.checkoutTitle(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = StringResource.commonBack()
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Surface)
            )
        },
        containerColor = Surface,
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                label = { Text(StringResource.checkoutName()) },
                singleLine = true,
                isError = isNameError,
                shape = RoundedCornerShape(12.dp)
            )

            if (isNameError) {
                Text(
                    text = StringResource.checkoutNameRequired(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                label = { Text(StringResource.checkoutEmail()) },
                singleLine = true,
                isError = isEmailError,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            if (isEmailError) {
                Text(
                    text = if (email.isBlank()) StringResource.checkoutEmailRequired() else StringResource.checkoutEmailInvalid(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                label = { Text(StringResource.checkoutPhone()) },
                singleLine = true,
                isError = isPhoneError,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            if (isPhoneError) {
                Text(
                    text = if (phone.isBlank()) StringResource.checkoutPhoneRequired() else StringResource.checkoutPhoneInvalid(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(Modifier.height(32.dp))


            Button(
                onClick = onPay,
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF2196F3) else Color(0xFFBDBDBD),
                    disabledContainerColor = Color(0xFFBDBDBD)
                )
            ) {
                Text(
                    StringResource.checkoutConfirmOrderButton(),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isFormValid) Color.White else Color(0xFF757575)
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
