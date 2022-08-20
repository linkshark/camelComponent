package com.linkjb.camelcomponent.demo;

import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName NullPointerExceptionCoffee
 * @Description NullPointerExceptionCoffee点餐
 * @Author 集成平台研发部-沈超琦
 * @Data 2022/7/21 20:59
 **/
public class NullPointerExceptionCoffee {
    private static final List<Coffee> coffeeMenu = new ArrayList<>();
    private static final Map<Coffee, Integer> orderedCoffee = new HashMap<>();
    private static Integer finalInCome = 0;
    private static Integer customerCount = 0;
    private static Integer reardCount = 5;


    static {
        coffeeMenu.add(new Coffee("美式咖啡", 9));
        coffeeMenu.add(new Coffee("拿铁", 11));
        coffeeMenu.add(new Coffee("生椰拿铁", 12));
        coffeeMenu.add(new Coffee("燕麦拿铁", 12));
        coffeeMenu.add(new Coffee("汤力水美式", 12));
        coffeeMenu.add(new Coffee("dirtyCoffee", 13));
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        welcome(true);
        while (in.hasNext()) {
            String incomeLine = in.nextLine();
            try {
                if (incomeLine.equals("0")) {
                    System.out.println("😄-----当前已售出coffee与数量-----😄");
                    orderedCoffee.forEach((key, value) -> System.out.println(key + " : " + value + " 杯"));
                    welcome(false);
                    continue;
                }
                if (incomeLine.contains("+") || incomeLine.contains("*")) {//组合下单
                    Integer singleCount = 0;
                    String[] split = incomeLine.split("[+]");
                    StringBuilder sb = new StringBuilder(customerCount + " 号顾客点单 ");
                    Map<Integer, Integer> map = new HashMap<>();
                    Integer totalCount = 0;
                    for (String single : split) {
                        if (single.contains("*")) {//批量下单
                            String[] tempSplit = single.split("[*]");
                            int i = Integer.parseInt(tempSplit[0]) - 1;
                            int count = Integer.parseInt(tempSplit[1]);
                            totalCount += count;
                            sb.append(coffeeMenu.get(i).getName()).append(" ").append(count).append(" 杯;");
                            singleCount += coffeeMenu.get(i).getPrice() * count;
                            map.merge(i, count, Integer::sum);
                        } else {//单笔下单
                            int i = Integer.parseInt(single) - 1;
                            totalCount++;
                            sb.append(coffeeMenu.get(i).getName()).append(" 一杯;");
                            singleCount += coffeeMenu.get(i).getPrice();
                            map.merge(i, 1, Integer::sum);
                        }
                    }
                    sb.append("收入 ").append(singleCount).append(" 元;");
                    finalInCome = finalInCome + singleCount;
                    sb.append("总收入 ").append(finalInCome).append(" 元;");
                    map.forEach((key, value) -> orderedCoffee.merge(coffeeMenu.get(key), value, Integer::sum));
                    luckyDraw(totalCount);
                    System.out.println(sb);
                } else {
                    int incomeNumber = Integer.parseInt(incomeLine);
                    Coffee coffee = coffeeMenu.get(--incomeNumber);
                    finalInCome += coffee.getPrice();
                    orderedCoffee.merge(coffee, 1, Integer::sum);
                    System.out.println(customerCount + " 号顾客点单 " + coffee.getName() + " 一杯" + " 收入 " + coffee.getPrice() + "元 总收入 " + finalInCome + " 元");
                    luckyDraw(1);
                }
                Thread.sleep(1000);//优化用户体验
                welcome(true);
            } catch (Exception e) {
                System.out.println("😭!!!!!!错误: 输入错误序号请重新输入!😭");
                Thread.sleep(1000);
                welcome(false);
            }
        }
    }

    static void luckyDraw(Integer coffeCount) throws InterruptedException {
        if (rewardCount > 0) {
            Thread.sleep(1000);
            Boolean lucklag = false;
            for (int i = 0; i < coffeCount; i++) {
                int num = (int) (Math.random() * 100) + 1;
                if (num <= 15) {
                    lucklag = true;
                    break;
                }
            }
            if (lucklag) {
                System.out.println("*******恭喜恭喜恭喜中奖************");
                rewardCount--;
            }
        }

    }

    static void welcome(Boolean flag) {
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        if (flag) {
            customerCount++;
            System.out.println("☺感谢您光顾NullPointerExceptionCoffee,您是本店第 " + customerCount + " 位顾客!☺️");
        }
        AtomicInteger tempIndex = new AtomicInteger(0);
        coffeeMenu.forEach(s -> System.out.println(tempIndex.incrementAndGet() + "- " + s.getName() + ": " + s.getPrice() + "元"));
        System.out.println("😄请输入您所需coffee的序号......😄");
    }
}


@Data
class Coffee {
    private String name;
    private Integer price;

    public Coffee(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
