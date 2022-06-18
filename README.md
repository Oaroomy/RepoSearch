# GitHub Repository Search App

입력한 키워드로 Git Repository를 검색합니다. 검색 결과는 최대 1000개까지 표시 됩니다.
  
  
  
  
## 사용 기술
- Kotlin
- MVVM
- Databinding
- Retrofit2
- Coroutine
- Paging3
- Glide
- Gson


   
## 아키텍쳐 설게
<img src="https://user-images.githubusercontent.com/45578591/174456391-906a192e-62c8-4b6c-a718-eb22e3b10160.png"  width="600" height="600"/>
  
  
## 프로젝트 구조
  

```
.
├── common
│   ├── ShareData.kt
│   └── SingleLiveEvent.kt
├── data
│   ├── GithubPagingSource.kt
│   ├── repository
│   │   └── GitApiRepository.kt
│   └── response
│       ├── Repo.kt
│       └── ResponseRepos.kt
├── model
│   └── GitRepo.kt
├── network
│   └── GitApi.kt
└── ui
    ├── BindingAdapters.kt
    ├── adapter
    │   ├── BottomLoadStateAdapter.kt
    │   ├── LoadStateViewHolder.kt
    │   └── RepoAdapter.kt
    ├── view
    │   └── MainActivity.kt
    └── viewmodel
        └── MainViewModel.kt
```
