# Week 02

## Keyword

- 화면의 생태계
  - Activity
  - Fragment
- Lifecycle
- Intent
- Bundle


## Mission

### 1. Activity 생명주기 점검

1-1. 앱을 처음 켰을 때

```
onCreate
onStart
onResume
```

1-2. 앱이 종료되지 않고 백그라운드로 내려갔을 때

```
onPause
onStop
```

1-3. 홈 버튼을 눌러 나갔다가, 다시 앱으로 돌아왔을 때

```
onPause
onStop
onRestart
onResume
```

#### 미션1 실행 후 정답 확인

1-1. 앱 실행
<img width="1000" alt="image" src="https://github.com/user-attachments/assets/df1e6f58-1e97-49ac-b1d2-2a7e0272915d" />

1-2. adb 명령어로 전화를 걸어본 상황
<img height="40" alt="image" src="https://github.com/user-attachments/assets/5a8eaa8d-8b69-4492-8f3f-740668176507" />

<img width="1000" alt="image" src="https://github.com/user-attachments/assets/dfeb7066-b2d2-4873-b06e-7ab924a5d61a" />


1-3. 홈 버튼을 눌렀다가, 앱으로 다시 돌아온 상황
<img width="1000" alt="image" src="https://github.com/user-attachments/assets/46df01de-38ad-4af1-b718-6c24371cf9b9" />

```
onPause
onStop
onRestart
onStart  <-- 빠뜨림
onResume
```

2. 
