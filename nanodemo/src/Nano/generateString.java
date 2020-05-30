package Nano;

import java.util.Random;

/*
随机生成指定长度的包含大小写字母及数字的字符串
 */
public class generateString {
    public static String getStringRandom(int length){
        StringBuffer val =new StringBuffer();
        Random random=new Random();
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2)%2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));

            } else {

                val.append(String.valueOf(random.nextInt(10)));

            }

        }

        return val.toString();
    }

}
