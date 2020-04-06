import com.qwq.dao.AccountDao;
import com.qwq.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;

/**
 * 爬虫类
 * @Author: QWQ
 * @Date: 2019.12.14 11:39
 * @Version: 1.0
 */
public class testGet {

    /*public static void main( String[] args ) {
        ApplicationContext ap = new ClassPathXmlApplicationContext( "spring.xml" );
        AccountDao accountOperations = ap.getBean( AccountDao.class );
        User user = accountOperations.userLogin( "admin","7c4a8d09ca3762af61e59520943dc26494f8941b" );
        System.out.println( "=======================\nuser:" + user.getUserName() + "\n" + accountOperations.get( 1 ).getUserTel() );
    }*/

    public static void main( String[] args ) {
        reptile( "http://localhost:8080/login.action?userId=admin&userPwd=123" );
        reptile( "http://localhost:8080/login?userId=admin&userPwd=123" );
        //reptile( "http://localhost:8080/getGenerateVerificationCode" );
        //reptile( "http://localhost:8080/about" );
    }

    private static void reptile( String url ) {
        System.out.println( "============================================================\nUrl：" + url + "，访问结果：" );
        HttpURLConnection conn;
        try {
            URL realUrl = new URL( url );
            conn = ( HttpURLConnection )realUrl.openConnection();
            //设置post方法
            conn.setRequestMethod( "POST" );
            //不使用缓存
            conn.setUseCaches( false );
            // 发送POST请求必须设置如下两行
            conn.setDoOutput( true );
            conn.setDoInput( true );
            //读取超时时间
            //conn.setReadTimeout( 8000 );
            //连接超时时间
            //conn.setConnectTimeout( 8000 );
            //这一句很重要，设置不要302自动跳转，后面会讲解到
            conn.setInstanceFollowRedirects( false );
            conn.setRequestProperty( "User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0" );
            int code = conn.getResponseCode();
            if( code == 200 ) {
                InputStream is = conn.getInputStream();
                BufferedReader in = new BufferedReader( new InputStreamReader( is,StandardCharsets.UTF_8 ) );
                StringBuilder buffer = new StringBuilder();
                String line;
                while( ( line = in.readLine() ) != null ) {
                    buffer.append( line );
                }
                String result = buffer.toString();
                //subscriber是观察者，在本代码中可以理解成发送数据给activity
                //subscriber.onNext(result);
                if( result.contains( "<title>Struts Problem Report</title>" ) ) {
                    System.out.print( "Struts报错了：" );
                    String div = result.substring( result.indexOf( "<div id=\"exception-info\">" ),result.indexOf( "</div>" ) );
                    if( div.contains( "<ol>" ) ) {
                        System.out.println();
                        String[] errorMess = div.substring( div.indexOf( "<li>" ) + 4,div.lastIndexOf( "</li>" ) ).split( "</li>" );
                        for( int i = 1;i <= errorMess.length;i++ ) {
                            System.out.println( i + "." + errorMess[i - 1].trim().replace( "<li>","" ) );
                        }
                    } else {
                        String herd = div.substring( div.indexOf( "<li>" ) + 4 );
                        System.out.println( herd.substring( 0,herd.indexOf( "</li>" ) ) );
                    }
                } else {
                    System.out.print( "浏览器回复：" );
                    if( ! result.contains( "<" ) && ! result.contains( ">" ) ) {
                        System.out.println( result );
                    } else {
                        String herd = result.substring( result.indexOf( "<title>" ) + 4 );
                        System.out.print( "\n标题：" + herd.substring( 0,herd.indexOf( "</title>" ) ) + "\n内容：" + result.substring( result.indexOf( "<body>" ) + 6,50 ) );
                        if( result.length() > 56 ) {
                            System.out.println( "...（此处省略" + ( result.length() - 50 ) + "个字）" );
                        }
                    }
                }
            } else if( code == 404 ) {
                System.out.println( "错误码：404 Not Found" );
            } else if( code == 500 ) {
                System.out.println( "错误码：500 Internal Server Error" );
            }
            System.out.println();
        } catch( Exception e ) {
            //Url出错
            System.out.println("访问失败，连接被拒绝");
        }
    }
}
