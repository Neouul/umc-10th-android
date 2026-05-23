# Week 08

## Keyword

- **지연 로딩 (Lazy Loading):**  
    화면에 지금 당장 보이는 만큼의 UI만 메모리에 로드하고, 스크롤을 내릴 때마다 필요한 부분을 추가로 로드하는 효율적인 렌더링 방식입니다.
    
- **리컴포지션 (Recomposition):**  
    데이터(상태)가 변경되었을 때, 화면을 업데이트하기 위해 컴포저블 함수를 다시 실행하는 Compose의 핵심 메커니즘입니다.
    
- **DSL (도메인 특화 언어):**  
    특정 목적(여기서는 리스트 구성)을 위해 아주 직관적이고 선언적인 형태로 코드를 짤 수 있게 만든 미니 언어 스타일입니다.

<br>
    
- **LazyListScope:**  
    `LazyColumn`이나 `LazyRow` 내부(중괄호 블록)에서만 유효한 전용 영역으로, 리스트의 아이템들을 정의할 수 있는 DSL 함수들을 제공합니다.
    
- **GridCells (그리드 셀 설정):**  
    그리드 레이아웃에서 열(Column)이나 행(Row)을 몇 개로 나눌 것인지, 혹은 크기를 어떻게 고정할 것인지 결정하는 설정 값입니다.
    
- **아이템 키 (Item Key):**  
    리스트 내의 각 아이템이 가진 '고유한 주민등록번호' 같은 값입니다. 데이터의 순서가 바뀌어도 Compose가 어떤 아이템이 어디로 이동했는지 정확히 추적할 수 있게 돕습니다.
    
- **스티키 헤더 (Sticky Header):**  
    고정 헤더를 의미합니다. 리스트를 아래로 스크롤할 때, 특정 섹션의 제목이 화면 최상단에 자석처럼 찰칵 붙어서 다음 섹션이 오기 전까지 고정되어 있는 UI 패턴입니다.
    
<br>

- **LazyListState:**  
    Lazy 컴포넌트의 현재 상태(스크롤 위치, 화면에 보이는 아이템 정보 등)를 담고 있는 '리모컨'이자 '관찰 카메라' 객체입니다.
    
- **파생된 상태 (derivedStateOf):**  
    다른 상태(`State`)를 기반으로 새로운 상태를 계산해 내는 도구입니다. 스크롤처럼 빈번하게 일어나는 변화 속에서 굳이 불필요한 리컴포지션을 막고 성능을 최적화할 때 필수적입니다.


## Summary

<details>

<summary>
  <strong>
RecyclerView vs Lazy 레이아웃의 차이 이해
  </strong>
</summary>

#### 뷰의 재사용 방식 차이
    
두 컴포넌트 모두 화면에 보이는 만큼만 효율적으로 처리하려는 목적은 같지만, 내부 메커니즘이 완전히 다릅니다.
    
 - **RecyclerView** (전통적인 View 방식): "진짜 재활용"
    - **방식:** 화면 위로 벗어나서 안 보이게 된 뷰(View) 객체를 버리지 않고 아래로 가져와, 내용(데이터)만 갈아 끼워서 다시 재사용(Recycle)합니다.
     - **특징:** 메모리에 뷰 객체를 한 번 만들어 두면 파괴하지 않고 계속 돌려 쓰기 때문에, 객체 생성 비용이 아끼고 스크롤 성능을 극대화할 수 있습니다. 이를 위해 `ViewHolder`라는 개념이 필수적입니다.
- **Lazy 레이아웃** (Compose 방식): "재생성 및 폐기"
    - **방식:** Compose는 뷰 객체라는 개념이 없고, 화면을 그리는 함수(`@Composable`)만 존재합니다. 따라서 스크롤을 내리면 화면에서 벗어난 컴포저블은 메모리에서 아예 파괴(Dispose)되고, 새로 나타나는 아이템은 그 순간 함수를 다시 실행(Recomposition)해서 화면에 그립니다.
    - **특징:** "매번 파괴하고 새로 만들면 느린 거 아냐?"라고 생각할 수 있지만, Compose의 컴포저블 함수는 일반 Java/Kotlin 객체 생성보다 **비교가 안 될 정도로 가볍게(Lightweight) 설계**되어 있어 스크롤 시 랙 없이 렌더링이 가능합니다. (단, `key`를 지정해야 이 과정이 더 최적화됩니다.)

</details>

#### **LazyColumn / LazyRow의 기본 사용법 숙지**

- 전통적인 뷰 방식의 `RecyclerView`를 대체하는 선언형 리스트 컴포넌트
- 무거운 View 객체를 재사용하는 대신, 가벼운 컴포저블 함수를 스크롤 위치에 따라 파괴하고 재생성(Disposal & Recomposition)하는 방식으로 성능을 최적화
- LazyListScope 블럭 내부에 전용 함수를 사용하여 리스트의 아이템들을 선언

#### **LazyListScope DSL의 다양한 함수**(`item`, `items`, `itemsIndexed`) 활용

> `Lazy` 레이아웃 내부(`LazyListScope`)에서 목록을 설명(Describe)하는데 쓰는 특수한 DSL 함수들
> 
- `item { ... }`
    - 단 하나의 컴포저블 아이템을 배치할 때 사용합니다.
- `items(List) { item -> ... }`
    - 대량의 데이터 리스트를 받아, 그 개수만큼 반복해서 동일한 형태의 UI 아이템을 생성합니다.
    - **주의사항:** 성능 최적화와 데이터 꼬임 방지를 위해 각 아이템을 구별할 수 있는 고유하고 변하지 않는 `key`를 지정하는 것이 권장됩니다.
- `itemsIndexed(List) { index, item -> ... }`
    - `items`와 동일하게 반복 생성하지만, 현재 몇 번째 아이템인지 나타내는 **인덱스(`index`) 정보**가 함께 필요할 때 사용합니다.

#### LazyVerticalGrid / LazyHorizontalGrid 그리드 레이아웃 이해

> 바둑판이나 갤러리 형태의 2차원 그리드 레이아웃을 지연 로딩 방식으로 구현하는 컴포넌트입니다. `LazyListScope`와 유사하게 **`LazyGridScope`** 공간 안에서 DSL 함수 사용
> 
- **`GridCells`** 설정 방식:
1. **`GridCells.Fixed(count)`**
    - 화면 크기와 관계없이 **무조건 고정된 개수**의 열/행을 만듭니다.
2. **`GridCells.Adaptive(minSize)`**
    - 아이템의 **최소 크기**를 지정해 두면, 화면 너비에 맞춰서 아이템이 들어갈 수 있는 만큼 개수를 알아서 조절(가변)합니다.

#### 아이템 Key, 애니메이션, Sticky Header 등 심화 기능 활용

- **아이템 Key의 올바른 활용**
    - **왜 쓰는가:** Key를 지정하지 않으면 Compose는 아이템의 '순서(index)'를 Key로 대신 씁니다. 이 상태에서 아이템이 추가/삭제되면 상태가 꼬이거나 엉뚱한 애니메이션이 튑니다.
    - **조건:** 반드시 **고유하고(Unique)**, 데이터가 바뀌어도 **변하지 않으며(Stable)**, Android **`Bundle`에 저장 가능한 타입**(`String`, `Long`, `Int` 등)이어야 합니다.
- **아이템 애니메이션 (`animateItem` / `animateItemPlacement`)**
    - **기능:** 리스트의 아이템이 추가되거나, 정렬이 바뀌거나, 삭제될 때 부드러운 전환 효과를 줍니다.
    - **사용법:** `items` 블록 내부의 아이템 컴포저블 `Modifier`에 `animateItem()`을 붙여줍니다. **(단, 반드시 아이템 `key`가 먼저 지정되어 있어야 작동합니다.)**
- **스티키 헤더 (`stickyHeader`)**
    - **기능:** 인스타그램의 날짜 헤더나, 주소록의 'ㄱ', 'ㄴ', 'ㄷ' 초성 타이틀처럼 스크롤 시 상단에 고정되는 뷰를 만듭니다.
    - **사용법:** `LazyListScope` 안에서 `item { ... }` 대신 `stickyHeader { ... }` 함수를 사용하여 구현합니다.

#### 스크롤 상태 제어 (`LazyListState`) 이해

> 스크롤의 위치를 코드로 직접 조종하거나, 스크롤 위치에 반응하여 UI를 동적으로 변화시키는 기술입니다.
 스크롤 리모컨을 쓸 때, 관찰할 때는 `derivedStateOf` / 명령할 때는 `CoroutineScope`
> 

```kotlin
val listState = rememberLazyListState()
val scope = rememberCoroutineScope()

// 1. 성능을 최적화하여 스크롤 위치 관찰 (Read)
val isShowTopButton by remember {
    derivedStateOf { listState.firstVisibleItemIndex > 2 }
}

// 2. 코루틴을 활용해 코드로 스크롤 제어 (Write)
if (isShowTopButton) {
    TopFloatingButton(onClick = {
        scope.launch {
            listState.animateScrollToItem(index = 0) // 맨 위로 부드럽게 이동
        }
    })
}
```

- **스크롤 위치 관찰 (Read) 패턴**
    - **기능:** 현재 사용자가 몇 번째 아이템을 보고 있는지, 위로 가는지 아래로 가는지 알아냅니다.
    - **핵심 속성:**
        - `firstVisibleItemIndex`: 현재 화면 맨 위에 보이는 첫 번째 아이템의 인덱스.
        - `firstVisibleItemScrollOffset`: 첫 번째 아이템이 위로 얼마나 밀려났는지의 픽셀 값.
    - **성능 최적화 필수 규칙:** 스크롤은 수 밀리초(ms)마다 수없이 변하므로, 이를 그대로 관찰하면 앱이 엄청나게 리컴포지션을 일으켜 버벅입니다. 따라서 반드시 `derivedStateOf`로 감싸서 "1번째 인덱스를 넘어갔는가?"라는 `Boolean` 결과만 걸러내야 합니다.
- **프로그래밍 방식의 스크롤 제어 (Write) 패턴**
    - **기능:** 사용자의 손가락 터치 외에, 버튼 클릭 등의 이벤트로 리스트를 강제 이동시킵니다.
    - **핵심 함수:**
        - `scrollToItem(index)`: 해당 인덱스로 즉시 순간 이동합니다.
        - `animateScrollToItem(index)`: 해당 인덱스로 부드럽게 스르륵 스크롤 이동합니다.
    - **주의사항:** 스크롤 제어 함수들은 모두 정지 함수(suspend function)이기 때문에, 반드시 `rememberCoroutineScope()`를 통한 코루틴 스코프(`launch`) 내부에서 실행해야 합니다.