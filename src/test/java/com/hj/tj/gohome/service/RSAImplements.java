package com.hj.tj.gohome.service;

import org.apache.commons.lang.math.RandomUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tangj
 * @description
 * @since 2018/9/25 18:06
 */
public class RSAImplements {

    public static void main(String[] args) {
        // 从10 开始，产生50个 质数
        List<Integer> zhiShuList = getZhiShuList(10, 50);

        Integer pIndex = RandomUtils.nextInt() % zhiShuList.size();
        Integer qIndex = pIndex;
        while (qIndex.equals(pIndex)) {
            qIndex = RandomUtils.nextInt() % zhiShuList.size();
        }

        // 随机产生两个互质的数，因为质数间彼此互质，所以随机产生下标就可以
        Integer p = zhiShuList.get(pIndex);
        Integer q = zhiShuList.get(qIndex);
        System.out.println("p:" + p);
        System.out.println("q:" + q);

        // 得到N
        Integer N = p * q;
        System.out.println("N:" + N);

        // 得到fn
        Integer fn = (p - 1) * (q - 1);
        System.out.println("fn:" + fn);

        // 产生从1开始 的100个质数
        List<Integer> fnZhiShuList = getZhiShuList(100);

        // 随机产生e
        Integer eIndex = RandomUtils.nextInt() % fnZhiShuList.size();
        Integer e = fnZhiShuList.get(eIndex);

        System.out.println("e:" + e);

        // 随机产生1 开始的1000个质数
        List<Integer> dZhiShuList = getZhiShuList(1000);

        Integer d = 0;
        for (int i = 0; i < dZhiShuList.size(); i++) {
            boolean getD = false;
            for (int k = 1; ; k++) {
                // 随机产生d的核心代码 d * e -1 == k * fn
                if (k * fn == e * dZhiShuList.get(i) - 1) {
                    getD = true;
                    System.out.println("k:" + k);
                    break;
                } else if (k * fn > e * dZhiShuList.get(i) - 1) {
                    break;
                }
            }

            if (getD) {
                d = dZhiShuList.get(i);
                break;
            }
        }

        System.out.println("d:" + d);

        if (d != 0) {
            // 找到了密钥
            System.out.println("公钥：(" + N + ", " + e + ")");
            System.out.println("私角：(" + N + ", " + d + ")");

            // 加密的数字，必须要小于fn
            Integer num = RandomUtils.nextInt() % fn;
            System.out.println("加密前：" + num);

            int protectNum = quick(num, e, N);
            System.out.println("加密后：" + protectNum);

            int upProtectNum = quick(protectNum, d, N);
            System.out.println("解密后：" + upProtectNum);

        }
    }

    private static List<Integer> getZhiShuList(Integer size) {
        return getZhiShuList(1, size);
    }

    private static List<Integer> getZhiShuList(Integer start, Integer size) {
        List<Integer> zhiShuList = new LinkedList<>();

        int count = 0;
        for (int i = start; count < size; i++) {
            boolean hasZhiShu = hasZhiShu(i);

            if (hasZhiShu) {
                zhiShuList.add(i);
                count++;
            }
        }

        return zhiShuList;
    }

    private static boolean hasZhiShu(int num) {
        boolean hasZhiShu = true;

        for (int j = 2; j < num / 2; j++) {
            if (num % j == 0) {
                hasZhiShu = false;
                break;
            }
        }

        return hasZhiShu;
    }

    /**
     * 快速幂取模   计算 (a^b) %c
     *
     * @param a
     * @param b
     * @param c
     * @return 计算结果
     */
    private static int quick(int a, int b, int c) {
        int ans = 1;   //记录结果
        a = a % c;   //预处理，使得a处于c的数据范围之下
        while (b != 0) {
            if ((b & 1) == 1) { //1即是0000000000000001，判断个位是否是1.如果b的二进制位是1，那么我们的结果是要参与运算的
                ans = (ans * a) % c;
            }
            b >>= 1;    //二进制的移位操作，相当于每次除以2，用二进制看，就是我们不断的遍历b的二进制位
            a = (a * a) % c;   //不断的加倍
        }
        return ans;
    }
}
