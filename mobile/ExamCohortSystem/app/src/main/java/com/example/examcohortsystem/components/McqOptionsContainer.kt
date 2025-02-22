package com.example.examcohortsystem.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examcohortsystem.model.McqOption

@Composable
fun McqOptionContainer(
    mcqOptions: List<McqOption>,
    newQuestion: Boolean = false
) {
        LazyColumn() {
            itemsIndexed(items = mcqOptions) { index: Int, item:
            McqOption ->
                val optionSelected = remember {
                    mutableStateOf(false)
                }
                if(newQuestion)optionSelected.value = false
                com.example.examcohortsystem.components.McqOption(
                    mcqOption = mcqOptions[index],
                    selected = optionSelected.value,
                    onClick = {
                        optionSelected.value = !optionSelected.value
                        mcqOptions[index].isSelectedInAnswer = optionSelected.value
                    }
                )
            }
        }
}
