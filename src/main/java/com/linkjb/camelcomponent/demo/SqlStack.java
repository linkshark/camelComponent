package com.linkjb.camelcomponent.demo;

/**
 * @ClassName SqlStack
 * @Description TODO
 * @Author shark
 * @Data 2022/7/28 14:34
 **/

/**
 * Created by Guanzhong Hu
 * <p>
 * Date :2019/12/28
 * <p>
 * Description :栈的JAVA表示，先进后出，后进先出
 * <p>
 * Version :1.0
 */

interface IStack {

// 置空栈

    public void clear();

    public boolean isEmpty();// 判断是否为空，top=0；

    public int length();// length = top

    public Object peek();//读取栈内元素，并返回值，若为空则返回null

    public void push(Object x) throws Exception;//入栈操作，将x元素压入栈顶

    public Object pop();// 删除并返回栈顶元素

    public void display();

}


/**
 * Created by Guanzhong Hu
 * <p>
 * Date :2019/12/28
 * <p>
 * Description : 栈的实现
 * <p>
 * Version :1.0
 */

public class SqlStack implements IStack {

    private Object[] stackElem; //对象数组

    private int top;// 在非空栈时，top始终指向栈顶元素的下一个存储位置，当栈空时，top = 0

    public SqlStack(int maxSize) {

        top = 0;

        stackElem = new Object[maxSize];

    }

    @Override

    public void clear() {

        top = 0;

    }

    @Override

    public boolean isEmpty() {

        return top == 0;

    }

    @Override

    public int length() {

        return top;

    }

    @Override

    public Object peek() {

        if (!isEmpty()) {

            return stackElem[top - 1];

        } else {

            return null;

        }

    }

    @Override

    public void push(Object x) throws Exception {

        if (top == stackElem.length) throw new Exception("栈内元素已满");

        stackElem[top] = x;

        top++;

    }

    @Override

    public Object pop() {

        if (isEmpty())

            return null;

        else

            return stackElem[--top];

    }

    @Override

    public void display() {

        for (int i = top - 1; i >= 0; i--) {

            System.out.println(stackElem[i].toString() + "");

        }

    }


    public static void main(String[] args) throws Exception {
        SqlStack s = new SqlStack(3);
        s.push(1);
        s.push(2);
        s.push(3);
        s.display();
        s.pop();
        s.pop();
        s.display();
        s.push(4);
        s.display();
    }

}

