package cn.loveapple.service.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * サービスレイヤを表す。<br />
 * サービスの実装クラスが該当するアノテーションを指定すべき。
 * 
 * @author hao_shurni
 * @since 2011/02/17
 * @version $Revision$
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ServiceComp {

}
