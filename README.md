# **HGU-chapeler-checker**

코로나 19로 인해 20-1학기 1~6주차는 [한동대학교회 유튜브 채널](https://www.youtube.com/channel/UCWgLEuiHGxyQ6sqL0EE-P0g)을 통해 채플을 드립니다. 이 기간동안 각 팀의 새섬 혹은 팀장님께서 채플을 수강하는 팀원들이 설교 영상에 남긴 댓글들을 일일이 확인하여 출석체크를 해야 합니다.</br></br>하지만 채플 수강생이 약 500명 가량 되는 RC도 있는데 매주 수백개의 댓글들을 일일이 확인하는 것은 아무리 섬김의 자리라지만 이건 너무 고된 일이라고 생각했습니다.</br></br>그래서 각 팀의 새섬 혹은 팀장님들의 수고를 조금이라도 덜고자 팀원들의 댓글을 자동으로 수집해서 엑셀 파일로 저장해주는 프로그램인 HGU-chapeler-checker를 개발했습니다. </br></br>이 프로그램이 제대로 동작하기 위해서는 각 팀의 새섬 혹은 팀장님들께서는 팀원분들께 아래와 같이 부탁하여야 합니다.

## **(필독!) 각 팀의 새섬/ 팀장님께서 팀원분들께 부탁해야 할 한 가지!**

[62388번 히즈넷 일반공지](https://hisnet.handong.edu/myboard/read.php?id=121303&Page=1&Board=NB0001&FindIt=subject&FindText=%C3%A4%C7%C3)에 따르면 아래와 같은 형식으로 댓글을 작성해야 합니다.
> 각 팀별 특정 키워드/ 학번(8자리)/출석</br>
> Ex. 뻐꾸기/22000001/출석

그래서 각 팀의 새섬 혹은 팀장님들은 각 팀별 특정 키워드를 정해주시길 바랍니다.</br>
!!! 이때 같은 채플을 수강하는 다른 팀들과 겹치지 않도록 **고유한** 키워드를 선정하시길 바랍니다. !!!

***

##  Version history
Ver 1.0 (released 2020. 03. 01)
- SHA-1 :  e9b4a2e53abbc29f9648660a0cfb750838a5ded5
- Remarks : None

Ver 1.1 (released 2020. 03. 02)
  - SHA-1 : 6485508623aa9875228236b3a41b81de40011ef6
  - Remarks : [Hisnet 공지](https://hisnet.handong.edu/myboard/read.php?id=121303&Page=1&Board=NB0001&FindIt=subject&FindText=%C3%A4%C7%C3)에서 언급한 형식으로 댓글 작성하도록 코드 수정

***

## Future works
**1. 팀 키워드에 오타가 있어도 유연하게 댓글 수집하는 모드 추가 (Using Normalized Levenshtein edit distance)**

유튜브 댓글에 아래와 같이 팀 키워드를 잘못 작성하는 경우도 있을 것이라고 생각합니다.

> 올바른 댓글 입력 예시 </br>
> 태연 / 21000000 / 출석

> 잘못된 댓글 예시 </br>
> 테연 / 21000000 / 출석

그래서 Normalized Levenshtein edit distance 같은 metric을 사용하여 문자열 간 유사도를 측정한 후 특정 threshold값을 넘으면 즉, 약간의 오타라고 판단되면 해당 댓글도 수집하는 f (flexible) 옵션을 추가할 것입니다. </br>

f option을 추가할 경우 오탐지(False Positive) 가 분명히 생길 것입니다. 그래서 f option은 사용자가 선택적으로 입력하는 옵션이어야 하며 아래와 같이 threshold을 넣으면 해당 threshold 값대로 실행하고 안 넣을 경우는 default 값으로 threshold가 설정되어야 할 것입니다.

> -f (th) [threshold 값은 넣어도 되고 안 넣으면 default 값]

그리고 normalized니까 [0, 1]의 값이 아니면 help 메시지 띄우도록 해야 합니다.

- 구현을 위해 참고해야 할 링크들</br>
[Levenshtein edit distance](http://rosettacode.org/wiki/Levenshtein_distance#Java)</br>
[Normalized Levenshtein edit distance](https://github.com/tdebatty/java-string-similarity)

**2. 댓글 작성이 종료됨을 알리는 댓글 이후의 댓글들은 수집하지 않도록 코드 수정**

[62388번 히즈넷 일반공지](https://hisnet.handong.edu/myboard/read.php?id=121303&Page=1&Board=NB0001&FindIt=subject&FindText=%C3%A4%C7%C3)에 따르면 댓글 작성이 종료됨을 알리는 댓글 이후의 댓글들은 출석 불인정으로 처리한다고 합니다. 하지만 현재 코드에는 댓글 작성이 종료됨을 알리는 댓글 이후의 댓글들을 수집하지 않도록 하는 기능이 없습니다. 따라서 이 기능을 구현해야 합니다.


**3. HGU-chpeler-checker web 제작**

***

## 시작하기

### 전제조건 (Prerequisites)
1. JDK/ JRE </br>(사실 버전을 안 알아봤습니다. 8 이상이면 다 되지 않을까 싶은데... 혹시 안 되면 아래 Bug Report에 적힌 이메일 주소로 연락 바랍니다.)

2. [Chrome](https://www.google.com/intl/ko/chrome/)

3. [Chrome driver](https://sites.google.com/a/chromium.org/chromedriver/)

4. [Gradle](https://gradle.org/install/)

### 빌드하기 (Build)

>./gradlew distZip

or

>gradle distZip

After executing above command, unzip build/distributions/HGU-chapeler-checker.zip</br>
You can find the executable file located in build/distributions/HGU-chapeler-checker/bin.

### 사용법 (Usage)

* 실행하기

> ./HGU-chapeler-checker -u [YOUTUBE_URL] -p [CHROME_DRIVER_PATH] -n [TEAM_NAME]

* Help message 보기

> ./HGU-chapeler-checker -h

### 예제 (Example)

* 일반적인 실행 예제

+ Mac

> ./HGU-chapeler-checker -u 'https://www.youtube.com/watch?v=vW6dzGwpSTM' -p /Users/kimseokjin/Downloads/chromedriver -n 아이들

or

> ./HGU-chapeler-checker -u https://www.youtube.com/watch?v=vW6dzGwpSTM -p /Users/kimseokjin/Downloads/chromedriver -n 아이들

**> u 옵션을 넣을 때 따옴표를 넣어야 되는 줄 알았는데 안 넣어도 실행된다는 소식을 들었습니다. 그래서 일단 넣고 실행해보시고 안 되면 뺴고 실행해보시길 바랍니다!**

+ Windows

> ./HGU-chapeler-checker.bat -u 'https://www.youtube.com/watch?v=vW6dzGwpSTM' -p /Users/kimseokjin/Downloads/chromedriver -n 아이들

**> Windows가 연구실에 있는데 제가 본가로 올라온 상황이라 테스트를 못 해봤습니다. 그래서 확실하진 않습니다. 죄송합니다...**

### 사용법 동영상 (Usage Video)
* Mac
</br> Youtube Link here

* Windows
</br> Coming soon...

### Bug Report
<21700105@handong.edu>로 "Bug in HGU-chapeler-checker"라는 제목으로 메일 보내주세요

***
In His Love</br>
<><</br>
by ALiNew
