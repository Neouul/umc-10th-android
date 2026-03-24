# Week 02

## Keyword

- 화면의 생태계
  - Activity
  - Fragment
- Lifecycle
- Intent
- Bundle


## Mission

1. Activity 생명주기 점검

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

1-1. 


1-2.


1-3.

```
onPause
onStop
onRestart
onStart  <-- 빠뜨림
onResume
```

2. 
