# UMC 10th Android - Week 10 Mission

## 프로젝트 개요
이 프로젝트는 UMC(University MakeUs Challenge) 10기 안드로이드 파트 10주차 미션 결과물입니다. Jetpack Compose, Retrofit2, Room DB, Dagger Hilt 등을 활용하여, 원격 데이터와 로컬 DB를 연동하는 쇼핑 앱을 구현하였습니다. MVI/MVVM 형태의 상태 관리 패턴과 Clean Architecture 스타일의 아키텍처를 도입하여 유지보수와 확장성을 극대화하였습니다.

## 주요 기술 스택
- **Language**: Kotlin 2.2.21
- **UI Framework**: Jetpack Compose (Material 3)
- **Dependency Injection**: Dagger Hilt
- **Local Database**: Room DB
- **Network**: Retrofit2 (with Gson Converter, OkHttp Logging Interceptor)
- **Asynchronous / Reactive Programming**: Kotlin Coroutines & Flow
- **Navigation**: Jetpack Navigation Compose (Type-safe navigation with Kotlin Serialization)
- **Secrets Management**: Secrets Gradle Plugin (BuildConfig API Key 관리)
- **Image Loading**: Coil 3 (with OkHttp Network Support)
- **Dependency Management**: Gradle Kotlin DSL (Version Catalog)
- **Serialization**: Kotlinx Serialization (for Type-safe Navigation)
- **Architecture**: Clean Architecture 스타일 (Domain, Data, Presentation 레이어 분리)

## 프로젝트 구조
- `app/src/main/java/com/neouul/umc10android/week10`
    - `core`: 앱 공통 핵심 라이브러리 및 설정
        - [base](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/base): MVI/MVVM 상태 관리를 위한 [BaseViewModel.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/base/BaseViewModel.kt), [UiState.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/base/UiState.kt) 정의
        - [di](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/di): Hilt 모듈들 정의 ([NetworkModule.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/di/NetworkModule.kt), [DatabaseModule.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/di/DatabaseModule.kt) 등)
        - [routing](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing): Type-safe 내비게이션 처리를 위한 [NavigationRoot.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing/NavigationRoot.kt) 및 [Route.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing/Route.kt)
    - `data`: 데이터 소스 및 리포지토리 구현부
        - `data_source`: 로컬 Room DB(dao, entity) 및 원격 Retrofit API(api, RemoteDataSource) 데이터 소스 구현
        - `dto`: 네트워크/원격 데이터 전달 모델 (ProductDto, UserDto 등)
        - `mapper`: 데이터 모델을 도메인 모델로 변환 (ProductMapper 등)
        - `repository`: 도메인의 리포지토리 인터페이스 구현체 ([ProductRepositoryImpl.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/data/repository/ProductRepositoryImpl.kt) 등)
    - `domain`: 비즈니스 로직 및 인터페이스
        - `model`: 핵심 비즈니스 엔티티 모델 (Product, User)
        - `repository`: 비즈니스 규칙이 의존하는 인터페이스 ([ProductRepository.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/domain/repository/ProductRepository.kt) 등)
    - `presentation`: UI 레이어
        - `component`: 재사용 가능한 UI 컴포넌트 ([MainBottomNavigationBar.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/presentation/component/MainBottomNavigationBar.kt) 등)
        - `screen`: 각 화면 구현부 (cart, detail, home, main, profiie, shop, splash, wish). MVI 구조의 Root-Screen-State-ViewModel 구조 지향.
    - `ui`: 테마, 컬러, 폰트 정의

## 빌드 및 실행
- **빌드**: `./gradlew assembleDebug`
- **테스트**: `./gradlew test` (Unit Test), `./gradlew connectedAndroidTest` (UI Test)
- **실행**: Android Studio를 통해 실행하거나 `./gradlew installDebug` 명령어를 사용합니다.

## 개발 컨벤션 및 아키텍처 가이드
1. **단방향 데이터 흐름 (UDF) & MVI 패턴**
   - 각 화면은 `UiState`를 정의하고 `BaseViewModel`을 구현하는 뷰모델을 갖습니다.
   - `Root` 컴포저블은 `hiltViewModel()`을 통해 뷰모델을 주입받고, `collectAsStateWithLifecycle()`로 상태를 구독합니다.
   - `Screen` 컴포저블은 순수 상태(UI State)만을 파라미터로 받아 화면을 그리는 Stateless 컴포저블로 설계하여 Compose Preview와 테스트가 쉽도록 작성합니다.
2. **의존성 주입 (Dagger Hilt)**
   - Hilt를 사용하여 모듈 및 뷰모델에 `@Inject`를 적용합니다.
   - API 통신 시 목적에 맞춰 `@Qualifier`를 사용해 Retrofit/OkHttp 인스턴스를 분기하여 주입합니다 (예: `@ReqResClient`, `@ProductClient`).
3. **데이터 소스 및 영속성 관리**
   - 로컬 데이터는 Room DB를, 원격 데이터는 Retrofit API를 통합니다.
   - 네트워크 및 로컬 DB 객체는 외부 노출 없이 `DataSource`를 거쳐 `Repository`에서 병합 및 동기화(`syncProducts`) 과정을 거칩니다.
   - 네트워크 응답을 받는 DTO 객체나 DB 테이블에 매핑되는 Entity 객체는 반드시 매퍼(`mapper`)를 거쳐 Domain Model(`Product` 등)로 변환해 레이어 간 독립성을 유지합니다.
4. **내비게이션**
   - [Route.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing/Route.kt)의 `@Serializable` 객체들을 이용해 Type-safe 내비게이션을 지원합니다.
   - 메인 내비게이션 그래프는 [NavigationRoot.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing/NavigationRoot.kt)에서 집중 관리하며, 하단 탭 영역은 중첩 그래프(`MainGraph` 등)로 구성하여 상태 관리를 효율화합니다.

## 주요 파일
- [MainActivity.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/MainActivity.kt): 앱의 최초 실행 및 네비게이션 진입점
- [MyApplication.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/MyApplication.kt): Hilt 앱 클래스 (`@HiltAndroidApp`)
- [NavigationRoot.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/routing/NavigationRoot.kt): 메인 내비게이션 및 중첩 그래프 정의
- [NetworkModule.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/di/NetworkModule.kt): API 통신을 위한 Retrofit / OkHttp 의존성 설정 모듈
- [DatabaseModule.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/core/di/DatabaseModule.kt): Room 데이터베이스 생성 및 Dao 제공 모듈
- [Theme.kt](file:///E:/Documents/Coding/Android/UMC/umc-10th-android/week10/mission/app/src/main/java/com/neouul/umc10android/week10/ui/theme/Theme.kt): 앱의 전역 테마 및 스타일 가이드
