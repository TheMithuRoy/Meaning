package com.cognizant.meaning.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cognizant.meaning.R
import com.cognizant.meaning.domain.model.Meaning

@Composable
fun MeaningItem(meaning: Meaning) {
    Card(shape = MaterialTheme.shapes.medium) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_default))
                .fillMaxWidth()
        ) {
            Text(
                text = meaning.longForm,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_default)))
            Column {
                Text(text = stringResource(id = R.string.txt_since))
                Text(text = meaning.since, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeaningItemPreview() {
    MeaningItem(meaning = dummyMeaning)
}

val dummyMeaning = Meaning(
    longForm = "International Cricket Council",
    since = "1909"
)