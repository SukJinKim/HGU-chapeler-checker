# **HGU-chapeler-checker**

코로나 19로 인해 20-1학기 1~6주차는 [한동대학교회 유튜브 채널](https://www.youtube.com/channel/UCWgLEuiHGxyQ6sqL0EE-P0g)을 통해 채플을 드립니다. 이 기간동안 각 팀의 새섬 혹은 팀장님께서 채플을 수강하는 팀원들이 설교 영상에 남긴 댓글들을 일일이 확인하여 출석체크를 해야 합니다.</br></br>하지만 채플 수강생이 약 500명 가량 되는 RC도 있는데 매주 수백개의 댓글들을 일일이 확인하는 것은 고된 일이라고 생각했습니다.</br></br>그래서 각 팀의 새섬 혹은 팀장님들의 수고를 조금이라도 덜고자 팀원들의 댓글을 자동으로 수집해서 엑셀 파일로 저장해주는 프로그램인 HGU-chapeler-checker를 개발했습니다. </br></br>이 프로그램이 제대로 동작하기 위해서는 각 팀의 새섬 혹은 팀장님들께서는 팀원분들께 아래와 같이 부탁하여야 합니다.

## **(필독!) 각 팀의 새섬/ 팀장님께서 팀원분들께 부탁해야 할 한 가지!**

[20-1 채플 관련 히즈넷 공지](https://hisnet.handong.edu/myboard/read.php?id=121185&Page=1&Board=NB0001)에 따르면 학부생의 경우 아래와 같이 댓글을 작성해야 합니다.
>학번(8자리)/출석</br>
> Ex. 22000001/출석

하지만 이렇게 작성할 경우 수백개의 댓글들 중 어떤 댓글을 수집해야 하는가에 대한 기준을 세울 수 없습니다.</br>
그래서 각 팀의 새섬/ 팀장님들께서는 팀원분들께 반드시 아래와 같은 형식으로 댓글을 작성해달라고 부탁해주시길 바랍니다.

>[교수님 성함] 교수님 팀/ 학번(8자리)/ 이름/ 출석</br>
>Ex. 손양원 교수님 팀/ 22000000/ 김벧엘/ 출석

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

> ./HGU-chapeler-checker -u [YOUTUBE_URL] -p [CHROME_DRIVER_PATH] -n [PROFESSOR_NAME]

* 네트워크 환경이 나쁠 때 pause 시간을 직접 설정하여 실행하기

> ./HGU-chapeler-checker -u [YOUTUBE_URL] -p [CHROME_DRIVER_PATH] -n [PROFESSOR_NAME] -t [PAUSE_TIME]

* Help message 보기

> ./HGU-chapeler-checker -h

### 예제 (Example)

* 일반적인 실행 예제

> ./HGU-chapeler-checker -u 'https://www.youtube.com/watch?v=vW6dzGwpSTM' -p /Users/kimseokjin/Downloads/chromedriver -n 아이들

* 5초동안 pause하면서 실행하는 예제

> ./HGU-chapeler-checker -u 'https://www.youtube.com/watch?v=vW6dzGwpSTM' -p /Users/kimseokjin/Downloads/chromedriver -n 아이들 -t 5

***Note that Youtube URL should be quoted!***

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
