package com.neouul.umc10android.example.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.example.component.pager.ImageGallery
import com.neouul.umc10android.example.component.pager.PagerDotIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val images = listOf<String>(
        "https://cdn.pixabay.com/photo/2019/09/16/20/21/cat-cat-4481997_1280.jpg",
        "https://cdn.pixabay.com/photo/2022/03/27/11/23/cat-7094808_1280.jpg",
        "https://cdn.pixabay.com/photo/2023/06/22/15/17/cat-8081701_1280.jpg",
        "https://cdn.pixabay.com/photo/2012/10/12/17/12/cat-61079_1280.jpg",
        "https://cdn.pixabay.com/photo/2018/03/26/02/05/cat-3261420_1280.jpg",
        "https://cdn.pixabay.com/photo/2019/05/08/21/21/cat-4189697_1280.jpg",
    )

    val scope = rememberCoroutineScope()

    var showSheet by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = { images.size })
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ImageGallery(
                pagerState = pagerState,
                images = images,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = pagerState.currentPage > 0
                ) {
                    Text(
                        text = "<<"
                    )
                }

                Spacer(Modifier.weight(1f))
                PagerDotIndicator(
                    pagerState = pagerState,
                    pageCount = images.size,
                )
                Spacer(Modifier.weight(1f))

                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    enabled = pagerState.currentPage < images.size - 1
                ) {
                    Text(
                        text = ">>"
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // 바텀시트 버튼
            Button(onClick = { showSheet = true }) { Text("Show ModalBottomSheet") }

            Spacer(Modifier.height(8.dp))

            // 다이얼로그 버튼
            Button(onClick = { openDialog = true }) { Text("AlertDialog") }
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            SheetContent(
                onConfirm = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { 
                            if (!sheetState.isVisible) {
                                showSheet = false 
                            }
                        }
                }
            )
        }
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Text("위시리스트에 추가") },
            text = { Text("이 상품을 위시리스트에 추가하시겠습니까?") },
            confirmButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("추가")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) { Text("취소") }
            }
        )
    }
}

@Composable
fun SheetContent(onConfirm: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("이것은 바텀 시트 내용입니다.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onConfirm) {
            Text("닫기")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}