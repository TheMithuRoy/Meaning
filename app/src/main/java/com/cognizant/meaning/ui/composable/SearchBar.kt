package com.cognizant.meaning.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cognizant.meaning.R

@Composable
fun SearchBar(@StringRes hint: Int = R.string.et_search_hint) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        label = { Text(stringResource(id = hint)) },
        value = "",
        onValueChange = {})
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar()
}