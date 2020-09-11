package test;

import java.util.UUID;

/**
 * @author beimo
 * @date 2020/8/26
 */
public class UUIDTest {

    public static void main(String[] args) {
        System.out.println("13d7ab25bae54b50aeac2cace67a82".length());

        for (int i = 0; i < 2; i++) {
            String uuid = UUID.randomUUID().toString().replace("-","").substring(0,30);
            System.out.println(uuid);
            System.out.println(uuid.length());
        }

    }

}
