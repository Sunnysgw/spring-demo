package redis;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class DCLDemo {

    public static void main(String[] args) {
        Object o = new Object();
        if (o == null) {
            synchronized (DCLDemo.class) {
                if (o == null) {
                    o = new Object();

                }
            }
        }
    }

}
