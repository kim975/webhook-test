2024.10.10
이슈 1.
implementation 'org.apache.httpcomponents.client5:httpclient5:5.4' 실행시 아래의 에러발생하여  
implementation 'org.apache.httpcomponents.client5:httpclient5:5.3.1' 로 다운그레이드
에러로그 별도 1 참조

이슈 2.
문제 : rest Template를 이용하여 네이버 API 검색시 한글 검색 결과가 안나옴  
원인 : uriComponentsBuilder.toUriString() 시 한글이 인코딩되어서 나오는데  
Rest template에서 uri를 한번더 인코딩한
해결방법 : uriComponentsBuilder.build().toString() 로 인코딩이 안된 uri를 만들고 rest template에서 인코딩하도록 수정

별도 1
An attempt was made to call a method that does not exist. The attempt was made from the following
location:

org.apache.hc.client5.http.impl.io.ManagedHttpClientConnectionFactory.<init>(
ManagedHttpClientConnectionFactory.java:86)

The following method did not exist:

'void org.apache.hc.core5.http.impl.io.DefaultHttpRequestWriterFactory.<init>(
org.apache.hc.core5.http.config.Http1Config)'

The calling method's class, org.apache.hc.client5.http.impl.io.ManagedHttpClientConnectionFactory,
was loaded from the following location:

jar:file:/C:
/Users/c/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents.client5/httpclient5/5.4/8f5efeb3af5b5196925ea20d25c052ec9ac24d63/httpclient5-5.4.jar!
/org/apache/hc/client5/http/impl/io/ManagedHttpClientConnectionFactory.class

The called method's class, org.apache.hc.core5.http.impl.io.DefaultHttpRequestWriterFactory, is
available from the following locations:

jar:file:/C:
/Users/c/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents.core5/httpcore5/5.2.5/dab1e18842971a45ca8942491ce005ab86a028d7/httpcore5-5.2.5.jar!
/org/apache/hc/core5/http/impl/io/DefaultHttpRequestWriterFactory.class

The called method's class hierarchy was loaded from the following locations:

org.apache.hc.core5.http.impl.io.DefaultHttpRequestWriterFactory: file:/C:
/Users/c/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents.core5/httpcore5/5.2.5/dab1e18842971a45ca8942491ce005ab86a028d7/httpcore5-5.2.5.jar

Action:

Correct the classpath of your application so that it contains compatible versions of the classes
org.apache.hc.client5.http.impl.io.ManagedHttpClientConnectionFactory and
org.apache.hc.core5.http.impl.io.DefaultHttpRequestWriterFactory
