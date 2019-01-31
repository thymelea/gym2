import com.example.gym2.dao.RedisDao;
import com.example.gym2.nettyDemo.server.NettyServer;
import  redis.clients.jedis.Jedis;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 15:44
 * @Version 1.0
 */
public class Trsrt {

    static String constr = "198.181.46.66" ;
    public static Jedis getRedis(){
        Jedis jedis = new Jedis(constr) ;
        jedis.auth("luo893589");
        return jedis ;
    }
    public static void main(String[] args){
//        Jedis j = Trsrt.getRedis() ;
//        String output ;
//        j.set( "hello", "world" ) ;
//        output = j.get( "hello") ;
//        System. out.println(output) ;
        new NettyServer(8699).run();
    }
}
