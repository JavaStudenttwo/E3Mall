package cn.e3mall.common.jedis;

import java.util.List;

/** 
 * @Date 2018/6/9 9:17
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.common.jedis
 * @ClassName: JedisClient 
 * @Description: 
 *
 */
public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Boolean hexists(String key, String field);
	List<String> hvals(String key);
	Long del(String key);
}
