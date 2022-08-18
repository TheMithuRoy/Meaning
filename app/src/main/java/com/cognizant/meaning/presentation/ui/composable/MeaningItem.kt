package com.cognizant.meaning.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cognizant.meaning.domain.model.Meaning
import com.cognizant.meaning.R

@Composable
fun MeaningItem(meaning: Meaning) {
    Card(shape = RoundedCornerShape(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = meaning.longForm,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
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